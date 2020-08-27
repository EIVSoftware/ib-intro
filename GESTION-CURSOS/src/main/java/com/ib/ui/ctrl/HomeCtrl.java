package com.ib.ui.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeCtrl {

    @GetMapping("/home")
    public String home(
            @RequestParam(name = "nombre", required = false) String nombre,
            Model model) {
        model.addAttribute("nombre", nombre);
        return "home";
    }
}
