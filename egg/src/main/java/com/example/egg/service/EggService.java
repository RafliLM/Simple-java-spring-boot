package com.example.egg.service;

import com.example.egg.dto.EggRecapResponseDTO;
import com.example.egg.model.EggLog;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EggService {
    List<EggLog> eggLogs = new ArrayList<EggLog>();
    public EggLog addEgg(Integer quantity){
        EggLog eggLog = new EggLog(UUID.randomUUID(), quantity, new Date());
        this.eggLogs.add(eggLog);
        return eggLog;
    }

    public EggRecapResponseDTO getLogRecap(Date day){
        List<EggLog> recap = this.eggLogs.stream().filter(egglog -> egglog.getLogDate().getDay() == day.getDay()).toList();

        return EggRecapResponseDTO.builder()
                .recap(recap)
                .totalQuantity(recap.stream().mapToInt(EggLog::getQuantity).sum())
                .build();
    }
}
