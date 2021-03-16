package Model.election;

import Model.election.enums.MandateDuration;
import Model.election.enums.ElectionType;
import Model.user.Candidate;

import java.time.Year;
import java.util.List;

public class LocalElection extends Election {
    List<Candidate> cityCouncil;
    List<Candidate> mayor;
    List<Candidate> countyCouncil;
    List<Candidate> presidentCountyCouncil;

    public LocalElection(Year yearOfElection, double electoralThreshold) {
        super(ElectionType.LOCAL, yearOfElection, MandateDuration.FOUR_YEARS, electoralThreshold);
    }

    public List<Candidate> getCityCouncil() {
        return cityCouncil;
    }

    public void setCityCouncil(List<Candidate> cityCouncil) {
        this.cityCouncil = cityCouncil;
    }

    public List<Candidate> getMayor() {
        return mayor;
    }

    public void setMayor(List<Candidate> mayor) {
        this.mayor = mayor;
    }

    public List<Candidate> getCountyCouncil() {
        return countyCouncil;
    }

    public void setCountyCouncil(List<Candidate> countyCouncil) {
        this.countyCouncil = countyCouncil;
    }

    public List<Candidate> getPresidentCountyCouncil() {
        return presidentCountyCouncil;
    }

    public void setPresidentCountyCouncil(List<Candidate> presidentCountyCouncil) {
        this.presidentCountyCouncil = presidentCountyCouncil;
    }
}
