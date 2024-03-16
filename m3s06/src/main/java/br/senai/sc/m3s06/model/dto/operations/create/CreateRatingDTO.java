package br.senai.sc.m3s06.model.dto.operations.create;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateRatingDTO(@NotNull @Min(1) @Max(5) Integer setRating) {
}
