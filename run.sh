#!/usr/bin/env bash

[ -f $HOME/.bash_aliases ] && . ~/.bashrc

rm -rf testserver
mkdir testserver
mkdir testserver/plugins
cp build/libs/testserver-*.jar testserver/plugins
wget -O testserver/bungeecord.jar https://ci.md-5.net/job/BungeeCord/lastSuccessfulBuild/artifact/bootstrap/target/BungeeCord.jar

cd testserver
java -jar bungeecord.jar