package no.ntnu.idatx2001.ui.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import no.ntnu.idatx2001.model.PostalNumber;
import no.ntnu.idatx2001.model.PostalRegister;
import no.ntnu.idatx2001.model.RemoveException;
import no.ntnu.idatx2001.ui.controller.MainController;
import no.ntnu.idatx2001.ui.factory.GUIFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The mainWindow of the application.
 */
public class MainWindow extends Application {
    private MainController mainController;
    private PostalRegister pNRegister;
    private ObservableList<PostalNumber> postalObservableList;
    private TableView<PostalNumber> tableView;
    private Logger logger;


    @Override
    public void init(){
        this.mainController = new MainController();
        this.pNRegister = new PostalRegister();
        this.tableView = new TableView<>();
        fillAndRemoveRegisterWithDummies();
    }

    /**
     * The start-method is called by the JavaFX platform upon starting the
     * JavaFX-platform. The method is abstract and must be overridden by any
     * subclass of Application. The main window is setup in this method,
     * including menus, toolboxes etc.
     *
     * @param primaryStage The main stage making up the main window.
     */
    @Override
    public void start(Stage primaryStage) {
        this.logger = Logger.getLogger(getClass().toString());
        var guiFactory = new GUIFactory();

        var root = new BorderPane();
        var topContainer = new VBox();

        topContainer.getChildren().add(guiFactory.createMenus(this.mainController, this.tableView, this.pNRegister, this));
        topContainer.getChildren().add(guiFactory.createToolBar(this.mainController, this.tableView, this.pNRegister));

        root.setTop(topContainer);
        root.setCenter(guiFactory.createCentreContent(this.tableView, this));
        root.setBottom(guiFactory.createStatusBar());

        var scene = new Scene(root, 700, 700);
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(700);
        primaryStage.setTitle("PostNumber APP");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(windowEvent -> mainController.doExitApp());

    }

    /**
     * Returns an ObservableList holding the postalNumbers to display.
     *
     * @return an ObservableList holding the postalNumbers to display
     */
    public ObservableList<PostalNumber> getPostalObservableList() {
        postalObservableList
                = FXCollections.observableArrayList(this.pNRegister.getPostalNumbers());
        return postalObservableList;
    }

    /**
     * Updates the ObservableArray wrapper with the content of the postalNumberRegister.
     */
    public void updateObservableList(){
        this.postalObservableList.setAll(this.pNRegister.getPostalNumbers());
    }

    /**
     * Fills register with dummies, then removes them.
     *
     * I will say myself that having this method in the MainWindow is a bad code design.
     * This method both adds, removes and catches exceptions for both, this would be better
     * suited to have in mainController or GUIFactory.
     *
     * I have this method here, simply because it was more convenient at the time.
     */
    public void fillAndRemoveRegisterWithDummies(){
        try {
            var person1 = new PostalNumber("6490", "Eide", "Hustadvika", "2819", "Å");
            var person2 = new PostalNumber("6463", "Tunevik", "Molde", "2125", "G");
            var person3 = new PostalNumber("6008", "Vika", "Ålesund", "1234", "Å");
            var person4 = new PostalNumber("6491", "Oblevai", "Oslo", "3021", "G");
            var person5 = new PostalNumber("6494", "Kjørsvika", "Bergen", "2615", "H");
            this.pNRegister.setPostalNumbers(person1);
            this.pNRegister.setPostalNumbers(person2);
            this.pNRegister.setPostalNumbers(person3);
            this.pNRegister.setPostalNumbers(person4);
            this.pNRegister.setPostalNumbers(person5);
            this.pNRegister.removePostal(person3);
            this.pNRegister.removePostal(person4);
            this.pNRegister.removePostal(person5);

        } catch (IllegalArgumentException i){
            this.logger.log(Level.WARNING,"IllegalArgumentException with failure because a field is empty");
        } catch(RemoveException e){
            this.logger.log(Level.WARNING,"RemoveException because failure to delete PostalNumber");
        }
    }
}
