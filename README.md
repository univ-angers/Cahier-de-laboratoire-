# Cahier de laboratoire


## Installation
Pour pouvoir exécuter l'application en local il faut installer les packages suivants :

1. Eclipse Java EE IDE for Web Developers.Version: 2018-09 (4.9.0)
2. Serveur Apache Tomcat 9.0

Avant de pouvoir exécuter le projet il faudra suivre les étapes ci-dessous:
1. Importer le projet en tant que "Existing Maven Project"
2. Faire un "Run as Maven clean"
3. Faire un "Run as Maven Install"
4. Faire un "Run as Maven build" -> Inscrire dans le champs "Goals" : "package"
5. Ensuite faire un "Run on server"

Attention à chaque changement, et pour que le fichier .war du projet soit à jour, il faudra suivre les même étapes.

## Base de données
Afin d'avoir la base de données sur son local, pensez à modifier ces deux fichiers :

1. /CahierLaboratoire/src/main/resources/hibernate.cfg.xml : lignes 9,10 & 11 en indicant:

    1.jdbc:mysql://localhost/cahierlabo (ici la bdd s'appelle cahierlabo)
    
    2.username (root)
    
    3.pwd (root)
2. Faire la même chose dans le fichier : /CahierLaboratoire/src/main/java/com/groupe6/dao/dao.properties
Pensez à bien créer votre base de données en local et à importer dessus celle qu'il y a dans le dossier (dump.sql)

## Inscription / Connexion
L'application est restreinte. Il faut obligatoirement être enregistré.  

1. Connexion  : http://localhost:8080/CahierLaboratoireMaven/connexion
    - Administrateur : 
        - Mail : a@a.a
        - Mot de passe : admin
    - Utilisateur : 
        - Mail : n@n.n
        - Mot de passe : normal
        
2. Accueil  : http://localhost:8080/CahierLaboratoireMaven/accueil

## Hiérarchie :
- l'accueil se trouve sous WEBINF
- Les scripts JS et appel AJAX se trouve dans /inc/script
- La gestion de la page principale se fait sous script/MainPageManagement/mainPageManagement.js
- Hibernate, classes et contrôleurs dans les classes java


