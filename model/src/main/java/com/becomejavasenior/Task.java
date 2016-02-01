package com.becomejavasenior;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Task implements Identified{

    private int id;
    private Date dateOfCreate;
    private Date finishDate;
    private String description;
    private User owner;

    private Company company;
    private Deal deal;
    private Contact contact;

    private TaskStatus status;
    private TaskType type;
    private Set<Comment> comments;

    public Task() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(Date dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (dateOfCreate != null ? !dateOfCreate.equals(task.dateOfCreate) : task.dateOfCreate != null) return false;
        if (finishDate != null ? !finishDate.equals(task.finishDate) : task.finishDate != null) return false;
        if (description != null ? !description.equals(task.description) : task.description != null) return false;
        if (owner != null ? !owner.equals(task.owner) : task.owner != null) return false;
        if (company != null ? !company.equals(task.company) : task.company != null) return false;
        if (deal != null ? !deal.equals(task.deal) : task.deal != null) return false;
        if (contact != null ? !contact.equals(task.contact) : task.contact != null) return false;
        if (status != null ? !status.equals(task.status) : task.status != null) return false;
        if (type != null ? !type.equals(task.type) : task.type != null) return false;
        return !(comments != null ? !comments.equals(task.comments) : task.comments != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dateOfCreate != null ? dateOfCreate.hashCode() : 0);
        result = 31 * result + (finishDate != null ? finishDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (deal != null ? deal.hashCode() : 0);
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", dateOfCreate=" + dateOfCreate +
                ", finishDate=" + finishDate +
                ", description='" + description + '\'' +
                ", owner=" + owner +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}

