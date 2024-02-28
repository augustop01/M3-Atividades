package br.senai.sc.m3s04.model.dto.operations.create;

import jakarta.validation.constraints.NotBlank;

public record CreatePersonDTO(@NotBlank String name,
                              @NotBlank String email,
                              @NotBlank String password){
}
