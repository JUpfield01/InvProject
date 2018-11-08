package server.models.services;

import server.Logger;
import server.DatabaseConnection;
import server.models.Sale;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SaleService {

    public static String selectAllInto(List<Sale> targetList) {
        targetList.clear();
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT SalesId, ProductId, AmountSold FROM Sales"
            );
            if (statement != null) {
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Sale());


                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select all from 'Sales' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
        return "OK";
    }

    public static Sale selectById(int id) {
        Sale result = null;
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT SalesId, ProductId, AmountSold FROM Sales WHERE SalesId = ?"
            );
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                if (results != null && results.next()) {
                    result = new Sale();


                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select by id from 'Sales' table: " + resultsException.getMessage();

            Logger.log(error);
        }
        return result;
    }

    public static String insert(Sale itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "INSERT INTO Sales (SalesId, ProductId, AmountSold) VALUES ("
            );
            statement.setInt(1, itemToSave.getSalesid());
            statement.setInt(2, itemToSave.getProductid());
            statement.setInt(3, itemToSave.getAmountsold());













            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't insert into 'Sales' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

    public static String update(Sale itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "UPDATE Sales SET  WHERE "
            );
            statement.setInt(1, itemToSave.getProductid());
            statement.setInt(2, itemToSave.getAmountsold());













            statement.setInt(3, itemToSave.getSalesid());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't update 'Sales' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

    public static String deleteById(int id) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "DELETE FROM Sales WHERE SalesId = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't delete by id from 'Sales' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

}