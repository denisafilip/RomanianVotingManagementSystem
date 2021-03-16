package Model.election;

import Model.election.enums.MandateDuration;
import Model.election.enums.ElectionType;
import Model.politicalParty.PoliticalParty;
import Model.politicalParty.PoliticalPosition;

import java.time.Year;
import java.util.LinkedHashMap;
import java.util.Map;

public class Election {
    private ElectionType type;
    private Year yearOfElection;
    private MandateDuration mandate;
    private Map<String, PoliticalParty> politicalParties;
    private double electoralThreshold;
    private int noOfVotesPlaced;

    public Election() {
        addPoliticalParties();
    }

    public Election(ElectionType type, Year yearOfElection) {
        this.type = type;
        this.yearOfElection = yearOfElection;
    }

    public Election(ElectionType type, Year yearOfElection, MandateDuration mandate, double electoralThreshold) {
        this.type = type;
        this.yearOfElection = yearOfElection;
        this.mandate = mandate;
        this.electoralThreshold = electoralThreshold;
        addPoliticalParties();
    }

    public void addPoliticalParties() {
        Map<String, PoliticalParty> pParties = new LinkedHashMap<>();
        pParties.put("USR PLUS", new PoliticalParty("Alianța USR PLUS", "USR PLUS", PoliticalPosition.CENTER_RIGHT));
        pParties.put("PNL", new PoliticalParty("Partidul Național Liberal", "PNL", PoliticalPosition.CENTER_RIGHT));
        pParties.put("PSD", new PoliticalParty("Partidul Social Democrat", "PSD", PoliticalPosition.CENTER_LEFT));
        pParties.put("UDMR", new PoliticalParty("Uniunea Democratică Maghiară din România", "UDMR", PoliticalPosition.CENTER_RIGHT));
        pParties.put("PRO România", new PoliticalParty("PRO România", "PRO România", PoliticalPosition.CENTER));
        pParties.put("PMP", new PoliticalParty("Partidul Mișcarea Populară", "PMP", PoliticalPosition.CENTER_RIGHT));
        pParties.put("AUR", new PoliticalParty("Alianța pentru Unirea Românilor", "AUR", PoliticalPosition.RIGHT));
        pParties.put("POL", new PoliticalParty("Partidul Oamenilor Liberi", "POL", PoliticalPosition.CENTER));
        pParties.put("PRU", new PoliticalParty("Partidul România Unită", "PRU", PoliticalPosition.EXTREME_RIGHT));
        this.politicalParties = pParties;
    }

    public Year getYearOfElection() {
        return yearOfElection;
    }

    public void setYearOfElection(Year yearOfElection) {
        this.yearOfElection = yearOfElection;
    }

    public double getElectoralThreshold() {
        return electoralThreshold;
    }

    public void setElectoralThreshold(double electoralThreshold) {
        this.electoralThreshold = electoralThreshold;
    }

    public Map<String, PoliticalParty> getPoliticalParties() {
        return politicalParties;
    }

    public void setPoliticalParties(Map<String, PoliticalParty> politicalParties) {
        this.politicalParties = politicalParties;
    }

    public ElectionType getType() {
        return type;
    }

    public void setType(ElectionType type) {
        this.type = type;
    }

    public int getNoOfVotesPlaced() {
        return noOfVotesPlaced;
    }

    public void setNoOfVotesPlaced(int noOfVotesPlaced) {
        this.noOfVotesPlaced = noOfVotesPlaced;
    }

    public void increaseNoOfVotes(int noOfVotes) {
        this.noOfVotesPlaced += noOfVotes;
    }
}
