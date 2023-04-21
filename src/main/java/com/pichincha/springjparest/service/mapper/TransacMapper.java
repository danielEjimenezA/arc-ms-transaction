package com.pichincha.springjparest.service.mapper;

import com.pichincha.springjparest.domain.Transac;
import com.pichincha.springjparest.service.dto.TransacDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface TransacMapper
{
    TransacMapper INSTANCE = Mappers.getMapper(TransacMapper.class);

    @Mapping(
            source = "id",
            target = "id"
    )
    @Mapping(
            source = "accountDto",
            target = "account"
    )
    @Mapping(
            source = "date",
            target = "date"
    )
    @Mapping(
            source = "transactionType",
            target = "transactionType"
    )
    @Mapping(
            source = "value",
            target = "value"
    )
    @Mapping(
            source = "balance",
            target = "balance"
    )
    Transac toTransac(TransacDto transacDto);

    @Mapping(
            source = "id",
            target = "id"
    )
    @Mapping(
            source = "account",
            target = "accountDto"
    )
    @Mapping(
            source = "date",
            target = "date"
    )
    @Mapping(
            source = "transactionType",
            target = "transactionType"
    )
    @Mapping(
            source = "value",
            target = "value"
    )
    @Mapping(
            source = "balance",
            target = "balance"
    )
    TransacDto toTransacDto(Transac transac);

    default List<Transac> toTransacList(List<TransacDto> transacDtoList)
    {
        if (transacDtoList == null)
        {
            return new ArrayList<>();
        }
        return transacDtoList
                .stream()
                .map(this::toTransac)
                .collect(Collectors.toList());
    }

    default List<TransacDto> toTransacDtoList(List<Transac> transacList)
    {
        if (transacList == null)
        {
            return new ArrayList<>();
        }
        return transacList
                .stream()
                .map(this::toTransacDto)
                .collect(Collectors.toList());
    }
}
