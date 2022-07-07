#!/bin/bash

#Création du répertoire pour la machine virtuelle
mkdir PC$1_sc

#Création du fichier .startup
echo "/etc/init.d/networking start" >  PC$1_sc.startup

#Ajout de la nouvelle machine virtuelle dans lab.conf
echo "PC$1_sc[0]=ResC" >> lab.conf

#Ajout de la nouvelle machine virtuelle dans lab.dep
echo "PC$1_sc: serv-dhcp" >> lab.dep

#Création de etc/network (l'option -p permet de créer la hierarchie)
mkdir -p PC$1_sc/etc/network

#création du fichier interfaces
echo "auto lo
iface lo inet loopback

auto eth0
iface eth0 inet dhcp" > PC$1_sc/etc/network/interfaces

