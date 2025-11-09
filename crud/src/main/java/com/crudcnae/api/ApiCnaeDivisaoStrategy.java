package com.crudcnae.api;

import com.crudcnae.model.SubclasseCnae;
import java.io.IOException;
import java.util.List;

public class ApiCnaeDivisaoStrategy extends AbstractApiCnaeStrategy {

    @Override
    public List<SubclasseCnae> consultar(String parametro) throws IOException, InterruptedException {
        return fetchList("/divisoes/" + parametro + "/subclasses");
    }

    @Override
    public SubclasseCnae consultarPorId(int id) throws IOException, InterruptedException {
        return fetchSingle("/subclasses/" + id);
    }
}
