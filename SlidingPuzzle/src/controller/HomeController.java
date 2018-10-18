/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Grid;

/**
 *
 * @author Al-Hussein
 */
public class HomeController implements Initializable {

    @FXML
    private Button btnPlay;

    @FXML
    void btnPlay(ActionEvent event) {
        Grid modelGrid = new Grid(4, 4);
        RunGameController runGameController = new RunGameController(modelGrid);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/RunGame.fxml"));

        ((Node) event.getSource()).getScene().getWindow().hide();
        try {
            fxmlLoader.setController(runGameController);
            Scene scene = new Scene((Parent) fxmlLoader.load());
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void btnChoose(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
