package com.crudcnae.api;

/**
 * Simple Factory para escolher a Strategy adequada de acordo com o tipo de consulta.
 */
public class ApiCnaeFactory {

    public ApiCnaeStrategy criarEstrategia(String tipoConsulta) {
        if (tipoConsulta == null) return new ApiCnaeSubclasseStrategy();
        switch (tipoConsulta.toLowerCase()) {
            case "classe":
                return new ApiCnaeClasseStrategy();
            case "grupo":
                return new ApiCnaeGrupoStrategy();
            case "divisao":
                return new ApiCnaeDivisaoStrategy();
            case "secao":
                return new ApiCnaeSecaoStrategy();
            default:
                return new ApiCnaeSubclasseStrategy();
        }


    }
}
