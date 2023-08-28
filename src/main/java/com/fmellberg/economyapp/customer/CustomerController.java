package com.fmellberg.economyapp.customer;

import com.fmellberg.economyapp.customer.DTO.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin(origins = "http://localhost:5173") // Allow VUE frontend
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        logger.info("Received a request to create a customer: {}", customerDTO);
        CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        logger.info("Received a request to retrieve all customers");
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers); // Returns customerDTOs
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable int id) {
        logger.info("Received a request to retrieve customer by ID: {}", id);
        CustomerDTO customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/customername/{customerName}") // Added 20230715
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String customerName) {
        logger.info("Received a request to retrieve customer by customername: {}", customerName);
        CustomerDTO customer = customerService.getCustomerByCustomerName(customerName);
        return ResponseEntity.ok(customer);
    }

    @PutMapping() // Removed redundant path var {id} 230625
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTO) { // Uses req body
//        logger.info("Received a request to update customer with ID: {}", customerDTO.getId());
        logger.info("Received a request to update customer with customerName: {}", customerDTO.getCustomerName());
        CustomerDTO updatedCustomer = customerService.updateCustomer(customerDTO);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        logger.info("Received a request to delete customer with ID: {}", id);
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
