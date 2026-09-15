@echo off
title Gerador de Documentos - SDS UFBA
chcp 65001 > nul
echo =======================================================
echo        Gerador de Documentos PDF - SDS UFBA
echo =======================================================
echo.
echo [1/2] Iniciando a aplicacao...
echo [2/2] Abrindo a interface web no navegador Chrome...
echo.

:: Abre o Chrome em http://localhost:8080 apos 3 segundos
start "" /b powershell -Command "Start-Sleep -Seconds 3; try { Start-Process chrome 'http://localhost:8080' -ErrorAction Stop } catch { try { Start-Process 'C:\Program Files\Google\Chrome\Application\chrome.exe' 'http://localhost:8080' -ErrorAction Stop } catch { try { Start-Process 'C:\Program Files (x86)\Google\Chrome\Application\chrome.exe' 'http://localhost:8080' -ErrorAction Stop } catch { Start-Process 'http://localhost:8080' } } }"

:: Usa o Java portatil da pasta jre (se existir) ou o Java instalado no sistema
if exist ".\jre\bin\java.exe" (
    ".\jre\bin\java.exe" -jar GeradorDocumentos.jar
) else (
    java -jar GeradorDocumentos.jar
)

pause
