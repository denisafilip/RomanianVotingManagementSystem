package Model.election;

import Model.election.enums.MandateDuration;
import Model.election.enums.RoundNumber;
import Model.election.enums.ElectionType;
import Model.user.Candidate;

import java.time.Year;
import java.util.ArrayList;

public class PresidentialElection extends Election {
    private final RoundNumber round; //1 or 2
    private ArrayList<Candidate> candidates;

    public PresidentialElection(Year yearOfElection, double electoralThreshold, RoundNumber round) {
        super(ElectionType.PRESIDENTIAL, yearOfElection, MandateDuration.FIVE_YEARS, electoralThreshold);
        this.round = round;
    }

    public ArrayList<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(ArrayList<Candidate> candidates) {
        this.candidates = candidates;
    }

    public RoundNumber getRound() {
        return round;
    }
}
