
package model;

/**
 *
 * @author Al-Hussein
 */
public class Grid {
    private int Height;
    private int Weight;
    private Square[][] CurGrid;

    public Grid(int Height, int Weight) {
        this.Height = Height;
        this.Weight = Weight;
        this.CurGrid = new Square[this.Height][this.Weight];
        initGrid();
    }

    private void initGrid() {
        for(int i=0;i<this.Height;i++)
            for(int j=0;j<this.Weight;j++)
                this.CurGrid[i][j] = new Square(i,j,Square.Status.EMPTY);
    }
    
    
    
    
    
}
