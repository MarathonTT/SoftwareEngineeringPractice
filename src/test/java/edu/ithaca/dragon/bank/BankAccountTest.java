
package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import javax.imageio.stream.ImageInputStreamImpl;

import static edu.ithaca.dragon.bank.BankAccount.isAmountValid;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount2 = new BankAccount("a@b.com", 1);
        assertEquals(1, bankAccount2.getBalance());

    }
    /*
    This is a 1 case equivalence class because it is only testing if the amount withdrawn is equal to one
    correct condition (100). This is a border case because if the number being tested is 99 or 101 it will throw
    a boundary error.
     */
    @Test
    void withdrawTest() throws InsufficientFundsException{
        //middle of partition
        BankAccount bankAccount1 = new BankAccount("a@b.com", 200);
        bankAccount1.withdraw(100);
        assertEquals(100, bankAccount1.getBalance());
        bankAccount1.withdraw(.01);
        assertEquals(99.99, bankAccount1.getBalance());

        //0 edge case with positive balance
        assertThrows(IllegalArgumentException.class, () -> bankAccount1.withdraw(1.005));
        assertThrows(IllegalArgumentException.class, () -> bankAccount1.withdraw(0));
        assertThrows(IllegalArgumentException.class, () -> bankAccount1.withdraw(-.01));
        assertThrows(IllegalArgumentException.class, () -> bankAccount1.withdraw(-.000001));

        //0 edge case with 0 balance
        bankAccount1.withdraw(99.99);
        assertThrows(IllegalArgumentException.class, () -> bankAccount1.withdraw(0));
        assertThrows(IllegalArgumentException.class, () -> bankAccount1.withdraw(-.01));
        assertThrows(IllegalArgumentException.class, () -> bankAccount1.withdraw(-.000001));
        assertEquals(0, bankAccount1.getBalance());

        //testing InsufficientFundsException
        BankAccount bankAccount2 = new BankAccount("a@b.com", 5);
        assertThrows(InsufficientFundsException.class, () -> bankAccount2.withdraw(10));
        assertThrows(InsufficientFundsException.class, () -> bankAccount2.withdraw(5.01));

        bankAccount2.withdraw(5);
        assertThrows(InsufficientFundsException.class, () -> bankAccount2.withdraw(.01));
        assertThrows(InsufficientFundsException.class, () -> bankAccount2.withdraw(10));
    }

    /*
       This isn't missing equivalence cases or boundaries because if someone withdraws $100 the bank should only
       send $100. That number can be edited.
     */

//     --------------------------------------------------------------------------------------------------
    /*This is a 21 case equivalence class where if one condition fails the whole partition is invalid.
      There are no boundary values because we are not dealing with numbers so there is no getting close to a limit
      it either breaks the rules for a valid email or passes.
     */

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid("a@b.com"));
        assertTrue(BankAccount.isEmailValid("abc-d@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc_def@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.cc"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail-archive.com"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.org"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.archive.com"));
        assertTrue(BankAccount.isEmailValid("a@cc.com"));

        assertFalse(BankAccount.isEmailValid("a@b")); //no period
        assertFalse(BankAccount.isEmailValid("ab.com@j")); //domain missing period
        assertFalse(BankAccount.isEmailValid("ab@j.c")); //domain suffix too short,
        assertFalse(BankAccount.isEmailValid("ab@domain.c")); // domain suffix too short
        assertFalse(BankAccount.isEmailValid("abc*domain.com")); //invalid characters
        assertFalse(BankAccount.isEmailValid("ab--c@domain.com")); //invalid characters
        assertFalse(BankAccount.isEmailValid("abc-@mail.com")); //breaks sequential special character rule
        assertFalse(BankAccount.isEmailValid("abc..def@mail.com")); //breaks sequential special character rule
        assertFalse(BankAccount.isEmailValid(".abc@mail.com")); //leading special character
        assertFalse(BankAccount.isEmailValid("abc$def@mail.com")); //invalid character
        assertFalse(BankAccount.isEmailValid("abc.def@mail.c")); // too short domain
        assertFalse(BankAccount.isEmailValid("abc.def@mail*archive.com")); //invalid character
        assertFalse(BankAccount.isEmailValid("abc.def@mail")); //invalid domain
        assertFalse(BankAccount.isEmailValid("abc.def@mail..com")); //double special in domain
        assertFalse(BankAccount.isEmailValid("abc@def.co-")); //ending in special character
        assertFalse(BankAccount.isEmailValid("abc.b@c")); //@ on wrong side of last period

    }

    /*This is missing the equivalence cases of double dashes and underscores. It also doesnt test for every
      special characters.
     */

    @Test
    void isAmountValidTest() {
        //testing valid values with and without valid decimal places
        assertTrue(isAmountValid(9679756476693.89));
        assertTrue(isAmountValid(100.0));
        assertTrue(isAmountValid(79.77));
        assertTrue(isAmountValid(1.0));
        assertTrue(isAmountValid(0.01));
        assertTrue(isAmountValid(0.0));

        //testing values too low with valid decimals
        assertFalse(isAmountValid(-9679756476693.89));
        assertFalse(isAmountValid(-100.0));
        assertFalse(isAmountValid(-9.82));
        assertFalse(isAmountValid(-100.0));

        //testing invalid decimal places positive and negative
        assertFalse(isAmountValid(-.001));
        assertFalse(isAmountValid(.001));
        assertFalse(isAmountValid(-10.001));
        assertFalse(isAmountValid(10.001));
        assertFalse(isAmountValid(99809.859));

    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());

        BankAccount bankAccount1 = new BankAccount("a@b.com", 1);

        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("ab@google.com", -100));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("ab@google.com", -.001));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("ab@google.com", 100.001));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("ab@google.com", .001));
    }

}