-- Insertion des exercices
INSERT INTO Exercice (dateDebut, dateFin) VALUES
('2024-01-01', '2024-12-31'),
('2023-01-01', '2023-12-31'),
('2025-01-01', '2025-12-31');


-- Insertion des categories (ROUGE)
INSERT INTO Categorie (nom) VALUES
('Bilan Actif'),
('Bilan Capitaux Propres et Passifs'),
('Compte de Resultat');

-- Insertion des sous-categories (VERT)
INSERT INTO SousCategorie (nom, id_categorie) VALUES
-- Pour "Bilan Actif"
('Actifs Non Courants', 1),
('Actifs Courants', 1),
-- Pour "Bilan Capitaux Propres et Passifs"
('Capitaux Propres', 2),
('Passifs Non Courants', 2),
('Passifs Courants', 2),
-- Pour "Compte de Resultat"
('Production de l Exercice', 3),
('Consommation de l Exercice', 3),  
('Valeur Ajoutee d Exploitation', 3),
('Resultat D Exploitation', 3),
('Excedent Brut d Exploitation', 3),
('Resultat Operationnel', 3),
('Resultat Financier', 3),
('Resultat Avant Impots', 3),
('Resultat Net des Activites Ordinaires', 3),
('Resultat Extraordinaire', 3),
('Resultat Net de l Exercice', 3),
('Total des produits des activites ordinaires', 3),
('Total des charges des activites ordinaires', 3);


-- Insertion des postes (BLEU)
INSERT INTO Poste (nom, id_sous_categorie) VALUES
('Ecart d acquisition (Goodwill)', 1),
('Immobilisations incorporelles', 1),
('Immobilisations corporelles', 1),
('Immobilisations en cours', 1),
('Immobilisations financieres', 1),
('Titres mis en equivalence', 1),
('Autres participations et creances rattachees', 1),
('Autres titres immobilises', 1),
('Prets et autres immobilisations financieres', 1),
('Stocks et en-cours', 2),
('Creances et emplois assimiles', 2),
('Clients et autres debiteurs', 2),
('Impots', 2),
('Autres creances et actifs assimiles', 2),
('Tresorerie et equivalents de tresorerie', 2),
('Placements et autres equivalences de tresorerie', 2),
('Tresorerie (fonds en caisse et depots a vue)', 2),
-- Pour "Capitaux Propres"
('Capital emis', 3),
('Primes et reserves consolidees', 3),
('Ecarts d evaluation', 3),
('Ecart d equivalence', 3),
('Resultat net part du groupe', 3),
('Autres capitaux propres - report a nouveau', 3),
-- Pour "Passifs Non Courants"
('Produits differes : subventions d investissement', 4),
('Impots differes', 4),
('Emprunts et dettes financieres', 4),
('Provisions et produits constates d avance', 4),
-- Pour "Passifs Courants"
('Dettes a court terme partie a court terme de dettes a long terme', 5),
('Fournisseurs et comptes rattaches', 5),
('Provisions et produits constates d avance passifs courants', 5),
('Autres dettes', 5),
('Comptes de tresorerie (decouverts bancaires)', 5),
-- Pour "Compte de Resultat"
('Chiffre d affaires', 6),
('Production stockee', 6),
('Production immobilisee', 6),
('Achats consommes', 7),
('Services exterieurs et autres consommations', 7),
('Autres services exterieurs', 7),
('Charges de personnel', 10),
('Impots, taxes et versements assimiles', 10),
('Autres produits operationnels', 11),
('Autres charges operationnelles', 11),
('Dotations aux amortissements, provisions et pertes de valeur', 11),
('Reprises sur provisions et pertes de valeur', 11),
('Produits financiers', 12),
('Charges financieres', 12),
('Impots exigibles sur resultats', 14),
('Impots differes (variations)', 14),
('Elements extraordinaires (produits)', 15),
('Elements extraordinaires (charges)', 15);



