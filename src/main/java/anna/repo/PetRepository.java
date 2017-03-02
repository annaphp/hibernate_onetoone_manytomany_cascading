package anna.repo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import anna.model.Pet;

public class PetRepository {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		return new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}
	
	public Long save (Pet pet){
	  Session session = sessionFactory.openSession();
	  session.beginTransaction();
	  Long id = (Long) session.save(pet);
	  session.getTransaction().commit();
	  session.close();
	  return id; 
	}
	
	public void update (Pet pet){
		Session session  = sessionFactory.openSession();
		session.beginTransaction();
		session.update(pet);
		session.beginTransaction().commit();
		session.close();
	}
	
	public Pet findPetById(Long id){
		Session session = sessionFactory.openSession();
		Pet pet = session.get(Pet.class, id);
		session.close();
		return pet;
	}
	
	public void delete(Pet pet){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(pet);
		session.getTransaction().commit();
		session.close();
	}

}
