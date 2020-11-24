// Creates connection by Login and password through Login.fxml scene

package whsystem;

import connectivity.DB_Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class Login_Controller implements Initializable {

    @FXML
    private TextField textUname;
    @FXML
    private PasswordField textPassword;



    static String dpass;
    Stage dialogStage = new Stage();
    Scene scene;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetMetaData resultMeta = null;





    public void button (ActionEvent actionEvent) throws SQLException {
        String loginName = textUname.getText().toString();
        String password = textPassword.getText().toString();



        String sql = "SELECT * FROM users WHERE username = ? and password = ?";

        try {

            DB_Connection con = new DB_Connection();
            
            dpass = password;
            preparedStatement = DB_Connection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, loginName);
            preparedStatement.setString(2, password );
            resultSet = preparedStatement.executeQuery();




            if (!resultSet.next()) {
                infoBox("Enter Correct Login and Password", "Failed", null);
            } else {
                infoBox("Login Successfull", "Success", null);
                String resource;
                 if (resultSet.getString("privilege").equals("manager"))
                    {
                        resource = "MMenu.fxml";
                        scene = new Scene(FXMLLoader.load(getClass().getResource(resource)));
                        dialogStage.setScene(scene);
                        dialogStage.show();
                    }
                else { infoBox("Only Managers can use order creator, please Login as manager", "Failed", null); }


            }

        } catch (Exception e) {
            e.printStackTrace();
        }




    }

// Shows alerts if login is correct or no.

    public static void infoBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}