-- Insertion des comptes (NOIR) pour Actifs Non Courants
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Actifs Non Courants
-- Ecart d'acquisition (Goodwill)
('Fonds commercial', '207', 1);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Immobilisations incorporelles
('Frais de developpement immobilisables', '203', 2),
('Logiciels informatiques et assimiles', '204', 2),
('Concessions, brevets, licences, marques, procedes', '205', 2),
('Autres immobilisations incorporelles', '208', 2);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Immobilisations corporelles
('Terrains', '211', 3),
('Agencements et amenagements de terrain', '212', 3),
('Constructions', '213', 3),
('Installations techniques, materiel, et outillage industriels', '215', 3),
('Autres immobilisations corporelles', '218', 3);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Immobilisations en cours
('Immobilisations corporelles en cours', '232', 4),
('Immobilisations incorporelles en cours', '237', 4),
('Avances et acomptes verses sur commandes d immobilisations', '238', 4);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Immobilisations financieres
('Titres de participation', '261', 5),
('Creances rattachees a des participations', '267', 5),
('Depots et cautionnements verses', '275', 5),
('Autres creances immobilisees', '276', 5);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Titres mis en equivalence
('Titres de participation evalues par equivalence', '265', 6);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Autres participations et creances rattachees
('Autres formes de participations', '262', 7);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Autres titres immobilises
('Titres immobilises autres que les titres immobilises de l activite de portefeuille', '271', 8);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Prets et autres immobilisations financieres
('Prets', '274', 9);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Stocks et en-cours
('Matieres premieres et fournitures', '31', 10),
('En-cours de production de biens', '33', 10),
('En-cours de production de services', '34', 10),
('Stocks de produits finis', '35', 10),
('Stocks de marchandises', '37', 10);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Creances et emplois assimiles
('Clients', '411', 11),
('Clients, effets a recevoir', '413', 11),
('Clients douteux', '416', 11),
('Clients - produits non encore factures', '418', 11);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Clients et autres debiteurs
('Clients', '411', 12),
('Creances sur cessions d immobilisations', '462', 12),
('Autres comptes debiteurs', '467', 12);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Impots
('Etat, subventions a recevoir', '441', 13),
('Etat, impots sur les resultats', '444', 13),
('Etat, taxes sur le chiffre d affaires', '445', 13);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Autres creances et actifs assimiles
('Charges constatees d avance', '481', 14),
('Produits constates d avance', '487', 14);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Tresorerie et equivalents de tresorerie
('Banques, comptes courants', '512', 15),
('Autres organismes financiers', '517', 15),
('Concours bancaires courants', '519', 15);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Placements et autres equivalents de tresorerie
('Autres valeurs mobilieres de placement et creances assimilees', '508', 16);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Tresorerie (fonds en caisse et depots a vue)
('Caisse', '53', 17);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Capitaux Propres
-- Capital emis
('Capital', '101', 18);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Primes et reserves consolidees
('Primes liees au capital social', '104', 19),
('Reserves', '106', 19);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Ecarts evaluation
('Ecart d evaluation', '105', 20);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Ecart equivalence
('Ecart d equivalence', '107', 21);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Resultat net - part du groupe
('Resultat de l exercice (benefice)', '120', 22),
('Resultat de l exercice (perte)', '129', 22);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Autres capitaux propres - report a nouveau
('Report a nouveau solde crediteur', '110', 23),
('Report a nouveau solde debiteur', '119', 23);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Passifs Non Courants
-- Produits differes : subventions d'investissement
('Subventions equipement', '131', 24),
('Autres subventions investissement', '132', 24);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Impots differes
('Impots differes actif', '133', 25),
('Impots differes passif', '134', 25);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Emprunts et dettes financieres
('Emprunts obligataires convertibles', '161', 26),
('Autres emprunts obligataires', '163', 26),
('Emprunts aupres des etablissements de credit', '164', 26),
('Dettes sur contrats de location-financement', '167', 26);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Provisions et produits constates avance
('Provisions pour pensions et obligations similaires', '153', 27),
('Provisions pour impots', '155', 27),
('Provisions pour renouvellement des immobilisations', '156', 27),
('Autres provisions pour charges - passifs non courants', '158', 27);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Passifs Courants
-- Dettes a court terme - partie a court terme de dettes a long terme
('Fournisseurs de biens et services', '401', 28),
('Autres emprunts et dettes assimilees', '168', 28);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Fournisseurs et comptes rattaches
('Fournisseurs de biens et services', '401', 29),
('Fournisseurs effets a payer', '403', 29),
('Fournisseurs d immobilisations', '404', 29);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Provisions et produits constates d avance - passifs courants
('Provisions - passifs courants', '481', 30),
('Produits constates d avance', '487', 30);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Autres dettes
('Associes - comptes courants', '455', 31),
('Associes, dividendes a payer', '457', 31),
('Autres comptes crediteurs ou debiteurs', '467', 31);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Comptes de tresorerie (decouverts bancaires)
('Concours bancaires courants', '519', 32);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Compte de Resultat
-- Chiffre d'affaires
('Ventes de produits finis', '701', 33),
('Ventes de produits intermediaires', '702', 33),
('Ventes de produits residuels', '703', 33),
('Ventes de travaux', '704', 33),
('Ventes d etudes', '705', 33),
('Ventes de prestations de service', '706', 33),
('Ventes de marchandises', '707', 33),
('Produits des activites annexes', '708', 33),
('Rabais, remises et ristournes accordes', '709', 33);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Production stockee
('Variation de stocks d en-cours', '713', 34),
('Variation de stocks de produits', '714', 34);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Production immobilisee
('Production immobilisee d ectif incorporel', '721', 35),
('Production immobilisee d ectif corporel', '722', 35);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Insertion des comptes pour Achats consommes
('Matieres premieres', '601', 36),
('Autres approvisionnements', '602', 36),
('Variations des stocks', '603', 36),
('Achats d etudes et de prestations de service', '604', 36),
('Achats de materiels, equipements et travaux', '605', 36),
('Achats non stockes de matieres et fournitures', '606', 36),
('Achats de marchandises', '607', 36),
('Frais accessoires d achat', '608', 36),
('Rabais, remises, ristournes obtenus sur achats', '609', 36);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Services exterieurs et autres consommations
('Sous-traitance generale', '611', 37),
('Locations', '613', 37),
('Charges locatives et charges de copropriete', '614', 37),
('Entretien, reparations et maintenance', '615', 37),
('Primes d assurances', '616', 37),
('etudes et recherches', '617', 37),
('Documentation et divers', '618', 37),
('Rabais, remises, ristournes obtenus sur services exterieurs', '619', 37);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Autres services exterieurs
('Personnel exterieur a l entreprise', '621', 38),
('Remunerations d intermediaires et honoraires', '622', 38),
('Publicite, publication, relations publiques', '623', 38),
('Transports de biens et transport collectif du personnel', '624', 38),
('Deplacements, missions et receptions', '625', 38),
('Frais postaux et de telecommunications', '626', 38),
('Services bancaires et assimiles', '627', 38),
('Cotisations et divers', '628', 38),
('Rabais, remises, ristournes obtenus sur autres services exterieurs', '629', 38);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
('Remunerations du personnel', '641', 39),
('Remunerations des dirigeants', '644', 39),
('Cotisations aux organismes sociaux', '645', 39),
('Charges sociales sur remunerations des dirigeants', '646', 39),
('Autres charges sociales', '647', 39),
('Autres charges de personnel', '648', 39);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
-- Insertion des comptes pour Impots, taxes et versements assimiles
('Impots, taxes et versements assimiles sur remunerations', '631', 40),
('Autres impots et taxes', '635', 40);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
('Redevances pour concessions, brevets, licences, logiciels et valeurs similaires', '751', 41),
('Plus-values sur cessions d actifs non courants', '752', 41),
('Quotes-parts de subventions d investissement virees au resultat', '754', 41),
('Quote-part de resultat sur operations faites en commun', '755', 41),
('Liberalites perçues, rentrees sur creances amorties', '756', 41),
('Produits exceptionnels sur operations de gestion', '757', 41),
('Autres produits de gestion courante', '758', 41);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
('Redevances pour concessions, brevets, licences, logiciels et valeurs similaires', '651', 42),
('Moins-values sur cessions d actifs non courants', '652', 42),
('Jetons de presence', '653', 42),
('Pertes sur creances irrecouvrables', '654', 42),
('Quote-part de resultat sur operations faites en commun', '655', 42),
('Amendes, penalites, subventions accordees, dons et liberalites', '656', 42),
('Charges exceptionnelles de gestion courante', '657', 42),
('Autres charges de gestion courante', '658', 42);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
('Dotations - actifs non courants', '681', 43),
('Dotations - actifs courants', '685', 43);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
('Reprises d exploitation - actifs non courants', '781', 44),
('Reprises d exploitation - actifs courants', '785', 44);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
('Produits de participations', '761', 45),
('Produits des autres immobilisations financieres', '762', 45),
('Revenus des autres creances', '763', 45),
('Revenus et plus-values des valeurs mobilieres de placement', '764', 45),
('Gains de change', '766', 45),
('Produits nets sur cessions de valeurs mobilieres de placement', '767', 45),
('Autres produits financiers', '768', 45);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
('Charges d interets', '661', 46),
('Pertes sur creances liees a des participations', '664', 46),
('Moins-values sur titres de placement', '665', 46),
('Pertes de change', '666', 46),
('Moins-values sur instruments financiers et assimiles', '667', 46),
('Autres charges financieres', '668', 46);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
('Impots sur les benefices bases sur le resultat des activites ordinaires', '695', 47),
('Autres impots sur les resultats', '698', 47);
INSERT INTO Compte (nom, numero_compte, id_poste) VALUES
('Imposition differee actif', '692', 48),
('Imposition differee passif', '693', 48);






