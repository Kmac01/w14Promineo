package pet.store.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pet.store.controller.model.PetStoreData;
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;

//created week 14 requested by page 3.3
@Service
public class PetStoreService {

	// private instance variable object. annotation injects DAO into variable
	@Autowired
	private PetStoreDao petStoreDao;

	// page 3.5 service class savePetStore method as continuation of 3.4.c
	public PetStoreData savePetStore(PetStoreData petStoreData) {
		Long pet_Store_Id = petStoreData.getPet_store_id();
		PetStore petStore = findOrCreatePetStore(pet_Store_Id);
		
		// 4.5.b Do not copy the customers or employees fields.
		copyPetStoreFields(petStore, petStoreData);

		// 4.5.c Calling PetStoreDao save(petStore)
		PetStore dbPetStore = petStoreDao.save(petStore);
		// return new object created from return value of save() method
		return new PetStoreData(dbPetStore);
	}

	// called by 4.5.b
	private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
		petStore.setPet_store_id(petStoreData.getPet_store_id());
		petStore.setPet_store_name(petStoreData.getPet_store_name());
		petStore.setPet_store_address(petStoreData.getPet_store_address());
		petStore.setPet_store_city(petStoreData.getPet_store_city());
		petStore.setPet_store_state(petStoreData.getPet_store_state());
		petStore.setPet_store_zip(petStoreData.getPet_store_zip());
		petStore.setPet_store_phone(petStoreData.getPet_store_phone());

	}
	
	
	//4.5.a second reference: returns a new PetStore object if the pet store ID is null
	private PetStore findOrCreatePetStore(Long petStoreId) {
		PetStore petStore;

		if (Objects.isNull(petStoreId)) {
			petStore = new PetStore();
		} else {
			petStore = findPetStoreById(petStoreId);
		}

		return petStore;
	}

	//4.5.a third reference: returns a PetStore object if a pet store with matching ID exists in the database. 
	private PetStore findPetStoreById(Long petStoreId) {
		return petStoreDao.findById(petStoreId)
	//If no matching pet store is found, the method should throw a NoSuchElementException with an appropriate message.
				.orElseThrow(() -> new NoSuchElementException("Pet store with ID=" + petStoreId + " does not exist."));
	}
	
	


}
