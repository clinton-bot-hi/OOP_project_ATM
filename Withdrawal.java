// Withdrawal.java
// Represents a withdrawal ATM transaction
import java.awt.*;  
import javax.swing.*; 
public class Withdrawal extends Transaction
{
   private int amount; // amount to withdraw
  
   private CashDispenser cashDispenser; // reference to cash dispenser

   // constant corresponding to menu option to cancel
   private final static int CANCELED = 7;

   // Withdrawal constructor
   public Withdrawal( int userAccountNumber, 
      BankDatabase atmBankDatabase,
      CashDispenser atmCashDispenser )
   {
      // initialize superclass variables
      super( userAccountNumber, atmBankDatabase );
      // initialize references to cash dispenser
      cashDispenser = atmCashDispenser;
   } // end Withdrawal constructor

   // perform transaction
   public void execute()
   {
      boolean cashDispensed = false; // cash was not dispensed yet
      double availableBalance; // amount available for withdrawal

      // get references to bank database and screen
      BankDatabase bankDatabase = getBankDatabase(); 
     

      // loop until cash is dispensed or the user cancels
      do
      {
         // obtain a chosen withdrawal amount from the user 
         amount = displayMenuOfAmounts();
         
         // check whether user chose a withdrawal amount or canceled
         if ( amount != CANCELED )
         {
            // get available balance of account involved
            availableBalance = 
               bankDatabase.getAvailableBalance( getAccountNumber() );
      
            // check whether the user has enough money in the account 
            if ( amount <= availableBalance )
            {   
               // check whether the cash dispenser has enough money
               if ( cashDispenser.isSufficientCashAvailable( amount ) )
               {
                  // update the account involved to reflect withdrawal
                  bankDatabase.debit( getAccountNumber(), amount );
                  
                  cashDispenser.dispenseCash( amount ); // dispense cash
                  cashDispensed = true; // cash was dispensed

                  // instruct user to take cash
                JOptionPane.showMessageDialog(null,"Please take your cash now.","Take cash",JOptionPane.PLAIN_MESSAGE);
                JOptionPane.showMessageDialog(null,"Please eject your card now.","Take card",JOptionPane.PLAIN_MESSAGE);
                
                ATMtest.frame.setVisible(false);
  
                ATMtest.Menu();

                if(ATMtest.database.authenticateUser(ATMtest.inID,ATMtest.inPW)==true)
                {ATMtest.frame.setVisible(true);}
            } // end if
               else {// cash dispenser does not have enough cash
                  JOptionPane.showMessageDialog(null,"Insufficient funds in your account." +
                  "\n\nPlease choose a smaller amount." ,"Insufficient funds",JOptionPane.PLAIN_MESSAGE);
                }
            } // end if
            else // not enough money available in user's account
            {
             JOptionPane.showMessageDialog(null,"Insufficient funds in your account." +
                  "\nPlease choose a smaller amount." ,"Insufficient funds",JOptionPane.PLAIN_MESSAGE);
            } // end else
         } // end if
         else // user chose cancel menu option 
         {
             JOptionPane.showMessageDialog(null,"\nCanceling transaction..." ,"Cancel",JOptionPane.PLAIN_MESSAGE);
            break;
         } // end else
      } while ( !cashDispensed );

   } // end method execute

   // display a menu of withdrawal amounts and the option to cancel;
   // return the chosen amount or 0 if the user chooses to cancel
   private int displayMenuOfAmounts()
   {
      int userChoice = 0; // local variable to store return value
     //provide input field
     JTextField textfield=new JTextField();
     //create menu using box
      Box box =  Box.createVerticalBox();;
      //create label array
     JLabel label[]= new JLabel[9];
     
     //set memu background color
     box.setOpaque(true);
     box.setBackground(Color.WHITE);
      // array of amounts to correspond to menu numbers
      int amounts[] = { 0, 100, 200, 500, 800, 1000 };
      // loop while no valid choice has been made
      while ( userChoice == 0 )
      {
         // display the menu
         label[0]=new JLabel("<html><body><p>Withdrawal Menu:<br></p></body></html>");
         label[1]=new JLabel("<html><body><p>1 - HKD100<br></p></body></html>" );
         label[2]=new JLabel("<html><body><p>2 - HKD200<br></p></body></html>" );
         label[3]=new JLabel("<html><body><p>3 - HKD500<br></p></body></html>");
         label[4]=new JLabel("<html><body><p>4 - HKD800<br></p></body></html>" );
         label[5]=new JLabel("<html><body><p>5 - HKD1000<br></p></body></html>");
         label[6]=new JLabel("<html><body><p>6 - Customize the withdrawal amount<br></p></body></html>");
         label[7]=new JLabel("<html><body><p>7 - Cancel transaction<br></p></body></html>" );
         label[8]=new JLabel("Choose a withdrawal amount: " );
        
        for(int i=0;i<9;i++)
        box.add(label[i]);
        box.add(textfield);
        // get user input
         int input1 = JOptionPane.showConfirmDialog(null, box,"Option",JOptionPane.OK_CANCEL_OPTION);
         String input2=textfield.getText();
         try{
         int input3=Integer.valueOf(input2);
         
         // determine how to proceed based on the input value
         switch ( input3 )
         {
            case 1: // if the user chose a withdrawal amount 
            case 2: // (i.e., chose option 1, 2, 3, 4 or 5), return the
            case 3: // corresponding amount from amounts array
            case 4:
            case 5:
               userChoice = amounts[ input3 ]; // save user's choice
               break;
            case 6:
                userChoice = multiple();
                break;
            case 7: // the user chose to cancel
               userChoice = CANCELED; // save user's choice
               break;
            default: // the user did not enter a value from 1-6
            JOptionPane.showMessageDialog(null,"\nIvalid selection. Try again." ,"Invalid Input",JOptionPane.PLAIN_MESSAGE);
               
         
         } // end switch
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"\nIvalid selection. Try again." ,"Invalid Input",JOptionPane.PLAIN_MESSAGE);
            userChoice =CANCELED;
            break;
        }
      } // end while
      
      return userChoice; // return withdrawal amount or CANCELED
   } // end method displayMenuOfAmounts
        private int multiple(){ //To check whether the withdrawal amount could be divided by 100, 500, 1000
            
             String ina;
                int a = 1;
                while( a % 100 != 0){
                    ina = JOptionPane.showInputDialog("Please insert the withdrawal amount: ");
                    a=Integer.valueOf(ina);
                    if( a % 100 != 0){
                        JOptionPane.showMessageDialog(null,"You can just withdraw the amount that is mutiples of HKD100, HKD500, HKD1000. Please input again.","error Message",JOptionPane.PLAIN_MESSAGE);
                        
                    }
                }
            return a;
        }
} // end class Withdrawal

    

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