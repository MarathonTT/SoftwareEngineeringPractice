
package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());
    }
    /*
    This is a 1 case equivalence class because it is only testing if the amount withdrawn is equal to one
    correct condition (100). This is a border case because if the number being tested is 99 or 101 it will throw
    a boundary error.
     */
    @Test
    void withdrawTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance());
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

        assertFalse(BankAccount.isEmailValid("a@b"));
        assertFalse(BankAccount.isEmailValid("ab.com@j"));
        assertFalse(BankAccount.isEmailValid("ab@j.c"));
        assertFalse(BankAccount.isEmailValid("ab@domain.c"));
        assertFalse(BankAccount.isEmailValid("ab.com@j"));
        assertFalse(BankAccount.isEmailValid("abc-@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc..def@mail.com"));
        assertFalse(BankAccount.isEmailValid(".abc@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc#def@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc.def@mail.c"));
        assertFalse(BankAccount.isEmailValid("abc.def@mail#archive.com"));
        assertFalse(BankAccount.isEmailValid("abc.def@mail"));
        assertFalse(BankAccount.isEmailValid("abc.def@mail..com"));

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