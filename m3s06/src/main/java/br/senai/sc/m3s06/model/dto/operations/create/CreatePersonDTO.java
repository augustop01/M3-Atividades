package br.senai.sc.m3s06.model.dto.operations.create;

import br.senai.sc.m3s06.enums.NotificationsEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePersonDTO(@NotBlank String name,
                              @NotBlank String email,
                              @NotBlank String phone,
                              @NotBlank String password,
                              @NotNull NotificationsEnum notificationsEnum){

}
