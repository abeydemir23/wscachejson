package com.beydemir.assignment.wscachejson.controller;


import com.beydemir.assignment.wscachejson.service.exception.SubscriberNotFoundException;
import com.beydemir.assignment.wscachejson.repository.Subscriber;
import com.beydemir.assignment.wscachejson.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class SubscriberController {

    private final SubscriberService service;

    @Autowired
    public SubscriberController(SubscriberService service) {
        this.service = service;
    }

    @PostMapping("/subscriber")
    public ResponseEntity<?> addSubscriber(@RequestBody Subscriber subscriber) {
        return new ResponseEntity<>(service.addNewSubscriber(subscriber), HttpStatus.OK);
    }

    @PutMapping("/subscriber")
    public ResponseEntity<?> updateSubscriber(@RequestBody Subscriber subscriber) {
        return new ResponseEntity<>(service.updateSubscriber(subscriber), HttpStatus.OK);
    }

    @DeleteMapping("/subscriber")
    public ResponseEntity<?> deleteSubscriber(@RequestBody Subscriber subscriber) {
        return new ResponseEntity<>(service.deleteSubscriber(subscriber), HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleSubscriberNotFoundException(
            SubscriberNotFoundException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

}
