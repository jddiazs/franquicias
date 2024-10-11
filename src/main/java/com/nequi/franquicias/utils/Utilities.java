package com.nequi.franquicias.utils;

import java.util.Optional;
import java.util.function.Consumer;

public final class Utilities {

    private Utilities() {
        throw new IllegalStateException("Utility class");
    }

    public static  <T> void actualizarSiCambio(T nuevoValor, Consumer<T> actualizador){
        Optional.ofNullable(nuevoValor)
                .filter(valor -> !valor.equals(actualizador))
                .ifPresent(actualizador);

    }
}
