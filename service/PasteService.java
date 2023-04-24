package com.example.pastebin.service;
import com.example.pastebin.dto.PasteDTO;
import com.example.pastebin.dto.PasteGetDTO;
import com.example.pastebin.dto.UrlDTO;
import com.example.pastebin.exception.InvalidParametersExeption;
import com.example.pastebin.exception.PasteNotFoundException;
import com.example.pastebin.model.ExpirationTime;
import com.example.pastebin.model.Paste;
import com.example.pastebin.model.Status;
import com.example.pastebin.repository.PasteRepository;
import com.example.pastebin.specification.PasteSpecificashion;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PasteService {
    private  PasteRepository pasteRepository;

    public PasteService(PasteRepository pasteRepository) {
        this.pasteRepository = pasteRepository;
    }

    // создание уникального ключа
    public String generatedKey(){
        SecureRandom secureRandom = new SecureRandom();
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int index = secureRandom.nextInt(alphabet.length());
            sb.append(alphabet.charAt(index));
        }
        return "http://my-awesome-pastebin.tld/" + sb;
    }
    public UrlDTO createPaste(PasteDTO pasteDTO, ExpirationTime expirationTime, Status status) {

        if (pasteDTO == null || pasteDTO.getBody() == null || pasteDTO.getBody().isBlank()) {
            throw new InvalidParametersExeption("No paste");
        }
        Paste paste = PasteDTO.toPaste(pasteDTO);

        paste.setUrl(generatedKey());
        if (expirationTime.equals(ExpirationTime.UNLIMITED)) {
            paste.setDataExpired(null);
        } else {
            paste.setDataExpired(Instant.now().plus(expirationTime.getTime(), expirationTime.getChronoUnit()));
        }
        paste.setDataCreated(Instant.now());
        paste.setStatus(status);
        pasteRepository.save(paste);
        UrlDTO urlDTO = new UrlDTO();
        urlDTO.setUrl(paste.getUrl());
        return urlDTO;
    }

    public List<PasteGetDTO> getLastTen() {
        return pasteRepository.findTop10ByStatusAndDataExpiredIsAfterOrderByDataCreatedDesc(Status.PUBLIC, Instant.now()).stream()
                .map(PasteGetDTO::from).collect(Collectors.toList());
    }

    public PasteGetDTO getPaste(String url) {
        Paste paste = pasteRepository.findByUrlAndDataExpiredIsAfter(url, Instant.now()).orElseThrow(PasteNotFoundException::new);
        return PasteGetDTO.from(paste);
    }

    public List<PasteGetDTO> pastesFoundByText(String titleText, String bodyText) {
        List<Paste> pasteList = pasteRepository.findAllByTitleContainsOrBodyContainsAndStatusAndDataExpiredIsAfter(titleText, bodyText, Status.PUBLIC, Instant.now());
        return pasteList.stream().map(PasteGetDTO::from).collect(Collectors.toList());
    }
}
