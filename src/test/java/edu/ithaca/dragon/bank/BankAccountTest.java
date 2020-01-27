
package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, bankAccount.getBalance());

        BankAccount bankAccount1 = new BankAccount("a@b.com", Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, bankAccount1.getBalance());

        BankAccount bankAccount2 = new BankAccount("a@b.com", 0);
        assertEquals(0, bankAccount2.getBalance());


    }
    /*
    This is a 1 case equivalence class because it is only testing if the amount withdrawn is equal to one
    correct condition (100). This is a border case because if the number being tested is 99 or 101 it will throw
    a boundary error.
     */
    @Test
    void withdrawTest() {
        //middle of partition
        BankAccount bankAccount1 = new BankAccount("a@b.com", 200);
        bankAccount1.withdraw(100);
        assertEquals(100, bankAccount1.getBalance());

        //0 edge case with positive balance
        assertThrows(IllegalArgumentException.class, () -> bankAccount1.withdraw(0));
        assertThrows(IllegalArgumentException.class, () -> bankAccount1.withdraw(bankAccount1.getBalance()+Double.MIN_VALUE));

        //0 edge case with 0 balance
        bankAccount1.withdraw(100);
        assertEquals(0, bankAccount1.getBalance());

        //Double max value testing and edge cases
        BankAccount bankAccount2 = new BankAccount("a@cc.com", Double.MAX_VALUE);
        assertThrows(IllegalArgumentException.class, () -> bankAccount2.withdraw(bankAccount1.getBalance()+Double.MIN_VALUE));
        bankAccount2.withdraw(Double.MAX_VALUE);
        assertEquals(0, bankAccount2.getBalance());
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

        assertFalse(BankAccount.isEmailValid("a@b")); //no period
        assertFalse(BankAccount.isEmailValid("ab.com@j")); //domain missing period
        assertFalse(BankAccount.isEmailValid("ab@j.c")); //domain suffix too short,
        assertFalse(BankAccount.isEmailValid("ab@domain.c")); // domain suffix too short
        assertFalse(BankAccount.isEmailValid("ab#c#domain.com")); //invalid characters
        assertFalse(BankAccount.isEmailValid("abc-@mail.com")); //breaks sequential special character rule
        assertFalse(BankAccount.isEmailValid("abc..def@mail.com")); //breaks sequential special character rule
        assertFalse(BankAccount.isEmailValid(".abc@mail.com")); //leading special character
        assertFalse(BankAccount.isEmailValid("abc$def@mail.com")); //invalid character
        assertFalse(BankAccount.isEmailValid("abc.def@mail.c")); // too short domain
        assertFalse(BankAccount.isEmailValid("abc.def@mail*archive.com")); //invalid character
        assertFalse(BankAccount.isEmailValid("abc.def@mail")); //invalid domain
        assertFalse(BankAccount.isEmailValid("abc.def@mail..com")); //double special in domain
        assertFalse(BankAccount.isEmailValid("abc@def.co-")); //ending in special chaaracter
        assertFalse(BankAccount.isEmailValid("abc.b@c")); //@ on wrong side of last period


    }

    /*This is missing the equivalence cases of double dashes and underscores. It also doesnt test for every
      special characters.
     */


    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}