package Model.database;

import Model.election.Election;
import Model.election.referendum.Referendum;
import Model.election.referendum.ReferendumPosition;
import Model.politicalParty.PoliticalParty;
import Model.user.User;
import Model.vote.ReferendumPairVote;
import Model.vote.ReferendumVote;

import java.sql.*;

public class DatabaseOperations {

    /** Queries for Model.database, used in prepared statements */
    private static final String INSERT_USER = "INSERT INTO [dbo].[User] VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_COUNTY_ID = "SELECT id FROM [dbo].[County] WHERE name = ?";
    private static final String CHECK_IF_USER_IN_DATABASE = "SELECT * from [dbo].[User] WHERE mail = ? and password = ? or CNP = ?";
    private static final String GET_USER_BY_CNP = "SELECT * from [dbo].[User] WHERE CNP = ?";
    private static final String GET_USER_COUNTY = "SELECT idCounty, name from [dbo].[User] INNER JOIN [dbo].[County] ON [User].idCounty = County.id WHERE CNP = ?";
    private static final String GET_USER_ADMIN_STATUS = "SELECT isAdmin from [dbo].[User] WHERE CNP = ?";
    private static final String GET_USER_BY_MAIL_AND_PASSWORD = "SELECT * from [dbo].[User] WHERE mail = ? and password = ?";

    private static final double NO_OF_VOTERS_IN_ROMANIA = 18191396;

