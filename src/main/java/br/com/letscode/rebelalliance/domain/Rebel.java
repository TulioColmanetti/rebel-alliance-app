package br.com.letscode.rebelalliance.domain;

import br.com.letscode.rebelalliance.enums.Race;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Rebel {
    @NotNull
    @NotEmpty
    private String Name;

    @NotNull
    @NotEmpty
    @Min(value = 0)
    private int Age;

    @NotNull
    @NotEmpty
    private Race Race;
}
