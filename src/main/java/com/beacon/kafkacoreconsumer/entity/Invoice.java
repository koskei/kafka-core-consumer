package com.beacon.kafkacoreconsumer.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Invoice {

    private String invoiceNumber;
    private int amount;
    private String currency;

}
