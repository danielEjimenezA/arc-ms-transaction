package com.pichincha.springjparest.service.impl;

import com.pichincha.springjparest.domain.Account;
import com.pichincha.springjparest.exception.ApiRequestException;
import com.pichincha.springjparest.repository.AccountRepository;
import com.pichincha.springjparest.service.AccountService;
import com.pichincha.springjparest.service.dto.AccountDto;
import com.pichincha.springjparest.service.mapper.AccountMapper;
import com.pichincha.springjparest.service.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService
{
    private final AccountRepository accountRepository;
    
    @Override
    @Transactional(readOnly = true)
    public AccountDto get(Long accountId)
    {
        return AccountMapper.INSTANCE.toAccountDto(accountRepository
                                                           .findById(accountId)
                                                           .orElse(new Account()));
    }
    
    @Override
    public AccountDto getByAccountNumber(Long accountNumber)
    {
        return AccountMapper.INSTANCE.toAccountDto(accountRepository.findByAccountNumber(accountNumber));
    }
    
    @Override
    @Transactional
    public AccountDto create(AccountDto accountDto) throws NoSuchAlgorithmException
    {
        if (accountRepository.findByAccountNumber(accountDto.getAccountNumber()) == null)
        {
            Random rand = SecureRandom.getInstanceStrong();
            accountDto.setId(rand.nextLong());
            return AccountMapper.INSTANCE.toAccountDto(accountRepository.save(AccountMapper.INSTANCE.toAccount(accountDto)));
        }
        else
        {
            throw new ApiRequestException("Número de cuenta ya existente");
        }
    }
    
    @Override
    @Transactional
    public AccountDto update(
            Long accountId,
            AccountDto accountDto
    )
    {
        Account account = accountRepository
                .findById(accountId)
                .orElse(new Account());
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccountType(accountDto.getAccountType());
        account.setClient(ClientMapper.INSTANCE.toClient(accountDto.getClientDto()));
        account.setStatus(accountDto.getStatus());
        account.setInitialAmount(accountDto.getInitialAmount());
        
        return AccountMapper.INSTANCE.toAccountDto(accountRepository.save(account));
    }
    
    @Override
    @Transactional
    public void delete(Long accountId)
    {
        accountRepository.deleteById(accountId);
    }
}
