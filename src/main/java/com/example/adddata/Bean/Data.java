package com.example.adddata.Bean;

import org.springframework.data.annotation.Id;


public class Data {

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    Integer id;

    String data;
}
