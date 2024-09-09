package com.example.datamodel;

import com.example.ClaimDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.JSR330)
public interface ClaimMapper {
        Claim publishedToInternal(ClaimDTO claimDto);
        ClaimDTO internalToPublished(Claim claim);
}
