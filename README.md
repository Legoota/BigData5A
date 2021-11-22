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
 * ```docker run --name nomCustom -d -p 8989:880 nginx```: permet de mettre un nom spécifique _nomCustom_
