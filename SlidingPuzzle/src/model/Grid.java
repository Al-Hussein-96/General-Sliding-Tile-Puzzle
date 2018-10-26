package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author Al-Hussein
 */
public class Grid {

    //  private static int Id = 1;
    private int Height;
    private int Weight;
    private int NumGrid;
    private Square[][] CurGrid;
    private List<Slice> listSlice = new ArrayList<>();

    public Grid(Grid grid) {
        this.Weight = grid.Weight;
        this.Height = grid.Height;
        this.CurGrid = new Square[Height][Weight];
        this.initGrid();

        for (int i = 0; i < grid.listSlice.size(); i++) {
            this.listSlice.add(new Slice(grid.listSlice.get(i)));
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.CurGrid[i][j] = new Square(i, j, grid.CurGrid[i][j].getStatus());
            }
        }
    }

    public Grid(int Height, int Weight, int NumGrid) {
        this.Height = Height;
        this.Weight = Weight;
        this.NumGrid = NumGrid;
        this.CurGrid = new Square[this.Height][this.Weight];
        addSlices(NumGrid);

        initGrid();

    }

    private void initGrid() {
        for (int i = 0; i < this.Height; i++) {
            for (int j = 0; j < this.Weight; j++) {
                this.CurGrid[i][j] = new Square(i, j, Color.WHITE);
            }
        }
        for (Slice s : listSlice) {
            for (Square square : s.getListSquare()) {
                this.CurGrid[square.getRow()][square.getCol()] = square;
            }
        }
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int Height) {
        this.Height = Height;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int Weight) {
        this.Weight = Weight;
    }

    public Square[][] getCurGrid() {
        return CurGrid;
    }

    public void setCurGrid(Square[][] CurGrid) {
        this.CurGrid = CurGrid;
    }

    public List<Slice> getListSlice() {
        return listSlice;
    }

    public void setListSlice(List<Slice> listSlice) {
        this.listSlice = listSlice;
    }

    public boolean checkMove(KeyCode direction, Paint fill) {
        Slice slice = null;

        for (int i = 0; i < this.listSlice.size(); i++) {
            if (this.listSlice.get(i).getColor() == fill) {
                slice = this.listSlice.get(i);
            }
        }
        if (slice == null) {
            return false;
        }
        //   System.out.println("dir: " + direction.toString());
        switch (direction) {
            case DOWN:
                for (int i = 0; i < slice.getListSquare().size(); i++) {
                    Square square = slice.getListSquare().get(i);
                    int row = square.getRow() + 1;
                    int col = square.getCol();
                    if (inside(row, col) == false) {
                        return false;

                    }
                    if (!this.CurGrid[row][col].getStatus().equals(Color.WHITE)
                            && !this.CurGrid[row][col].getStatus().equals(slice.getColor())) {
                        return false;
                    }
                }
                break;
            case UP:
                for (int i = 0; i < slice.getListSquare().size(); i++) {
                    Square square = slice.getListSquare().get(i);
                    int row = square.getRow() - 1;
                    int col = square.getCol();
                    if (inside(row, col) == false) {
                        return false;

                    }
                    if (!this.CurGrid[row][col].getStatus().equals(Color.WHITE)
                            && !this.CurGrid[row][col].getStatus().equals(slice.getColor())) {
                        return false;
                    }
                }
                break;
            case RIGHT:
                for (int i = 0; i < slice.getListSquare().size(); i++) {
                    Square square = slice.getListSquare().get(i);
                    int row = square.getRow();
                    int col = square.getCol() + 1;
                    if (inside(row, col) == false) {
                        return false;

                    }
                    if (!this.CurGrid[row][col].getStatus().equals(Color.WHITE)
                            && !this.CurGrid[row][col].getStatus().equals(slice.getColor())) {
                        return false;
                    }
                }
                break;
            case LEFT:
                for (int i = 0; i < slice.getListSquare().size(); i++) {
                    Square square = slice.getListSquare().get(i);
                    int row = square.getRow();
                    int col = square.getCol() - 1;
                    if (inside(row, col) == false) {
                        return false;

                    }
                    if (!this.CurGrid[row][col].getStatus().equals(Color.WHITE)
                            && !this.CurGrid[row][col].getStatus().equals(slice.getColor())) {
                        return false;
                    }
                }
                break;
        }

        return true;
    }

    private boolean inside(int row, int col) {
        return row >= 0 && row < this.Weight && col >= 0 && col < Height;
    }

    public void moveSlice(KeyCode keyCode, Paint fill) {
        Slice slice = null;

        for (int i = 0; i < this.listSlice.size(); i++) {
            if (this.listSlice.get(i).getColor() == fill) {
                slice = this.listSlice.get(i);
            }
        }
        if (slice == null) {
            return;
        }
        for (int i = 0; i < slice.getListSquare().size(); i++) {
            Square square = slice.getListSquare().get(i);
            int row = square.getRow() + (keyCode.equals(KeyCode.DOWN) == true ? 1 : 0) - (keyCode.equals(KeyCode.UP) == true ? 1 : 0);
            int col = square.getCol() + (keyCode.equals(KeyCode.RIGHT) == true ? 1 : 0) - (keyCode.equals(KeyCode.LEFT) == true ? 1 : 0);
            //System.out.println("ROW ROW:: " + row);
            square.setRow(row);
            square.setCol(col);
        }
//        initGrid();
        buildGrid();
    }

    public Grid moveSliceCopy(KeyCode keyCode, Paint fill) {
        Slice slice = null;

        for (int i = 0; i < this.listSlice.size(); i++) {
            if (this.listSlice.get(i).getColor() == fill) {
                slice = this.listSlice.get(i);
            }
        }
        if (slice == null) {
            return null;
        }
        for (int i = 0; i < slice.getListSquare().size(); i++) {
            Square square = slice.getListSquare().get(i);
            int row = square.getRow() + (keyCode.equals(KeyCode.DOWN) == true ? 1 : 0) - (keyCode.equals(KeyCode.UP) == true ? 1 : 0);
            int col = square.getCol() + (keyCode.equals(KeyCode.RIGHT) == true ? 1 : 0) - (keyCode.equals(KeyCode.LEFT) == true ? 1 : 0);
            // System.out.println("ROW ROW:: " + row);
            square.setRow(row);
            square.setCol(col);
        }
        buildGrid();
        return this;
    }

    private void buildGrid() {
        for (int i = 0; i < this.Height; i++) {
            for (int j = 0; j < this.Weight; j++) {
                this.CurGrid[i][j].setStatus(Color.WHITE);
            }
        }
        for (Slice u : this.getListSlice()) {
            for (Square square : u.getListSquare()) {
                int row = square.getRow();
                int col = square.getCol();
                this.CurGrid[row][col].setStatus(square.getStatus());
            }
        }
    }

    public boolean isWinning() {
        Slice slice = null;
        for (Slice u : listSlice) {
            if (u.getColor() == Color.RED) {
                slice = u;
            }
        }

        if (slice == null) {
            return false;
        }
        boolean ok1 = false, ok2 = false;
        for (Square u : slice.getListSquare()) {
            if (!inside(u.getRow() + 1, u.getCol())) {
                ok1 = true;
            }
            if (!inside(u.getRow(), u.getCol() + 1)) {
                ok2 = true;
            }
        }
        return ok1 && ok2;
    }

    public List<Grid> getPossibleMove() {
        List<Grid> grids = new ArrayList<>();
        for (Slice u : listSlice) {
//            System.out.println("Slice Square:");
//            u.print();
            if (checkMove(KeyCode.UP, u.getColor())) {
                Grid newGrid = new Grid(this);
                newGrid.moveSlice(KeyCode.UP, u.getColor());
                grids.add(newGrid);
            }
            if (checkMove(KeyCode.DOWN, u.getColor())) {
                Grid newGrid = new Grid(this);
                //     newGrid.printGrid();
                newGrid.moveSlice(KeyCode.DOWN, u.getColor());
                grids.add(newGrid);
            }
            if (checkMove(KeyCode.RIGHT, u.getColor())) {
                Grid newGrid = new Grid(this);
                newGrid.moveSlice(KeyCode.RIGHT, u.getColor());
                grids.add(newGrid);
            }
            if (checkMove(KeyCode.LEFT, u.getColor())) {
                Grid newGrid = new Grid(this);
                newGrid.moveSlice(KeyCode.LEFT, u.getColor());
                grids.add(newGrid);
            }
        }
        return grids;
    }

    public void printGrid() {
        for (int i = 0; i < this.Weight; i++) {
            {
                for (int j = 0; j < this.Height; j++) {
                    System.out.print(this.CurGrid[i][j].getStatus() + " ");
                }
                System.out.println("");
            }
        }
    }

    public void printSlices() {
        for (Slice slice : getListSlice()) {
            System.out.println("Slice : " + slice.getColor());
            for (Square square : slice.getListSquare()) {
                System.out.println("Square: " + square.getRow() + " , " + square.getCol());
            }
            System.out.println("");
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.Height;
        hash = 79 * hash + this.Weight;
        hash = 79 * hash + Arrays.deepHashCode(this.CurGrid);
        hash = 79 * hash + Objects.hashCode(this.listSlice);
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
        final Grid other = (Grid) obj;
        if (!Arrays.deepEquals(this.CurGrid, other.CurGrid)) {
            return false;
        }
        return true;
    }

    private void addFirstSlice() {

    }

    private void addSecondSlice() {

    }

    private void addSlices(int NumGrid) {
        Slice slice = new Slice();

        switch (NumGrid) {
            case 1:
                slice.setColor(Color.RED);
                slice.insertSquare(new Square(0, 0, Color.RED));
                slice.insertSquare(new Square(0, 1, Color.RED));
                slice.insertSquare(new Square(0, 2, Color.RED));
                slice.insertSquare(new Square(1, 0, Color.RED));
                slice.insertSquare(new Square(1, 2, Color.RED));
                slice.insertSquare(new Square(2, 2, Color.RED));
                this.listSlice.add(slice);
                slice = new Slice();
                slice.setColor(Color.YELLOW);
                slice.insertSquare(new Square(1, 1, Color.YELLOW));
                slice.insertSquare(new Square(2, 0, Color.YELLOW));
                slice.insertSquare(new Square(2, 1, Color.YELLOW));
                this.listSlice.add(slice);
                break;
            case 2:
                slice.setColor(Color.RED);
                slice.insertSquare(new Square(1, 0, Color.RED));
                slice.insertSquare(new Square(2, 0, Color.RED));
                slice.insertSquare(new Square(2, 1, Color.RED));
                this.listSlice.add(slice);
                slice = new Slice();
                slice.setColor(Color.YELLOW);
                slice.insertSquare(new Square(1, 2, Color.YELLOW));
                slice.insertSquare(new Square(2, 2, Color.YELLOW));
                slice.insertSquare(new Square(2, 3, Color.YELLOW));
                slice.insertSquare(new Square(3, 3, Color.YELLOW));
                this.listSlice.add(slice);
                slice = new Slice();
                slice.setColor(Color.BLUE);
                slice.insertSquare(new Square(3, 1, Color.BLUE));
                slice.insertSquare(new Square(3, 2, Color.BLUE));
                this.listSlice.add(slice);
                break;
            case 3:
                slice.setColor(Color.RED);
                slice.insertSquare(new Square(0, 2, Color.RED));
                slice.insertSquare(new Square(0, 3, Color.RED));
                slice.insertSquare(new Square(1, 2, Color.RED));
                this.listSlice.add(slice);
                slice = new Slice();
                slice.setColor(Color.YELLOW);
                slice.insertSquare(new Square(1, 0, Color.YELLOW));
                slice.insertSquare(new Square(1, 1, Color.YELLOW));
                this.listSlice.add(slice);
                slice = new Slice();
                slice.setColor(Color.BLUE);
                slice.insertSquare(new Square(2, 1, Color.BLUE));
                slice.insertSquare(new Square(3, 0, Color.BLUE));
                slice.insertSquare(new Square(3, 1, Color.BLUE));
                this.listSlice.add(slice);
                slice = new Slice();
                slice.setColor(Color.DARKGOLDENROD);
                slice.insertSquare(new Square(2, 2, Color.DARKGOLDENROD));
                slice.insertSquare(new Square(3, 2, Color.DARKGOLDENROD));
                this.listSlice.add(slice);
                break;
            case 4:
                slice.setColor(Color.RED);
                slice.insertSquare(new Square(0, 0, Color.RED));
                slice.insertSquare(new Square(1, 0, Color.RED));
                this.listSlice.add(slice);
                slice = new Slice();
                slice.setColor(Color.YELLOW);
                slice.insertSquare(new Square(0, 2, Color.YELLOW));
                slice.insertSquare(new Square(0, 3, Color.YELLOW));
                this.listSlice.add(slice);
                slice = new Slice();
                slice.setColor(Color.BLUE);
                slice.insertSquare(new Square(1, 2, Color.BLUE));
                slice.insertSquare(new Square(2, 2, Color.BLUE));
                slice.insertSquare(new Square(2, 3, Color.BLUE));
                this.listSlice.add(slice);
                slice = new Slice();
                slice.setColor(Color.CADETBLUE);
                slice.insertSquare(new Square(2, 1, Color.CADETBLUE));
                slice.insertSquare(new Square(3, 1, Color.CADETBLUE));
                slice.insertSquare(new Square(3, 2, Color.CADETBLUE));
                this.listSlice.add(slice);
                slice = new Slice();
                slice.setColor(Color.BROWN);
                slice.insertSquare(new Square(2, 0, Color.BROWN));
                this.listSlice.add(slice);
                break;
            case 7:
                slice.setColor(Color.RED);
                slice.insertSquare(new Square(0, 1, Color.RED));
                slice.insertSquare(new Square(0, 2, Color.RED));
                this.listSlice.add(slice);
                slice = new Slice();
                slice.setColor(Color.YELLOW);
                slice.insertSquare(new Square(0, 3, Color.YELLOW));
                slice.insertSquare(new Square(1, 3, Color.YELLOW));
                this.listSlice.add(slice);
                slice = new Slice();
                slice.setColor(Color.YELLOWGREEN);
                slice.insertSquare(new Square(2, 3, Color.YELLOWGREEN));
                slice.insertSquare(new Square(3, 3, Color.YELLOWGREEN));
                this.listSlice.add(slice);
                slice = new Slice();
                slice.setColor(Color.BLUE);
                slice.insertSquare(new Square(1, 0, Color.BLUE));
                slice.insertSquare(new Square(1, 1, Color.BLUE));
                this.listSlice.add(slice);
                slice = new Slice();
                slice.setColor(Color.CADETBLUE);
                slice.insertSquare(new Square(2, 1, Color.CADETBLUE));
                slice.insertSquare(new Square(2, 2, Color.CADETBLUE));
                this.listSlice.add(slice);
                slice = new Slice();
                slice.setColor(Color.BROWN);
                slice.insertSquare(new Square(1, 2, Color.BROWN));
                this.listSlice.add(slice);
                slice = new Slice();
                slice.setColor(Color.GREEN);
                slice.insertSquare(new Square(3, 2, Color.GREEN));
                this.listSlice.add(slice);
                slice = new Slice();
                slice.setColor(Color.DARKKHAKI);
                slice.insertSquare(new Square(2, 0, Color.DARKKHAKI));
                slice.insertSquare(new Square(3, 0, Color.DARKKHAKI));
                this.listSlice.add(slice);

                break;
            case 10:
                slice.setColor(Color.RED);
                slice.insertSquare(new Square(0, 0, Color.RED));
                this.listSlice.add(slice);
                slice = new Slice();
                slice.setColor(Color.YELLOW);
                slice.insertSquare(new Square(0, 1, Color.YELLOW));
                slice.insertSquare(new Square(0, 2, Color.YELLOW));
                slice.insertSquare(new Square(0, 3, Color.YELLOW));
                slice.insertSquare(new Square(1, 2, Color.YELLOW));
                slice.insertSquare(new Square(1, 3, Color.YELLOW));
                this.listSlice.add(slice);
                slice = new Slice();
                slice.setColor(Color.YELLOWGREEN);
                slice.insertSquare(new Square(1, 0, Color.YELLOWGREEN));
                this.listSlice.add(slice);
                slice = new Slice();
                slice.setColor(Color.BLUE);
                slice.insertSquare(new Square(1, 1, Color.BLUE));
                slice.insertSquare(new Square(2, 1, Color.BLUE));
                slice.insertSquare(new Square(2, 2, Color.BLUE));
                this.listSlice.add(slice);
                slice = new Slice();
                slice.setColor(Color.CADETBLUE);
                slice.insertSquare(new Square(2, 0, Color.CADETBLUE));
                slice.insertSquare(new Square(3, 0, Color.CADETBLUE));
                this.listSlice.add(slice);
                slice = new Slice();
                slice.setColor(Color.BROWN);
                slice.insertSquare(new Square(3, 1, Color.BROWN));
                this.listSlice.add(slice);
                slice = new Slice();
                slice.setColor(Color.GREEN);
                slice.insertSquare(new Square(3, 2, Color.GREEN));
                slice.insertSquare(new Square(3, 3, Color.GREEN));
                this.listSlice.add(slice);
                break;
        }

    }

}
