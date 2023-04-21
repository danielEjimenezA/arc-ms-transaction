package com.pichincha.springjparest.service.mapper;

import com.pichincha.springjparest.domain.Client;
import com.pichincha.springjparest.service.dto.ClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ClientMapper
{
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(
            source = "id",
            target = "id"
    )
    @Mapping(
            source = "password",
            target = "password"
    )
    @Mapping(
            source = "status",
            target = "status"
    )
    @Mapping(
            source = "personDto",
            target = "person"
    )
    Client toClient(ClientDto clientDto);

    @Mapping(
            source = "id",
            target = "id"
    )
    @Mapping(
            source = "password",
            target = "password"
    )
    @Mapping(
            source = "status",
            target = "status"
    )
    @Mapping(
            source = "person",
            target = "personDto"
    )
    ClientDto toClientDto(Client client);

    default List<Client> toClientList(List<ClientDto> clientDtoList)
    {
        if (clientDtoList == null)
        {
            return new ArrayList<>();
        }
        return clientDtoList
                .stream()
                .map(this::toClient)
                .collect(Collectors.toList());
    }

    default List<ClientDto> toClientDtoList(List<Client> clientList)
    {
        if (clientList == null)
        {
            return new ArrayList<>();
        }
        return clientList
                .stream()
                .map(this::toClientDto)
                .collect(Collectors.toList());
    }
}
