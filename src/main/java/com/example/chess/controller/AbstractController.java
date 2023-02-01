package com.example.chess.controller;

import com.example.chess.model.Player;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.util.ArrayList;

/**
 * AbstractController is an abstract class that implements PublisherInterface.
 * It provides an abstract implementation of Publisher-Subscriber design pattern.
 */
public abstract class AbstractController implements PublisherInterface<Boolean> {

    private Stage stage = null;

    private ArrayList<SubscriberInterface<Boolean>> list = new ArrayList<>();

    /**
     * The loadFxmlFile method is used to load a FXML file and return its controller.
     * It also sets up the stage of the controller.
     *
     * @param path the path to the FXML file
     * @param title the title of the stage
     * @param owner the owner window of the stage
     * @param classOfController the class of the controller
     * @param <T> the type parameter that extends AbstractController
     * @return the controller of the FXML file
     * @throws IOException if an I/O error occurs
     */
    public <T extends AbstractController> T loadFxmlFile(String path, String title, Window owner, Class<T> classOfController) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AbstractController.class.getResource(path));
        Parent scene = fxmlLoader.load();

        T controller = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);

        stage.setScene(new Scene(scene));
        stage.setTitle(title);
        scene.getScene().setFill(Color.TRANSPARENT);

        controller.setStage(stage);

        if (owner != null) {
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(owner);
        }


        stage.show();

        return controller;
    }

    /**
     * The setStage method is used to set the stage of the controller.
     *
     * @param s the stage to be set
     */
    public void setStage(Stage s) {
        this.stage = s;
    }

    /**
     * The closeWindow method is used to close the stage of the controller.
     */
    public void closeWindow() {
        if (stage != null) {
            stage.close();
        }
    }

    @Override
    public void addSubscriber(SubscriberInterface<Boolean> sub) {
        list.add(sub);
    }

    @Override
    public void removeSubscriber(SubscriberInterface<Boolean> sub) {
        list.remove(sub);
    }

    @Override
    public void notifyAllSubscribers(Boolean what) {
        for (SubscriberInterface<Boolean> sub : list) {
            sub.notify(what);
        }
    }
}
