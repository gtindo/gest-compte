package cm.compte.banquaire.vue;

import java.io.File;

import cm.compte.banquaire.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SupprimerCompteController {
	@FXML
	private PasswordField password ;

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

	}

	@FXML
	public void annulerHandled(){
		this.mainApp.closeDialog();
	}

	@FXML
	public void validerHandled(){

		if(!password.getText().equals(this.mainApp.getCompteBanquaire().getMotDePasse())){
			Alert alert = new Alert(AlertType.ERROR) ;
			alert.setTitle("Suppression du compte ");
			alert.setHeaderText("Echec operation");
			alert.setContentText("Mot de passe incorrect");
			alert.showAndWait() ;
		}else
		{
			try{
				File f = new File("comptes/"+this.mainApp.getCompteBanquaire().getNumeroCompte()+".dat") ;
				f.delete() ;

				Alert alert = new Alert(AlertType.INFORMATION) ;
				alert.setTitle("Suppression du compte ");
				alert.setHeaderText("Operation reussie");
				alert.setContentText("Le compte "+this.mainApp.getCompteBanquaire().getNumeroCompte()+" a ete supprime.");
				alert.showAndWait() ;

				this.mainApp.getCompteBanquaire().setLigneComptable(null);
				this.mainApp.getCompteBanquaire().setMotDePasse(null);
				this.mainApp.getCompteBanquaire().setNumeroCompte(null);
				this.mainApp.getCompteBanquaire().setTaux(0);
				this.mainApp.getCompteBanquaire().setTypeDeCompte(null);
				this.mainApp.getObList().remove(0, this.mainApp.getObList().size());
				this.mainApp.setValeurCourante(0);

				this.mainApp.closeFirstStage();
				this.mainApp.initFirstStageLayout();
			}catch(Exception e){

				Alert alert = new Alert(AlertType.INFORMATION) ;
				alert.setTitle("Suppression du compte ");
				alert.setHeaderText("Echec operation");
				alert.setContentText("Erreurs rencontrees lors de la suppression du compte");
				alert.showAndWait() ;

			}
		}
	}

}
