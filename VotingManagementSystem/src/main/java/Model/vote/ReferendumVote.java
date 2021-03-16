package Model.vote;

import Model.election.enums.ElectionType;
import Model.election.referendum.ReferendumPosition;

public class ReferendumVote extends Vote {
    /** Position of Model.user regarding the question of the referendum. Can be pro or against.*/
    private final ReferendumPosition position;
    private int noOfVotes;

    public ReferendumVote(ElectionType type, ReferendumPosition position) {
        super(type);
        this.position = position;
    }

    public ReferendumPosition getPosition() {
        return position;
    }

    public int getNoOfVotes() {
        return noOfVotes;
    }

    public void setNoOfVotes(int noOfVotes) {
        this.noOfVotes = noOfVotes;
    }

    public void increaseNoOfVotes(int noOfVotes) {
        this.noOfVotes += noOfVotes;
    }
}
