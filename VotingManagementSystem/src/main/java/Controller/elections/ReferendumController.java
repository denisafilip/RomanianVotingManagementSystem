package Controller.elections;

import Controller.ParentController;
import Model.election.Election;
import Model.election.referendum.ReferendumPosition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReferendumController extends ParentController implements Initializable {
    @FXML private ImageView imgVotedYes1;
    @FXML private ImageView imgVotedYes2;
    @FXML private ImageView imgVotedNo1;
    @FXML private ImageView imgVotedNo2;

    @FXML private Label lblElection;
    @FXML private Label lblQuestion1;
    @FXML private Label lblQuestion2;
    @FXML private Button btnBack;
    @FXML private Button btnYes1;
    @FXML private Button btnYes2;
    @FXML private Button btnNo1;
    @FXML private Button btnNo2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblElection.setText("REFERENDUM - " + getFirstReferendum().getYearOfElection());
        lblQuestion1.setText(getFirstReferendum().getQuestion().getLabel());
        lblQuestion2.setText(getSecondReferendum().getQuestion().getLabel());
    }

    public void goBackToElection(ActionEvent event) throws IOException {
        changeScene(event, "/electionType.fxml", "Alegeri");
    }

    public void voteYesInFirstReferendum() {
        placeVoteReferendum(imgVotedYes1, btnYes1, getFirstReferendum(), ReferendumPosition.PRO.getLabel());
    }

    public void voteYesInSecondReferendum() {
        placeVoteReferendum(imgVotedYes2, btnYes2, getSecondReferendum(), ReferendumPosition.PRO.getLabel());
    }

    public void voteNoInFirstReferendum() {
        placeVoteReferendum(imgVotedNo1, btnNo1, getFirstReferendum(), ReferendumPosition.AGAINST.getLabel());
    }

    public void voteNoInSecondReferendum() {
        placeVoteReferendum(imgVotedNo2, btnNo2, getSecondReferendum(), ReferendumPosition.AGAINST.getLabel());
    }

    public void placeVoteReferendum(ImageView img, Button button, Election election, String label) {
        Window owner = button.getScene().getWindow();
        if (!database.hasUserVoted(CNP, election)) {
            database.incrementVotesForCountyReferendum(election, CNP, label);
            database.updateUserVotingStatus(election, CNP);
            img.setVisible(true);
        } else {
            showAlert(Alert.AlertType.ERROR, owner,"Vot nereușit!", "Puteți să votați o singură dată în cadrul unui referendum.");
        }
    }
}
