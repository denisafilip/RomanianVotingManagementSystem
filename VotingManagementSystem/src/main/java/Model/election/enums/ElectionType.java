package Model.election.enums;

public enum ElectionType {
    PRESIDENTIAL("Presidential", "Alegeri Prezidențiale"),
    PARLIAMENT("Parliament", "Alegeri Parlamentare"),
    SENATE_PARLIAMENT("SenateParliament", "Alegeri Parlamentare - Senat"),
    CHAMBER_OF_DEPUTIES_PARLIAMENT("ChamberOfDeputiesParliament", "Alegeri Parlamentare - Camera Deputaților"),
    LOCAL("Local", "Alegeri Locale"),
    EUROPEAN_PARLIAMENT("EuropeanParliament", "Alegeri Europarlamentare"),
    REFERENDUM_QUESTION_1("ReferendumQuestion1", "Referendum - Prima Întrebare"),
    REFERENDUM_QUESTION_2("ReferendumQuestion2", "Referendum - Prima Întrebare");

    private final String label;
    private final String labelRomanian;

    ElectionType(String label, String labelRomanian) {
        this.label = label;
        this.labelRomanian = labelRomanian;
    }

    public String getLabel() {
        return label;
    }

    public String getLabelRomanian() {
        return labelRomanian;
    }
}
