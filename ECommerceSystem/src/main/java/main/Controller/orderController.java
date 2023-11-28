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

import main.Service.OrderService;
import main.database.order_info;


@RestController
@RequestMapping("/order")
public class orderController {
	@Autowired
	private OrderService service;
	
	@PostMapping("/")
	public void saveOrder(@RequestBody order_info p){
		service.saveOrder(p);
	}
	
	@GetMapping("/")
	public List<order_info> getAllOrder(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public List<order_info> getById(@PathVariable("id") Integer id){
		return service.findById(id);
	}
	
	@DeleteMapping("/")
	public String deleteAll(){
		service.deleteAll();
		return "All orders was deleted.";
	}
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable("id") Integer id){
		service.deleteById(id);
		return "order: " + id + " was deleted.";
	}
	
}
