package by.tms.workouthub.controller;

import by.tms.workouthub.dto.AnthropometryCreateDto;
import by.tms.workouthub.dto.AnthropometryResponseDto;
import by.tms.workouthub.dto.AnthropometryUpdateDto;
import by.tms.workouthub.service.AnthropometryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/anthropometry")
public class AnthropometryController {
    private final AnthropometryService anthropometryService;
    public AnthropometryController(AnthropometryService anthropometryService) {
        this.anthropometryService = anthropometryService;
    }

    @GetMapping()
    public ResponseEntity<AnthropometryResponseDto> getCurrentAnthropometry(Authentication authentication) {
        return new ResponseEntity<>(anthropometryService.getCurrentAnthropometryByOwner(authentication), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<AnthropometryResponseDto> createAnthropometry(@RequestBody AnthropometryCreateDto anthropometryCreateDto, Authentication authentication) {
        return new ResponseEntity<>(anthropometryService.createAnthropometry(anthropometryCreateDto, authentication), HttpStatus.CREATED);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<AnthropometryResponseDto> updateCurrentAnthropometry(@PathVariable Long id, @RequestBody AnthropometryUpdateDto anthropometryUpdateDto, Authentication authentication) {
        return new ResponseEntity<>(anthropometryService.updateCurrentAnthropometry(anthropometryUpdateDto, authentication),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnthropometry(@PathVariable Long id, Authentication authentication) {
        anthropometryService.deleteAnthropometryById(id, authentication);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
