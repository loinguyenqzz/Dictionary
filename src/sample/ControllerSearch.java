package sample;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class ControllerSearch {
    @FXML
    private Label alo;

    @FXML
    private JFXTextField searchEnglish;

    @FXML
    private JFXTextArea displayTranslate;

    @FXML
    private JFXListView<String> listSuggest;

    public void getWord(ActionEvent event) {
        String s = searchEnglish.getText();
        displayTranslate.setText(new database().search(s));
    }

    public void selectList(MouseEvent event) {
        searchEnglish.setText(listSuggest.getSelectionModel().getSelectedItem());
    }

    public void showList(KeyEvent event) {
        String s = searchEnglish.getText();
        List<String> list;
        list = new database().suggestion(s);
        listSuggest.getItems().clear();
        listSuggest.getItems().addAll(list);
        list.clear();
    }

//    public void showList(MouseEvent event) {
//        List<String> list;
//        list = new database().suggestion();
//        listSuggest.getItems().addAll(list);
//        list.clear();
//    }

    public void readText(ActionEvent event) {
        VoiceManager freeVM;
        Voice voice;
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();// Allocating Voice
            try {
                voice.setRate(190);// Setting the rate of the voice
                voice.setPitch(150);// Setting the Pitch of the voice
                voice.setVolume(3);// Setting the volume of the voice
                voice.speak(searchEnglish.getText());// Calling speak() method

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } else {
            throw new IllegalStateException("Cannot find voice: kevin16");
        }
    }
}
