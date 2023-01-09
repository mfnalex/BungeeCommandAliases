package com.jeff_media.bungeecommandaliases;

import lombok.*;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.config.Configuration;

import java.util.Map;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Command {

    @Getter private final String command;
    @Getter private final CommandSenderType senderType;

    public enum CommandSenderType {
        CONSOLE, PLAYER;

        public static CommandSenderType fromString(String string) {
            return valueOf(string.toUpperCase());
        }
    }

    public static Command fromSection(Map<String,Object> map) {
        String command = (String) map.get("command");
        CommandSenderType senderType = CommandSenderType.fromString((String) map.get("runas"));
        return new Command(command, senderType);
    }



}
