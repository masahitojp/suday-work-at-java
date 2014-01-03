package me.masahito.localdb.impl;


import com.google.common.primitives.Ints;
import me.masahito.localdb.LocalKVS;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import static org.fusesource.leveldbjni.JniDBFactory.*;

import java.io.IOException;
import java.nio.file.Path;

public class LevelDBKVS implements LocalKVS {

    private Options options;
    private DB db;

    @Override
    public void start(Path path) {

        try {
            options = new Options();
            options.createIfMissing(true);
            db = factory.open(path.getFileName().toFile(), options);
        } catch (IOException e) {
            // do nothing
        }
    }

    @Override
    public void close() throws IOException {
        db.close();
    }

    @Override
    public void put(Integer key, String value) {
        db.put(Ints.toByteArray(key), bytes(value));
    }

    @Override
    public String get(Integer key) {
        return new String(db.get(Ints.toByteArray(key)));
    }
}
