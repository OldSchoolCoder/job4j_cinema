package ru.job4j.store;

import ru.job4j.model.Account;
import ru.job4j.model.Ticket;

import java.sql.SQLException;
import java.util.Collection;

public interface Store {

    void create(Account account) throws SQLException;

    void create(Ticket ticket) throws SQLException;

    Collection<Account> findAllAccounts() throws SQLException;

}

