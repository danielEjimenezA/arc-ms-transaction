package com.pichincha.springjparest.service.mapper;

import com.pichincha.springjparest.domain.Account;
import com.pichincha.springjparest.service.dto.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface AccountMapper
{
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(
            source = "id",
            target = "id"
    )
    @Mapping(
            source = "clientDto",
            target = "client"
    )
    @Mapping(
            source = "accountType",
            target = "accountType"
    )
    @Mapping(
            source = "initialAmount",
            target = "initialAmount"
    )
    @Mapping(
            source = "status",
            target = "status"
    )
    Account toAccount(AccountDto accountDto);

    @Mapping(
            source = "id",
            target = "id"
    )
    @Mapping(
            source = "client",
            target = "clientDto"
    )
    @Mapping(
            source = "accountType",
            target = "accountType"
    )
    @Mapping(
            source = "initialAmount",
            target = "initialAmount"
    )
    @Mapping(
            source = "status",
            target = "status"
    )
    AccountDto toAccountDto(Account account);

    default List<Account> toAccountList(List<AccountDto> accountDtoList)
    {
        if (accountDtoList == null)
        {
            return new ArrayList<>();
        }
        return accountDtoList
                .stream()
                .map(this::toAccount)
                .collect(Collectors.toList());
    }

    default List<AccountDto> toAccountDtoList(List<Account> accountList)
    {
        if (accountList == null)
        {
            return new ArrayList<>();
        }
        return accountList
                .stream()
                .map(this::toAccountDto)
                .collect(Collectors.toList());
    }
}