-- Insertion des écritures pour les comptes

INSERT INTO ecriture (date, id_compte, id_exercice, montant, description) VALUES
-- Comptes de l'exercice 3
-- Actifs Non Courants Total 2.870.000
('2025-01-01', 1, 3, 150000, 'Fonds commercial'),
('2025-01-01', 2, 3, 200000, 'Frais de developpement immobilisables'),
('2025-01-01', 3, 3, 120000, 'Logiciels informatiques et assimiles'),
('2025-01-01', 4, 3, 723000, 'Concessions, brevets, licences, marques, procedes'),
('2025-01-01', 5, 3, 10000, 'Autres immobilisations incorporelles'),
('2025-01-01', 6, 3, 25000, 'Terrains'),
('2025-01-01', 7, 3, 50000, 'Agencements et amenagements de terrain'),
('2025-01-01', 8, 3, 15000, 'Constructions'),
('2025-01-01', 9, 3, 10000, 'Installations techniques, materiel, et outillage industriels'),
('2025-01-01', 10, 3, 12000, 'Autres immobilisations corporelles'),
('2025-01-01', 11, 3, 30000, 'Immobilisations corporelles en cours'),
('2025-01-01', 12, 3, 15000, 'Immobilisations incorporelles en cours'),
('2025-01-01', 13, 3, 50000, 'Avances et acomptes verses sur commandes d immobilisations'),
('2025-01-01', 14, 3, 40000, 'Titres de participation'),
('2025-01-01', 15, 3, 25000, 'Creances rattachees a des participations'),
('2025-01-01', 16, 3, 20000, 'Depots et cautionnements verses'),
('2025-01-01', 17, 3, 15000, 'Autres creances immobilisees'),
('2025-01-01', 18, 3, 30000, 'Titres de participation evalues par equivalence'),
('2025-01-01', 19, 3, 45000, 'Autres formes de participations'),
('2025-01-01', 20, 3, 70000, 'Titres immobilises autres que les titres immobilises de l activite de portefeuille'),
('2025-01-01', 21, 3, 50000, 'Prets'),
('2025-01-01', 22, 3, 60000, 'Matieres premieres et fournitures'),
('2025-01-01', 23, 3, 80000, 'En-cours de production de biens'),
('2025-01-01', 24, 3, 70000, 'En-cours de production de services'),
('2025-01-01', 25, 3, 90000, 'Stocks de produits finis'),
('2025-01-01', 26, 3, 12000, 'Stocks de marchandises'),
('2025-01-01', 27, 3, 25000, 'Clients'),
('2025-01-01', 28, 3, 15000, 'Clients, effets a recevoir'),
('2025-01-01', 29, 3, 20000, 'Clients douteux'),
('2025-01-01', 30, 3, 194000, 'Clients - produits non encore factures'),
('2025-01-01', 31, 3, 75000, 'Clients'),
('2025-01-01', 32, 3, 400000, 'Creances sur cessions d immobilisations'),
('2025-01-01', 33, 3, 90000, 'Autres comptes debiteurs'),
('2025-01-01', 34, 3, 100000, 'Etat, subventions a recevoir'),
('2025-01-01', 35, 3, 25000, 'Etat, impots sur les resultats'),
('2025-01-01', 36, 3, 35000, 'Etat, taxes sur le chiffre d affaires'),
('2025-01-01', 37, 3, 15000, 'Charges constatees d avance'),
('2025-01-01', 38, 3, 200000, 'Produits constates d avance'),
('2025-01-01', 39, 3, 25000, 'Banques, comptes courants'),
('2025-01-01', 40, 3, 300000, 'Autres organismes financiers'),
('2025-01-01', 41, 3, 100000, 'Concours bancaires courants'),
('2025-01-01', 42, 3, 15000, 'Autres valeurs mobilieres de placement et creances assimilees'),
('2025-01-01', 43, 3, 200000, 'Caisse'),

