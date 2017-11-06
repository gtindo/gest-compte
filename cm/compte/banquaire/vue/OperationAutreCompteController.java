package cm.compte.banquaire.vue;

import javafx.scene.control.TextField ;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField ;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton ;
import javafx.scene.control.MenuItem ;
import cm.compte.banquaire.MainApp;
import cm.compte.banquaire.modele.CompteBanquaire;
import cm.compte.banquaire.modele.Motif;
import cm.compte.banquaire.modele.MoyenPaiement;
import cm.compte.banquaire.modele.Operation;
import javafx.fxml.FXML;

import java.util.Date ;


public class OperationAutreCompteController {

	@FXML
	private TextField numCompte ;

	@FXML
	private TextField montant ;

	@FXML
	private PasswordField motDePasse ;

	@FXML
	private MenuButton menuMoyen ;

	@FXML
	private MenuItem carte ;

	@FXML
	private MenuItem virement ;

	@FXML
	private MenuItem cheque ;

	@FXML
	private MenuButton menuMotif ;

	@FXML
	private MenuItem alimentation ;

	@FXML
	private MenuItem divers ;

	@FXML
	private MenuItem loyer ;

	@FXML
	private MenuItem salaire ;

	@FXML
	private Button valider ;

	@FXML
	private Button annuler ;

	private MainApp mainApp ;

	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp ;
	}

	@FXML
	public void initialize(){
		carte.setOnAction((event) -> {
			menuMoyen.setText(carte.getText());
		});

		virement.setOnAction((event) -> {
			menuMoyen.setText(virement.getText());
		});

		cheque.setOnAction((event) -> {
			menuMoyen.setText(cheque.getText());
		});

		alimentation.setOnAction((event)-> {
			menuMotif.setText(alimentation.getText());
		});

		divers.setOnAction((event) -> {
			menuMotif.setText(divers.getText());
		});

		loyer.setOnAction((event) -> {
			menuMotif.setText(loyer.getText());
		});

		salaire.setOnAction((event)-> {
			menuMotif.setText(salaire.getText());
		}) ;
	}

	@FXML
	public void sortieHandled(){
		this.mainApp.closeDialog();
	}

	@FXML
	public void validerHandled(){
		Operation opp = new Operation() ;
		double montantOpp = 0.0 ;
		boolean testExistence ;
		CompteBanquaire compte = new CompteBanquaire() ;

		String erreur = "" ;

		if(montant.getText().equals("") || motDePasse.getText().equals("") || menuMotif.getText().equals("Motif operation") || menuMoyen.getText().equals("Moyen de paiement")){
			erreur += "Un ou plusieurs champs nom remplis \n" ;
		}else{
			testExistence = compte.ouvrireCompteBanquaire(numCompte.getText()) ;

			if(!testExistence){
				erreur += "Le compte entre n'existe pas" ;
			}
		}

		if(!motDePasse.getText().equals(this.mainApp.getCompteBanquaire().getMotDePasse())){
			erreur += "Mot de passe incorrect \n" ;
		}

		try{
			montantOpp = Double.parseDouble(this.montant.getText()) ;
			if(montantOpp <= 0){
				erreur += "Le montant dit etre strictement superieur a 0. \n" ;
			}

			if(montantOpp >= this.mainApp.getValeurCourante()){
				erreur += "Montant Superieur a la valeur courante" ;
			}
		}catch(Exception e){
			erreur += "Montant incorrect \n" ;
		}

		if(erreur == ""){
			opp.setMontant(montantOpp);
			opp.setDateOperation((new Date()).toString());
			opp.setTypeOperation("Credit");
			opp.setNumeroCompte(this.mainApp.getCompteBanquaire().getNumeroCompte());

			if(this.menuMotif.getText().equals(Motif.ALIMENTATION.toString())){
				opp.setMotifOperation(Motif.ALIMENTATION);
			}else if(this.menuMotif.getText().equals(Motif.DIVERS.toString())){
				opp.setMotifOperation(Motif.DIVERS);
			}else if(this.menuMotif.getText().equals(Motif.LOYER.toString())){
				opp.setMotifOperation(Motif.LOYER);
			}else{
				opp.setMotifOperation(Motif.SALAIRE);
			}

			if(this.menuMoyen.getText().equals(MoyenPaiement.CB.toString())){
				opp.setMoyenDePaiement(MoyenPaiement.CB);
			}else if(this.menuMoyen.getText().equals(MoyenPaiement.CHEQUE.toString())){
				opp.setMoyenDePaiement(MoyenPaiement.CHEQUE);
			}else{
				opp.setMoyenDePaiement(MoyenPaiement.VIREMENT);
			}

			compte.getLigneComptable().add(opp) ;
			compte.enregistrerCompte() ;
			opp.setNumeroCompte(compte.getNumeroCompte());
			opp.setTypeOperation("Debit");
			this.mainApp.getObList().remove(0, this.mainApp.getObList().size());
			this.mainApp.setValeurCourante(0);
			this.mainApp.getCompteBanquaire().getLigneComptable().add(opp) ;
			this.mainApp.getCompteBanquaire().enregistrerCompte() ;

			this.mainApp.showOperationEtEtat();

			Alert alert = new Alert(AlertType.INFORMATION) ;
			alert.setTitle("Operation");
			alert.setHeaderText("Operation reussie");
			alert.showAndWait() ;

			this.mainApp.closeDialog();

		}else{
			Alert alert = new Alert(AlertType.ERROR) ;
			alert.setTitle("Erreur");
			alert.setHeaderText("Une ou plusieurs erreurs rencontrees");
			alert.setContentText(erreur);
			alert.showAndWait() ;
		}


	}
}
