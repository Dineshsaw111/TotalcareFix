package com.totalcarefix.Repos;

import com.totalcarefix.Entities.roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repo extends JpaRepository<roles,Integer> {
}
