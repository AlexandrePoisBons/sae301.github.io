DROP TABLE IF EXISTS Heure             CASCADE;
DROP TABLE IF EXISTS Type_Heure        CASCADE;
DROP TABLE IF EXISTS Intervenant       CASCADE;
DROP TABLE IF EXISTS Statut            CASCADE;
DROP TABLE IF EXISTS Module            CASCADE;
DROP TABLE IF EXISTS IntervenantModule CASCADE;
DROP TABLE IF EXISTS IntervenantHeure  CASCADE;
DROP TABLE IF EXISTS HeureModule       CASCADE;

Create Table Module (
    id_module serial primary key,
    type_module VARCHAR(50),
    libelle VARCHAR(100),
    libelle_court VARCHAR(50),
    code VARCHAR(15),
    nb_etudiants integer,
    nb_gp_td integer,
    nb_gp_tp integer,
    nb_semaines integer,
    nb_heures integer NOT NULL
);
/*
Un module possèdera :
1. Un identifiant (un nombre entier positif).
2. Un nom (de 50 caractères maximum).
3. Un nombre d’heure associé
*/


Create table Type_Heure(
    id_type_heure serial primary key,
    nom_type_heure varchar(50),
    coeff float
);
/*
Un type d’heure possèdera :
1. Un identifiant (de 15 caractères maximum)
2. Un coefficient (un nombre décimal)
*/


Create table Statut (
    nom_statut VARCHAR(10) primary key,
    nb_heure_mini int check(nbHeureMini>0),
    nb_heure_maxi int check(nbHeureMax>0),
    coeff_tp     float NOT NULL
);
/*
Un statut possèdera :
1. Un identifiant (de 10 caractères maximum)
2. Un nombre d’heures minimum
3. Un nombre d’heures maximum
*/


Create table Intervenant (
    id_intervenant serial primary key,
    nom VARCHAR(25),
    prenom VARCHAR(25),
    nb_equivalent_td int,
    id_statut VARCHAR(10) REFERENCES Statut(id_statut)
);
/*
Un intervenant possèdera :
1. Un identifiant (un nombre entier positif)
2. Un nom (de 25 caractères maximum)
3. Un prenom (de 25 caractères maximum)
4. Un statut associé
*/


Create table Heure (
    id_heure serial primary key,
    id_module integer REFERENCES Module(id_module),
    id_intervenant integer REFERENCES Intervenant(id_intervenant),
    id_type_heure integer REFERENCES Type_Heure(id_type_heure),
    nb_heures integer NOT NULL CHECK(nb_heures > 0)
);

/*
Une heure possèdera :
1. Un identifiant (un nombre entier positif)
2. Un module
3. Un intervenant
4. Un type d’heure
5. Un nombre d’heures (un nombre entier positif)
*/

Create table IntervantModule (
    id_intervenant integer REFERENCES Intervant(id_intervenant),
    id_module integer  REFERENCES Module(id_module),
    primary key (id_intervenant, id_module)
)

Create table IntervenantHeure ( 
    id_intervenant integer REFERENCES Intervenant(id_intervenant),
    id_module integer REFERENCES Module(id_module),
    primary key (id_intervenant, id_module)
)


Create table HeureModule (
    id_module integer REFERENCES Module(id_module),
    id_heure integer REFERENCES Heure(id_heure),
    primary key (id_module, id_heure)
)