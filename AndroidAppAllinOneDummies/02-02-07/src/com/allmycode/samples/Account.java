package com.allmycode.samples;

public class Account {
    public String name;
    public String address;
    public double balance;
    
    public Account(String name, 
                   String address, 
                   double balance) {
        this.name = name;
        this.address = address;
        this.balance = balance;
    }

    public String infoString() {
        return name + " (" + address + 
               ") has $" + balance;
    }
}
