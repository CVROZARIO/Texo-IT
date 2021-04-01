package com.texoit.movies.entities;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "DataError")
@Table(name = "dataerror")
public class DataError {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date date;
    private String message;

    public DataError() {
        super();
    }

    public DataError(String message) {
        super();
        this.date = new Date();
        this.message = message;
    }

    public DataError(Date date, String message) {
        super();
        this.date = date;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
