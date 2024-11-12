package com.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbPermission")
public class Permission {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long idUser;

    @Column
    private Long idSpace;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdSpace() {
        return idSpace;
    }

    public void setIdSpace(Long idSpace) {
        this.idSpace = idSpace;
    }
}
