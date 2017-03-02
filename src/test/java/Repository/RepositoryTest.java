package Repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import anna.model.Owner;
import anna.model.Pet;
import anna.repo.OwnerRepository;
import anna.repo.PetRepository;

public class RepositoryTest {
	
	OwnerRepository ownerRepo;
	PetRepository petRepo;
	Long ownerId;
	Long ownerId2;
	Long petId;
	Long petId2;
	
	@Before
	public void setup() throws Exception{
		ownerRepo = new OwnerRepository();
		petRepo = new PetRepository();
		
		ownerId = ownerRepo.save(new Owner("Johny", "24452345245"));
		ownerId2 = ownerRepo.save(new Owner("Donny", "345634563465"));

		petId = petRepo.save(new Pet("kitty", "cheshire", "Zina", "12/12/12"));
		petId2 = petRepo.save(new Pet("doggy", "lab", "Karjavka", "09/09/09"));
	}
	
	@Test
	public void shouldSavePet(){
		Owner owner = ownerRepo.findOwnerById(ownerId);
		Pet kitty = petRepo.findPetById(petId);
		Owner owner2 = ownerRepo.findOwnerById(ownerId2);
		Pet dog = petRepo.findPetById(petId2);
		
		// first owner has kitty and dog
		owner.getPets().add(kitty);     //   <-- bidirectional many to many linking
		kitty.getOwners().add(owner);   // 
		
		owner.getPets().add(dog);
		dog.getOwners().add(owner);
		
		// second owner had kitty
		owner2.getPets().add(kitty);
		kitty.getOwners().add(owner2);
		
		
		
		ownerRepo.update(owner); // save owner of relation
		
		assertFalse(ownerRepo.findOwnerById(ownerId2).getPets().contains(kitty));
		
		ownerRepo.update(owner2); // save owner of relation
		
		assertTrue(ownerRepo.findOwnerById(ownerId2).getPets().contains(kitty));
		
		
		System.out.println("\n kitty's owners: ");
		for(Owner o : petRepo.findPetById(petId).getOwners()){
			System.out.println(o);
		}
		
		System.out.println("\n dog's owners: ");
		for(Owner o : petRepo.findPetById(petId2).getOwners()){
			System.out.println(o);
		}
		
		System.out.println("\n owner1 pets: ");
		for(Pet p : ownerRepo.findOwnerById(ownerId).getPets()){
			System.out.println(p);
		}
		
		System.out.println("\n owner2 pets: ");
		for(Pet p : ownerRepo.findOwnerById(ownerId2).getPets()){
			System.out.println(p);
		}


		
	
		
		assertTrue(ownerRepo.findOwnerById(ownerId).getPets().contains(kitty));
		assertTrue(ownerRepo.findOwnerById(ownerId).getPets().contains(dog));
	}
}