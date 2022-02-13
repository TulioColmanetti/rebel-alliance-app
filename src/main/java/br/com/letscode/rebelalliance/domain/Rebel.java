package br.com.letscode.rebelalliance.domain;

import br.com.letscode.rebelalliance.enums.Race;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Rebel {
    private String Name;
    private int Age;
    private Race Race;
}
