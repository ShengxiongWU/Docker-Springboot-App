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

import main.Service.ProductService;
import main.database.product;


@RestController
@RequestMapping("/product")
public class productController {
	@Autowired
	private ProductService service;
	
	@PostMapping("/")
	public void saveProduct(@RequestBody product p){
		service.saveProduct(p);
	}
	
	@GetMapping("/")
	public List<product> getAllProduct(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public List<product> getById(@PathVariable("id") Integer id){
		return service.findById(id);
	}
	
	@DeleteMapping("/")
	public String deleteAll(){
		service.deleteAll();
		return "All products was deleted.";
	}
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable("id") Integer id){
		service.deleteById(id);
		return "product: " + id + " was deleted.";
	}
	
}
