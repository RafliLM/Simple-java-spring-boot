package com.example.ticket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private Integer ticketId;

    private String ticketType;

    @JsonIgnore
    private Integer stock;

    private Integer price;
}
