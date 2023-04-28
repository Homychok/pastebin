package com.example.pastebin.scheduler;

import com.example.pastebin.repository.PasteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.Instant;

@Slf4j
@Component
public class ClearPastes {

    private final PasteRepository pasteRepository;

    public ClearPastes(PasteRepository pasteRepository) {
        this.pasteRepository = pasteRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void clear(){
        pasteRepository.deleteAll(Instant.now());
    }
}