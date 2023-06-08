package com.digilink.crud.controller;

import com.digilink.crud.entity.Customer;
import com.digilink.crud.repository.ICustomerRepo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    //Creating Instance Of Customer Repo Interface
    @Autowired
    ICustomerRepo customerRepo;


    //URI / Post mapping
    @PostMapping("/customers")

    //post method named "save"

    /*
     * Return type = Response Entity Generic
     * Method Parameter RequestBody of type Customer
     */
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {

        // Try Post Save (Create new Customer)
        try {
            return new ResponseEntity<>(customerRepo.save(customer), HttpStatus.CREATED);
        }
        //Catch Error

        /*
         * Catch Error When Post Request Fails
         */ catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    /*
     * Read Customers
     * Method Parameter RequestBody of type Customer
     */

    //URI localhost:8080/customers
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {

        // Try Get Read (Read All Customers)
        try {


            // Store into List
            List<Customer> List = customerRepo.findAll();

            //If Customers Storage Is Empty/Customers Do Not Exist
            if (List.isEmpty() || List.size() == 0) {

                //Return Response No Content
                return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
            }
            //Return Customer List
            return new ResponseEntity<List<Customer>>(List, HttpStatus.OK);
        }

        /*
         * Catch Error When Post Request Fails
         */ catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


    /*
     * Read Single Customer
     * Get Customer By ID
     * Method Parameter RequestBody of type Customer
     */
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getSingleCustomer(@PathVariable Long id) {

        //Searching Customer
        Optional<Customer> customer = customerRepo.findById(id);
        //Validating If Search Result Returns Value
        if(customer.isPresent()){

            //Return Customer Details
            return  new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
        }

        //Resource Not Found
        return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
    }

}
