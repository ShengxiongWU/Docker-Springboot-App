package main.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<user, Integer>{
	

	List<user> findById(int id);
	

	List<user> findAll();

	@Query("SELECT u FROM user u WHERE u.username LIKE %:username%")
	List<user> findByUsernameLike(String username);
}
