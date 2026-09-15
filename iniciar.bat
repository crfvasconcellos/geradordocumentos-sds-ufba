@echo off
title Gerador de Documentos - SDS UFBA
chcp 65001 > nul
echo =======================================================
echo        Gerador de Documentos PDF - SDS UFBA
echo =======================================================
echo.
echo [1/2] Iniciando a aplicação...
echo [2/2] Abrindo a interface web no navegador...
echo.

:: Abre o navegador padrão em http://localhost:8080 após 3 segundos
start "" /b powershell -Command "Start-Sleep -Seconds 3; Start-Process 'http://localhost:8080'"

:: Usa o Java portátil da pasta jre (se existir) ou o Java instalado no sistema
if exist ".\jre\bin\java.exe" (
    ".\jre\bin\java.exe" -jar GeradorDocumentos.jar
) else (
    java -jar GeradorDocumentos.jar
)

pause
