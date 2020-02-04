package ru.alikhano.cyberlife.supplier;

import ru.alikhano.cyberlife.dto.CustomerDTO;
import ru.alikhano.cyberlife.model.Customer;

import java.util.Collections;
import java.util.List;

public class CustomerSupplier {

    private static final String TEST_FIRST_NAME = "Anna";
    private static final String TEST_LAST_NAME  = "Johnson";
    private static final String TEST_BIRTH_DATE = "1994-10-26";
    private static final String TEST_EMAIL = "johnsonanna@gmail.com";

    public static List<Customer> getCustomers() {
        return Collections.singletonList(getCustomer());
    }

    public static List<CustomerDTO> getCustomersDTO() {
        return Collections.singletonList(getCustomerDTO());
    }

    public static Customer getCustomer() {
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName(TEST_FIRST_NAME);
        customer.setLastName(TEST_LAST_NAME);
        customer.setBirthDate(TEST_BIRTH_DATE);
        customer.setEmail(TEST_EMAIL);
        customer.setAddress(AddressSupplier.getAddress());

        return customer;
    }

    public static CustomerDTO getCustomerDTO() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(1);
        customerDTO.setFirstName(TEST_FIRST_NAME);
        customerDTO.setLastName(TEST_LAST_NAME);
        customerDTO.setBirthDate(TEST_BIRTH_DATE);
        customerDTO.setEmail(TEST_EMAIL);
        customerDTO.setAddress(AddressSupplier.getAddressDTO());

        return customerDTO;
    }
}
