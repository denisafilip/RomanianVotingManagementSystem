package Model.vote;

import Model.election.enums.ElectionType;

public class Vote {
    private ElectionType type;

    public Vote(ElectionType type) {
        this.type = type;
    }

    public ElectionType getType() {
        return type;
    }

    public void setType(ElectionType type) {
        this.type = type;
    }
}
