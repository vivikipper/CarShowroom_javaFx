package com.example.carshowroom;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.*;

public class HelloController {
    @FXML
    private TextField nameOfCar;

    @FXML
    private TextField loanTerm;

    @FXML
    private Text message;

    @FXML
    private Text errorMessage;

    @FXML
    protected void calculateLoan() {
        String SQL_SELECT = "Select price from cars where carname=?";

        double price=0;
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3307/carinventory", "root", "root123");
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

            // 1 replaces the first ? by name of car.
             preparedStatement.setString(1,nameOfCar.getText());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                price= resultSet.getDouble("price");
            }
            if(price==0){
                errorMessage.setVisible(true);
                message.setVisible(false);
            }else{
                errorMessage.setVisible(false);
                message.setVisible(true);

                double term = Double.parseDouble(loanTerm.getText());
                double payment = price/(term*12);

                message.setText("You have to pay "+payment+" every month.");
            }

         System.out.println("the price is "+price);

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}