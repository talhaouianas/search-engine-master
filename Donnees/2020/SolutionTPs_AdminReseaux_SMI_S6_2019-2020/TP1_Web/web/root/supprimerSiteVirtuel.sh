#!/bin/bash 

#Tester si le fichier est passé en argument
if [ $# -eq 0 ] 
then
	echo "Usage : $0 nomSiteVirtuel"
else
	#nom du site virtuel
	siteVirtuel=$1

	#désactivation du site virtuel 
	a2dissite $siteVirtuel

	#suppression du répertoire qui contient le contenu du web
	# la commande rm -r permet de supprimer le répertoire ainsi que son contenu (l'option -r, veut dire récursive)
	rm -r  /var/www/$siteVirtuel


	#suppression du fichier de configuration du site virtuel
	rm  /etc/apache2/sites-available/$siteVirtuel

	#rechargement d'apache
	/etc/init.d/apache2 reload
fi
