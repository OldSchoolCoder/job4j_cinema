package ru.job4j.store;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.model.Account;
import ru.job4j.model.Ticket;

import java.io.IOException;
import java.util.logging.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store {

    private final BasicDataSource pool = new BasicDataSource();
    private static final Logger LOGGER = Logger.getLogger(PsqlStore.class.getName());

    private PsqlStore() {
        Properties cfg = new Properties();
        try (BufferedReader io = new BufferedReader(
                new FileReader("db.properties")
        )) {
            cfg.load(io);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error! IOException!", e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error! ClassNotFoundException!", e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }

    private static final class Lazy {
        private static final Store INST = new PsqlStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public void create(Account account) throws SQLException {
        Connection cn = pool.getConnection();
        PreparedStatement ps = cn.prepareStatement("INSERT INTO account(username, email, phone) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, account.getName());
        ps.setString(2, account.getEmail());
        ps.setString(3, account.getPhone());
        ResultSet rs = ps.getGeneratedKeys();
        ps.executeUpdate();
        if (rs.next()) {
            account.setId(rs.getInt(1));
        }
        cn.close();
        ps.close();
        rs.close();
    }

    @Override
    public void create(Ticket ticket) throws SQLException {
        Connection cn = pool.getConnection();
        PreparedStatement ps = cn.prepareStatement("INSERT INTO ticket(session_id, row, cell, account_id) VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setInt(1,ticket.getSessionId());
        ps.setInt(2,ticket.getRow());
        ps.setInt(3,ticket.getCell());
        ps.setInt(4,ticket.getAccountId());
        ResultSet rs = ps.getGeneratedKeys();
        ps.executeUpdate();
        if (rs.next()) {
            ticket.setId(rs.getInt(1));
        }
        cn.close();
        ps.close();
        rs.close();
    }

    @Override
    public Collection<Account> findAllAccounts() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        Connection cn = pool.getConnection();
        //PreparedStatement ps = cn.prepareStatement("SELECT * FROM account");
        //PreparedStatement ps = cn.prepareStatement("SELECT * FROM \"account\"");
        PreparedStatement ps = cn.prepareStatement("SELECT * FROM public.\"account\"");
        ResultSet it = ps.executeQuery();
        while (it.next()) {
            accounts.add(new Account(it.getInt("id"), it.getString("username"),it.getString("email"),it.getString("phone")));
        }
        cn.close();
        ps.close();
        it.close();
        return accounts;
    }


}

