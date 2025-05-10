package com.example.HelloRestApi.controller;

import com.example.HelloRestApi.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/hello") 
public class HelloRestController {
	
	@RequestMapping(value = {"", "/", "/home"}) 
    public String sayHello() {
        return "Hello from BridgeLabz";
    }
	
	@GetMapping("/query")
    public String sayHello(@RequestParam(value = "name") String name) { // UC 2
        return "Hello " + name + " from BridgeLabz";
    }
    
    @GetMapping("/param/{name}") 
    public String sayHelloParam(@PathVariable String name) { // UC 3
        return "Hello " + name + " from BridgeLabz";
    }
    
    @PostMapping("/post") // UC 4
    public String sayHello(@RequestBody User user) { 
        return "Hello " + user.getFirstName() + " " + user.getLastName() + " from BridgeLabz";
    }
    
    @PutMapping("/put/{firstName}")  // UC 5
    public String sayHello(@PathVariable String firstName,
                            @RequestParam(value = "lastName") String lastName) {
        return "Hello " + firstName + " " + lastName + " from BridgeLabz";
    }

}
