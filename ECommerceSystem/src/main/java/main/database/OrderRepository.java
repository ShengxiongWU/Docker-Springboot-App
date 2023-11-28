package main.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<order_info, Integer>{
	

	List<order_info> findById(int id);
	

	List<order_info> findAll();
	
	
}
