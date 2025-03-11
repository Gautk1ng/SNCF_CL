DROP DATABASE IF EXISTS sncf;
CREATE DATABASE sncf;
USE sncf;

CREATE TABLE TypeConge(
   id_typeConge INT (5) NOT NULL AUTO_INCREMENT,
   libelle VARCHAR(50),
   PRIMARY KEY(id_typeConge)
);

insert into TypeConge values (null, "Conge Parental"), (null, "Conge Familial"), (null, "Conge Paye"), (null, "Conge Sante"), (null, "Autre");

CREATE TABLE Personne(
   id_personne INT (5) NOT NULL AUTO_INCREMENT,
   nom VARCHAR(50),
   email VARCHAR(50),
   mdp VARCHAR(50),
   statut VARCHAR(50),
   PRIMARY KEY(id_personne)
);

CREATE TABLE TypeCommande(
   id_typeCommande INT (5) NOT NULL AUTO_INCREMENT,
   libelle VARCHAR(50),
   PRIMARY KEY(id_typeCommande)
);

CREATE TABLE Prestataire(
   id_personne INT (5) NOT NULL AUTO_INCREMENT,
   contact_nom VARCHAR(50),
   contact_prenom VARCHAR(50),
   adresse VARCHAR(50),
   PRIMARY KEY(id_personne),
   FOREIGN KEY(id_personne) REFERENCES Personne(id_personne)
);

CREATE TABLE Dossier(
   id_dossier INT (5) NOT NULL AUTO_INCREMENT,
   nom_dossier VARCHAR(50),
   PRIMARY KEY(id_dossier)
);

CREATE TABLE Fichier(
   id_fichier INT (5) NOT NULL AUTO_INCREMENT,
   nom_fichier VARCHAR(50),
   type_fichier VARCHAR(50),
   id_dossier INT NOT NULL,
   PRIMARY KEY(id_fichier),
   FOREIGN KEY(id_dossier) REFERENCES Dossier(id_dossier)
);

CREATE TABLE Employe(
   id_personne INT (5) NOT NULL,
   prenom VARCHAR(50),
   date_embauche DATE,
   poste VARCHAR(50),
   PRIMARY KEY(id_personne),
   FOREIGN KEY(id_personne) REFERENCES Personne(id_personne)
);

CREATE TABLE Admin(
   id_personne INT (5) NOT NULL,
   droits VARCHAR(50),
   PRIMARY KEY(id_personne),
   FOREIGN KEY(id_personne) REFERENCES Employe(id_personne)
);

CREATE TABLE RH(
   id_personne INT (5) NOT NULL,
   responsabilite VARCHAR(50),
   PRIMARY KEY(id_personne),
   FOREIGN KEY(id_personne) REFERENCES Employe(id_personne)
);

CREATE TABLE Commande(
   id_commande INT (5) NOT NULL AUTO_INCREMENT,
   date_commande DATE,
   description ENUM("Cafe", "Clavier", "Souris", "Papier Toillettes", "Agrafeuse", "Gourde"),
   statut_validation VARCHAR(50),
   quantite INT,
   id_personne INT (5) NOT NULL,
   id_typeCommande INT (5) NOT NULL,
   id_personne_1 INT (5) NOT NULL,
   id_personne_2 INT (5) NOT NULL,
   PRIMARY KEY(id_commande),
   FOREIGN KEY(id_personne) REFERENCES Prestataire(id_personne),
   FOREIGN KEY(id_typeCommande) REFERENCES TypeCommande(id_typeCommande),
   FOREIGN KEY(id_personne_1) REFERENCES RH(id_personne),
   FOREIGN KEY(id_personne_2) REFERENCES Employe(id_personne)
);

CREATE TABLE Compte(
   id_compte INT (5) NOT NULL AUTO_INCREMENT,
   date_creation DATE,
   statut VARCHAR(50),
   mdpTemp VARCHAR(50),
   id_personne INT (5) NOT NULL,
   id_personne_1 INT (5) NOT NULL,
   PRIMARY KEY(id_compte),
   FOREIGN KEY(id_personne) REFERENCES RH(id_personne),
   FOREIGN KEY(id_personne_1) REFERENCES Admin(id_personne)
);

CREATE TABLE Conge(
   id_conge INT (5) NOT NULL AUTO_INCREMENT,
   date_debut DATE,
   date_fin DATE,
   description TEXT,
   statut VARCHAR(50),
   id_personne INT (5) NOT NULL,
   id_personne_1 INT (5) NOT NULL,
   id_typeConge INT (5) NOT NULL,
   PRIMARY KEY(id_conge),
   FOREIGN KEY(id_personne) REFERENCES RH(id_personne),
   FOREIGN KEY(id_personne_1) REFERENCES Employe(id_personne),
   FOREIGN KEY(id_typeConge) REFERENCES TypeConge(id_typeConge)
);

