package pet.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.service.PetStoreService;

//created week 14. Requested by page 3.4

//default response code = 200. Deals with JSON
@RestController
//the additional ("/pet_store") is required for proper mapping
@RequestMapping("/pet_store") 
//Lombok gibberish = logger. instance variable named log
//use like this: log.info("This is a log line"):
@Slf4j
public class PetStoreController {
	
	//3.4.b autowire injects PetStoreService as instance variable
	@Autowired
	private PetStoreService petStoreService;
	
	
	//to complete post method http://localhost:8080/pet_store/petstore
	//then name Accept, Value application/json
	//then Name Content-Type Value application/json
	//3.4.c 
	@PostMapping("/petstore")
	//response status should be 201(Created)
	@ResponseStatus(code = HttpStatus.CREATED)
	//Create a public method that maps an HTTP POST request to "/pet_store".
	//Pass the contents of the request body as a parameter (type PetStoreData) to the method. 
	public PetStoreData insertPetStore(@RequestBody PetStoreData petStoreData) {
		//Log the request. before return, as return terminates method.
		log.info("Creating the pet store {}", petStoreData);
		//The method should return a PetStoreData object
		return petStoreService.savePetStore(petStoreData);
		//Call a method in the service class (savePetStore) that will insert or modify the pet store data.

}
	
	
	//to complete put method http://localhost:8080/pet_store/petstore/the id you wish to change
	//then name Accept, Value application/json
	//then Name Content-Type Value application/json
	@PutMapping("/petstore/{petStoreId}")
	public PetStoreData updatePetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
		petStoreData.setPet_store_id(petStoreId);
		log.info("Updating pet store {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}
}
