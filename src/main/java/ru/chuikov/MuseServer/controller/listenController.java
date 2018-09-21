package ru.chuikov.MuseServer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/listen")
public class listenController {

    @GetMapping(value = "/{id}")
    public String getListener(@PathVariable("musicId") Long id,ModelMap model)
    {
        model.addAttribute("id",id);
        return "listen";
    }
}
