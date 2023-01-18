package com.beydemir.assignment.wscachejson.service;

import com.beydemir.assignment.wscachejson.repository.Subscriber;
import com.beydemir.assignment.wscachejson.repository.SubscriberRepository;
import com.beydemir.assignment.wscachejson.service.exception.SubscriberNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberService {
    private final SubscriberRepository repository;
    private final static Logger logger = LoggerFactory.getLogger(SubscriberService.class);


    @Autowired
    public SubscriberService(SubscriberRepository repository) {
        this.repository = repository;
    }

    // get all records

    public List<Subscriber> getAllSubscribers() {
        logger.info("/subscriberService getAll");
        return repository.findAll();
    }

    // save all records
    public void saveAll(List<Subscriber> subscriberList) {
        logger.info("/subscriberService SaveAll");
        repository.saveAll(subscriberList);
    }

    // inserting new record
    public Subscriber addNewSubscriber(Subscriber subscriber) {
        Subscriber saved = repository.save(subscriber);
        logger.info("/subscriber[POST] id = {}, name = {}, msisdn = {}", saved.getId(), subscriber.getName(), subscriber.getMsisdn());
        return saved;
    }

    // find existing record
    @Cacheable(cacheNames = {"subscriberCache"}, key = "#subscriberId")
    public Subscriber findSubscriber(Long subscriberId) {
        // simulating backend querying delay
        return repository.findById(subscriberId).get();
    }

    // updating existing record
    @CachePut(cacheNames = {"subscriberCache"}, key="#subscriber.subscriberId")
    public Subscriber updateSubscriber(Subscriber subscriber) {
        logger.info("/subscriber[PUT] id = {}, name = {}, msisdn = {}", subscriber.getId(), subscriber.getName(), subscriber.getMsisdn());

        Subscriber subscriberRead = repository.findById(subscriber.getId()).orElseThrow(() -> new SubscriberNotFoundException("Subscriber Not Found"));
        subscriberRead.setName(subscriber.getName());
        subscriberRead.setMsisdn(subscriber.getMsisdn());
        return repository.save(subscriberRead);
    }

    // deleting existing record
    @CacheEvict(cacheNames = {"subscriberCache"}, key = "#subscriberId")
    public Subscriber deleteSubscriber(Subscriber subscriber) {
        Subscriber subscriberRead = repository.findById(subscriber.getId()).orElseThrow(() -> new SubscriberNotFoundException("Subscriber Not Found"));
        logger.info("/subscriber[DELETE] id = {} ", subscriberRead.getId());
        repository.deleteById(subscriberRead.getId());
        return subscriberRead;
    }
}
