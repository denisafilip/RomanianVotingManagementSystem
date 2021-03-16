package Model.country;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.text.Collator;
import java.util.*;

public class Country {
    private final String name;
    private List<County> Counties;
    private int totalVotes;

    public Country(String name) {
        this.name = name;
    }

    /**
     * Scraping the name of Romania's counties from the web through Jsoup and parsing the data into an arraylist of counties
     *
     * @param country is a Country object
     * @param docCounty takes a Document object that references a website, through Jsoup
     */
    public void setCountiesOfRomania(Country country, Document docCounty){
        Elements link = docCounty.select("a[href]");
        //Country Romania = new Country("Romania");

        List<County> counties = new ArrayList<County>();
        for (int i = 20; i < 61; i++) {
            County county = new County(link.get(i).text());
            counties.add(county);
        }
        County county = new County("București");
        counties.add(county);

        counties.sort(Comparator.comparing(County::getCountyName));

        //moving items of arraylist around to match the order they are displayed into the website with the candidates for the parliament Model.election
        counties.add(11, counties.remove(15));
        counties.add(32, counties.remove(35));
        country.setCounties(counties);
    }

    /**
     *
     * @param country is the Model.country which counties we want to sort in alphabetical order
     *                in this case, using the Locale class, I sort Romania's counties, taking into consideration the special letters
     */
    public void sortCountiesAlphabetically(Country country) {
        Locale locale = new Locale("ro", "RO");
        final Collator collator = Collator.getInstance(locale);

        country.getCounties().sort(new Comparator<County>() {
            @Override
            public int compare(County o1, County o2) {
                return collator.compare(o1.getCountyName(), o2.getCountyName());
            }
        });
        Collections.swap(country.getCounties(), 39, 40);
    }

    public void setCounties(List<County> counties) {
        Counties = counties;
    }

    public List<County> getCounties() {
        return Counties;
    }

    public String getName() {
        return name;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }
}