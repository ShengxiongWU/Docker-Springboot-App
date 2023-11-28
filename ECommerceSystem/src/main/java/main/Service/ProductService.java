package main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.database.ProductRepository;
import main.database.product;

@Service
public class ProductService {
	@Autowired
	private ProductRepository mainRepository;
	
	public product saveProduct(product p){
		return mainRepository.save(p);
	}
	
	public List<product> findAll(){
		return mainRepository.findAll();
	}
	
	public List<product> findById(int id){
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
	
    public List<product> searchProducts(Integer id, String name, String category, Integer seller) {
        if (id != null && name != null && category != null && seller != null) {
            return mainRepository.findByIdAndNameContainingAndCategoryContainingAndSeller(id, name, category, seller);
        } else if (id != null && name != null && category != null) {
            return mainRepository.findByNameContainingAndCategoryContainingAndId(name, category,id);
        } else if (id != null && name != null && seller != null) {
            return mainRepository.findByNameContainingAndSellerAndId(name, seller, id);
        } else if (id != null && category != null && seller != null) {
            return mainRepository.findByCategoryContainingAndSellerAndId(category, seller, id);
        } else if (name != null && category != null && seller != null) {
            return mainRepository.findByNameContainingAndCategoryContainingAndSeller(name, category, seller);
        } else if (id != null && name != null) {
            return mainRepository.findByNameContainingAndId(name, id);
        } else if (id != null && category != null) {
            return mainRepository.findByCategoryContainingAndId(category, id);
        } else if (id != null && seller != null) {
            return mainRepository.findByIdAndSeller(id, seller);
        } else if (name != null && category != null) {
            return mainRepository.findByNameContainingAndCategoryContaining(name, category);
        } else if (name != null && seller != null) {
            return mainRepository.findByNameContainingAndSeller(name, seller);
        } else if (category != null && seller != null) {
            return mainRepository.findByCategoryContainingAndSeller(category, seller);
        } else if (id != null) {
            return findById(id);
        } else if (name != null) {
            return mainRepository.findByNameContaining(name);
        } else if (category != null) {
            return mainRepository.findByCategoryContaining(category);
        } else if (seller != null) {
            return mainRepository.findBySeller(seller);
        } else {
            return mainRepository.findAll();
        }
    }
    

}
