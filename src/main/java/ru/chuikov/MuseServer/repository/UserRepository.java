package ru.chuikov.MuseServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.chuikov.MuseServer.entity.Uaccount;

public interface UserRepository extends JpaRepository<Uaccount,Long> {
    @Query("select b from Uaccount b where b.id = :id")
    Uaccount findOne(@Param("id") long id);
}
