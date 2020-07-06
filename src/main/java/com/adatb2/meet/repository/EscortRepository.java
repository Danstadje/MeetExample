package com.adatb2.meet.repository;

import com.adatb2.meet.model.Escort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EscortRepository extends Repository<Escort> {

    @Query("SELECT e.escortId FROM Escort e INNER JOIN e.user u WHERE u.regDate = " +
            "(SELECT MAX(u2.regDate) FROM Escort e2 INNER JOIN e2.user u2)")
    String getMaxId();

    @Query("SELECT COUNT(e) FROM Escort e INNER JOIN e.user u WHERE u.userId = :userId")
    int checkIfEscort(@Param("userId") String userId);

    @Query("SELECT e FROM Escort e INNER JOIN e.user u WHERE u.userId = :userId")
    Escort findByUserId(@Param("userId") String userId);
}
