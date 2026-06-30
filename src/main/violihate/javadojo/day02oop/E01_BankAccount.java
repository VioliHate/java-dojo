package main.violihate.javadojo.day02oop;

import java.util.UUID;

/*
========================================
JAVA DOJO - DAY 02
Exercise 01 - Bank Account
Difficulty: ⭐
========================================

DESCRIPTION

Design a simple BankAccount class.

A bank account has:

- accountNumber
- owner
- balance

IMPLEMENT

- deposit(double amount)
- withdraw(double amount)
- getBalance()

REQUIREMENTS

- A deposit amount must be greater than zero.
- A withdrawal amount must be greater than zero.
- A withdrawal cannot exceed the current balance.

CONSTRAINTS

- Throw IllegalArgumentException for invalid amounts.
- Keep the balance private.

EXTRA

- Add a transfer(BankAccount destination, double amount) method.
*/
public class E01_BankAccount {

    static class BankAccount {
        private final String id;
        private final String owner;
        private double balance;

        BankAccount(String owner, double balance) {
            this.id = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            this.owner = owner;
            this.balance = balance > 0 ? balance : 0.0;
        }

        @Override
        public String toString() {
            return "Bank Account: [id: " + id + ", owner: " + owner + ", balance: " + balance + "]";
        }

        public double getBalance() {
            return balance;
        }

        public void withdraw(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("A withdrawal amount must be greater than zero.");
            }
            if (this.balance < amount) {
                throw new IllegalArgumentException("A withdrawal cannot exceed the current balance");
            }
            this.balance -= amount;
        }

        public void deposit(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("A deposit amount must be greater than zero");
            }
            this.balance += amount;
        }

        public void transfer(BankAccount destination, double amount) {
            if (destination == null) {
                throw new IllegalArgumentException("The destination cannot be null.");
            }
            this.withdraw(amount);
            destination.deposit(amount);
        }

    }

    public static void main(String[] args) {
        BankAccount firstAccount = new BankAccount("First Account", 100.0);
        BankAccount secondAccount = new BankAccount("Second Account", 200.0);

        firstAccount.withdraw(100.0);
        secondAccount.withdraw(200.0);
        System.out.println(firstAccount);
        System.out.println(secondAccount);
        firstAccount.deposit(200.0);
        firstAccount.deposit(200.0);
        secondAccount.deposit(200.0);
        System.out.println(firstAccount);
        System.out.println(secondAccount);
        firstAccount.transfer(secondAccount, 200.0);
        System.out.println(firstAccount);
        System.out.println(secondAccount);

    }
}
