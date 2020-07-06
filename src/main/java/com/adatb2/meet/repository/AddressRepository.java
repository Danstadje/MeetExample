package com.adatb2.meet.repository;

import com.adatb2.meet.model.Address;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface AddressRepository extends Repository<Address> {

    @Modifying(clearAutomatically = true)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Query("UPDATE Address SET country = :country, zip = :zip, town = :town WHERE addressId = :addressId")
    int updateAddress(@Param("addressId") String addressId, @Param("country") String country, @Param("town") String town
        , @Param("zip") String zip);
}
