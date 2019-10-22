package com.hkd.community.dto;

public class GitUser {
    private String name;
    private Long id;

    @Override
    public String toString() {
        return "GitUser{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
