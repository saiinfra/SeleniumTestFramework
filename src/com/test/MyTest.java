package com.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MyTest{

    @Test
    public void test(){
        int i=5;
        int j=5;
        assertTrue(i==j);
    }

    @Test
    public void test2(){
        int i=5;
        int j=15;
        assertTrue(i!=j);
    }
}   