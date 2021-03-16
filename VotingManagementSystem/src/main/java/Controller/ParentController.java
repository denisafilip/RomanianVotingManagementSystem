package Controller;

import Model.database.DatabaseOperations;
import Model.election.*;
import Model.election.enums.Question;
import Model.election.enums.RoundNumber;
import Model.election.enums.ElectionType;
import Model.election.referendum.Referendum;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.time.Year;

public class ParentController {
    public static ElectionType buttonPressed;
    private Referendum firstReferendum = new Referendum(ElectionType.REFERENDUM_QUESTION_1, Question.QUESTION1, Year.of(2019));
    private Referendum secondReferendum = new Referendum(ElectionType.REFERENDUM_QUESTION_2, Question.QUESTION2, Year.of(2019));
    private Election presidentialElection = new PresidentialElection(Year.of(2019), 5, RoundNumber.ONE);
    private Election localElection = new LocalElection(Year.of(2020), 5);
    private Election europeanParliamentElection = new EuropeanParliamentElection(Year.of(2019), 5);
    private Election senateParliamentElection = new ParliamentElection(ElectionType.SENATE_PARLIAMENT, Year.of(2020), 5);
    private Election deputiesParliamentElection = new ParliamentElection(ElectionType.CHAMBER_OF_DEPUTIES_PARLIAMENT, Year.of(2020), 5);
    public static String CNP;
    public final DatabaseOperations database = new DatabaseOperations();

    /**
     * Function that changes from a scene to another, specified through the fxmlFilePath parameter
     */
    public void changeScene(ActionEvent event, String fxmlFilePath, String windowTitle) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(fxmlFilePath));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setTitle(windowTitle);
        window.setScene(scene);
        window.show();
    }

    /**
     * pop-up alert, to inform Model.user after different actions
     *
     * @param alertType - the type of alert (ERROR, INFORMATION, CONFIRMATION, WARNING(
     * @param owner - the window the alert belongs to
     * @param title - title of the alert
     * @param message - message of the alert
     */
    public void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    /**
     * Function that handles all of the necessary changes after a Model.vote is placed by the Model.user - increasing the number of votes for the specific political party at the specific
     * Model.election, increasing the number of votes in the county in which the Model.user lives; setting the voting status of the Model.user to true     */
    public void placeVote(ImageView img, Button button, Election election, String politicalPartyAbbr, String CNP) {
        Window owner = button.getScene().getWindow();
        if (!database.hasUserVoted(CNP, election)) {
            database.incrementVotesForParty(election, election.getPoliticalParties().get(politicalPartyAbbr));
            database.incrementVotesForCounty(election, CNP);
            database.updateUserVotingStatus(election, CNP);
            img.setVisible(true);
        } else {
            showAlert(Alert.AlertType.ERROR, owner,"Vot nereușit!", "Puteți să votați o singură dată în cadrul unor alegeri.");
        }
    }

    public Referendum getFirstReferendum() {
        return firstReferendum;
    }

    public void setFirstReferendum(Referendum firstReferendum) {
        this.firstReferendum = firstReferendum;
    }

    public Referendum getSecondReferendum() {
        return secondReferendum;
    }

    public void setSecondReferendum(Referendum secondReferendum) {
        this.secondReferendum = secondReferendum;
    }

    public Election getPresidentialElection() {
        return presidentialElection;
    }

    public void setPresidentialElection(Election presidentialElection) {
        this.presidentialElection = presidentialElection;
    }

    public Election getLocalElection() {
        return localElection;
    }

    public void setLocalElection(Election localElection) {
        this.localElection = localElection;
    }

    public Election getEuropeanParliamentElection() {
        return europeanParliamentElection;
    }

    public void setEuropeanParliamentElection(Election europeanParliamentElection) {
        this.europeanParliamentElection = europeanParliamentElection;
    }

    public Election getSenateParliamentElection() {
        return senateParliamentElection;
    }

    public void setSenateParliamentElection(Election senateParliamentElection) {
        this.senateParliamentElection = senateParliamentElection;
    }

    public Election getDeputiesParliamentElection() {
        return deputiesParliamentElection;
    }

    public void setDeputiesParliamentElection(Election deputiesParliamentElection) {
        this.deputiesParliamentElection = deputiesParliamentElection;
    }
}
