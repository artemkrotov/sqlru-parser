package com.example.demo.services;

import com.example.demo.models.Message;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class ParseServiceImpl implements ParseService {

    @Autowired
    UserRepository userRepository;

    final private AtomicInteger atomicInteger = new AtomicInteger();
    @Override
    public Message startParse(PageRequest pageRequest) {

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (long i = 1; i < 9999999; i++) {
            executorService.submit(() -> parsePage(atomicInteger.incrementAndGet()));
        }

        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private void parsePage(long i) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {

            String url = "http://sql.ru/forum/memberinfo.aspx?mid=" + i; //7259
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = client.execute(httpGet);

            String body = EntityUtils.toString(response.getEntity());
            body = new String(body.getBytes(), "utf-8");
            String name = getName(body);
            // TODO: Выбрать другое условие
            if (name.length() <= 1) {
                return;
            }

            Long messageCount = getMessageCount(body);
            Long messageCountInPhp = getMessageCountInPhp(body);
            String city = getCity(body);
            User user = User.builder().id(i).name(name).messageCount(messageCount).city(city).messageCountInPhp(messageCountInPhp).build();
            log.info("Thread-{}: Saving {}", i, user);
            User save = userRepository.save(user);
            log.info("Thread-{}: Saved {}", i, save);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Message statusParse(PageRequest pageRequest) {
        return null;
    }

    @Override
    public Message startStop(PageRequest pageRequest) {
        return null;
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

    private String getName(String body) {
        String pattern = "Информация об участнике форума : (.*) /";
        return getString(pattern, body);
    }

    private String getCity(String body) {
        String pattern = "<td><strong>Откуда:<\\/strong><\\/td>\r\n" +
                "\t\t\t\t\t  <td>(.*)<\\/td>";
        return getString(pattern, body);
    }

    private String getString(String pattern, String body) {
        Matcher matcher = Pattern.compile(pattern).matcher(body);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }
}
