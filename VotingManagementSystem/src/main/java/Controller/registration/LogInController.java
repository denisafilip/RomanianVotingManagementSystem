package Controller.registration;

import Controller.ParentController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Window;
import Model.user.User;

import java.io.IOException;

public class LogInController extends ParentController {
    @FXML private Label lblPassword;
    @FXML private Label lblEmail;
    @FXML private TextField txtEmail;
    @FXML private PasswordField passPassword;
    @FXML private Button btnRegister;
    @FXML private Button btnVote;

    public void goToElectionType(ActionEvent actionEvent) throws IOException {
        clearLabels();
        Window owner = btnVote.getScene().getWindow();
        if (txtEmail.getText().isEmpty()) {
            lblEmail.setText("Vă rog să vă introduceți email-ul.");
            return;
        }
        if (passPassword.getText().isEmpty()) {
            lblPassword.setText("Vă rog să vă introduceți parola.");
            return;
        }
        User u = new User(txtEmail.getText(), passPassword.getText());
        if (database.isUserInDatabaseForLogIn(u)) {
            CNP = database.getUserCNP(u);
            changeScene(actionEvent, "/electionType.fxml", "Alegeri");
        } else {
            showAlert(Alert.AlertType.ERROR, owner, "Conectare nereușită", "Acest cont nu a fost înregistrat. Verificați credențialele de conectare sau apăsați pe butonul ”Înregistrare” pentru a vă crea un cont.");
        }
    }

    public void goToRegistration(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "/registration.fxml", "Înregistrare");
    }

    public void clearLabels() {
        lblEmail.setText("");
        lblPassword.setText("");
    }
}
