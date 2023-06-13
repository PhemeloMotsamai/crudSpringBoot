package com.digilink.crud.controller;

import com.digilink.crud.entity.Customer;
import com.digilink.crud.service.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;



/**
 *This class represents a REST controller for managing users.
 */
@RestController
@RequestMapping("users")


public class CustomerController {
    // Service Class Declaration


    private final Service service;// Service Class Declaration

    /**
     * Constructor for the CustomerController class.
     * @param service The service used to perform user-related operations.
     */
    // Service Constructor
    public CustomerController(Service service) {
        this.service = service;
    }

    /**
     * Endpoint for creating a new customer.
     * @param customer The customer object to be created.
     * @return The created customer object.
     */
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

    /**
     * Endpoint for retrieving all customers.
     * @return A list of all customers.
     */

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

    /**
     * Endpoint for retrieving a customer by their ID.
     * @param id The ID of the customer to retrieve.
     * @return The customer with the specified ID.
     */
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


    /**
     * Endpoint for updating a customer.
     * @param id The ID of the customer to update.
     * @param customer The updated customer object.
     * @return The updated customer object.
     */

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

    /**
     * Endpoint for deleting a customer.
     * @param id The ID of the customer to delete.
     */
    @DeleteMapping("/employees/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        service.deleteUser(id);
        Customer user = service.findUserById(id);
        service.deletedUser(user);
    }

}

//END OF FILE