package cm.compte.banquaire.vue;

import cm.compte.banquaire.modele.*;
import cm.compte.banquaire.MainApp;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList ;
import java.util.Date ;


public class ConnexionEtInscriptionController {

	//premier onglet

	@FXML
	private TextField coNumeroCompte ;

	@FXML
	private PasswordField coMotDePasse ;

	@FXML
	private Button connexion ;

	// deuxieme onglet

	@FXML
	private MenuButton menuTypeCompte;

	@FXML
	private MenuItem menuEpargne ;

	@FXML
	private MenuItem menuJoint ;

	@FXML
	private MenuItem menuCourant ;

	@FXML
	private TextField inNumeroCompte;

	@FXML
	private TextField premiereValeur;

	@FXML
	private TextField tauxPlacement ;

	@FXML
	private PasswordField inMotDePasse ;

	private MainApp mainApp ;

	public ConnexionEtInscriptionController(){

	}

	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp ;
	}

	@FXML
	private void initialize(){
		this.menuEpargne.setOnAction((event) -> {
			this.menuTypeCompte.setText(this.menuEpargne.getText()) ;
		});

		this.menuJoint.setOnAction((event)-> {
			this.menuTypeCompte.setText(this.menuJoint.getText());
		});

		this.menuCourant.setOnAction((event) -> {
			this.menuTypeCompte.setText(this.menuCourant.getText());
		});
	}

	@FXML
	private void inscriptionHandled(){
		CompteBanquaire compte = new CompteBanquaire() ;
		ArrayList<Operation> ligneCompta  = new ArrayList<Operation>() ;
		Operation op = new Operation() ;
		TypeCompte type = TypeCompte.COURANT ;
		float taux = 0 ;
		String erreur = "" ;
		boolean test = false ;

		if(inNumeroCompte.getText().equals("") || inMotDePasse.getText().equals("") || premiereValeur.getText().equals("") || tauxPlacement.getText().equals("")){
			erreur += "Un ou plusieur champs vides" ;
		}else{

			String numCompte = inNumeroCompte.getText() ;
			compte.setNumeroCompte(numCompte);

			String passe = inMotDePasse.getText() ;

			test = compte.ouvrireCompteBanquaire(numCompte) ;
			compte.setMotDePasse(passe);

			if(test){
				erreur += "Le compte banquaire "+ numCompte +" existe deja. \n" ;
			}else{
				try{
					op.setMontant(Double.parseDouble(premiereValeur.getText()));
				}catch(Exception e){
					erreur += "Montant incorrecte\n" ;
				}
				op.setMotifOperation(Motif.SI);
				op.setMoyenDePaiement(MoyenPaiement.VIREMENT);
				op.setNumeroCompte(numCompte);
				op.setTypeOperation("Credit");
				op.setDateOperation((new Date()).toString());

				if(op.getMontant() <= 0){
					erreur += "La premiere valeur creditee doit etre superieure a 0. \n" ;
				}else{
					ligneCompta.add(op) ;
				}

				if(menuTypeCompte.getText().equals(TypeCompte.COURANT.toString())){
					type = TypeCompte.COURANT ;
				}else if(menuTypeCompte.getText().equals(TypeCompte.EPARGNE.toString())){
					type = TypeCompte.EPARGNE ;
				}else if(menuTypeCompte.getText().equals(TypeCompte.JOINT.toString())){
					type = TypeCompte.JOINT ;
				}else{
					erreur += "Veuillez choisir un type de compte\n" ;
				}

				try{
					taux = Float.parseFloat(tauxPlacement.getText()) ;
				}catch(Exception e){
					erreur += "Taux incorrect \n" ;
				}

				if(taux != 0 && type != TypeCompte.EPARGNE){
					erreur += "Seul le type de compte epargne a un taux non nul.\n" ;
				}

				if(taux <= 0 && type == TypeCompte.EPARGNE){
					erreur += "Veuillez choisir un taux superieur a 0.\n" ;
				}

				if(passe.length() < 6 || passe == null){
					erreur += "Le mot de passe doit avoir au moins 6 caracteres\n" ;
				}
			}
		}

		if(erreur.equals("")){
			compte.setTypeDeCompte(type);
			compte.setLigneComptable(ligneCompta);
			compte.setTaux(taux);
			compte.enregistrerCompte() ;

			Alert alert = new Alert(AlertType.INFORMATION) ;
			alert.setTitle("Inscription");
			alert.setContentText("Inscription reussie");
			alert.showAndWait() ;
			this.mainApp.setCompteBanquaire(compte) ;
			this.mainApp.closeFirstStage();
			this.mainApp.changeFirstStageLayout();
			this.mainApp.showOperationEtEtat();
		}else{
			Alert alert = new Alert(AlertType.ERROR) ;
			alert.setTitle("Erreurs") ;
			alert.setHeaderText("Erreurs rencontrees lors de l'incription");
			alert.setContentText(erreur) ;
			alert.showAndWait() ;
		}
	}

	@FXML
	private void connextionHandled(){
		CompteBanquaire compte = new CompteBanquaire() ;
		String erreur = "" ;
		compte.setMotDePasse(coMotDePasse.getText());

		boolean test = compte.ouvrireCompteBanquaire(coNumeroCompte.getText()) ;

		if(coNumeroCompte.getText().equals("") || coMotDePasse.getText().equals("")){
			erreur += "Veuillez entrer le numero de compte \n et le mot de passe. \n" ;
		}else{

			if(test){
				if(compte.getMotDePasse().equals(coMotDePasse.getText())){
					//rien
				}else{
					erreur += "Mot de passe incorrect. \n" ;
				}
			}else{
				erreur += "Numero de compte ou mot de passe incorrect. \n" ;
			}
		}

		if(erreur.equals("")){
			Alert alert = new Alert(AlertType.INFORMATION) ;
			alert.setTitle("Connexion");
			alert.setContentText("Connexion reussie");
			alert.showAndWait() ;
			this.mainApp.setCompteBanquaire(compte) ;
			this.mainApp.changeFirstStageLayout();
			this.mainApp.showOperationEtEtat();
		}else{
			Alert alert = new Alert(AlertType.ERROR) ;
			alert.setTitle("Erreur connexion");
			alert.setHeaderText("Echec de la connexion");
			alert.setContentText(erreur) ;
			alert.showAndWait() ;
		}
	}

}
