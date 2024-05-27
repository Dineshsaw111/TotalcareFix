package com.totalcarefix.repos;

import com.totalcarefix.entities.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactsRepo extends JpaRepository<Contacts,Integer> {
    Contacts findByUserId(int userId);
}
