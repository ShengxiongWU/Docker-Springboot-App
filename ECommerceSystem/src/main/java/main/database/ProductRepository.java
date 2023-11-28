package main.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends JpaRepository<product, Integer>{
	
	
	
	
	List<product> findAll();
	

    List<product> findByIdAndSeller(Integer id, Integer seller);

    List<product> findById(int id);

    void deleteById(Integer id);

    List<product> findBySeller(Integer seller);
    
    @Query("SELECT p FROM product p WHERE p.id = :id AND p.name LIKE %:name% AND p.category LIKE %:category% AND p.seller = :seller")
    List<product> findByIdAndNameContainingAndCategoryContainingAndSeller(@Param("id") Integer id,@Param("name") String name, @Param("category") String category, @Param("seller") Integer seller);
    
    @Query("SELECT p FROM product p WHERE p.name LIKE %:name% AND p.category LIKE %:category% AND p.seller = :seller")
    List<product> findByNameContainingAndCategoryContainingAndSeller(@Param("name") String name, @Param("category") String category, @Param("seller") Integer seller);

    @Query("SELECT p FROM product p WHERE p.name LIKE %:name% AND p.category LIKE %:category% AND p.id = :id")
    List<product> findByNameContainingAndCategoryContainingAndId(@Param("name") String name, @Param("category") String category, @Param("id") Integer id);

    @Query("SELECT p FROM product p WHERE p.name LIKE %:name% AND p.seller = :seller AND p.id = :id")
    List<product> findByNameContainingAndSellerAndId(@Param("name") String name, @Param("seller") Integer seller, @Param("id") Integer id);

    @Query("SELECT p FROM product p WHERE p.category LIKE %:category% AND p.seller = :seller AND p.id = :id")
    List<product> findByCategoryContainingAndSellerAndId(@Param("category") String category, @Param("seller") Integer seller, @Param("id") Integer id);

    @Query("SELECT p FROM product p WHERE p.name LIKE %:name% AND p.category LIKE %:category%")
    List<product> findByNameContainingAndCategoryContaining(@Param("name") String name, @Param("category") String category);

    @Query("SELECT p FROM product p WHERE p.name LIKE %:name% AND p.seller = :seller")
    List<product> findByNameContainingAndSeller(@Param("name") String name, @Param("seller") Integer seller);

    @Query("SELECT p FROM product p WHERE p.category LIKE %:category% AND p.seller = :seller")
    List<product> findByCategoryContainingAndSeller(@Param("category") String category, @Param("seller") Integer seller);

    @Query("SELECT p FROM product p WHERE p.name LIKE %:name% AND p.id = :id")
    List<product> findByNameContainingAndId(@Param("name") String name, @Param("id") Integer id);

    @Query("SELECT p FROM product p WHERE p.category LIKE %:category% AND p.id = :id")
    List<product> findByCategoryContainingAndId(@Param("category") String category, @Param("id") Integer id);

    @Query("SELECT p FROM product p WHERE p.name LIKE %:name%")
    List<product> findByNameContaining(@Param("name") String name);

    @Query("SELECT p FROM product p WHERE p.category LIKE %:category%")
    List<product> findByCategoryContaining(@Param("category") String category);


}
