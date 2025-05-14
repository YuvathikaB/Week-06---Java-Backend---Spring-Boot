package com.example.greetingapp;

import org.springframework.stereotype.Service;
import com.example.greetingapp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class GreetingService {
	
	@Autowired
    private GreetingRepository greetingRepository;
	
	public String getSimpleGreeting() { //UC 2
        return "Hello World";
    }
	
	public String getGreeting(User user) {  //UC 3
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        
        boolean hasFirstName = firstName != null && !firstName.trim().isEmpty();
        boolean hasLastName = lastName != null && !lastName.trim().isEmpty();
        
        if (hasFirstName && hasLastName) {
            return "Hello " + firstName + " " + lastName;
        } else if (hasFirstName) {
            return "Hello " + firstName;
        } else if (hasLastName) {
            return "Hello " + lastName;
        } else {
            return "Hello World"; 
        }
          
    }
	
	public Greeting addGreeting(User user) {  //UC 4
        String message = getGreeting(user); 
        Greeting greeting = new Greeting(message);
        return greetingRepository.save(greeting); 
    }
	
	public Greeting getGreetingById(long id) {  //UC 5
        return greetingRepository.findById(id)
                                 .orElseThrow(() -> new RuntimeException("Greeting Not Found with ID: " + id));
    }
	
	public List<Greeting> getAllGreetings() { //UC 6
        return greetingRepository.findAll(); // Provided by JpaRepository
    }
	
	public Greeting updateGreeting(long id, User user) { //UC 7
        Greeting existingGreeting = greetingRepository.findById(id)
                                                     .orElseThrow(() -> new RuntimeException("Greeting Not Found with ID: " + id));

        String updatedMessage = getGreeting(user); 
        existingGreeting.setMessage(updatedMessage); 

        return greetingRepository.save(existingGreeting); 
    }
	
	public void deleteGreeting(long id) { //UC 8
        if (!greetingRepository.existsById(id)) {
            throw new RuntimeException("Greeting Not Found with ID to Delete: " + id);
        }
        greetingRepository.deleteById(id); 
    }
}
