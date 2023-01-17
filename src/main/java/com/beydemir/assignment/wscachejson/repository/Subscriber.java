package com.beydemir.assignment.wscachejson.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Subscriber {

    @Id
    private Long id;
    private String name;
    private String msisdn;

}
