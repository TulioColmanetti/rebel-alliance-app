package br.com.letscode.rebelalliance.enums;

public enum OrderBy {
    NAME("Nome"),
    AGE("Idade"),
    RACE("Raça");

    private String description;

    OrderBy(String description) {this.description = description;}

    public String getDescription() { return description; }
}
