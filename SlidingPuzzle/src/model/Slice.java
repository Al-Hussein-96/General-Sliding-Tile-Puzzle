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
    private List<Square> listSquare = new ArrayList<>();
    
    public void insertSquare(Square square){
        this.listSquare.add(square);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List<Square> getListSquare() {
        return listSquare;
    }

    public void setListSquare(List<Square> listSquare) {
        this.listSquare = listSquare;
    }
    
    
    
}
