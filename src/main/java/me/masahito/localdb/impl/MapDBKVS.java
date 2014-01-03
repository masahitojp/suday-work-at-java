package me.masahito.localdb.impl;

import me.masahito.localdb.LocalKVS;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentNavigableMap;

public class MapDBKVS implements LocalKVS {
    private DB db;
    private ConcurrentNavigableMap<Integer,String> map;


    @Override
    public void start(Path path) {
        // configure and open database using builder pattern.
        // all options are available with code auto-completion.
        this.db = DBMaker.newFileDB(path.getFileName().toFile())
                .closeOnJvmShutdown()
                .make();

        // open existing an collection (or create new)
        this.map = this.db.getTreeMap("collectionName");
    }

    @Override
    public void close() {
        this.db.close();
    }

    @Override
    public void put(Integer key, String value) {
        map.put(key, value);
    }

    @Override
    public String get(Integer key) {
        return this.map.get(key);
    }
}
