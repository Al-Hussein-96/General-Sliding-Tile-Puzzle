/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;
import javafx.scene.paint.Color;

/**
 *
 * @author Al-Hussein
 */
public class Square {

    private int idSquare;
    private int row;
    private int col;
    private Color status;

    public Square(int row, int col, Color status) {
        this.row = row;
        this.col = col;
        this.status = status;
    }

    Square(Square get) {
        this(get.row,get.col,get.status);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Color getStatus() {
        return status;
    }

    public void setStatus(Color status) {
        this.status = status;
    }

    public int getIdSquare() {
        return idSquare;
    }

    public void setIdSquare(int idSquare) {
        this.idSquare = idSquare;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.idSquare;
        hash = 29 * hash + this.row;
        hash = 29 * hash + this.col;
        hash = 29 * hash + Objects.hashCode(this.status);
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
        final Square other = (Square) obj;
        if (this.idSquare != other.idSquare) {
            return false;
        }
        if (this.row != other.row) {
            return false;
        }
        if (this.col != other.col) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        return true;
    }

}
