@echo off
md bin
javac -d bin -sourcepath src src\com\arwichok\Main.java
echo Compiled