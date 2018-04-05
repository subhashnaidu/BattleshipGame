package battleshipgame.userInterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PlayerOptionDialog 
{
    private static JLabel shipColorLbl;
    private static JLabel firstPlayerLbl;
    private static JComboBox shipColor;
    private static JComboBox firstPlayer;
    private static JButton saveBtn;
    private static JButton canxBtn;
    private static JRadioButton player1;
    private static JRadioButton player2;
    private static ButtonGroup playerOptions;
    private static JDialog dialog;

    // data for JComboBox    
    private static String[] colors = {"Cyan", "Green", "Yellow", "Magenta", "Pink", "Red", "White"};
    private static String[] players = {"Player 1", "Player 2", "Random"};
    // Array of Players
    private static Player[] playerArray;

    // Custom constructor    
    public PlayerOptionDialog(JFrame parent, Player[] inPlayers) 
    {
        playerArray = inPlayers;
        initComponents(parent);
    }

    
    // initialize UI
    private static void initComponents(JFrame parent)
    {
        dialog = new JDialog(parent,true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        OptionListener optionslistener = new OptionListener();
        
        //Creates Borders for the Panel
        JPanel options = new JPanel();
        options.setBorder(BorderFactory.createTitledBorder("Player OPtions"));
        options.setLayout(new GridLayout(3,2));
        options.setPreferredSize(new Dimension(275, 125));
        
        //Button Gorup for Radio Buttons
        playerOptions = new ButtonGroup();      
        player1 = new JRadioButton("Player 1");
        playerOptions.add(player1);
        options.add(player1);
        player2 = new JRadioButton("Player 2");
        playerOptions.add(player2);
        options.add(player2);
        
        //JLabls for Dialog Box
        shipColorLbl = new JLabel("Ship Color");
        options.add(shipColorLbl);
        shipColor = new JComboBox(colors);
        options.add(shipColor);
        firstPlayerLbl = new JLabel("Who Plays First?");        
        options.add(firstPlayerLbl);       
        firstPlayer = new JComboBox(players);        
        options.add(firstPlayer);
        
        //Creates new panel for save and cancel buttons.
        JPanel buttonspanel = new JPanel();
        buttonspanel.setLayout(new FlowLayout());
        
        saveBtn = new JButton("Save");
        canxBtn = new JButton("Cancel");
        canxBtn.addActionListener(optionslistener);
        
        buttonspanel.add(saveBtn);
        buttonspanel.add(canxBtn);
        
        //Adds the various items to the Dialog box
        dialog.setTitle("Player Options");
        dialog.setLayout(new BorderLayout());
        dialog.add(options, BorderLayout.CENTER);
        dialog.add(buttonspanel, BorderLayout.SOUTH);
        dialog.setMinimumSize(new Dimension(300, 225));
        dialog.setLocation(200,200);
        dialog.setVisible(true);

    }
    
    private void initObjects()
    {
//        currentPlayer = new Player();
    }
    
    //Action Listner for the cancel button.
    private static class OptionListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Cancel")) 
            {
                dialog.dispose();
            }
        }
    }
}
  