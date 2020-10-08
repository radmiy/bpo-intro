package com.intro.users.demo.models;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    private String title;
    
    @Column(unique = true)
    private String secureNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSecureNumber() {
        return secureNumber;
    }

    public void setSecureNumber(String secureNumber) {
        this.secureNumber = secureNumber;
    }
}
