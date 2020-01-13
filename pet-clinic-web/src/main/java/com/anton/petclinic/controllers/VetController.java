package com.anton.petclinic.controllers;

import com.anton.petclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetController {
    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping("/vets")
    public String listVets() {
        return "vets/index";
    }
}
