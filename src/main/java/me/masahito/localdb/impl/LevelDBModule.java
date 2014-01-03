/*
 * Created by IntelliJ IDEA.
 * User: masahito
 * Date: 2014/01/03
 * Time: 2:59
 */
package me.masahito.localdb.impl;

import com.codahale.metrics.MetricRegistry;
import com.google.inject.AbstractModule;
import me.masahito.localdb.LocalKVS;

public class LevelDBModule extends AbstractModule {
    protected final MetricRegistry metricRegistry;
    public LevelDBModule(final MetricRegistry metricRegistry) {
        this.metricRegistry = metricRegistry;
    }
    protected void configure() {
        //add configuration logic here
        bind(LocalKVS.class).to(LevelDBKVS.class);
        bind(MetricRegistry.class).toInstance(this.metricRegistry);
    }
}
