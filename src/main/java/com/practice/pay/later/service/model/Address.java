package com.practice.pay.later.service.model;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Address {

    private String street;
    private String city;
    private String state;
    private String pin;
}
