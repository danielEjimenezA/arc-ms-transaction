package com.pichincha.springjparest.service.mapper;

import com.pichincha.springjparest.domain.Person;
import com.pichincha.springjparest.service.dto.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface PersonMapper
{
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(
            source = "id",
            target = "id"
    )
    @Mapping(
            source = "name",
            target = "name"
    )
    @Mapping(
            source = "gender",
            target = "gender"
    )
    @Mapping(
            source = "age",
            target = "age"
    )
    @Mapping(
            source = "address",
            target = "address"
    )
    @Mapping(
            source = "phone",
            target = "phone"
    )
    Person toPerson(PersonDto personDto);

    @Mapping(
            source = "id",
            target = "id"
    )
    @Mapping(
            source = "name",
            target = "name"
    )
    @Mapping(
            source = "gender",
            target = "gender"
    )
    @Mapping(
            source = "age",
            target = "age"
    )
    @Mapping(
            source = "address",
            target = "address"
    )
    @Mapping(
            source = "phone",
            target = "phone"
    )
    PersonDto toPersonDto(Person person);

    default List<Person> toPersonList(List<PersonDto> personDtoList)
    {
        if (personDtoList == null)
        {
            return new ArrayList<>();
        }
        return personDtoList
                .stream()
                .map(this::toPerson)
                .collect(Collectors.toList());
    }

    default List<PersonDto> toPersonDtoList(List<Person> personList)
    {
        if (personList == null)
        {
            return new ArrayList<>();
        }
        return personList
                .stream()
                .map(this::toPersonDto)
                .collect(Collectors.toList());
    }
}
