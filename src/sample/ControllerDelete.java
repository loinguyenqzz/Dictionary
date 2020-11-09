package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class ControllerDelete {
    @FXML
    private JFXTextField textEnglishDel;

    public void submitDeleted(ActionEvent event) {
        String word;
        word = textEnglishDel.getText();
        Alert delSuccess = new Alert(Alert.AlertType.INFORMATION);
        if (new database().deleteWord(word)) {
            delSuccess.setHeaderText("Bạn đã xóa thành công !");
            delSuccess.setContentText("Bạn đã xóa từ vựng: " + word);
            delSuccess.setTitle("Xóa từ vựng");
            delSuccess.show();
        } else {
            delSuccess.setHeaderText("Xóa từ vựng không thành công");
            delSuccess.setContentText("Từ bạn cần tìm không có trong danh sách");
            delSuccess.setTitle("Xóa từ vựng");
            delSuccess.show();
        }
    }
}
