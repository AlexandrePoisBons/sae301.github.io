DROP TABLE IF EXISTS Intervenant_Heure  CASCADE;
DROP TABLE IF EXISTS Intervenant_Module CASCADE;
DROP TABLE IF EXISTS Heure_Module       CASCADE;
DROP TABLE IF EXISTS Heure              CASCADE;
DROP TABLE IF EXISTS Type_Heure         CASCADE;
DROP TABLE IF EXISTS Intervenant        CASCADE;
DROP TABLE IF EXISTS Statut             CASCADE;
DROP TABLE IF EXISTS Module             CASCADE;


Create Table Module (
    id_module serial primary key,
    type_module VARCHAR(50) NOT NULL,
    libelle VARCHAR(100) NOT NULL,
    libelle_court VARCHAR(50) NOT NULL,
    code VARCHAR(15) NOT NULL,
    nb_etudiants integer,
    nb_gp_td integer NOT NULL,
    nb_gp_tp integer NOT NULL,
    nb_semaines integer NOT NULL,
    nb_heures integer NOT NULL
);


Create table Type_Heure(
    id_type_heure serial primary key,
    nom_type_heure varchar(50),
    coeff float
);


Create table Statut (
    nom_statut VARCHAR(10) primary key,
    nb_heure_mini int check(nb_heure_mini>0),
    nb_heure_maxi int check(nb_heure_maxi>0),
    coeff_tp     float NOT NULL
);


Create table Intervenant (
    id_intervenant serial primary key,
    nom VARCHAR(25),
    prenom VARCHAR(25),
    nb_equivalent_td float,
    nom_statut VARCHAR(10) REFERENCES Statut(nom_statut)
);


Create table Heure (
    id_heure serial primary key,
    id_module integer REFERENCES Module(id_module),
    id_intervenant integer REFERENCES Intervenant(id_intervenant),
    id_type_heure integer REFERENCES Type_Heure(id_type_heure),
    commentaire VARCHAR(250),
    duree integer NOT NULL CHECK(duree > 0)
);

Create table Intervenant_Module (
    id_intervenant integer REFERENCES Intervenant(id_intervenant),
    id_module integer  REFERENCES Module(id_module),
    primary key (id_intervenant, id_module)
);

Create table Intervenant_Heure ( 
    id_intervenant integer REFERENCES Intervenant(id_intervenant),
    id_heure integer REFERENCES Heure(id_heure),
    primary key (id_intervenant, id_heure)
);


Create table Heure_Module (
    id_heure integer REFERENCES Heure(id_heure),
    id_module integer REFERENCES Module(id_module),
    primary key (id_heure, id_module)
);
