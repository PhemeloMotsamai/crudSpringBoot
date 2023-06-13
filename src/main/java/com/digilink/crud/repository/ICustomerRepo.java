package com.digilink.crud.repository;

import com.digilink.crud.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 *This interface represents a repository for managing Customer entities.
 */
@Repository
public interface ICustomerRepo extends JpaRepository<Customer,Long> {
}
