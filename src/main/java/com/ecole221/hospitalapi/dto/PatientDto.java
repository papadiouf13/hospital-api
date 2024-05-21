package com.ecole221.hospitalapi.dto;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private int idDto;
    private String codeDto;
    private String nomDto;
    private String prenomDto;
    private String telephoneDto;
    private String emailDto;
    private List<AntecedentDto> antecedentDto;

}
