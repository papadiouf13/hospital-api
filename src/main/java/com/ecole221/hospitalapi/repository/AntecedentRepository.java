package com.ecole221.hospitalapi.repository;


import com.ecole221.hospitalapi.model.Antecedent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AntecedentRepository extends JpaRepository <Antecedent, Integer> {
    @Query("select a from Antecedent a where a not in :selectedAntecedent")
    public List<Antecedent> getAntecedentsByOtherList(List<Antecedent> selectedAntecedent);

    @Query("select a from Antecedent a where a in :selectedAntecedent")
    public List<Antecedent> getAntecedentsByList(List<Antecedent> selectedAntecedent);
}
