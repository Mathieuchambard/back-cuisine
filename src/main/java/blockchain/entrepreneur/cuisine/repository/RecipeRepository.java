package blockchain.entrepreneur.cuisine.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import blockchain.entrepreneur.cuisine.model.Recipe;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {

	Optional<Recipe> findByName(String name);
	
    @DeleteQuery
	void deleteByName(String name);
}