
-- Insertions dans la table Module
INSERT INTO Module (id_module, type_module, semestre, libelle, libelle_court, code, nb_etudiants, nb_gp_td, nb_gp_tp, nb_semaines, nb_heures, valide)
VALUES
	( 1, 'Ressource', 'S1', 'Algorithmique et Structures de Données', 'Algo & Structures', 'ASD201', 40, 1, 2, 12, 48, FALSE ),
	( 2, 'SAE',       'S2', 'Base de Données Avancées', 'BDA', 'BDA301',                             30, 0, 2, 10, 40, FALSE ),
	( 3, 'Stage',     'S3', 'Réseaux Informatiques', 'Réseaux', 'RESEAU401',                         60, 2, 1, 14, 56, FALSE ),
	( 4, 'PPP',       'S1', 'Mathématiques Discrètes', 'Math Discrète', 'MATH101',                   40, 1, 1, 12, 36, FALSE ),
	( 5, 'Ressource', 'S2', 'Développement Web Avancé', 'Web Avancé', 'WEB401',                      30, 0, 2, 10, 40, FALSE )
	;


-- Insertions dans la table Type_Heure
INSERT INTO Type_Heure (id_type_heure, nom_type_heure, coeff)
VALUES
	( 1, 'TD',  1    ),
	( 2, 'TP',  0.66 ),
	( 3, 'CM',  1.5  ),
	( 4, 'TUT', 1    ),
	( 5, 'REH', 1    ),
	( 6, 'SAE', 1    ),
	( 7, 'HP',  1    )
	;


-- CM, TD, TP, HP
INSERT INTO TypeHeure_Module (id_module, id_type_heure, pn, nb_semaines, nb_heures)
VALUES
	( 4, 3, 10, 1, 5  ),
	( 4, 1, 20, 2, 10 ),
	( 4, 2, 15, 3, 15 ),
	( 4, 7,  0, 0, 5  ),
	( 2, 6, 1, 2, 3 ),
	( 2, 4, 4, 5, 6 ),
	( 3, 6, 10, 8, 6 ),
	( 3, 4, 4, 2, 1 ),
	( 3, 5, 7, 7, 7 )
	;


-- Insertions dans la table Statut
INSERT INTO Statut (nom_statut, nb_heures_service, nb_heures_maxi, coeff_tp)
VALUES
	( 'Enseignant', 180, 350, 1.0  ),
	( 'Chercheur',  120, 250, 1.0  ),
	( 'Vacataire',  100, 200, 0.66 )
	;


-- Insertions dans la table Intervenant
INSERT INTO Intervenant (id_intervenant, nom, prenom, nb_equivalent_td, nom_statut)
VALUES
	( 1, 'Chabrier', 'Pierre',  277.5, 'Enseignant' ),
	( 2, 'Levy',     'Sylvain', 249.0, 'Chercheur'  ),
	( 3, 'Vatanen',  'Ari',     102.0, 'Vacataire'  )
	;


-- Insertions dans la table Heure
INSERT INTO Heure (id_heure, id_module, id_type_heure, nb_semaines, nb_gp_nb_h, duree, commentaire)
VALUES
	( 1,  1, 1, 12, 48, 10, 'TD Ressource: Algo 10h'   ),
	( 2,  1, 2, 12, 48, 20, 'TP Ressource: Algo 20h'   ),
	( 3,  1, 1, 12, 48, 15, 'TD Ressource: Algo 15h'   ),
	( 4,  1, 3, 12, 48, 16, 'CM Ressource: Algo 16h'   ),
	( 5,  2, 1, 10, 40, 35, 'TD SAE: BADO Avancée 35h' ),
	( 6,  2, 3, 10, 40, 4,  'CM SAE: BADO Avancée 4h'  ),
	( 7,  3, 2, 14, 56, 7,  'TP Stage: Réseaux 7h'     ),
	( 8,  3, 2, 14, 56, 13, 'TP Stage: Réseaux 13h'    ),
	( 9,  3, 3, 14, 56, 8,  'CM Stage: Réseaux 8h'     ),
	( 10, 4, 1, 12, 36, 2,  'TD PPP: Maths 2h'         )
	;


-- Insertions dans la table Intervenant_Module
INSERT INTO Intervenant_Module (id_intervenant, id_module)
VALUES
	( 1, 1 ),
	( 2, 1 ),
	( 1, 1 ),
	( 1, 1 ),
	( 2, 2 ),
	( 1, 2 ),
	( 2, 3 ),
	( 3, 3 ),
	( 3, 3 ),
	( 1, 4 )
	;


-- Insertions dans la table Intervenant_Heure
INSERT INTO Intervenant_Heure (id_intervenant, id_heure)
VALUES
	( 1, 1  ),
	( 2, 2  ),
	( 1, 3  ),
	( 1, 4  ),
	( 2, 5  ),
	( 1, 6  ),
	( 2, 7  ),
	( 3, 8  ),
	( 3, 9  ),
	( 1, 10 )
	;


-- Insertions dans la table Heure_Module
INSERT INTO Heure_Module (id_heure, id_module)
VALUES
	( 1 , 1 ),
	( 2 , 1 ),
	( 3 , 1 ),
	( 4 , 1 ),
	( 5 , 2 ),
	( 6 , 2 ),
	( 7 , 3 ),
	( 8 , 3 ),
	( 9 , 3 ),
	( 10, 4 )
	;

