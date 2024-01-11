
-- Insertions dans la table Module
INSERT INTO Module (id_module, type_module, semestre, libelle, libelle_court, code, nb_etudiants, nb_gp_td, nb_gp_tp, nb_semaines, nb_heures, valide)
VALUES
	(1, 'Ressource', 'S1', 'Algorithmique et Structures de Données', 'Algo & Structures', 'ASD201', 40, 1, 2, 12, 48, FALSE),
  	(2, 'SAE', 'S2', 'Base de Données Avancées', 'BDA', 'BDA301', 30, 0, 2, 10, 40, FALSE),
  	(3, 'Stage', 'S3', 'Réseaux Informatiques', 'Réseaux', 'RESEAU401', 60, 2, 1, 14, 56, FALSE),
  	(4, 'PPP', 'S1', 'Mathématiques Discrètes', 'Math Discrète', 'MATH101', 40, 1, 1, 12, 36, FALSE),
  	(5, 'Ressource', 'S2', 'Développement Web Avancé', 'Web Avancé', 'WEB401', 30, 0, 2, 10, 40, FALSE),
  	(6, 'Stage', 'S3', 'Systèmes Exploitation', 'Sys OS', 'SYS401', 50, 1, 2, 14, 56, FALSE),
  	(7, 'Stage', 'S5', 'Ingénierie Logicielle', 'Ingé Log', 'ING401', 35, 0, 2, 10, 40, FALSE),
  	(8, 'HP', 'S1', 'Réseaux Avancés', 'Réseaux Av', 'RNET501', 40, 1, 1, 12, 36, FALSE),
  	(9, 'Stage', 'S2', 'Sécurité Informatique', 'Secu Info', 'SEC401', 25, 0, 2, 10, 40, FALSE),
  	(10, 'SAE', 'S3', 'Web Services', 'Web Serv', 'WEB601', 55, 2, 1, 14, 56, FALSE),
  	(11, 'HP', 'S5', 'Blockchain', 'Block', 'BCN801', 20, 0, 1, 12, 36, FALSE),
  	(12, 'SAE', 'S2', 'Cloud Computing', 'Cloud Comp', 'CLOUD301', 40, 1, 1, 12, 36, FALSE),
  	(13, 'HP', 'S3', 'Big Data', 'Big Data', 'BIGDATA401', 30, 0, 2, 14, 40, FALSE),
  	(14, 'Stage', 'S5', 'Génie Logiciel', 'Génie Log', 'GL701', 35, 0, 2, 14, 40, FALSE),
  	(15, 'SAE', 'S1', 'Data Science', 'Data Sci', 'DATASCI501', 60, 2, 1, 14, 56, FALSE);


-- Insertions dans la table Type_Heure
INSERT INTO Type_Heure (id_type_heure, nom_type_heure, coeff)
VALUES
	( 1, 'TD',  1   ),
	( 2, 'TP',  1   ),
	( 3, 'CM',  1.5 ),
	( 4, 'TUT', 1   ),
	( 5, 'REH', 1   ),
	( 6, 'SAE', 1   ),
	( 7, 'HP',  1   );


-- CM, TD, TP, HP
INSERT INTO TypeHeure_Module (id_module, id_type_heure, pn, nb_semaines, nb_heures)
VALUES
	-- Module 1
(1, 1, 10, 12, 120),
(1, 2, 20, 12, 240),
(1, 3, 15, 12, 180),
(1, 4, 5, 12, 60),
(1, 5, 5, 12, 60),
(1, 6, 5, 12, 60),
(1, 7, 5, 12, 60),
(1, 1, 8, 12, 80),
(1, 2, 15, 12, 150),
(1, 3, 12, 12, 144),

-- Module 2
(2, 1, 10, 10, 100),
(2, 2, 15, 10, 150),
(2, 3, 8, 10, 120),
(2, 4, 6, 10, 72),
(2, 5, 4, 10, 48),
(2, 6, 6, 10, 72),
(2, 7, 4, 10, 48),
(2, 1, 12, 10, 120),
(2, 2, 18, 10, 180),
(2, 3, 10, 10, 150),

