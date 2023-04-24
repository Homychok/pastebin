package com.example.pastebin.dto;
import com.example.pastebin.model.ExpirationTime;
import com.example.pastebin.model.Paste;
import com.example.pastebin.model.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.Instant;

@Getter
@Setter
public class PasteDTO {

    @Enumerated(EnumType.STRING)
    private Status status;
    private ExpirationTime expirationTime;
    private String title;
    private String body;

    public static Paste toPaste(PasteDTO pasteDTO) {
        Paste paste = new Paste();
        paste.setTitle(pasteDTO.getTitle());
        paste.setBody(pasteDTO.getBody());
        paste.setDataExpired(Instant.now()
                .plus(pasteDTO.expirationTime.getTime(),
                        pasteDTO.expirationTime.getChronoUnit()));
        paste.setStatus(pasteDTO.status);
        return paste;
    }
}