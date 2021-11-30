# BigData5A
### Big Data course at Polytech Nancy

#### Cours 1
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


#### Cours 2

[MongoDB](https://www.mongodb.com/fr-fr) est un système de gestion de base de données documentaire.

Une base de données documentaire est, à la différence d'une base de données relationnelle, orientée sur les documents. Il n'existe pas de "tables" avec des relations entre-elles, ici, il s'agit de documents sous tout type de forme. Les documents ne nécessitent pas d'avoir tous les mêmes champs, ce qui permet une grande flexibilité.

Ce type de base de données est donc idéal lorsque les données prennent différentes formes ou que les données sont très volumineuses.

Utilisation de l'image Docker de MongoDB:
```docker run --name mongodb -d -p 27017:27017 mongo```  
Il est possible d'accéder à la CLI MongoDB avec la commande suivante: ```docker exec -ti *containerID* mongo```