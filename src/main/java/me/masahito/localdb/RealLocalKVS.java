package me.masahito.localdb;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;
import com.google.inject.Inject;

import java.io.IOException;
import java.nio.file.Path;

public class RealLocalKVS {
    private Histogram responseSizes;

    @Inject
    private  MetricRegistry metrics;
    @Inject
    private LocalKVS localdb;

    public RealLocalKVS() {
    }

    public void start(final Path path) {
        localdb.start(path);
    }

    public void close() {
        try {
            localdb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void put(Integer key, String value) {
        responseSizes = this.metrics.histogram(MetricRegistry.name(RealLocalKVS.class, "response-sizes"));
        responseSizes.update(1);
        localdb.put(key, value);
    }

    public final String get(Integer key) {
        return localdb.get(key);
    }
}
