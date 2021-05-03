package ru.job4j.store;

import ru.job4j.model.Account;
import ru.job4j.model.Ticket;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface Store {

//    void create(Account account) throws SQLException;
//
//    void create(Ticket ticket) throws SQLException;

    //Collection<Account> findAllAccounts() throws SQLException;

    List<Integer> findAllCell(int row) throws SQLException;

    void create(Account account, Ticket ticket) throws SQLException;
}

