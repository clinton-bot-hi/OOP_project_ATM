import java.awt.*;  
import javax.swing.*; 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Frame1 extends JFrame
{
 private JButton buttons[]; 
 private JTextField jTextField1 = new JTextField();
 private JPasswordField passwordField=new JPasswordField();
 private static final String[] services={"view my balance", "Withdrawal"," Transfer", "Exit"};
 private BankDatabase bankDatabase; // account information database
 private CashDispenser cashDispenser; // ATM's cash dispenser
 private int currentAccountNumber; // current user's account number
 //constructor to set up GUI
 public Frame1() {
 super("ATMGUI");
   currentAccountNumber = 0; // no current account number to start
   cashDispenser = new CashDispenser(); // create cash dispenser
   bankDatabase = new BankDatabase(); // create acct info database

JLabel label = new JLabel("<html><body><p>Main Menu</p><br><p>Welcome back, feel free to use our services</p></body></html>");
label.setOpaque(true);
label.setBackground(Color.WHITE);
add(label);

buttons=new JButton[services.length];
 for(int i=0;i<services.length;i++){
buttons[i]=new JButton(services[i]);

}
add(buttons[0]);
add(buttons[1]);
add(buttons[2]);
add(buttons[3]);
setLayout(new GridLayout(5,5));
ButtonHandler handler = new ButtonHandler(); 
buttons[0].addActionListener(handler);
buttons[1].addActionListener(handler);
buttons[2].addActionListener(handler);
buttons[3].addActionListener(handler);
}
private class ButtonHandler implements ActionListener
 {
     Transaction currentTransaction=null;
 public void actionPerformed( ActionEvent e )
 {
     
 if(e.getSource()==buttons[0]){
  JOptionPane.showMessageDialog(null, "Ready for Viewing Your balance","balance",JOptionPane.PLAIN_MESSAGE);

  createTransaction( 0 );
     
 }
  if(e.getSource()==buttons[1]){
  JOptionPane.showMessageDialog(null, "Ready for Withdrawl ","Withdrawl",JOptionPane.PLAIN_MESSAGE);

   createTransaction( 1 );
     
 }
  if(e.getSource()==buttons[2]){
  JOptionPane.showMessageDialog(null, "Ready for Transfer ","Transfer",JOptionPane.PLAIN_MESSAGE);

  createTransaction( 2 );
}   
 
  if(e.getSource()==buttons[3]){
  ATMtest.frame.setVisible(false);
  
  ATMtest.Menu();

  if(ATMtest.database.authenticateUser(ATMtest.inID,ATMtest.inPW)==true)
  {ATMtest.frame.setVisible(true);}
 
 } 
}
} 
   // return object of specified Transaction subclass
   private Transaction createTransaction( int type )
   {
      Transaction temp = null; // temporary Transaction variable
      currentAccountNumber= Integer.valueOf(ATMtest.accountID);
      // determine which type of Transaction to create     
      switch ( type )
      {
         case 0: // create new BalanceInquiry transaction
            temp = new BalanceInquiry( 
               currentAccountNumber, bankDatabase );
            temp.execute();
            break;
         case 1:
            temp= new Withdrawal(currentAccountNumber, bankDatabase,cashDispenser);
            temp.execute();
            break;
         case 2:
            temp = new Transfer(currentAccountNumber,bankDatabase);
            temp.execute();
           
      } // end switch

      return temp; // return the newly created object
   } // end method createTransaction
} // end class ATM


