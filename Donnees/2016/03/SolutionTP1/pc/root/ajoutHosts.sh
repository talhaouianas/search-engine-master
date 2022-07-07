#!/bin/bash 

#Tester si le nom du site virtuel est passé en argument
if [ $# -eq 0 ] 
then
	echo "Usage : $0 nomSiteVirtuel"
else
	#nom du site virtuel
	siteVirtuel=$1

	#Ajout du site au fichier hosts (DNS local)
	echo  -e "192.168.1.1 \t $siteVirtuel.ump.ma \t www.$siteVirtuel.ump.ma" >> /etc/hosts
fi
