package com.adatb2.meet.repository;

import com.adatb2.meet.model.Authentication;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Repository
public interface AuthenticationRepository extends Repository<Authentication> {

    @Query("SELECT authId FROM Authentication WHERE username = :username AND password = :pw")
    String checkLogin(@Param("username") String username, @Param("pw") String pw);

    @Query("SELECT a FROM Authentication a WHERE username = :username")
    Authentication findByUsername(@Param("username") String username);

    @Modifying(clearAutomatically = true)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Query("UPDATE Authentication SET username = :username, password = :pw WHERE authId = :authId")
    int updateAuth(@Param("authId") String authId, @Param("username") String username, @Param("pw") String pw);
}
