package main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.database.OrderRepository;

import main.database.order_info;

@Service
public class OrderService {
	@Autowired
	private OrderRepository mainRepository;
	
	public order_info saveOrder(order_info p){
		return mainRepository.save(p);
	}
	
	public List<order_info> findAll(){
		return mainRepository.findAll();
	}
	
	public List<order_info> findById(int id){
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
