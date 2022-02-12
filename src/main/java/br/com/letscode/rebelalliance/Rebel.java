package br.com.letscode.rebelalliance;

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
