package battleshipgame.userInterface;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class BattleshipUI extends JFrame
{
    private JMenuBar menuBar;
    private JMenu gameMenu;
    private JMenu optionMenu;
    private JMenuItem playerPlayer;
    private JMenuItem playerComputer;
    private JMenuItem computerComputer;
    private JMenuItem exit;
    private JMenuItem game;
    private JMenuItem player;
    private JButton deploy;
    private JComboBox shipCb;
    private JComboBox directionCb;
    private JPanel playerOnePanel;
    private JPanel playerTwoPanel;
    private JPanel shipLayoutPanel;

   
    // Data arrays for various components on the UI
    private String[] rowLetters = {" ","A","B","C","D","E","F","G","H","I","J"};
    private String[] columnNumbers = {" ","1","2","3","4","5","6","7","8","9","10"};
    private static final String[] SHIPS = {"Carrier","Battleship","Submarine","Destroyer", "Patrol Boat"};
    private static final String[] DIRECTION = {"Horizontal","Vertical"};
    private GameListener gameListener;
    private OptionsListener optionListener;
    private static final int PLAYER_ONE = 0;
    private static final int PLAYER_TWO = 1;
    private Player playerOne;
    private Player playerTwo;
    private Player[] players = new Player[2];
    private Color[] color = {Color.cyan, Color.green, Color.yellow, Color.magenta, Color.pink, Color.red, Color.white};

    public BattleshipUI()
    {
        initObjects();
        initComponents();              
    }
    
    public BattleshipUI getThisParent()
    {
        return this;
    }
    
    private void initObjects()
    {   
        playerOne = new Player("Player One");
        playerTwo = new Player("Player Two");
        
        players[0] = playerOne;
        players[1] = playerTwo;
        
    }
    
    private void initComponents()
     {
         //Builds the Frame
        this.setLayout(new BorderLayout());
        this.setResizable(true);
        this.setTitle("Battleship");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500,500));
        this.setMinimumSize(new Dimension(500,500));
        
        gameListener = new GameListener();
        optionListener = new OptionsListener();
        
        //Creates Border around Combobox
        TitledBorder shipborder = new TitledBorder("Ships");
        shipborder.setTitleJustification(TitledBorder.CENTER);
        shipborder.setTitlePosition(TitledBorder.TOP);

        //Creates Border around Combobox
        TitledBorder directionborder = new TitledBorder("Direction");
        directionborder.setTitleJustification(TitledBorder.CENTER);
        directionborder.setTitlePosition(TitledBorder.TOP);
        
        //Creates Menu bar and adds menu items.
        menuBar = new JMenuBar();
        gameMenu = new JMenu("Game");
        optionMenu = new JMenu("Options"); 
        menuBar.add(gameMenu);
        menuBar.add(optionMenu);
        
        //Game Menu
        playerPlayer = new JMenuItem("Player vs. Player");
        playerPlayer.setEnabled(false);
        gameMenu.add(playerPlayer);
        playerPlayer.addActionListener(gameListener);
        
        playerComputer = new JMenuItem("Player vs. Computer");
        gameMenu.add(playerComputer);
        playerComputer.addActionListener(gameListener);
        
        computerComputer = new JMenuItem("Computer vs. Computer");
        computerComputer.setEnabled(false);
        gameMenu.add(computerComputer);
        computerComputer.addActionListener(gameListener);

        exit = new JMenuItem("Exit");
        gameMenu.add(exit);
        exit.addActionListener(gameListener);
        
        //Options Menu
        game = new JMenuItem("Game Options");
        optionMenu.add(game);
        game.addActionListener(optionListener);
        
        player = new JMenuItem("Player Options");
        optionMenu.add(player);
        player.addActionListener(optionListener);
        
        gameMenu.setMnemonic(KeyEvent.VK_F);
        optionMenu.setMnemonic(KeyEvent.VK_H);
        
        //ComboBox
        shipCb = new JComboBox(SHIPS);
        shipCb.setSelectedIndex(0);
        shipCb.setBorder(shipborder);
        
        directionCb = new JComboBox(DIRECTION);
        directionCb.setSelectedIndex(0);
        directionCb.setBorder(directionborder);
        
        //Deploy Button
        deploy = new JButton("Deploy Ships");
        deploy.setEnabled(false);
        //deploy.addActionListener(new DeployListener());

        shipLayoutPanel = new JPanel(new FlowLayout());
        shipLayoutPanel.add(shipCb);
        shipLayoutPanel.add(directionCb);
        shipLayoutPanel.add(deploy);
        shipLayoutPanel.setPreferredSize(null);
        shipLayoutPanel.setBorder(BorderFactory.createTitledBorder("Select Ship and Direction"));
        shipLayoutPanel.setVisible(true);
        
        //Offloads player and button
        setupPlayerPanels();
        
        this.add(shipLayoutPanel, BorderLayout.NORTH);
        this.add(playerOnePanel, BorderLayout.CENTER);
        this.setJMenuBar(menuBar);    
        this.setLocationByPlatform(true);
        this.setVisible(true);
    }

    private void setupPlayerPanels() 
    {
        //creates panel for button array 
        playerOnePanel = new JPanel(new GridLayout(11,11));
        playerOnePanel.setMinimumSize(new Dimension(400,400));
        playerOnePanel.setPreferredSize(new Dimension(400,400));
        playerOnePanel.setBorder(BorderFactory.createTitledBorder("Player One"));
        
//        playerTwoPanel = new JPanel(new GridLayout(11, 11));
//        playerTwoPanel.setMinimumSize(new Dimension(400, 400));
//        playerTwoPanel.setPreferredSize(new Dimension(400, 400));
//        playerTwoPanel.setBorder(BorderFactory.createTitledBorder("Player Two"));
        
        JButton[][] playerOneArray = new JButton[10][10];
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                playerOneArray[row][col] = new JButton();
                playerOneArray[row][col].putClientProperty(row, row);
                playerOneArray[row][col].putClientProperty(col, col);
                //add action listner   
            }
        }
        
        //Inserts JLabels for the columns and rows
        for(int row = 0; row <= 10; row++)
        {
            for(int col = 0; col <= 10; col++)
            {
                //first row shows numbers
                if(row == 0)
                {
                    JLabel colLabel = new JLabel(columnNumbers[col],SwingConstants.CENTER);
                    playerOnePanel.add(colLabel);
                }
                else if(row > 0 && col == 0)
                {
                    JLabel rowLabel = new JLabel(rowLetters[row], SwingConstants.CENTER);
                    playerOnePanel.add(rowLabel);
                }
                else
                {
                    playerOnePanel.add(playerOneArray[row-1][col-1]);
                }
                
            }
            
        }
  
    }
    
    //Action listner for the Game Options
    private class GameListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(e.getActionCommand().equals("Player vs. Player"))
            {
//                players[Constants.PLAYER_ONE].setPlayMode(Constants.HUMAN);
//                players[Constants.PLAYER_TWO].setPlayMode(Constants.HUMAN);
            }
            else if(e.getActionCommand().equals("Player vs. Computer"))
            {
//                players[Constants.PLAYER_ONE].setPlayMode(Constants.HUMAN);
//                players[Constants.PLAYER_TWO].setPlayMode(Constants.COMPUTER);
            }
            else if(e.getActionCommand().equals("Computer vs. Computer"))
            {
//                players[Constants.PLAYER_ONE].setPlayMode(Constants.COMPUTER);
//                players[Constants.PLAYER_ONE].setPlayMode(Constants.COMPUTER);
            }
            else if(e.getActionCommand().equals("Exit"))
            {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Exit?", dialogButton);
                if (dialogResult == 0) 
                {
                    System.exit(0);   
                }
            }    
        }  
    }
    
    //Action Listner for the Options menu
    private class OptionsListener implements ActionListener 
    {
        GameOptionDialog gameOptions;
        PlayerOptionDialog playerOptions;

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(e.getActionCommand().equals("Game Options"))
            {
                //calls game option dialog
                gameOptions = new GameOptionDialog(getThisParent(), players);
                
            }
            else if (e.getActionCommand().equals("Player Options"))
            {
                //calls player option dialog.
                playerOptions = new PlayerOptionDialog(getThisParent(), players);
            }
            
        }

    }

}



