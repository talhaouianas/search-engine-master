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
		#Lire chaque ligne du Fichier et le l'utiliser comme login
		while read ligne
		do
            ligne=`echo $ligne |sed y/éèàùçï/eeauci/|tr A-Z a-z`
		    #Récupérer le nom de l'étudiant
			nom=`echo $ligne |cut -d ';' -f1`
			#Récupérer le prénom de l'étudiant
			prenom=`echo $ligne |cut -d ';' -f2 | cut -c1`
			login=`echo $prenom.$nom`
			#l'option -r permet de supprimer les données de l'utilsateur
			userdel -r  $login
		done < $1  #Lire le fichier 
	fi
fi
