package com.totalcarefix.Services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class calcTest {
    @Test
    void addsum(){
        calc c =new calc();
        assertEquals(4,c.add(2,2));
    }
}
