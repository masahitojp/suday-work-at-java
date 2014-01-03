package me.masahito.giuce;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.matcher.Matchers;


class GiuceMain {

    private Speaker speaker;

    @Inject
    public void setSpeaker(final Speaker speaker) {
        System.out.println("constructer injection");
        this.speaker = speaker;
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override protected void configure() {
                bind(Speaker.class).to(JapaneseSpeaker.class);
                bindInterceptor(Matchers.any(), Matchers.any(), new HelloInterceptor());
            }
        });

        GiuceMain main = injector.getInstance(GiuceMain.class);
        main.speaker.thankYou();
    }
}
