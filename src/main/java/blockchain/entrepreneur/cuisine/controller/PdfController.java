package blockchain.entrepreneur.cuisine.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import blockchain.entrepreneur.cuisine.model.Difficulty;
import blockchain.entrepreneur.cuisine.model.IngredientDTO;
import blockchain.entrepreneur.cuisine.model.NutriScore;
import blockchain.entrepreneur.cuisine.model.Recipe;
import blockchain.entrepreneur.cuisine.model.TimeRecipe;
import blockchain.entrepreneur.cuisine.model.Unit;
import blockchain.entrepreneur.cuisine.pdf.config.PdfGenerationService;

@RestController
public class PdfController {
	
	
	private PdfGenerationService pdfGenerationService;

	public PdfController(PdfGenerationService pdfGenerationService) {
		this.pdfGenerationService = pdfGenerationService;
	}

	
	@GetMapping("/test")
	public void test() {
        Map<String, Object> data = new HashMap<>();
        Recipe recipe = new Recipe();
        recipe.setName("Cassoulet de merde");
        recipe.setDifficulty(Difficulty.EASY);
        List<String> instructions = new ArrayList<String>();
        
        instructions.add("Eclater le poulet");
        instructions.add("Le fourrer avec du tartare");
        instructions.add("faire cuire 30 min à Auswitch");
        
        recipe.setInstructions(instructions);

        recipe.setServes(4);
        recipe.setPrice(5);
        
        IngredientDTO ingr1 = new IngredientDTO("Cuisse de canard", 200, Unit.G);
        IngredientDTO ingr2 = new IngredientDTO("Oignons", 200, Unit.G);
        IngredientDTO ingr3 = new IngredientDTO("Sel", 0, Unit.NONE);
        IngredientDTO ingr4 = new IngredientDTO("Creme de cyprine", 200, Unit.CL);
        
        List<IngredientDTO> ingredients = new ArrayList<IngredientDTO>();
        ingredients.add(ingr1);
        ingredients.add(ingr2);
        ingredients.add(ingr3);
        ingredients.add(ingr4);
        
        recipe.setIngredients(ingredients);
        
        TimeRecipe time = new TimeRecipe(5,10,0);
        
        recipe.setTimeRecipe(time);
        
        
        data.put("recipe", recipe);
        pdfGenerationService.generatePdfFile("recipe", data, "recipe.pdf");
	}
}
