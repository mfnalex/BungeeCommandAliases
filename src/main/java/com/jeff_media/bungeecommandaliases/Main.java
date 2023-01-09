package com.jeff_media.bungeecommandaliases;

import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main extends Plugin {

    private static ConfigurationProvider yamlConfigurationProvider = ConfigurationProvider.getProvider(YamlConfiguration.class);
    @Getter private Configuration commandsConfig;

    @Getter private List<Alias> aliases;

    @Override
    public void onEnable() {
        createDataFolder();
        commandsConfig = loadConfig("commands.yml");
        loadAliases();
    }

    private void loadAliases() {
        aliases = commandsConfig.getKeys().stream().map(key -> Alias.fromMap(key, commandsConfig.getSection(key))).collect(Collectors.toList());
    }

    private void createDataFolder() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
    }

    private Configuration saveIncludedConfig(File file, String fileName) {
        Configuration defaultConfig = yamlConfigurationProvider.load(getResourceAsStream(fileName));
        if(!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                yamlConfigurationProvider.save(defaultConfig, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return defaultConfig;
    }

    public Configuration loadConfig(String fileName) {
        File file = new File(getDataFolder(), fileName);
        Configuration defaultConfig = saveIncludedConfig(file, fileName);
        try {
            Configuration config = yamlConfigurationProvider.load(file);
            for(String key : defaultConfig.getKeys()) {
                if(config.contains(key)) {
                    System.out.println("Loaded value: " + fileName + ", " + key + ": " + config.get(key));
                    continue;
                }
                System.out.println(fileName + " doesn't have any value for " + key + ", using default value " + defaultConfig.get(key));
                config.set(key, defaultConfig.get(key));
            }
            return config;
        } catch (IOException e) {
            e.printStackTrace();
            return defaultConfig;
        }
    }
}