-- Module 3
(3, 1, 15, 14, 210),
(3, 2, 10, 14, 140),
(3, 3, 8, 14, 120),
(3, 4, 7, 14, 112),
(3, 5, 6, 14, 96),
(3, 6, 5, 14, 70),
(3, 7, 7, 14, 98),
(3, 1, 18, 14, 180),
(3, 2, 12, 14, 144),
(3, 3, 15, 14, 225),

-- Module 4
(4, 1, 5, 12, 60),
(4, 2, 8, 12, 96),
(4, 3, 12, 12, 144),
(4, 4, 3, 12, 36),
(4, 5, 4, 12, 48),
(4, 6, 2, 12, 24),
(4, 7, 3, 12, 36),
(4, 1, 10, 12, 100),
(4, 2, 15, 12, 150),
(4, 3, 8, 12, 96),

-- Module 5
(5, 1, 12, 10, 120),
(5, 2, 18, 10, 180),
(5, 3, 10, 10, 150),
(5, 4, 6, 10, 72),
(5, 5, 8, 10, 80),
(5, 6, 6, 10, 72),
(5, 7, 3, 10, 36),
(5, 1, 15, 10, 150),
(5, 2, 20, 10, 200),
(5, 3, 12, 10, 144),

-- Module 6
(6, 1, 8, 14, 112),
(6, 2, 12, 14, 144),
(6, 3, 15, 14, 225),
(6, 4, 6, 14, 72),
(6, 5, 7, 14, 98),
(6, 6, 10, 14, 140),
(6, 7, 9, 14, 126),
(6, 1, 10, 14, 100),
(6, 2, 15, 14, 150),
(6, 3, 8, 14, 96),

-- Module 7
(7, 1, 20, 12, 240),
(7, 2, 15, 12, 180),
(7, 3, 10, 12, 150),
(7, 4, 5, 12, 60),
(7, 5, 10, 12, 100),
(7, 6, 8, 12, 96),
(7, 7, 6, 12, 72),
(7, 1, 12, 12, 120),
(7, 2, 18, 12, 180),
(7, 3, 15, 12, 180),

-- Module 8
(8, 1, 15, 10, 150),
(8, 2, 20, 10, 200),
(8, 3, 12, 10, 144),
(8, 4, 8, 10, 96),
(8, 5, 5, 10, 60),
(8, 6, 10, 10, 100),
(8, 7, 7, 10, 84),
(8, 1, 18, 10, 180),
(8, 2, 12, 10, 120),
(8, 3, 15, 10, 150),

-- Module 9
(9, 1, 10, 12, 100),
(9, 2, 15, 12, 150),
(9, 3, 8, 12, 96),
(9, 4, 6, 12, 72),
(9, 5, 7, 12, 84),
(9, 6, 5, 12, 60),
(9, 7, 7, 12, 84),
(9, 1, 20, 12, 200),
(9, 2, 10, 12, 120),
(9, 3, 12, 12, 144),

-- Module 10
(10, 1, 15, 10, 150),
(10, 2, 18, 10, 180),
(10, 3, 10, 10, 100),
(10, 4, 8, 10, 96),
(10, 5, 6, 10, 72),
(10, 6, 6, 10, 72),
(10, 7, 3, 10, 36),
(10, 1, 12, 10, 120),
(10, 2, 15, 10, 150),
(10, 3, 8, 10, 96),

-- Module 11
(11, 1, 12, 14, 144),
(11, 2, 18, 14, 180),
(11, 3, 15, 14, 225),
(11, 4, 10, 14, 140),
(11, 5, 8, 14, 112),
(11, 6, 12, 14, 144),
(11, 7, 10, 14, 100),
(11, 1, 6, 14, 72),
(11, 2, 8, 14, 96),
(11, 3, 10, 14, 120),

