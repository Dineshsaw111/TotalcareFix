package com.totalcarefix.repos;

import com.totalcarefix.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users,Integer> {
    public Users findByEmail(String email);
}
