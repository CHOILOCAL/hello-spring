package com.damdeeng.webservice.common.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailDto {

    private String address;
    private String title;
    private String message;

}
