package org.example.account;

import lombok.Data;

@Data
public class Customer {

    private String name;
    private int balance;
    private boolean creditAllowed;
//    private int maxCredit = 0;
    private boolean vip;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public void setCreditAllowed(boolean creditAllowed) {
        this.creditAllowed = creditAllowed;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public boolean isVip() {
        return vip;
    }

    public boolean isCreditAllowed() {
        return creditAllowed;
    }
}
