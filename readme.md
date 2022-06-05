# Compilation & lancement
On peut lancer l'application directement ou via docker.
## Solution native
* Installer MongoDB et un shell ou Robo 3T pour créer un utilisateur avec la commande : db.createUser({ user:'user',pwd:'123456',roles:[ { role:'readWrite', db: 'tripDB'}]});
* reprendre le nom d'utilisateur choisi et le mot de passe pour l'indiquer dans le fichier application.properties
* Pour lancer directement l'application : mvn spring-boot:run

* Pour compiler l'application : mvn clean install (les tests unitaires seront lancés)

## Solution Dockerisée
* Il faut configurer l'utilisateur mongo dans le fichier init-mongo.js (il doit être identique que dans le fichier application.properties)
* La stack peut être lancée avec la commande : docker-compose up

## Accès à l'application
* l'application est ensuite accessible sur http://localhost:8080/swagger-ui.html pour l'interface OpenAPI3

Les endpoints sont accessibles aux URL suivantes :
* POST : localhost:8080/v1/trip-manager/user (pour la création d'utilisateur) La sortie donne l'objet "User" avec un identifiant unique et assez long.
* POST : localhost:8080/v1/trip-manager/user/<identifiant_utilisateur>/trip : il faut passer l'identifiant récupéré à la création de l'utilisateur et passer l'objet Trip tel qu'il est décrit dans l'énnoncé. Le service s'occupe de faire les différents calculs (accélération max, vitesse moyenne etc.)
* GET : localhost:8080/v1/trip-manager/user/<identifiant_utilisateur>/trip : en passant l'identifiant de l'utilisateur, on obtient la liste de ses trajets
* PUT : localhost:8080/v1/trip-manager/user/<identifiant_utilisateur>/trip : on passe l'identifiant de l'utilisateur dans l'url et l'identifiant du trajets dans le payload envoyé (on aurait pu aussi passer l'identifiant du trajet dans l'url, c'est un choix)

# Choix technique & Difficultés

* J'ai choisi d'organiser mes classes dans une approches "package-by-feature" : https://medium.com/sahibinden-technology/package-by-layer-vs-package-by-feature-7e89cde2ae3a 
* J'ai utilisé SpringDoc pour générer l'interface OpenAPI 3 (SpringFox ne semble plus maintenu et n'est plus compatible à partir de la version 2.5 ou 2.6 de Spring Boot) : http://localhost:8080/swagger-ui/index.html
* Approche BDD des tests (Behaviour-driven-development) Given / When / Then
* Le module Spring Boot Security est une partie que je maitrise assez mal et il pourrait être intéressant de suivre un cours en ligne/Mooc/Livre de manière plus approfondie
* Toujours sur la partie sécurité, le mot de passe est stocké sous forme de MD5 qui est très peu robuste, l'utilisation de Spring Security / BCryptPasswordEncoder permettrait une plus grande robustesse.
* Il semble possible de faire tourner un MongoDB embarqué avec de.flapdoodle.embed.mongo mais cela ralenti les TU et la couche repository étant actuellement relativement simple, il ne me semblait pas nécessaire d'approfondir cette partie.
* Je ne connais pas très bien les fonctionnalités "modulaires" apportées par Java 11. J'ai donc utilisé Java 11 pour la compilation, mais en gardant l'architecture classique des développements java.

# Améliorations possibles
Outre les points abordés dans le paragraphe précédent, on aurait pu améliorer :
* les codes retours et la gestion des erreurs (un mauvais identifiant d'utilisateur renvoie un contenu vide plutôt qu'un message qui indique que cet utilisateur n'existe pas)
* Quelques endpoints manquants (avoir la liste des utilisateurs, supprimer un trajet
* Un compte "super-utilisateur" qui pourrait supprimer des utilisateurs et des trajets qui ne seraient pas à lui.
* La couverture du code est loin d'être idéale, utiliser des outils comme SonarQube permettrait de voir les parties à couvrir en priorité.