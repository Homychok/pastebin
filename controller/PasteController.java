package com.example.pastebin.controller;
import com.example.pastebin.dto.PasteDTO;
import com.example.pastebin.dto.PasteGetDTO;
import com.example.pastebin.dto.UrlDTO;
import com.example.pastebin.exception.PasteNotFoundException;
import com.example.pastebin.model.ExpirationTime;
import com.example.pastebin.model.Status;
import com.example.pastebin.service.PasteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")
public class PasteController {

    private PasteService pasteService;
    public PasteController(PasteService pasteService) {
        this.pasteService = pasteService;
    }

    @PostMapping
    public ResponseEntity<UrlDTO> createPaste(@RequestBody PasteDTO pasteDTO,
                                              @RequestParam("expirationTime") ExpirationTime expirationTime,
                                              @RequestParam("status") Status status){
        if (pasteDTO == null || pasteDTO.getBody() == null || pasteDTO.getBody().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(pasteService.createPaste(pasteDTO, expirationTime, status));
    }

    @GetMapping("last_ten")
    public ResponseEntity<List<PasteGetDTO>> getLastTen(){
        return ResponseEntity.ok(pasteService.getLastTen());
    }

    @GetMapping("{url}")
    public ResponseEntity<PasteGetDTO> getPaste(String url) throws PasteNotFoundException {
        PasteGetDTO pasteGetDTO = pasteService.getPaste(url);
        return ResponseEntity.ok(pasteGetDTO);
    }

    @GetMapping("text")
    public ResponseEntity<List<PasteGetDTO>> pastesFoundByText(
            @RequestParam(required = false) String titleText,
            @RequestParam(required = false) String bodyText){
        return ResponseEntity.ok(pasteService.pastesFoundByText(titleText, bodyText));
    }
}