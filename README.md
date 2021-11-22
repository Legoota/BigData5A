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
 * ```docker volume```: permet de réaliser des opérations sur les volumes 

Exemple pour démarrer une image MySQL:
```docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -d mysql```: Nom de l'image: _mysql_, mot de passe root: _root_  
```docker exec -ti mysql mysql --password```

Exemple pour démarrer une image Ubuntu avec Java:
```docker run -it ubuntu bash```: Obtention image ubuntu  
```apt-get update```: Mise à jour APT  
```apt-get install java```: Installation Java  
```apt-get install vim```: Installation de VIM

Pour faire du mapping:
```docker run -dti -p 8080:80 -v /Users/user/doc/dockerexample/page/:/usr/share/nginx/html/ --name servernginx nginx```
