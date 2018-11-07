package com.groupe6.beans;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.jboss.jandex.TypeTarget.Usage;

import com.mysql.jdbc.Util;

import java.util.List;

public class UtilisateurManager{
    protected SessionFactory sessionFactory;
    
    protected void setup() {
    	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
    	        .configure() // configures settings from hibernate.cfg.xml
    	        .build();
    	try {
    	    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    	} catch (HibernateException hex) {
			System.out.println("Problem creating session factory");
			hex.printStackTrace();
    	}
    }
    protected void exit() {
    	sessionFactory.close();
    }
 
    protected void createUser(Utilisateur utilisateur) {
        /*Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("monemail@gmail.com");
        utilisateur.setMotDePasse("monmdp");
        utilisateur.setNom("Jack");
        utilisateur.setPrenom("Jean-Jacques");
        */
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    
	    session.save(utilisateur);
	    
    	session.getTransaction().commit();
    	session.close();
    } 
 
    protected Utilisateur readUser(Long id) {
        Session session = sessionFactory.openSession();
        Utilisateur utilisateur = new Utilisateur();
        try {
        	utilisateur = session.get(Utilisateur.class, id);
	        /*System.out.println("ID : " + utilisateur.getId());
	        System.out.println("Nom : " + utilisateur.getNom());
	        System.out.println("Prénom : " + utilisateur.getPrenom());
	        System.out.println("Email : " + utilisateur.getEmail());
	        System.out.println("Mot de passe : " + utilisateur.getMotDePasse());
	        */
        	System.out.println(utilisateur.toString());
        } catch (NullPointerException npe) {
        	npe.printStackTrace();
        }
        session.close();
        return utilisateur;
    }
    
	protected List<Utilisateur> readAllUsers() {
    	Session session = sessionFactory.openSession();
    	CriteriaBuilder cBuilder = session.getCriteriaBuilder();
    	CriteriaQuery<Utilisateur> criteriaQuery = cBuilder.createQuery(Utilisateur.class);
    	Root<Utilisateur> root = criteriaQuery.from(Utilisateur.class);
    	criteriaQuery.select(root);
    	Query query = session.createQuery(criteriaQuery);
    	List<Utilisateur> results = query.getResultList();
    	System.out.println("Nombre d'utilisateurs : " + results.size());
    	for (Utilisateur utilisateur : results) {
    		readUser(utilisateur.getId());
		}
    	session.close();
    	return results;
	}
 
    protected void updateUser(Utilisateur updateUser) {
        /*Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(20);
        utilisateur.setNom("MyName");
        utilisateur.setPrenom("Jean-Jacques");
        utilisateur.setEmail("myemailaddress@gmail.com");
        utilisateur.setMotDePasse("mypassword");
        */
    	
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        session.update(updateUser);
        
        session.getTransaction().commit();
        session.close();
    }
 
    protected void deleteUser(Utilisateur utilisateur) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        session.delete(utilisateur);
        
        session.getTransaction().commit();
        session.close();		
	}
    
    protected void deleteAll() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId((long) 20);
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        session.delete(utilisateur);
        
        session.getTransaction().commit();
        session.close();
    }
    
    public static void main(String[] args) {
    	Utilisateur utilisateur = new Utilisateur("myemail@gmail.com","mypassword", "myName", "myFirstName");
    	UtilisateurManager manager = new UtilisateurManager();
        manager.setup();
        //manager.create(utilisateur);
        //Utilisateur getUser = manager.read(id)
        manager.readAllUsers();
        //manager.delete(utilisateur)
        manager.exit();
    }
}