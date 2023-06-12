package com.digilink.crud.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.digilink.crud.entity.Customer;
import com.digilink.crud.repository.ICustomerRepo;
import java.util.List;
import org.springframework.stereotype.*;

@org.springframework.stereotype.Service
public class Service {
        private final ICustomerRepo customerRepository;

        public Service(ICustomerRepo userRepository) {
            this.customerRepository = userRepository;
        }

        public List<Customer> findAllUsers() {
            return customerRepository.findAll();
        }

        public Customer findUserById(Long id) {
            return customerRepository.findById(id).orElse(null);
        }

        public Customer createUser(Customer customer) {

            String hashedPsw = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(12, customer.getPassword().toCharArray());
            customer.setPassword(hashedPsw);
            return customerRepository.save(customer);
        }

        public Boolean checkPassword(String password, String hashedPassword) {
            return BCrypt.verifyer().verify(password.toCharArray(), hashedPassword).verified;
        }

        public Customer updateUser(Customer customer) {

            String hashedPassword = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(12, customer.getPassword().toCharArray());
            customer.setPassword(hashedPassword);

            return customerRepository.save(customer);
        }

        public void deleteUser(Long id) {
            customerRepository.deleteById(id);
        }

        public String deletedUser(Customer customer) {
            return "Employee with id " + customer.getId() + " has been deleted";
        }
    }
