package com.example.ticket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private UUID transactionId;
    private List<Ticket> tickets;
    private Integer totalPrice;
    private Date transactionTime;
}
