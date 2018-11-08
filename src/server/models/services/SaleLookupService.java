package server.models.services;

import server.Logger;
import server.DatabaseConnection;
import server.models.SaleLookup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SaleLookupService {

    public static String selectAllInto(List<SaleLookup> targetList) {
        targetList.clear();
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT InventoryId, SalesId FROM SalesLookup"
            );
            if (statement != null) {
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new SaleLookup());


                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select all from 'SalesLookup' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
        return "OK";
    }

    public static SaleLookup selectById(int id) {
        SaleLookup result = null;
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT InventoryId, SalesId FROM SalesLookup WHERE InventoryId = ?"
            );
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                if (results != null && results.next()) {
                    result = new SaleLookup();


                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select by id from 'SalesLookup' table: " + resultsException.getMessage();

            Logger.log(error);
        }
        return result;
    }

    public static String insert(SaleLookup itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "INSERT INTO SalesLookup (InventoryId, SalesId) VALUES ("
            );
            statement.setInt(1, itemToSave.getInventoryid());
            statement.setInt(2, itemToSave.getSalesid());














            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't insert into 'SalesLookup' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

    public static String update(SaleLookup itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "UPDATE SalesLookup SET  WHERE "
            );
            statement.setInt(1, itemToSave.getSalesid());














            statement.setInt(2, itemToSave.getInventoryid());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't update 'SalesLookup' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

    public static String deleteById(int id) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "DELETE FROM SalesLookup WHERE InventoryId = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't delete by id from 'SalesLookup' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

}