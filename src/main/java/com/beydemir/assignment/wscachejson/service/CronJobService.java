package com.beydemir.assignment.wscachejson.service;

import com.beydemir.assignment.wscachejson.repository.Subscriber;
import com.beydemir.assignment.wscachejson.repository.SubscriberRepository;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
@EnableScheduling
public class CronJobService {

    private SubscriberRepository subscriberRepository;
    private Environment environment;
    @Autowired
    public CronJobService(SubscriberRepository subscriberRepository, Environment environment) {
        this.subscriberRepository = subscriberRepository;
        this.environment = environment;
    }

    @Scheduled(cron = "${cron.expression}")
    public void doScheduled() throws IOException {
        String dataFile = environment.getProperty("data.file.location");
        List<Subscriber> subscribers = subscriberRepository.findAll();
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(dataFile), subscribers);
    }
}
