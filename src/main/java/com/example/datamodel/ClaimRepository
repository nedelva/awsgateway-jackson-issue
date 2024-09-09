package com.example.datamodel;

import jakarta.inject.Singleton;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;
import java.util.Optional;

@Singleton
public class ClaimRepository {
    public Optional<Claim> findById(String id) {
        return switch(Objects.requireNonNull(id)) {
            case "1" -> Optional.of(new Claim("1", LocalDate.now(), null, null, null));
            case "1234" -> Optional.of(new Claim("1234", LocalDate.of(2024, Month.JULY, 1), LocalDate.of(2024, Month.JUNE, 15), "some description", null));
            default -> Optional.empty();
        };
    }
}
