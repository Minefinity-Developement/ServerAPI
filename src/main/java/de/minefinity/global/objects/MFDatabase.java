package de.minefinity.global.objects;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
    public MongoClient mongoClient;

    @Getter @Setter
    public MongoDatabase mongoDatabase;

    public ArrayList<MFTable> tables = new ArrayList<>();

    @Getter
    private final ExecutorService executorService;

    public MFDatabase(String HOST, String USER, String PASSWORD, String DATABASE, int PORT, Logger logger) {
        this.HOST = HOST;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
        this.DATABASE = DATABASE;
        this.PORT = PORT;

        this.logger = logger;

        this.executorService = Executors.newFixedThreadPool(8);
    }


    public void connect() {
        try {
            this.mongoClient = new MongoClient(new MongoClientURI(MessageFormat.format("mongoDb://{0}:{1}@{2}:{3}", new Object[]{
                    this.USER,
                    this.PASSWORD,

                    this.HOST,

                    this.PORT
            })));

            this.mongoDatabase = this.getMongoClient().getDatabase(this.DATABASE);

            this.logger.log(Level.INFO, "Verbindung zur Datenbank wurde hergestellt.");


        } catch (Exception e) {
            e.printStackTrace();

            this.logger.log(Level.WARNING, "Verbindung zur Datenbank konnte nicht hergestellt werden.");

        }
    }


    public void disconnect() {
        try {
            this.mongoClient.close();

            this.logger.log(Level.INFO, "Verbindung zur Datenbank wurde getrennt.");
        } catch (Exception e) {
            e.printStackTrace();

            this.logger.log(Level.WARNING, "Verbindung zur Datenbank konnte nicht getrennt werden.");
        }
    }


}