-- Module 12
(12, 1, 8, 10, 80),
(12, 2, 15, 10, 150),
(12, 3, 10, 10, 100),
(12, 4, 6, 10, 72),
(12, 5, 8, 10, 80),
(12, 6, 10, 10, 100),
(12, 7, 6, 10, 72),
(12, 1, 12, 10, 120),
(12, 2, 18, 10, 180),
(12, 3, 15, 10, 150),

-- Module 13
(13, 1, 15, 12, 180),
(13, 2, 10, 12, 120),
(13, 3, 8, 12, 96),
(13, 4, 7, 12, 84),
(13, 5, 6, 12, 72),
(13, 6, 5, 12, 60),
(13, 7, 7, 12, 84),
(13, 1, 18, 12, 216),
(13, 2, 12, 12, 144),
(13, 3, 15, 12, 180),

-- Module 14
(14, 1, 5, 12, 60),
(14, 2, 8, 12, 96),
(14, 3, 12, 12, 144),
(14, 4, 3, 12, 36),
(14, 5, 4, 12, 48),
(14, 6, 2, 12, 24),
(14, 7, 3, 12, 36),
(14, 1, 10, 12, 100),
(14, 2, 15, 12, 150),
(14, 3, 8, 12, 96),

-- Module 15
(15, 1, 20, 12, 240),
(15, 2, 15, 12, 180),
(15, 3, 10, 12, 150),
(15, 4, 5, 12, 60),
(15, 5, 10, 12, 100),
(15, 6, 8, 12, 96),
(15, 7, 6, 12, 72),
(15, 1, 12, 12, 120),
(15, 2, 18, 12, 180),
(15, 3, 15, 12, 180);


-- Insertions dans la table Statut
INSERT INTO Statut (nom_statut, nb_heures_service, nb_heures_maxi, coeff_tp)
VALUES
	( 'Enseignant', 180, 350, 1.0  ),
	( 'Chercheur',  120, 250, 1.0  ),
	( 'Vacataire',  100, 200, 0.66 );


-- Insertions dans la table Intervenant
INSERT INTO Intervenant (id_intervenant, nom, prenom, nb_equivalent_td, nom_statut)
VALUES
	(1, 'Jean', 'Dupont', 150, 'Vacataire'),
  	(2, 'Marie', 'Martin', 280, 'Enseignant'),
  	(3, 'Pierre', 'Dubois', 200, 'Chercheur'),
	(4, 'Sacha', 'Touille', 100, 'Enseignant'),
  	(5, 'Nicolas', 'Moreau', 330, 'Chercheur'),
  	(6, 'Sophie', 'Roux', 300, 'Enseignant');

-- Insertions dans la table Heure
INSERT INTO Heure (id_heure, id_module, id_type_heure, nb_semaines, nb_gp_nb_h, duree, commentaire)
VALUES
	-- Heure
