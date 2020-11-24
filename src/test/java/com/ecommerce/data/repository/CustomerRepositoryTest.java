package com.ecommerce.data.repository;

import com.ecommerce.data.model.Address;
import com.ecommerce.data.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressRepository addressRepository;

    Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
    }


    @Test
    void testThatWeCanSaveCustomer(){
        customer.setContactNo("09099887766");
        customer.setEmail("cust@yahoo.com");
        customer.setFirstName("John");
        customer.setLastName("Bull");
        customer.setPassword("12321");

        Address address = new Address();
        address.setZipcode("100123");
        address.setCountry("Nigeria");
        address.setState("Lagos");
        address.setCity("Ikeja");
        address.setStreet("Balogun");

        customer.setAddresses(address);

        log.info("Customer before saving --> {}", customer);

        customerRepository.save(customer);
        assertThat(customer.getId()).isNotNull();

        log.info("Customer after saving  -->{}", customer);

    }

    @Test
    void testThatTwoCustomersCanShareOneAddress(){
        customer.setContactNo("09099884433");
        customer.setEmail("cjay@yahoo.com");
        customer.setFirstName("Tony");
        customer.setLastName("Stephen");
        customer.setPassword("100211");

        Address address = addressRepository.findById(5).orElse(null);
        customer.setAddresses(address);

        customerRepository.save(customer);
        log.info("customer --> {}", customer);

        assertThat(customer.getId()).isNotNull();
        assertThat(customer.getAddresses()).isNotEmpty();

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void testThatOneCustomerCanHaveMultipleAddresses(){
        customer =  customerRepository.findById(1).orElse(null);
        Address address = addressRepository.findById(5).orElse(null);

        customer.setAddresses(address);

        customerRepository.save(customer);

        log.info("customer --> {}", customer);

        assertThat(customer.getAddresses().size()).isEqualTo(3);
    }


    @Test
    @Transactional
    @Rollback(value = false)
    void testThatWeCanFetchAllCustomerAddress(){
        customer = customerRepository.findById(3).orElse(null);

        assert customer != null;
        for(Address address : customer.getAddresses()){
            log.info("All addresses --> {}", address);

            assertThat(customer.getAddresses().size()).isEqualTo(1);
        }
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void testThatWeCanRemoveAnAddressFromACustomerAddressesList(){
        customer = customerRepository.findById(3).orElse(null);

        assert customer != null;

        Address address = addressRepository.findById(5).orElse(null);

        if (customer.getAddresses().contains(address)){
            customer.getAddresses().remove(address);
        }

        assertThat(customer.getAddresses().size()).isEqualTo(1);
    }



}