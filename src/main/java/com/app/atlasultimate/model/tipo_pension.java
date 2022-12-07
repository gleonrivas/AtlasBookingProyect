package com.app.atlasultimate.model;

public enum tipo_pension {
    sa("Solo alojamineto"),
    ad("Almuerzo y desayuno"),
    pc("Pensión completa"),
    mp("Media pensión");

    private final String displayValue;

    tipo_pension(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

}