-- Capitaux Propres Total 550.000
('2025-01-01', 44, 3, 50000, 'Capital'),
('2025-01-01', 45, 3, 30000, 'Primes liees au capital social'),
('2025-01-01', 46, 3, 100000, 'Reserves'),
('2025-01-01', 47, 3, 25000, 'Ecart d evaluation'),
('2025-01-01', 48, 3, 50000, 'Ecart d equivalence'),
('2025-01-01', 51, 3, 70000, 'Report a nouveau solde crediteur'),
('2025-01-01', 52, 3, 80000, 'Report a nouveau solde debiteur'),

-- Passifs Non Courants Total 1.180.000
('2025-01-01', 53, 3, 150000, 'Subventions equipement'),
('2025-01-01', 54, 3, 20000, 'Autres subventions investissement'),
('2025-01-01', 55, 3, 10000, 'Impots differes actif'),
('2025-01-01', 56, 3, 150000, 'Impots differes passif'),
('2025-01-01', 57, 3, 38000, 'Emprunts obligataires convertibles'),
('2025-01-01', 58, 3, 10000, 'Autres emprunts obligataires'),
('2025-01-01', 59, 3, 150000, 'Emprunts aupres des etablissements de credit'),
('2025-01-01', 60, 3, 20000, 'Dettes sur contrats de location-financement'),
('2025-01-01', 61, 3, 30000, 'Provisions pour pensions et obligations similaires'),
('2025-01-01', 62, 3, 50000, 'Provisions pour impots'),
('2025-01-01', 63, 3, 200000, 'Provisions pour renouvellement des immobilisations'),
('2025-01-01', 64, 3, 100000, 'Autres provisions pour charges - passifs non courants'),

