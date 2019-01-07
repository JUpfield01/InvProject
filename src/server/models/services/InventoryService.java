package server.models.services;

import server.Logger;
import server.DatabaseConnection;
import server.models.Inventory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class InventoryService {

    public static String selectAllInto(List<Inventory> targetList) {
        targetList.clear();
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT InventoryId, UserId FROM InventoryLookup"
            );
            if (statement != null) {
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Inventory());


                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select all from 'InventoryLookup' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
        return "OK";
    }


    public static String selectAllIntoByUser(List<Inventory> targetList, int ID) {
        targetList.clear();
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT InventoryId, UserId FROM InventoryLookup where ID = ?"
            );
            if (statement != null) {
                statement.setInt(1, ID);
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Inventory());
                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select all from 'Inventory' table by ID: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
        return "OK";
    }


    public static Inventory selectById(int id) {
        Inventory result = null;
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT InventoryId, UserId FROM InventoryLookup WHERE InventoryId = ?"
            );
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                if (results != null && results.next()) {
                    result = new Inventory();


                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select by id from 'InventoryLookup' table: " + resultsException.getMessage();

            Logger.log(error);
        }
        return result;
    }

    public static String insert(Inventory itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "INSERT INTO InventoryLookup (InventoryId, UserId) VALUES ("
            );
            statement.setInt(1, itemToSave.getInventoryid());
            statement.setInt(2, itemToSave.getUserid());














            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't insert into 'InventoryLookup' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

    public static String update(Inventory itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "UPDATE InventoryLookup SET  WHERE "
            );
            statement.setInt(1, itemToSave.getUserid());














            statement.setInt(2, itemToSave.getInventoryid());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't update 'InventoryLookup' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

    public static String deleteById(int id) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "DELETE FROM InventoryLookup WHERE InventoryId = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't delete by id from 'InventoryLookup' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

}