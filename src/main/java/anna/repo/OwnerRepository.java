package anna.repo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import anna.model.Owner;

public class OwnerRepository {
	
	
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		return new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}
	
	public Long save(Owner owner){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Long id = (Long) session.save(owner);
		session.getTransaction().commit();
		session.close();
		return id;
	}
	
	public void update(Owner owner){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(owner);
		session.getTransaction().commit();
		session.close();
	}
	
	public Owner findOwnerById( Long id){
		Session session = sessionFactory.openSession();
		Owner owner = session.get(Owner.class, id);
		
		session.close();
		return owner;
	}
	
	public void delete(Owner owner){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(owner);
		session.getTransaction().commit();
		session.close();
	}

}
