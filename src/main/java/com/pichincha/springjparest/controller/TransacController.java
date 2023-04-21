package com.pichincha.springjparest.controller;

import com.pichincha.springjparest.service.TransacService;
import com.pichincha.springjparest.service.dto.TransacDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movimientos")
@RequiredArgsConstructor
public class TransacController
{
    private final TransacService transacService;
    
    @GetMapping("/{transacId}")
    public ResponseEntity<TransacDto> get(
            @PathVariable
            Long transacId
    )
    {
        return ResponseEntity.ok(transacService.get(transacId));
    }
    
    @GetMapping("/report{personId}/{fechaInicio}/{fechaFin}")
    public ResponseEntity<List<TransacDto>> getReportTransac(
            @PathVariable
            String personId,
            @PathVariable
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate fechaInicio,
            @PathVariable
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate fechaFin
    )
    {
        return ResponseEntity.ok(transacService.findByPersonIdAndInitialDateAndFinishDate(
                personId,
                fechaInicio,
                fechaFin
        ));
    }
    
    @PostMapping
    public ResponseEntity<TransacDto> create(
            @RequestBody
            @Valid TransacDto transacDto
    ) throws NoSuchAlgorithmException
    {
        return ResponseEntity.ok(transacService.create(transacDto));
    }
    
    @PutMapping("/{transacId}")
    public ResponseEntity<TransacDto> update(
            @PathVariable
            Long transacId,
            @RequestBody
            @Valid TransacDto transacDto
    )
    {
        return ResponseEntity.ok(transacService.update(
                transacId,
                transacDto
        ));
    }
    
    @DeleteMapping("/{transacId}")
    public ResponseEntity<Boolean> delete(
            @PathVariable
            Long transacId
    )
    {
        transacService.delete(transacId);
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
