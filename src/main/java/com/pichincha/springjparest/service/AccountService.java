package com.pichincha.springjparest.service;

import com.pichincha.springjparest.service.dto.AccountDto;

import java.security.NoSuchAlgorithmException;

public interface AccountService
{
    AccountDto get(Long accountId);
    AccountDto getByAccountNumber(Long accountNumber);

    AccountDto create(AccountDto accountDto) throws
                                             NoSuchAlgorithmException;

    AccountDto update(
            Long accountId,
            AccountDto accountDto
    );

    void delete(
            Long accountId
    );

}
