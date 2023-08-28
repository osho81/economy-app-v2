package com.osho81.economyapp.customer;


import com.osho81.economyapp.customer.DTO.CustomerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createCustomer_ShouldReturnCreatedCustomerAndResponseCREATED() {
        // Prepare test data
        int id = 1;
        String firstName = "John";
        String lastName = "Doe";
        String email = "johndoe@example.com";
        String customerName = "johndoe";
        String password = "password";

        // Create CustomerDTO with constructor
        CustomerDTO customerDTO = new CustomerDTO(id, firstName, lastName, email, customerName, password);

        // Mock the CustomerService behavior
        CustomerDTO createdCustomerDTO = new CustomerDTO(id, firstName, lastName, email, customerName, password);
        Mockito.when(customerService.createCustomer(customerDTO)).thenReturn(createdCustomerDTO);

        // Perform the request to the CustomerController
        ResponseEntity<CustomerDTO> response = customerController.createCustomer(customerDTO);

        // Verify the CustomerService method was called
        Mockito.verify(customerService, Mockito.times(1)).createCustomer(customerDTO);

        // Verify the response status code and body
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdCustomerDTO, response.getBody());
    }



    @Test
    void getAllCustomers_ShouldReturnListOfCustomersAndResponseOK() {
        // Prepare test data
        CustomerDTO customer1 = new CustomerDTO(1, "John", "Doe", "john@example.com", "johndoe", "password");
        CustomerDTO customer2 = new CustomerDTO(2, "Jane", "Smith", "jane@example.com", "janesmith", "password");
        List<CustomerDTO> expectedCustomers = Arrays.asList(customer1, customer2);

        // Mock the CustomerService behavior
        Mockito.when(customerService.getAllCustomers()).thenReturn(expectedCustomers);

        // Perform the request to the CustomerController
        ResponseEntity<List<CustomerDTO>> response = customerController.getAllCustomers();

        // Verify the CustomerService method was called
        Mockito.verify(customerService, Mockito.times(1)).getAllCustomers();

        // Verify the response status code and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCustomers, response.getBody());
    }

    @Test
    void getCustomerById_ShouldReturnCustomerAndResponseOK() {
        // Prepare test data
        int customerId = 1;
        CustomerDTO expectedCustomer = new CustomerDTO(customerId, "John", "Doe", "john@example.com", "johndoe", "password");

        // Mock the CustomerService behavior
        Mockito.when(customerService.getCustomerById(customerId)).thenReturn(expectedCustomer);

        // Perform the request to the CustomerController
        ResponseEntity<CustomerDTO> response = customerController.getCustomerById(customerId);

        // Verify the CustomerService method was called
        Mockito.verify(customerService, Mockito.times(1)).getCustomerById(customerId);

        // Verify the response status code and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCustomer, response.getBody());
    }

    @Test
    void updateCustomer_ShouldReturnUpdatedCustomerAndResponseOK() {
        // Prepare test data
        int customerId = 1;
        CustomerDTO customerDTO = new CustomerDTO(customerId, "John", "Doe", "john@example.com", "johndoe", "password");
        CustomerDTO updatedCustomer = new CustomerDTO(customerId, "John", "Doe", "updated@example.com", "johndoe", "password");

        // Mock the CustomerService behavior
        Mockito.when(customerService.updateCustomer(customerDTO)).thenReturn(updatedCustomer);

        // Perform the request to the CustomerController
        ResponseEntity<CustomerDTO> response = customerController.updateCustomer(customerDTO);

        // Verify the CustomerService method was called
        Mockito.verify(customerService, Mockito.times(1)).updateCustomer(customerDTO);

        // Verify the response status code and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedCustomer, response.getBody());
    }

    @Test
    void deleteCustomer_ShouldReturnResponseNoContent() {
        // Prepare test data
        int customerId = 1;
        //hejhej
        // Perform the request to the CustomerController
        ResponseEntity<Void> response = customerController.deleteCustomer(customerId);

        // Verify the CustomerService method was called
        Mockito.verify(customerService, Mockito.times(1)).deleteCustomer(customerId);

        // Verify the response status code
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }
}