#!/bin/bash 

#Tester si le fichier est passé en argument
if [ $# -eq 0 ] 
then
	echo "Usage : $0 nomSiteVirtuel"
else
	#nom du site virtuel
	siteVirtuel=$1


	#création du répertoire pour le contenu du web
	mkdir  /var/www/$siteVirtuel

	#Ajout du site au fichier hosts (DNS local)
	#echo "192.168.1.1	$siteVirtuel	www.$siteVirtuel" >> /etc/hosts

	#création du fichier index.html
	echo "<html>

	<body>
	<h1>
	Site de $siteVirtuel <br>
	Université Mohammed Premier
	<br>Faculté des Sciences
	<br>Oujda</h1>
	</body>

	</html>" > /var/www/$siteVirtuel/index.html


	#création du fichier de configuration du site virtuel
	echo "<VirtualHost *:80>
	    DocumentRoot /var/www/$siteVirtuel
	    ServerName  $siteVirtuel.ump.ma
	    ServerAlias www.$siteVirtuel.ump.ma
	</VirtualHost>" > /etc/apache2/sites-available/$siteVirtuel

	#activation du site virtuel 
	a2ensite $siteVirtuel

	#rechargement d'apache
	/etc/init.d/apache2 reload
fi
