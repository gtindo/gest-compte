package cm.compte.banquaire.vue;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import cm.compte.banquaire.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BarcharMotifController {
	@FXML
	private BarChart<String, Integer> barChart;

	@FXML
	private CategoryAxis xAxis ;

	private ObservableList<String> typeName = FXCollections.observableArrayList() ;

	private MainApp mainApp ;

	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp ;

		XYChart.Series<String, Integer> series = new XYChart.Series<>() ;

		series.getData().add(new XYChart.Data<>(typeName.get(0), this.mainApp.getNbOppMotifSi())) ;
		series.getData().add(new XYChart.Data<>(typeName.get(1), this.mainApp.getNbOppMotifSalaire())) ;
		series.getData().add(new XYChart.Data<>(typeName.get(2), this.mainApp.getNbOppMotifAlimentation())) ;
		series.getData().add(new XYChart.Data<>(typeName.get(3), this.mainApp.getNbOppMotifLoyer())) ;
		series.getData().add(new XYChart.Data<>(typeName.get(4), this.mainApp.getNbOppMotifDivers())) ;

		barChart.getData().add(series) ;
	}

	@FXML
	private void initialize(){
		String[] type = {"SI","Salaire", "Alimentation", "Loyer", "Divers"} ;
		this.typeName.addAll(type) ;
		xAxis.setCategories(typeName);
	}
}
