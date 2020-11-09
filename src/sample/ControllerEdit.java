package sample;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class ControllerEdit {
    @FXML
    private JFXTextField beforeEnglish;

    @FXML
    private JFXTextField afterEnglish;

    @FXML
    private JFXTextArea TranslateEnglish;

    @FXML

    public void submitUpdated(ActionEvent event) {
        String word;
        Vocabulary update = new Vocabulary();
        word = beforeEnglish.getText();
        update.English = afterEnglish.getText();
        update.Vietnamese = TranslateEnglish.getText();
        Alert updateSuccess = new Alert(Alert.AlertType.INFORMATION);
        if (new database().updateWord(word, update.English, update.Vietnamese)) {
            updateSuccess.setHeaderText("Bạn đã sủa từ vựng thành công !");
            updateSuccess.setTitle("Sửa từ vựng");
            updateSuccess.setContentText("Bạn đã sửa từ: " + word + " thành " + update.English);
            updateSuccess.show();
        } else {
            updateSuccess.setHeaderText("Bạn đã sửa từ vựng không thành công !");
            updateSuccess.setTitle("Sửa từ vựng");
            updateSuccess.setContentText("Từ bạn cần tìm không có trong danh sách");
            updateSuccess.show();
        }
    }
}
