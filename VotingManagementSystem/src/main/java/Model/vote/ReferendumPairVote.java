package Model.vote;

public class ReferendumPairVote {
    private ReferendumVote proVote;
    private ReferendumVote againstVote;

    public ReferendumPairVote() {

    }

    public ReferendumPairVote(ReferendumVote proVote, ReferendumVote againstVote) {
        this.proVote = proVote;
        this.againstVote = againstVote;
    }

    public ReferendumVote getProVote() {
        return proVote;
    }

    public void setProVote(ReferendumVote proVote) {
        this.proVote = proVote;
    }

    public ReferendumVote getAgainstVote() {
        return againstVote;
    }

    public void setAgainstVote(ReferendumVote againstVote) {
        this.againstVote = againstVote;
    }

    public void increaseNumberOfVotes(ReferendumPairVote votes) {
        this.proVote.increaseNoOfVotes(votes.getProVote().getNoOfVotes());
        this.againstVote.increaseNoOfVotes(votes.getAgainstVote().getNoOfVotes());
    }

    public int getTotalVotesReferendum() {
        return this.proVote.getNoOfVotes() + this.againstVote.getNoOfVotes();
    }
}
