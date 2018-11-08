package server.models.services;

import server.Logger;
import server.DatabaseConnection;
import server.models.TokenCheck;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TokenCheckService {

    public static String selectAllInto(List<TokenCheck> targetList) {
        targetList.clear();
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT Username, Password, SessionToken FROM TokenChecker"
            );
            if (statement != null) {
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new TokenCheck());


                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select all from 'TokenChecker' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
        return "OK";
    }

    public static TokenCheck selectById(int id) {
        TokenCheck result = null;
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT Username, Password, SessionToken FROM TokenChecker WHERE Username = ?"
            );
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                if (results != null && results.next()) {
                    result = new TokenCheck();


                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select by id from 'TokenChecker' table: " + resultsException.getMessage();

            Logger.log(error);
        }
        return result;
    }


    public static String update(TokenCheck itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "UPDATE TokenChecker SET  WHERE "
            );
            statement.setString(2, itemToSave.getSessiontoken());
            statement.setString(3, itemToSave.getUsername());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't update 'TokenChecker' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }


}