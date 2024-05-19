package com.totalcarefix.Repos;

import com.totalcarefix.Entities.Skills;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillsRepo extends JpaRepository<Skills,Integer> {

    Skills findByName(String skill);
}
