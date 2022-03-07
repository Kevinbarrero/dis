package ru.itis.service;
import ru.itis.db.BlockModel;
import ru.itis.db.DataModel;

import java.sql.*;
import java.util.List;
public class DbWorker {
    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    public static void EstablishConnection() throws SQLException {


        String url = "jdbc:postgresql://localhost:5432/blockchain";
        String username = "postgres";
        String password = "";
        System.out.println("DB Connection established......");
        connection = DriverManager.getConnection(url, username, password);
    }

    public static void addChainDB(List<BlockModel> chain) {
        if (chain == null) return;
        clearDB();
        for (BlockModel blockModel : chain) {
            addBlockDB(blockModel);
        }
    }

    public static void addBlockDB(BlockModel blockModel) {

        String sqlModel = "INSERT into block_info(info) values ((?)::json)";
        String sqlBlock = "INSERT into block_data(block_id,prev_hash,public_key,signature,block_formation_date) values(?,?,?,?,?)";
        PreparedStatement add_model;
        PreparedStatement add_block;
        try {
            //Add Model
            add_model = connection.prepareStatement(sqlModel);
            add_model.setString(1, blockModel.getData().toString());
            add_model.execute();

            //Add Block
            add_block = connection.prepareStatement(sqlBlock);
            int index = getDataModelIndex(blockModel.getData());
            add_block.setInt(1, index);
            add_block.setString(2, blockModel.getPrevhash());
            add_block.setString(3, blockModel.getPublickey());
            add_block.setString(4, blockModel.getSignature());
            add_block.setString(5, blockModel.getTs());
            add_block.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int getDataModelIndex(DataModel dataModel) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("Select id from block_info where (info ->> 'name' = ?) and (info ->> 'data' = ?)");
        preparedStatement.setString(1, dataModel.getName());
        preparedStatement.setString(2, dataModel.getData());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("id");
    }

    public static void clearDB() {
        try {
            connection.createStatement().execute("DELETE FROM block_data where block_id != null ");
            connection.createStatement().execute("DELETE FROM block_info where id != null ");
        } catch (SQLException e) {
            System.err.println("Dropping Data Failed");
            e.printStackTrace();
        }
    }
}
