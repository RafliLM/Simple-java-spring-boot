package com.example.ticket.service;

import com.example.ticket.dto.TransactionRequestDTO;
import com.example.ticket.model.Ticket;
import com.example.ticket.model.Transaction;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransactionService {
    private List<Ticket> tickets = new ArrayList<Ticket>(Arrays.asList(
            new Ticket(1, "Adult", 50, 100000),
            new Ticket(2, "Teenager", 75, 75000),
            new Ticket(3, "Kid", 100, 50000)
    ));
    private List<Transaction> transactions = new ArrayList<Transaction>();

    public Transaction buyTickets(List<TransactionRequestDTO> createTransactionDTOList) throws Exception {
        Integer totalPrice = 0;
        List<Ticket> purchasedTickets = new ArrayList<Ticket>();
        for (TransactionRequestDTO tr: createTransactionDTOList) {
            Ticket ticket = tickets.stream().filter(t -> t.getTicketId().equals(tr.getTicketId())).findFirst().get();
            if (ticket.getStock() < tr.getQuantity())
                throw new BadRequestException("Quantity can't be more than stock");
            totalPrice += ticket.getPrice() * tr.getQuantity();
            ticket.setStock(ticket.getStock() - tr.getQuantity());
            purchasedTickets.add(ticket);
        }
        Transaction transaction = Transaction
                .builder()
                .transactionId(UUID.randomUUID())
                .tickets(purchasedTickets)
                .totalPrice(totalPrice)
                .transactionTime(new Date())
                .build();
        transactions.add(transaction);
        return transaction;
    }
}
