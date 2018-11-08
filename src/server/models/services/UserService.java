package server.models.services;

import server.Logger;
import server.DatabaseConnection;
import server.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    public static String selectAllInto(List<User> targetList) {
        targetList.clear();
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT UserId, UserName, UserEmail, UserPassword, UserDOB FROM UserInfo"
            );
            if (statement != null) {
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new User());


                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select all from 'UserInfo' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
        return "OK";
    }

    public static User selectById(int id) {
        User result = null;
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT UserId, UserName, UserEmail, UserPassword, UserDOB FROM UserInfo WHERE UserId = ?"
            );
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                if (results != null && results.next()) {
                    result = new User();


                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select by id from 'UserInfo' table: " + resultsException.getMessage();

            Logger.log(error);
        }
        return result;
    }

    public static String insert(User itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "INSERT INTO UserInfo (UserId, UserName, UserEmail, UserPassword, UserDOB) VALUES ("
            );
            statement.setString(1, itemToSave.getUserid());
            statement.setString(2, itemToSave.getUsername());
            statement.setString(3, itemToSave.getUseremail());
            statement.setString(4, itemToSave.getUserpassword());
            statement.setString(5, itemToSave.getUserdob());











            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't insert into 'UserInfo' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

    public static String update(User itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "UPDATE UserInfo SET  WHERE "
            );
            statement.setString(1, itemToSave.getUsername());
            statement.setString(2, itemToSave.getUseremail());
            statement.setString(3, itemToSave.getUserpassword());
            statement.setString(4, itemToSave.getUserdob());
            statement.setString(5, itemToSave.getUserid());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't update 'UserInfo' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

    public static String deleteById(int id) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "DELETE FROM UserInfo WHERE UserId = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't delete by id from 'UserInfo' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

}