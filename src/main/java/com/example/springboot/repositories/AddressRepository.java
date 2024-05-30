package com.example.springboot.repositories;

import com.example.springboot.models.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, UUID> {
}
