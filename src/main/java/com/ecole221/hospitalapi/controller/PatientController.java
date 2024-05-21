package com.ecole221.hospitalapi.controller;

import com.ecole221.hospitalapi.dto.PatientDto;
import com.ecole221.hospitalapi.mapper.AntecedentMapper;
import com.ecole221.hospitalapi.mapper.PatientMapper;
import com.ecole221.hospitalapi.model.Antecedent;
import com.ecole221.hospitalapi.model.DossierPatient;
import com.ecole221.hospitalapi.model.Patient;
import com.ecole221.hospitalapi.repository.AntecedentRepository;
import com.ecole221.hospitalapi.repository.DossierPatientRepository;
import com.ecole221.hospitalapi.repository.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/patient")
public class PatientController {
    private final AntecedentMapper antecedentMapper;
    private final PatientMapper patientMapper;
    private final AntecedentRepository antecedentRepository;
    private final PatientRepository patientRepository;
    private final DossierPatientRepository dossierPatientRepository;
    //liste des antecedent qu'il faut charger dans le select
    private List<Antecedent> antecedents;
    String action = "";

    public PatientController(AntecedentMapper antecedentMapper, PatientMapper patientMapper, AntecedentRepository antecedentRepository, PatientRepository patientRepository, DossierPatientRepository dossierPatientRepository) {
        this.antecedentMapper = antecedentMapper;
        this.patientMapper = patientMapper;
        this.antecedentRepository = antecedentRepository;
        this.patientRepository = patientRepository;
        this.dossierPatientRepository = dossierPatientRepository;
        antecedents = new ArrayList<>();
    }

   /* @GetMapping("/add")
    public String test(Model model){
        DossierPatient dossierPatient = dossierPatientRepository.findAll().get(0);
        String numero = String.format("%06d", dossierPatient.getNumero())+ LocalDate.now().getYear();
        Patient patient = new Patient();
        patient.setCode(numero);
        model.addAttribute("patient", patient);
        model.addAttribute("patients" , patientRepository.findAll());
        if (action.equals("edit")) {
            antecedents = new ArrayList<>();
            action = "add";
        }
        return "patient";
    }

    */
    @GetMapping("/numerodossier")
    public @ResponseBody String getNumeroDossier(){
        DossierPatient dossierPatient = dossierPatientRepository.findAll().get(0);
        return String.format("%06d", dossierPatient.getNumero())+ LocalDate.now().getYear();

    }
    @GetMapping("/antecedent")
    public @ResponseBody List<Antecedent> getAntecedents(){
    return antecedentRepository.getAntecedentsByOtherList(antecedents);
    }

    @GetMapping("/list")
    public @ResponseBody List<Patient> listPatient(){
        return patientRepository.findAll();
    }
    @GetMapping("/addantecedent/{id}")
    public @ResponseBody Antecedent addAntecedent(@PathVariable int id){
        Optional<Antecedent> antecedent = antecedentRepository.findById(id);
        if (antecedent.isPresent()) {
            Antecedent antecedent1 = antecedent.get();
            antecedents.add(antecedent1);
            return antecedent1;
        }
        return null;
    }

    @GetMapping("/allSelectedantecedent")
    public @ResponseBody List<Antecedent> allSelectedantecedent (){
        return antecedents;
    }

    @GetMapping("/antecedent/delete/{libelle}")
    public @ResponseBody List<Antecedent> removeAntecedent (@PathVariable String libelle){
        Optional<Antecedent> ant = antecedents.stream().filter(antecedent -> antecedent.getLibelle().equals(libelle)).findFirst();
        if (ant.isPresent()) {
            antecedents.remove(ant.get());
        }
        return antecedents;
    }

    @PostMapping("/add")
    public @ResponseBody Patient addPatient(@RequestBody PatientDto patientDto){
        DossierPatient dossierPatient = dossierPatientRepository.findAll().get(0);
        patientDto.setCodeDto(String.format("%06d", dossierPatient.getNumero())+ LocalDate.now().getYear());
        Patient patient = patientMapper.patientDtoToPatientEntity(patientDto);
        patient.setAntecedents(antecedentRepository.getAntecedentsByList(patient.getAntecedents()));
        patientRepository.save(patient);
        return patient;
    }

    @PutMapping("/update/{id}")
    public @ResponseBody Patient updatePatient(@RequestBody Patient patient, @PathVariable int id){
        Patient patient1 = patientRepository.findById(id).orElse(null);
        patient1.setEmail(patient.getEmail());
        patient1.setNom(patient.getNom());
        patientRepository.save(patient1);
        return patient;
    }


    @GetMapping("/edit/{id}")
    public String editPatient(@PathVariable int id, Model model){
        Patient patient = patientRepository.findById(id).get();
        antecedents = patient.getAntecedents();
        model.addAttribute("patient", patient);
        model.addAttribute("patients" , patientRepository.findAll());
        model.addAttribute("antecedents", antecedents);
        action = "edit";
        return "patient";
    }
    @GetMapping("/remove/{id}")
    public String removePatient(@PathVariable int id, Model model){
        Patient patient = patientRepository.findById(id).get();
        patientRepository.delete(patient);
        return "redirect:/patient/add";
    }

    @DeleteMapping("/delete/{id}")
    public  Boolean deletePatient(@PathVariable int id){
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null) {
            return false;
        }
        patientRepository.delete(patient);
        return true;
    }

}
