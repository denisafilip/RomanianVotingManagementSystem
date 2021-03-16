package Model.politicalParty;

public class PoliticalParty {
    private String name;
    private String abbreviation;
    private PoliticalPosition politicalPosition; //position of the political party in the political spectrum

    public PoliticalParty(String name, String abbreviation, PoliticalPosition politicalPosition) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.politicalPosition = politicalPosition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public PoliticalPosition getPoliticalPosition() {
        return politicalPosition;
    }

    public void setPoliticalPosition(PoliticalPosition politicalPosition) {
        this.politicalPosition = politicalPosition;
    }

}
