package server.models.services;

import server.Logger;
import server.DatabaseConnection;
import server.models.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductService {

    public static String selectAllInto(List<Product> targetList) {
        targetList.clear();
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT ProductID, InventoryId, ProductName, ProductDescription, ProductCost, Quantity, ImageURL FROM Products"
            );
            if (statement != null) {
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Product(results.getInt("ProductID"), results.getInt("InventoryId"), results.getString("ProductName"), results.getString("ProductDescription"), results.getFloat("ProductCost"), results.getInt("Quantity"), results.getString("ImageURL")));


                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select all from 'Products' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
        return "OK";
    }

    public static Product selectById(int id) {
        Product result = null;
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT ProductID, InventoryId, ProductName, ProductDescription, ProductCost, Quantity, ImageURL FROM Products WHERE ProductID = ?"
            );
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                if (results != null && results.next()) {
                    result = new Product(results.getInt("ProductID"), results.getInt("InventoryId"), results.getString("ProductName"), results.getString("ProductDescription"), results.getFloat("ProductCost"), results.getInt("Quantity"), results.getString("ImageURL"));


                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select by id from 'Products' table: " + resultsException.getMessage();

            Logger.log(error);
        }
        return result;
    }

    public static String insert(Product itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "INSERT INTO Products (ProductID, InventoryId, ProductName, ProductDescription, ProductCost, Quantity, ImageURL) VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            statement.setInt(1, itemToSave.getProductid());
            statement.setInt(2, itemToSave.getInventoryid());
            statement.setString(3, itemToSave.getProductname());
            statement.setString(4, itemToSave.getProductdescription());
            statement.setFloat(5, itemToSave.getProductcost());
            statement.setInt(6, itemToSave.getQuantity());
            statement.setString(7, itemToSave.getImageurl());









            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't insert into 'Products' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

    public static String update(Product itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "UPDATE Products SET InventoryId = ?, ProductName = ?, ProductDescription = ?, ProductCost = ?, Quantity = ?, ImageURL = ? WHERE ProductID = ?"
            );
            statement.setInt(1, itemToSave.getInventoryid());
            statement.setString(2, itemToSave.getProductname());
            statement.setString(3, itemToSave.getProductdescription());
            statement.setFloat(4, itemToSave.getProductcost());
            statement.setInt(5, itemToSave.getQuantity());
            statement.setString(6, itemToSave.getImageurl());









            statement.setInt(7, itemToSave.getProductid());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't update 'Products' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

    public static String deleteById(int id) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "DELETE FROM Products WHERE ProductID = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't delete by id from 'Products' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

}