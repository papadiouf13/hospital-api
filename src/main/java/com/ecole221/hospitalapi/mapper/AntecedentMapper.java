package com.ecole221.hospitalapi.mapper;

import com.ecole221.hospitalapi.dto.AntecedentDto;
import com.ecole221.hospitalapi.dto.PatientDto;
import com.ecole221.hospitalapi.model.Antecedent;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AntecedentMapper {
    private final PatientMapper patientMapper;

    public AntecedentMapper(PatientMapper patientMapper) {
        this.patientMapper = patientMapper;
    }

    public AntecedentDto antecedentEntityToAntecedentDto(Antecedent antecedent){

        return AntecedentDto.builder()
                .idDto(antecedent.getId())
                .libelleDto(antecedent.getLibelle())
                .patientsDto(
                        antecedent.getPatients().stream().map(patientMapper::patientEntityToPatientDto).toList())
                .build();
    }

    public Antecedent antecedentDtoToAntecedentEntity(AntecedentDto antecedentDto){
        return Antecedent.builder()
                .id(antecedentDto.getIdDto())
                .libelle(antecedentDto.getLibelleDto())
                .patients(antecedentDto.getPatientsDto().stream().map(patientMapper::patientDtoToPatientEntity).toList())
                .build();
    }
}
