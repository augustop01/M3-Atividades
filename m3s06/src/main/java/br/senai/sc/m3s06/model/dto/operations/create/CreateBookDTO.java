package br.senai.sc.m3s06.model.dto.operations.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateBookDTO(@NotBlank String title,
                            @NotNull Integer publishedYear) {
}
