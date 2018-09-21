package ru.chuikov.MuseServer.service.impl;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.chuikov.MuseServer.entity.Music;
import ru.chuikov.MuseServer.entity.Uaccount;
import ru.chuikov.MuseServer.repository.MusicRepository;
import ru.chuikov.MuseServer.service.MusicService;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicRepository musicRepository;
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Autowired
    private GridFSBucket gridFSBucket;


    //add
    @Override
    public void addMusic(MultipartFile file) throws IOException {
        Music music=new Music();
        music.setName(file.getOriginalFilename());
        music.setCreated(new Date());
        music.setContentLength(file.getSize());
        music.setContentId(gridFsTemplate.store(file.getInputStream(),
                file.getOriginalFilename(),music.getMimeType()).toString());
        music.setMimeType(file.getContentType());
        musicRepository.saveAndFlush(music);
        //add user account
    }

    //stream
    @Override
    public InputStream getMusicStream(Long id) throws IOException {
        Optional<Music> music = musicRepository.findById(id);
        if(music.isPresent())
        {
            GridFSFile gridFsdbFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").
                    is(music.get().getContentId())));

            GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFsdbFile.getObjectId());
            GridFsResource gridFsResource = new GridFsResource(gridFsdbFile,gridFSDownloadStream );
            return gridFsResource.getInputStream();
        }else
        {
            return null;
        }
    }
    @Override
    public InputStream getMusicStream(Music music) throws IOException {
        GridFSFile gridFsdbFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").
                is(music.getContentId())));

        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFsdbFile.getObjectId());
        GridFsResource gridFsResource = new GridFsResource(gridFsdbFile,gridFSDownloadStream );
        return gridFsResource.getInputStream();
    }

    //get
    @Override
    public Music getMusic(Long id) {
        Optional<Music> music = musicRepository.findById(id);
        if(music.isPresent())return music.get();
        else return null;
    }
    @Override
    public List<Music> getPrivateMusicList(Long from, Long to) {
        return null;
    }

    //remove
    @Override
    public void removeMusic(Music music) {
        if(music!=null)
        {
            gridFsTemplate.delete(new Query(Criteria.where("_id").
                    is(music.getContentId())));
            musicRepository.delete(music);
        }
    }

    @Override
    public void removeMusic(Long id) {
        Music music=getMusic(id);
        if(music!=null)
        {
            gridFsTemplate.delete(new Query(Criteria.where("_id").
                    is(music.getContentId())));
            musicRepository.delete(music);
        }
    }

    //all
    @Override
    public List<Music> getAll() {
        return musicRepository.findAll();
    }
}
