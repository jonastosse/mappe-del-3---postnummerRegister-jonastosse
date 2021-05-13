package no.ntnu.idatx2001.ui.controller;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import no.ntnu.idatx2001.model.PostalNumber;
import no.ntnu.idatx2001.model.PostalRegister;
import no.ntnu.idatx2001.model.RemoveException;
import no.ntnu.idatx2001.ui.view.MainWindow;
import java.io.BufferedReader;
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
public class MainController {

    private final Logger logger;

    /**
     * An instance of MainController.
     */
    public MainController() {
        this.logger = Logger.getLogger(getClass().toString());
    }

    /**
     * Exits the application and displays a confirmation dialog.
     */
    public void doExitApp() {
        var alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation window");
        alert.setHeaderText("Exit Application ?");
        alert.setContentText("Are you sure you want to exit this app?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
                Platform.exit();
        }
    }

    /**
     * Defines what to search for, which is postalCode, postalPlace
     * and municipality.
     *
     * @param postalNumber the postalNumber to search for
     * @param search       the search text
     * @return the search results, based on postalCode, postalPlace
     * and municipality.
     */
    private boolean searchFindsOrder(PostalNumber postalNumber, String search) {
        return (postalNumber.getPostalCode().contains(search)) ||
                (postalNumber.getPostalPlace().toLowerCase().contains(search.toLowerCase())) ||
                (postalNumber.getMunicipality().toLowerCase().contains(search.toLowerCase()));
    }

    /**
     * Filters a observable list to be shown to the user, based on the users input.
     *
     * @param list   the list to filter
     * @param search the search text
     * @return the filtered list as a ObservableList
     */
    public ObservableList<PostalNumber> filterList(List<PostalNumber> list, String search) {
        List<PostalNumber> filtered = new ArrayList<>();
        for (PostalNumber postalNumber : list) {
            if (searchFindsOrder(postalNumber, search)) filtered.add(postalNumber);
        }
        return FXCollections.observableArrayList(filtered);
    }

    /**
     * Deletes a postal if user pressed OK in showDeleteConfirmationDialog. Then updates ObservableList.
     * It catches a RemoveException if the PostalNumber cannot be removed.
     *
     * @param postalRegister The postalRegister to delete a PostalNumber from
     * @param postalNumber   The postalNumber to remove
     * @param parent         The MainWindow to update
     */
    public void doShowDeletePostal(PostalRegister postalRegister, PostalNumber postalNumber, MainWindow parent){
        if(showDeleteConfirmationDialog()){
            try {
                postalRegister.removePostal(postalNumber);
                parent.updateObservableList();
            } catch (RemoveException e){
                this.logger.log(Level.WARNING,"Could not remove postalNumber, because it doesn't exist");
                showRemoveFailure();
            }
        }
    }

    /**
     * A method to decide if the user have selected a postal, then delete that postal.
     * If the user have not selected postal, show selectItemDialog.
     *
     * @param postalRegister
     * @param postalNumber
     * @param parent
     */
    public void doShowDelete(PostalRegister postalRegister, PostalNumber postalNumber, MainWindow parent){
        if (postalNumber == null){
            doShowSelectItemDialog();
        } else {
            doShowDeletePostal(postalRegister, postalNumber, parent);
        }
    }


    /**
     * A method to decide if the user have selected a postal, then show info about that postal.
     * If the user have not selected postal, show selectItemDialog.
     *
     * @param postalNumber the postalNumber to be shown info about
     */
    public void doShowInfo(PostalNumber postalNumber) {
        if (postalNumber == null) {
            doShowSelectItemDialog();
        } else {
            postalNumberDialog(postalNumber);
        }
    }

    //------------------------------------
    //  DIALOGS
    //------------------------------------

    /**
     * Displays a delete confirmation dialog. If the user confirms the delete,
     * <code>true</code> is returned.
     *
     * @return <code>true</code> if the user confirms the delete
     */
    public boolean showDeleteConfirmationDialog() {
        boolean deleteConfirmed = false;

        var alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Delete confirmation");
        alert.setContentText("Are you sure you want to delete this item?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            deleteConfirmed = (result.get() == ButtonType.OK);
        }
        return deleteConfirmed;
    }

    /**
     * An alert to show if CSV fails to import.
     */
    public void showCSVFailure(){
        var alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Unsuccessful");
        alert.setHeaderText("Unsuccessful import");
        alert.setContentText("The File import failed because of a error in the file you imported.\n " + "\n"
                + "Please check for a empty field in your file");

        alert.showAndWait();
    }

    /**
     * An alert to show error when a removal is unsuccessful.
     */
    public void showRemoveFailure(){
        var alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Unsuccessful");
        alert.setHeaderText("Unsuccessful remove");
        alert.setContentText("Could not remove postal place, because it doesn't exist");

        alert.showAndWait();
    }

