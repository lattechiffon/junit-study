package com.lattechiffon.junit.chapter3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Customer {
    List<Account> accounts = new ArrayList<>();

    void add(Account account) {
        accounts.add(account);
    }

    Iterator<Account> getAccounts() {
        return accounts.iterator();
    }
}
