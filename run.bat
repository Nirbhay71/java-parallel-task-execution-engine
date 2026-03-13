@echo off
echo Cleaning old build files...
if exist bin rd /s /q bin
mkdir bin

echo Compiling project...
javac -d bin -sourcepath src\main\java src\main\java\engine\demo\Main.java

if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b %errorlevel%
)

echo.
echo Running Parallel Task Execution Engine Demo...
echo ------------------------------------------
java -cp bin engine.demo.Main
pause
