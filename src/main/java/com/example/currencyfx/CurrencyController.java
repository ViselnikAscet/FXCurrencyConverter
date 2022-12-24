package com.example.currencyfx;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;


public class CurrencyController implements Initializable {

    @FXML
    private ChoiceBox<String> fromBox;
    @FXML
    private ChoiceBox<String> toCode;

    @FXML
    private TextField Amount;
    @FXML
    private Label price;
    @FXML
    private ImageView send;

    @FXML
    Label price2;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadData();

    }

    private void LoadData() {
        fromBox.setItems(FXCollections.observableArrayList("USD", "EUR", "JPY", "GBP", "AUD", "CAD", "TRY", "CHF", "CNY", "SEC", "MXN", "NZD", "SGD"));
        toCode.setItems(FXCollections.observableArrayList("USD", "EUR", "JPY", "GBP", "AUD", "CAD", "TRY", "CHF", "CNY", "SEC", "MXN", "NZD", "SGD"));
    }


    public void clickSend() throws IOException, InterruptedException {
        String API_KEY = "Your API Key";
        String url_str = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + fromBox.getValue() + "/" + toCode.getValue() + "/" + Amount.getText() + "";
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        // Convert to JSON
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();
        // Accessing object
        String req_result = jsonobj.get("conversion_result").getAsString();
        price.setText(req_result + "  " + toCode.getValue());
        Thread.sleep(1200);

    }

}
