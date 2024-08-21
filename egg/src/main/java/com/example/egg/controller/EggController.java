package com.example.egg.controller;

import com.example.egg.dto.EggRecapResponseDTO;
import com.example.egg.model.EggLog;
import com.example.egg.service.EggService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/egg")
public class EggController {
    @Autowired
    private EggService eggService;

    @PostMapping
    public ResponseEntity<EggLog> addRecipe(@RequestBody Integer quantity){
        return ResponseEntity.status(HttpStatus.CREATED).body(eggService.addEgg(quantity));
    }

    @GetMapping("/{date}")
    public ResponseEntity<EggRecapResponseDTO> getEggRecap(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return ResponseEntity.status(HttpStatus.OK).body(eggService.getLogRecap(date));
    }
}
