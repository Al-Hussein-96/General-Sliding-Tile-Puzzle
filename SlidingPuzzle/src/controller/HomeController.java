/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import algortihms.BFS;
import algortihms.DFS;
import algortihms.Dijkstra;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
        Grid modelGrid = new Grid(4, 4, 7);
        BFS bfs = new BFS(modelGrid);
        Dijkstra dijkstra = new Dijkstra(modelGrid);


        Grid WinGrid1 = bfs.Solve();
        Grid WinGrid2 = dijkstra.Solve();

        List<Grid> path1 = bfs.path(WinGrid1);
        
        List<Grid> path2 = dijkstra.path(WinGrid2);
      //  DFS dfs = new DFS(modelGrid);

        System.out.println("Path1: " + path1.size() + " Path2: " + path2.size());

        RunGameController runGameController = new RunGameController(modelGrid, path1);
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
