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
import java.util.Collection;
import java.util.List;


//@Repository
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
    }


    
    protected void setup() {
    	System.out.println("Setup Manager");
    
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
    	System.out.println("Close Manager");
    	sessionFactory.close();
    }

    /*
     * Général <-- NE PAS UTILISER POUR LE MOMENT...
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
    @SuppressWarnings("unchecked")
	public List<Utilisateur> selectAllUsers() {

    	Session session = sessionFactory.openSession();
    	CriteriaBuilder cBuilder = session.getCriteriaBuilder();
    	CriteriaQuery<Utilisateur> criteriaQuery = cBuilder.createQuery(Utilisateur.class);
    	Root<Utilisateur> root = criteriaQuery.from(Utilisateur.class);
    	criteriaQuery.select(root);
    	Query query = session.createQuery(criteriaQuery);
    	List<Utilisateur> results = query.getResultList();

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
    @SuppressWarnings("unchecked")
	public List<Categorie> selectAllCategories() {
    	Session session = sessionFactory.openSession();
    	CriteriaBuilder cBuilder = session.getCriteriaBuilder();
    	CriteriaQuery<Categorie> criteriaQuery = cBuilder.createQuery(Categorie.class);
    	Root<Categorie> root = criteriaQuery.from(Categorie.class);
    	criteriaQuery.select(root);
    	Query query = session.createQuery(criteriaQuery);
    	List<Categorie> results = query.getResultList();

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
		    List<Billet> listB = selectAllBillets();
		    idB = listB.get(listB.size()-1).getIdB() + 1;
	    	session.getTransaction().commit();
	    	idB = selectAllBillets().get(selectAllBillets().size()-1).getIdB();
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
    @SuppressWarnings("unchecked")
	public List<Billet> selectAllBillets() {
    	Session session = sessionFactory.openSession();
    	CriteriaBuilder cBuilder = session.getCriteriaBuilder();
    	CriteriaQuery<Billet> criteriaQuery = cBuilder.createQuery(Billet.class);
    	Root<Billet> root = criteriaQuery.from(Billet.class);
    	criteriaQuery.select(root);
    	Query query = session.createQuery(criteriaQuery);
    	List<Billet> results = query.getResultList();

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
	    	session.getTransaction().commit();
	    	idT = selectAllTags().get(selectAllTags().size()-1).getIdT();
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
    	
    	List<Tag> listTags = selectAllTags();
    	for (Tag tag : listTags) {
			if(tag.getIdC() == idC && tag.getNomTag().equals(nomTag))
				return tag;
		}
    	return null;
    }
    

    
    @SuppressWarnings("unchecked")
	public List<Tag> selectAllTags() {
    	Session session = sessionFactory.openSession();
    	CriteriaBuilder cBuilder = session.getCriteriaBuilder();
    	CriteriaQuery<Tag> criteriaQuery = cBuilder.createQuery(Tag.class);
    	Root<Tag> root = criteriaQuery.from(Tag.class);
    	criteriaQuery.select(root);
    	Query query = session.createQuery(criteriaQuery);
    	List<Tag> results = query.getResultList();

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
		System.out.println("Close Manager");
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
        
    /*
     * Billet-Tag
     * Rajouter une clé primaire idBT représentant l'association du billet et du tag
     * passer idB en clé étrangère, comme idT?
     */
    public Long createBilletTag(Billet billet, Tag tag) {
    	Session session = sessionFactory.openSession();
    	Long idBT;
    	try {
    		session.beginTransaction();
    		session.save(new Billet_Tag(billet.getIdB(), tag.getIdT()));
    		session.getTransaction().commit();
    		idBT = selectAllBilletsTags().get(selectAllBilletsTags().size()-1).getIdBT();
    	} catch(Exception e) {
    		return Long.valueOf(0);
    	}
    	session.close();
    	return idBT;
    }
    
    public Billet_Tag selectBilletTag(Billet billet, Tag tag) {
    	List<Billet_Tag> listBT = selectAllBilletsTags();
    	for(Billet_Tag bTag : listBT) {
    		if(bTag.getIdB()==billet.getIdB() && bTag.getIdT() == tag.getIdT())
    			return bTag;
    	}
    	return null;
    }
    
    public List<Tag> selectTagsByBillet(Billet billet){
    	List<Billet_Tag> listBT = selectAllBilletsTags();
    	List<Tag> listTags = new ArrayList<Tag>();
    	for(Billet_Tag billet_Tag : listBT) {
    		if(billet_Tag.getIdB() == billet.getIdB())
    			listTags.add(selectTagByID(billet_Tag.getIdT()));
    	}
    	return listTags;
    }
    
    public List<Tag> selectTagsByBilletId(Long id){
    	List<Billet_Tag> listBT = selectAllBilletsTags();
    	List<Tag> listTags = new ArrayList<Tag>();
    	for(Billet_Tag billet_Tag : listBT) {
    		if(billet_Tag.getIdB() == id)
    			listTags.add(selectTagByID(billet_Tag.getIdT()));
    	}
    	return listTags;
    }
    
    
    public List<Billet> selectBilletsByTag(Tag tag){
    	List<Billet_Tag> listBT = selectAllBilletsTags();
    	List<Billet> listBillets = new ArrayList<Billet>();
    	for(Billet_Tag billet_Tag : listBT) {
    		if(billet_Tag.getIdT() == tag.getIdT()) {
    			listBillets.add(selectBilletByID(                     billet_Tag.getIdB()    ));
    			System.out.println("Match"+ selectBilletByID(billet_Tag.getIdT()) );
    		}

    	}

    	return listBillets;
    }
    
    @SuppressWarnings("unchecked")
    public List<Billet_Tag> selectAllBilletsTags(){
    	Session session = sessionFactory.openSession();
    	CriteriaBuilder cBuilder = session.getCriteriaBuilder();
    	CriteriaQuery<Billet_Tag> criteriaQuery = cBuilder.createQuery(Billet_Tag.class);
    	Root<Billet_Tag> root = criteriaQuery.from(Billet_Tag.class);
    	criteriaQuery.select(root);
    	Query query = session.createQuery(criteriaQuery);
    	List<Billet_Tag> results = query.getResultList();
    	session.close();
    	return results;
    }
    
    public boolean deleteBilletTag(Billet_Tag billet_Tag) {
    	Session session = sessionFactory.openSession();
    	try {
    		session.beginTransaction();
    		session.delete(billet_Tag);
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
    
    public boolean deleteAllBilletsTags(Billet billet) {
    	List<Billet_Tag> listBT = selectAllBilletsTags();
    	for(Billet_Tag bTag : listBT)
    		if(bTag.getIdB() == billet.getIdB())
    			if(!deleteBilletTag(bTag))
    				return false;
    	return true;
    }
    
    public boolean deleteAllBilletsTags(Tag tag) {
    	List<Billet_Tag> listBT = selectAllBilletsTags();
    	for(Billet_Tag bTag : listBT) 
    		if(bTag.getIdT() == tag.getIdT()) 
    			if(!deleteBilletTag(bTag))
    				return false;
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
    
        public void printListBilletsTags() {
    	List<Billet_Tag> listBT = this.selectAllBilletsTags();
    	System.out.println("Liste des billet_tags : ");
    	for (Billet_Tag bTag : listBT) {
    		System.out.println(bTag.toString());
    	}
    }

    private void test(){

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
        //manager.deleteBillet(manager.selectBillet(newBillet.getText()));
        //System.out.println("Après suppression : \n");
        //manager.printListUsers();
        //manager.printListTags();
        //manager.printListCategories();
        manager.printListBilletsTags();
        manager.exit();
    }

    
    public Collection<Utilisateur> findAll() {
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

}