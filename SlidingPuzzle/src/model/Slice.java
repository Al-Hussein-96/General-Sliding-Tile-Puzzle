/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

/**
 *
 * @author Al-Hussein
 */
public class Slice {
    private Color color;
    private final List<Square> listSquare = new ArrayList<>();
    
    public void insertSquare(Square square){
        this.listSquare.add(square);
    }
    
}
