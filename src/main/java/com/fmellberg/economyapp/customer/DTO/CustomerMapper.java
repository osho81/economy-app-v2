package com.fmellberg.economyapp.customer.DTO;

import com.fmellberg.economyapp.customer.Customer;

// Maps db entity Customer.java with data transfer object CustomerDTo

public class CustomerMapper {

    // Maps Customer entity to CustomerDTO
    public static CustomerDTO toCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setCustomerName(customer.getCustomerName());
        customerDTO.setPassword(customer.getPassword());

        // Add savingsGoals (manyToOne) 230708
        customerDTO.setSavingGoals(customer.getSavingGoals());

        return customerDTO;
    }


    // Maps CustomerDTO to Customer entity
    public static Customer toEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setPassword(customerDTO.getPassword());
        return customer;
    }


}
