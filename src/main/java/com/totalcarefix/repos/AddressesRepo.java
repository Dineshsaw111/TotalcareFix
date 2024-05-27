package com.totalcarefix.repos;

import com.totalcarefix.entities.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressesRepo extends JpaRepository<Addresses,Integer> {
}
