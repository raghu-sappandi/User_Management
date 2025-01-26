package in.raghu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.raghu.entity.Search_Query;
import in.raghu.entity.User;
import in.raghu.repo.UserRepo;
import in.raghu.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class User_Controller {

	@Autowired
	private UserService service;
	
	

	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		if (service.unameExist(user.getUserName()) && service.emailExist(user.getEmail())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
		}
		else {
			Integer Id = service.saveUser(user);
			if (Id != null) {
				return ResponseEntity.ok("User Created Successfully [ User ID:  " + Id+" ]");
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create");
			}
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		if (service.login(user.getUserName(), user.getPassword())) {
			System.out.println("Sucess==============================");	
			return ResponseEntity.ok("Login Sucessfull");	
				
		}
		else {
			System.out.println("Not Sucess==============================");	
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect Credentials");
		}
	}

	@GetMapping("/")
	public ResponseEntity<?> getAllUsers() {
		return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
	}
	
	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestBody Search_Query search){
		
		return new ResponseEntity<>(service.getUserBySearch(search), HttpStatus.OK);
		
	}
	
	
}
