package com.fcv.expressCourier.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class GreetingController {

    @GetMapping("/greeting" )
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping(value = "/ex/foos", headers = "key=val", method = GET)
    @ResponseBody
    public String getFoosWithHeader() {
        return "Get some Foos with Header";
    }

}