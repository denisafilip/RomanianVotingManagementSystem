package Controller.elections;

import Controller.ParentController;
import Model.country.Country;
import Model.election.ParliamentElection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import Model.user.Candidate;
import Model.webScraping.Scraping;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ParliamentController extends ParentController implements Initializable {
    @FXML private AnchorPane paneUSR;
    @FXML private AnchorPane panePNL;
    @FXML private AnchorPane panePSD;
    @FXML private AnchorPane paneUDMR;
    @FXML private AnchorPane panePMP;
    @FXML private AnchorPane panePRO;

    @FXML private ImageView imgVotedUSR;
    @FXML private ImageView imgVotedPNL;
    @FXML private ImageView imgVotedPSD;
    @FXML private ImageView imgVotedPMP;
    @FXML private ImageView imgVotedPRO;
    @FXML private ImageView imgVotedUDMR;

    @FXML private Label lblElection;
    @FXML private Button btnBack;
    @FXML private Button btnDepChamber;
    @FXML private Button btnSenate;

    @FXML private Button btnVoteUSR;
    @FXML private Button btnVotePNL;
    @FXML private Button btnVotePSD;
    @FXML private Button btnVoteUDMR;
    @FXML private Button btnVotePMP;
    @FXML private Button btnVotePRO;

    @FXML private Button btnVoteUSRSenate;
    @FXML private Button btnVotePNLSenate;
    @FXML private Button btnVotePSDSenate;
    @FXML private Button btnVoteUDMRSenate;
    @FXML private Button btnVotePMPSenate;
    @FXML private Button btnVotePROSenate;

    @FXML private ComboBox<String> comboDepUSR;
    @FXML private ComboBox<String> comboDepPNL;
    @FXML private ComboBox<String> comboDepPSD;
    @FXML private ComboBox<String> comboDepUDMR;
    @FXML private ComboBox<String> comboDepPMP;
    @FXML private ComboBox<String> comboDepPRO;
    @FXML private ComboBox<String> comboSenateUSR;
    @FXML private ComboBox<String> comboSenatePNL;
    @FXML private ComboBox<String> comboSenatePSD;
    @FXML private ComboBox<String> comboSenateUDMR;
    @FXML private ComboBox<String> comboSenatePMP;
    @FXML private ComboBox<String> comboSenatePRO;


    private final String countyName = database.getUserCountyByName(CNP);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblElection.setText("ALEGERI PARLAMENTARE - " + countyName);
        addValuesToComboBox(countyName, "USR PLUS", comboDepUSR, comboSenateUSR);
        addValuesToComboBox(countyName, "PNL", comboDepPNL, comboSenatePNL);
        addValuesToComboBox(countyName, "PSD", comboDepPSD, comboSenatePSD);
        addValuesToComboBox(countyName,"Pro România", comboDepPRO, comboSenatePRO);
        addValuesToComboBox(countyName,"PMP", comboDepPMP, comboSenatePMP);
        addValuesToComboBox(countyName,"UDMR", comboDepUDMR, comboSenateUDMR);
    }

    public void goBackToElection(ActionEvent event) throws IOException {
        changeScene(event, "/electionType.fxml", "Alegeri");
    }


    public void voteChamberOfDeputies() {
        lblElection.setText("ALEGERI PARLAMENTARE - CAMERA DEPUTAȚILOR - " + countyName);
        changeVisibilityOfVotedImage(false);
        changeVisibilityOfAnchorPanes(false);
        changeVisibilityOfSenateComboBox(false);
        changeVisibilityOfSenateVoteButton(false);
        changeVisibilityOfAnchorPanes(true);
        changeVisibilityOfDeputiesComboBox(true);
        changeVisibilityOfDeputiesVoteButton(true);
    }

    public void voteSenate() {
        lblElection.setText("ALEGERI PARLAMENTARE - SENAT - " + countyName);
        changeVisibilityOfVotedImage(false);
        changeVisibilityOfAnchorPanes(false);
        changeVisibilityOfDeputiesComboBox(false);
        changeVisibilityOfDeputiesVoteButton(false);
        changeVisibilityOfAnchorPanes(true);
        changeVisibilityOfSenateComboBox(true);
        changeVisibilityOfSenateVoteButton(true);
    }

    /** voting for Chamber of Deputies candidates */
    public void voteUSR() {
        placeVote(imgVotedUSR, btnVoteUSR, getDeputiesParliamentElection(), "USR PLUS", CNP);
    }

    public void votePNL() {
        placeVote(imgVotedPNL, btnVotePNL, getDeputiesParliamentElection(), "PNL", CNP);
    }

    public void votePSD() {
        placeVote(imgVotedPSD, btnVotePSD, getDeputiesParliamentElection(), "PSD", CNP);
    }

    public void voteUDMR() {
        placeVote(imgVotedUDMR, btnVoteUDMR, getDeputiesParliamentElection(), "UDMR", CNP);
    }

    public void votePMP() {
        placeVote(imgVotedPMP, btnVotePMP, getDeputiesParliamentElection(), "PMP", CNP);
    }

    public void votePRO() {
        placeVote(imgVotedPRO, btnVotePRO, getDeputiesParliamentElection(), "PRO România", CNP);
    }


    /** voting for Senate candidates */
    public void voteUSRSenate() { placeVote(imgVotedUSR, btnVoteUSRSenate, getSenateParliamentElection(), "USR PLUS", CNP); }

    public void votePNLSenate() {
        placeVote(imgVotedPNL, btnVotePNLSenate, getSenateParliamentElection(), "PNL", CNP);
    }

    public void votePSDSenate() {
        placeVote(imgVotedPSD, btnVotePSDSenate, getSenateParliamentElection(), "PSD", CNP);
    }

    public void voteUDMRSenate() { placeVote(imgVotedUDMR, btnVoteUDMRSenate, getSenateParliamentElection(), "UDMR", CNP); }

    public void votePMPSenate() {
        placeVote(imgVotedPMP, btnVotePMPSenate, getSenateParliamentElection(), "PMP", CNP);
    }

    public void votePROSenate() { placeVote(imgVotedPRO, btnVotePROSenate, getSenateParliamentElection(), "PRO România", CNP);}



    public void addValuesToComboBox(String countyName, String politicalParty, ComboBox<String> comboBoxDep, ComboBox<String> comboBoxSenate) {
        final Scraping scraper = new Scraping();
        final Country Romania = scraper.webScrapingCounties();
        final ParliamentElection parliamentElection = scraper.webScrapingPCandidates(Romania);

        List<String> candidatesDep = new ArrayList<>();
        for (Candidate c : parliamentElection.getChamberOfDeputies()) {
            if (c.getPoliticalPartyAbbr().equals(politicalParty) && c.getCounty().getCountyName().equals(countyName)) {
                candidatesDep.add(c.getFirstName());
            }
        }
        ObservableList<String> candidatesDepOptions = FXCollections.observableArrayList(candidatesDep);
        comboBoxDep.setItems(candidatesDepOptions);

        List<String> candidatesSenate = new ArrayList<>();
        for (Candidate c : parliamentElection.getSenate()) {
            if (c.getPoliticalPartyAbbr().equals(politicalParty) && c.getCounty().getCountyName().equals(countyName)) {
                candidatesSenate.add(c.getFirstName());
            }
        }
        ObservableList<String> candidatesSenateOptions = FXCollections.observableArrayList(candidatesSenate);
        comboBoxSenate.setItems(candidatesSenateOptions);
    }

    public void changeVisibilityOfAnchorPanes(boolean visibility) {
        panePMP.setVisible(visibility);
        paneUSR.setVisible(visibility);
        panePNL.setVisible(visibility);
        panePSD.setVisible(visibility);
        paneUDMR.setVisible(visibility);
        panePRO.setVisible(visibility);
    }

    public void changeVisibilityOfSenateComboBox(boolean visibility) {
        comboSenatePMP.setVisible(visibility);
        comboSenateUSR.setVisible(visibility);
        comboSenatePNL.setVisible(visibility);
        comboSenatePSD.setVisible(visibility);
        comboSenateUDMR.setVisible(visibility);
        comboSenatePRO.setVisible(visibility);
    }

    public void changeVisibilityOfDeputiesComboBox(boolean visibility) {
        comboDepPMP.setVisible(visibility);
        comboDepUSR.setVisible(visibility);
        comboDepPNL.setVisible(visibility);
        comboDepPSD.setVisible(visibility);
        comboDepUDMR.setVisible(visibility);
        comboDepPRO.setVisible(visibility);
    }

    public void changeVisibilityOfSenateVoteButton(boolean visibility) {
        btnVotePMPSenate.setVisible(visibility);
        btnVoteUSRSenate.setVisible(visibility);
        btnVotePNLSenate.setVisible(visibility);
        btnVotePROSenate.setVisible(visibility);
        btnVotePSDSenate.setVisible(visibility);
        btnVoteUDMRSenate.setVisible(visibility);
    }

    public void changeVisibilityOfDeputiesVoteButton(boolean visibility) {
        btnVotePMP.setVisible(visibility);
        btnVoteUSR.setVisible(visibility);
        btnVotePNL.setVisible(visibility);
        btnVotePRO.setVisible(visibility);
        btnVotePSD.setVisible(visibility);
        btnVoteUDMR.setVisible(visibility);
    }

    public void changeVisibilityOfVotedImage(boolean visibility) {
        imgVotedPMP.setVisible(visibility);
        imgVotedUSR.setVisible(visibility);
        imgVotedPNL.setVisible(visibility);
        imgVotedPRO.setVisible(visibility);
        imgVotedPSD.setVisible(visibility);
        imgVotedUDMR.setVisible(visibility);
    }

}
