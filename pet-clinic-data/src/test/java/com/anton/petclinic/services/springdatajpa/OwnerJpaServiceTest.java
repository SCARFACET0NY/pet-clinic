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
        owner = new Owner();
        owner.setId(1L);
        owner.setLastName(LAST_NAME);
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
        Owner owner1 = new Owner();
        Owner owner2 = new Owner();
        owner1.setId(1L);
        owner2.setId(2L);

        Set<Owner> owners = new HashSet<>();
        owners.add(owner1);
        owners.add(owner2);

        when(ownerRepository.findAll()).thenReturn(owners);
        Set<Owner> returnedOwners = ownerJpaService.findAll();

        assertNotNull(returnedOwners);
        assertEquals(2, returnedOwners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner));
        Owner returnedOwner = ownerJpaService.findById(1L);
        assertNotNull(returnedOwner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
        Owner returnedOwner = ownerJpaService.findById(1L);
        assertNull(returnedOwner);
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(owner);
        Owner ownerToSave = new Owner();
        ownerToSave.setId(1L);
        Owner savedOwner = ownerJpaService.save(ownerToSave);

        assertNotNull(savedOwner);
        assertEquals(1L, savedOwner.getId());
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