-- Passifs Courants Total 1.140.000
('2025-01-01', 65, 3, 50000, 'Fournisseurs de biens et services'),
('2025-01-01', 66, 3, 122000, 'Autres emprunts et dettes assimilees'),
('2025-01-01', 67, 3, 170000, 'Fournisseurs de biens et services'),
('2025-01-01', 68, 3, 150000, 'Fournisseurs effets a payer'),
('2025-01-01', 69, 3, 200000, 'Fournisseurs d immobilisations'),
('2025-01-01', 70, 3, 50000, 'Provisions - passifs courants'),
('2025-01-01', 71, 3, 60000, 'Produits constates d avance'),
('2025-01-01', 72, 3, 100000, 'Associes - comptes courants'),
('2025-01-01', 73, 3, 150000, 'Associes, dividendes a payer'),
('2025-01-01', 74, 3, 200000, 'Autres comptes crediteurs ou debiteurs'),
('2025-01-01', 75, 3, 50000, 'Concours bancaires courants'),

-- Compte de Resultat Tsy nicalculeko fa tsy fanatko hoe miditra @le calcul ve
('2025-01-01', 76, 3, 100000, 'Ventes de produits finis'),
('2025-01-01', 77, 3, 200000, 'Ventes de produits intermediaires'),
('2025-01-01', 78, 3, 150000, 'Ventes de produits residuels'),
('2025-01-01', 79, 3, 250000, 'Ventes de travaux'),
('2025-01-01', 80, 3, 300000, 'Ventes d etudes'),
('2025-01-01', 81, 3, 100000, 'Ventes de prestations de service'),
('2025-01-01', 82, 3, 150000, 'Ventes de marchandises'),
('2025-01-01', 83, 3, 200000, 'Produits des activites annexes'),
('2025-01-01', 84, 3, 500000, 'Rabais, remises et ristournes accordes'),
('2025-01-01', 85, 3, 10000, 'Variation de stocks d en-cours'),
('2025-01-01', 86, 3, 150000, 'Variation de stocks de produits'),
('2025-01-01', 87, 3, 20000, 'Production immobilisee d ectif incorporel'),
('2025-01-01', 88, 3, 250000, 'Production immobilisee d ectif corporel'),

