package com.demo.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// COMO FUNCIONA:
// se a pessoa nao tiver nenhuma linha da tabela permissions vinculada a ela, ela é apenas leitora.
// se a pessoa tiver uma permissao e adm = 0, ela participa do espaço.
// se a pessoa tiver uma permissao e adm = 1, ela participa do espaço e é ADM.

@Entity
@Table(name = "tbPermission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Boolean adm;

    @OneToMany(mappedBy = "permission")
    private Set<Answer> answers = new HashSet<>();

    @OneToMany(mappedBy = "permission")
    private Set<Question> questions = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_space")
    private Space space;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAdm() {
        return adm;
    }

    public void setAdm(Boolean adm) {
        this.adm = adm;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }
}
