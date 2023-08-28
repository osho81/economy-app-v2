package com.fmellberg.economyapp.customer;

import com.fmellberg.economyapp.customer.DTO.CustomerMapper;
import com.fmellberg.economyapp.exception.ResourceNotFoundException;
import com.fmellberg.economyapp.customer.DTO.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.toEntity(customerDTO); // Transform DTO-customer into model-customer
        logger.debug("Creating customer: {}", customer);
        Customer createdCustomer = customerRepository.save(customer); // Save created Customer
        logger.info("Customer created: {}", createdCustomer);
        return CustomerMapper.toCustomerDTO(createdCustomer); // Return CustomerDTO in response
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        logger.info("Total customers found: {}", customers.size());

        List<CustomerDTO> customerDTOS = new ArrayList<>();
        // Turn each customer from db, into a DTO
        for (Customer customer : customers) {
            CustomerDTO customerDTO = CustomerMapper.toCustomerDTO(customer);
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    public CustomerDTO getCustomerById(int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            CustomerDTO customerDTO = CustomerMapper.toCustomerDTO(customer.get());
            return customerDTO;
        } else {
            logger.error("Customer not found with ID: {}", id);
            throw new ResourceNotFoundException("Customer", "id", id);
        }
    }

    @Override // Added 20230715
    public CustomerDTO getCustomerByCustomerName(String customerName) {
        Optional<Customer> customer = customerRepository.findByCustomerName(customerName);
        if (customer.isPresent()) {
            CustomerDTO customerDTO = CustomerMapper.toCustomerDTO(customer.get());
            return customerDTO;
        } else {
            logger.error("Customer not found with customername: {}", customerName);

            // Must return/throw something if not found
            // Temporary solution: return 0 as id
            // since strings (customername) not accepted yet in ResourceNotFoundException class
            throw new ResourceNotFoundException("Customer", "id", 0);
        }
    }

    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        // Get Customer, and update by passed in CustomerDTO fields
        Optional<Customer> existingCustomerOptional = customerRepository.findById(customerDTO.getId());
        Optional<Customer> existingCustomerOptionalByEmail = customerRepository.findByCustomerName(customerDTO.getCustomerName());

        if (!existingCustomerOptional.isPresent() && !existingCustomerOptionalByEmail.isPresent()) {
            logger.error("Customer not found with ID: {}", customerDTO.getId());
            throw new ResourceNotFoundException("Customer", "id", customerDTO.getId());

        } else {
            Customer existingCustomer = existingCustomerOptional.isPresent() ? existingCustomerOptional.get() : existingCustomerOptionalByEmail.get();

            existingCustomer.setFirstName(customerDTO.getFirstName());
            existingCustomer.setLastName(customerDTO.getLastName());
            existingCustomer.setEmail(customerDTO.getEmail());
            existingCustomer.setCustomerName(customerDTO.getCustomerName());

            logger.debug("Updating customer: {}", existingCustomer);
            Customer updatedCustomer = customerRepository.save(existingCustomer); // save updated customer
            logger.info("Customer updated: {}", updatedCustomer);

            return CustomerMapper.toCustomerDTO(updatedCustomer); // Return as DTO
        }
    }

    public void deleteCustomer(int id) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            logger.debug("Deleting customer: {}", existingCustomer.get());
            customerRepository.deleteById(id);
            logger.info("Customer with ID {} deleted", id);
        } else {
            logger.error("Customer not found with ID: {}", id);
            throw new ResourceNotFoundException("Customer", "id", id);
        }
    }

}
