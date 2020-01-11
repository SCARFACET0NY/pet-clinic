package com.anton.petclinic.services;

import com.anton.petclinic.model.Owner;

import java.util.Set;

public interface OwnerService {
    Owner findByLastName();

    Owner findById(Long id);

    Owner save(Owner owner);

    Set<Owner> findAll();
}
