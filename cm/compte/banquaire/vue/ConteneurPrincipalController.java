package cm.compte.banquaire.vue;

import cm.compte.banquaire.MainApp ;
import javafx.scene.control.MenuItem;
import javafx.fxml.FXML ;

public class ConteneurPrincipalController {

	@FXML
	private MenuItem sortie ;

	@FXML
	private MenuItem deconnexion ;

	@FXML
	private MenuItem crediteMonCompte ;

	@FXML
	private MenuItem debiterMonCompte ;

	@FXML
	private MenuItem crediterAutreCompte ;

	@FXML
	private MenuItem diagrammeMotif ;

	@FXML
	private MenuItem diagrammeMoyen ;

	@FXML
	private MenuItem camemberOperation ;

	@FXML
	private MenuItem changerMotDePasse ;

	@FXML
	private MenuItem supprimerCompte ;

	@FXML
	private MenuItem preferences ;

	@FXML
	private MenuItem aide ;

	private MainApp mainApp ;

	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp ;
	}

	public void initialize(){

	}

	@FXML
	public void exitHandled(){
		this.mainApp.closeFirstStage() ;
	}

	@FXML
	public void deconnexionHandled(){
		this.mainApp.getCompteBanquaire().setLigneComptable(null);
		this.mainApp.getCompteBanquaire().setMotDePasse(null);
		this.mainApp.getCompteBanquaire().setNumeroCompte(null);
		this.mainApp.getCompteBanquaire().setTaux(0);
		this.mainApp.getCompteBanquaire().setTypeDeCompte(null);
		this.mainApp.getObList().remove(0, this.mainApp.getObList().size());
		this.mainApp.setValeurCourante(0);
		this.mainApp.setNumbOperation(10);

		this.mainApp.closeFirstStage();
		this.mainApp.initFirstStageLayout();
	}

	@FXML
	public void crediteMonCompteHandled(){
		this.mainApp.showOperationMonCompte("Crediter mon Compte");
	}

	@FXML
	public void debiteMonCompteHandled(){
		this.mainApp.showOperationMonCompte("Debiter mon Compte");
	}

	@FXML
	public void crediterAutreCompteHandled(){
		this.mainApp.showOperationAutreCompte("Crediter un autre Compte");
	}

	@FXML
	public void diagrammeMoyenHandled(){
		this.mainApp.showDiagrammeMoyen();
	}

	@FXML
	public void diagrammeMotifHandled(){
		this.mainApp.showDiagrammeMotif();
	}

	@FXML
	public void camemberOperationHandled(){
		this.mainApp.showOperationCamember();
	}

	@FXML
	public void changerMotDePasseHandled(){
		this.mainApp.showChangerMotDePasse();
	}

	@FXML
	public void supprimerCompteHandled(){
		this.mainApp.showSupprimerCompte();
	}

	@FXML
	public void preferencesHandled(){
		this.mainApp.showPreference();
	}

	@FXML
	public void aideHandled(){
		this.mainApp.showAide();
	}

}
