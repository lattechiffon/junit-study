package com.lattechiffon.junit.chapter3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.jupiter.api.Assertions.*;

class AssertTest {
    private Account account;

    @BeforeEach
    public void createAccount() {
        account = new Account("an account name");
    }

    @Test
    public void hasPositiveBalance() {
        account.deposit(50);
        assertTrue(account.hasPositiveBalance());
    }

    @Test
    public void depositIncreaseBalance() {
        int initialBalance = account.getBalance();
        account.deposit(100);
        assertTrue(account.getBalance() > initialBalance); // not good test
        assertThat(account.getBalance(), equalTo(100)); // good test - hamcrest
        assertThat(account.getBalance() > 0, is(true)); // not good test
    }

    @Test
    public void matchesFailure() {
        assertThat(account.getName(), is(not(startsWith("xyz"))));
    }

    @Test
    public void comparesArraysFailing() {
        assertThat(new String[] {"a", "b", "c"}, is(not(equalTo(new String[] {"a", "b"}))));
    }

    @Test
    public void comparesArraysPassing() {
        assertThat(new String[] {"a", "b"}, is(equalTo(new String[] {"a", "b"})));
    }

    @Test
    public void comparesCollectionsFailing() {
        assertThat(Arrays.asList(new String[] {"a"}), is(not(equalTo(Arrays.asList(new String[] {"a", "ab"})))));
    }

    @Test
    public void comparesCollectionsPassing() {
        assertThat(Arrays.asList(new String[] {"a"}), is(equalTo(Arrays.asList(new String[] {"a"}))));
    }

    @Test
    public void variousMatcherTests() {
        Account account = new Account("my big fat acct");
        assertThat(account.getName(), is(equalTo("my big fat acct")));

        assertThat(account.getName(), allOf(startsWith("my"), endsWith("acct")));

        assertThat(account.getName(), anyOf(startsWith("my"), endsWith("loot")));

        assertThat(account.getName(), not(equalTo("plunderings")));

        // null인지 아닌지 검사하는 것은 설계 문제이거나 지나치게 걱정하는 것임.
        // 즉, 대부분 불필요한 경우가 많음.
        assertThat(account.getName(), is(not(nullValue())));
        assertThat(account.getName(), is(notNullValue()));

        assertThat(account.getName(), isA(String.class));
    }

    @Test
    public void assertDoublesCloseTo() {
        assertThat(2.32 * 3, closeTo(6.96, 0.0005));
    }

    @Test
    public void readsFromTestFile() throws IOException {
        String filename = "test.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write("test data");
        writer.close();
        // ...
    }

    @Test
    public void withdrawMoreThanBalanceGeneratesInsufficientFundsException() {
        // JUnit5는 람다식을 활용한 assertThrows를 이용하여 기대되는 예외처리를 검사함.
        assertThrows(InsufficientFundsException.class, () -> account.withdraw(100));
    }
}