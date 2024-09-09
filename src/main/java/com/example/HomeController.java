package com.example;

import com.example.datamodel.Claim;
import com.example.datamodel.ClaimMapper;
import com.example.datamodel.ClaimRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@io.micronaut.http.annotation.Controller("/claims")
@Slf4j
public class HomeController {

    @Inject
    private ClaimRepository repository;
    @Inject
    private ClaimMapper mapper;

    @Get(value = "{id}", produces = MediaType.APPLICATION_JSON)
    public Optional<ClaimDTO> getClaim(@PathVariable @NonNull final String id,
                                                 HttpHeaders httpHeaders) {

        Optional<Claim> optionalClaimDeclaration = repository.findById(id);
        log.info("findById returned {}", optionalClaimDeclaration);
        return optionalClaimDeclaration.map(mapper::internalToPublished);
    }
    
}
