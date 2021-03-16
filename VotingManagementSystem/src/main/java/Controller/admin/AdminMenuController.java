package Controller.admin;

import Controller.ParentController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AdminMenuController extends ParentController {
    @FXML private Button btnReferendum;
    @FXML private Button btnEuro;
    @FXML private Button btnLocal;
    @FXML private Button btnPresidential;
    @FXML private Button btnParliament;
    @FXML private Button btnBack;

    public void goToParliament(ActionEvent actionEvent) throws IOException {
        buttonPressed = getDeputiesParliamentElection().getType();
        changeScene(actionEvent, "/admin.fxml", "Administrator");

    }

    public void goToEuropeanParliament(ActionEvent actionEvent) throws IOException {
        buttonPressed = getEuropeanParliamentElection().getType();
        changeScene(actionEvent, "/admin.fxml", "Administrator");

    }

    public void goToPresidential(ActionEvent actionEvent) throws IOException {
        buttonPressed = getPresidentialElection().getType();
        changeScene(actionEvent, "/admin.fxml", "Administrator");

    }

    public void goToLocal(ActionEvent actionEvent) throws IOException {
        buttonPressed = getLocalElection().getType();
        changeScene(actionEvent, "/admin.fxml", "Administrator");

    }

    public void goToReferendum(ActionEvent actionEvent) throws IOException {
        buttonPressed = getFirstReferendum().getType();
        changeScene(actionEvent, "/admin.fxml", "Administrator");

    }

    public void goBackToElectionTypes(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "/electionType.fxml", "Alegeri");
    }
}
