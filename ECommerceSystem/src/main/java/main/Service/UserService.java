package main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.database.UserRepository;

import main.database.user;

@Service
public class UserService {
	@Autowired
	private UserRepository mainRepository;
	
	public user saveUser(user p){
		return mainRepository.save(p);
	}
	
	public List<user> findAll(){
		return mainRepository.findAll();
	}
	
	public List<user> findById(int id){
		return mainRepository.findById(id);
	}

	public List<user> findByUsernameLike(String username){
		return mainRepository.findByUsernameLike(username);
	}

	public void deleteAll() {
		// TODO Auto-generated method stub
		mainRepository.deleteAll();
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		mainRepository.deleteById(id);
	}

}
