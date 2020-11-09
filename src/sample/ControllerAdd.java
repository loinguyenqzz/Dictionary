package sample;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class ControllerAdd {
    @FXML
    private JFXTextField addEnglish;

    @FXML
    private JFXTextArea addVietnamese;

    public void submitAdded(ActionEvent event) {
        Vocabulary word = new Vocabulary();
        word.English = addEnglish.getText();
        word.Vietnamese = addVietnamese.getText();
        Alert addSuccess = new Alert(Alert.AlertType.INFORMATION);
        if(new database().addWord(word)) {
            addSuccess.setContentText("Bạn đã thêm 1 từ mới: " + word.English);
            addSuccess.setHeaderText("Bạn đã thêm thành công !");
            addSuccess.setTitle("Thêm từ vựng");
            addSuccess.show();
        } else {
            addSuccess.setContentText("Từ này đã có trong danh sách !");
            addSuccess.setHeaderText("Thêm từ mới không thành công !");
            addSuccess.setTitle("Thêm từ vựng");
            addSuccess.show();
        }
    }
}
