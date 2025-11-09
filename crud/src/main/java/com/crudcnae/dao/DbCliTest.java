package com.crudcnae.dao;

import com.crudcnae.model.RegistroLocalCnae;

import java.util.List;

public class DbCliTest {
    public static void main(String[] args) throws Exception {
        DatabaseManager db = new DatabaseManager();
        db.initSchemaIfNeeded();

        CnaeDAO dao = new CnaeDAO(db);

        System.out.println("Inserindo registro de exemplo...");
        RegistroLocalCnae r = new RegistroLocalCnae(12345, "01.11-3", "Cultivo de arroz");
        dao.inserir(r);
        System.out.println("Inserido com idLocal = " + r.getIdLocal());

        System.out.println("Listando todos:");
        List<RegistroLocalCnae> all = dao.listarTodos();
        all.forEach(System.out::println);

        System.out.println("Atualizando descrição...");
        r.setDescricao("Cultivo de arroz (alterado)");
        dao.atualizar(r);

        System.out.println("Buscando por idLocal = " + r.getIdLocal());
        RegistroLocalCnae fetched = dao.buscarPorId(r.getIdLocal());
        System.out.println("Encontrado: " + fetched);

        System  .out.println("Excluindo...");
        dao.excluir(List.of(r.getIdLocal()));
        System.out.println("Registros após exclusão:");
        dao.listarTodos().forEach(System.out::println);
    }
}
