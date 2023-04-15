package com.example.pastebin.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
public class Paste {
    @Id
    private String url;
    private Instant dataExpired;
    @Column(name = "data_created")
    private Instant dataCreated;
    private String title;
    private String body;
    @Enumerated(EnumType.STRING)
    private Status status;
}