# You need download:

*TIKA (jar version 2.4.0)

*Solr (Version 8.11.1 or 9.0.0)

*Pour démarrer le serveur Solr, il suffit de suivez les étapes suivantes via l’invite de commande :

C:\>cd solr-8.11.1 

C:\solr-8.11.1>cd bin 

C:\solr-8.11.1\ bin>solr start -p 8983

*Télécharger Docker via cette l'adresse suivante :
https://docs.docker.com/desktop/windows/install/

Télécharger le package de mise à jour du noyau Linux via le lien ci-dessous : https://wslstorestorage.blob.core.windows.net/wslblob/wsl_update_x64.msi

# Introduction

Le but de cette application est de faire découvrir apache Tika qui nous permettant de détecter et d’extraire des métadonnées et du contenu textuel structuré à partir de divers documents (zip, pdf, word….) à l’aide de bibliothèques d’analyseurs existantes.
Et Solr, qui est un serveur de recherche permettant de centraliser les opérations d'indexation et de services de résultats. 
Puis Docker, qui est une plateforme pour le développement qui nous permettent d’embarquer notre application dans un container logiciels afin qu’il nous puisse livrer rapidement notre application qui pourra s’exécuter sur n’importe quel serveur machine (physique ou virtuel) a pour but de faciliter les déploiements d’application.

# Résumé

-Détection et l'extraction des métadonnées et du contenu textuel structuré à partir de divers documents (zip, pdf, word….) à l'aide d'apache Tika.

-Indexation des fichiers générée par Tika dans Solr.

-Réalisation d'un mini-moteur de recherche avec une application web en Spring boot qui implémente l’interface de Moteur de recherche, récupère la requête et l’envoyer au solr, solr fait l’indexation sur la base de donné, enfin l’affichage de résultat se fait dans une interface personnalisé.

