//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.16 at 08:03:41 PM TRT 
//


package com.beydemir.assignment.wscachejson.webservice;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the io.spring.guides.gs_producing_web_service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: io.spring.guides.gs_producing_web_service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetSubscriberRequest }
     * 
     */
    public GetSubscriberRequest createGetSubscriberRequest() {
        return new GetSubscriberRequest();
    }

    /**
     * Create an instance of {@link GetSubscriberResponse }
     * 
     */
    public GetSubscriberResponse createGetSubscriberResponse() {
        return new GetSubscriberResponse();
    }

    /**
     * Create an instance of {@link Subscriber }
     * 
     */
    public Subscriber createSubscriber() {
        return new Subscriber();
    }

    /**
     * Create an instance of {@link GetAllSubscribersRequest }
     * 
     */
    public GetAllSubscribersRequest createGetAllSubscribersRequest() {
        return new GetAllSubscribersRequest();
    }

    /**
     * Create an instance of {@link GetAllSubscribersResponse }
     * 
     */
    public GetAllSubscribersResponse createGetAllSubscribersResponse() {
        return new GetAllSubscribersResponse();
    }

}