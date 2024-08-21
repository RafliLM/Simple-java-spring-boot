package com.example.purchase.controller;

import com.example.purchase.dto.PurchaseResponseDTO;
import com.example.purchase.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/{budget}")
    public ResponseEntity<?> spendBudget(@PathVariable Integer budget){
        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.optimal(budget));
    }

}
