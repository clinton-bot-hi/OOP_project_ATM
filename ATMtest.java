import java.awt.*;  
import javax.swing.*; 
import java.util.Scanner;
public class ATMtest
{
    public static String accountID;
    public static BankDatabase database=new  BankDatabase();
    public static Frame1 frame = new Frame1();
    public static int count;
    public static int inPW;
    public static int inID;
public static void main (String[] args)
{
    Box box = Box.createHorizontalBox();

  Menu();
  if(database.authenticateUser(inID,inPW)==true){
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
      frame.setUndecorated(true);
      frame.setVisible(true);
   }

}
public static String getPassword(){
    //assume pw is only 6 dignits
    
    JPasswordField passwordField=new JPasswordField(5);
    //hide the pw using('*')
passwordField.setEchoChar( '*' );
JLabel j1 = new JLabel("(Reminder: account will be freeze for three times worng password input)Enter Your Password: ");
Box box = Box.createHorizontalBox();
box.add(j1);
box.add(passwordField);
int password = JOptionPane.showConfirmDialog(null, box,"Password",JOptionPane.OK_CANCEL_OPTION);
 if (password == JOptionPane.OK_OPTION) {
      return passwordField.getText();
    }
 return null;
}

public static void Menu(){
    JOptionPane.showMessageDialog(null,"Welcome, feel free to use our ATM services","Greeting",JOptionPane.PLAIN_MESSAGE);
  accountID=JOptionPane.showInputDialog("Insert your ATM card: (Insert ID)");
    
  inPW= Integer.valueOf(getPassword());
  inID= Integer.valueOf(accountID);

while(database.authenticateUser(inID,inPW)==false){
    accountID=JOptionPane.showInputDialog("Insert your ATM card: (Insert ID)");
    inPW=Integer.valueOf(getPassword());
    inID=Integer.valueOf(accountID);
}
}

}