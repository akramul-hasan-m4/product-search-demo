package com.quebic.productsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.lovi.searchbox.annotation.EnableSearchBox;

/**
 * 
 * @author Tharanga
 *
 */
@SpringBootApplication
@EnableSearchBox
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args); 
    }
}
