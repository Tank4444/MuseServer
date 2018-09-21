package ru.chuikov.MuseServer.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;
import ru.chuikov.MuseServer.entity.Music;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface MusicService {

    //add
    void addMusic(MultipartFile file) throws IOException;

    //stream
    InputStream getMusicStream(Long id) throws IOException;
    InputStream getMusicStream(Music music) throws IOException;

    //get music by id
    Music getMusic(Long id);
    List<Music> getPrivateMusicList(Long from,Long to);

    //remove
    void removeMusic(Music music);
    void removeMusic(Long id);

    //all
    List<Music> getAll();


}
