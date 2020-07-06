package com.adatb2.meet.repository;

import com.adatb2.meet.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@org.springframework.stereotype.Repository
public interface UserRepository extends Repository<User> {

    @Query("SELECT u FROM User u INNER JOIN u.authentication au WHERE au.authId = :auth_id")
    User checkLogin(@Param("auth_id") String auth_id);

    @Query("SELECT userId FROM User WHERE regDate = (SELECT MAX(regDate) FROM User )")
    String getMaxId();

    @Query("SELECT u FROM User u WHERE regDate = (SELECT MAX(regDate) FROM User )")
    User getLastEntry();

    @Modifying(clearAutomatically = true)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Query("UPDATE User SET firstName = :fn, lastName = :ln, birthday = :bd, " +
            "gender = :gender, email = :email WHERE userId = :userId")
    int updateUser(@Param("userId") String userId, @Param("fn") String firstName, @Param("ln") String lastName
            , @Param("bd") Date birthday, @Param("gender") String gender, @Param("email") String email);
}
