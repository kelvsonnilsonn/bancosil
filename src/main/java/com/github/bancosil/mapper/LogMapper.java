package com.github.bancosil.mapper;

import com.github.bancosil.dto.LogResponseDTO;
import com.github.bancosil.model.Log;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LogMapper {

    @Mapping(target = "author", expression = "java(log.getAuthorName())")
    @Mapping(target = "receiver", expression = "java(log.getReceiverName())")
    LogResponseDTO toResponse(Log log);
}
