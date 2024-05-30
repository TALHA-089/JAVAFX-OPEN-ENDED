package com.example.openendedlab.AddProduct;

import com.example.openendedlab.Products;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AddProductController {

    @FXML
    private Button btnAddProduct;
    @FXML
    private Button btnBack;
    @FXML
    private TextField tfProductName;
    @FXML
    private TextField tfProductDescription;
    @FXML
    private TextField tfProductPrice;
    @FXML
    private TextField tfProductCategory;
    @FXML
    private TextField tfImagePath;
    @FXML
    private TextField tfStock;

    @FXML
    protected void Back() throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Products.class.getResource("Products.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("WELCOME!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void AddProduct() {
        String name = tfProductName.getText();
        String description = tfProductDescription.getText();
        double price = Double.parseDouble(tfProductPrice.getText());
        String category = tfProductCategory.getText();
        String imagePath = tfImagePath.getText();
        int stock = Integer.parseInt(tfStock.getText());

        addProductToDatabase(name, description, price, category, imagePath, stock);
    }

    private void addProductToDatabase(String name, String description, double price, String category, String imagePath, int stock) {
        String url = "jdbc:mysql://localhost/Products?serverTimezone=UTC";
        String user = "root";
        String password = "Talha@786";

        String query = "INSERT INTO PRODUCTS (Name, Description, ImagePath, Category, Price, Stock) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, imagePath);
            preparedStatement.setString(4, category);
            preparedStatement.setDouble(5, price);
            preparedStatement.setInt(6, stock);

            preparedStatement.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText(null);
            alert.setContentText("Product Added Successfully!");
            alert.showAndWait();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
