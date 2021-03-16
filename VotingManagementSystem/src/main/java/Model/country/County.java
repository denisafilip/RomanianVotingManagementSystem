package Model.country;

public class County {

    private String countyName;
    private int noOfPresidentialVotes;
    private int noOfEuroVotes;
    private int noOfLocalVotes;
    private int noOfParliamentVotes;
    private int noOfReferendumVotes;

    public County(String name) {
        this.countyName = name;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public int getNoOfPresidentialVotes() {
        return noOfPresidentialVotes;
    }

    public void setNoOfPresidentialVotes(int noOfPresidentialVotes) {
        this.noOfPresidentialVotes = noOfPresidentialVotes;
    }

    public int getNoOfEuroVotes() {
        return noOfEuroVotes;
    }

    public void setNoOfEuroVotes(int noOfEuroVotes) {
        this.noOfEuroVotes = noOfEuroVotes;
    }

    public int getNoOfLocalVotes() {
        return noOfLocalVotes;
    }

    public void setNoOfLocalVotes(int noOfLocalVotes) {
        this.noOfLocalVotes = noOfLocalVotes;
    }

    public int getNoOfParliamentVotes() {
        return noOfParliamentVotes;
    }

    public void setNoOfParliamentVotes(int noOfParliamentVotes) {
        this.noOfParliamentVotes = noOfParliamentVotes;
    }

    public int getNoOfVotesReferendum() {
        return noOfReferendumVotes;
    }

    public void setNoOfVotesReferendum(int noOfVotesReferendum) {
        this.noOfReferendumVotes = noOfVotesReferendum;
    }

    public void incrementNoOfPresidentialVotes() {
        this.noOfPresidentialVotes++;
    }

    public void incrementNoOfEuroVotes() {
        this.noOfEuroVotes++;
    }

    public void incrementNoOfLocalVotes() {
        this.noOfLocalVotes++;
    }

    public void incrementNoOfParliamentVotes() {
        this.noOfParliamentVotes++;
    }

    public void incrementNoOfReferendumVotes() {
        this.noOfReferendumVotes++;
    }
}
