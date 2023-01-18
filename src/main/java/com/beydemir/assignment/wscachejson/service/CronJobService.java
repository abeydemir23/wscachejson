package com.beydemir.assignment.wscachejson.service;

import com.beydemir.assignment.wscachejson.repository.Subscriber;
import com.beydemir.assignment.wscachejson.repository.SubscriberRepository;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private SubscriberService subscriberService;
    private Environment environment;

    private Logger logger = LoggerFactory.getLogger(CronJobService.class);
    @Autowired
    public CronJobService(SubscriberService subscriberService, Environment environment) {
        this.subscriberService = subscriberService;
        this.environment = environment;
    }

    @Scheduled(cron = "${cron.expression}")
    public void doScheduled() throws IOException {
        String dataFile = environment.getProperty("data.file.location");
        List<Subscriber> subscribers = subscriberService.getAllSubscribers();
        ObjectMapper mapper = new ObjectMapper();
        logger.info("Subscribers : {} will be saved to File : {}", subscribers, dataFile);
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(dataFile), subscribers);
    }
}
