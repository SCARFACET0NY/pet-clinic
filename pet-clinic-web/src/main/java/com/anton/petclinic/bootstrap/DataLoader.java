package com.anton.petclinic.bootstrap;

import com.anton.petclinic.model.Owner;
import com.anton.petclinic.model.PetType;
import com.anton.petclinic.model.Vet;
import com.anton.petclinic.services.OwnerService;
import com.anton.petclinic.services.PetTypeService;
import com.anton.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDog = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCat = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Name 1");
        owner1.setLastName("Surname 1");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Name 2");
        owner2.setLastName("Surname 2");
        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Name 1");
        vet1.setLastName("Surname 1");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Name 2");
        vet2.setLastName("Surname 2");
        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
