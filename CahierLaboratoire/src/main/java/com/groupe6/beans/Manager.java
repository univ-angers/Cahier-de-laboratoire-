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

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Manager {
    protected SessionFactory sessionFactory;
    
    /*
     * Toujours appeler le constructeur, puis la méthode à utiliser, puis exit()
     */
    public Manager() {
    	setup();
    	test();
    }
    
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
     * Utilisateur
     */
    //Renvoie l'id généré lors de la création de l'utilisateur
    public Long createUser(Utilisateur utilisateur) {
        Session session = sessionFactory.openSession();
        try {
	        session.beginTransaction();
		    session.save(utilisateur);
		    createTagForUser(Long.valueOf(2), utilisateur);
	    	session.getTransaction().commit();
        } catch (Exception e) {
        	return Long.valueOf(0);
        }
    	session.close();
    	return selectUser(utilisateur.getEmail(), utilisateur.getMotDePasse()).getId();
    } 
    
    private boolean createTagForUser(Long idC, Utilisateur utilisateur) {
		try{
			String categorie = selectCategoryByID(idC).getNomCategorie();
			Tag tag = new Tag(idC, categorie + " " + utilisateur.getNom() + " " + utilisateur.getPrenom());
			createTag(tag);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public Utilisateur selectUserByID(Long id) {
        Session session = sessionFactory.openSession();
        Utilisateur utilisateur;
        try {
        	utilisateur = session.get(Utilisateur.class, id);
        } catch (NullPointerException npe) {
        	npe.printStackTrace();
        	return null;
        }
        session.close();
        return utilisateur;
    }
    
    public Utilisateur selectUser(String email, String password) {
    	List<Utilisateur> listUsers = selectAllUsers();
    	for (Utilisateur user : listUsers) {
			if(user.getEmail().equals(email) && user.getMotDePasse().equals(password))
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
 
    public boolean updateUser(Utilisateur updateUser, Utilisateur newUser) {
    	Session session = sessionFactory.openSession();
        try{
        	session.beginTransaction();
            Utilisateur user = (Utilisateur)session.get(Utilisateur.class, updateUser.getId()); 
            Tag tag = selectTag(Long.valueOf(2), "Utilisateur " + user.getNom() + " " + user.getPrenom());
            user.setNom(newUser.getNom());
            user.setPrenom(newUser.getPrenom());
            user.setEmail(newUser.getEmail());
            user.setMotDePasse(newUser.getMotDePasse());
            user.setIsAdmin(newUser.getIsAdmin());            
            session.update(user); 
            //Modification du tag lié à l'utilisateur
            tag.setNomTag("Utilisateur " + user.getNom() + " " +  user.getPrenom());
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
 
    public boolean deleteUser(Utilisateur utilisateur) {
        Session session = sessionFactory.openSession();
        try{
        	session.beginTransaction();
	        session.delete(utilisateur);
	        session.delete(selectTag(Long.valueOf(2),"Utilisateur " + utilisateur.getNom() + " " + utilisateur.getPrenom()));
	        session.getTransaction().commit();
	        return true;
        } catch (HibernateException e) {
        	if(session.getTransaction()!=null)
        		session.getTransaction().rollback();
        	e.printStackTrace();
        	return false;
		} finally {
			session.close();
		}
	}
    
    public boolean deleteAllUsers() {
    	List<Utilisateur> listUtilisateurs = selectAllUsers();
    	for (Utilisateur utilisateur : listUtilisateurs) {
			if(!deleteUser(utilisateur))
				return false;
		}
    	return true;
    }

    
    /*
     * Catégorie
     */
    public Long createCategory(Categorie categorie) {
        Session session = sessionFactory.openSession();
        try {
	        session.beginTransaction();
		    session.save(categorie);
	    	session.getTransaction().commit();
        } catch (Exception e) {
        	return Long.valueOf(0);
        }
    	session.close();
    	return selectCategory(categorie.getNomCategorie()).getIdC();
    } 
    
    public Categorie selectCategoryByID(Long id) {
        Session session = sessionFactory.openSession();
        try {
        	return session.get(Categorie.class, id);
        } catch (NullPointerException npe) {
        	npe.printStackTrace();
        }
        session.close();
        return null;
    }
    
    public Categorie selectCategory(String nomCategorie) {
    	List<Categorie> listCategories = selectAllCategories();
    	for (Categorie categorie : listCategories) {
			if(categorie.getNomCategorie().equals(nomCategorie))
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
 
	public List<Categorie> selectAllCategoriesLike(String like){
		List<Categorie> listCat = selectAllCategories();
		List<Categorie> newListCat = new ArrayList<Categorie>();
		for (Categorie categorie : listCat) {
			if(categorie.getNomCategorie().startsWith(like))
				newListCat.add(categorie);
		}
		return newListCat;
	}
	
    public boolean updateCategory(Categorie updateCategorie, Categorie newCategorie) {
    	Session session = sessionFactory.openSession();
        try{
        	session.beginTransaction();
        	Categorie categorie = (Categorie)session.get(Categorie.class, updateCategorie.getIdC()); 
            categorie.setNomCategorie(newCategorie.getNomCategorie());
            session.update(categorie); 
            //Pour tous les tags concernés : mise à jour du nom de la catégorie dans le nom du tag
            for (Tag tag : selectAllTagsLike(updateCategorie.getNomCategorie())) {
            	Tag newTag = new Tag(tag.getIdC(), tag.getNomTag().replace(updateCategorie.getNomCategorie(), newCategorie.getNomCategorie()));
				updateTag(tag, newTag);
			}
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
 
    public boolean deleteCategory(Categorie categorie) {
        Session session = sessionFactory.openSession();
        try{
        	session.beginTransaction();
	        session.delete(categorie);
	        for (Tag tag : selectAllTagsLike(categorie.getNomCategorie())) {
				deleteTag(tag);
			}
	        session.getTransaction().commit();
	        return true;
        } catch (HibernateException e) {
        	if(session.getTransaction()!=null)
        		session.getTransaction().rollback();
        	e.printStackTrace();
        	return false;
		} finally {
			session.close();
		}
	}
    
    public boolean deleteAllCategories() {
    	List<Categorie> listCategories = selectAllCategories();
    	for (Categorie categorie : listCategories) {
			if(!deleteCategory(categorie))
				return false;
		}
    	return true;
    }    
    
    /*
     * Billet
     */
    public Long createBillet(Billet billet) {
        Session session = sessionFactory.openSession();
        Long idB;
        try {
	        session.beginTransaction();
		    session.save(billet);
		    idB = selectAllBillets().get(selectAllBillets().size()-1).getIdB();
	    	session.getTransaction().commit();
        } catch (Exception e) {
        	return Long.valueOf(0);
        }
    	session.close();
    	return idB;
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
    
    public Billet selectBillet(String text) {
    	Session session = sessionFactory.openSession();
    	List<Billet> listBillet = selectAllBillets();
    	for (Billet billet : listBillet) {
			if(billet.getText().equals(text))
				return billet;
		}
    	return null;    	
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
 
    public boolean updateBillet(Billet updateBillet, Billet newBillet) {
    	Session session = sessionFactory.openSession();
        try{
        	session.beginTransaction();
        	Billet billet = (Billet)session.get(Billet.class, updateBillet.getIdB()); 
            billet.setText(newBillet.getText());
            billet.setCreation(newBillet.getCreation());
            billet.setModification(new Date(new java.util.Date().getTime()));
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
 
    public boolean deleteBillet(Billet billet) {
        Session session = sessionFactory.openSession();
        try{
        	session.beginTransaction();
	        session.delete(billet);
	        session.getTransaction().commit();
	        return true;
        } catch (HibernateException e) {
        	if(session.getTransaction()!=null)
        		session.getTransaction().rollback();
        	e.printStackTrace();
        	return false;
		} finally {
			session.close();
		}		
	}
    
    public boolean deleteAllBillets() {
    	List<Billet> listBillets = selectAllBillets();
    	for (Billet billet : listBillets) {
			if(!deleteBillet(billet))
				return false;
		}
    	return true;
    }
    

    /*
     * Tag 
     */
    public Long createTag(Tag tag) {
        Session session = sessionFactory.openSession();
        Long idT;
        try {
	        session.beginTransaction();
		    session.save(tag);
		    idT = selectAllTags().get(selectAllTags().size()-1).getIdT();
	    	session.getTransaction().commit();
        } catch (Exception e) {
        	return Long.valueOf(0);
        }
    	session.close();
    	return idT;
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
    
    public Tag selectTag(Long idC, String nomTag) {
    	Session session = sessionFactory.openSession();
    	List<Tag> listTags = selectAllTags();
    	for (Tag tag : listTags) {
			if(tag.getIdC() == idC && tag.getNomTag().equals(nomTag))
				return tag;
		}
    	return null;
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

	public List<Tag> selectAllTagsLike(String like){
		List<Tag> listTag = selectAllTags();
		List<Tag> newListTag = new ArrayList<Tag>();
		for (Tag tag : listTag) {
			if(tag.getNomTag().startsWith(like))
				newListTag.add(tag);
		}
		return newListTag;
	}
	
    public boolean updateTag(Tag updateTag, Tag newTag) {
    	Session session = sessionFactory.openSession();
        try{
        	session.beginTransaction();
        	Tag tag = (Tag)session.get(Tag.class, updateTag.getIdT()); 
        	tag.setNomTag(newTag.getNomTag());
            tag.setIdC(newTag.getIdC()); 	//A supprimer ou conserver??
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
 
    public boolean deleteTag(Tag tag) {
        Session session = sessionFactory.openSession();
        try{
        	session.beginTransaction();
	        session.delete(tag);
	        session.getTransaction().commit();
	        return true;
        } catch (HibernateException e) {
        	if(session.getTransaction()!=null)
        		session.getTransaction().rollback();
        	e.printStackTrace();
        	return false;
		} finally {
			session.close();
		}
	}
    
    public boolean deleteAllTags() {
    	List<Tag> listTags = selectAllTags();
    	for (Tag tag : listTags) {
			if(!deleteTag(tag))
				return false;
		}
    	return true;
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
    
    private void test() {
    //public static void main(String args[]) {
    	Utilisateur utilisateur = new Utilisateur("myemail@gmail.com","mypassword", "myName", "myFirstName",1);
    	Utilisateur newUtilisateur = new Utilisateur("mynewemail@gmail.com","mynewpassword", "myNewName", "myNewFirstName",1);
    	Tag tag = new Tag(Long.valueOf(1), "Projet 3");
    	Tag newTag = new Tag(Long.valueOf(1), "Projet 3bis");
    	Categorie categorie = new Categorie("test");
    	Categorie categorie2 = new Categorie("newTest");
    	Billet billet = new Billet("Billet test");
    	Billet newBillet = new Billet("UPDATE Billet test");
    	Manager manager = new Manager();
        manager.setup();
        //Situation de départ
        System.out.println("Situation de départ : \n");
        //manager.printListUsers();
        //manager.printListTags();       
        //manager.printListCategories();
        manager.printListBillets();
        
        //Création
        //manager.createUser(utilisateur);
        //manager.createTag(tag);
        //manager.createCategory(categorie);
    	//Tag testTag = new Tag(manager.selectCategory("test").getIdC(),"test 3");
    	//manager.createTag(testTag);
        manager.createBillet(billet);
        System.out.println("Après création : \n");
        //manager.printListUsers();
        //manager.printListTags();
        //manager.printListCategories();
        manager.printListBillets();

        //Modification
        //manager.updateUser(utilisateur, newUtilisateur); 
        //manager.updateTag(tag, newTag);
        //manager.updateCategory(categorie, categorie2);
        //Categorie categorie3 = new Categorie("Utilisateur");
        //manager.updateCategory(manager.selectCategory("auteur"), categorie3);
        manager.updateBillet(billet, newBillet);
        System.out.println("Après modification : \n");
        //manager.printListUsers();
        //manager.printListTags();
        //manager.printListCategories();
        manager.printListBillets();
        
        //Suppression
        //Utilisateur delNewUser = manager.selectUser(newUtilisateur.getEmail(), newUtilisateur.getMotDePasse());
        //Utilisateur delUser = manager.selectUser(utilisateur.getEmail(), utilisateur.getMotDePasse());
        //manager.deleteUser(delUser);
        //manager.deleteUser(delNewUser);
        //Tag delTag = manager.selectTag(tag.getIdC(), tag.getNomTag());
        //Tag delNewTag = manager.selectTag(newTag.getIdC(), newTag.getNomTag());
        //manager.deleteTag(delTag);
        //manager.deleteTag(delNewTag);
        //Categorie delCat = manager.selectCategory(categorie2.getNomCategorie());
        //manager.deleteCategory(delCat);
        //manager.deleteCategory(categorie);
        manager.deleteBillet(manager.selectBillet(newBillet.getText()));
        System.out.println("Après suppression : \n");
        //manager.printListUsers();
        //manager.printListTags();
        //manager.printListCategories();
        manager.printListBillets();
        manager.exit();
    }
}