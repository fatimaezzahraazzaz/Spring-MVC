#  Rapport de TP – Partie 1
##  Réalisé par :
- **Nom :** [Ton Nom]  
- **Filière :** [Ta Filière]  
- **Encadrant :** [Nom de l'enseignant]  
- **Date :** Mai 2025  

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

## 🧬 Modèle de données

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






