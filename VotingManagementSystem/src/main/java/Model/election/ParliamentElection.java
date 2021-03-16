package Model.election;

import Model.country.Country;
import Model.election.enums.MandateDuration;
import Model.election.enums.ElectionType;
import Model.user.Candidate;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.Year;
import java.util.*;

public class ParliamentElection extends Election {
    private List<Candidate> chamberOfDeputies;
    private List<Candidate> senate;

    public ParliamentElection(ElectionType type, Year yearOfElection, double electoralThreshold) {
        super(type, yearOfElection, MandateDuration.FOUR_YEARS, electoralThreshold);
    }

    public List<Candidate> getChamberOfDeputies() {
        return chamberOfDeputies;
    }

    public void setChamberOfDeputies(List<Candidate> chamberOfDeputies) {
        this.chamberOfDeputies = chamberOfDeputies;
    }

    public List<Candidate> getSenate() {
        return senate;
    }

    public void setSenate(List<Candidate> senate) {
        this.senate = senate;
    }

    /**
     * Scraping the candidates for the 2020 Parliament/Legislative Election from a web through Jsoup and parsing the respective data into
     * 2 arraylists: one for the candidates of the Chamber of Deputies and one for the candidates of the Senate
     * @param country takes a Country object
     * @param paragraph takes an Elements object: the paragraphs found in the HTML code of a website containing the candidates for the 2020
     *                  Parliament Election
     */
    public void setParliamentCandidates(Country country, Elements paragraph) {
        List<Candidate> chamberOfDeputies = new ArrayList<>();
        List<Candidate> senate = new ArrayList<>();
        int countyIndex = -1;
        for (Element p : paragraph) {
            String text = p.text();
            List<String> split = Arrays.asList(text.replace(" – ", ", ").replace(": ", ", ").replace("/ ", ", ").split(", "));
            if (split.get(0).equals("PNL")) {
                countyIndex++;
            }

            int j = 2; //starting from 2 because I want to skip over political party and "Senat" word
            while(!split.get(j).equals("Camera Deputaților")) {
                Candidate forSenate = new Candidate(split.get(j), country.getCounties().get(countyIndex), split.get(0));
                senate.add(forSenate);
                j++;
            }
            j++; //skipping over "Camera Deputatilor" text
            while (split.size() > j) {
                Candidate forDeputy = new Candidate(split.get(j), country.getCounties().get(countyIndex), split.get(0));
                chamberOfDeputies.add(forDeputy);
                j++;
            }
        }
        this.chamberOfDeputies = chamberOfDeputies;
        this.senate = senate;
    }
}
