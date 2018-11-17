package com.groupe6.beans;

import javax.persistence.Convert;
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

import java.awt.Window.Type;
import java.util.List;

public class Manager {
    protected SessionFactory sessionFactory;
    
/*    public Manager() {
    	setup();
    }*/
    
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

    public void exit() {
    	sessionFactory.close();
    }

    /*
     * Général
     *
    public boolean create(BaseEntity tableBD) {
    	Session session = sessionFactory.openSession();
    	try {
    		session.beginTransaction();
    		session.save(tableBD);
    		session.getTransaction().commit();
    	} catch (Exception e) {
    		return false;
    	}
    	session.close();
    	return true;
    }

	public List<BaseEntity> selectAll() {
    	Session session = sessionFactory.openSession();
    	CriteriaBuilder cBuilder = session.getCriteriaBuilder();
    	CriteriaQuery<Utilisateur> criteriaQuery = cBuilder.createQuery(Utilisateur.class);
    	Root<Utilisateur> root = criteriaQuery.from(Utilisateur.class);
    	criteriaQuery.select(root);
    	Query query = session.createQuery(criteriaQuery);
    	List<BaseEntity> results = query.getResultList();
    	for (BaseEntity tableBD : results) {
    		selectUserByID(tableBD.getId());
		}
    	session.close();
    	return results;
	}

    public void delete(BaseEntity tableBD) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        session.delete(tableBD);
        
        session.getTransaction().commit();
        session.close();		
	}
    
    //Supprimer tous les tags?
    public void deleteAll() {
    	List<BaseEntity> listTableBD = selectAll();
    	for (BaseEntity tableBD : listTableBD) {
			delete(tableBD);
		}
    }
	
/*	public BaseEntity selectByID(Long id) {
        Session session = sessionFactory.openSession();
        BaseEntity tableBD = new BaseEntity();
        try {
        	tableBD = session.get(TableBD.class, id);
        } catch (NullPointerException npe) {
        	npe.printStackTrace();
        }
        session.close();
        return tableBD;    	
    }*/

    /*
     * Utilisateur
     */
    public boolean createUser(Utilisateur utilisateur) {
        Session session = sessionFactory.openSession();
        try {
	        session.beginTransaction();
		    session.save(utilisateur);
		    session.save(new Tag(Long.valueOf(2),"Utilisateur " + utilisateur.getNom() + " " + utilisateur.getPrenom()));
		    //OU createTag(new Tag(Long.valueOf(2),"Utilisateur " + utilisateur.getNom() + " " + utilisateur.getPrenom()));
	    	session.getTransaction().commit();
        } catch (Exception e) {
        	return false;
        }
    	session.close();
    	return true;
    } 
    
    public Utilisateur selectUserByID(Long id) {
        Session session = sessionFactory.openSession();
        Utilisateur utilisateur = new Utilisateur();
        try {
        	utilisateur = session.get(Utilisateur.class, id);
        } catch (NullPointerException npe) {
        	npe.printStackTrace();
        }
        session.close();
        return utilisateur;
    }
    
    public Utilisateur selectUser(String email, String password) {
    	List<Utilisateur> listUsers = selectAllUsers();
    	for (Utilisateur user : listUsers) {
			if(user.getEmail() == email && user.getMotDePasse() == password)
				return user;
		}
    	return null;
    }
    
	public List<Utilisateur> selectAllUsers() {
    	Session session = sessionFactory.openSession();
    	CriteriaBuilder cBuilder = session.getCriteriaBuilder();
    	CriteriaQuery<Utilisateur> criteriaQuery = cBuilder.createQuery(Utilisateur.class);
    	Root<Utilisateur> root = criteriaQuery.from(Utilisateur.class);
    	criteriaQuery.select(root);
    	Query query = session.createQuery(criteriaQuery);
    	List<Utilisateur> results = query.getResultList();
    	for (Utilisateur utilisateur : results) {
    		selectUserByID(utilisateur.getId());
		}
    	session.close();
    	return results;
	}
 
	//Rajouter update du tag
    public boolean updateUser(Utilisateur updateUser, Utilisateur newUser) {
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
    public void deleteUser(Utilisateur utilisateur) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        session.delete(utilisateur);
        
        session.getTransaction().commit();
        session.close();		
	}
    
