package Model.vote;

import Model.election.enums.ElectionType;

public class UserVote extends Vote {
    /** Status of the Model.vote: true if it has been placed by the Model.user, false on the contrary.*/
    private boolean status;

    public UserVote(ElectionType type, boolean status) {
        super(type);
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
