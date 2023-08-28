package com.osho81.economyapp.customer;

import com.osho81.economyapp.customer.DTO.CustomerMapper;
import com.osho81.economyapp.exception.ResourceNotFoundException;
import com.osho81.economyapp.customer.DTO.CustomerDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createCustomer_ShouldReturnCreatedCustomerDTO() {
        // Prepare test data
        CustomerDTO customerDTO = new CustomerDTO(1,"John", "Doe", "john@example.com", "johndoe", "password");
        Customer createdCustomer = new Customer(1, "John", "Doe", "john@example.com", "johndoe", "password");

        // Mock the customerRepository behavior
        Mockito.when(customerRepository.save(any(Customer.class))).thenReturn(createdCustomer);

        // Perform the createCustomer operation
        CustomerDTO createdCustomerDTO = customerService.createCustomer(customerDTO);

        // Verify the customerRepository save method was called with the expected Customer object
        Mockito.verify(customerRepository, Mockito.times(1)).save(any(Customer.class));

        // Verify the returned CustomerDTO matches the expected values
        assertEquals(customerDTO, createdCustomerDTO);
    }

    @Test
    public void getAllCustomers_ShouldReturnListOfCustomerDTOs() {
        // Prepare test data
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "John", "Doe", "john@example.com", "johndoe", "password"));
        customers.add(new Customer(2, "Jane", "Smith", "jane@example.com", "janesmith", "password"));

        // Mock the customerRepository behavior
        Mockito.when(customerRepository.findAll()).thenReturn(customers);

        // Perform the getAllCustomers operation
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        // Verify the customerRepository findAll method was called
        Mockito.verify(customerRepository, Mockito.times(1)).findAll();

        // Verify the returned CustomerDTO list size and contents
        assertEquals(2, customerDTOS.size());
        Assertions.assertEquals(CustomerMapper.toCustomerDTO(customers.get(0)), customerDTOS.get(0));
        assertEquals(CustomerMapper.toCustomerDTO(customers.get(1)), customerDTOS.get(1));
    }

    @Test
    public void getCustomerById_ShouldReturnCustomerDTO() {
        // Prepare test data
        int customerId = 1;
        Customer customer = new Customer(customerId, "John", "Doe", "john@example.com", "johndoe", "password");
        CustomerDTO expectedCustomerDTO = CustomerMapper.toCustomerDTO(customer);

        // Mock the customerRepository behavior
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        // Perform the getCustomerById operation
        CustomerDTO retrievedCustomerDTO = customerService.getCustomerById(customerId);

        // Verify the customerRepository findById method was called
        Mockito.verify(customerRepository, Mockito.times(1)).findById(customerId);

        // Verify the returned CustomerDTO matches the expected CustomerDTO
        assertEquals(expectedCustomerDTO, retrievedCustomerDTO);
    }

    @Test
    public void getCustomerById_CustomerNotFound_ShouldThrowResourceNotFoundException() {
        // Prepare test data
        int customerId = 1;

        // Mock the customerRepository behavior
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // Perform the getCustomerById operation and assert that it throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> customerService.getCustomerById(customerId));

        // Verify the customerRepository findById method was called
        Mockito.verify(customerRepository, Mockito.times(1)).findById(customerId);
    }

    @Test
    public void updateCustomer_ShouldReturnUpdatedCustomerDTO() {
        // Prepare test data
        CustomerDTO customerDTO = new CustomerDTO(1, "John", "Doe", "john@example.com", "johndoe", "password");
        Customer existingCustomer = new Customer(1, "Existing", "Customer", "existing@example.com", "existingcustomer", "password");
        Customer updatedCustomer = new Customer(1, "John", "Doe", "john@example.com", "johndoe", "password");

        // Mock the customerRepository behavior
        Mockito.when(customerRepository.findById(customerDTO.getId())).thenReturn(Optional.of(existingCustomer));
        Mockito.when(customerRepository.save(existingCustomer)).thenReturn(updatedCustomer);

        // Perform the updateCustomer operation
        CustomerDTO updatedCustomerDTO = customerService.updateCustomer(customerDTO);

        // Verify the customerRepository findById method was called
        Mockito.verify(customerRepository, Mockito.times(1)).findById(customerDTO.getId());

        // Verify the customerRepository save method was called
        Mockito.verify(customerRepository, Mockito.times(1)).save(existingCustomer);

        // Verify the returned CustomerDTO matches the expected values
        assertEquals(customerDTO, updatedCustomerDTO);
    }

    @Test
    public void updateCustomer_CustomerNotFound_ShouldThrowResourceNotFoundException() {
        // Prepare test data
        CustomerDTO customerDTO = new CustomerDTO(1, "John", "Doe", "john@example.com", "johndoe", "password");

        // Mock the customerRepository behavior
        Mockito.when(customerRepository.findById(customerDTO.getId())).thenReturn(Optional.empty());

        // Perform the updateCustomer operation and assert that it throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> customerService.updateCustomer(customerDTO));

        // Verify the customerRepository findById method was called
        Mockito.verify(customerRepository, Mockito.times(1)).findById(customerDTO.getId());
    }

    @Test
    public void deleteCustomer_ShouldDeleteCustomer()  {
        // Prepare test data
        int customerId = 1;
        Customer existingCustomer = new Customer(customerId, "John", "Doe", "john@example.com", "johndoe", "password");

        // Mock the customerRepository behavior
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));

        // Perform the deleteCustomer operation
        customerService.deleteCustomer(customerId);

        // Verify the customerRepository findById method was called
        Mockito.verify(customerRepository, Mockito.times(1)).findById(customerId);

        // Verify the customerRepository deleteById method was called
        Mockito.verify(customerRepository, Mockito.times(1)).deleteById(customerId);
    }

    @Test
    public void deleteCustomer_CustomerNotFound_ShouldThrowResourceNotFoundException() {
        // Prepare test data
        int customerId = 1;

        // Mock the customerRepository behavior
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // Perform the deleteCustomer operation and assert that it throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> customerService.deleteCustomer(customerId));

        // Verify the customerRepository findById method was called
        Mockito.verify(customerRepository, Mockito.times(1)).findById(customerId);
    }
}