package no.ntnu.Idatx2001.ui.controller;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import no.ntnu.Idatx2001.model.PostalNumber;
import no.ntnu.Idatx2001.model.PostalNumberRegister;
import no.ntnu.Idatx2001.ui.view.MainWindow;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The main controller for the application. It handles the actions to be performed in the GUI.
 */
public class MainController{

    private final Logger logger;

    /**
     * An instance of MainController.
     */
    public MainController(){
        this.logger = Logger.getLogger(getClass().toString());
    }
    /**
     *  Exits the application and displays a confirmation dialog.
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
     * Displays a dialog to choose a .csv file to be read. Then it reads the document
     * and add them to the postalNumberRegister. Returns <code>True</code> if the
     * process is successful, and <code>False</code> if it fails.
     *
     * @param postalNumberRegister
     * @param parent
     * @return
     */
    public boolean doShowImportCSVDialog(PostalNumberRegister postalNumberRegister, MainWindow parent) {
        boolean importCheck = false;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open CSV File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(null);

        try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))){

            String row = "";
            while((row = reader.readLine()) != null){
                String[] data = row.split("\t");
                postalNumberRegister.setPostalNumbers(new PostalNumber(data[0],data[1],data[2],data[3],data[4]));
                parent.updateObservableList();
                importCheck = true;
            }
        }catch(IOException e){
            this.logger.log(Level.WARNING, e.getMessage());
        }
        return importCheck;
    }

    /**
     * The update status bar is called to update the statusBar
     * when CSV is imported
     */
    public void updateStatusBar(){

    }

    /**
     * Defines what to search for, which is postalCode, postalPlace
     * and municipality.
     *
     * @param postalNumber the postalNumber to search for
     * @param search       the search text
     * @return             the search results, based on postalCode, postalPlace
     *                     and municipality.
     */
    private boolean searchFindsOrder(PostalNumber postalNumber, String search){
        return (postalNumber.getPostalCode().toLowerCase().contains(search.toLowerCase())) ||
                (postalNumber.getPostalPlace().toLowerCase().contains(search.toLowerCase())) ||
                (postalNumber.getMunicipality().toLowerCase().contains(search.toLowerCase()));
    }

    /**
     * Filters a observable list to be shown to the user, based on the users input.
     *
     * @param list   the list to filter
     * @param search the search text
     * @return       the filtered list as a ObservableList
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
     * A about dialog to show creator, app, version and date of creation.
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
