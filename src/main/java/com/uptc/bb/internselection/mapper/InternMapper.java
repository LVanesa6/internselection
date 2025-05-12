package com.uptc.bb.internselection.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.uptc.bb.internselection.dto.InternDTO;
import com.uptc.bb.internselection.entity.Intern;

@Mapper
public interface InternMapper {

    InternMapper INSTANCE = Mappers.getMapper(InternMapper.class);

    Intern mapInternDTOToIntern(InternDTO internDTO);

    InternDTO mapInternToInternDTO(Intern intern);
}
