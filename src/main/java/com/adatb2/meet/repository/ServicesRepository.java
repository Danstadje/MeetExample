package com.adatb2.meet.repository;

import com.adatb2.meet.model.Services;
import org.springframework.data.jpa.repository.Query;

public interface ServicesRepository extends Repository<Services> {

    @Query("SELECT serviceName FROM Services")
    Iterable<String> getServices();
}
