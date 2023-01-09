package com.jeff_media.bungeecommandaliases;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.config.Configuration;

import java.util.Map;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Command {

    @Getter private final String command;
    @Getter private final CommandSenderType senderType;

    public enum CommandSenderType {
        CONSOLE, PLAYER;

        public static CommandSenderType fromString(String string) {
            return valueOf(string.toUpperCase());
        }
    }

    public static Command fromSection(Configuration map) {
        String command = map.getString("command");
        CommandSenderType senderType = CommandSenderType.fromString(map.getString("sender"));
        return new Command(command, senderType);
    }

}
