package com.pichincha.springjparest.service;

import com.pichincha.springjparest.service.dto.TransacDto;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;

public interface TransacService
{
    TransacDto get(Long transacId);
    
    List<TransacDto> findByPersonIdAndInitialDateAndFinishDate(
            String personId,
            LocalDate fechaInicio,
            LocalDate fechaFin
    );
    
    TransacDto create(TransacDto transacDto) throws NoSuchAlgorithmException;
    
    TransacDto update(
            Long transacId,
            TransacDto transacDto
    );
    
    void delete(
            Long transacId
    );
}
