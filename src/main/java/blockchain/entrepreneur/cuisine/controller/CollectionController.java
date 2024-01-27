package blockchain.entrepreneur.cuisine.controller;

import java.util.List;

import blockchain.entrepreneur.cuisine.model.CollectionRecipe;
import blockchain.entrepreneur.cuisine.service.CollectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class CollectionController {

    private CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }


    @GetMapping("/collections")
    public ResponseEntity<List<CollectionRecipe>> getAllCollections() {
        return new ResponseEntity<List<CollectionRecipe>>(collectionService.getAllCollections(), HttpStatus.OK);
    }

    @GetMapping("collection/{nameId}")
    public ResponseEntity<CollectionRecipe> getCollection(@PathVariable String nameId) {
        return new ResponseEntity<CollectionRecipe>(collectionService.getCollection(nameId), HttpStatus.OK);
    }


    @PostMapping("/post/collection")
    public ResponseEntity<CollectionRecipe> postCollection(@RequestBody CollectionRecipe collection) {
        return new ResponseEntity<CollectionRecipe>(collectionService.postCollection(collection), HttpStatus.OK);
    }

    @PutMapping("/modify/collection/{nameId}")
    public ResponseEntity<CollectionRecipe> modifyCollection(@RequestBody CollectionRecipe collection,@PathVariable String nameId) {
        return new ResponseEntity<CollectionRecipe>(collectionService.modifyCollection(nameId,collection), HttpStatus.OK);
    }

    @DeleteMapping("deleteCollection/{nameId}")
    public ResponseEntity<CollectionRecipe> deleteCollection(@PathVariable String nameId) {
        return new ResponseEntity<CollectionRecipe>(collectionService.deleteCollection(nameId), HttpStatus.OK);
    }


}