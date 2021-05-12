package no.ntnu.idatx2001.ui.factory;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import no.ntnu.idatx2001.model.PostalNumber;
import no.ntnu.idatx2001.model.PostalRegister;
import no.ntnu.idatx2001.ui.controller.MainController;
import no.ntnu.idatx2001.ui.view.MainWindow;
import java.util.logging.Logger;

/**
 * The GuiFactory has the responsibility to create Nodes for the MainWindow.
 */
public class GUIFactory {
    private Logger logger;

    /**
     * Creates an instance of the GuiFactory.
     */
    public GUIFactory(){this.logger = Logger.getLogger(getClass().toString());}

    /**
     * Creates a menus of type Node, to be sent to MainWindow.
     *
     * @param controller            a link to the MainController
     * @param tableView             the TableView to do operations in
     * @param postalRegister  the register to add, edit and remove
     * @param mainWindow            a link to the MainWindow
     * @return                      the toolbar as a Node
     */
    public Node createMenus(MainController controller, TableView tableView, PostalRegister postalRegister, MainWindow mainWindow){
        Menu menuFile = new Menu("File");
        MenuItem openFile = new MenuItem("Import File");
        openFile.setOnAction(ActionEvent -> controller.doShowImportCSVDialog(postalRegister, mainWindow));

        MenuItem exitApp = new MenuItem("Exit");
        exitApp.setOnAction(ActionEvent -> controller.doExitApp());

        menuFile.getItems().add(openFile);
        menuFile.getItems().add(new SeparatorMenuItem());
        menuFile.getItems().add(exitApp);

        Menu menuEdit = new Menu("Edit");

        MenuItem seePN = new MenuItem("See selected Item");
        seePN.setOnAction(ActionEvent -> {
            PostalNumber selectedPostal = (PostalNumber) tableView.getSelectionModel().getSelectedItem();
            controller.doShowInfo(selectedPostal);
        });

        menuEdit.getItems().addAll(seePN);

        Menu menuHelp = new Menu("Help");
        MenuItem about = new MenuItem("About");
        MenuItem search = new MenuItem("search info");
        about.setOnAction(event -> controller.doShowAbout());
        menuHelp.getItems().add(about);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menuFile, menuEdit, menuHelp);
        return menuBar;
    }

    /**
     * Creates a toolbar of type Node, to be sent to MainWindow.
     *
     * @param controller            a link to the MainController
     * @param tableView             the TableView to do operations in
     * @param postalRegister  the register to add, edit and remove
     * @param mainWindow            a link to the MainWindow
     * @return                      the toolbar as a Node
     */
    public Node createToolBar(MainController controller, TableView tableView, PostalRegister postalRegister, MainWindow mainWindow){

        TextField searchText = new TextField();
        searchText.setPromptText("Search");
        searchText.textProperty().addListener((observable, oldValue, newValue) -> tableView.setItems
                (controller.filterList(postalRegister.getPostalNumbers(),newValue)));

        Button infoButton = new Button();
        infoButton.setMinWidth(35);
        ImageView infoImgView = new ImageView(new Image(getClass().getResource("/pictures/info.png").toExternalForm()));
        infoButton.setGraphic(infoImgView);
        infoImgView.setFitHeight(25);
        infoImgView.setFitWidth(25);

        infoButton.setOnAction(ActionEvent -> {
            PostalNumber selectedPostal = (PostalNumber) tableView.getSelectionModel().getSelectedItem();
            controller.doShowInfo(selectedPostal);
        });

        ImageView searchImageView = new ImageView();
        Image searchImage = new Image(getClass().getResource("/pictures/search.png").toExternalForm());
        searchImageView.setImage(searchImage);
        searchImageView.setFitWidth(25);
        searchImageView.setFitHeight(25);

        BorderPane toolBar = new BorderPane();
        toolBar.setLeft(infoButton);
        toolBar.setRight(new HBox(searchText,searchImageView));
        return toolBar;
    }

    /**
     * Creates centreContent of type Node, to be sent to MainWindow.
     *
     * @param controller    a link to the MainController
     * @param tableView     the TableView to do operations in
     * @param mainWindow    a link to the MainWindow
     * @return              the scrollPane as a Node
     */
    public Node createCentreContent(MainController controller, TableView tableView, MainWindow mainWindow){
        TableColumn<PostalNumber, String> postalCodeColumn = new TableColumn<>("Postal code");
        postalCodeColumn.setMinWidth(100);
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        TableColumn<PostalNumber, String> postalPlaceColumn = new TableColumn<>("Postal place");
        postalPlaceColumn.setMinWidth(200);
        postalPlaceColumn.setCellValueFactory(new PropertyValueFactory<>("postalPlace"));

        TableColumn<PostalNumber, String> municipalityColumn = new TableColumn<>("Municipality");
        municipalityColumn.setMinWidth(200);
        municipalityColumn.setCellValueFactory(new PropertyValueFactory<>("municipality"));

        TableColumn<PostalNumber, String> municipalityCodeColumn = new TableColumn<>("Municipality code");
        municipalityCodeColumn.setMinWidth(100);
        municipalityCodeColumn.setCellValueFactory(new PropertyValueFactory<>("municipalityCode"));

        TableColumn<PostalNumber, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setMinWidth(100);
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));


        tableView.setItems(mainWindow.getPostalObservableList());
        tableView.getColumns().addAll(postalCodeColumn, postalPlaceColumn, municipalityColumn, municipalityCodeColumn, categoryColumn);


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(tableView);

        return scrollPane;
    }

    /**
     * Creates a statusBar of type Node, to be sent to the MainWindow.
     *
     * @return the statusBar as a Node
     */
    public Node createStatusBar(){
        HBox statusBar = new HBox();
        statusBar.setStyle("-fx-background-color: '#1';");
        statusBar.getChildren().add(new Text("Status: OK"));
        return statusBar;
    }
    public Node newStatusBar(){
        HBox statusbar = new HBox();
        statusbar.setStyle("-fx-background-color: '#1';");
        statusbar.getChildren().add(new Text("Status: import successful"));
        return statusbar;
    }

}

