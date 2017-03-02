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
	Long petId;
	
	@Before
	public void setup() throws Exception{
		ownerRepo = new OwnerRepository();
		petRepo = new PetRepository();
		
		ownerId = ownerRepo.save(new Owner("Johny", "24452345245"));
		petId = petRepo.save(new Pet("kitty", "cheshire", "Zina", "12/12/12"));
	}
	
	@Test
	public void shouldSavePet(){
		Owner owner = ownerRepo.findOwnerById(ownerId);
		Pet kitty = petRepo.findPetById(petId);
		
		owner.setPet(kitty); // create relation
		
		ownerRepo.update(owner); // save owner of relation
		
	
		
		assertEquals(ownerRepo.findOwnerById(ownerId).getPet(), kitty);
	}
	
	@Test
	public void shouldRemovePet(){
		Owner owner = ownerRepo.findOwnerById(ownerId);
		Pet kitty = petRepo.findPetById(petId);
		
		owner.setPet(kitty); // create relation
		
		ownerRepo.update(owner); // save owner of relation
		
	
		
		assertEquals(ownerRepo.findOwnerById(ownerId).getPet(), kitty);
		
		owner = ownerRepo.findOwnerById(ownerId);
		owner.setPet(null);
		ownerRepo.update(owner); // save owner of relation
		
		assertNull(ownerRepo.findOwnerById(ownerId).getPet());

		

		
	}
	
	

}
