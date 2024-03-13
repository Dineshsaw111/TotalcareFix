package com.totalcarefix.Controllers;

import com.totalcarefix.DTO.RequestUtest;
import com.totalcarefix.Services.UtestSetvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
   private UtestSetvice utestSetvice;

    @PostMapping("/add")
    public void add(@RequestBody RequestUtest requestUtest){
        utestSetvice.addtest(requestUtest);
    }
}
