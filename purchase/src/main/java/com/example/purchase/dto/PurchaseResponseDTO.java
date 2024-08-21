package com.example.purchase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PurchaseResponseDTO {
    private List<ItemDTO> items;
    private Integer totalPrice;
}
