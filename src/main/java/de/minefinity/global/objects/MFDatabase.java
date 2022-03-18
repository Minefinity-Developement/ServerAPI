package de.minefinity.global.objects;

import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MFDatabase {


    public String HOST = "";
    public String USER = "";
    public String PASSWORD = "";
    public String DATABASE = "";

    public int PORT = 3306;

    public Logger logger;

    @Getter @Setter
    public Connection connection;


    public ArrayList<MFTable> tables = new ArrayList<>();


    public MFDatabase(String HOST, String USER, String PASSWORD, String DATABASE, int PORT, Logger logger) {
        this.HOST = HOST;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
        this.DATABASE = DATABASE;
        this.PORT = PORT;

        this.logger = logger;
    }


    public void connect() {
        try {
            this.connection = DriverManager.getConnection(MessageFormat.format("jdbc:mysql://{0}:{1}/{2}", new Object[]{
                    this.HOST,
                    this.PORT,
                    this.DATABASE
            }), this.USER, this.PASSWORD);

            this.logger.log(Level.INFO, "Verbindung zur Datenbank wurde hergestellt.");


            for (MFTable table : this.tables) {
                table.setDatabase(this);
            }


        } catch (SQLException e) {
            e.printStackTrace();

            this.logger.log(Level.WARNING, "Verbindung zur Datenbank konnte nicht hergestellt werden.");

        }
    }


    public void disconnect() {
        try {
            this.connection.close();

            this.logger.log(Level.INFO, "Verbindung zur Datenbank wurde getrennt.");
        } catch (SQLException e) {
            e.printStackTrace();

            this.logger.log(Level.WARNING, "Verbindung zur Datenbank konnte nicht getrennt werden.");
        }
    }


    public Statement createStatement() {
        try {
            return this.connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
