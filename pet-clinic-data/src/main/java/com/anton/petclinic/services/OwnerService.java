package com.anton.petclinic.services;

import com.anton.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName();
}
