package Controller.elections;

import Controller.ParentController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PresidentialController extends ParentController implements Initializable  {
    @FXML private ImageView imgVotedUSR;
    @FXML private ImageView imgVotedPNL;
    @FXML private ImageView imgVotedPSD;
    @FXML private ImageView imgVotedAUR;
    @FXML private ImageView imgVotedPMP;
    @FXML private ImageView imgVotedPRO;
    @FXML private ImageView imgVotedUDMR;
    @FXML private ImageView imgVotedPOL;
    @FXML private ImageView imgVotedPRU;
    @FXML private Button btnBack;
    @FXML private Label lblElection;
    @FXML private Button btnVoteUSR;
    @FXML private Button btnVotePNL;
    @FXML private Button btnVotePSD;
    @FXML private Button btnVoteUDMR;
    @FXML private Button btnVotePMP;
    @FXML private Button btnVoteAUR;
    @FXML private Button btnVotePRO;
    @FXML private Button btnVotePOL;
    @FXML private Button btnVotePRU;

    public void goBackToElection(ActionEvent event) throws IOException {
        changeScene(event, "/electionType.fxml", "Alegeri");
    }

    public void voteUSR() {
        placeVote(imgVotedUSR, btnVoteUSR, getPresidentialElection(), "USR PLUS", CNP);
    }

    public void votePNL() {
        placeVote(imgVotedPNL, btnVotePNL, getPresidentialElection(), "PNL", CNP);
    }

    public void votePSD() {
        placeVote(imgVotedPSD, btnVotePSD, getPresidentialElection(), "PSD", CNP);
    }

    public void voteUDMR() {
        placeVote(imgVotedUDMR, btnVoteUDMR, getPresidentialElection(), "UDMR", CNP);
    }

    public void votePMP() {
        placeVote(imgVotedPMP, btnVotePMP, getPresidentialElection(), "PMP", CNP);
    }

    public void voteAUR() {
        placeVote(imgVotedAUR, btnVoteAUR, getPresidentialElection(), "AUR", CNP);
    }

    public void votePRO() {
        placeVote(imgVotedPRO, btnVotePRO, getPresidentialElection(), "PRO România", CNP);
    }

    public void votePOL() {
        placeVote(imgVotedPOL, btnVotePOL, getPresidentialElection(), "POL", CNP);
    }

    public void votePRU() {
        placeVote(imgVotedPRU, btnVotePRU, getPresidentialElection(), "PRU", CNP);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblElection.setText("ALEGERI PREZIDENȚIALE - " + getPresidentialElection().getYearOfElection());
    }
}
