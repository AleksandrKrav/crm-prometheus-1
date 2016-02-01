package com.becomejavasenior;

import java.io.Serializable;
import java.util.Date;

public class ConnectionHistory implements Serializable {

    private int id;
    private Date date;
    private String ip;
    private Browser browser;
    private User user;

    public ConnectionHistory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Browser getBrowser() {
        return browser;
    }

    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConnectionHistory that = (ConnectionHistory) o;

        if (id != that.id) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        if (browser != null ? !browser.equals(that.browser) : that.browser != null) return false;
        return !(user != null ? !user.equals(that.user) : that.user != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (browser != null ? browser.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ConnectionHistory{" +
                "id=" + id +
                ", date=" + date +
                ", ip='" + ip + '\'' +
                ", browser=" + browser +
                ", user=" + user +
                '}';
    }
}
