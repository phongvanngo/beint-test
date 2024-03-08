package org.beint.beintappapi.web.rest.v1;

import org.beint.beintappapi.exceptions.NotFoundException;
import org.beint.beintappapi.utils.ErrorParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/health-check/")
@RequiredArgsConstructor
public class HealthCheckController {

    private final ErrorParser errorParser;

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        throw new NotFoundException(errorParser.get("greeting.mess"));
    }
}
