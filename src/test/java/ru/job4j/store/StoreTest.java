package ru.job4j.store;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.model.Account;
import ru.job4j.model.Ticket;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class StoreTest {

    private Store store;
    private Account account;
    private Ticket ticket;

    @Before
    public void setUp() throws Exception {
        this.store = PsqlStore.instOf();
        this.account = new Account(0, "Ben", "ben3@sd.io", "04");
        this.ticket = new Ticket(0, 1, 1, 1);
    }

    @Test
    public void createTest() {
        try {
            store.create(account, ticket);
        } catch (SQLException e) {
            Assert.fail();
            e.printStackTrace();
        }
    }
}