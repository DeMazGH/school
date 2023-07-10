package ru.hogwarts.school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.InfoService;

@RestController
@RequestMapping("/info")
@RequiredArgsConstructor
public class InfoController {

    private final InfoService infoService;

    @GetMapping("/getPort")
    public String getPort() {
        return infoService.getCurrentPort();
    }

    @GetMapping("/getSum")
    public ResponseEntity<Integer> getSum() {
        return ResponseEntity.ok(infoService.getSum());
    }
}
