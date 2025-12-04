package com.jhontruse.wsrcitamedica.model.record;

import com.fasterxml.jackson.annotation.JsonProperty;

// JWT - AUTH
public record JwtResponse(
        @JsonProperty(value = "access_token") String accessToken,
        @JsonProperty(value = "AuthType") String AuthType) {

}
