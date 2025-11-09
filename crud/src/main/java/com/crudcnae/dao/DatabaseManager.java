package com.crudcnae.dao;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Gerencia a conexão com o banco SQLite e inicialização do schema.
 * - Cria o arquivo cnae.db no diretório atual (workdir) se não existir.
 * - Inicializa tabela registro_local_cnae.
 */
public final class DatabaseManager {
    private static final String DB_FILENAME = "cnae.db";
    private static final String JDBC_PREFIX = "jdbc:sqlite:";

    private final String jdbcUrl;

    public DatabaseManager() {
        this(Path.of(DB_FILENAME));
    }

    public DatabaseManager(Path dbPath) {
        this.jdbcUrl = JDBC_PREFIX + dbPath.toAbsolutePath().toString();
    }

    /**
     * Retorna uma nova conexão. Caller deve fechar (try-with-resources).
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl);
    }

    /**
     * Inicializa o schema se necessário.
     */
    public void initSchemaIfNeeded() throws SQLException {
        // Se o arquivo não existir, a conexão criará o arquivo. Usamos DDL idempotente.
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(true);
            String sql1 =
                "CREATE TABLE IF NOT EXISTS registro_local_cnae (" +
                "id_lo  cal INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_ibge INTEGER," +
                "codigo TEXT NOT NULL," +
                "descricao TEXT NOT NULL," +
                "data_cadastro TEXT NOT NULL," +
                "data_atualizacao TEXT" +
                ");";
            String sql2 =
                "CREATE INDEX IF NOT EXISTS idx_registro_idibge ON registro_local_cnae(id_ibge);";
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
        }
    }
}
