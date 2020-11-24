package com.ecommerce.data.repository;

import lombok.extern.slf4j.Slf4j;
//import org.apache.tomcat.jni.Address;
import com.ecommerce.data.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class AddressRepositoryTest {

    @Autowired
    AddressRepository addressRepository;

    Address address;

    @BeforeEach
    void setUp() {

        address = new Address();
    }

    @Test

    void testThatWeCanSaveAddress(){
        address.setState("Lagos");
        address.setCity("Yaba");
        address.setStreet("Herbert_Macaulay");
        address.setCountry("Nigeria");
        address.setZipcode("234");

      //  log.info("Instance object before saving -->{}",address);

        addressRepository.save(address);

        address.setState("Lagos");
        address.setCity("Ikeja");
        address.setStreet("Isaac_John");
        address.setCountry("Nigeria");
        address.setZipcode("153");


        addressRepository.save(address);

       // log.info("Instance object before saving -->{}",address);

        assertThat(address.getId()).isNotNull();

        log.info("Instance object after saving -->{}",address);


    }

    @Test

    void testThatWeCanUpdateAddress(){

        address = addressRepository.findById(1).orElse(null);

        address.setZipcode("259");

        addressRepository.save(address);

        assertThat(address.getZipcode()).isEqualTo("259");

        log.info("Address Instance --> {}", address);


    }

    @Test
    void testThatWeCanDeleteAddress(){
        assertThat(addressRepository.existsById(1)).isTrue();

        addressRepository.deleteById(1);

        assertThat(addressRepository.existsById(1)).isFalse();
    }

    @Test

    void testThatWeCanGetAllTheAddress(){

        address.setState("Lagos");
        address.setCity("Oshodi");
        address.setStreet("Akindele");
        address.setCountry("Nigeria");
        address.setZipcode("313");

        addressRepository.save(address);

        List<Address> addresses = addressRepository.findAll();

        log.info("Address Instance --> {}", address);




    }



}