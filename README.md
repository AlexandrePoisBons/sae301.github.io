# Pour les utilisateurs :

# Guide d'utilisation de l'Application ASTRE

Bienvenue sur l'application ASTRE ! Ce guide vous fournira des instructions détaillées sur la configuration des paramètres, la saisie du prévisionnel, la gestion des intervenants, et la génération des états au sein de l'application.

---
## Connexion

- Si vous êtes connecté sur le réseau du serveur qui héberge votre base de données, remplissez les champs de saisie par ces informations : 

- database : le nom de votre base de donnée
- login    : le nom d'utilisateur qui se connectera à la base de donnée
- mdp      : le mot de passe de l'utilisateur à la base de donnée

- Si vous n'êtes pas sur le réseau du serveur hébergeant votre base de données, cochez la case en dessous de "Connexion distante" puis remplissez les champs de saisies par ces informations :

- database : le nom de votre base de donnée
- login    : le nom d'utilisateur qui se connectera à la base de donnée
- mdp      : le mot de passe de l'utilisateur à la base de donnée
- mdp LDAP : le mot de passe de votre session

## Paramétrage

### Configuration des Statuts d'Intervenants

1. Allez sur le tableau "Statut".
2. Configurez chaque catégorie d'intervenant en spécifiant le nom, le service, le nombre d'heures maximum autorisé, et le coefficient TP à l'aide des boutons "ajouter", "modifier". Cliquez sur "Enregistrer" pour chaque modification.

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

L'équivalent TD sera calculé automatiquement pour chaque ligne. Si des heures ponctuelles sont saisies, le nombre de semaines devient invisible et non saisissable. Le total des équivalents TD affectées au module est également calculé automatiquement.

#### 2.2. Module Saé

- Définissez le nombre d'heures-pn Saé par semestre.
- Précisez le nombre d'heures tutorées par semestre.

##### Saisie des Informations

- Nom de l'intervenant.
- Types d'heures.
- Nombre d'heures.
- Zone "commentaires" pour élaborer l'emploi du temps.

##### Calcul Automatique

L'équivalent TD sera calculé automatiquement pour chaque ligne. Le total des équivalents TD affectées au module est également calculé automatiquement.

#### 2.3. Module Stages/Suivi

- Définissez le nombre d'heures REH.
- Précisez le nombre d'heures tutorées.

##### Saisie des Informations

- Nom de l'intervenant.
- Types d'heures.
- Nombre d'heures.
- Zone "commentaires" pour élaborer l'emploi du temps.

##### Calcul Automatique

L'équivalent TD sera calculé automatiquement pour chaque ligne. Le total des équivalents TD affectées au module est également calculé automatiquement.

---

## Gestion des Intervenants

### Ajout d'Intervenants

1. Ajoutez, modifiez ou supprimez des intervenants. Remplissez les informations requises telles que la catégorie, le service, etc.
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