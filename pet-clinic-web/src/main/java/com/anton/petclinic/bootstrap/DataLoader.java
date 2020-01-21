package com.anton.petclinic.bootstrap;

import com.anton.petclinic.model.*;
import com.anton.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDog = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCat = petTypeService.save(cat
        );

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        radiology.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        radiology.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Name 1");
        owner1.setLastName("Surname 1");
        owner1.setAddress("Address 1");
        owner1.setCity("City 1");
        owner1.setTelephone("1234567890");

        Pet pet1 = new Pet();
        pet1.setPetType(savedDog);
        pet1.setName("Pet Name 1");
        pet1.setOwner(owner1);
        pet1.setBirthDate(LocalDate.now());

        owner1.getPets().add(pet1);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Name 2");
        owner2.setLastName("Surname 2");
        owner2.setAddress("Address 2");
        owner2.setCity("City 2");
        owner2.setTelephone("0987654321");

        Pet pet2 = new Pet();
        pet2.setPetType(savedCat);
        pet2.setName("Pet Name 2");
        pet2.setOwner(owner2);
        pet2.setBirthDate(LocalDate.now());

        owner2.getPets().add(pet2);
        ownerService.save(owner2);

        Visit visit1 = new Visit();
        visit1.setPet(pet2);
        visit1.setDate(LocalDate.now());
        visit1.setDescription("Visit description 1");

        visitService.save(visit1);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Name 1");
        vet1.setLastName("Surname 1");
        vet1.getSpecialties().add(radiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Name 2");
        vet2.setLastName("Surname 2");
        vet2.getSpecialties().add(surgery);
        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
