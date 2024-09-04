package com.ecloud.scratchgame.service;

import com.ecloud.scratchgame.model.Config;
import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.FileReader;

@Service
public class ConfigService {
    private Config config;

    @PostConstruct
    public void init() throws Exception {
        Gson gson = new Gson();
        FileReader reader = new FileReader("src/main/resources/config.json");
        this.config = gson.fromJson(reader, Config.class);
    }

    public Config getConfig() {
        return config;
    }
}
