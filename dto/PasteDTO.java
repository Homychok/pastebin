package com.example.pastebin.dto;
import com.example.pastebin.model.Paste;
import lombok.Data;

@Data
public class PasteDTO {

    private String title;
    private String body;

    public static PasteDTO from(Paste paste){
        PasteDTO pasteDTO = new PasteDTO();
        pasteDTO.setTitle(paste.getTitle());
        pasteDTO.setBody(paste.getBody());
        return pasteDTO;
    }
}