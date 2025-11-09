package com.crudcnae.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.Objects;

/**
 * Modelo representando uma subclasse CNAE (mapeamento da API IBGE).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SubclasseCnae {

    @JsonProperty("id")
    private int idIBGE;

    @JsonDeserialize(using = NestedTextDeserializer.class)
    private String codigo;

    private String descricao;

    @JsonDeserialize(using = NestedTextDeserializer.class)
    private String classe;

    @JsonDeserialize(using = NestedTextDeserializer.class)
    private String grupo;

    @JsonDeserialize(using = NestedTextDeserializer.class)
    private String divisao;

    @JsonDeserialize(using = NestedTextDeserializer.class)
    private String secao;

    public SubclasseCnae() {}

    public SubclasseCnae(int idIBGE, String codigo, String descricao) {
        this.idIBGE = idIBGE;
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getIdIBGE() { return idIBGE; }
    public void setIdIBGE(int idIBGE) { this.idIBGE = idIBGE; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getClasse() { return classe; }
    public void setClasse(String classe) { this.classe = classe; }

    public String getGrupo() { return grupo; }
    public void setGrupo(String grupo) { this.grupo = grupo; }

    public String getDivisao() { return divisao; }
    public void setDivisao(String divisao) { this.divisao = divisao; }

    public String getSecao() { return secao; }
    public void setSecao(String secao) { this.secao = secao; }

    @Override
    public String toString() {
        return "SubclasseCnae{" +
                "idIBGE=" + idIBGE +
                ", codigo='" + codigo + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubclasseCnae that = (SubclasseCnae) o;
        return idIBGE == that.idIBGE;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idIBGE);
    }

    /** Desserializador genérico para converter objetos aninhados em String. */
    public static class NestedTextDeserializer extends StdDeserializer<String> {
        public NestedTextDeserializer() { super(String.class); }

        @Override
        public String deserialize(com.fasterxml.jackson.core.JsonParser p,
                                  com.fasterxml.jackson.databind.DeserializationContext ctxt)
                throws IOException {
            JsonNode node = p.getCodec().readTree(p);
            if (node.isTextual()) {
                return node.asText();
            } else if (node.isObject()) {
                if (node.has("descricao")) return node.get("descricao").asText();
                if (node.has("id")) return String.valueOf(node.get("id").asInt());
                if (node.has("codigo")) return node.get("codigo").asText();
                return node.toString();
            } else if (node.isNumber()) {
                return node.asText();
            }
            return null;
        }
    }
}
