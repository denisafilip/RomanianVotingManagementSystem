package Model.webScraping;

import Model.country.Country;
import Model.election.ParliamentElection;
import Model.election.enums.ElectionType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.Year;

public class Scraping {

    public Country webScrapingCounties() {
        //web scraping counties of Romania
        try {
            Document docCounty = Jsoup.connect("https://ro.wikipedia.org/wiki/Lista_județelor_României_după_populație").get();
            Country Romania = new Country("Romania");
            Romania.setCountiesOfRomania(Romania, docCounty);
            return Romania;
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }

    }

    public ParliamentElection webScrapingPCandidates(Country Romania) {
        //web scraping counties of Romania
        try {
            //parsing data of the candidates for the parliament elections
            Document doc = Jsoup.connect("https://www.g4media.ro/harta-candidatii-la-alegerile-parlamentare-2020-istoricul-prezentei-mandatele-pe-fiecare-judet-si-scorurile-din-2016.html").get();
            Elements paragraph = doc.select("p:contains(: Senat)");
            ParliamentElection parliamentElection = new ParliamentElection(ElectionType.PARLIAMENT, Year.of(2020), 5);
            parliamentElection.setParliamentCandidates(Romania, paragraph);
            return parliamentElection;
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }
}

