DROP TABLE IF EXISTS Intervenant_Heure  CASCADE;
DROP TABLE IF EXISTS Intervenant_Module CASCADE;
DROP TABLE IF EXISTS Heure_Module       CASCADE;
DROP TABLE IF EXISTS Heure              CASCADE;
DROP TABLE IF EXISTS Type_Heure         CASCADE;
DROP TABLE IF EXISTS Intervenant        CASCADE;
DROP TABLE IF EXISTS Statut             CASCADE;
DROP TABLE IF EXISTS Module             CASCADE;



Create Table Module (
    id_module integer primary key,
    type_module VARCHAR(50) NOT NULL,
    semestre VARCHAR(2) NOT NULL,
    libelle VARCHAR(100) NOT NULL,
    libelle_court VARCHAR(50) NOT NULL,
    code VARCHAR(15) NOT NULL,
    nb_etudiants integer,
    nb_gp_td integer NOT NULL,
    nb_gp_tp integer NOT NULL,
    nb_semaines integer NOT NULL,
    nb_heures integer NOT NULL,
    valide boolean DEFAULT FALSE
);


Create table Type_Heure (
    id_type_heure integer primary key,
    nom_type_heure varchar(50),
    coeff float DEFAULT 1
);


Create table Statut (
    nom_statut VARCHAR(10) primary key,
    nb_heures_service int check(nb_heures_service>0),
    nb_heures_maxi int check(nb_heures_maxi>0),
    coeff_tp float NOT NULL DEFAULT 1
);


Create table Intervenant (
    id_intervenant integer primary key,
    nom VARCHAR(25),
    prenom VARCHAR(25),
    nb_equivalent_td float,
    nom_statut VARCHAR(10) REFERENCES Statut(nom_statut) ON DELETE CASCADE
);


Create table Heure (
    id_heure integer primary key,
    id_module integer REFERENCES Module(id_module) ON DELETE CASCADE,
    id_type_heure integer REFERENCES Type_Heure(id_type_heure) ON DELETE CASCADE,
    nb_semaines integer,
    nb_gp_nb_h integer,
    duree integer NOT NULL CHECK(duree >= 0),
    commentaire VARCHAR(250)
);


Create table Intervenant_Module (
    id_intervenant integer REFERENCES Intervenant(id_intervenant) NOT NULL,
    id_module integer  REFERENCES Module(id_module) NOT NULL
);


Create table Intervenant_Heure (
    id_intervenant integer REFERENCES Intervenant(id_intervenant) NOT NULL,
    id_heure integer REFERENCES Heure(id_heure) NOT NULL,
    primary key (id_intervenant, id_heure)
);


Create table Heure_Module (
    id_heure integer REFERENCES Heure(id_heure) NOT NULL,
    id_module integer REFERENCES Module(id_module) NOT NULL,
    primary key (id_heure, id_module)
);
