package Controller.elections;

import Controller.ParentController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ElectionTypeController extends ParentController implements Initializable {
    @FXML private Button btnAdmin;
    @FXML
    private Button btnReferendum;
    @FXML
    private Button btnParliament;
    @FXML
    private Button btnEuro;
    @FXML
    private Button btnLocal;
    @FXML
    private Button btnPresidential;
    @FXML
    private Button btnBack;

    public void goBackToRegistration(ActionEvent event) throws IOException {
        changeScene(event, "/registration.fxml", "Înregistrare");
    }

    public void goToPresidential(ActionEvent event) throws IOException {
      changeScene(event, "/presidential.fxml", "Alegeri prezidențiale");
    }

    public void goToEuropeanParliament(ActionEvent event) throws IOException {
        changeScene(event, "/europeanParliament.fxml", "Alegeri parlament european");
    }

    public void goToParliament(ActionEvent event) throws IOException {
        changeScene(event, "/parliament.fxml", "Alegeri parlamentare");
    }

    public void goToLocal(ActionEvent event) throws IOException {
        changeScene(event, "/local.fxml", "Alegeri locale");
    }

    public void goToReferendum(ActionEvent event) throws IOException {
        changeScene(event, "/referendum.fxml", "Referendum");
    }

    public void goToAdminMenu(ActionEvent event) throws IOException {
        changeScene(event, "/adminMenu.fxml", "Meniu administrator");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Boolean isUserAdmin = database.checkIfUserIsAdmin(CNP);
        if (!isUserAdmin) {
            btnAdmin.setVisible(false);
            btnAdmin.setDisable(true);
        }
    }


}
