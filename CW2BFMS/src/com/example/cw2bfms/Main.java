package com.example.cw2bfms;

/**
 * Main class to initialize the Family Management System.
 */
public class Main {

    public static void main(String[] args) {
        IDandPasswords idandPasswords = new IDandPasswords();
        LoginPage loginPage = new LoginPage(idandPasswords.getLoginInfo());
    }
}
