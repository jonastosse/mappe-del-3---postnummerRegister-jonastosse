package no.ntnu.Idatx2001.ui.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import no.ntnu.Idatx2001.model.PostalNumber;
import no.ntnu.Idatx2001.model.PostalNumberRegister;
import no.ntnu.Idatx2001.ui.controller.MainController;
import no.ntnu.Idatx2001.ui.factory.GUIFactory;

/**
 *
 */
public class MainWindow extends Application {
    private MainController mainController;
    private PostalNumberRegister pNRegister;
    private ObservableList<PostalNumber> postalObservableList;
    private TableView<PostalNumber> tableView;

    /**
     *
     */
    public void init(){
        this.mainController = new MainController();
        this.pNRegister = new PostalNumberRegister();
        this.tableView = new TableView<>();
        pNRegister.fillRegisterWithDummies();
    }

    /**
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        GUIFactory guiFactory = new GUIFactory();

        BorderPane root = new BorderPane();
        VBox topContainer = new VBox();

        topContainer.getChildren().add(guiFactory.createMenus(this.mainController, this.tableView, this.pNRegister, this));
        topContainer.getChildren().add(guiFactory.createToolBar(this.mainController, this.tableView, this.pNRegister));

        root.setTop(topContainer);
        root.setCenter(guiFactory.createCentreContent(this.mainController, this.tableView, this));
        //root.setBottom();

        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(700);
        primaryStage.setTitle("PostalRegister");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     *
     * @return
     */
    public ObservableList<PostalNumber> getPostalObservableList() {
        postalObservableList
                = FXCollections.observableArrayList(this.pNRegister.getPostalNumbers());
        return postalObservableList;
    }

    /**
     *
     */
    public void updateObservableList(){
        this.postalObservableList.setAll(this.pNRegister.getPostalNumbers());
    }
}
