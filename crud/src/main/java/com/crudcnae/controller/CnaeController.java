package com.crudcnae.controller;

import com.crudcnae.api.ApiCnaeFacade;
import com.crudcnae.model.SubclasseCnae;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Controlador da tela principal.
 */
public class CnaeController {

    @FXML private TableView<SubclasseCnae> tableCnaes;
    @FXML private TableColumn<SubclasseCnae, Integer> colId;
    @FXML private TableColumn<SubclasseCnae, String> colDescricao;

    private final ApiCnaeFacade api = new ApiCnaeFacade();

    @FXML
    private void initialize() {
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getIdIBGE()).asObject());
        colDescricao.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescricao()));

        buscarSubclassesAPI();
    }

    @FXML
    public void buscarSubclassesAPI() {
        try {
            List<SubclasseCnae> list = api.listarSubclasses();
            ObservableList<SubclasseCnae> obsList = FXCollections.observableArrayList(list);
            tableCnaes.setItems(obsList);
        } catch (Exception e) {
            showAlert("Erro ao consultar API IBGE", e.getMessage());
        }
    }

    @FXML
    public void abrirPesquisaDetalhada() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/crudcnae/view/DetailedView.fxml"));
            Stage stage = (Stage) tableCnaes.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Pesquisa Detalhada");
        } catch (IOException e) {
            showAlert("Erro ao abrir tela", e.getMessage());
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
