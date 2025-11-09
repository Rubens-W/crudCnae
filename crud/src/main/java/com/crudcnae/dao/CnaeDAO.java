package com.crudcnae.dao;

import com.crudcnae.model.RegistroLocalCnae;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO simples para registro_local_cnae.
 *
 * Observações:
 * - Datas são persistidas como ISO-8601 (String).
 * - Métodos lançam RuntimeException em caso de erro (pode mudar para checked exceptions).
 */
public final class CnaeDAO {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private final DatabaseManager dbManager;

    public CnaeDAO(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public void inserir(RegistroLocalCnae reg) {
        String sql = "INSERT INTO registro_local_cnae (id_ibge, codigo, descricao, data_cadastro, data_atualizacao) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            conn.setAutoCommit(false);
            ps.setObject(1, reg.getIdIBGE(), Types.INTEGER);
            ps.setString(2, reg.getCodigo());
            ps.setString(3, reg.getDescricao());
            LocalDateTime now = reg.getDataCadastro() != null ? reg.getDataCadastro() : LocalDateTime.now();
            ps.setString(4, now.format(FORMATTER));
            ps.setString(5, reg.getDataAtualizacao() != null ? reg.getDataAtualizacao().format(FORMATTER) : null);

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    reg.setIdLocal(generatedId);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir registro local CNAE", e);
        }
    }

    public List<RegistroLocalCnae> listarTodos() {
        String sql = "SELECT id_local, id_ibge, codigo, descricao, data_cadastro, data_atualizacao FROM registro_local_cnae ORDER BY id_local";
        List<RegistroLocalCnae> result = new ArrayList<>();
        try (Connection conn = dbManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                RegistroLocalCnae r = mapResultSet(rs);
                result.add(r);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar registros locais CNAE", e);
        }
        return result;
    }

    public RegistroLocalCnae buscarPorId(int idLocal) {
        String sql = "SELECT id_local, id_ibge, codigo, descricao, data_cadastro, data_atualizacao FROM registro_local_cnae WHERE id_local = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idLocal);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar registro por idLocal=" + idLocal, e);
        }
    }

    public void atualizar(RegistroLocalCnae reg) {
        if (reg.getIdLocal() == null) {
            throw new IllegalArgumentException("Registro deve ter idLocal para atualização");
        }
        String sql = "UPDATE registro_local_cnae SET id_ibge = ?, codigo = ?, descricao = ?, data_atualizacao = ? WHERE id_local = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            ps.setObject(1, reg.getIdIBGE(), Types.INTEGER);
            ps.setString(2, reg.getCodigo());
            ps.setString(3, reg.getDescricao());
            LocalDateTime now = LocalDateTime.now();
            reg.setDataAtualizacao(now);
            ps.setString(4, now.format(FORMATTER));
            ps.setInt(5, reg.getIdLocal());
            int affected = ps.executeUpdate();
            conn.commit();
            if (affected == 0) {
                throw new RuntimeException("Nenhuma linha atualizada (idLocal=" + reg.getIdLocal() + ")");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar registro local CNAE idLocal=" + reg.getIdLocal(), e);
        }
    }

    public void excluir(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) return;
        String sql = "DELETE FROM registro_local_cnae WHERE id_local = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            for (Integer id : ids) {
                ps.setInt(1, id);
                ps.addBatch();
            }
            ps.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir registros locais CNAE", e);
        }
    }

    private RegistroLocalCnae mapResultSet(ResultSet rs) throws SQLException {
        RegistroLocalCnae r = new RegistroLocalCnae();
        r.setIdLocal(rs.getInt("id_local"));
        int idibge = rs.getInt("id_ibge");
        if (!rs.wasNull()) r.setIdIBGE(idibge);
        r.setCodigo(rs.getString("codigo"));
        r.setDescricao(rs.getString("descricao"));
        String created = rs.getString("data_cadastro");
        if (created != null) r.setDataCadastro(LocalDateTime.parse(created, FORMATTER));
        String updated = rs.getString("data_atualizacao");
        if (updated != null) r.setDataAtualizacao(LocalDateTime.parse(updated, FORMATTER));
        return r;
    }
}
