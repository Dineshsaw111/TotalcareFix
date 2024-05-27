package com.totalcarefix.repos;

import com.totalcarefix.entities.Technicians;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechniciansRepo extends JpaRepository<Technicians,Integer> {
}
