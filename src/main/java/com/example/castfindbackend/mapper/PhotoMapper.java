package com.example.castfindbackend.mapper;

import com.example.castfindbackend.entity.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PhotoMapper {

    @Mapping(target = "type", source = "type")
    @Mapping(target = "data", source = "bytes")
    @Mapping(target = "otherId", source = "otherId")
    Photo convertFromByteToEntity(byte[] bytes, Long otherId, String type);

}