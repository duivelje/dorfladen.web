package com.steffi.dorfladen.web;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.steffi.dorfladen.web.monitoring.MonitoringFrame;

@SpringBootApplication
public class DorfladenBackend {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(DorfladenBackend.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);

        var gui = context.getBean(MonitoringFrame.class);
        gui.setVisible(true);
    }
}