    public void deleteAllUsers() {
    	List<Utilisateur> listUtilisateurs = selectAllUsers();
    	for (Utilisateur utilisateur : listUtilisateurs) {
			deleteUser(utilisateur);
		}
    }

    /*
     * Catégorie
     */
    public boolean createCategory(Categorie categorie) {
        Session session = sessionFactory.openSession();
        try {
	        session.beginTransaction();
		    session.save(categorie);
	    	session.getTransaction().commit();
        } catch (Exception e) {
        	return false;
        }
    	session.close();
    	return true;
    } 
    
    public Categorie selectCategoryByID(Long id) {
        Session session = sessionFactory.openSession();
        Categorie categorie = new Categorie();
        try {
        	categorie = session.get(Categorie.class, id);
        } catch (NullPointerException npe) {
        	npe.printStackTrace();
        }
        session.close();
        return categorie;
    }
    
    public Categorie selectCategory(String nomCategorie) {
    	List<Categorie> listCategories = selectAllCategories();
    	for (Categorie categorie : listCategories) {
			if(categorie.getNomCategorie() == nomCategorie)
				return categorie;
		}
    	return null;
    }
    
	public List<Categorie> selectAllCategories() {
    	Session session = sessionFactory.openSession();
    	CriteriaBuilder cBuilder = session.getCriteriaBuilder();
    	CriteriaQuery<Categorie> criteriaQuery = cBuilder.createQuery(Categorie.class);
    	Root<Categorie> root = criteriaQuery.from(Categorie.class);
    	criteriaQuery.select(root);
    	Query query = session.createQuery(criteriaQuery);
    	List<Categorie> results = query.getResultList();
    	for (Categorie categorie : results) {
    		selectUserByID(categorie.getIdC());
		}
    	session.close();
    	return results;
	}
 
