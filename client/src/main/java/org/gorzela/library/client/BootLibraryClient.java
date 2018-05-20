package org.gorzela.library.client;


import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import org.gorzela.library.client.view.MainFormView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "org.gorzela.library.common")
public class BootLibraryClient extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
        launchApp(BootLibraryClient.class, MainFormView.class, args);
    }

}
