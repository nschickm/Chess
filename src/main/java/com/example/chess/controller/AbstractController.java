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

public abstract class AbstractController implements PublisherInterface<Boolean> {

    private Stage stage = null;

    private ArrayList<SubscriberInterface<Boolean>> list = new ArrayList<>();

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

    public void setStage(Stage s) {
        this.stage = s;
    }

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
