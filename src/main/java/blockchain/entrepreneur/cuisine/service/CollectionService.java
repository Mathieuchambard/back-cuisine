package blockchain.entrepreneur.cuisine.service;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


import blockchain.entrepreneur.cuisine.model.CollectionRecipe;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.text.Normalizer;
import java.util.Random;
import java.util.regex.Pattern;



@Service
public class CollectionService {
    @Value("${chemin.ressource}")
    private String cheminRessource ;

    public CollectionService() {
    }


    public CollectionRecipe getCollection(String nameId){
        CollectionRecipe coll = new CollectionRecipe();
        try (FileReader reader = new FileReader(cheminRessource  + "collection/" + nameId+".json")) {
            BufferedReader fileReader = new BufferedReader(reader);
            ObjectMapper mapper = new ObjectMapper();
            coll = mapper.readValue(fileReader, CollectionRecipe.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coll;
    }

    public CollectionRecipe deleteCollection(String nameId){
        String cheminFichier = cheminRessource  + "collection/"+ nameId+".json";
        File fichierASupprimer = new File(cheminFichier);
        fichierASupprimer.delete();
        return null;
    }
    public CollectionRecipe postCollection(CollectionRecipe collection) {
        ObjectMapper objectMapper = new ObjectMapper();


        File dossier = new File(cheminRessource  + "collection/");
        List<String> listNameId = new ArrayList<>();

        File[] files = dossier.listFiles();
        for (File file : files) {
            listNameId.add(file.getName());
        }



        String nameNormalise = Normalizer.normalize(collection.getName(), Normalizer.Form.NFD);
        nameNormalise = nameNormalise.replaceAll("[^\\p{ASCII}]", "");
        nameNormalise = nameNormalise.replaceAll("[^a-zA-Z0-9]", "");
        nameNormalise = nameNormalise.replaceAll("\\s+", "");

        Random random = new Random();

        String nameId = nameNormalise + random.nextInt(100000);

        while (listNameId.contains(nameId+".json")){
            nameId = nameNormalise + random.nextInt(100000);
        }

        collection.setNameId(nameId);


        try (FileWriter fileWriter = new FileWriter(cheminRessource  + "collection/" + nameId + ".json")) {
            objectMapper.writeValue(fileWriter, collection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return collection;
    }


    public CollectionRecipe modifyCollection(String nameId, CollectionRecipe collection) {
        ObjectMapper objectMapper = new ObjectMapper();
        collection.setNameId(nameId);


        try (FileWriter fileWriter = new FileWriter(cheminRessource  + "collection/" + nameId + ".json")) {
            objectMapper.writeValue(fileWriter, collection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return collection;
    }





    public List<CollectionRecipe> getAllCollections() {
        List<CollectionRecipe> collections = new ArrayList<>();
        File directory = new File(cheminRessource  + "collection/");
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {

                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".json")) {
                        try (FileReader reader = new FileReader(file)) {

                            BufferedReader fileReader = new BufferedReader(reader);
                            ObjectMapper mapper = new ObjectMapper();
                            CollectionRecipe coll = mapper.readValue(fileReader, CollectionRecipe.class);
                            collections.add(coll);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return collections;
    }

}