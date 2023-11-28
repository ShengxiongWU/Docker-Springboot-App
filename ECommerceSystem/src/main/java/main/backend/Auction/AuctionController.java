package main.backend.Auction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/Auction")
public class AuctionController {
	@Autowired
	private AuctionService service;
	
	@PostMapping("/")
	public void saveAuction(@RequestBody Auction p){
		service.saveAuction(p);
	}
	
	@GetMapping("/")
	public List<Auction> getAllAuction(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public List<Auction> getById(@PathVariable("id") Integer id){
		return service.findById(id);
	}
	
	@DeleteMapping("/")
	public String deleteAll(){
		service.deleteAll();
		return "All Auction was deleted.";
	}
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable("id") Integer id){
		service.deleteById(id);
		return "Auction: " + id + " was deleted.";
	}
	
	@PutMapping("/")
	public void update(Auction p){
		service.saveAuction(p);
	}
	

	
}
