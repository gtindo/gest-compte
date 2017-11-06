package cm.compte.banquaire.vue;

import cm.compte.banquaire.modele.*;
import cm.compte.banquaire.MainApp;

import javafx.scene.control.Label  ;
import javafx.scene.control.TableColumn ;
import javafx.scene.control.TableView ;
import javafx.fxml.FXML;

public class OperationsEtEtat {

	@FXML
	private Label numeroCompte ;

	@FXML
	private Label valeurCourante ;

	@FXML
	private Label typeDeCompte ;

	@FXML
	private Label taux ;

	@FXML
	private TableView<Operation> operations ;

	@FXML
	private TableColumn<Operation, String> numCompte;

	@FXML
	private TableColumn<Operation, String> typeOperation ;

	@FXML
	private TableColumn<Operation, Double> montant ;

	@FXML
	private TableColumn<Operation, String> date ;

	@FXML
	private TableColumn<Operation, Motif> motifPaiement ;

	@FXML
	private TableColumn<Operation, MoyenPaiement> moyenPaiement ;

	private MainApp mainApp = new MainApp() ;

	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp ;

		this.mainApp.appRefresh();
		numeroCompte.setText(this.mainApp.getCompteBanquaire().getNumeroCompte());
		typeDeCompte.setText(this.mainApp.getCompteBanquaire().getTypeDeCompte().toString());
		taux.setText(this.mainApp.getCompteBanquaire().getTaux()+"");
		valeurCourante.setText(this.mainApp.getValeurCourante()+"");

		operations.setItems(this.mainApp.getObList());
	}

	public void setValeurCourante(double val){
		this.valeurCourante.setText(val+"");
	}

	public void initialize(){

		numCompte.setCellValueFactory(cellData->cellData.getValue().getNumeroCompteProperty());
		typeOperation.setCellValueFactory(cellData->cellData.getValue().getTypeOperationProperty());
		montant.setCellValueFactory(cellData->cellData.getValue().getMontantProperty());
		date.setCellValueFactory(cellData->cellData.getValue().getDateOperationProperty()) ;
		motifPaiement.setCellValueFactory(cellData->cellData.getValue().getMotifOperationProperty()) ;
		moyenPaiement.setCellValueFactory(cellData->cellData.getValue().getMoyenDePaiementProperty());

	}

}
