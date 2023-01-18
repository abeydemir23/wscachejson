package com.beydemir.assignment.wscachejson.service;

import com.beydemir.assignment.wscachejson.repository.Subscriber;
import com.beydemir.assignment.wscachejson.repository.SubscriberRepository;
import com.beydemir.assignment.wscachejson.service.exception.SubscriberNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberService {
    private final SubscriberRepository repository;
    private CacheManager cacheManager;
    private final static Logger logger = LoggerFactory.getLogger(SubscriberService.class);


    @Autowired
    public SubscriberService(SubscriberRepository repository, CacheManager cacheManager) {
        this.repository = repository;
        this.cacheManager = cacheManager;
    }

    // get all records
    public List<Subscriber> getAllSubscribers() {
        logger.info("/subscriberService getAll");
        logger.info("Cache Status : {}", cacheManager.getCache("subscribersCache"));
        return repository.findAll();
    }

    // save all records
    public void saveAll(List<Subscriber> subscriberList) {
        logger.info("/subscriberService SaveAll");
        repository.saveAll(subscriberList);
    }

    // inserting new record
    @CachePut(cacheNames = {"subscribersCache"})
    public Subscriber addNewSubscriber(Subscriber subscriber) {
        Subscriber saved = repository.save(subscriber);
        logger.info("/subscriber[POST] id = {}, name = {}, msisdn = {}", saved.getId(), subscriber.getName(), subscriber.getMsisdn());
        logger.info("Cache Status : {}", cacheManager.getCache("subscribersCache"));
        return saved;
    }

    // find existing record
    @Cacheable(cacheNames = {"subscribersCache"}, key = "#subscriberId")
    public Subscriber findSubscriber(Long subscriberId) {
        logger.info("Cache Status : {}", cacheManager.getCache("subscribersCache").getNativeCache());
        return repository.findById(subscriberId).orElseThrow(() -> new SubscriberNotFoundException("Subscriber Not Found"));
    }

    // updating existing record
    @CachePut(cacheNames = {"subscribersCache"}, key="#subscriber.id")
    public Subscriber updateSubscriber(Subscriber subscriber) {
        logger.info("/subscriber[PUT] id = {}, name = {}, msisdn = {}", subscriber.getId(), subscriber.getName(), subscriber.getMsisdn());
        logger.info("Cache Status : {}", cacheManager.getCache("subscribersCache").getNativeCache());
        Subscriber subscriberRead = findSubscriber(subscriber.getId());
        subscriberRead.setName(subscriber.getName());
        subscriberRead.setMsisdn(subscriber.getMsisdn());
        return repository.save(subscriberRead);
    }

    // deleting existing record
    @CacheEvict(cacheNames = {"subscribersCache"}, key = "#subscriber.id")
    public Subscriber deleteSubscriber(Subscriber subscriber) {
        Subscriber subscriberRead = findSubscriber(subscriber.getId());
        logger.info("/subscriber[DELETE] id = {} ", subscriberRead.getId());
        logger.info("Cache Status : {}", cacheManager.getCache("subscribersCache").getNativeCache());
        repository.deleteById(subscriberRead.getId());
        return subscriberRead;
    }
}
