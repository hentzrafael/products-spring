package com.example.springboot.dtos;

import jakarta.validation.constraints.NotBlank;

public record EstabelecimentoRecordDto(
        @NotBlank String cnpj,
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String password
) {
}
