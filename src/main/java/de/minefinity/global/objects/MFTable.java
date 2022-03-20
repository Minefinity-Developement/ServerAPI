package de.minefinity.global.objects;

import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;
import org.bson.conversions.Bson;

public class MFTable {


    /**
     *
     * Cache:
     * SELECT's zwischenspeichern (innerhalb 5 Minuten)
     *
     */

    public String collection = "";

    @Getter @Setter
    public MongoDatabase mongoDatabase;




    public MFTable(String collection, MFDatabase mfDatabase) {
        this.collection = collection;

        this.setMongoDatabase(mfDatabase.getMongoDatabase());
    }

    public void insert(Document document){
        this.getMongoDatabase().getCollection(this.collection).insertOne(document);
    }


    public void delete(Bson filter){
        this.getMongoDatabase().getCollection(this.collection).deleteMany(filter);
    }


    public void update(Bson filter, Document document){
        this.getMongoDatabase().getCollection(this.collection).updateOne(filter, document);
    }



}
