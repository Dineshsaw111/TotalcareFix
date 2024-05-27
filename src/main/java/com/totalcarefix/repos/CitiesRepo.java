package com.totalcarefix.repos;

import com.totalcarefix.entities.Cities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitiesRepo extends JpaRepository<Cities,Integer> {
}
