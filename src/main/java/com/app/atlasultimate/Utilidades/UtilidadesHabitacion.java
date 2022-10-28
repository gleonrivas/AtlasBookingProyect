package com.app.atlasultimate.Utilidades;

public class UtilidadesHabitacion {

    public static String tipoHabitacion(Integer camaIndividual, Integer camadoble) {
        if (camaIndividual == 1 && camadoble == 0) {
            return "Habitación individual con cama individual";
        } else if (camaIndividual == 0 && camadoble == 1) {
            return "Habitación doble con una cama doble";
        } else if(camaIndividual == 2 && camadoble == 0){
            return "Habitación doble con camas individuales";
        } else (camaIndividual < 2 || camadoble<2){
            return "Habitación múltiple";
        }
    }
}
