package com.example.greetingapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.greetingapp.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/greeting")
public class GreetingController {
	
	@Autowired 
    private GreetingService greetingService;
	
	@GetMapping("/simple") //UC 2
    public String getSimpleGreetingFromService() { 
        return greetingService.getSimpleGreeting();
    }
	
	 @GetMapping("/hello") 
	    public String sayHello() {
	        return "Hello World";
	 }
	 
	 @GetMapping("/name/{name}")
	    public ResponseEntity<String> sayHelloToName(@PathVariable String name) {
	        return new ResponseEntity<>("Hello " + name, HttpStatus.OK);
	 }
	 
	 @GetMapping("/query") //UC 3
	    public String getGreetingByQuery(
	            @RequestParam(value = "firstName", required = false) String firstName,
	            @RequestParam(value = "lastName", required = false) String lastName) {
	        User user = new User(firstName, lastName);
	        return greetingService.getGreeting(user);
	}
	 
	 @GetMapping("/id/{id}") //UC 5
	    public ResponseEntity<Greeting> getGreetingById(@PathVariable long id) {
	        Greeting greeting = greetingService.getGreetingById(id);
	        return new ResponseEntity<>(greeting, HttpStatus.OK);
	}
	 
	 @GetMapping("/all")  //UC 6
	    public ResponseEntity<List<Greeting>> getAllGreetings() {
	        List<Greeting> greetings = greetingService.getAllGreetings();
	        return new ResponseEntity<>(greetings, HttpStatus.OK);
	 }
	 
	 @PostMapping("/create")
	    public ResponseEntity<String> createGreeting() {
	        return new ResponseEntity<>("Greeting created successfully!", HttpStatus.CREATED);
	 }
	 
	 @PostMapping("/personalized")  //UC 3
	    public String getPersonalizedGreeting(@RequestBody User user) {
	        return greetingService.getGreeting(user);
	 }
	 
	 @PostMapping("/add")  //UC 4
	    public ResponseEntity<Greeting> addGreeting(@RequestBody User user) {
	        Greeting greeting = greetingService.addGreeting(user);
	        return new ResponseEntity<>(greeting, HttpStatus.CREATED);
	 }
	 
	 @PutMapping("/update")
	    public ResponseEntity<String> updateGreeting() {
	        return new ResponseEntity<>("Greeting updated successfully!", HttpStatus.OK);
	 }
	 
	 @PutMapping("/update/{id}") //UC 7
	    public ResponseEntity<Greeting> updateGreeting(@PathVariable long id, @RequestBody User user) {
	        Greeting updatedGreeting = greetingService.updateGreeting(id, user);
	        return new ResponseEntity<>(updatedGreeting, HttpStatus.OK);
	 }
	 
	 @DeleteMapping("/delete")
	    public ResponseEntity<String> deleteGreeting() {
	        return new ResponseEntity<>("Greeting deleted successfully!", HttpStatus.NO_CONTENT);
	 }
	 
	 @DeleteMapping("/delete/{id}") //UC 8
	    public ResponseEntity<String> deleteGreeting(@PathVariable long id) {
	        greetingService.deleteGreeting(id);
	        return new ResponseEntity<>("Greeting with ID " + id + " deleted successfully!", HttpStatus.NO_CONTENT);
	    }
	 
}
	 
	 


