package com.totalcarefix.Services;

import com.totalcarefix.DTO.RequestUtest;
import com.totalcarefix.Entities.Utest;
import com.totalcarefix.Repos.UtestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class UtestSetvice {

    @Autowired
    private UtestRepo utestRepo;

    public  void addtest(RequestUtest requestUtest){
        Utest u1=Utest.builder()
                .name(requestUtest.getName())
                .creation_time(Timestamp.from(Instant.now()))
                .build();

        utestRepo.save(u1);
    }
}
