DROP DATABASE IF EXISTS gestion_finance;
CREATE DATABASE gestion_finance;

\c gestion_finance;

CREATE TABLE Exercice (
    id SERIAL PRIMARY KEY,
    dateDebut DATE NOT NULL,
    dateFin DATE NOT NULL
);


CREATE TABLE Categorie (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL 
);


CREATE TABLE SousCategorie (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    id_categorie INT REFERENCES Categorie(id) ON DELETE CASCADE
);


CREATE TABLE Poste (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    id_sous_categorie INT REFERENCES SousCategorie(id) ON DELETE CASCADE
);

CREATE TABLE Compte (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    numero_compte VARCHAR(20) NOT NULL,  -- Numéro de compte
    id_poste INT REFERENCES Poste(id) ON DELETE CASCADE
);

CREATE TABLE Ecriture (
    id SERIAL PRIMARY KEY,
    date DATE NOT NULL,
    id_exercice INT REFERENCES Exercice(id) ON DELETE CASCADE,
    id_categorie INT REFERENCES Categorie(id) ON DELETE CASCADE,
    id_sous_categorie INT REFERENCES SousCategorie(id) ON DELETE CASCADE,
    id_poste INT REFERENCES Poste(id) ON DELETE CASCADE,
    id_compte INT REFERENCES Compte(id) ON DELETE CASCADE,
    montant DECIMAL(15, 2) NOT NULL,
    description VARCHAR(255) 
);