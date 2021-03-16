package Controller.admin;

import Controller.ParentController;
import Model.country.Country;
import Model.country.County;
import Model.election.Election;
import Model.election.enums.ElectionType;
import Model.election.referendum.Referendum;
import Model.election.referendum.ReferendumPosition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import Model.politicalParty.PoliticalParty;
import Model.vote.ReferendumPairVote;
import Model.webScraping.Scraping;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.ResourceBundle;

public class AdminController extends ParentController implements Initializable {
    @FXML private Button btnBack;

    @FXML private ImageView imgVotesUSR;
    @FXML private ImageView imgVotesPNL;
    @FXML private ImageView imgVotesPSD;
    @FXML private ImageView imgVotesAUR;
    @FXML private ImageView imgVotesPMP;
    @FXML private ImageView imgVotesPRO;
    @FXML private ImageView imgVotesUDMR;
    @FXML private ImageView imgVotesPOL;
    @FXML private ImageView imgVotesPRU;

    @FXML private Label lblElection;
    @FXML private Label lblVoteAttendance;
    @FXML private Label lblVoteAttendance2;
    @FXML private AnchorPane anchorPane;

    @FXML private Label lblS;
    @FXML private Label lblCD;
    @FXML private Line line1;
    @FXML private Line line2;
    @FXML private Line lineAttendance;

    @FXML private BarChart<String, Number> barChartCounties;
    @FXML private BarChart<String, Number> barChartRef1;
    @FXML private BarChart<String, Number> barChartRef2;

