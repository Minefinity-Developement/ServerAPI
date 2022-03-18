package de.minefinity.global.objects;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MFTable {


    /**
     *
     * Cache:
     * SELECT's zwischenspeichern (innerhalb 5 Minuten)
     *
     */

    public String table = "";

    @Getter @Setter
    public MFDatabase database;




    public MFTable(String TABLE) {
        this.table = TABLE;
    }


    public ResultSet select(HashMap<String, String> where){

        ArrayList<String> whereArray = new ArrayList<>();
        ArrayList<String> whereValues = new ArrayList<>();


        for(Map.Entry<String, String> entries : where.entrySet()){
            whereArray.add(entries.getKey() + " = ? ");
            whereValues.add(entries.getValue());
        }

        String sql = "SELECT * FROM " + table + (where.size() >= 1 ? " WHERE " + String.join(" AND ", whereArray) : "");

        ResultSet resultSet = null;

        try {
            PreparedStatement preparedStatement = this.getDatabase().getConnection().prepareStatement(sql);

            for(int i = 0; i <= whereValues.size(); i++){
                preparedStatement.setString(i, whereValues.get(i));
            }

            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;

    }




}
