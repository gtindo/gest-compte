package cm.compte.banquaire.vue;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Button ;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType ;
import javafx.fxml.FXML;

import cm.compte.banquaire.MainApp ;

public class PreferencesController {
	@FXML
	private MenuButton menuPreference ;

	@FXML
	private MenuItem tenElements ;

	@FXML
	private MenuItem twentyElements ;

	@FXML
	private MenuItem allElements ;

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
		tenElements.setOnAction((event) -> {
			menuPreference.setText(tenElements.getText());
		});

		twentyElements.setOnAction((event) -> {
			menuPreference.setText(twentyElements.getText()) ;
		});

		allElements.setOnAction((event)-> {
			menuPreference.setText(allElements.getText());
		}) ;
	}

	@FXML
	public void annulerHandled(){
		this.mainApp.closeDialog();
	}

	@FXML
	public void validerHandled(){

		if(menuPreference.getText().equals("Nombres d'operations")){
			Alert alert = new Alert(AlertType.ERROR) ;
			alert.setTitle("Preferences") ;
			alert.setHeaderText("Choix du nombre d'operations");
			alert.setContentText("Aucun choix effectue");
			alert.showAndWait() ;
		}else{
			if(menuPreference.getText().equals("10 dernieres operations")){
				this.mainApp.setNumbOperation(10);
			}

			if(menuPreference.getText().equals("20 dernieres operations")){
				this.mainApp.setNumbOperation(20);
			}

			if(menuPreference.getText().equals("Toutes les opertions")){
				this.mainApp.setNumbOperation(this.mainApp.getCompteBanquaire().getLigneComptable().size());
			}

			this.mainApp.getObList().remove(0, this.mainApp.getObList().size());
			this.mainApp.setValeurCourante(0);
			this.mainApp.showOperationEtEtat();
			this.mainApp.closeDialog();

		}
	}
}
