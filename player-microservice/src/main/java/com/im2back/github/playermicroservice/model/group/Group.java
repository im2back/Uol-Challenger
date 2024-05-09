package com.im2back.github.playermicroservice.model.group;

public enum Group {
    VINGADORES,
    LIGA_DA_JUSTICA;

    public static Group convertStringInGroup(String param) {
        for (Group group : Group.values()) {
            if (group.name().equals(param)) {
                return group;
            }
        }
        throw new IllegalArgumentException("Grupo inválido: " + param);
    }
}
