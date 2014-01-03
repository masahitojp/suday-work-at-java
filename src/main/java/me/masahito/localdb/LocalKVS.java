package me.masahito.localdb;

import java.io.IOException;
import java.nio.file.Path;

public interface LocalKVS {
    public void start(final Path path);
    public void close() throws IOException;

    public void put(final Integer key, final String value);
    public String get(final Integer key);
}