	//Rajouter update du tag
    public boolean updateCategory(Categorie updateCategorie, Categorie newCategorie) {
    	Session session = sessionFactory.openSession();
        try{
        	session.beginTransaction();
        	Categorie categorie = (Categorie)session.get(Categorie.class, updateCategorie.getIdC()); 
            categorie.setNomCategorie(newCategorie.getNomCategorie());
            session.update(categorie); 
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
    public void deleteCategory(Categorie categorie) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        session.delete(categorie);
        
        session.getTransaction().commit();
        session.close();		
	}
    
    public void deleteAllCategories() {
    	List<Categorie> listCategories = selectAllCategories();
    	for (Categorie categorie : listCategories) {
			deleteCategory(categorie);
		}
    }
    
    
    /*
     * Billet
     */
    public boolean createBillet(Billet billet) {
        Session session = sessionFactory.openSession();
        try {
	        session.beginTransaction();
		    session.save(billet);
	    	session.getTransaction().commit();
        } catch (Exception e) {
        	return false;
        }
    	session.close();
    	return true;
    } 
    
    public Billet selectBilletByID(Long id) {
        Session session = sessionFactory.openSession();
        Billet billet = new Billet();
        try {
        	billet = session.get(Billet.class, id);
        } catch (NullPointerException npe) {
        	npe.printStackTrace();
        }
        session.close();
        return billet;
    }
    
	public List<Billet> selectAllBillets() {
    	Session session = sessionFactory.openSession();
    	CriteriaBuilder cBuilder = session.getCriteriaBuilder();
    	CriteriaQuery<Billet> criteriaQuery = cBuilder.createQuery(Billet.class);
    	Root<Billet> root = criteriaQuery.from(Billet.class);
    	criteriaQuery.select(root);
    	Query query = session.createQuery(criteriaQuery);
    	List<Billet> results = query.getResultList();
    	for (Billet billet : results) {
    		selectUserByID(billet.getIdB());
		}
    	session.close();
    	return results;
	}
 
	//Rajouter update du tag
    public boolean updateBillet(Billet updateBillet, Billet newBillet) {
    	Session session = sessionFactory.openSession();
        try{
        	session.beginTransaction();
        	Billet billet = (Billet)session.get(Billet.class, updateBillet.getIdB()); 
            billet.setText(newBillet.getText());
            billet.setCreation(newBillet.getCreation());
            billet.setModification(newBillet.getModification());
            session.update(billet); 
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
    public void deleteBillet(Billet billet) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        session.delete(billet);
        
        session.getTransaction().commit();
        session.close();		
	}
    
    public void deleteAllBillets() {
    	List<Billet> listBillets = selectAllBillets();
    	for (Billet billet : listBillets) {
			deleteBillet(billet);
		}
    }
    

    /*
     * Tag 
     */
    public boolean createTag(Tag tag) {
        Session session = sessionFactory.openSession();
        try {
	        session.beginTransaction();
		    session.save(tag);
	    	session.getTransaction().commit();
        } catch (Exception e) {
        	return false;
        }
    	session.close();
    	return true;
    } 
        
    public Tag selectTagByID(Long id) {
        Session session = sessionFactory.openSession();
        Tag tag = new Tag();
        try {
        	tag = session.get(Tag.class, id);
        } catch (NullPointerException npe) {
        	npe.printStackTrace();
        }
        session.close();
        return tag;
    }
    
	public List<Tag> selectAllTags() {
    	Session session = sessionFactory.openSession();
    	CriteriaBuilder cBuilder = session.getCriteriaBuilder();
    	CriteriaQuery<Tag> criteriaQuery = cBuilder.createQuery(Tag.class);
    	Root<Tag> root = criteriaQuery.from(Tag.class);
    	criteriaQuery.select(root);
    	Query query = session.createQuery(criteriaQuery);
    	List<Tag> results = query.getResultList();
    	for (Tag tag : results) {
    		selectUserByID(tag.getIdT());
		}
    	session.close();
    	return results;
	}
 
	//Rajouter update du tag
    public boolean updateTag(Tag updateTag, Tag newTag) {
    	Session session = sessionFactory.openSession();
        try{
        	session.beginTransaction();
        	Tag tag = (Tag)session.get(Tag.class, updateTag.getIdT()); 
        	tag.setNomTag(newTag.getNomTag());
            tag.setIdC(newTag.getIdC()); 	//A supprimer ou conserver??
            tag.setIdT(newTag.getIdT());	//A supprimer ou conserver??
        	session.update(tag); 
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
    public void deleteTag(Tag tag) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        session.delete(tag);
        
        session.getTransaction().commit();
        session.close();		
	}
    
    public void deleteAllTags() {
    	List<Tag> listTags = selectAllTags();
    	for (Tag tag : listTags) {
			deleteTag(tag);
		}
    }
        
    public void printListUsers() {
        List<Utilisateur> listUtilisateurs = this.selectAllUsers();
        System.out.println("Liste des utilisateurs : ");
        for (Utilisateur utilisateur2 : listUtilisateurs) {
			System.out.println(utilisateur2.toString());
		}    	
    }

    public void printListCategories() {
    	List<Categorie> listCategories = this.selectAllCategories();
        System.out.println("Liste des catégories : ");
        for (Categorie categorie : listCategories) {
			System.out.println(categorie.toString());
		}
    }
    
    public void printListBillets() {
    	List<Billet> listBillets = this.selectAllBillets();
        System.out.println("Liste des billets : ");
        for (Billet billet : listBillets) {
			System.out.println(billet.toString());
		}
    }
    
    public void printListTags() {
    	List<Tag> listTags = this.selectAllTags();
        System.out.println("Liste des tags : ");
        for (Tag tag : listTags) {
			System.out.println(tag.toString());
		}
    }
    
    public static void main(String[] args) {
    	Utilisateur utilisateur = new Utilisateur("myemail@gmail.com","mypassword", "myName", "myFirstName",1);
    	Utilisateur newUtilisateur = new Utilisateur("mynewemail@gmail.com","mynewpassword", "myNewName", "myNewFirstName",1);
    	Manager manager = new Manager();
        manager.setup();
        //manager.printListUsers();
//        System.out.println("Après création");
        //manager.createUser(utilisateur);
//        manager.updateUser(utilisateur, newUtilisateur);
        manager.printListUsers();
        manager.printListTags();
        //Utilisateur getUser = manager.read((long)20)
        //List<Utilisateur> listUsers = manager.selectAllUsers();
        //Utilisateur newUser = new Utilisateur("a@a.a","admin","nom","prenom",1);
        //System.out.println("\n ---------- APRES MODIFICATION : ------------- \n");
        //manager.updateUser(manager.selectUserByID((long)28), newUser);
        //manager.printListBillets();
    	//manager.printListCategories();
        //manager.printListTags();
        //manager.printListUsers();
        //manager.create(new Utilisateur("a@a.a","admin","nom","prenom",1));
        //manager.delete(utilisateur)
        manager.exit();
    }
}