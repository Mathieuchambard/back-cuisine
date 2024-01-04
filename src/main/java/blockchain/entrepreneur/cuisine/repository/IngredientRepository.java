package blockchain.entrepreneur.cuisine.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import blockchain.entrepreneur.cuisine.model.Ingredient;

@Repository
public interface IngredientRepository extends MongoRepository<Ingredient, String> {

	Optional<Ingredient> findByName(String name);
	
    @DeleteQuery
	void deleteByName(String name);
}