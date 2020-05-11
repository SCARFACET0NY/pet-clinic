package com.anton.petclinic.services.springdatajpa;

import com.anton.petclinic.model.Owner;
import com.anton.petclinic.repositories.OwnerRepository;
import com.anton.petclinic.repositories.PetRepository;
import com.anton.petclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {
    public static final Long ID = 1L;
    public static final String LAST_NAME = "Smith";
    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;
    @InjectMocks
    OwnerJpaService ownerJpaService;
    Owner owner;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(ID).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(owner);
        Owner returnedOwner = ownerJpaService.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, returnedOwner.getLastName());
        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().id(ID).build());
        owners.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(owners);
        Set<Owner> returnedOwners = ownerJpaService.findAll();

        assertNotNull(returnedOwners);
        assertEquals(2, returnedOwners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner));
        Owner returnedOwner = ownerJpaService.findById(ID);
        assertNotNull(returnedOwner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
        Owner returnedOwner = ownerJpaService.findById(ID);
        assertNull(returnedOwner);
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(owner);

        Owner savedOwner = ownerJpaService.save(Owner.builder().id(ID).build());

        assertNotNull(savedOwner);
        assertEquals(ID, savedOwner.getId());
    }

    @Test
    void delete() {
        ownerJpaService.delete(owner);
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        ownerJpaService.deleteById(owner.getId());
        verify(ownerRepository, times(1)).deleteById(any());
    }
}