package com.totalcarefix.repos;

import com.totalcarefix.entities.Skills;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillsRepo extends JpaRepository<Skills,Integer> {

    Skills findByName(String skill);
}
