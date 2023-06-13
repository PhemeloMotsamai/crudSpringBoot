package com.digilink.crud.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.digilink.crud.entity.Customer;
import com.digilink.crud.repository.ICustomerRepo;
import java.util.List;
import org.springframework.stereotype.*;



/**
 *This class represents a service for managing customer-related operations.
 */
@org.springframework.stereotype.Service
public class Service {
        private final ICustomerRepo customerRepository;

        /**
        * Constructor for the Service class.
        * @param customerRepo The repository used to interact with the customer data.
        */
        public Service(ICustomerRepo customerRepo) {
            this.customerRepository = customerRepo;
        }

        /**
        * Retrieves all customers from the repository.
        * @return A list of all customers.
        */

        public List<Customer> findAllUsers() {
            return customerRepository.findAll();
        }

        /**
         *Retrieves a customer by their ID from the repository.
         * @param id The ID of the customer to retrieve.
         * @return The customer with the specified ID, or null if not found.
        */

        public Customer findUserById(Long id) {
            return customerRepository.findById(id).orElse(null);
        }
        /**
        * Creates a new customer and saves it to the repository.
        * @param customer The customer object to create.
        * @return The created customer object.
        */
        public Customer createUser(Customer customer) {

            String hashedPsw = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(12, customer.getPassword().toCharArray());
            customer.setPassword(hashedPsw);
            return customerRepository.save(customer);
        }

        /**
        *Checks if the provided password matches the hashed password.
        * @param password The password to check.
        * @param hashedPassword The hashed password to compare against.
        * @return true if the password is verified, false otherwise.
        */
        public Boolean checkPassword(String password, String hashedPassword) {
            return BCrypt.verifyer().verify(password.toCharArray(), hashedPassword).verified;
        }

        /**
        * Updates an existing customer in the repository.
        * @param customer The updated customer object.
        * @return The updated customer object.
        */

        public Customer updateUser(Customer customer) {

            String hashedPassword = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(12, customer.getPassword().toCharArray());
            customer.setPassword(hashedPassword);

            return customerRepository.save(customer);
        }
        /**
        * Deletes a customer from the repository by their ID.
        * @param id The ID of the customer to delete.
        */
        public void deleteUser(Long id) {
            customerRepository.deleteById(id);
        }

        /**

        * Returns a message indicating that an employee with the specified ID has been deleted.
        * @param customer The deleted customer object.
        * @return A message indicating the deletion of the customer.
        */
        public String deletedUser(Customer customer) {
            return "Employee with id " + customer.getId() + " has been deleted";
        }
    }
