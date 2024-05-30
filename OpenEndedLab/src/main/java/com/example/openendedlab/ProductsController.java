package com.example.openendedlab;

import com.example.openendedlab.Model.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductsController {

    @FXML
    private Button btnExit;
    @FXML
    private Button btnAddProduct;
    @FXML
    private Button btnShowProducts;

    @FXML
    protected void GoToAddProduct() throws IOException {
        Stage stage = (Stage) btnAddProduct.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddProduct.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void GoToShowProducts() throws IOException {
        Stage stage = (Stage) btnShowProducts.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShowProducts.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void ExitApplication(){
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

}
