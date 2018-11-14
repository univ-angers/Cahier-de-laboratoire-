package com.groupe6.beans;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transaction;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.jboss.jandex.TypeTarget.Usage;

import com.mysql.jdbc.Util;

import antlr.Utils;

import java.util.List;

public class Manager {
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
 
    protected boolean createUtilisateur(Utilisateur utilisateur) {
        Session session = sessionFactory.openSession();
        try {
	        session.beginTransaction();
		    session.save(utilisateur);
	    	session.getTransaction().commit();
        } catch (Exception e) {
        	return false;
        }
    	session.close();
    	return true;
    } 
 
    protected Utilisateur selectUserByID(Long id) {
        Session session = sessionFactory.openSession();
        Utilisateur utilisateur = new Utilisateur();
        try {
        	utilisateur = session.get(Utilisateur.class, id);
        	System.out.println(utilisateur.toString());
        } catch (NullPointerException npe) {
        	npe.printStackTrace();
        }
        session.close();
        return utilisateur;
    }
    
    protected Utilisateur selectUser(String email, String password) {
    	List<Utilisateur> listUsers = selectAllUsers();
    	for (Utilisateur user : listUsers) {
			if(user.getEmail() == email && user.getMotDePasse() == password)
				return user;
		}
    	return null;
    }
    
	protected List<Utilisateur> selectAllUsers() {
    	Session session = sessionFactory.openSession();
    	CriteriaBuilder cBuilder = session.getCriteriaBuilder();
    	CriteriaQuery<Utilisateur> criteriaQuery = cBuilder.createQuery(Utilisateur.class);
    	Root<Utilisateur> root = criteriaQuery.from(Utilisateur.class);
    	criteriaQuery.select(root);
    	Query query = session.createQuery(criteriaQuery);
    	List<Utilisateur> results = query.getResultList();
    	System.out.println("Nombre d'utilisateurs : " + results.size());
    	for (Utilisateur utilisateur : results) {
    		selectUserByID(utilisateur.getId());
		}
    	session.close();
    	return results;
	}
 
	//Rajouter update du tag
    protected boolean updateUser(Utilisateur updateUser, Utilisateur newUser) {
    	Session session = sessionFactory.openSession();
        try{
        	session.beginTransaction();
            Utilisateur user = (Utilisateur)session.get(Utilisateur.class, updateUser.getId()); 
            user.setNom(newUser.getNom());
            user.setPrenom(newUser.getPrenom());
            user.setEmail(newUser.getEmail());
            user.setMotDePasse(newUser.getMotDePasse());
            user.setIsAdmin(newUser.getIsAdmin());            
            session.update(user); 
            session.getTransaction().commit();
            return true;
         } catch (HibernateException e) {
            if (session.getTransaction()!=null) 
            	session.getTransaction().rollback();
            e.printStackTrace(); 
            return false;
         }finally {
            session.close(); 
         }
    }
 
    //Supprimer aussi le tag?
    protected void deleteUser(Utilisateur utilisateur) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        session.delete(utilisateur);
        
        session.getTransaction().commit();
        session.close();		
	}
    
    //Supprimer tous les tags?
    protected void deleteAllUsers() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId((long) 20);
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        session.delete(utilisateur);
        
        session.getTransaction().commit();
        session.close();
    }
    
    public static void main(String[] args) {
    	Utilisateur utilisateur = new Utilisateur("myemail@gmail.com","mypassword", "myName", "myFirstName",1);
    	Manager manager = new Manager();
        manager.setup();
        //manager.create(utilisateur);
        //Utilisateur getUser = manager.read((long)20)
        List<Utilisateur> listUsers = manager.selectAllUsers();
        Utilisateur newUser = new Utilisateur("a@a.a","admin","nom","prenom",1);
        System.out.println("\n ---------- APRES MODIFICATION : ------------- \n");
        //manager.updateUser(manager.selectUserByID((long)28), newUser);
        manager.selectAllUsers();
        //manager.delete(utilisateur)
        manager.exit();
    }
}