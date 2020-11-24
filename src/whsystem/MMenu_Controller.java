// Controller for the MMenu.fxml


package whsystem;

import connectivity.DB_Connection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class MMenu_Controller implements Initializable {

    private static Collection<String> list = new ArrayList<>();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private ResultSetMetaData resultMeta = null;
    private String passwd;
    @FXML private ListView palID;
    @FXML private ListView storeID;






    public void getDataList (String sql, String sql1) {
        try {
            preparedStatement = DB_Connection.getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            resultMeta = resultSet.getMetaData();
            String test = null;
            while (resultSet.next()) {

                list.add(resultSet.getString(sql1));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MMenu_Controller one = new MMenu_Controller();
        String sql = "Select * FROM pallets";
        String sql1 = "SKU_number";
        one.getDataList(sql, sql1);
        palID.getItems().addAll(list);
        list.clear();
        one.getDataList("Select * FROM stores","name");
        storeID.getItems().addAll(list);

    }


    public void setString(String line) {
        this.passwd = line;
    }
}
