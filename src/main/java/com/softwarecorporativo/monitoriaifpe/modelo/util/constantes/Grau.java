package com.softwarecorporativo.monitoriaifpe.modelo.util.constantes;

public enum Grau {
    SUPERIOR("Superior"), TECNICO("Técnico");

    private final String label;

    private Grau(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
