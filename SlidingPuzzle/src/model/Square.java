/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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

}
