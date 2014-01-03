package me.masahito.localdb;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.google.inject.Guice;
import com.google.inject.Injector;
import me.masahito.localdb.impl.LevelDBModule;

import java.nio.file.FileSystems;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) {

        // create Metric
        final MetricRegistry registry = new MetricRegistry();
        final ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
                .build();
        reporter.start(50, TimeUnit.MILLISECONDS);

        try {

            Injector injector = Guice.createInjector(new LevelDBModule(registry));
            final RealLocalKVS kvs = injector.getInstance(RealLocalKVS.class);

            kvs.start(FileSystems.getDefault().getPath(".", "test"));

            int i = 0;
            while(i < 1000) {
                kvs.put(i, "test");
                assert kvs.get(i).equals("test");
                i++;
            }
            kvs.close();
        } finally {
            reporter.close();

        }
    }
}