    private final DecimalFormat doubleFormat = new DecimalFormat("#.000000000");
    private final int DISTANCE_BETWEEN_LABELS = 80;
    private final int START_LAYOUT_Y = 60;
    private final int LAYOUT_X_FIRST_LABELS = 901;
    private final int LAYOUT_X_SECOND_LABELS = 1013;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (buttonPressed == getFirstReferendum().getType()) {
            displayVotesReferendum();
        } else if (buttonPressed == getDeputiesParliamentElection().getType()) {
            displayVotesForParliamentElection();
            displayVotesPoliticalPartiesParliament();
        } else {
            displayVotesPoliticalParties();
            displayVotesCounties();
        }
    }

    public void goBackToAdminMenu(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "/adminMenu.fxml", "Meniu administrator");
    }

    public void displayVotesReferendum() {
        lblElection.setText("STATISTICI - Referendum - " + getFirstReferendum().getYearOfElection());
        Scraping scraper = new Scraping();
        Country Romania = scraper.webScrapingCounties();

        barChartCounties.setVisible(false);
        barChartRef1.setVisible(true);
        barChartRef2.setVisible(true);
        lineAttendance.setVisible(false);

        setUpBarChartForReferendum(Romania, barChartRef1, "Voturi pe Județe Întrebare 1 Referendum", getFirstReferendum());
        setUpBarChartForReferendum(Romania, barChartRef2, "Voturi pe Județe Întrebare 2 Referendum", getSecondReferendum());

        changeVisibilityOfImageViews();

        for (County c : Romania.getCounties()) {
            getFirstReferendum().getReferendumVotes().increaseNumberOfVotes(database.getNoOfVotesCountyReferendum(getFirstReferendum(), c.getCountyName()));
            getSecondReferendum().getReferendumVotes().increaseNumberOfVotes(database.getNoOfVotesCountyReferendum(getSecondReferendum(), c.getCountyName()));
        }
        final int layoutX = 815;
        int layoutY = 228;
        int distanceBetweenLabels = 80;

        createLabelForReferendum(layoutY, layoutX, getFirstReferendum().getReferendumVotes().getProVote().getNoOfVotes(), 1, ReferendumPosition.PRO.getLabel());
        createLabelForReferendum(layoutY + distanceBetweenLabels, layoutX, getFirstReferendum().getReferendumVotes().getAgainstVote().getNoOfVotes(), 1, "Contra");
        createLabelForReferendum(layoutY + 2*distanceBetweenLabels, layoutX, getSecondReferendum().getReferendumVotes().getProVote().getNoOfVotes(), 2, ReferendumPosition.PRO.getLabel());
        createLabelForReferendum(layoutY + 3*distanceBetweenLabels, layoutX, getSecondReferendum().getReferendumVotes().getAgainstVote().getNoOfVotes(), 2, "Contra");

    }

    public void changeVisibilityOfImageViews() {
        imgVotesAUR.setVisible(false);
        imgVotesUSR.setVisible(false);
        imgVotesPNL.setVisible(false);
        imgVotesPSD.setVisible(false);
        imgVotesUDMR.setVisible(false);
        imgVotesPOL.setVisible(false);
        imgVotesPMP.setVisible(false);
        imgVotesPRU.setVisible(false);
        imgVotesPRO.setVisible(false);
    }

    public void createLabelForReferendum(int layoutY, int layoutX, int noOfVotes, int noOfQuestion, String positionToQuestion) {
        Label lbl = new Label();
        lbl.setText("Întrebare " + noOfQuestion + ": voturi " + positionToQuestion + " - " + noOfVotes);
        lbl.setLayoutX(layoutX);
        lbl.setLayoutY(layoutY);
        lbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        anchorPane.getChildren().add(lbl);
    }

    public void setUpBarChartForReferendum(Country Romania, BarChart<String, Number> barChart, String barChartTitle, Referendum referendum) {
        barChart.setTitle(barChartTitle);
        Romania.sortCountiesAlphabetically(Romania);
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Număr de voturi");
        for (County c : Romania.getCounties()) {
            ReferendumPairVote referendumPairVote = database.getNoOfVotesCountyReferendum(referendum, c.getCountyName());
            series.getData().add(new XYChart.Data<>(c.getCountyName(), referendumPairVote.getTotalVotesReferendum()));
        }
        barChart.getData().add(series);
    }

    public void displayVotesCounties() {
        Scraping scraper = new Scraping();
        Country Romania = scraper.webScrapingCounties();

        barChartCounties.setVisible(true);
        barChartRef1.setVisible(false);
        barChartRef2.setVisible(false);
        barChartCounties.setTitle("Voturi pe Județe");
        Romania.sortCountiesAlphabetically(Romania);
        Election typeOfVote = setTypeOfElection();
        lblVoteAttendance.setText("Prezență vot - " + doubleFormat.format(database.computeVoteAttendance(typeOfVote)) + "%");
        getDataForBarChart(Romania, typeOfVote, "Număr de voturi");
    }

    public void displayVotesForParliamentElection() {
        lblElection.setText("STATISTICI - " + ElectionType.PARLIAMENT.getLabelRomanian() + getDeputiesParliamentElection().getYearOfElection());
        Scraping scraper = new Scraping();
        Country Romania = scraper.webScrapingCounties();

        barChartCounties.setVisible(true);
        barChartRef1.setVisible(false);
        barChartRef2.setVisible(false);
        barChartCounties.setTitle("Voturi pe Județe");
        Romania.sortCountiesAlphabetically(Romania);
        lblVoteAttendance.setText("Prezență vot " + getSenateParliamentElection().getType().getLabelRomanian() + " - " + doubleFormat.format(database.computeVoteAttendance(getSenateParliamentElection())) + "%");
        lblVoteAttendance2.setText("Prezență vot " + getDeputiesParliamentElection().getType().getLabelRomanian() + " - " + doubleFormat.format(database.computeVoteAttendance(getDeputiesParliamentElection())) + "%");
        getDataForBarChart(Romania, getSenateParliamentElection(), "Număr de voturi Senat");
        getDataForBarChart(Romania, getDeputiesParliamentElection(), "Număr de voturi Camera Deputaților");
    }

    public void getDataForBarChart(Country country, Election typeOfVote, String titleOfSeries) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(titleOfSeries);
        for (County c : country.getCounties()) {
            int noOfVotes = database.getNoOfVotesCounty(typeOfVote, c.getCountyName());
            series.getData().add(new XYChart.Data<>(c.getCountyName(), noOfVotes));
        }
        barChartCounties.getData().add(series);
    }

    public void displayVotesPoliticalPartiesParliament() {
        int layoutY = START_LAYOUT_Y;
        line1.setVisible(true);
        line2.setVisible(true);
        lblS.setVisible(true);
        lblCD.setVisible(true);
        Election senate = getSenateParliamentElection();
        Election chamberOfDeputies = getDeputiesParliamentElection();
        for (Map.Entry<String, PoliticalParty> entry : senate.getPoliticalParties().entrySet()) {
            PoliticalParty p = entry.getValue();
            createLabelForPoliticalParty(LAYOUT_X_FIRST_LABELS, layoutY, senate, p);
            createLabelForPoliticalParty(LAYOUT_X_SECOND_LABELS, layoutY, chamberOfDeputies, p);
            layoutY += DISTANCE_BETWEEN_LABELS;
        }
    }

    public void displayVotesPoliticalParties() {
        int layoutY = START_LAYOUT_Y;
        Election typeOfElection = setTypeOfElection();
        lblElection.setText("STATISTICI - " + typeOfElection.getType().getLabelRomanian() + " - " + typeOfElection.getYearOfElection());
        for (Map.Entry<String, PoliticalParty> entry : typeOfElection.getPoliticalParties().entrySet()) {
            PoliticalParty p = entry.getValue();
            createLabelForPoliticalParty(LAYOUT_X_FIRST_LABELS, layoutY, typeOfElection, p);
            layoutY += DISTANCE_BETWEEN_LABELS;
        }
    }

    public void createLabelForPoliticalParty(int layoutX, int layoutY, Election election, PoliticalParty p) {
        int noOfVotes = database.getNoOfVotesPoliticalParty(election, p.getAbbreviation());
        Label lbl = new Label();
        lbl.setText("voturi: " + noOfVotes);
        lbl.setLayoutX(layoutX);
        lbl.setLayoutY(layoutY);
        lbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        anchorPane.getChildren().add(lbl);
    }

    private Election setTypeOfElection() {
        Election typeOfVote;
        switch (buttonPressed) {
            case EUROPEAN_PARLIAMENT:
                typeOfVote = getEuropeanParliamentElection();
                break;
            case PRESIDENTIAL:
                typeOfVote = getPresidentialElection();
                break;
            case LOCAL:
                typeOfVote = getLocalElection();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + buttonPressed);
        }
        return typeOfVote;
    }
}
