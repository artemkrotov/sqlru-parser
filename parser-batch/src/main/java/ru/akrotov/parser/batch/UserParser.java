package com.example.demo.services;

import com.example.demo.models.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class UserParser implements Parser<User> {

    @Autowired
    private Page page;

    final private String urlTemplate = "http://sql.ru/forum/memberinfo.aspx?mid=%s";

    @Override
    public User getById(long id) {

        String url = String.format(urlTemplate, id);
        String body = page.getBody(url);

        if (!validate(body)) {
           return null;
        }

        String name = getName(body);
        String city = getCity(body);
        Long messageCount = getMessageCount(body);
        Long messageCountInPhp = getMessageCountInPhp(body);

        return User.builder()
                .id(id)
                .name(name)
                .messageCount(messageCount)
                .city(city)
                .messageCountInPhp(messageCountInPhp)
                .build();

    }

    private boolean validate(String body) {
        return body.contains("Информация об авторе");
    }

    private String getName(String body) {
        String pattern = "Информация об участнике форума : (.*) /";
        return getString(pattern, body);
    }

    private String getCity(String body) {
        String pattern = "<td><strong>Откуда:<\\/strong><\\/td>\r\n" +
                "\t\t\t\t\t  <td>(.*)<\\/td>";
        return getString(pattern, body);
    }

    private Long getMessageCount(String body) {
        String pattern = "<td><strong>Сообщений:<\\/strong><\\/td>\r\n" +
                "\t\t\t\t\t  <td>(.*)<\\/td>";

        return Long.valueOf(getString(pattern, body));
    }

    private Long getMessageCountInPhp(String body) {
        String pattern = "PHP, Perl, Python<\\/td>\r\n\r\n" +
                "                  <td align=\"center\">(.*)<\\/td>";
        Long result = 0L;
        try {
            result = Long.valueOf(getString(pattern, body));
        } catch (Exception e) {
        }
        return result;
    }

    private String getString(String pattern, String body) {
        Matcher matcher = Pattern.compile(pattern).matcher(body);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

}
