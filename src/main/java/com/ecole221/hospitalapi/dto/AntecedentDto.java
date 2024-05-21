package com.ecole221.hospitalapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AntecedentDto {

    private int idDto;
    private String libelleDto;
    private List<PatientDto> patientsDto;


}
