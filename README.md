# FinanceDash

## 1. Introduction
FinanceDash est une application de gestion financière permettant d'afficher des bilans, des comptes de résultats et des analyses détaillées des finances d'une entreprise, tout en exploitant des fonctionnalités avancées comme l'interprétation IA et des dashboards pour les analyses visuelles.

### 1.1. Fonctionnalités principales
- **Affichage des résultats financiers** : bilan actif/passif et compte de résultat par période.
- **Analyses financières avancées** :
  - Calcul et affichage des ratios financiers : ROA, ROE, ratios de liquidité, levier financier, etc.
  - Études et interprétations IA (via Cohere).
  - Visualisation des données à travers des dashboards interactifs.
- **Gestion des données** :
  - Insertion de bilans et comptes de résultats.
  - Calcul automatique des analyses par période.
- **PostgreSQL en backend** pour le stockage sécurisé des données financières.
- **Spring Boot** pour le backend, avec **Thymeleaf** pour le rendu HTML côté client.

---

## 2. Installation

### 2.1. Prérequis
- **Java 17** ou version ultérieure.
- **Maven** : Gestionnaire de dépendances.
- **PostgreSQL** : Base de données relationnelle.
- **IDE** : Visual Studio Code, IntelliJ ou Eclipse.
- **Navigateurs Web** : Chrome, Firefox ou équivalent pour accéder à l'application.

---

### 2.2. Étapes pour configurer et exécuter le projet

#### Étape 1 : Cloner le repository
Clonez ou téléchargez le repository :
```bash
git clone <https://github.com/TsantaRandriamihary/FinanceDash/>
```

#### Étape 2 : Configurer la base de données
1. Ouvrez PostgreSQL et créez une base de données nommée `financedb` :
   ```sql
   CREATE DATABASE financedb;
   ```
2. Exécutez les scripts SQL fournis dans le dossier `/SQL` pour configurer les tables :
   - `Tables.sql` : Crée les structures des tables (`Exercice`, `Categorie`, `SousCategorie`, `Poste`, etc.).
   - `Data.sql` : Ajoute des données initiales dans les tables.

#### Étape 3 : Configurer `application.properties`
Dans le fichier `src/main/resources/application.properties`, configurez les paramètres suivants :
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/financedb
spring.datasource.username=<votre_nom_utilisateur>
spring.datasource.password=<votre_mot_de_passe>
```

#### Étape 4 : Compiler et exécuter le projet
1. Ouvrez un terminal ou le terminal intégré de votre IDE.
2. Compilez et exécutez le projet avec Maven :
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
3. Accédez à l'application via un navigateur à l'adresse suivante :
   ```
   http://localhost:8080
   ```

---

## 3. Fonctionnalités des écrans

### 3.1. Gestion des données
- Insertion des bilans et comptes de résultats.
- Visualisation des bilans actifs et passifs.
- Détails des comptes de résultats pour une période donnée.

### 3.2. Analyses financières
- **Ratios calculés automatiquement** pour une période donnée :
  - Marge nette
  - ROA (Return on Assets)
  - ROE (Return on Equity)
  - Ratio de liquidité générale et réduite
  - Ratio d’endettement global
  - Ratio de couverture des intérêts
  - Levier financier
- **Études de levier financier** et interprétation des résultats.
- Dashboards interactifs pour visualiser les données et tendances financières.

### 3.3. Fonctionnalités IA
- Interprétation des ratios et analyses via Cohere.
- Recommandations automatiques pour améliorer la performance financière.

---

## 4. Calculs des ratios

### 4.1. Marge nette
```text
Résultat net de l’exercice / Total des produits des activités ordinaires
```

### 4.2. ROA (Return on Assets)
```text
Résultat net de l’exercice / Total des actifs
```

### 4.3. ROE (Return on Equity)
```text
(Résultat net de l’exercice / Capitaux propres) × 100
```

### 4.4. Ratio de liquidité générale
```text
Total des actifs courants / Total des passifs courants
```

### 4.5. Ratio de liquidité réduite
```text
(Actifs courants − Stock) / Passifs courants
```

### 4.6. Ratio d’endettement global
```text
(Total des dettes / Total des actifs) × 100
```

### 4.7. Ratio de couverture des intérêts
```text
Résultat opérationnel / Charges d’intérêts
```

### 4.8. Levier financier
```text
Total des actifs / Capitaux propres
```

---

## 5. Maintenance et Mise à jour

### Mise à jour de la base de données
Pour appliquer des modifications à la structure ou aux données :
1. Exécutez le script SQL de mise à jour dans PostgreSQL.
2. Redémarrez l'application via Maven :
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

### Mise à jour du projet
1. Mettez à jour le code via Git :
   ```bash
   git pull origin main
   ```
2. Compilez à nouveau le projet :
   ```bash
   mvn clean install
   ```

---
