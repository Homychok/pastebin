package com.example.pastebin.dto;

import com.example.pastebin.enums.ExpirationTime;
import com.example.pastebin.enums.Status;
import com.example.pastebin.model.Paste;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


@Data
public class CreateDTO {
    private String title;
    private String body;
    private Status status;
    private ExpirationTime expirationTime;
    @JsonIgnore
    private String link;

    public Paste to(){
        Paste paste = new Paste();
        paste.setTitle(getTitle());
        paste.setBody(getBody());
        return paste;
    }
}
