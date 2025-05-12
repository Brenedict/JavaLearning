package com.t4bam.NDD_Adoption.adoptionForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping(path = "api/v1/adoption")
public class AdoptionController {

    private final AdoptionService adoptionService;

    // Magic instantiation of AdoptionService
    @Autowired
    public AdoptionController(AdoptionService adoptionService) {
        this.adoptionService = adoptionService;
    }

    // Calls the function within the AdoptionService
    @GetMapping
    public List<Adoption> getAdoption() {
        return adoptionService.getAdoption();
    }
}
