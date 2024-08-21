package com.example.ticket.controller;

import com.example.ticket.dto.TransactionRequestDTO;
import com.example.ticket.model.Transaction;
import com.example.ticket.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/create")
    public ResponseEntity<Transaction> buyTickets(@RequestBody List<TransactionRequestDTO> transactionDTOList) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.buyTickets(transactionDTOList));
    }
}
