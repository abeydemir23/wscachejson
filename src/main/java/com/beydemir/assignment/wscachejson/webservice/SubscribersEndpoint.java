package com.beydemir.assignment.wscachejson.webservice;

import com.beydemir.assignment.wscachejson.repository.Subscriber;
import com.beydemir.assignment.wscachejson.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class SubscribersEndpoint {

    private SubscriberService subscriberService;
    private final static Logger logger = LoggerFactory.getLogger(SubscribersEndpoint.class);


    @Autowired
    public SubscribersEndpoint(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetSubscriberRequest")
    @ResponsePayload
    public GetSubscriberResponse getSubscriberById(@RequestPayload GetSubscriberRequest request) {
        logger.info("/getSubscriberById is = {}", request.getId());
        GetSubscriberResponse response = new GetSubscriberResponse();
        Subscriber subscriber = subscriberService.findSubscriber(request.getId());
        com.beydemir.assignment.wscachejson.webservice.Subscriber returnVal = new com.beydemir.assignment.wscachejson.webservice.Subscriber();
        returnVal.setId(subscriber.getId());
        returnVal.setName(subscriber.getName());
        returnVal.setMsisdn(subscriber.getMsisdn());
        response.setSubscriber(returnVal);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllSubscribersRequest")
    @ResponsePayload
    public GetAllSubscribersResponse getAllSubscribers(@RequestPayload GetAllSubscribersRequest request) {
        logger.info("/getAllSubscribers");

        GetAllSubscribersResponse response = new GetAllSubscribersResponse();
        List<Subscriber> subscribers = subscriberService.getAllSubscribers();
        List<com.beydemir.assignment.wscachejson.webservice.Subscriber> returnListVal = new ArrayList<>();

        for(Subscriber subscriberElement : subscribers) {
            com.beydemir.assignment.wscachejson.webservice.Subscriber returnVal = new com.beydemir.assignment.wscachejson.webservice.Subscriber();
            returnVal.setId(subscriberElement.getId());
            returnVal.setName(subscriberElement.getName());
            returnVal.setMsisdn(subscriberElement.getMsisdn());
            returnListVal.add(returnVal);
        }
        response.setSubscriber(returnListVal);
        return response;
    }
}
