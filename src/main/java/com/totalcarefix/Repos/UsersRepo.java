package com.totalcarefix.Repos;

import com.totalcarefix.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users,Integer> {
    public Users findByEmail(String email);
}
