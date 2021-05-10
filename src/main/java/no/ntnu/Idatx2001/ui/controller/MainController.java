package no.ntnu.Idatx2001.ui.controller;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import no.ntnu.Idatx2001.model.PostalNumber;
import no.ntnu.Idatx2001.ui.view.MainWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainController{

    /**
     *
     */
    public void doExitApp(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation window");
        alert.setHeaderText("Exit Application ?");
        alert.setContentText("Are you sure you want to exit this app?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent()){
            if(result.get() == ButtonType.OK){
                Platform.exit();
            } else {
                //nothing
            }
        }
    }

    /**
     *
     * @param postalNumber
     * @param search
     * @return
     */
    private boolean searchFindsOrder(PostalNumber postalNumber, String search){
        return (postalNumber.getPostalCode().toLowerCase().contains(search.toLowerCase())) ||
                (postalNumber.getPostalPlace().toLowerCase().contains(search.toLowerCase())) ||
                (postalNumber.getMunicipality().toLowerCase().contains(search.toLowerCase()));
    }

    /**
     *
     * @param list
     * @param search
     * @return
     */
    public ObservableList<PostalNumber> filterList(List<PostalNumber> list, String search){
        List<PostalNumber> filtered = new ArrayList<>();
        for (PostalNumber postalNumber : list){
            if(searchFindsOrder(postalNumber, search)) filtered.add(postalNumber);
        }
        return FXCollections.observableArrayList(filtered);
    }

    //------------------------------------
    //  DIALOGS
    //------------------------------------

    /**
     *
     */
    public void doShowAbout(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information - About");
        alert.setHeaderText("Post Number Register");
        alert.setContentText("A app made by:\n"
                + "Jonas Tosse\n"
                + "v0.1  10.05.2021");

        alert.showAndWait();
    }
}
