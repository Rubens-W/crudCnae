package com.crudcnae.api;

import com.crudcnae.model.SubclasseCnae;
import java.io.IOException;
import java.util.List;

/**
 * Strategy para diferentes tipos de consulta na API do IBGE CNAE 2.0.
 */
public interface ApiCnaeStrategy {
    List<SubclasseCnae> consultar(String parametro) throws IOException, InterruptedException;
    SubclasseCnae consultarPorId(int id) throws IOException, InterruptedException;
}
