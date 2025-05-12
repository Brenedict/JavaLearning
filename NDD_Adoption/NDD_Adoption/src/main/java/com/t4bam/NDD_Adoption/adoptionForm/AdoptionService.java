package com.t4bam.NDD_Adoption.adoptionForm;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

// Tells spring that this class is a bean/auto instantiated when passed to the constructor. Component==Service (Semantics)
@Service
public class AdoptionService {
    public List<Adoption> getAdoption() {
        return List.of(new Adoption("1", "JB", 12, "Stud"));
    }
}
