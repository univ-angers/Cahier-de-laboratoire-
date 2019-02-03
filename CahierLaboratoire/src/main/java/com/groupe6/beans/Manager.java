package com.groupe6.beans;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
    }
    
    /*
     * Construit la sessionFactory à partir du fichier hibernate.cfg.xml
     */
    protected void setup() {   
    	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
    	        .configure()
    	        .build();
    	try {
    	    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    	} catch (HibernateException hex) {
			hex.printStackTrace();
    	}
    }

    /*
     * Ferme la sessionFactory
     */
    public void exit() {
    	sessionFactory.close();
    }


    /*
     * UTILISATEUR
     */

    /* 
     * Ajoute l'utilisateur pris en paramètre dans la table Utilisateur
     * Ajoute également un tag nommé "Utilisateur Nom Prénom" dans la table Tag
     * Renvoie l'id généré lors de la création de l'utilisateur
     */
    public Long createUser(Utilisateur utilisateur) {
        Session session = sessionFactory.openSession();
        try {

	        session.beginTransaction();
		    session.save(utilisateur);
		    createTagForUser(utilisateur);
	    	session.getTransaction().commit();
        } catch (Exception e) {
        	return Long.valueOf(0);
        }
    	session.close();
    	return selectUser(utilisateur.getEmail(), utilisateur.getMotDePasse()).getId();
    } 
    
    /*
     * Crée un tag relié à l'utilisateur en cours de création (appelé dans createUser)
     * On récupère la catégorie "Utilisateur" avant d'ajouter le nouveau tag à la table
     */
    private boolean createTagForUser(Utilisateur utilisateur) {
		try{
			Tag tag = new Tag(selectCategory("Utilisateur").getIdC(), "Utilisateur " + utilisateur.getNom() + " " + utilisateur.getPrenom());
			createTag(tag);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
    
    /*
     * Récupère l'utilisateur à l'aide de son ID puis le renvoie
     */
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
    
	/*
	 * Récupère l'utilisateur à l'aide du couple email/password qui est unique, puis le renvoie
	 */
    public Utilisateur selectUser(String email, String password) {
    	Session session = sessionFactory.openSession();
    	TypedQuery<Utilisateur> query = session.createQuery("SELECT u FROM Utilisateur u WHERE email = :ema AND motDePasse = :mdp",Utilisateur.class);
    	query.setParameter("ema", email);
    	query.setParameter("mdp", password);
    	try {
    		return query.getSingleResult();
    	} catch (NoResultException nre) {
    		return null;
    	}
	}
    
    /*
     * Renvoie la liste de tous les utilisateurs présents dans la table Utilisateur
     */
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
 
    /*
     * Modifie les valeurs des attributs de l'utilisateur pris en paramètre 1 par celles des attributs de l'utilisateur pris en second paramètre
     * Modifie le tag associé à l'utilisateur en conséquence
     */
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
 
    /*
     * Supprime l'utilisateur de la table Utilisateur
     * Supprime également son tag associé (catégorie Utilisateur = catégorie d'id 2)
     */
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
    
    /*
     * Supprime l'ensemble des utilisateurs de la table Utilisateur
     */
    public boolean deleteAllUsers() {
    	List<Utilisateur> listUtilisateurs = selectAllUsers();
    	for (Utilisateur utilisateur : listUtilisateurs) {
			if(!deleteUser(utilisateur))
				return false;
		}
    	return true;
    }
    

    /*
     * CATEGORIE
     */
    
    /* 
     * Ajoute la catégorie prise en paramètre dans la table Catégorie
     * Renvoie l'id généré lors de la création de la catégorie
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
    
    /*
     * Renvoie la catégorie à partir de son ID
     */
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
    
    /*
     * Renvoie la catégorie à partir de son nom
     */
    public Categorie selectCategory(String nomCategorie) {
    	Session session = sessionFactory.openSession();
    	TypedQuery<Categorie> query = session.createQuery("SELECT c FROM Categorie c WHERE nom_categorie = :nom",Categorie.class);
    	query.setParameter("nom", nomCategorie);
    	try {
    		return query.getSingleResult();
    	} catch (NoResultException nre) {
    		return null;
    	}
    }
    
    /*
     * Renvoie la liste de toutes les catégories présentes dans la table Categorie
     */
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
 
    /*
     * Renvoie la liste de toutes les catégories dont le nom commence par la chaîne de caractères prise en paramètre
     */
	public List<Categorie> selectAllCategoriesLike(String like){
    	Session session = sessionFactory.openSession();
    	TypedQuery<Categorie> query = session.createQuery("SELECT c FROM Categorie c WHERE lower(nom_categorie) LIKE lower(:nom)",Categorie.class);
    	query.setParameter("nom", like+"%");
    	try {
    		return query.getResultList();
    	} catch (NoResultException nre) {
    		return null;
    	}
	}
	
	/*
	 * Modifie le nom de la catégorie prise en premier paramètre par celui de la catégorie présente en second paramètre 
	 */
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
 
    /*
     * Supprime la catégorie prise en paramètre dans la table Catégorie
     * Supprime également l'ensemble des tags appartenant à cette catégorie
     */
    public boolean deleteCategory(Categorie categorie) {
        Session session = sessionFactory.openSession();
        try{
        	session.beginTransaction();
	        session.delete(categorie);
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
    
    /*
     * Supprime l'ensemble des catégories présentes dans la table Catégorie
     * Supprime par conséquent l'ensemble des tags (cascade, clé étrangère dans la table Tag)
     */
    public boolean deleteAllCategories() {
    	List<Categorie> listCategories = selectAllCategories();
    	for (Categorie categorie : listCategories) {
			if(!deleteCategory(categorie))
				return false;
		}
    	return true;
    }    

    
    /*
     * BILLET
     */
    
    /*
     * Ajoute un billet à la table Billet puis renvoie son id généré
     */
    public Long createBillet(Billet billet,Long utilisateur) {
        Session session = sessionFactory.openSession();
        Long idB = (long) 0;
        try {
        	// session.beginTransaction();
        	session.save(billet);
        	idB=billet.getIdB();
        	initPermission(utilisateur,billet.getIdB(),session);
        	//session.getTransaction().commit();   
        	session.close();
   
	    
        } catch (Exception e) {
        	
        	return idB;
        }
    	
    
    	return idB;
    } 
    
    /*
     * Renvoie un billet présent dans la table Billet à l'aide de son id
     */
    public Billet selectBilletByID(Long id) {
        Session session = sessionFactory.openSession();
        Billet billet = new Billet();
        try {
        	billet = session.get(Billet.class, id);
        } catch (NullPointerException npe) {
        	npe.printStackTrace();
        	return null;
        }
        session.close();
        return billet;
    }
    
    /*
     * Renvoie un billet à l'aide de son contenu (à priori, jamais utilisé)
     */
    public Billet selectBillet(String text) {
    	Session session = sessionFactory.openSession();
    	TypedQuery<Billet> query = session.createQuery("SELECT b FROM Billet b WHERE texte = :txt",Billet.class);
    	query.setParameter("txt", text);
    	try {
    		return query.getSingleResult();
    	} catch (NoResultException nre) {
    		return null;
    	}
    }
    
    /*
     * Renvoie la liste de tous les billets présents dans la table Billet
     */
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
    
    @SuppressWarnings("unchecked")
	public List<Permission> selectAllPermisions() {
    	Session session = sessionFactory.openSession();
    	CriteriaBuilder cBuilder = session.getCriteriaBuilder();
    	CriteriaQuery<Permission> criteriaQuery = cBuilder.createQuery(Permission.class);
    	Root<Permission> root = criteriaQuery.from(Permission.class);
    	criteriaQuery.select(root);
    	Query query = session.createQuery(criteriaQuery);
    	List<Permission> results = query.getResultList();
    	session.close();
    	return results;
	}
    
    
    /*
     * Modifie le premier billet pris en paramètre en lui passant les valeurs des attributs du deuxième billet pris en paramètre
     * Met également à jour la date de modification du billet
     */
    public boolean updateBillet(Billet updateBillet, Billet newBillet,Long idUser) {
    	Session session = sessionFactory.openSession();
        try{
        	
        	if (updateBillet==null)
        	{
        		updateBillet= selectAllBillets().get(selectAllBillets().size()-1);
        	}
       
        //	session.beginTransaction();
        	if (hasPermission(idUser,updateBillet.getIdB(),"update",session))
        	{
        		session.beginTransaction();
            	Billet billet = (Billet)session.get(Billet.class, updateBillet.getIdB()); 
                billet.setText(newBillet.getText());
                billet.setCreation(newBillet.getCreation());
                billet.setModification(new Date(new java.util.Date().getTime()));
                session.update(billet); 
                session.getTransaction().commit();
                return true;
        	}
        	//session.getTransaction().commit();
        	return false;
        	
         } catch (HibernateException e) {
           // if (session.getTransaction()!=null) 
            //	session.getTransaction().rollback();
            return false;
         }finally {
            session.close(); 
         }
    }
 
    /*
     * Supprime le billet pris en paramètre de la table Billet
     */
    public boolean deleteBillet(Billet billet,Long idUser) {
        Session session = sessionFactory.openSession();  
        
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx deleBillet"+billet.getIdB()+" user : "+ idUser );
        try{
        	
         if (hasPermission(idUser,billet.getIdB(),"delete",session))
         {
        	 System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx hasPermission,");
	        	session.beginTransaction();
		        session.delete(billet);
		        session.getTransaction().commit();
		        return true;
         }
         return false;
        } catch (HibernateException e) {
        	//if(session.getTransaction()!=null)
        		//session.getTransaction().rollback();
        	e.printStackTrace();
        	return false;
		} finally {
			session.close();
		}		
	}
    
    /*
     * Supprime l'ensemble des billets de la table Billet
     */
   /* public boolean deleteAllBillets() {
    	List<Billet> listBillets = selectAllBillets();
    	for (Billet billet : listBillets) {
			if(!deleteBillet(billet))
				return false;
		}
    	return true;
    }*/
    

    /*
     * TAG 
     */
    
    /*
     * Ajoute un tag à la table Tag puis renvoie son id généré
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
    
    /*
     * Renvoie un tag présent dans la table Tag à l'aide de son id
     */
    public Tag selectTagByID(Long id) {
        Session session = sessionFactory.openSession();
        Tag tag = new Tag();
        try {
        	tag = session.get(Tag.class, id);
        } catch (NullPointerException npe) {
        	npe.printStackTrace();
        	return null;
        }
        session.close();
        return tag;
    }
    
    /*
     * Renvoie un tag présent dans la table Tag à l'aide du couple idCategorie / nomTag
     */
    public Tag selectTag(Long idC, String nomTag) {
    	Session session = sessionFactory.openSession();
    	TypedQuery<Tag> query = session.createQuery("SELECT t FROM Tag t WHERE id_c = :idc AND nom_tag = :nom",Tag.class);
    	query.setParameter("idc", idC);
    	query.setParameter("nom", nomTag);
    	try {
    		return query.getSingleResult();
    	} catch (NoResultException nre) {
    		return null;
    	}
    }
    
    /*
     * Renvoie la liste de tous les tags présents dans la table Tag
     */
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

    /*
     * Renvoie la liste de tous les tags dont le nom commence par la chaîne de caractères prise en paramètre
     */
	public List<Tag> selectAllTagsLike(String like){
    	Session session = sessionFactory.openSession();
    	TypedQuery<Tag> query = session.createQuery("SELECT t FROM Tag t WHERE lower(nom_tag) LIKE lower(:nom)",Tag.class);
    	query.setParameter("nom", like+"%");
    	try {
    		return query.getResultList();
    	} catch (NoResultException nre) {
    		return null;
    	}
	}
	
	/*
	 * Modifie le tag passé en premier paramètre en lui attribuant les valeurs idCategorie et nomTag du tag passé en second paramètre
	 */
    public boolean updateTag(Tag updateTag, Tag newTag) {
    	Session session = sessionFactory.openSession();
        try{
        	session.beginTransaction();
        	Tag tag = (Tag)session.get(Tag.class, updateTag.getIdT()); 
        	tag.setNomTag(newTag.getNomTag());
            tag.setIdC(newTag.getIdC());
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
 
    /*
     * Supprime le tag pris en paramètre de la table Tag
     */
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
    
    /*
     * Supprime l'ensemble des tags présents dans la table Tag
     */
    public boolean deleteAllTags() {
    	List<Tag> listTags = selectAllTags();
    	for (Tag tag : listTags) {
			if(!deleteTag(tag))
				return false;
		}
    	return true;
    }

    
    /*
     * BILLET-TAG
     */
    
    /*
     * Crée une relation Billet_Tag dans la table Billet-tag
     * Cette méthode est appelée uniquement lorsque l'utilisateur associe un tag à un billet
     */
    public Long createBilletTag(Billet billet, Tag tag) {
    	Session session = sessionFactory.openSession();
    	Long idBT;	
    	if(tag.getIdU() != null)
    	{
           initPermission(tag.getIdU(),billet.getIdB(),session); 
    	}
    	try {
    		//session.beginTransaction();
    		session.save(new Billet_Tag(billet.getIdB(), tag.getIdT()));
    		//session.getTransaction().commit();
    		idBT = selectAllBilletsTags().get(selectAllBilletsTags().size()-1).getIdBT();
    	} catch(Exception e) {
    		return Long.valueOf(0);
    	}
    	session.close();
    	return idBT;
    }
    
    /*
     * Renvoie un Billet_Tag présent dans la base à l'aide d'un billet et d'un tag pris en paramètres
     */
    public Billet_Tag selectBilletTag(Billet billet, Tag tag) {
    	Session session = sessionFactory.openSession();
    	TypedQuery<Billet_Tag> query = session.createQuery("SELECT bt FROM Billet_Tag bt WHERE id_b = :idb AND id_t = :idt",Billet_Tag.class);
    	query.setParameter("idb", billet.getIdB());
    	query.setParameter("idt", tag.getIdT());
    	try {
    		return query.getSingleResult();
    	} catch (NoResultException nre) {
    		return null;
    	}
    }
    
    /*
     * Renvoie l'ensemble des tags associés à un billet pris en paramètre
     */
    public List<Tag> selectTagsByBillet(Billet billet){
    	Session session = sessionFactory.openSession();
    	TypedQuery<Billet_Tag> query = session.createQuery("SELECT bt FROM Billet_Tag bt WHERE id_b = :idb",Billet_Tag.class);
    	query.setParameter("idb", billet.getIdB());
    	try {
    		List<Billet_Tag> listBT = query.getResultList();
    		List<Tag> listTags = new ArrayList<Tag>();
    		for(Billet_Tag bTag : listBT) {
				listTags.add(selectTagByID(bTag.getIdT()));
    		}
    		if(listTags.isEmpty())
    			return null;
    		else
    			return listTags;    	
    	} catch (NoResultException nre) {
    		return null;
    	} 
    }
    
    /*
     * Renvoie l'ensemble des tags associés à un billet dont l'id est pris en paramètre
     */
    public List<Tag> selectTagsByBilletId(Long id){
    	Billet billet = selectBilletByID(id);
    	if(billet!=null)
    		return selectTagsByBillet(billet);
		else
			return null;
	}
    
    /*
     * Renvoie l'ensemble des billets associés à un tag pris en paramètre
     */
    public List<Billet> selectBilletsByTag(Tag tag){
    	Session session = sessionFactory.openSession();
    	TypedQuery<Billet_Tag> query = session.createQuery("SELECT bt FROM Billet_Tag bt WHERE id_t = :idt",Billet_Tag.class);
    	query.setParameter("idt", tag.getIdT());
    	try {
    		List<Billet_Tag> listBT = query.getResultList();
    		List<Billet> listBillets = new ArrayList<Billet>();
    		for(Billet_Tag bTag : listBT) {
				listBillets.add(selectBilletByID(bTag.getIdB()));
    		}
    		if(listBillets.isEmpty())
    			return null;
    		else
    			return listBillets;    	
    	} catch (NoResultException nre) {
    		return null;
    	} 
	}
    
    
    /*
     * Renvoie la liste de tous les couples Billet-Tag présents dans la table Billet_tag
     */
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
    
    /*
     * Supprime une relation billet-tag présente dans la table Billet_tag
     * Méthode appelée dans le cas où l'utilisateur supprime un tag ou bien le détache simplement du billet
     */
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
    
    /*
     * Supprime l'ensemble des relations billet-tag présentes dans la table Billet_tag concernées par le billet pris en paramètre
     * Méthode jamais appelée car le cas d'une suppression de billet est traité automatiquement (Cascade, clé étrangère vers la table Billet)
     */
    @SuppressWarnings("unused")
	private boolean deleteAllBilletsTags(Billet billet) {
    	List<Billet_Tag> listBT = selectAllBilletsTags();
    	for(Billet_Tag bTag : listBT)
    		if(bTag.getIdB() == billet.getIdB())
    			if(!deleteBilletTag(bTag))
    				return false;
    	return true;
    }
    
    /*
     * Supprime l'ensemble des relations billet-tag présentes dans la table Billet_Tag concernées par le tag pris en paramètre
     * Méthode jamais appelée car le cas d'une suppression de tag est traité automatiquement (Cascade, clé étrangère vers la table Tag)
     */
    @SuppressWarnings("unused")
	private boolean deleteAllBilletsTags(Tag tag) {
    	List<Billet_Tag> listBT = selectAllBilletsTags();
    	for(Billet_Tag bTag : listBT) 
    		if(bTag.getIdT() == tag.getIdT()) 
    			if(!deleteBilletTag(bTag))
    				return false;
    	return true;
    }
    
    /*
     * Affiche l'ensemble des utilisateurs
     */
    public void printListUsers() {
        List<Utilisateur> listUtilisateurs = this.selectAllUsers();
        System.out.println("Liste des utilisateurs : ");
        for (Utilisateur utilisateur2 : listUtilisateurs) {
			System.out.println(utilisateur2.toString());
		}    	
    }

    /*
     * Affiche l'ensemble des catégories
     */
    public void printListCategories() {
    	List<Categorie> listCategories = this.selectAllCategories();
        System.out.println("Liste des catégories : ");
        for (Categorie categorie : listCategories) {
			System.out.println(categorie.toString());
		}
    }
    
    /*
     * Affiche l'ensemble des billets
     */
    public void printListBillets() {
    	List<Billet> listBillets = this.selectAllBillets();
        System.out.println("Liste des billets : ");
        for (Billet billet : listBillets) {
			System.out.println(billet.toString());
		}
    }
    
    /*
     * Affiche l'ensemble des tags
     */
    public void printListTags() {
    	List<Tag> listTags = this.selectAllTags();
        System.out.println("Liste des tags : ");
        for (Tag tag : listTags) {
			System.out.println(tag.toString());
		}
    }
    
    /*
     * Affiche l'ensemble des relations billet-tag
     */
    public void printListBilletsTags() {
    	List<Billet_Tag> listBT = this.selectAllBilletsTags();
    	System.out.println("Liste des billet_tags : ");
    	for (Billet_Tag bTag : listBT) {
    		System.out.println(bTag.toString());
    	}
    }
    
    /**
     * Initialiser les droits pour un utilisateur sur un billet 
     * @param idUtilisateur
     * @param idBillet
     * @param session
     */
    public void initPermission(Long idUtilisateur,Long idBillet,Session session) {

    	Permission permission = new Permission("delete",idBillet, idUtilisateur);
    	Permission permission2 = new Permission("update",idBillet, idUtilisateur);
    	session.save(permission);
    	session.save(permission2);
    

    }
    
	public boolean hasPermission(Long idUser,Long idBillet,String permission,Session session){
    	
    	try {
    
	    	TypedQuery<Permission> query = session.createQuery("SELECT p FROM Permission p WHERE p.name = :permission "
	    			+ "AND p.idUser = :idUser AND p.idBillet = :idBillet ",Permission.class);
	    	query.setParameter("permission", permission);
	    	query.setParameter("idUser", idUser);
	    	query.setParameter("idBillet", idBillet);
	    	
	    	List<Permission> p = query.getResultList();
    		if (!p.isEmpty())
    		{
    			  
    			return true;
    		}
    		return false;
    		
    	} catch (NoResultException nre) {
        		return false;
    	}
    	
	}

	public boolean hasPermission(Long idUser,Long idBillet,List<Permission> permissions){
    	
		
		for (Permission p : permissions) {
		
			if(p.getIdBillet().equals(idBillet) && p.getIdUser().equals(idUser))
			{
				return true;
			}
		
		}
    	return false;
    	
	}


    /*
     * Méthode privée ayant pour but de tester l'ensemble des méthodes de la classe Manager
     */
	@SuppressWarnings("unused")
	private void test() {
    //public static void main(String args[]) {
    	//Utilisateur utilisateur = new Utilisateur("myemail@gmail.com","mypassword", "myName", "myFirstName",1);
    	//Utilisateur newUtilisateur = new Utilisateur("mynewemail@gmail.com","mynewpassword", "myNewName", "myNewFirstName",1);
    	//Tag tag = new Tag(Long.valueOf(30), "bio234");
    	//Tag newTag = new Tag(tag.getIdC(), "bio2345");
    	//Categorie categorie = new Categorie("test");
    	//Categorie categorie2 = new Categorie("newTest");
    	//Billet billet = new Billet("Billet test");
    	//Billet newBillet = new Billet("UPDATE Billet test");
    	Manager manager = new Manager();
        manager.setup();
        //Situation de départ
        System.out.println("Situation de départ : \n");
        //manager.printListUsers();
        //manager.printListTags();       
       
        //manager.printListCategories();
        //manager.printListBillets();
        
        //Création
        //manager.createUser(utilisateur);
        //manager.createTag(tag);
        //manager.createCategory(categorie);
    	//Tag testTag = new Tag(manager.selectCategory("test").getIdC(),"test 3");
    	//manager.createTag(tag);
        //manager.createBillet(billet);
        System.out.println("Après création : \n");
        //manager.printListUsers();
        //manager.printListTags();
        //manager.printListCategories();
        //manager.printListBillets();
        
        //Utilisateur testUtil = manager.selectUser("a@a.a", "jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=");
        //Categorie testCat = manager.selectCategory("projet");
        //Billet testBillet = manager.selectBillet("<p>Billet 5</p>");
        //Tag testTag = manager.selectTag(Long.valueOf(2), "User1");
        //Tag testTag2 = manager.selectAllTagsLike("User").get(0);
        //Billet_Tag testBilletTag = manager.selectBilletTag(manager.selectBilletByID(Long.valueOf(118)), manager.selectTagByID(Long.valueOf(2)));
        
        //Modification
        //manager.printListBilletsTags();
        //List<Categorie> listCat = manager.selectAllCategoriesLike("pro");
        //System.out.println(listCat.size());
        //manager.updateUser(utilisateur, newUtilisateur); 
        //manager.updateTag(tag, newTag);
        //manager.updateCategory(categorie, categorie2);
        //Categorie categorie3 = new Categorie("Utilisateur");
        //manager.updateCategory(manager.selectCategory("auteur"), categorie3);
        //manager.updateBillet(billet, newBillet);
        System.out.println("Après modification : \n");
        //manager.printListUsers();
        //manager.printListTags();
        //manager.printListCategories();
        //manager.printListBillets();
        
        //Suppression
        //Utilisateur delNewUser = manager.selectUser(newUtilisateur.getEmail(), newUtilisateur.getMotDePasse());
        //Utilisateur delUser = manager.selectUser(utilisateur.getEmail(), utilisateur.getMotDePasse());
        //manager.deleteUser(delUser);
        //manager.deleteUser(delNewUser);
        //Tag delTag = manager.selectTag(tag.getIdC(), newTag.getNomTag());
        //Tag delNewTag = manager.selectTag(newTag.getIdC(), newTag.getNomTag());
        //manager.deleteTag(tag);
        //manager.deleteTag(delTag);
        //Categorie delCat = manager.selectCategory(categorie2.getNomCategorie());
        //manager.deleteCategory(delCat);
        //manager.deleteCategory(categorie);
        //manager.deleteBillet(manager.selectBillet(newBillet.getText()));
        System.out.println("Après suppression : \n");
        //manager.printListUsers();
        //manager.printListTags();
        //manager.printListCategories();
        //manager.printListBilletsTags();
        manager.exit();
    } 
}