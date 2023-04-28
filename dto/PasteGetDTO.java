package com.example.pastebin.dto;
import com.example.pastebin.model.Paste;
import lombok.Data;
@Data
public class PasteGetDTO {

    private String title;
    private String link;

    public static PasteGetDTO from(Paste paste){
        PasteGetDTO pasteGetDTO = new PasteGetDTO();
        pasteGetDTO.setTitle(paste.getTitle());
        pasteGetDTO.setLink("/my-awesome-pastebin.tld/" + paste.getId());
        return pasteGetDTO;
    }
}