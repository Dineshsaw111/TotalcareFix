package com.totalcarefix.Repos;

import com.totalcarefix.Entities.Addresses;
import com.totalcarefix.Entities.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactsRepo extends JpaRepository<Contacts,Integer> {
    Contacts findByUserId(int userId);
}
