package com.osho81.economyapp.customer;

import com.osho81.economyapp.customer.DTO.CustomerDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(int id);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(int id);

    CustomerDTO getCustomerByCustomerName(String customername);
}
