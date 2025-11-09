package com.crudcnae.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidade persistida localmente (tabela registro_local_cnae).
 */
public final class RegistroLocalCnae {
    private Integer idLocal;      // AUTOINCREMENT
    private Integer idIBGE;       // id na API IBGE (pode ser null)
    private String codigo;        // ex: "01.11-3"
    private String descricao;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;

    public RegistroLocalCnae() {}

    public RegistroLocalCnae(Integer idIBGE, String codigo, String descricao) {
        this.idIBGE = idIBGE;
        this.codigo = codigo;
        this.descricao = descricao;
        this.dataCadastro = LocalDateTime.now();
        this.dataAtualizacao = null;
    }

    public Integer getIdLocal() { return idLocal; }
    public void setIdLocal(Integer idLocal) { this.idLocal = idLocal; }

    public Integer getIdIBGE() { return idIBGE; }
    public void setIdIBGE(Integer idIBGE) { this.idIBGE = idIBGE; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }

    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }

    @Override
    public String toString() {
        return "RegistroLocalCnae{" +
                "idLocal=" + idLocal +
                ", idIBGE=" + idIBGE +
                ", codigo='" + codigo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataCadastro=" + dataCadastro +
                ", dataAtualizacao=" + dataAtualizacao +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegistroLocalCnae that = (RegistroLocalCnae) o;
        return Objects.equals(idLocal, that.idLocal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLocal);
    }
}
