package com.ncamc.service;

public interface TransactionService {

    void transferMoney(String fromAccount, String toAccount, double amount);

    void logTransaction(String message);

}