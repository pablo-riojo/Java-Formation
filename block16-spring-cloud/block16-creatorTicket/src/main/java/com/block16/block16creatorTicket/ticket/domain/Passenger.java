package com.block16.block16creatorTicket.ticket.domain;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Passenger {
    private UUID id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String phone;
    private Date createdAt = new Date();
    private Date updatedAt;
}