package com.example.pastebin.controller;
import com.example.pastebin.dto.CreateDTO;
import com.example.pastebin.dto.PasteDTO;
import com.example.pastebin.dto.PasteGetDTO;
import com.example.pastebin.dto.UrlDTO;
import com.example.pastebin.service.PasteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/my-awesome-pastebin.tld")
public class PasteController {

    private PasteService pasteService;
    public PasteController(PasteService pasteService) {
        this.pasteService = pasteService;
    }

    @PostMapping("/save")
    public UrlDTO createPaste(@RequestBody CreateDTO createDTO){
               return pasteService.create(createDTO);
    }

    @GetMapping("/last10")
    public ResponseEntity<List<PasteGetDTO>> getLastTen(){
        return ResponseEntity.ok(pasteService.getTenLast());
    }

    @GetMapping("{id}")
    public ResponseEntity<PasteDTO> getPaste(@PathVariable String id) {
        return ResponseEntity.ok(pasteService.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<PasteDTO>> pastesFoundByText(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String body){
        return ResponseEntity.ok(pasteService.search(title, body));
    }
}