(1, 1, 1, 12, 48, 10, 'TD Ressource: Algo 10h'),
(2, 1, 2, 12, 48, 20, 'TP Ressource: Algo 20h'),
(3, 1, 1, 12, 48, 15, 'TD Ressource: Algo 15h'),
(4, 1, 3, 12, 48, 16, 'CM Ressource: Algo 16h'),
(5, 2, 6, 10, 40, 35, 'SAE SAE: BADO Avancée 35h'),
(6, 2, 4, 10, 40, 4, 'TUT SAE: BADO Avancée 4h'),
(7, 2, 6, 10, 40, 25, 'SAE SAE: BADO Avancée 25h'),
(8, 3, 2, 14, 56, 7, 'TP Stage: Réseaux 7h'),
(9, 3, 2, 14, 56, 13, 'TP Stage: Réseaux 13h'),
(10, 3, 3, 14, 56, 8, 'CM Stage: Réseaux 8h'),
(11, 3, 1, 14, 56, 10, 'TD Stage: Réseaux 10h'),
(12, 4, 1, 12, 36, 2, 'TD PPP: Maths 2h'),
(13, 4, 2, 12, 36, 5, 'TP PPP: Maths 5h'),
(14, 4, 3, 12, 36, 8, 'CM PPP: Maths 8h'),
(15, 4, 7, 12, 36, 3, 'HP PPP: Maths 3h'),
(16, 5, 2, 10, 40, 18, 'TP Ressource: Web Avancé 18h'),
(17, 5, 1, 10, 40, 12, 'TD Ressource: Web Avancé 12h'),
(18, 5, 3, 10, 40, 15, 'CM Ressource: Web Avancé 15h'),
(19, 5, 7, 10, 40, 5, 'HP Ressource: Web Avancé 5h'),
(20, 6, 3, 14, 56, 10, 'CM Stage: Sys OS 10h'),
(21, 6, 2, 14, 56, 14, 'TP Stage: Sys OS 14h'),
(22, 6, 7, 14, 56, 12, 'HP Stage: Sys OS 12h'),
(23, 7, 1, 12, 48, 20, 'TD REH: IA 20h'),
(24, 7, 3, 12, 48, 8, 'CM REH: IA 8h'),
(25, 7, 7, 12, 48, 15, 'HP REH: IA 15h'),
(26, 7, 4, 12, 48, 4, 'TUT REH: IA 4h'),
(27, 8, 2, 10, 40, 10, 'TP Stage: Ingé Log 10h'),
(28, 8, 7, 10, 40, 12, 'HP Stage: Ingé Log 12h'),
(29, 8, 1, 10, 40, 20, 'TD Stage: Ingé Log 20h'),
(30, 8, 4, 10, 40, 3, 'TUT Stage: Ingé Log 3h'),
(31, 9, 3, 12, 36, 15, 'CM Stage: Réseaux Av 15h'),
(32, 9, 7, 12, 36, 12, 'HP Stage: Réseaux Av 12h'),
(33, 9, 1, 12, 36, 10, 'TD Stage: Réseaux Av 10h'),
(34, 9, 4, 12, 36, 5, 'TUT Stage: Réseaux Av 5h'),
(35, 10, 1, 10, 40, 18, 'TD Stage: Secu Info 18h'),
(36, 10, 2, 10, 40, 15, 'TP Stage: Secu Info 15h'),
(37, 10, 7, 10, 40, 10, 'HP Stage: Secu Info 10h'),
(38, 10, 4, 10, 40, 3, 'TUT Stage: Secu Info 3h'),
(39, 11, 6, 14, 56, 12, 'SAE SAE: Web Services 12h'),
(40, 11, 6, 14, 56, 18, 'SAE SAE: Web Services 18h'),
(41, 11, 4, 14, 56, 15, 'TUT SAE: Web Services 15h'),
(42, 11, 6, 14, 56, 5, 'SAE SAE: Web Services 5h'),
(43, 12, 3, 10, 40, 22, 'CM REH: Machine Learning 22h'),
(44, 12, 2, 10, 40, 12, 'TP REH: Machine Learning 12h'),
(45, 12, 1, 10, 40, 18, 'TD REH: Machine Learning 18h'),
(46, 12, 5, 10, 40, 7, 'REH REH: Machine Learning 7h'),
(47, 13, 1, 12, 36, 15, 'TD HP: Blockchain 15h'),
(48, 13, 2, 12, 36, 18, 'TP HP: Blockchain 18h'),
(49, 13, 3, 12, 36, 20, 'CM HP: Blockchain 20h'),
(50, 13, 5, 12, 36, 8, 'REH HP: Blockchain 8h'),
(51, 14, 3, 12, 48, 20, 'CM REH: Dev Mobile 20h'),
(52, 14, 1, 12, 48, 12, 'TD REH: Dev Mobile 12h'),
(53, 14, 2, 12, 48, 15, 'TP REH: Dev Mobile 15h'),
(54, 14, 5, 12, 48, 6, 'REH REH: Dev Mobile 6h'),
(55, 15, 2, 12, 36, 10, 'TP SAE: Cloud Computing 10h'),
(56, 15, 6, 12, 36, 16, 'CM SAE: Cloud Computing 16h'),
(57, 15, 4, 12, 36, 6, 'TUT SAE: Cloud Computing 6h'),
(58, 15, 6, 12, 36, 5, 'SAE SAE: Cloud Computing 5h');



