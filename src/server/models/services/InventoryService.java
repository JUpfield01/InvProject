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
                    "SELECT ProductId, UserId FROM InventoryLookUp"
            );
            if (statement != null) {
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Inventory(results.getInt("ProductId"), results.getInt("UserId")));


                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select all from 'InventoryLookUp' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
        return "OK";
    }

    public static Inventory selectById(int id) {
        Inventory result = null;
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT ProductId, UserId FROM InventoryLookUp WHERE ProductId = ?"
            );
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                if (results != null && results.next()) {
                    result = new Inventory(results.getInt("ProductId"), results.getInt("UserId"));


                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select by id from 'InventoryLookUp' table: " + resultsException.getMessage();

            Logger.log(error);
        }
        return result;
    }

    public static String insert(Inventory itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "INSERT INTO InventoryLookUp (ProductId, UserId) VALUES (?, ?)"
            );
            statement.setInt(1, itemToSave.getProductId());
            statement.setInt(2, itemToSave.getUserid());














            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't insert into 'InventoryLookUp' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

    public static String update(Inventory itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "UPDATE InventoryLookUp SET UserId = ? WHERE ProductId = ?"
            );
            statement.setInt(1, itemToSave.getUserid());














            statement.setInt(2, itemToSave.getProductId());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't update 'InventoryLookUp' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

    public static String deleteById(int id) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "DELETE FROM InventoryLookUp WHERE ProductId = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't delete by id from 'InventoryLookUp' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

}