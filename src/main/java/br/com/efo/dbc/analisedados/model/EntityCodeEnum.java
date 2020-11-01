package br.com.efo.dbc.analisedados.model;

import lombok.AllArgsConstructor;
import lombok.val;

@AllArgsConstructor
public enum EntityCodeEnum {

    VENDOR("001"),
    CLIENT("002"),
    SALES("003"),
    UNKNOWN("UNKNOWN");

    private final String code;

    public static EntityCodeEnum getEntityByCode(final String code) {
        for (val e : values()) {
            if (e.code.equals(code)) {
                return e;
            }
        }
        return UNKNOWN;
    }
}
