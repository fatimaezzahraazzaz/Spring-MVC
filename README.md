#  Rapport de TP – Partie 1
##  Réalisé par :
- **Nom :** FatimaEzzhara Azzaz 
- **Filière :** SDIA


##  Objectif du TP

L’objectif de ce TP est de créer une application web JEE complète permettant de gérer une base de données de patients. Le projet repose sur le framework **Spring Boot** et utilise **Spring MVC** pour la couche web, **Thymeleaf** pour la vue, et **Spring Data JPA** pour l’accès aux données.

##  Technologies utilisées

| Technologie       | Description                        |
|-------------------|------------------------------------|
| Java 17+          | Langage principal                  |
| Spring Boot 3     | Cadre principal de l’application   |
| Spring MVC        | Gestion des routes et du modèle MVC |
| Thymeleaf         | Moteur de template HTML côté serveur |
| Spring Data JPA   | Accès aux données (ORM)            |
| H2 / MySQL        | Base de données                    |
| Bootstrap 5       | Mise en forme de l’interface       |
| Maven             | Gestion des dépendances            |

##  Modèle de données

![image](https://github.com/user-attachments/assets/3e2f33a5-7928-42d0-85eb-5d226dd95ad5)


## Fonctionnalités développées
### 1. Affichage des patients
Liste dynamique avec pagination de la base de données des patients. Les patients sont affichés sous forme de tableau HTML formaté avec Bootstrap.

![image](https://github.com/user-attachments/assets/31b7a0a6-fe6a-4126-9081-7e576d172cbf)

### 2. Pagination
La pagination est implémentée via PageRequest.of(page, size) pour charger les données par page.

### 3. Recherche
La barre de recherche permet de filtrer les patients par nom à l'aide de la méthode findPatientByNomContains() de Spring Data JPA.
![image](https://github.com/user-attachments/assets/ce41c5be-723d-42c3-b649-22dd5de11cb4)


### 4. Suppression
Un bouton permet de supprimer un patient en appelant le contrôleur avec /delete?id={id}. L’identifiant est passé en paramètre via @RequestParam.
![image](https://github.com/user-attachments/assets/98970ea9-b7ed-4dfb-96f2-782c48b25523)


### 5. Améliorations de l’interface
L’interface a été améliorée grâce à Bootstrap :

table-bordered pour le tableau

btn pour les boutons d’action

form-control pour le champ de recherche

## Interface utilisateur
Les captures suivantes montrent l’interface développée :

Liste des patients et pagination :

![image](https://github.com/user-attachments/assets/e766b9d7-beb2-47e1-9c0b-767c2d8f39e6)


Recherche par nom :

![image](https://github.com/user-attachments/assets/cd54cb75-bc35-47ee-82a1-37c43b03c15d)


Suppression d’un patient
![image](https://github.com/user-attachments/assets/c6570c01-c561-463b-92fc-047a7bce3c25)


# Rapport – Partie 2:

## 1. Objectif
Cette deuxième partie du projet consiste à implémenter l’interface utilisateur de l’application de gestion des patients à l’aide de Thymeleaf, un moteur de templates pour Spring MVC. Le but est de fournir des vues web fonctionnelles et ergonomiques pour :

Fournir un formulaire pour créer un patient,Modifier un patient,Validation des formulaire,Utiliser un template commun pour uniformiser le rendu des pages

## 3. Description des fichiers de vues
### 3.1 Template principal (template1.html)
Définit la structure HTML globale : navbar, inclusion des CSS/JS Bootstrap, layout

Navbar avec liens vers l’accueil, création d’un nouveau patient, recherche, et menu utilisateur

Utilisation de la balise layout:fragment="content1" pour injecter le contenu des vues enfants

![image](https://github.com/user-attachments/assets/da087359-c0d3-4b28-be4d-17404ebf25dc)


### 3.2 Liste des patients (patients.html)
- Page principale affichant la liste paginée des patients dans un tableau

- Formulaire de recherche par mot-clé (keyword) au-dessus du tableau

- Chaque patient est affiché avec ses informations : ID, nom, date de naissance, malade (booléen), score

- Boutons d’action :

- Supprimer avec confirmation JavaScript

- Modifier redirige vers le formulaire de modification

- Pagination sous forme de boutons numérotés

- Intégration des icônes Bootstrap pour rendre les actions plus intuitives
  
![image](https://github.com/user-attachments/assets/67ab9f66-ec0e-492d-ad76-4c928e016cd7)

### 3.3 Formulaire patient pour création et modification (formPatients.html)
Formulaire centralisé permettant de :

- Créer un nouveau patient (sans champ ID visible)

- Modifier un patient existant (ID caché dans un champ hidden et affiché en label)
  
- Affichage des messages d’erreur de validation sous chaque champ via th:errors

- Bouton “Save” pour soumettre le formulaire au backend
  - image Formulaire d'ajout:
![image](https://github.com/user-attachments/assets/ae52d473-eb0f-485a-a856-e0084db4de92)

  - image formulaire de modification :
    ![image](https://github.com/user-attachments/assets/ac4abfa3-cde4-4963-87cf-d1888f12ce84)


## 4. Fonctionnalités implémentées
- Affichage dynamique des données patients depuis le backend via Spring MVC

- Recherche fonctionnelle grâce au champ keyword qui filtre les patients côté serveur

- Pagination pour limiter le nombre de patients affichés par page, avec navigation simple

- Suppression sécurisée avec popup de confirmation avant la suppression

- Formulaire réutilisable pour création et modification avec validation affichée en temps réel
  
- Navigation claire grâce au template commun et à la navbar

# Rapport -Partie 3
## Description du projet
Ce projet est une application web de gestion des patients construite avec Spring Boot 3.5.0, Spring Security, Thymeleaf, et Bootstrap. L’application permet à des utilisateurs ayant des rôles distincts (USER, ADMIN) de consulter ou gérer des informations sur des patients.

## Fonctionnalités de sécurité
## Authentification
- Utilisateurs en mémoire (InMemoryUserDetailsManager)

- Mots de passe encodés avec PasswordEncoder

- Page de login personnalisée (/login)

- Page d’erreur d’accès refusé (/notAuthorized)

## Architecture du projet
### security/SecurityConfig.java
- Définit les rôles, les utilisateurs et le filtre de sécurité (SecurityFilterChain)

- Gère les redirections vers /login et /notAuthorized

### web/SecurityController.java
- Contrôleur pour gérer les pages login et notAuthorized

### web/PatientController.java
- Gère l’affichage, l’ajout, la suppression et l’édition des patients

- Accès contrôlé avec @PreAuthorize("hasRole('ROLE_ADMIN')") pour les actions admin
##  Interfaces Thymeleaf
- login.html : Formulaire d’authentification
![image](https://github.com/user-attachments/assets/2bd5716f-cbb5-4f8c-8239-d27854bcdd0f)


- notAuthorized.html : Affiché lors d’un accès non autorisé
![image](https://github.com/user-attachments/assets/a8e4b4c2-4df8-455c-85cc-69ab91dd1cc5)

- Liste Patient pour Admin :
![image](https://github.com/user-attachments/assets/c4ca3318-ba81-4002-ab77-344aca17c4c2)

- Liste Patient pour User :
![image](https://github.com/user-attachments/assets/1953d9af-adb7-44a2-b982-a92c28d16be8)

# Authentification JDBC avec base de données MySQL:
Dans cette partie, nous avons remplacé l’authentification en mémoire par une authentification via JDBC. Les utilisateurs et leurs rôles sont désormais enregistrés dans une base de données MySQL grâce au bean JdbcUserDetailsManager.
## 1. Configuration de Spring Security avec JDBC:
- JdbcUserDetailsManager est une implémentation de UserDetailsManager qui lit les utilisateurs depuis la base de données.

- Il s'appuie sur deux tables : users et authorities (standard JDBC schema de Spring Security).

- DataSource est automatiquement injecté par Spring à partir des propriétés de configuration (spring.datasource.url, etc.).
 ## 2. Structure de la base de données (schema.sql):
 -, users : stocke le login, mot de passe encodé, et si l’utilisateur est activé (enabled).

- authorities : contient les rôles associés à chaque utilisateur (ROLE_USER, ROLE_ADMIN...).

- Ce schéma est attendu par défaut par JdbcUserDetailsManager.

## 3.Sécurité avec SecurityFilterChain :
- authorizeHttpRequests : toute requête doit être authentifiée, sauf /webjars/**.

- formLogin().loginPage("/login") : remplace le formulaire par défaut par un formulaire personnalisé.

- accessDeniedPage("/notAuthorized") : page affichée si un utilisateur authentifié mais non autorisé tente d’accéder à une ressource.
 ## 4. Création des utilisateurs automatiquement
- Ce CommandLineRunner s’exécute au démarrage de l’application.

- Il vérifie si les utilisateurs existent via userExists.

- S’ils n’existent pas, ils sont créés dans la base avec un mot de passe BCrypté.

- Les utilisateurs sont associés à des rôles via la table authorities.

  ### Table user :
  ![image](https://github.com/user-attachments/assets/fe6b41e1-6d27-49ad-99d3-ce4ec84df7ba)

  ### Table authoritize :
  ![image](https://github.com/user-attachments/assets/bb8bae13-df2a-466b-8cfd-6c5429aaed0d)

  ### Authentification avec Admin2 :
  ![image](https://github.com/user-attachments/assets/8df370f4-df80-4d4a-abd6-9b22d04ee292)

  ### Authentification avec user11 :

  ![image](https://github.com/user-attachments/assets/a47887e1-45bf-490f-b1f0-6227fdd8bbc5)

  # UserDetails Service:
  ## 1. Modèles d’Entités
  ### a) AppRole :
  - Cette entité représente un rôle de sécurité dans l’application (ex : USER, ADMIN).

  - Le rôle est identifié par une chaîne de caractères unique (role).
  ### b) AppUser:
  - Cette entité représente un utilisateur de l’application.

  - Chaque utilisateur a un identifiant unique userId (généré par UUID).

   - Un utilisateur possède un nom d’utilisateur, un mot de passe (hashé), un email et une liste de rôles (relation ManyToMany).

  - Le chargement des rôles est éager (EAGER), ce qui signifie que les rôles sont automatiquement chargés avec l'utilisateur.
   ## 2. Service Métier - Gestion des Utilisateurs et Rôles
   ### Interface AccountService :
   - Cette interface définit les opérations principales pour la gestion des utilisateurs et rôles.

   - Ajout d’utilisateur et de rôle, affectation et retrait de rôle, récupération d’un utilisateur.
  ### Implémentation AccountServiceImpl:
  -   Ajout d’un nouvel utilisateur : vérifie que le nom d’utilisateur n’existe pas déjà et que les mots de passe correspondent, encode le mot de     passe avec PasswordEncoder, puis sauvegarde l’utilisateur.

   - Ajout d’un nouveau rôle : vérifie l’existence avant d’enregistrer un nouveau rôle.

  - Gestion des rôles des utilisateurs : ajoute ou retire un rôle d’un utilisateur existant.

  - Chargement d’un utilisateur : récupère un utilisateur via son nom.

  - Le service est annoté avec @Transactional pour gérer les transactions automatiquement et @Service pour que Spring le détecte comme bean.
    ## 3. Intégration avec Spring Security
    ### a) UserDetailServiceImpl
     - Implémente l’interface UserDetailsService de Spring Security.

    -  Utilise AccountService pour récupérer un utilisateur par son nom.

    - Convertit l’entité AppUser en UserDetails utilisable par Spring Security.

    -  Les rôles sont extraits et passés sous forme de tableau pour être utilisés dans la sécurité.
   
    ### b) SecurityConfig

      - Configure Spring Security.

      - Définit que toutes les requêtes (sauf /webjars/**) nécessitent une authentification.

      - Personnalise la page de login et la redirection en cas de succès.

      - Définit une page d’accès refusé personnalisée (/notAuthorized).

       -  le UserDetailsService personnalisé qui s’appuie sur la base de données.
   
    ## 4. Chargement initial des données (CommandLineRunner)
    Le bean commandLineRunnerUserDetails initialise la base de données avec des rôles et des utilisateurs par défaut :
    - Crée les rôles USER et ADMIN.

    - Crée trois utilisateurs avec leurs mots de passe (chiffrés dans AccountServiceImpl).

    - Attribue les rôles adéquats à chaque utilisateur.

    ## login avec Admin
    ![image](https://github.com/user-attachments/assets/88dec010-ea55-4d0f-9332-60ff5d86c410)

    ## login avec User1:
    ![image](https://github.com/user-attachments/assets/02a15ac9-deff-4353-9be9-01a1eb46d8a6)

    ## Table appUser :
    ![image](https://github.com/user-attachments/assets/37b7e4eb-70d6-4364-958c-4ee9a4dcc6bf)

    ## Table appRole
    ![image](https://github.com/user-attachments/assets/314ec008-d5fe-4569-a7c5-1fe654ff1ef6)

    ## Table app_user -roles

    ![image](https://github.com/user-attachments/assets/d273c83e-5747-4772-bfba-3a3cec01eb7b)


    ## Ajouter un role a User1 :
    ![image](https://github.com/user-attachments/assets/b78a940e-6e0d-4d37-92ce-b7b91bd304bb)
    ![image](https://github.com/user-attachments/assets/20d55929-542e-49ec-890a-14b192951e82)
    ![image](https://github.com/user-attachments/assets/3dadf997-b531-40df-8247-aed872681ec0)
    - user1 a maintenet le droit de supprimer ajouter ou modifier un user :
      ![image](https://github.com/user-attachments/assets/a0bd7e72-7365-4cef-8778-b174c25bc67c)









 







