package com.jeff_media.bungeecommandaliases;

import lombok.*;
import net.md_5.bungee.config.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Alias {

    @Getter private final String name;
    @Getter private final List<Command> commands;
    @Getter private final List<String> messages;

    public static Alias fromMap(String name, Configuration config) {
        List<Command> commands = new ArrayList<>();
        List<Map<String, Object>> list = (List<Map<String, Object>>) config.getList("commands");
        for(Map<String, Object> map : list) {
            Command command = Command.fromSection(map);
            commands.add(command);
        }
        List<String> messages = config.getStringList("messages");
        return new Alias(name, commands, messages);
    }

}
