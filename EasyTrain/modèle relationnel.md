# Modèle relationnel EasyTrain 1
Gabriel Namias, <br>
04/10/2024

### Utilisateurs
- utilisateur(id :int(3), login: varchar(20), mdr: varchar(256), nom: varchar(30), <br> prenom: varchar(30), date_embauche: datetime, role: enum('ADMIN', 'EMPLOYE'))
- clé primaire : id
- clé étrangère : __
- champs unique : login

### Arret:
- arret(id: int(3), nom: varchar(30))
- clé primaire : id
- clé étrangère : __
- champs unique : __

### Trajet:
- trajet(code: varchar(30), temps_depart: datetime, temps-arrivee: datetime, arret_depart_id: <br> int(3), arret_arrivee_id: int(3))
- clé primaire : id
- clé étrangère : - arret_depart_id référence à Arret(id) <br> - arret_arrivee_id référence à Arret(id)
- champs unique : __

## Requête BDD potentielles

### Requete sur les données:
UPDATE <br>
DELETE <br>
INSERT
### Requete Recherche (Select):
- Récupérer l'utilisateur (toute les colonnes) qui ont un id = 1
- Lister les utilisateurs qui sont ADMIN.
- Récupérer tout les trajets sur une période.
- Ajouter un utilisateur. 
- Modifier le temps d'arrivée d'un trajet avec son code. 

// requete a faire


