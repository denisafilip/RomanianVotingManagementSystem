package Controller.registration;


import Controller.ParentController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Window;
import Model.user.User;

import java.io.IOException;

public class RegistrationController extends ParentController {

    @FXML private Label lblFirstName;
    @FXML private Label lblLastName;
    @FXML private Label lblEmail;
    @FXML private Label lblPassword;
    @FXML private Label lblConfirmPassword;
    @FXML private Label lblBirthday;
    @FXML private Label lblCNP;

    @FXML
    private Button btnVote;
    @FXML private Button btnLogIn;
    @FXML
    private DatePicker dateBirthday;
    @FXML
    private TextField txtCNP;
    @FXML
    private PasswordField passPassword;
    @FXML
    private PasswordField passConfirmPassword;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;

    public void setOnAction(ActionEvent event) throws IOException {
        Window owner = btnVote.getScene().getWindow();

        clearErrorLabels();
        if (txtLastName.getText().isEmpty()) {
            lblLastName.setText("Vă rog să vă introduceți numele.");
            return;
        }
        if (txtFirstName.getText().isEmpty()) {
            lblFirstName.setText("Vă rog să vă introduceți prenumele.");
            return;
        }
        if (txtEmail.getText().isEmpty()) {
            lblEmail.setText("Vă rog să vă introduceți email-ul.");
            return;
        }
        if (passPassword.getText().isEmpty()) {
            lblPassword.setText("Vă rog să vă introduceți parola.");
            return;
        }
        if (passPassword.getText().length() < 6) {
            lblPassword.setText("Parola trebuie să conțină cel puțin 6 caractere.");
            return;
        }
        if (passConfirmPassword.getText().isEmpty()) {
            lblConfirmPassword.setText("Vă rog să vă confirmați parola.");
            return;
        }
        if (!passPassword.getText().equals(passConfirmPassword.getText())) {
            lblConfirmPassword.setText("Parolele nu coincid.");
            return;
        }
        if (dateBirthday.getValue() == null) {
            lblBirthday.setText("Vă rog să vă introduceți ziua de naștere.");
            return;
        }
        if (txtCNP.getText().isEmpty()) {
            lblCNP.setText("Vă rog să vă introduceți CNP-ul.");
            return;
        }
        User u = new User(txtEmail.getText(), passPassword.getText(), txtFirstName.getText(), txtLastName.getText(), txtCNP.getText(), dateBirthday.getValue());
        u.setVotingStatus(false);
        if (!u.verifyLastName()) {
            lblLastName.setText("Numele trebuie să conțină doar litere.");
            return;
        }
        if (!u.verifyFirstName()) {
            lblFirstName.setText("Prenumele trebuie să conțină doar litere.");
            return;
        }
        if (!u.isMajor()) {
            lblBirthday.setText("Trebuie să aveți peste 18 ani pentru a putea vota.");
            return;
        }
        if (!u.verifyCNP(lblCNP)) {
            return;
        }
        if (u.verifyDateOfBirth() != 0) {
            lblBirthday.setText("Ziua de naștere introdusă nu coincide cu cea din CNP.");
            return;
        }
        CNP = u.getCNP();
        if (!database.isUserInDatabaseWithCNP(u)) {
            database.insertUserIntoDatabase(u);
            changeScene(event, "/electionType.fxml", "Alegeri");
        } else {
            showAlert(Alert.AlertType.INFORMATION, owner, "Înregistrare nereușită", "Acest cont este deja înregistrat.");
        }
    }

    public void goToLogIn(ActionEvent event) throws IOException {
        changeScene(event, "/logIn.fxml", "Conectare");
    }

    /**
     * clearing the text of the error labels from the sign up form
     */
    public void clearErrorLabels() {
        lblFirstName.setText("");
        lblLastName.setText("");
        lblEmail.setText("");
        lblPassword.setText("");
        lblConfirmPassword.setText("");
        lblBirthday.setText("");
        lblCNP.setText("");
    }
}
