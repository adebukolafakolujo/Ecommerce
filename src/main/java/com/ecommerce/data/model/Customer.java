package com.ecommerce.data.model;

import lombok.Data;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String contactNo;

    private String address;

    private String password;

    @ManyToMany(cascade = CascadeType.DETACH )
    private Set<Address> addresses;

    public void setAddresses(Address address){
        if(addresses == null){
            addresses = new HashSet<>();
        }

        if (checkIfAddressDoesNotExist(address)) {
            addresses.add(address);
        }

    }

    private boolean checkIfAddressDoesNotExist(Address address){

        if (!getAddresses().contains(address)){
            return true;
        }

        return false;
    }


}
