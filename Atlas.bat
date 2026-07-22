@echo off
cd /d "C:\Program Files"

start "" java -cp ".;C:\Program Files\atlas\libs\sqlite-jdbc-3.45.1.0.jar;C:\Program Files\atlas\libs\slf4j-api-2.0.9.jar;C:\Program Files\atlas\libs\slf4j-nop-2.0.18.jar;C:\Program Files\atlas\libs\jackson-annotations-2.22.jar;C:\Program Files\atlas\libs\jackson-core-2.22.1.jar;C:\Program Files\atlas\libs\jackson-databind-2.22.1.jar" atlas.Atlas