    /**
     * Displays a dialog to choose a .csv file to be read. Then it reads the document
     * and add them to the postalNumberRegister. Returns <code>True</code> if the
     * process is successful, and <code>False</code> if it fails.
     *
     * @param postalRegister  the postalRegister to add postalNumbers
     * @param parent          a link to the MainWindow
     */
    public void doShowImportCSVDialog(PostalRegister postalRegister, MainWindow parent) {
        var fileChooser = new FileChooser();
        fileChooser.setTitle("Open CSV File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV files", "*.csv"));
        var selectedFile = fileChooser.showOpenDialog(null);

        try (var reader = new BufferedReader(new FileReader(selectedFile))) {
            var row = "";
            while ((row = reader.readLine()) != null) {
                String[] data = row.split("\t");
                postalRegister.setPostalNumbers(new PostalNumber(data[0], data[1], data[3], data[2], data[4]));
                parent.updateObservableList();
            }
        } catch (IOException e) {
            this.logger.log(Level.WARNING, "IOException with failure to read the file");
            showCSVFailure();

        } catch (ArrayIndexOutOfBoundsException i){
            this.logger.log(Level.WARNING, "The index is either negative or greater than or equal to the size of the array");
            showCSVFailure();

        } catch (IllegalArgumentException u){
            this.logger.log(Level.WARNING, "IllegalArgumentException because of a empty string");
            showCSVFailure();
        }
    }

    /**
     * A about dialog to show creator, app, version and date of creation.
     */
    public void doShowAbout() {
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information - About");
        alert.setHeaderText("Post Number Register");
        alert.setContentText("A app made by:\n"
                + "Jonas Tosse\n"
                + "v0.1  10.05.2021");

        alert.showAndWait();
    }

    /**
     * A about dialog to show creator, app, version and date of creation.
     */
    public void doShowSearchInfo() {
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information - Search");
        alert.setHeaderText("You can search for both PostalCode, PostalName and Municipality");
        alert.setContentText("In this application you can search for postal code, postal name and municipality. \n"
                + "And you don't even need to press anything, to search!\n"
                + "Just begin to write and your hits will show");

        alert.showAndWait();
    }

    /**
     * A warning dialog that shows that the user must select an
     * item from the table.
     */
    public void doShowSelectItemDialog() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Info");
        alert.setHeaderText("Postal Numbers");
        alert.setContentText("No postal number is selected.\n"
                + "Please select a item");

        alert.showAndWait();
    }

    /**
     * A postalNumber dialog to show info about a postalNumber. Fields will be a non editable,
     * except the comment field. This is a editable field for the actual user alone.
     *
     * @param postalNumber the postalNumber to be shown
     */
    public void postalNumberDialog(PostalNumber postalNumber) {
        var postalNumberDialog = new Dialog<>();

            postalNumberDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
            var grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            var pCode = new TextField();
            pCode.setEditable(false);
            pCode.setText(postalNumber.getPostalCode());
            pCode.setMaxWidth(50);

            var pPlace = new TextField();
            pPlace.setEditable(false);
            pPlace.setText(postalNumber.getPostalPlace());
            pPlace.setMaxWidth(150);

            var municipality = new TextField();
            municipality.setEditable(false);
            municipality.setText(postalNumber.getMunicipality());
            municipality.setMaxWidth(150);

            var mCode = new TextField();
            mCode.setEditable(false);
            mCode.setText(postalNumber.getMunicipalityCode());
            mCode.setMaxWidth(50);

            var category = new TextField();
            category.setEditable(false);
            category.setText(postalNumber.getCategory());
            category.setMaxWidth(25);

            var additionalComment = new TextArea();
            additionalComment.setPromptText("Comment");
            additionalComment.setEditable(true);
            additionalComment.setText(postalNumber.getComment());
            additionalComment.setWrapText(true);

            grid.add(new Label("Postal Code:"), 0, 0);
            grid.add(pCode, 1, 0);

            grid.add(new Label("Postal Name:"), 2, 0);
            grid.add(pPlace, 3, 0);

            grid.add(new Label("Municipality:"), 0, 1);
            grid.add(municipality, 1, 1);

            grid.add(new Label("Municipality Code:"), 2, 1);
            grid.add(mCode, 3, 1);

            grid.add(new Label("Category:"), 0, 2);
            grid.add(category, 1, 2);

            grid.add(new Label("Comment:"), 0, 3);
            grid.add(additionalComment, 1 , 3);
            additionalComment.setMaxSize(300, 200);

            postalNumberDialog.getDialogPane().setContent(grid);
            postalNumberDialog.showAndWait();
            postalNumber.setComment(additionalComment.getText());
    }
}