package com.example.egg.dto;

import com.example.egg.model.EggLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EggRecapResponseDTO {
    private List<EggLog> recap;
    private Integer totalQuantity;
}
