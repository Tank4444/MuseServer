package ru.chuikov.MuseServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.chuikov.MuseServer.entity.Music;

public interface MusicRepository extends JpaRepository<Music,Long> {
    @Query("select b from Music b where b.id = :id")
    Music findOne(@Param("id") long id);
}
