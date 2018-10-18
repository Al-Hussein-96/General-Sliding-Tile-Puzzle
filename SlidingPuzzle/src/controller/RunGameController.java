/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import model.Grid;

/**
 * FXML Controller class
 *
 * @author Al-Hussein
 */
public class RunGameController implements Initializable {
    private Grid modelGrid;

    public RunGameController(Grid modelGrid) {
        this.modelGrid = modelGrid;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
