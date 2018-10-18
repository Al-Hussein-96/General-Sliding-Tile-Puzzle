/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Al-Hussein
 */
public class Square {
    private int x;
    private int y;
    private Status status;

    public Square(int x, int y, Status status) {
        this.x = x;
        this.y = y;
        this.status = status;
    }
    
    
    
    enum Status{
        EMPTY,FILLED;
 
    }
    
}
