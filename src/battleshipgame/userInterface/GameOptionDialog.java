package battleshipgame.userInterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class GameOptionDialog 
{
    private static JLabel computerAiLbl;
    private static JLabel shipLayoutLbl;
    private static JComboBox computerAi;
    private static JComboBox shipLayout;
    private static JButton saveBtn;
    private static JButton canxBtn;
    private static JDialog dialog;
    //Data for the JCombo box
    private static String[] level = {"Normal", "Ridiculously Hard"};
    private static String[] layout = {"Manual", "Automatic"};
    // array of Players
    private static Player[] playerArray;

    // Customer constructor    
    public GameOptionDialog(JFrame parent, Player[] inPlayers) 
    {
        playerArray = inPlayers;
        initComponents(parent);
    }
    
    // initialize components    
    private void initComponents(JFrame parent)
    {
        dialog = new JDialog(parent, true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        //Options Lisnter for buttons on dialog box
        OptionListener option = new OptionListener();
        
        //Creates Borders for the Panel
        TitledBorder optionsborder = new TitledBorder("Game Options");
        optionsborder.setTitleJustification(TitledBorder.LEFT);
        optionsborder.setTitlePosition(TitledBorder.TOP);
        
        //Creates new Panel for various items of the dialog box
        JPanel boxpanel = new JPanel();
        boxpanel.setLayout(new GridLayout(2,2));
        boxpanel.setBorder(optionsborder);
        
        computerAi = new JComboBox(level);
        shipLayout = new JComboBox(layout);
        computerAiLbl = new JLabel("Computer AI");
        shipLayoutLbl = new JLabel("Ship Layout");
        boxpanel.add(computerAiLbl);
        boxpanel.add(computerAi);
        boxpanel.add(shipLayoutLbl);
        boxpanel.add(shipLayout);
               
        //Creates seperate panal for the button group
        JPanel buttonspanel = new JPanel();
        buttonspanel.setLayout(new FlowLayout());
        
        canxBtn = new JButton();
        canxBtn.setText("Cancel");
        canxBtn.addActionListener(option);
        
        saveBtn = new JButton();
        saveBtn.setText("Save");
        
        buttonspanel.add(saveBtn);
        buttonspanel.add(canxBtn);
        
        //Adds variouts items to the dialog box.
        dialog.add(boxpanel, BorderLayout.CENTER);
        dialog.add(buttonspanel, BorderLayout.PAGE_END);
        
        dialog.setTitle("Game Options");
        dialog.setLayout(new BorderLayout());
        dialog.add(boxpanel, BorderLayout.CENTER);
        dialog.add(buttonspanel, BorderLayout.SOUTH);
        dialog.setMinimumSize(new Dimension(300, 225));
        dialog.setLocation(200, 200);
        dialog.setVisible(true);
           
    }
    
    //Action Listner for the cancel button.
    private class OptionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(e.getActionCommand().equals("Cancel"))
            {
                dialog.dispose();
            }            
        }    
    }   
}
