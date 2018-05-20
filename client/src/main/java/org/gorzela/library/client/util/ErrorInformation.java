package org.gorzela.library.client.util;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Optional;

@Component
public class ErrorInformation {

    public void showInformation(String title, String contentText ) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);

        DialogPane dialogPane = alert.getDialogPane();
        Scene scene = dialogPane.getScene();
        File file = new File("client/src/main/resources/org/gorzela/library/client/view/style.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + file.getAbsolutePath().replace("\\", "/"));
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent()) {
            ButtonType pressed = result.get();
            if(pressed == ButtonType.OK) {
                System.out.println("Jest o.k.");
                //return true;
            }
            else
                System.out.println("jrst false");
                //return false;
        }
    }

    public void showConfirm(String title, String contentText ) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);

        DialogPane dialogPane = alert.getDialogPane();
        Scene scene = dialogPane.getScene();
        File file = new File("client/src/main/resources/org/gorzela/library/client/view/style.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + file.getAbsolutePath().replace("\\", "/"));
        alert.showAndWait();
    }
}
