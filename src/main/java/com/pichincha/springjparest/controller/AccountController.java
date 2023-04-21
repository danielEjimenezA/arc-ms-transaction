package com.pichincha.springjparest.controller;

import com.pichincha.springjparest.service.AccountService;
import com.pichincha.springjparest.service.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/v1/cuentas")
@RequiredArgsConstructor
public class AccountController
{
    private final AccountService accountService;
    
    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDto> get(
            @PathVariable
            Long accountNumber
    )
    {
        return ResponseEntity.ok(accountService.getByAccountNumber(accountNumber));
    }
    
    @PostMapping
    public ResponseEntity<AccountDto> create(
            @RequestBody
            @Valid AccountDto accountDto
    ) throws NoSuchAlgorithmException
    {
        return ResponseEntity.ok(accountService.create(accountDto));
    }
    
    @PutMapping("/{accountId}")
    public ResponseEntity<AccountDto> update(
            @PathVariable
            Long accountId,
            @RequestBody
            @Valid AccountDto accountDto
    )
    {
        return ResponseEntity.ok(accountService.update(
                accountId,
                accountDto
        ));
    }
    
    @DeleteMapping("/{accountId}")
    public ResponseEntity<Boolean> delete(
            @PathVariable
            Long accountId
    )
    {
        accountService.delete(accountId);
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
