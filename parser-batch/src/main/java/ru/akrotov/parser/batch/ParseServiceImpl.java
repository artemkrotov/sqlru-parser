package com.example.demo.services;

import com.example.demo.models.Message;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.aspectj.runtime.internal.cflowstack.ThreadStackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class ParseServiceImpl implements ParseService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    Parser userParser;

    @Override
    public Message startParse(PageRequest pageRequest) {

        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("ProfileParser-%d")
                .setDaemon(true)
                .build();

        Long initId = userRepository.getMaxId() + 1;

        ExecutorService executorService = Executors.newFixedThreadPool(1, threadFactory);
        AtomicInteger atomicInteger = new AtomicInteger();

        for (long i = initId; i < 9999999; i++) {
            executorService.submit(() -> userParser.getById(atomicInteger.incrementAndGet()));
        }

        Long messageCount = getMessageCount(body);
        Long messageCountInPhp = getMessageCountInPhp(body);

           log.info("Thread-{}: Saving {}", i, user);
        User save = userRepository.save(user);
        log.info("Thread-{}: Saved {}", i, save);



        while (!executorService.isTerminated()) {
            try {
                if (true) {
                    executorService.shutdown();
                    ((ThreadPoolExecutor) executorService).get
                }
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Message statusParse(PageRequest pageRequest) {
        return null;
    }

    @Override
    public Message startStop(PageRequest pageRequest) {
        return null;
    }





}
