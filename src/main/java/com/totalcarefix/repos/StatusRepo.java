package com.totalcarefix.repos;

import com.totalcarefix.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepo extends JpaRepository<Status,Integer> {
}
