package com.pichincha.springjparest.controller;

import com.pichincha.springjparest.service.ClientService;
import com.pichincha.springjparest.service.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientController
{
    private final ClientService clientService;

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDto> get(
            @PathVariable
            Long clientId
    )
    {
        return ResponseEntity.ok(clientService.get(clientId));
    }

    @PostMapping
    public ResponseEntity<ClientDto> create(
            @RequestBody
            @Valid ClientDto clientDto
    ) throws
      NoSuchAlgorithmException
    {
        return ResponseEntity.ok(clientService.create(clientDto));
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<ClientDto> update(
            @PathVariable
            Long clientId,
            @RequestBody
            @Valid ClientDto clientDto
    )
    {
        return ResponseEntity.ok(clientService.update(
                clientId,
                clientDto
        ));
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Boolean> delete(
            @PathVariable
            Long clientId
    )
    {
        clientService.delete(clientId);
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
