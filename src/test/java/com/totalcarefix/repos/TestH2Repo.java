package com.totalcarefix.repos;

import com.totalcarefix.entities.roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repo extends JpaRepository<roles,Integer> {
}
