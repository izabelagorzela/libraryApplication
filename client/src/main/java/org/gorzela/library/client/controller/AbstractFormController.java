package org.gorzela.library.client.controller;

import de.felixroske.jfxsupport.AbstractFxmlView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.net.URISyntaxException;

public  abstract class AbstractFormController {

    public void createAndShowWindow(AbstractFxmlView v, AbstractFormController form, String title){

        Stage stage = new Stage();
        Scene scene = (v.getView().getScene() == null ? new Scene(v.getView()) : v.getView().getScene());
        stage.setScene(scene);
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            if(form.setWindow() == false)
                return;
        } catch (URISyntaxException e) {

            e.printStackTrace();
        }
        stage.show();

    }

    public void closeWindow(Button actionButton) {

        Stage stage = (Stage) actionButton.getScene().getWindow();
        stage.close();
    }

    public abstract boolean setWindow() throws URISyntaxException;
}
