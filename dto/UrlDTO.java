package com.example.pastebin.dto;

import lombok.Data;

@Data
public class UrlDTO {

    private String url;
    public static UrlDTO fromCreateDTO(CreateDTO createDTO){
        UrlDTO urlDTO = new UrlDTO();
        urlDTO.setUrl(createDTO.getLink());
        return urlDTO;
    }
}
