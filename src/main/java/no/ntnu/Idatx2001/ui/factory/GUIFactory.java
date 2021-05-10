package no.ntnu.Idatx2001.ui.factory;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import no.ntnu.Idatx2001.model.PostalNumber;
import no.ntnu.Idatx2001.model.PostalNumberRegister;
import no.ntnu.Idatx2001.ui.controller.MainController;
import no.ntnu.Idatx2001.ui.view.MainWindow;

/**
 *
 */
public class GUIFactory {

    /**
     *
     */
    public GUIFactory(){}

    /**
     *
     * @param controller
     * @param tableView
     * @param postalNumberRegister
     * @param mainWindow
     * @return
     */
    public Node createMenus(MainController controller, TableView tableView, PostalNumberRegister postalNumberRegister, MainWindow mainWindow){
        Menu menuFile = new Menu("File");
        MenuItem openFile = new MenuItem("Import File");
        openFile.setOnAction(ActionEvent -> controller.doShowImportCSVDialog(postalNumberRegister, mainWindow));

        MenuItem exportFile = new MenuItem("Export File");
        //TODO

        MenuItem exitApp = new MenuItem("Exit");
        exitApp.setOnAction(ActionEvent -> controller.doExitApp());

        menuFile.getItems().addAll(openFile, exportFile);
        menuFile.getItems().addAll(new SeparatorMenuItem());
        menuFile.getItems().add(exitApp);

        Menu menuEdit = new Menu("Edit");

        MenuItem addPN = new MenuItem("Add new Postal Address");
        //TODO
        MenuItem editPN = new MenuItem("Edit Postal Address");
        //TODO
        MenuItem removePN = new MenuItem("Remove Postal Address");
        //TODO

        menuEdit.getItems().addAll(addPN, editPN, removePN);

        Menu menuHelp = new Menu("Help");
        MenuItem about = new MenuItem("About");
        about.setOnAction(event -> controller.doShowAbout());
        menuHelp.getItems().add(about);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menuFile, menuEdit, menuHelp);
        return menuBar;
    }

    /**
     *
     * @param controller
     * @param tableView
     * @param postalNumberRegister
     * @return
     */
    public Node createToolBar(MainController controller, TableView tableView, PostalNumberRegister postalNumberRegister){

        TextField searchText = new TextField();
        searchText.setPromptText("Search for postal code or postal place");
        searchText.textProperty().addListener((observable, oldValue, newValue) -> tableView.setItems
                (controller.filterList(postalNumberRegister.getPostalNumbers(),newValue)));

        Button addButton = new Button();
        addButton.setText("ADD");

        Button editButton = new Button();
        editButton.setText("EDIT");

        Button deleteButton = new Button();
        deleteButton.setText("DELETE");

        BorderPane toolBar = new BorderPane();
        ToolBar vBox = new ToolBar();

        vBox.getItems().addAll(addButton, editButton, deleteButton);

        toolBar.setLeft(vBox);
        toolBar.setRight(searchText);
        return toolBar;
    }

    /**
     *
     * @param controller
     * @param tableView
     * @param mainWindow
     * @return
     */
    public Node createCentreContent(MainController controller, TableView tableView, MainWindow mainWindow){
        TableColumn<PostalNumber, String> postalCodeColumn = new TableColumn<>("Postal code");
        postalCodeColumn.setMinWidth(200);
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        TableColumn<PostalNumber, String> postalPlaceColumn = new TableColumn<>("Postal place");
        postalPlaceColumn.setMinWidth(300);
        postalPlaceColumn.setCellValueFactory(new PropertyValueFactory<>("postalPlace"));

        TableColumn<PostalNumber, String> municipalityColumn = new TableColumn<>("Municipality");
        municipalityColumn.setMinWidth(300);
        municipalityColumn.setCellValueFactory(new PropertyValueFactory<>("municipality"));


        tableView.setItems(mainWindow.getPostalObservableList());
        tableView.getColumns().addAll(postalCodeColumn, postalPlaceColumn, municipalityColumn);


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(tableView);

        return scrollPane;
    }

    /**
     *
     * @return
     */
    public Node createStatusBar(){
        HBox statusbar = new HBox();
        statusbar.setStyle("-fx-background-color: '#1';");
        //if()
        return statusbar;
    }

}

