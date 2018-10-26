/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.scene.paint.Color;

/**
 *
 * @author Al-Hussein
 */
public class Slice {

    private Color color;
    private List<Square> listSquare = new ArrayList<>();

    public Slice() {
    }

    public Slice(Color color) {
        this.color = color;
    }

    Slice(Slice slice) {
        this(slice.getColor());
        for (int i = 0; i < slice.getListSquare().size(); i++) {
            listSquare.add(new Square(slice.getListSquare().get(i)));
        }
    }

    public void insertSquare(Square square) {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.color);
        hash = 73 * hash + Objects.hashCode(this.listSquare);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Slice other = (Slice) obj;
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        if (!Objects.equals(this.listSquare, other.listSquare)) {
            return false;
        }
        return true;
    }

    public void print() {
        for (Square u : listSquare) {
            System.out.println("Square: " + u.getRow() + " , " + u.getCol());
        }
    }

}
