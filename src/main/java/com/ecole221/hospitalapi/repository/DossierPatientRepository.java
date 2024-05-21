package com.ecole221.hospitalapi.repository;

import com.ecole221.hospitalapi.model.DossierPatient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DossierPatientRepository extends JpaRepository<DossierPatient, Integer> {
}
