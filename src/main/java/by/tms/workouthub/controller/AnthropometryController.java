package by.tms.workouthub.controller;

import by.tms.workouthub.dto.AnthropometryCreateDto;
import by.tms.workouthub.dto.AnthropometryResponseDto;
import by.tms.workouthub.dto.AnthropometryUpdateDto;
import by.tms.workouthub.service.AnthropometryHistoryService;
import by.tms.workouthub.service.AnthropometryService;
import by.tms.workouthub.validation.annotation.ValidateThis;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anthropometry")
public class AnthropometryController {
    private final AnthropometryService anthropometryService;
    private final AnthropometryHistoryService anthropometryHistoryService;

    public AnthropometryController(AnthropometryService anthropometryService, AnthropometryHistoryService anthropometryHistoryService) {
        this.anthropometryService = anthropometryService;
        this.anthropometryHistoryService = anthropometryHistoryService;
    }


    @Operation(summary = "Get current anthropometry data of the user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response", content = @Content(mediaType = "application/json"))
    })
    @GetMapping()
    public ResponseEntity<AnthropometryResponseDto> getCurrentAnthropometry(Authentication authentication) {
        return new ResponseEntity<>(anthropometryService.getCurrentAnthropometry(authentication), HttpStatus.OK);
    }

    @Operation(summary = "Create anthropometry data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json"))
    })
    @ValidateThis
    @PostMapping
    public ResponseEntity<AnthropometryResponseDto> createAnthropometry( @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Data for creating anthropometry", required = true)
                                                                             @Valid @RequestBody AnthropometryCreateDto anthropometryCreateDto,
                                                                             Authentication authentication) {
        return new ResponseEntity<>(anthropometryService.createAnthropometry(anthropometryCreateDto, authentication), HttpStatus.CREATED);
    }

    @Operation(summary = "Update current anthropometry data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AnthropometryResponseDto.class)))
    })
    @PatchMapping
    @ValidateThis
    public ResponseEntity<AnthropometryResponseDto> updateCurrentAnthropometry( @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Data for updating current anthropometry", required = true)
                                                                                    @RequestBody AnthropometryUpdateDto anthropometryUpdateDto,
                                                                                    Authentication authentication) {
        return new ResponseEntity<>(anthropometryService.updateCurrentAnthropometry(anthropometryUpdateDto, authentication),HttpStatus.OK);
    }

    @Operation(summary = "Delete current anthropometry data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content, successfully deleted")
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteCurrentAnthropometry(Authentication authentication) {
        anthropometryService.deleteCurrentAnthropometry(authentication);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get anthropometry history")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AnthropometryResponseDto.class)))
    })
    @GetMapping("/history")
    public ResponseEntity<List<AnthropometryResponseDto>> getAnthropometryHistory(Authentication authentication) {
        return new ResponseEntity<>(anthropometryHistoryService.getHistoryOfAnthropometry(authentication), HttpStatus.OK);
    }

    @Operation(summary = "Get anthropometry history by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AnthropometryResponseDto.class))),
            @ApiResponse(responseCode = "403", description = "Access denied", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Anthropometry history not found")

    })
    @GetMapping("/history/{id}")
    public ResponseEntity<AnthropometryResponseDto> getAnthropometryHistoryById(@PathVariable Long id, Authentication authentication) {
        return new ResponseEntity<>(anthropometryHistoryService.getAnthropometryHistoryById(id, authentication), HttpStatus.OK);
    }

    @Operation(summary = "Update anthropometry history by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AnthropometryResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "403", description = "Access denied", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Anthropometry history not found")
    })
    @PatchMapping("/history/{id}")
    @ValidateThis
    public ResponseEntity<AnthropometryResponseDto> updateAnthropometryHistoryById(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Data for updating anthropometry history", required = true) @Valid @RequestBody AnthropometryUpdateDto anthropometryUpdateDto,
                                                                                   @PathVariable Long id,
                                                                                   Authentication authentication) {
        return new ResponseEntity<>(anthropometryHistoryService.updateAnthropometryHistoryById(id, anthropometryUpdateDto, authentication),HttpStatus.OK);
    }

    @Operation(summary = "Delete anthropometry history by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content, successfully deleted"),
            @ApiResponse(responseCode = "403", description = "Access denied", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Anthropometry history not found")
    })
    @DeleteMapping("/history/{id}")
    public ResponseEntity<Void> deleteAnthropometryHistoryById(@PathVariable Long id, Authentication authentication) {
        anthropometryHistoryService.deleteAnthropometryHistoryById(id, authentication);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
