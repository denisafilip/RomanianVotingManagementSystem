package Model.election.referendum;

public enum ReferendumPosition {
    PRO("Pro"),
    AGAINST("Against");

    private final String label;

    ReferendumPosition(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
