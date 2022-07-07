#!/bin/bash
#Tester si le fichier est passé en argument
if [ $# -eq 0 ] 
then
	echo "Usage : $0 <Nom_fichier>"
else
	#Tester l'existence du fichier qui contient les noms
	if [ ! -f $1 ]
	then 
		echo "Fichier <$1> inexistant"
	else
		#Numéro de compte (commence à 1100)
		((uid=1100))
		#Lire chaque ligne du Fichier et le l'utiliser comme login
		while read compte
		do
			#l'option -r permet de supprimer les données de l'utilsateur
			userdel -r  $compte
		done < $1  #Lire le fichier 
	fi
fi
