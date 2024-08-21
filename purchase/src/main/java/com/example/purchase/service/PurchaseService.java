package com.example.purchase.service;

import com.example.purchase.dto.ItemDTO;
import com.example.purchase.dto.PurchaseResponseDTO;
import com.example.purchase.model.Item;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PurchaseService {
    private List<Item> items = new ArrayList<Item>(Arrays.asList(
            new Item(UUID.randomUUID(), "Mouse", 12000),
            new Item(UUID.randomUUID(), "Mouse", 20000),
            new Item(UUID.randomUUID(), "Mouse", 35000),
            new Item(UUID.randomUUID(), "Keyboard", 50000),
            new Item(UUID.randomUUID(), "Keyboard", 75000)
    ));
    public List<ItemDTO> optimal(int budget) {
        List<ItemDTO> bestCombination = new ArrayList<>();
        List<List<ItemDTO>> allCombinations = new ArrayList<>();

        generateCombinations(new ArrayList<>(), 0, budget, allCombinations);

        int bestTotal = 0;
        for (List<ItemDTO> combination : allCombinations) {
            int totalCost = combination.stream().mapToInt(dto -> dto.getPrice() * dto.getQuantity()).sum();
            if (totalCost <= budget && totalCost > bestTotal) {
                bestTotal = totalCost;
                bestCombination = combination;
            }
        }
        return bestCombination.stream().filter(combination -> combination.getQuantity() > 0).toList();
    }

    private void generateCombinations(List<ItemDTO> currentCombination, int currentIndex, int budget, List<List<ItemDTO>> allCombinations) {
        if (currentIndex >= items.size()) {
            allCombinations.add(new ArrayList<>(currentCombination));
            return;
        }

        Item currentItem = items.get(currentIndex);
        int maxQuantity = budget / currentItem.getPrice();

        for (int quantity = 0; quantity <= maxQuantity; quantity++) {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setItemName(currentItem.getItemName());
            itemDTO.setPrice(currentItem.getPrice());
            itemDTO.setQuantity(quantity);
            currentCombination.add(itemDTO);

            generateCombinations(currentCombination, currentIndex + 1, budget - (quantity * currentItem.getPrice()), allCombinations);

            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}