('2025-01-01', 89, 3, 30000, 'Matieres premieres'),
('2025-01-01', 90, 3, 10000, 'Autres approvisionnements'),
('2025-01-01', 91, 3, 15000, 'Variations des stocks'),
('2025-01-01', 92, 3, 20000, 'Achats d etudes et de prestations de service'),
('2025-01-01', 93, 3, 25000, 'Achats de materiels, equipements et travaux'),
('2025-01-01', 94, 3, 30000, 'Achats de marchandises'),
('2025-01-01', 95, 3, 50000, 'Frais de transport'),
('2025-01-01', 96, 3, 60000, 'Frais de stockage'),
('2025-01-01', 97, 3, 10000, 'Frais de manutention'),
('2025-01-01', 98, 3, 15000, 'Frais de vente et de distribution'),
('2025-01-01', 99, 3, 20000, 'Autres charges externes'),
('2025-01-01', 100, 3, 30000, 'Ressources humaines'),
('2025-01-01', 101, 3, 40000, 'Salaires et traitements'),
('2025-01-01', 102, 3, 50000, 'Charges sociales'),
('2025-01-01', 103, 3, 15000, 'Revenus d activités de placement'),
('2025-01-01', 104, 3, 20000, 'Autres produits'),
('2025-01-01', 105, 3, 30000, 'Autres charges'),
('2025-01-01', 106, 3, 40000, 'Impots sur les benefices'),
('2025-01-01', 107, 3, 50000, 'Provisions pour risques et charges'),
('2025-01-01', 108, 3, 6000, 'Frais bancaires'),
('2025-01-01', 109, 3, 7000, 'Charges d intetres'),
('2025-01-01', 110, 3, 8000, 'Autres impots et taxes'),

