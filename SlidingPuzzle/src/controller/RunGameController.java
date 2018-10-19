/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.javafx.scene.traversal.Direction;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Cell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javax.jws.Oneway;
import model.Grid;
import model.Slice;
import model.Square;

/**
 * FXML Controller class
 *
 * @author Al-Hussein
 */
public class RunGameController implements Initializable, EventHandler<MouseEvent> {

    private Grid modelGrid;

    @FXML
    private GridPane gridpane;

    @FXML
    private Rectangle rec1;

    @FXML
    private Rectangle rec5;

    @FXML
    private Rectangle rec9;

    @FXML
    private Rectangle rec13;

    @FXML
    private Rectangle rec2;

    @FXML
    private Rectangle rec6;

    @FXML
    private Rectangle rec10;

    @FXML
    private Rectangle rec14;

    @FXML
    private Rectangle rec3;

    @FXML
    private Rectangle rec7;

    @FXML
    private Rectangle rec11;

    @FXML
    private Rectangle rec15;

    @FXML
    private Rectangle rec4;

    @FXML
    private Rectangle rec8;

    @FXML
    private Rectangle rec12;

    @FXML
    private Rectangle rec16;

    public RunGameController(Grid modelGrid) {
        this.modelGrid = modelGrid;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Color[] colors = {Color.BLACK, Color.BLUE, Color.GREEN, Color.RED};

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Rectangle rectangle = (Rectangle) getNodeFromGridPane(gridpane, i, j);
                rectangle.setFill(Color.WHITE);
            }
        }
        initGrid();

        //  gridpane.setOnMouseClicked(this);
        rec1.setOnMouseClicked(this);
        rec2.setOnMouseClicked(this);
        rec3.setOnMouseClicked(this);
        rec4.setOnMouseClicked(this);
        rec5.setOnMouseClicked(this);
        rec6.setOnMouseClicked(this);
        rec7.setOnMouseClicked(this);
        rec8.setOnMouseClicked(this);
        rec9.setOnMouseClicked(this);
        rec10.setOnMouseClicked(this);
        rec11.setOnMouseClicked(this);
        rec12.setOnMouseClicked(this);
        rec13.setOnMouseClicked(this);
        rec14.setOnMouseClicked(this);
        rec15.setOnMouseClicked(this);
        rec16.setOnMouseClicked(this);

    }

    private Node getNodeFromGridPane(GridPane gridPane, int row, int col) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                //       System.out.println("node: " + node.toString());

                return node;
            }
        }
        return null;
    }

    private void initGrid() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Rectangle rectangle = (Rectangle) getNodeFromGridPane(gridpane, i, j);
                rectangle.setFill(Color.WHITE);
            }
        }
        for (Slice slice : modelGrid.getListSlice()) {
            for (Square square : slice.getListSquare()) {
                System.out.println("row,col: " + square.getRow() + " " + square.getCol());
                Rectangle rectangle = (Rectangle) getNodeFromGridPane(this.gridpane, square.getRow(), square.getCol());
                rectangle.setFill(slice.getColor());

            }
        }
    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
        for (Node node : gridpane.getChildren()) {
            ((Rectangle) node).setStrokeWidth(1);
        }
        Node source = (Node) event.getSource();
        Rectangle rectangle = (Rectangle) source;
        Paint color = rectangle.getFill();
        for (Node node : gridpane.getChildren()) {
            if (((Rectangle) node).getFill() == rectangle.getFill()) {
                //  System.out.println("node: " + node.toString());
                ((Rectangle) node).setStrokeWidth(3);
            }
        }

        /**
         * * Move
         */
        gridpane.getScene().setOnKeyPressed((ev) -> {
            System.out.println("ON CLICK KEY");

            boolean move = modelGrid.checkMove(ev.getCode(), rectangle.getFill());
            System.out.println("move: " + move + " : " + ev.getCode());
            if (move) {
                modelGrid.moveSlice(ev.getCode(), rectangle.getFill());
            }

            modelGrid.printGrid();
            modelGrid.printSlices();

            initGrid();
            for (Node node : gridpane.getChildren()) {
                if (((Rectangle) node).getFill() == color) {
                    ((Rectangle) node).setStrokeWidth(3);
                } else {
                    ((Rectangle) node).setStrokeWidth(1);
                }
            }
        });

        System.out.println("node: " + rectangle.toString());

    }

}
