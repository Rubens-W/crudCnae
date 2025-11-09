package com.crudcnae.api;

import com.crudcnae.model.SubclasseCnae;
import java.io.IOException;
import java.util.List;

/**
 * Estratégia para consultar subclasses gerais ou por id.
 */
public class ApiCnaeSubclasseStrategy extends AbstractApiCnaeStrategy {

    @Override
    public List<SubclasseCnae> consultar(String parametro) throws IOException, InterruptedException {
        // se parametro for vazio → listar todas
        if (parametro == null || parametro.isBlank()) {
            return fetchList("/subclasses");
        }
        // se for número → consulta por ID
        try {
            int id = Integer.parseInt(parametro);
            SubclasseCnae single = consultarPorId(id);
            return List.of(single);
        } catch (NumberFormatException e) {
            // caso contrário, faz uma busca textual (não há endpoint direto, então filtra localmente)
            List<SubclasseCnae> all = fetchList("/subclasses");
            String lower = parametro.toLowerCase();
            return all.stream()
                    .filter(s -> s.getDescricao().toLowerCase().contains(lower)
                              || s.getCodigo().toLowerCase().contains(lower))
                    .toList();
        }
    }

    @Override
    public SubclasseCnae consultarPorId(int id) throws IOException, InterruptedException {
        return fetchSingle("/subclasses/" + id);
    }
}
