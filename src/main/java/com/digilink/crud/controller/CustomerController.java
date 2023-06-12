package com.digilink.crud.controller;

import com.digilink.crud.entity.Customer;
import com.digilink.crud.service.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("users")
public class CustomerController {
    // Service Class Declaration
    private final Service service;
    // Service Constructor
    public CustomerController(Service service) {
        this.service = service;
    }

    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer) {
        // Try Post Save (Create new Customer)
        try {
            return service.createUser(customer);
        }
         //Catch Error When Post Request Fails
          catch (Exception e) {

            System.out.println(HttpStatus.INTERNAL_SERVER_ERROR);
            return null;
        }
    }

    //URI localhost:8080/customers

    @GetMapping("/customers")
    public List<Customer> getAllUsers() {
        try {
            return service.findAllUsers();
        }
        catch (Exception e){

            System.out.println(HttpStatus.NO_CONTENT);
            return null;
        }
    }

    @GetMapping("/customers/{id}")
    public Customer getUserById(@PathVariable("id") Long id)  {
        try{
            System.out.println(HttpStatus.OK);
            return service.findUserById(id);
        }

        catch (Exception e){
            System.out.println(HttpStatus.NOT_FOUND);
            return null;
        }
    }
    @PutMapping("/customers/update/{id}")
    public Customer updateUser(@PathVariable("id") Long id, @RequestBody Customer customer) {
        customer.setId(id);
        try {
            return service.updateUser(customer);
        }
        catch (Exception e){
            System.out.println(HttpStatus.INTERNAL_SERVER_ERROR);
            return null;
        }
    }
    @DeleteMapping("/employees/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        service.deleteUser(id);
        Customer user = service.findUserById(id);
        service.deletedUser(user);
    }

}
