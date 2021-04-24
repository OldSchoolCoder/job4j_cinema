package ru.job4j.model;

import java.util.Objects;

public class Ticket {
    private int id;
    private int sessionId;
    private int row;
    private int cell;
    private int accountId;

    public Ticket(int id, int sessionId, int row, int cell, int accountId) {
        this.id = id;
        this.sessionId = sessionId;
        this.row = row;
        this.cell = cell;
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return getId() == ticket.getId() && getSessionId() == ticket.getSessionId() && getRow() == ticket.getRow() && getCell() == ticket.getCell() && getAccountId() == ticket.getAccountId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSessionId(), getRow(), getCell(), getAccountId());
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getId() {
        return id;
    }

    public int getSessionId() {
        return sessionId;
    }

    public int getRow() {
        return row;
    }

    public int getCell() {
        return cell;
    }

    public int getAccountId() {
        return accountId;
    }
}