    /**
     * @param u - the User which we want to insert into the Model.database
     * inserts Model.user's data into User table, from Microsoft SQL Server Database
     */
    public void insertUserIntoDatabase(User u)  {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement selectFromCounty = conn.prepareStatement(GET_COUNTY_ID)) {
            selectFromCounty.setString(1, u.getCounty().getCountyName());
            int idCounty = 0;
            try (ResultSet resultSet = selectFromCounty.executeQuery()){
                while (resultSet.next()) {
                    idCounty = resultSet.getInt("id");
                }
            }

            try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_USER)){
                preparedStatement.setString(1, u.getFirstName());
                preparedStatement.setString(2, u.getLastName());
                preparedStatement.setString(3, u.getMail());
                preparedStatement.setString(4, u.getPassword());
                preparedStatement.setString(5, u.getGender());
                preparedStatement.setDate(6, Date.valueOf(u.getDateOfBirth()));
                preparedStatement.setInt(7, u.getAge());
                preparedStatement.setString(8, u.getCNP());
                preparedStatement.setInt(9, idCounty);
                preparedStatement.setBoolean(10, u.getPresidential());
                preparedStatement.setBoolean(11, u.getEuropeanParliament());
                preparedStatement.setBoolean(12, u.getLocal());
                preparedStatement.setBoolean(13, u.getFirstReferendum());
                preparedStatement.setBoolean(14, u.getSecondReferendum());
                preparedStatement.setBoolean(15, u.isAdmin());
                preparedStatement.setBoolean(16, u.getSenateParliament());
                preparedStatement.setBoolean(17, u.getDeputiesParliament());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** @return true if the Model.user is already found in the Model.database */
    public Boolean isUserInDatabaseWithCNP(User u) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(CHECK_IF_USER_IN_DATABASE)) {
            preparedStatement.setString(1, u.getMail());
            preparedStatement.setString(2, u.getPassword());
            preparedStatement.setString(3, u.getCNP());

           try (ResultSet resultSet = preparedStatement.executeQuery()) {
               return resultSet.next();
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Get Model.user from Model.database, based on email and password combination - used for log in.
     * @return true if the Model.user is found in the Model.database
     */
    public Boolean isUserInDatabaseForLogIn(User u) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_USER_BY_MAIL_AND_PASSWORD)) {
            preparedStatement.setString(1, u.getMail());
            preparedStatement.setString(2, u.getPassword());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /** Getting the CNP of the Model.user, based on his email and password combination - for log in */
    public String getUserCNP(User u) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_USER_BY_MAIL_AND_PASSWORD)) {
            preparedStatement.setString(1, u.getMail());
            preparedStatement.setString(2, u.getPassword());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                String cnp = null;
                while (resultSet.next()) {
                    cnp = resultSet.getString("CNP");
                }
                return cnp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param CNP - CNP of Model.user
     * @param election - the Model.election for which we want to increment the number of votes (presidential, euro, local, parliament)
     * @return true if Model.user has voted, false on the contrary
     */
    public Boolean hasUserVoted(String CNP, Election election) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_USER_BY_CNP)) {
            preparedStatement.setString(1, CNP);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                boolean hasVoted = false;
                while (resultSet.next()) {
                    hasVoted = resultSet.getBoolean(election.getType().getLabel());
                }
                return hasVoted;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Function that increments the number of specific votes of a political party in the PoliticalParty table from the SQL Server Model.database, after an Model.user has voted for them
     * @param election - the Model.election for which we want to increment the number of votes (presidential, euro, local, parliament)
     * @param politicalParty - the political party for which we want to increment the number of votes
     */
    public void incrementVotesForParty(Election election, PoliticalParty politicalParty) {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement statement = conn.createStatement()) {
            String query = "UPDATE [dbo].[PoliticalParty] SET " + election.getType().getLabel() + " = " + election.getType().getLabel() + " + 1 WHERE abbreviation = '" + politicalParty.getAbbreviation() + "'";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** After an Model.user placed his Model.vote, change his voting status for the respective Model.election to true - this doesn't allow an Model.user to vot multiple
     * times in the same Model.election.
     */
    public void updateUserVotingStatus(Election election, String CNP) {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement statement = conn.createStatement()) {
            String query = "UPDATE [dbo].[User] SET " + election.getType().getLabel() + " = 'true' WHERE CNP = '" + CNP + "'";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Gets the ID of the Model.user's county (the one that appears on the CNP) */
    public int getUserCountyById(String CNP) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_USER_COUNTY)) {
            preparedStatement.setString(1, CNP);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                int idCounty = 0;
                while (resultSet.next()) {
                    idCounty = resultSet.getInt("idCounty");
                }
                return idCounty;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /** @return the name of the county the Model.user (that appears on the CNP) */
    public String getUserCountyByName(String CNP) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_USER_COUNTY)) {
            preparedStatement.setString(1, CNP);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                String countyName = null;
                while (resultSet.next()) {
                    countyName = resultSet.getString("name");
                }
                return countyName;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** After an Model.user has voted, increment the total number of votes placed in the Model.user's county */
    public void incrementVotesForCounty(Election election, String CNP) {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement increaseNoOfVotesForCounty = conn.createStatement()) {
            int idCounty = getUserCountyById(CNP);
            String query = "UPDATE [dbo].[County] SET " + election.getType().getLabel() + " = " + election.getType().getLabel() + " + 1 WHERE id = " + idCounty;
            increaseNoOfVotesForCounty.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void incrementVotesForCountyReferendum(Election election, String CNP, String label) {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement increaseNoOfVotesForCounty = conn.createStatement()) {
            String voteName = election.getType().getLabel() + label;
            int idCounty = getUserCountyById(CNP);
            String query = "UPDATE [dbo].[County] SET " + voteName + " = " + voteName + " + 1 WHERE id = " + idCounty;
            increaseNoOfVotesForCounty.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param election - the Model.election for which we want to select the number of votes
     * @param politicalPartyAbbr - abbreviation of the political party whose votes we want to see
     * @return the number of votes of the political party obtained in a specific Model.election
     */
    public int getNoOfVotesPoliticalParty(Election election, String politicalPartyAbbr) {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement statement = conn.createStatement()) {
            String query = "SELECT " + election.getType().getLabel() + " FROM [dbo].[PoliticalParty] WHERE abbreviation = '" + politicalPartyAbbr + "'";
            try (ResultSet resultSet = statement.executeQuery(query)) {
                int noOfVotes = 0;
                while (resultSet.next()) {
                    noOfVotes = resultSet.getInt(election.getType().getLabel());
                }
                return noOfVotes;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /** @return the number of votes placed in a county at a specific type of Model.election */
    public int getNoOfVotesCounty(Election election, String countyName) {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement statement = conn.createStatement()) {
            String query = "SELECT " + election.getType().getLabel() + " FROM [dbo].[County] WHERE name = N'" + countyName + "'";
            try (ResultSet resultSet = statement.executeQuery(query)) {
                int noOfVotes = 0;
                while (resultSet.next()) {
                    noOfVotes = resultSet.getInt(election.getType().getLabel());
                }
                return noOfVotes;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /** @return a pair of Referendum Votes, containing the number of pro and against votes placed in a county for a referendum question*/
    public ReferendumPairVote getNoOfVotesCountyReferendum(Referendum referendum, String countyName) {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement statement = conn.createStatement();
             Statement statement1 = conn.createStatement()) {
            String againstVoteName = referendum.getType().getLabel() + referendum.getReferendumVotes().getAgainstVote().getPosition().getLabel();
            String proVoteName = referendum.getType().getLabel() + referendum.getReferendumVotes().getProVote().getPosition().getLabel();

            String query2 = "SELECT " + proVoteName + " FROM [dbo].[County] WHERE name = N'" + countyName + "'";
            String query = "SELECT " + againstVoteName + " FROM [dbo].[County] WHERE name = N'" + countyName + "'";
            ReferendumPairVote referendumVotes = new ReferendumPairVote(new ReferendumVote(referendum.getType(), ReferendumPosition.PRO), new ReferendumVote(referendum.getType(), ReferendumPosition.AGAINST));

            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    referendumVotes.getAgainstVote().setNoOfVotes(resultSet.getInt(againstVoteName));
                }
            }
            try (ResultSet resultSet1 = statement1.executeQuery(query2)) {
                while (resultSet1.next()) {
                    referendumVotes.getProVote().setNoOfVotes(resultSet1.getInt(proVoteName));
                }
            }
            return referendumVotes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Function that checks the Model.user's admin status, in order to grant him permission to the admin page.
     * @return true if Model.user is an admin, who can see statistics about voting */
    public Boolean checkIfUserIsAdmin(String CNP) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_USER_ADMIN_STATUS)) {
            preparedStatement.setString(1, CNP);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                boolean isAdmin = false;
                while (resultSet.next()) {
                    isAdmin = resultSet.getBoolean("isAdmin");
                }
                return isAdmin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /** @return a double value, which represents the attendance at a specific Model.election, with respect to the total number of eligible voters from Romania */
    public double computeVoteAttendance(Election election) {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement statement = conn.createStatement()) {
            String query = "SELECT SUM(" + election.getType().getLabel() + ") AS SUM FROM [dbo].[County]";
            try (ResultSet resultSet = statement.executeQuery(query)) {
                double sumOfVotes = 0;
                while (resultSet.next()) {
                    sumOfVotes = resultSet.getInt("SUM");
                }
                return (sumOfVotes / NO_OF_VOTERS_IN_ROMANIA) * 100.0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
