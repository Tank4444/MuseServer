package ru.chuikov.MuseServer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/load")
public class loadController {

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model)
    {
        return "musicUpload";
    }
}
