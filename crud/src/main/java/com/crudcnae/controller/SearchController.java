package com.crudcnae.controller;

import com.crudcnae.api.ApiCnaeFacade;
import com.crudcnae.dao.CnaeDAO;
import com.crudcnae.dao.DatabaseManager;
import com.crudcnae.model.RegistroLocalCnae;
import com.crudcnae.model.SubclasseCnae;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador da tela de pesquisa detalhada.
 */
public class SearchController {

    @FXML private TextField txtCnae;
    @FXML private TableView<SubclasseCnae> tableResultados;
    @FXML private TableColumn<SubclasseCnae, Integer> colId;
    @FXML private TableColumn<SubclasseCnae, String> colDescricao;

    private final ApiCnaeFacade api = new ApiCnaeFacade();
    private final DatabaseManager db = new DatabaseManager();
    private final CnaeDAO dao = new CnaeDAO(db);

    @FXML
    private void initialize() {
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getIdIBGE()).asObject());
        colDescricao.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescricao()));
    }

    @FXML
    public void onPesquisar() {
        try {
            String entrada = txtCnae.getText().trim();
            if (entrada.isEmpty()) {
                showInfo("Informe um número de CNAE completo.");
                return;
            }
            List<SubclasseCnae> result = api.consultar("subclasse", entrada);
            tableResultados.setItems(FXCollections.observableArrayList(result));
        } catch (Exception e) {
            showError("Erro na consulta à API", e.getMessage());
        }
    }

    @FXML
    public void onSalvarTabela() {
        try {
            List<SubclasseCnae> selecionados = tableResultados.getSelectionModel().getSelectedItems();
            if (selecionados.isEmpty()) {
                selecionados = tableResultados.getItems(); // salva todos se nada selecionado
            }
            for (SubclasseCnae s : selecionados) {
                dao.inserir(new RegistroLocalCnae(s.getIdIBGE(), s.getCodigo(), s.getDescricao()));
            }
            showInfo("CNAEs salvos com sucesso no banco local!");
        } catch (Exception e) {
            showError("Erro ao salvar CNAEs", e.getMessage());
        }
    }

    @FXML
    public void onEditarSelecionado() {
        SubclasseCnae s = tableResultados.getSelectionModel().getSelectedItem();
        if (s == null) {
            showInfo("Selecione um item para editar.");
            return;
        }
        TextInputDialog dialog = new TextInputDialog(s.getDescricao());
        dialog.setTitle("Editar CNAE");
        dialog.setHeaderText("Editar descrição da Subclasse");
        dialog.setContentText("Nova descrição:");
        dialog.showAndWait().ifPresent(newDesc -> {
            RegistroLocalCnae reg = dao.listarTodos().stream()
                    .filter(r -> r.getIdIBGE() == s.getIdIBGE())
                    .findFirst()
                    .orElse(null);
            if (reg != null) {
                reg.setDescricao(newDesc);
                dao.atualizar(reg);
                showInfo("Descrição atualizada localmente!");
            } else {
                showInfo("Este CNAE ainda não foi salvo localmente.");
            }
        });
    }

    @FXML
    public void onExcluirSelecionados() {
        List<Integer> ids = tableResultados.getSelectionModel().getSelectedItems().stream()
                .map(SubclasseCnae::getIdIBGE)
                .collect(Collectors.toList());
        if (ids.isEmpty()) {
            showInfo("Nenhum item selecionado para exclusão.");
            return;
        }
        dao.excluir(ids);
        showInfo("Registros excluídos com sucesso!");
    }

    @FXML
    public void onVoltar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/crudcnae/view/MainView.fxml"));
            Stage stage = (Stage) tableResultados.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Consulta de Subclasses CNAE");
        } catch (IOException e) {
            showError("Erro ao voltar", e.getMessage());
        }
    }

    private void showError(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void showInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
