# BigData5A
### Big Data course at Polytech Nancy

Lien de l'image Docker de l'exercice: https://hub.docker.com/r/leokr/syoucef-mongodbservice

## Cours 1
Image: Element générique  
Conteneur: Instance d'une image  
Commandes:
 * ```docker run image_name```: permet de créer et démarrer une image existante en local. Si l'image n'existe pas en local, elle sera téléchargée à partir de _docker hub_
 * ```docker run -d image_name:tag```: permet de démarrer une image en arrière plan, avec le tag permettant de détailler la version à démarrer (par défaut: _latest_)
 * ```docker ps```: permet de lister tous les conteneurs qui sont en cours d'exécution. Chaque conteneur dispose d'un identifiant unique
 * ```docker ps -a```: permet de lister tous les conteneurs avec leurs status
 * ```docker run -d -p 9999:80 nginx```: permet de démarrer une instance de Nginx sur port 9999 sur la machine hôte, et sur le port 80 sur le conteneur
 * ```docker run --name customName -d -p 8989:880 nginx```: permet de mettre un nom spécifique _nomCustom_
 * ```docker stop containerId```: permet de stopper un conteneur en cours d'exécution
 * ```docker start containerId```: permet de redémarrer un conteneur
 * ```docker rm containerId```: permet de supprimer un conteneur (ne doit pas être en cours d'exécution)
 * ```docker rmi -f image_name```: permet de supprimer une ou plusieurs image(s) de force
 * ```exec commande```: permet d'exécuter une commande dans un conteneur démarré
 * ```docker inspect containerId```: permet d'inspecter le conteneur _containerId_
 * ```docker logs containerId```: permet d'obtenir les fichiers de log
 * ```docker run -tid --name variableenvironnement --env MAVARIABLE="variable" ubuntu:latest```: permet de transmettre une variable d'environnement
 * ```docker run -tid --name fichiervariableenvironnement --env-file mesvariables.lst ubuntu:latest```: permet de transmettre un fichier de variables d'environnement
 * ```docker exec -ti variableenvironnement sh```: récupération de la variable d'environnement
 * ```docker volume```: permet de réaliser des opérations sur les volumes
   * ```create```
   * ```inspect```
   * ```ls```
   * ```prune```
   * ```rm```

Exemple pour démarrer une image MySQL:
```docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -d mysql```: Nom de l'image: _mysql_, mot de passe root: _root_  
```docker exec -ti mysql mysql --password```

Exemple pour démarrer une image Ubuntu avec Java:
```docker run -it ubuntu bash```: Obtention image ubuntu  
```apt-get update```: Mise à jour APT  
```apt-get install java```: Installation Java  
```apt-get install vim```: Installation de VIM

Pour faire du mapping de volume:  
```docker run -dti -p 8080:80 -v /Users/user/doc/dockerexample/page/:/usr/share/nginx/html/ --name servernginx nginx```  
```docker run -tid --name webvolume -p 9698 --mount source=monvolume,target=/usr/share/nginx/html/ nginx```  
```docker run -it --privileged --pid=host debian nsenter -t 1 -m -u -n -i sh``` (accéder au volume)  
```cd /var/lib/docker/volumes/ | ls``` (volumes)

Définir sa propre image:  
 * Faire son conteneur comme souhaité
 * ```docker commit -m "création d'une image ubuntu modifiée" containerId imageName```  

Définir un Dockerfile:  
 * Créer un fichier _Dockerfile_
 * Structure:

```java
FROM openjdk
COPY Application.class Application.class
CMD ["java", "Application"]
```

```python
FROM ubuntu
COPY test.py test.py
RUN apt-get update
RUN apt-get install python -y
CMD ["python", "test.py"]
```

Envoyer une image sur DockerHub:
 * Créer une image
 * Créer un dépôt dockerHub
 * Se connecter à docker ```docker login```
 * Créer un lien entre l'image et le dépôt ```docker tag image depot```
 * Envoyer l'image sur dockerHub ```docker push depot```


## Cours 2

[MongoDB](https://www.mongodb.com/fr-fr) est un système de gestion de base de données documentaire.

Une base de données documentaire est, à la différence d'une base de données relationnelle, orientée sur les documents. Il n'existe pas de "tables" avec des relations entre-elles, ici, il s'agit de documents sous tout type de forme. Les documents ne nécessitent pas d'avoir tous les mêmes champs, ce qui permet une grande flexibilité.

Ce type de base de données est donc idéal lorsque les données prennent différentes formes ou que les données sont très volumineuses.

Utilisation de l'image Docker de MongoDB:
```docker run --name mongodb -d -p 27017:27017 mongo```  
Il est possible d'accéder à la CLI MongoDB avec la commande suivante: ```docker exec -ti *containerID* mongo```

##### Commandes MongoDB CLI utiles

 * ```use produit```: créé une base de données intitulée *produit*
 * ```db.createCollection("test")```: créé une collection intitulée *test*
 * ```db.test.insert({"propriete":"valeur"})```: ajoute un objet à la collection *test*, possedant une propriété *propriete* ayant la valeur *valeur*
 * ```show collections```: affiche les collections
 * ```db.test.find()```: affiche tous les éléments contenus dans la collection *test*
 * ```db.test.find({"propriete":"autrevaleur"})```: affiche l'objet possédant la propriété *propriete* égale à la valeur *autrevaleur*

## Exercice

##### Sujet

```Développer un micro-service qui utilise pour la persistance des données MongoDB. Créer une image Docker de votre service et renseigner le lien au début de votre readme.md pour la télécharger.```

##### Création du service
Nous commençons par réaliser un nouveau projet Spring, avec les dépendances ```Spring Web``` et ```MongoDB```. Ensuite, nous créons l'interface implémentant le ```MongoRepository```, que nous appelons ```ProduitRepository```:  
```java
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProduitRepository extends MongoRepository<Produit, String> {
    Produit findByDescription(String desc);
}
```  
Ensuite, nous créons le modèle que nous souhaitons stocker, des Produits:
```java
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Document(collection = "produit")
public class Produit implements Serializable {
    @Id
    private String id; // id auto-généré par mongodb
    @Field("description")
    private String description;
    @Field("prix")
    private double prix;

    public Produit() {}

    public Produit(String id, String description, double prix) {
        this.id = id;
        this.description = description;
        this.prix = prix;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id: '" + id + '\'' +
                ", description: '" + description + '\'' +
                ", prix: " + prix +
                '}';
    }
}
```
Nous pouvons noter les différentes annotations:
 * ```@Document(collection = "produit")```: symbolise la collection utilisée
 * ```@Id```: identifiant du document, généré automatiquement lors de l'ajout par MongoDB dans la base de données
 * ```@Field("description")```: indication que cet attribut est converti en tant que clé/valeur nommée *description* dans les objets de la collection ```produit``` de MongoDB

Maintenant, nous pouvons réaliser le ```contrôleur/service``` qui va s'occuper de répondre aux requêtes *HTTP* sur notre *endpoint*. Nous l'appelons ```ProduitService```:
```java
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/produit")
public class ProduitService {
    private final ProduitRepository _ps;

    public ProduitService(ProduitRepository produitRepository) {
        this._ps = produitRepository;
    }

    @GetMapping
    public Collection<Produit> get() {
        return _ps.findAll();
    }

    @GetMapping("/{description}")
    public Produit getByDescription(@PathVariable String description ) {
        return _ps.findByDescription(description);
    }

    @GetMapping("/count")
    public long countProduits() {
        return _ps.count();
    }

    @PostMapping("/{description}/{prix}")
    public Produit add(@PathVariable String description, @PathVariable double prix) {
        if(description.isEmpty()) return null; // on renvoit une erreur si pas de description
        return _ps.insert(new Produit(null, description, prix)); // sinon on insert le produit
    }

    @DeleteMapping
    public void remove(@RequestBody Produit p) {
        _ps.delete(p);
    }
}
```
Nous voyons que dans ce contrôleur, nous retrouvons des annotations relatives aux verbes du protocole *HTTP* tels que ```GetMapping``` (*GET*), ```PostMapping``` (*POST*) et ```DeleteMapping``` (*DELETE*). De plus, afin de pouvoir interagir avec la base de données *MongoDB*, nous avons besoin d'instancier notre ```ProduitRepository``` créé précédemment. Ensuite, nous pouvons utiliser les fonctions déjà existantes (grâce à l'implémentation de l'interface ```MongoRepository```) afin de réaliser des opérations de type *CRUD* sur la base.  
Dans notre projet, nous utilisons les méthodes suivantes:

 * ```count()```: permet de compter le nombre d'objets dans la collection *produit*
 * ```findAll()```: permet de renvoyer l'intégralité des objets dans la collection *produit*
 * ```insert(Produit p)```: permet d'insérer un nouvel objet dans la base de données
 * ```findByDescription(description)```: permet de faire une recherche sur la collection *produit* pour trouver un objet possédant une description identique à *description*
 * ```delete(Produit p)```: permet de supprimer un objet de la base de données

 Enfin, nous renseignons ensuite nos informations MongoDB dans les propriétés de l'application, dans ```application.properties```:
 ```properties
 spring.data.mongodb.host=mongodb
spring.data.mongodb.database=produit
```
* La première ligne indique que nous utilisons un conteneur appelé ```mongodb```
* La seconde ligne indique que notre base de données s'appelle ```produit```

Notre service est maintenant opérationnel. Nous pouvons effectuer les commandes ```mvn clean``` et ```mvn install``` pour générer notre jar exécutable, qui se nomme ```projetmongo.jar```.

##### Création du *Dockerfile*

Maintenant, nous pouvons créer un *Dockerfile* qui nous servira à concevoir l'image contenant notre service. Voici son contenu:
```dockerfile
FROM openjdk:8
ADD target/projetmongo.jar projetmongo.jar
CMD ["java", "-jar", "projetmongo.jar"]
EXPOSE 8080
```

##### Utilisation

Nous commençons par lancer un conteneur MongoDB grâce à la commande suivante: ```docker run --name mongodb -d -p 27017:27017 mongo```. Le port d'écoute de MongoDB est alors 27017.  
Ensuite, nous pouvons construire l'image de notre service grâce au *Dockerfile* créé précédemment: ```docker build -t service .``` (L'image aura donc pour nom: *service*). Maintenant nous lançons un conteneur de notre *service*, en spécifiant que nous faisons un lien avec le conteneur MongoDB: ```docker run -p 8181:8080 --link mongodb:mongodb -d service```.

Notre service est à présent disponible à l'adresse http://localhost:8181/produit.  
Voici les différentes possibilités:
 * ```GET``` http://localhost/produit : liste l'ensemble des produits de la collection
 * ```GET``` http://localhost/produit/description : renvoit l'objet correspondant à la *description*
 * ```GET``` http://localhost/produit/count : renvoit le nombre total d'objets de la collection
 * ```POST``` http://localhost/produit/{description}/{prix} : ajoute un nouveau produit ayant une description *description* et un prix *prix* dans la base de données
 * ```DELETE``` http://localhost/produit/ (avec un ```produit``` en body au format *JSON*) : permet de supprimer un produit de la base de données

##### Upload de l'image sur *Docker Hub*

Après avoir testé les différents *endpoints* de notre service, nous terminons l'exercice en envoyant l'image du service sur *Docker Hub*.  
Pour réaliser cette manipulation, nous utilisons les commandes suivantes (une fois avoir créé un *Repository* sur *Docker Hub*, intitulé ```syoucef-mongodbservice```):
 * ```docker tag service leokr/syoucef-mongodbservice```: permet de faire le lien entre l'image ```service``` et le *Repository* ```leokr/syoucef-mongodbservice```
 * ```docker push leokr/syoucef-mongodbservice```: permet d'envoyer la dernière version de l'image sur *Docker Hub*

Voici le lien de notre image: https://hub.docker.com/r/leokr/syoucef-mongodbservice