# Pour les utilisateurs :

# Guide d'utilisation de l'Application ASTRE

Bienvenue sur l'application ASTRE ! Ce guide vous fournira des instructions détaillées sur la configuration des paramètres, la saisie du prévisionnel, la gestion des intervenants, et la génération des états au sein de l'application.

---
## Prérequis

1. Aller dans le répertoire src/controleur et modifier le fichier infos.txt en y renseignant les différents champs qui sont :

- database : le nom de votre base de donnée
- login    : le nom d'utilisateur qui se connectera à la base de donnée
- mdp      : le mot de passe de l'utilisateur à la base de donnée

## Paramétrage

### 1. Configuration des Types d'Heures

1. Allez sur le tableau "Types d'Heures".
2. Créez les catégories d'heures telles que CM, TD, TP, H tut, REH, H Saé, HP. Affectez les coefficients équivalents TD à chacune de ces catégories. Enregistrez les modifications.

### 2. Configuration des Statuts d'Intervenants

1. Allez sur le tableau "Statut".
2. Configurez chaque catégorie d'intervenant en spécifiant le nom, le service, le nombre d'heures maximum autorisé, et le ratio TP à l'aide des boutons "ajouter", "modifier". Cliquez sur "Enregistrer" pour chaque modification.

---

## Saisie du Prévisionnel

### 1. Liste des Modules

1. Sélectionnez "Liste des Modules".
2. Créez ou modifiez la liste des modules, y compris les ressources, Saé, et stages/suivi. Cliquez sur "Enregistrer" pour chaque modification.

### 2. Saisie des Heures par Module

1. Choisissez dans la liste déroulante le module que vous voulez creer
2. Pour chaque type de module, suivez les instructions ci-dessous :

#### 2.1. Module Ressources

- Définissez le nombre d'heures-pn par semestre et par catégorie de cours (TD, TP…).
- Précisez le nombre d'heures par semaine de TD, TP, CM, et heures tutorées.
- Saisissez le nombre d'heures HP (heures ponctuelles).

##### Saisie des Informations

- Nom de l'intervenant.
- Types d'heures.
- Nombre de groupes.
- Nombre de semaines.
- Zone "commentaires" pour élaborer l'emploi du temps.

##### Calcul Automatique

L'équivalent eqtd sera calculé automatiquement pour chaque ligne. Si des heures ponctuelles sont saisies, le nombre de semaines devient invisible et non saisissable. Le total d'eqtd affectées au module est également calculé automatiquement.

#### 2.2. Module Saé

- Définissez le nombre d'heures-pn Saé par semestre.
- Précisez le nombre d'heures tutorées par semestre.

##### Saisie des Informations

- Nom de l'intervenant.
- Types d'heures.
- Nombre d'heures.
- Zone "commentaires" pour élaborer l'emploi du temps.

##### Calcul Automatique

L'équivalent eqtd sera calculé automatiquement pour chaque ligne. Le total d'eqtd affectées au module est également calculé automatiquement.

#### 2.3. Module Stages/Suivi

- Définissez le nombre d'heures REH.
- Précisez le nombre d'heures tutorées.

##### Saisie des Informations

- Nom de l'intervenant.
- Types d'heures.
- Nombre d'heures.
- Zone "commentaires" pour élaborer l'emploi du temps.

##### Calcul Automatique

L'équivalent eqtd sera calculé automatiquement pour chaque ligne. Le total d'eqtd affectées au module est également calculé automatiquement.

---

## Gestion des Intervenants

### 1. Ajout d'Intervenants

1. Ajoutez, modifiez ou supprimez des intervenants. Remplissez les informations requises telles que la catégorie, le coefficient TP, le service, etc.
2. **Saisie des Heures :** Pour chaque intervenant, saisissez les heures prévisionnelles par semestre, en veillant à respecter les contraintes du programme national.

---

## Génération des États

### 1. Récapitulatif par Intervenant

1. Sélectionnez "Intervenant".
2. Choisissez l'intervenant que vous voulez cliquez sur "générer HTML" ou sur "générer CSV".

### 2. Récapitulatif par Module

1. Sélectionnez "Modules"
2. Cliquez sur "générer HTML" 

---