-- Insertions dans la table Intervenant_Module
INSERT INTO Intervenant_Module (id_intervenant, id_module)
VALUES
	(1, 1),
	(2, 1),
	(3, 1),
	(1, 2),
	(2, 2),
	(3, 2),
	(1, 3),
	(2, 3),
	(3, 3),
	(1, 4),
	(2, 4),
	(3, 4),
	(4, 5),
	(5, 5),
	(6, 5),
	(4, 6),
	(5, 6),
	(6, 6),
	(4, 7),
	(5, 7),
	(6, 7),
	(4, 8),
	(5, 8),
	(6, 8),
	(4, 9),
	(5, 9),
	(6, 9),
	(4, 10),
	(5, 10),
	(6, 10),
	(1, 11),
	(2, 11),
	(3, 11),
	(1, 12),
	(2, 12),
	(3, 12),
	(1, 13),
	(2, 13),
	(3, 13),
	(1, 14),
	(2, 14),
	(3, 14),
	(1, 15),
	(2, 15),
	(3, 15);



-- Insertions dans la table Intervenant_Heure
-- Insertions dans la table Intervenant_Heure
INSERT INTO Intervenant_Heure (id_intervenant, id_heure)
VALUES
	(1, 1),
	(2, 2),
	(3, 3),
	(4, 4),
	(5, 5),
	(6, 6),
	(1, 7),
	(2, 8),
	(3, 9),
	(4, 10),
	(5, 11),
	(6, 12),
	(1, 13),
	(2, 14),
	(3, 15),
	(4, 16),
	(5, 17),
	(6, 18),
	(1, 19),
	(2, 20),
	(3, 21),
	(4, 22),
	(5, 23),
	(6, 24),
	(1, 25),
	(2, 26),
	(3, 27),
	(4, 28),
	(5, 29),
	(6, 30),
	(1, 31),
	(2, 32),
	(3, 33),
	(4, 34),
	(5, 35),
	(6, 36),
	(1, 37),
	(2, 38),
	(3, 39),
	(4, 40),
	(5, 41),
	(6, 42),
	(1, 43),
	(2, 44),
	(3, 45),
	(4, 46),
	(5, 47),
	(6, 48),
	(1, 49),
	(2, 50),
	(3, 51),
	(4, 52),
	(5, 53),
	(6, 54),
	(1, 55),
	(2, 56),
	(3, 57),
	(4, 58);



-- Insertions dans la table Heure_Module
-- Insertions dans la table Heure_Module
INSERT INTO Heure_Module (id_heure, id_module)
VALUES
	(1, 1),
	(2, 1),
	(3, 1),
	(4, 1),
	(5, 2),
	(6, 2),
	(7, 3),
	(8, 3),
	(9, 3),
	(10, 4),
	(11, 5),
	(12, 5),
	(13, 6),
	(14, 6),
	(15, 6),
	(16, 7),
	(17, 7),
	(18, 7),
	(19, 8),
	(20, 8),
	(21, 8),
	(22, 9),
	(23, 9),
	(24, 9),
	(25, 10),
	(26, 10),
	(27, 10),
	(28, 11),
	(29, 11),
	(30, 11),
	(31, 12),
	(32, 12),
	(33, 12),
	(34, 13),
	(35, 13),
	(36, 13),
	(37, 14),
	(38, 14),
	(39, 14),
	(40, 15),
	(41, 15),
	(42, 15);


