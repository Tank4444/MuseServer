package ru.chuikov.MuseServer.controller.api;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.chuikov.MuseServer.entity.Music;
import ru.chuikov.MuseServer.service.impl.MusicServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/music")
public class apiMuseController {

    //services
    @Autowired
    private MusicServiceImpl musicService;


    //add
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addMusic(@RequestParam("music") MultipartFile file,
                                      @RequestParam("private") boolean privet )
    {
        try {
            musicService.addMusic(file,privet);
            return new ResponseEntity<Object>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get
    @RequestMapping(value = "/getAll",method = RequestMethod.GET )
    @ResponseBody
    public List<Music> getAllClient()
    {
        return musicService.getAll();
    }

    @RequestMapping(value="/get/stream/{musicId}", method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_OCTET_STREAM_VALUE })
    @ResponseBody
    public void getMusicStream(@PathVariable("musicId") Long id, HttpServletResponse response) {
        Music music = musicService.getMusic(id);
        if (music != null) {
            InputStream inputStream = null;
            try {

                inputStream = musicService.getMusicStream(music);
                response.setContentType(music.getMimeType());
                byte[] buff=IOUtils.toByteArray(inputStream);
                response.getOutputStream().write(buff);

                /*
                HttpHeaders header = new HttpHeaders();
                header.setContentType(new MediaType("audio", "mp3"));
                header.setContentLength(music.getContentLength());
                response.getOutputStream().write(inputStream.read());
                return new HttpEntity<byte[]>(IOUtils.toByteArray(inputStream), header);

                return ResponseEntity.ok()
                        .contentLength(music.getContentLength())
                        .contentType(new MediaType("audio","*"))
                        .body(new InputStreamResource(inputStream));
                */

            } catch (IOException e) {
            }
        }
    }

    @RequestMapping(value="/get/{musicId}", method = RequestMethod.GET)
    public @ResponseBody Music getMusic(@PathVariable("musicId") Long id) {
        Music music = musicService.getMusic(id);
        if (music != null) { return music;}
        else return null;
    }
    @RequestMapping(value="/getRandomPublicOne", method = RequestMethod.GET)
    public @ResponseBody Music getRandomPublicOne() {
        return musicService.getRandomPublicOne();
    }

    //delete
    @RequestMapping(value = "/delete/{musicId}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMusic(@PathVariable("musicId") Long id)
    {
        musicService.removeMusic(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
