package battleshipgame.userInterface;
import javax.swing.*;
import java.awt.Color;

public class Player 
{
    Color shipColor;
    String userName;
    boolean isFirst;
    JButton[][] buttonBoard;
    final static int ROWS = 10;
    final static int COLS = 10;
    
    public Player()
    {
        //initComponents();
    }
    
    public Player(String name)
    {
        userName = name;
        //initObjects();
        initComponents();
        
    }
    
    
    private JButton[][] initComponents()
    {
        buttonBoard = new JButton[10][10];
        
        for (int row = 0; row < 10; row++)
        {
            for(int col = 0; col < 10; col++)
            {
                JButton button = new JButton();
                button.putClientProperty(row, row);
                button.putClientProperty(col, col);
                //setListener(buttonBoard[10][10], boardListener);  
            }
        }
        
        return buttonBoard;
    }
    public JButton[][] getBoard()
    {
        return buttonBoard;
    }
     
}
