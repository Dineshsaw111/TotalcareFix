package com.totalcarefix.Repos;

import com.totalcarefix.Entities.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressesRepo extends JpaRepository<Addresses,Integer> {
}
