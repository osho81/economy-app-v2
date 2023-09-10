package com.osho81.economyapp.config;

// Not part of security implementation

// Modify/complete the mock data that are created by data.sql

// Update mockdata, to complete (missing fields in) data.sql

// Disabled/commented out om 230827; all fields in data.sql instead

//@Configuration
//public class MockdataConfig {
//    @Bean
//    CommandLineRunner commandLineRunner(CustomerRepository customerRepository, CustomerService customerService,
//                                        SavingsGoalRepository savingsGoalRepository, SavingsGoalService savingsGoalService) {
//        return args -> {
//            List<CustomerDTO> mockCustomers = customerService.getAllCustomers();
//
//            int days = 1; // Used to manipulate fictive time in timestamp/localDateTime hereunder
//            int hours = 10;
//
//            for (CustomerDTO customerDto : mockCustomers) {
//                Customer tempCustomer = customerRepository.findById(customerDto.getId()).get();
//                // Add a fictive creation date
//                tempCustomer.setCreatedAt(Timestamp.valueOf(LocalDateTime.now().minusDays(days).minusHours(hours)));
//                // Save same customer, i.e. update, just adding the creation date (& modification date)
//                customerRepository.save(tempCustomer);
//
//                // Next round, fictive time is created a day further away & 2 hours later
//                days++;
//                hours -= 2;
//            }
//
//            // Same update for savings-goals mockdata as for customers mockdata
//            List<SavingsGoalDTO> mockSavingsGoals = savingsGoalService.getAllSavingsGoals();
//            for (SavingsGoalDTO sgDto: mockSavingsGoals) {
//                SavingsGoal tempSg = savingsGoalRepository.findById(sgDto.getId()).get();
//                tempSg.setCreatedAt(Timestamp.valueOf(LocalDateTime.now().minusDays(1)));
//                savingsGoalRepository.save(tempSg);
//            }
//
//
//        };
//    }
//}