CREATE TABLE Absence(
   id_absence INT (5) NOT NULL AUTO_INCREMENT,
   date_debut DATE,
   date_fin DATE,
   motif VARCHAR(50),
   typeAbsence VARCHAR(50),
   id_personne INT (5) NOT NULL,
   id_personne_1 INT (5) NOT NULL,
   statut enum('validee','refusee','en cours'),
   PRIMARY KEY(id_absence),
   FOREIGN KEY(id_personne) REFERENCES Employe(id_personne),
   FOREIGN KEY(id_personne_1) REFERENCES RH(id_personne)
);

CREATE TABLE Acceder(
   id_personne INT (5) NOT NULL,
   id_dossier INT (5) NOT NULL,
   droits VARCHAR(50),
   PRIMARY KEY(id_personne, id_dossier),
   FOREIGN KEY(id_personne) REFERENCES Employe(id_personne),
   FOREIGN KEY(id_dossier) REFERENCES Dossier(id_dossier)
);

CREATE TABLE Acceder2(
   id_personne INT (5) NOT NULL,
   id_fichier INT (5) NOT NULL,
   droits VARCHAR(50),
   PRIMARY KEY(id_personne, id_fichier),
   FOREIGN KEY(id_personne) REFERENCES Employe(id_personne),
   FOREIGN KEY(id_fichier) REFERENCES Fichier(id_fichier)
);

# Creation une vue : TH - Employe - Personne

create view vueRH as (
	select p.*, e.prenom, e.date_embauche, e.poste, rh.responsabilite
	from Personne p
	inner join Employe e on e.id_personne = p.id_personne
	inner join RH on rh.id_personne = e.id_personne
);

# Creation une vue : Employe - Personne

create view vueEmploye as (
	select p.*, e.prenom, e.date_embauche, e.poste
	from Personne p
	inner join Employe e on e.id_personne = p.id_personne
);

# procedure stockee pour ajouter un RH

delimiter $
create procedure insertRH (IN p_nom VARCHAR(50), IN p_email VARCHAR(50), IN p_mdp VARCHAR(50),
IN p_statut VARCHAR(50), IN p_prenom VARCHAR(50), IN p_date_embauche VARCHAR(50),
IN p_poste VARCHAR(50), IN p_responsabilite VARCHAR(50))

Begin
	declare p_id_personne int(5);
	insert into personne values (null, p_nom, p_email, p_mdp, p_statut);
	select id_personne into p_id_personne from personne where nom = p_nom and email = p_email;
	insert into Employe values (p_id_personne, p_prenom, p_date_embauche, p_poste);
	insert into RH values (p_id_personne, p_responsabilite);
End $
delimiter ;

call insertRH("Gauthier", "a@gmail.com", "123", "RH", "Arthur", "2020-12-12", "Cadre", "RH Etudes");
call insertRH("Cyril", "c@gmail.com", "123", "RH", "Paul", "2020-12-12", "Alternant", "RH Compta");


# procedure stockee pour ajouter un Employe

delimiter $
create procedure insertEmploye (IN p_nom VARCHAR(50), IN p_email VARCHAR(50), IN p_mdp VARCHAR(50),
IN p_statut VARCHAR(50), IN p_prenom VARCHAR(50), IN p_date_embauche VARCHAR(50),
IN p_poste VARCHAR(50))

Begin
	declare p_id_personne int(5);
	insert into personne values (null, p_nom, p_email, p_mdp, p_statut);
	select id_personne into p_id_personne from personne where nom = p_nom and email = p_email;
	insert into Employe values (p_id_personne, p_prenom, p_date_embauche, p_poste);
End $
delimiter ;

call insertEmploye("Esteban", "b@gmail.com", "456", "Employe", "Hugo", "2021-11-12", "Salarie");


# procedure stockee pour ajouter un Employe

delimiter $
create procedure insertPrestataire (IN p_nom VARCHAR(50), IN p_email VARCHAR(50), IN p_mdp VARCHAR(50),
IN p_statut VARCHAR(50), IN p_contact_nom VARCHAR(50), IN p_contact_prenom VARCHAR(50),
IN p_adresse VARCHAR(50))

Begin
	declare p_id_personne int(5);
	insert into personne values (null, p_nom, p_email, p_mdp, p_statut);
	select id_personne into p_id_personne from personne where nom = p_nom and email = p_email;
	insert into Prestataire values (p_id_personne, p_contact_nom, p_contact_prenom, p_adresse);
End $
delimiter ;

call insertPrestataire("HP", "hp@gmail.com", "678", "Prestataire", "Dupond", "Paul", "rue de Paris, 75");

# procedure modifier RH

delimiter $
create procedure updateRH (IN p_id_personne INT, IN p_nom VARCHAR(50), IN p_email VARCHAR(50), IN p_mdp VARCHAR(50),
IN p_statut VARCHAR(50), IN p_prenom VARCHAR(50), IN p_date_embauche VARCHAR(50),
IN p_poste VARCHAR(50), IN p_responsabilite VARCHAR(50))

Begin
	update RH set responsabilite = p_responsabilite where id_personne = p_id_personne;	
	update Employe set prenom = p_prenom, date_embauche = p_date_embauche, poste = p_poste where id_personne = p_id_personne;	
	update personne set nom = p_nom, email = p_email, mdp = p_mdp, statut = p_statut where id_personne = p_id_personne;
End $
delimiter ;