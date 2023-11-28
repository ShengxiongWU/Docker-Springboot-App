package main.backend.Auction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuctionService {
	@Autowired
	private AuctionRepository mainRepository;
	
	public Auction saveAuction(Auction p){
		return mainRepository.save(p);
	}
	
	public List<Auction> findAll(){
		return mainRepository.findAll();
	}
	
	public List<Auction> findById(int id){
		return mainRepository.findById(id);
	}

	public void deleteAll() {
		// TODO Auto-generated method stub
		mainRepository.deleteAll();
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		mainRepository.deleteById(id);;
	}

}
