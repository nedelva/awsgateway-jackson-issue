package com.example.datamodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@com.fasterxml.jackson.databind.annotation.JsonDeserialize(builder = Claim.ClaimDeclarationBuilder.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
@Introspected
public class Claim {
    private String id;
    private LocalDate declarationDate;
    private LocalDate claimDate;
    private String circumstances;
    private String contractNumber;

    public static ClaimDeclarationBuilder builder() {
        return new ClaimDeclarationBuilder();
    }

    @JsonIgnoreProperties
    @com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "", buildMethodName = "build")
    public static class ClaimDeclarationBuilder {
        private String id;
        private LocalDate declarationDate;
        private LocalDate claimDate;
        private String circumstances;
        private String contractNumber;


        public ClaimDeclarationBuilder id(String id) {
            this.id = id;
            return this;
        }

        public ClaimDeclarationBuilder claimDate(LocalDate claimDate) {
            this.claimDate = claimDate;
            return this;
        }

        public ClaimDeclarationBuilder declarationDate(LocalDate declarationDate) {
            this.declarationDate = declarationDate;
            return this;
        }

        public ClaimDeclarationBuilder circumstances(String circumstances) {
            this.circumstances = circumstances;
            return this;
        }

        public ClaimDeclarationBuilder contractNumber(String contractNumber) {
            this.contractNumber = contractNumber;
            return this;
        }

        public Claim build() {
            return new Claim(this.id, this.declarationDate, this.claimDate, this.circumstances, this.contractNumber);
        }
    }
}
