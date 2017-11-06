package cm.compte.banquaire.vue;

import javafx.scene.control.Button ;
import javafx.scene.control.PasswordField ;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;

import cm.compte.banquaire.MainApp ;

public class ChangerMotDePasseController {
	@FXML
	private PasswordField oldPassword ;

	@FXML
	private PasswordField newPassword ;

	@FXML
	private Button valider ;

	@FXML
	private Button annuler ;

	private MainApp mainApp ;

	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp ;
	}

	public void initialize(){

	}

	@FXML
	public void annulerHandled(){
		this.mainApp.closeDialog();
	}

	@FXML
	public void validerHandled(){
		String erreur = "" ;

		if(oldPassword.getText().equals("") || newPassword.getText().equals("")){
			erreur += "Un ou plusieurs champs vides.\n" ;
		}else{
			if(!oldPassword.getText().equals(this.mainApp.getCompteBanquaire().getMotDePasse())){

				erreur += "Ancien mot de passe incorrecte.\n" ;
			}

			if(newPassword.getText().length() < 6){
				erreur += "Le nouveau mot de passe doit avoir au moins 6 caracteres.\n" ;
			}
		}

		if(erreur.equals("")){
			this.mainApp.getCompteBanquaire().setMotDePasse(newPassword.getText());
			this.mainApp.getCompteBanquaire().enregistrerCompte() ;

			Alert alert = new Alert(AlertType.INFORMATION) ;
			alert.setTitle("Changer de mot de passe") ;
			alert.setContentText("Changement de mot de passe reussi");
			alert.showAndWait() ;
			this.mainApp.closeDialog();
		}else{
			Alert alert = new Alert(AlertType.ERROR) ;
			alert.setTitle("Erreur mot de passe") ;
			alert.setHeaderText("Une ou plusieurs erreurs rencontrees");
			alert.setContentText(erreur);
			alert.showAndWait() ;
		}
	}

}
