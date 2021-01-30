package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class DrzavaController {
    public TextField fieldNaziv;
    public ChoiceBox<Grad> choiceGrad;
    public ChoiceBox<Grad> choiceGradNajveci;
    private Drzava drzava;
    private ObservableList<Grad> listGradovi;
    public RadioButton radioIsti,radioDrugi;

    public DrzavaController(Drzava drzava, ArrayList<Grad> gradovi) {
        this.drzava = drzava;
        listGradovi = FXCollections.observableArrayList(gradovi);
    }

    @FXML
    public void initialize() {
        choiceGrad.setItems(listGradovi);
        choiceGradNajveci.setItems(listGradovi);
        //choiceGradNajveci.setDisable(true);
        if (drzava != null) {
            fieldNaziv.setText(drzava.getNaziv());
            choiceGrad.getSelectionModel().select(drzava.getGlavniGrad());
            choiceGradNajveci.getSelectionModel().select(drzava.getNajveciGrad());

            if (drzava.getGlavniGrad().getId() == drzava.getNajveciGrad().getId()){
                radioIsti.setSelected(true);
                choiceGradNajveci.setDisable(true);
            }
            else{
                radioDrugi.setSelected(true);
                choiceGradNajveci.setDisable(false);
            }

        } else {
            choiceGrad.getSelectionModel().selectFirst();
            choiceGradNajveci.getSelectionModel().selectFirst();
            radioIsti.setSelected(true);
            choiceGradNajveci.setDisable(true);
        }
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void clickOk(ActionEvent actionEvent) {
        boolean sveOk = true;

        if (fieldNaziv.getText().trim().isEmpty()) {
            fieldNaziv.getStyleClass().removeAll("poljeIspravno");
            fieldNaziv.getStyleClass().add("poljeNijeIspravno");
            sveOk = false;
        } else {
            fieldNaziv.getStyleClass().removeAll("poljeNijeIspravno");
            fieldNaziv.getStyleClass().add("poljeIspravno");
        }

        if (!sveOk) return;

        if (drzava == null) drzava = new Drzava();
        drzava.setNaziv(fieldNaziv.getText());
        drzava.setGlavniGrad(choiceGrad.getSelectionModel().getSelectedItem());
        if(radioIsti.isSelected())drzava.setNajveciGrad(choiceGrad.getSelectionModel().getSelectedItem());
        else drzava.setNajveciGrad(choiceGradNajveci.getSelectionModel().getSelectedItem());


        closeWindow();
    }

    public void clickCancel(ActionEvent actionEvent) {
        drzava = null;
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) fieldNaziv.getScene().getWindow();
        stage.close();
    }

    public void onemoguciChoice(){
        choiceGradNajveci.setDisable(true);
    }

    public void omoguciChoice(){
        choiceGradNajveci.setDisable(false);
    }
}
