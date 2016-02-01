package com.becomejavasenior;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Deal implements Identified {

    private int id;
    private Date date;
    private String name;
    private BigDecimal budget;
    private DealStatus status;
    private Currency currency;
    private User owner;
    private Company company;
    private Set<Tag> tags;
    private Set<File> files;
    private Set<Comment> comments;
    private List<Contact> contacts;

    public Deal() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public DealStatus getStatus() {
        return status;
    }

    public void setStatus(DealStatus status) {
        this.status = status;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<File> getFiles() {
        return files;
    }

    public void setFiles(Set<File> files) {
        this.files = files;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Deal deal = (Deal) o;

        if (id != deal.id) return false;
        if (date != null ? !date.equals(deal.date) : deal.date != null) return false;
        if (name != null ? !name.equals(deal.name) : deal.name != null) return false;
        if (budget != null ? !budget.equals(deal.budget) : deal.budget != null) return false;
        if (status != null ? !status.equals(deal.status) : deal.status != null) return false;
        if (currency != null ? !currency.equals(deal.currency) : deal.currency != null) return false;
        if (owner != null ? !owner.equals(deal.owner) : deal.owner != null) return false;
        if (tags != null ? !tags.equals(deal.tags) : deal.tags != null) return false;
        if (files != null ? !files.equals(deal.files) : deal.files != null) return false;
        if (comments != null ? !comments.equals(deal.comments) : deal.comments != null) return false;
        return !(contacts != null ? !contacts.equals(deal.contacts) : deal.contacts != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (budget != null ? budget.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (files != null ? files.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (contacts != null ? contacts.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "Deal{" +
                "id=" + id +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", status=" + status +
                ", currency=" + currency +
                ", owner=" + owner +
                ", tags=" + tags +
                ", files=" + files +
                ", comments=" + comments +
                ", contacts=" + contacts +
                '}';
    }
}
