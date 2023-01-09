package com.jeff_media.bungeecommandaliases;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.config.Configuration;

import java.util.List;

@RequiredArgsConstructor
public class Alias {

    @Getter private final String name;
    @Getter private final List<Command> commands;
    @Getter private final List<String> messages;

    public static Alias fromMap(String name, Configuration config) {

    }

}
