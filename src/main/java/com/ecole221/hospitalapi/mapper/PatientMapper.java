package com.ecole221.hospitalapi.mapper;

import com.ecole221.hospitalapi.dto.AntecedentDto;
import com.ecole221.hospitalapi.dto.PatientDto;
import com.ecole221.hospitalapi.model.Antecedent;
import com.ecole221.hospitalapi.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {
    public PatientDto patientEntityToPatientDto(Patient patient){
        return PatientDto.builder()
                .idDto(patient.getId())
                .nomDto(patient.getNom())
                .prenomDto(patient.getPrenom())
                .emailDto(patient.getEmail())
                .codeDto(patient.getCode())
                .telephoneDto(patient.getTelephone())
                .antecedentDto(
                        patient.getAntecedents().stream().map(a->AntecedentDto.builder()
                                .idDto(a.getId())
                                .libelleDto(a.getLibelle())
                                .build()).toList()
                )
                .build();
    }

    public Patient patientDtoToPatientEntity(PatientDto patientDto){
        return Patient.builder()
                .id(patientDto.getIdDto())
                .nom(patientDto.getNomDto())
                .prenom(patientDto.getPrenomDto())
                .email(patientDto.getEmailDto())
                .telephone(patientDto.getTelephoneDto())
                .code(patientDto.getCodeDto())
                .antecedents(patientDto.getAntecedentDto().stream().map(a->Antecedent.builder()
                        .id(a.getIdDto())
                        .libelle(a.getLibelleDto())
                        .build()).toList())
                .build();
    }
}
