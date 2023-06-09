/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tester.oumarket;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

/**
 *
 * @author LENOVO
 */
public class ManageServiceController extends AbstractManageController {

    @FXML
    private Pane pane;

    private void changeScene(String fxml) throws IOException {
        App.setRoot(fxml);
    }

    public void manageBranch(ActionEvent event) throws IOException {
        changeScene("ManageBranchMarket");
    }

    public void manageEmployee(ActionEvent event) throws IOException {
        changeScene("ManageEmployeePage");
    }

    public void manageProduct(ActionEvent event) throws IOException {
        changeScene("ManageProductPage");
    }

    public void manageEvent(ActionEvent event) throws IOException {
        changeScene("ManageEventPage");
    }

    public void stats(ActionEvent event) throws IOException {
        changeScene("Stats");
    }
}
