package com.example.cryptodemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor()
@NoArgsConstructor()
public class MainResponseDTO {
    @JsonProperty("text")
    private String text;
    @JsonProperty("text1")
    private String text1;
    @JsonProperty("text2")
    private String text2;
}
