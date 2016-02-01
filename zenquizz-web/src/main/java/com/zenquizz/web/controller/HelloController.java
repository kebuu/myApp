package com.zenquizz.web.controller;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.google.api.Google;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class HelloController extends SecuredController{

    private Google google;
    private ConnectionRepository connectionRepository;

    @Inject
    public HelloController(Google google, ConnectionRepository connectionRepository) {
        this.google = google;
        this.connectionRepository = connectionRepository;
    }

    @RequestMapping(method= RequestMethod.GET)
    public String hello(Principal principal, Model model) {
        model.addAttribute("profile", principal);
        return "hello";
    }

    @RequestMapping(path = "/securedHello", method= RequestMethod.GET)
    public String securedHello(Model model) {
        model.addAttribute("user", getAuthenticatedUser());
        return "secured/securedHello";
    }

    @RequestMapping(path = "/angular", method= RequestMethod.GET)
    public String angular() {
        return "secured/withAngular2";
    }

    @RequestMapping(path = "/react", method= RequestMethod.GET)
    public String react() {
        return "secured/withReact";
    }
}