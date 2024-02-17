package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.LoginEvent;
import com.longware.financetracker.repository.LoginEventRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is the REST controller for the LoginEvent entity.
 */
@RestController
@RequestMapping("/loginEvent")
@RequiredArgsConstructor
@Getter
@Setter
public class LoginEventController {

    private final LoginEventRepository loginEventRepository;

    // Write methods to create, update, and delete LoginEvent objects using
    // available methods in the LoginEventRepository interface.

    // Write a method to return a LoginEvent object by its id.
    @Operation(summary = "Get a LoginEvent by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the LoginEvent", 
            content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = LoginEvent.class)) }),
        @ApiResponse(responseCode = "404", description = "LoginEvent not found") })
    @RequestMapping("/getLoginEventById")
    public LoginEvent getLoginEventById(
        @Parameter(description = "ID of the LoginEvent to be obtained", required = true) Long id, 
        Principal principal) {
        return loginEventRepository.findById(id).orElse(null);
    }

    // Write a method to return all LoginEvent objects.
    @Operation(summary = "Get all LoginEvents")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found all LoginEvents", 
            content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = LoginEvent.class)) }) })
    @RequestMapping("/getAllLoginEvents")
    public Iterable<LoginEvent> getAllLoginEvents(Principal principal) {
        return loginEventRepository.findAll();
    }

    @Operation(summary = "Save a LoginEvent")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "LoginEvent saved", 
            content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = LoginEvent.class)) }) })
    @RequestMapping("/saveLoginEvent")
    public LoginEvent saveLoginEvent(LoginEvent loginEvent, Principal principal) {
        return loginEventRepository.save(loginEvent);
    }

    @Operation(summary = "Delete a LoginEvent")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "LoginEvent deleted") })
    @RequestMapping("/deleteLoginEvent")
    public void deleteLoginEvent(LoginEvent loginEvent, Principal principal) {
        loginEventRepository.delete(loginEvent);
    }

}
