import java.awt.*;  
import javax.swing.*; 
public class Transfer extends Transaction
{
   private double amount; // amount to transfer

   private final static int CANCELED = 0; // constant for cancel option

   // Deposit constructor
   public Transfer( int userAccountNumber,  
      BankDatabase atmBankDatabase )
   {
      // initialize superclass variables
      super( userAccountNumber,  atmBankDatabase );

      // initialize references to keypad and deposit slot
      
   } // end Deposit constructor

   // perform transaction
   public void execute()
   {
      double availableBalance; // amount available for withdrawal

      // get references to bank database and screen
      BankDatabase bankDatabase = getBankDatabase(); 
     
         Box box =  Box.createVerticalBox();
      // loop until cash is dispensed or the user cancels
        JLabel label= new JLabel( "Input the transfer account number: ");
        JTextField textfield = new JTextField();
         box.add(label);
         box.add(textfield);
         
         int input1 = JOptionPane.showConfirmDialog(null, box,"account number",JOptionPane.OK_CANCEL_OPTION);
         String input2=textfield.getText();
         try{
         int transferAccount=Integer.valueOf(input2);
         amount = TransferAmounts();

         // check whether user chose a transfer or canceled
         if ( amount != CANCELED )
         {
            // get available balance of account involved
            availableBalance = 
               bankDatabase.getAvailableBalance( getAccountNumber() );
      
            // check whether the user has enough money in the account 
            if ( amount <= availableBalance )
            {   
               bankDatabase.debit( getAccountNumber(), amount );
               bankDatabase.credit( transferAccount, amount );
               JOptionPane.showMessageDialog(null,"Successfully transfered." ,"success",JOptionPane.PLAIN_MESSAGE);
            } // end if
            else // not enough money available in user's account
            {
              JOptionPane.showMessageDialog(null,"Insufficient funds in your account." +
                  "\n\nPlease input a smaller amount." ,"Insufficient",JOptionPane.PLAIN_MESSAGE);
            } // end else
         } // end if
         else // user chose cancel menu option 
         {
             JOptionPane.showMessageDialog(null,"Canceling transaction..." ,"cancel",JOptionPane.PLAIN_MESSAGE);
            return;
         } // end else
        }
        catch(Exception e){
            //incorrect accountID or wrong Type
            JOptionPane.showMessageDialog(null,"no such a Account\nreturn to menu..." ,"Invalid",JOptionPane.PLAIN_MESSAGE);
        }
   } // end method execute

   // prompt user to enter a transfer amount in cents 
   private double TransferAmounts()
   {
      double userChoice = 0; // local variable to store return value

      // loop while no valid choice has been made
      while ( userChoice == 0 )
      {
         // display the menu
       String a=JOptionPane.showInputDialog("Input a transfer amount:");
        try{
            double input=Double.valueOf(a);
         // determine how to proceed based on the input value
         if(input ==0){
           userChoice = CANCELED; // save user's choice
         }
         else
         {   
           userChoice = input;
         } // end switch          
        }
        catch(Exception e){
        JOptionPane.showMessageDialog(null,"Canceling transaction..." ,"wrong input",JOptionPane.PLAIN_MESSAGE);
        userChoice=CANCELED;
        }
      } // end while
      
      return userChoice; // return transfer amount or CANCELED
   } // end method TransferAmounts
        
} // end class Deposit



/**************************************************************************
 * (C) Copyright 1992-2007 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/