('2025-01-01', 111, 3, 20000, 'Dons et subventions'),
('2025-01-01', 112, 3, 30000, 'Intérêts de la dette'),
('2025-01-01', 113, 3, 40000, 'Commissions'),
('2025-01-01', 114, 3, 50000, 'Autres taxes et cotisations'),
('2025-01-01', 115, 3, 10000, 'Retraite'),
('2025-01-01', 116, 3, 15000, 'Commissions de financement'),
('2025-01-01', 117, 3, 20000, 'Commissions d agencement'),
('2025-01-01', 118, 3, 50000, 'Frais juridiques et judiciaires'),
('2025-01-01', 119, 3, 60000, 'Frais de litiges'),
('2025-01-01', 120, 3, 15000, 'Provision sur stocks'),
('2025-01-01', 121, 3, 10000, 'Provisions sur comptes clients'),
('2025-01-01', 122, 3, 70000, 'Régularisations fiscales'),
('2025-01-01', 123, 3, 8000, 'Frais d indemnisation'),
('2025-01-01', 124, 3, 25000, 'Frais de restructuration'),
('2025-01-01', 125, 3, 30000, 'Provisions sur pertes'),
('2025-01-01', 126, 3, 15000, 'Frais de liquidation'),
('2025-01-01', 127, 3, 20000, 'Remboursement de dettes'),
('2025-01-01', 128, 3, 10000, 'Taxes locales'),
('2025-01-01', 129, 3, 40000, 'Cotisations sociales'),
('2025-01-01', 130, 3, 15000, 'Autres dettes fiscales'),
('2025-01-01', 131, 3, 20000, 'Frais d acquisition'),
('2025-01-01', 132, 3, 30000, 'Frais d obtention de crédit'),
('2025-01-01', 133, 3, 40000, 'Frais de notaire'),
('2025-01-01', 134, 3, 50000, 'Frais de transaction'),
('2025-01-01', 135, 3, 6000, 'Frais d assurance'),
('2025-01-01', 136, 3, 70000, 'Frais divers'),
('2025-01-01', 137, 3, 8000, 'Frais d entretien et de réparation'),
('2025-01-01', 138, 3, 90000, 'Frais de publicité et de marketing'),
('2025-01-01', 139, 3, 15000, 'Frais de formation'),
('2025-01-01', 140, 3, 20000, 'Frais d étudier les marchés'),
('2025-01-01', 141, 3, 25000, 'Frais d informatique'),
('2025-01-01', 142, 3, 3000, 'Frais d audit'),
('2025-01-01', 143, 3, 100000, 'Frais d analyse financière'),
('2025-01-01', 144, 3, 12000, 'Frais d analyse marketing'),
('2025-01-01', 145, 3, 15000, 'Frais d recherche et développement'),
('2025-01-01', 146, 3, 20000, 'Frais de construction'),
('2025-01-01', 147, 3, 25000, 'Frais de formation professionnelle'),
('2025-01-01', 148, 3, 30000, 'Frais de R&D'),
('2025-01-01', 149, 3, 10000, 'Frais en compte de banque'),
('2025-01-01', 150, 3, 12000, 'Frais d adjoindre des stocks'),
('2025-01-01', 151, 3, 20000, 'Frais de production'),
('2025-01-01', 152, 3, 30000, 'Frais de distribution'),
('2025-01-01', 153, 3, 40000, 'Frais d exploitation'),
('2025-01-01', 154, 3, 50000, 'Frais d administration'),
('2025-01-01', 155, 3, 60000, 'Frais d investissement'),
('2025-01-01', 156, 3, 7000, 'Frais de développement'),
('2025-01-01', 157, 3, 8000, 'Frais de marketing'),
('2025-01-01', 158, 3, 10000, 'Frais de communication');
