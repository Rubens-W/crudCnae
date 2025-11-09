package com.crudcnae.api;

import com.crudcnae.model.SubclasseCnae;
import java.util.List;

public class ApiCliTest {
    public static void main(String[] args) throws Exception {
        ApiCnaeFacade api = new ApiCnaeFacade();

        System.out.println("🔹 Testando listagem de subclasses (primeiras 5):");
        List<SubclasseCnae> list = api.listarSubclasses();
        list.stream().limit(5).forEach(System.out::println);

        System.out.println("\n🔹 Testando busca por ID (id=4929902):");
        SubclasseCnae s = api.consultarPorId(4929902);
        System.out.println(s);

        System.out.println("\n🔹 Testando filtro textual (parametro='TRANSPORTE'):");
        List<SubclasseCnae> filtrados = api.consultar("classe", "TRANSPORTE");
        filtrados.stream().limit(5).forEach(System.out::println);
    }
}
