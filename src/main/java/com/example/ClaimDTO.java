package com.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@com.fasterxml.jackson.databind.annotation.JsonDeserialize(builder = ClaimDTO.Builder.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
@Introspected
public class ClaimDTO {

    private String id;
    private LocalDate claimDate;
    private String circumstances;


    public static Builder builder() {
        return new Builder();
    }

    @JsonIgnoreProperties
    @com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "", buildMethodName = "build")
    public static class Builder {

        private String id;
        private LocalDate claimDate;
        private String circumstances;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder claimDate(LocalDate claimDate) {
            this.claimDate = claimDate;
            return this;
        }

        public Builder circumstances(String circumstances) {
            this.circumstances = circumstances;
            return this;
        }

        public ClaimDTO build() {
            return new ClaimDTO(this.id, this.claimDate, this.circumstances);
        }
    }
}
