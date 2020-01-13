package com.anton.petclinic.bootstrap;

import com.anton.petclinic.model.Owner;
import com.anton.petclinic.model.Vet;
import com.anton.petclinic.services.OwnerService;
import com.anton.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Name 1");
        owner1.setLastName("Surname 1");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Name 2");
        owner2.setLastName("Surname 2");
        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Name 1");
        vet1.setLastName("Surname 1");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(1L);
        vet2.setFirstName("Name 2");
        vet2.setLastName("Surname 2");
        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
