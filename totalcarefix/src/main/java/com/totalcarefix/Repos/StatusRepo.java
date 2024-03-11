package com.totalcarefix.Repos;

import com.totalcarefix.Entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepo extends JpaRepository<Status,Integer> {
}
