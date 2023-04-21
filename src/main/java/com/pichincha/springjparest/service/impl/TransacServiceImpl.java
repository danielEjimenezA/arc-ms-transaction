package com.pichincha.springjparest.service.impl;

import com.pichincha.springjparest.domain.Account;
import com.pichincha.springjparest.domain.Transac;
import com.pichincha.springjparest.exception.ApiRequestException;
import com.pichincha.springjparest.repository.AccountRepository;
import com.pichincha.springjparest.repository.TransacRepository;
import com.pichincha.springjparest.service.TransacService;
import com.pichincha.springjparest.service.dto.TransacDto;
import com.pichincha.springjparest.service.mapper.AccountMapper;
import com.pichincha.springjparest.service.mapper.TransacMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TransacServiceImpl implements TransacService
{
    private final TransacRepository transacRepository;
    private final AccountRepository accountRepository;
    
    @Override
    @Transactional(readOnly = true)
    public TransacDto get(Long transacId)
    {
        return TransacMapper.INSTANCE.toTransacDto(transacRepository
                                                           .findById(transacId)
                                                           .orElse(new Transac()));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<TransacDto> findByPersonIdAndInitialDateAndFinishDate(
            String personId,
            LocalDate fechaInicio,
            LocalDate fechaFin
    )
    {
        return TransacMapper.INSTANCE.toTransacDtoList(transacRepository.findByPersonIdAndInitialDateAndFinishDate(
                personId,
                fechaInicio,
                fechaFin
        ));
    }
    
    @Override
    @Transactional
    public TransacDto create(TransacDto transacDto) throws NoSuchAlgorithmException
    {
        Account account = accountRepository.findByAccountNumber(transacDto
                                                                        .getAccountDto()
                                                                        .getAccountNumber());
        switch (transacDto.getTransactionType())
        {
            case "Depósito":
                account.setInitialAmount(account.getInitialAmount() + transacDto.getValue());
                accountRepository.save(account);
                break;
            case "Débito":
                if (account
                            .getInitialAmount()
                            .compareTo(transacDto.getValue()) > 0)
                {
                    List<Transac> transacList = transacRepository.findAllByDate(LocalDate.now());
                    double limitAmountDaily = 0.0;
                    for (Transac transac : transacList)
                    {
                        limitAmountDaily = limitAmountDaily + transac.getValue();
                    }
                    
                    if (limitAmountDaily != 1000)
                    {
                        account.setInitialAmount(account.getInitialAmount() - transacDto.getValue());
                        accountRepository.save(account);
                    }
                    else
                    {
                        throw new ApiRequestException("Cupo diario excedido");
                    }
                    
                }
                else
                {
                    throw new ApiRequestException("Saldo no disponible");
                }
                break;
            default:
        }
        Random rand = SecureRandom.getInstanceStrong();
        transacDto.setId(rand.nextLong());
        transacDto.setBalance(account.getInitialAmount());
        return TransacMapper.INSTANCE.toTransacDto(transacRepository.save(TransacMapper.INSTANCE.toTransac(transacDto)));
    }
    
    @Override
    @Transactional
    public TransacDto update(
            Long transacId,
            TransacDto transacDto
    )
    {
        Transac transac = transacRepository
                .findById(transacId)
                .orElse(new Transac());
        
        transac.setValue(transacDto.getValue());
        transac.setDate(transacDto.getDate());
        transac.setAccount(AccountMapper.INSTANCE.toAccount(transacDto.getAccountDto()));
        transac.setBalance(transacDto.getBalance());
        transac.setTransactionType(transacDto.getTransactionType());
        
        return TransacMapper.INSTANCE.toTransacDto(transacRepository.save(transac));
    }
    
    @Override
    @Transactional
    public void delete(Long transacId)
    {
        transacRepository.deleteById(transacId);
    }
}
