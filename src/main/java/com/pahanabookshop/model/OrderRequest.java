package com.pahanabookshop.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data

public class OrderRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String mobileNo;

    private String address;

    private String paymentType;
}
