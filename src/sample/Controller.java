package sample;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Controller {
    @FXML
    private BorderPane home;
    public void search(MouseEvent event) {
        loadPage("search");
    }
    public void edit(MouseEvent event) { loadPage("edit"); }
    public void delete(MouseEvent event) {
        loadPage("delete");
    }
    public void add(MouseEvent event) {
        loadPage("add");
    }

    private void loadPage(String page) {
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource(page +".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        home.setCenter(root);
    }
}
