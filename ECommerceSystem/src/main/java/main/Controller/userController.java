package main.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.Service.UserService;
import main.database.user;


@RestController
@RequestMapping("/user")
public class userController {
	@Autowired
	private UserService service;
	
	@PostMapping("/")
	public void saveUser(@RequestBody user p){
		service.saveUser(p);
	}
	
	@GetMapping("/")
	public List<user> getAllUser(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public List<user> getById(@PathVariable("id") Integer id){
		return service.findById(id);
	}
	
	@DeleteMapping("/")
	public String deleteAll(){
		service.deleteAll();
		return "All users was deleted.";
	}
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable("id") Integer id){
		service.deleteById(id);
		return "user: " + id + " was deleted.";
	}
	
}
