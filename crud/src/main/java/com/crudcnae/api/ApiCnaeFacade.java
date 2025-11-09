package com.crudcnae.api;

import com.crudcnae.model.SubclasseCnae;
import java.io.IOException;
import java.util.List;

/**
 * Facade para simplificar o uso da API IBGE.
 * O Controller chama apenas esta classe.
 */
public class ApiCnaeFacade {

    private final ApiCnaeFactory factory = new ApiCnaeFactory();

    public List<SubclasseCnae> listarSubclasses() throws IOException, InterruptedException {
        return factory.criarEstrategia("subclasse").consultar("");
    }

    public List<SubclasseCnae> consultar(String tipo, String parametro) throws IOException, InterruptedException {
        ApiCnaeStrategy strategy = factory.criarEstrategia(tipo);
        return strategy.consultar(parametro);
    }

    public SubclasseCnae consultarPorId(int id) throws IOException, InterruptedException {
        return factory.criarEstrategia("subclasse").consultarPorId(id);
    }
}
