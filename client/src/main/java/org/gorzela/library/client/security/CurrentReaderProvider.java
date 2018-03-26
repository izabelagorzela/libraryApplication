package org.gorzela.library.client.security;

import de.felixroske.jfxsupport.AbstractFxmlView;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.*;
import org.gorzela.library.client.view.LoginFormView;
import org.gorzela.library.common.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CurrentReaderProvider {

    @Autowired
    private LoginFormView loginWindow;

    private Reader currentReader;
    private int currentReaderSetupAccount;



    public Reader getCurrentReaderState () {

        if(currentReader == null) {

            System.out.println("Jestem w get");

            Stage stage = new Stage();
            Scene scene = (loginWindow.getView().getScene() == null ? new Scene(loginWindow.getView()) : loginWindow.getView().getScene());
            stage.setScene(scene);
            stage.setTitle("Logowanie");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            System.out.println("po show");
        }
        System.out.println(currentReader);
        return currentReader;
    }

    //po wylogowaniu
    public void unsetCurrentReader() {

        currentReader = null;
    }



}
