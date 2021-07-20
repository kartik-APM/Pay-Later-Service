package com.practice.pay.later.service.dto;

import com.practice.pay.later.service.model.Address;
import lombok.Data;

@Data
public class UserDTO {

    private Long userId;
    private String Name;
    private String emailId;
    private String phoneNumber;
    private Address address;

}
