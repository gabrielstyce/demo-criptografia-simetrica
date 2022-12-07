package com.example.cryptodemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor()
@NoArgsConstructor()
public class MainRequestDTO {
    @JsonProperty("text")
    private String text;
}
