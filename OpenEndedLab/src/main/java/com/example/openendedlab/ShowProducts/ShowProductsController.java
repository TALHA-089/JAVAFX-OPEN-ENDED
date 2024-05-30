package com.example.openendedlab.ShowProducts;

import com.example.openendedlab.Model.Product;
import com.example.openendedlab.Products;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShowProductsController {

    @FXML
    private TilePane tilePane;

    @FXML
    private Button btnBack;

    @FXML
    public void initialize() {
        List<Product> products = getProductsFromDatabase();
        for (Product product : products) {
            ImageView imageView = new ImageView(new Image(product.getImageUrl()));
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);

            Label nameLabel = new Label("Name: " + product.getName());
            Label descriptionLabel = new Label("Description: " + product.getDescription());
            Label priceLabel = new Label("Price: $" + product.getPrice());
            Label categoryLabel = new Label("Category: " + product.getCategory());
            Label stockLabel = new Label("Stock: " + product.getStockQuantity());

            VBox tile = new VBox();
            tile.getChildren().addAll(imageView, nameLabel, descriptionLabel, priceLabel, categoryLabel, stockLabel);
            tile.setSpacing(5);

            tilePane.getChildren().add(tile);
        }
    }

    private List<Product> getProductsFromDatabase() {
        List<Product> products = new ArrayList<>();
        String url = "jdbc:mysql://localhost/Products?serverTimezone=UTC";
        String user = "root";
        String password = "Talha@786";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTS");

            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                String description = resultSet.getString("Description");
                double price = resultSet.getDouble("Price");
                String category = resultSet.getString("Category");
                String imagePath = resultSet.getString("ImagePath");
                int stock = resultSet.getInt("Stock");

                products.add(new Product(name, description, price, category, imagePath, stock));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    @FXML
    protected void GoBack() throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Products.class.getResource("Products.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 400);
        stage.setTitle("WELCOME!");
        stage.setScene(scene);
        stage.show();
    }
}
