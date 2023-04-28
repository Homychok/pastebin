package com.example.pastebin.model;

import com.example.pastebin.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
public class Paste {
    @Id
    private String id;
    private Instant dateExpired;
    private Instant dateCreated;
    private String title;
    private String body;
    @Enumerated(EnumType.STRING)
    private Status status;
}