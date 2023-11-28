package main.backend.Auction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AuctionRepository extends JpaRepository<Auction, Integer>{
	
	List<Auction> findById(int id);
	
	List<Auction> findAll();
	
	
}
