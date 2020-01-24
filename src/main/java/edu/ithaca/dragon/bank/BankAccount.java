package edu.ithaca.dragon.bank;

import javax.print.DocFlavor;
import java.util.ArrayList;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){

        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount)  {
        balance -= amount;
    }

    /**
     * returns true if email follows correct conventions.
     */

    public static boolean isEmailValid(String email) {


            //declares a variable with the locations of the @ signs in the email.
            int atSign = email.indexOf('@');
            int lastAtSign = email.lastIndexOf('@');



            //makes sure that an @ sign is in the email.
            if (atSign == -1)
                return false;



            //makes sure that there is only one @ sign.
            if (atSign != lastAtSign)
                return false;



            //Makes sure that a special character is not followed by a "@"
            if (email.charAt(lastAtSign-1) == '.')
                return false;



            if (email.charAt(lastAtSign-1) == '_')
                return false;



            if (email.charAt(lastAtSign-1) == '-')
                return false;










            //declares a variable with the location of the last "." in the email
            int lastPeriodSign = email.lastIndexOf('.');



            //makes sure that there is a '.' in the email.
            if (lastPeriodSign == -1)
                return false;



            //makes sure that there is a period after @
            if (email.charAt(lastAtSign) < email.charAt(lastPeriodSign))
                return false;







            //makes sure that there is not two periods, dashes, or underscores back to back in the email
            int x = 0;
            int lengthOfEmail = email.length();

            int locationOfLastPeriod = -1000;
            int locationOfCurrentPeriod;

            int locationOfLastDash = -2;
            int locationOfCurrentDash;

            int locationOfLastUnderscore = -2;
            int locationOfCurrentUnderscore;

            //makes sure the period follows a .com or .cc or .org or .edu etc...
            if (lastPeriodSign != lengthOfEmail - 3 && lastPeriodSign != lengthOfEmail - 4)
                return false;




            while (x < (email.length()-1)){

                //checks to make sure the email does not start with a period
                if(email.charAt(x) == '.' && x == 0)
                    return false;
                //checks for double periods
                if (email.charAt(x) =='.' && x == (locationOfLastPeriod+1))
                    return false;
                if(email.charAt(x) =='.')
                    locationOfLastPeriod = x;

                //checks for double underscores
                if(email.charAt(x) == '_' && x == 0)
                    return false;
                if (email.charAt(x) == locationOfLastUnderscore)
                    return false;
                else if(email.charAt(x) =='_')
                    locationOfLastUnderscore = x;

                //checks for double dashes
                if(email.charAt(x) == '-' && x == 0)
                    return false;
                if (email.charAt(x) == locationOfLastDash)
                    return false;
                else if(email.charAt(x) =='-')
                    locationOfLastDash = x;

                x++;
            }



            //makes sure there is not a #
            x = 0;
            while (x < (email.length()-1)){
                if (email.charAt(x) == '#')
                    return false;
                x++;
            }

            return true;









    }
}
