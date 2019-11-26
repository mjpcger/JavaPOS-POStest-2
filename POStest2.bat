@echo off

setlocal

REM ***************************************
REM *  Batch file for Java 11             *
REM *  JAVA_HOME must be set correctly    *
REM ***************************************

REM ***************************************
REM *  Find JavaFX SDK                    *
REM ***************************************

if not x%JFX_HOME%==x goto :jfxPresent

REM ***************************************
REM *  JFX_HOME not set: Search folder    *
REM *  starting with javafx-sdk- in       *
REM *  current path                       *
REM ***************************************

pushd .
for %%i in (%cd%) do set drv=%%~di\
:loop
if exist javafx-sdk-* for /d %%i in (javafx-sdk-*) do set JFX_HOME=%cd%\%%i& popd & goto :jfxPresent
if not %drv%==%cd% cd .. & goto :loop

REM ***************************************
REM *  Now search in some other places    *
REM ***************************************

if exist "%USERPROFILE%\javafx-sdk-*" for /d %%j in ("%USERPROFILE%\javafx-sdk-*") do set JFX_HOME=%%j& popd & goto :jfxPresent
if exist "%ALLUSERSPROFILE%\javafx-sdk-*" for /d %%j in ("%ALLUSERSPROFILE%\javafx-sdk-*") do set JFX_HOME=%%j& popd & goto :jfxPresent
if exist "%ProgramFiles%\javafx-sdk-*" for /d %%j in ("%ProgramFiles%\javafx-sdk-*") do set JFX_HOME=%%j& popd & goto :jfxPresent
if exist "%ProgramFiles(x86)%\javafx-sdk-*" for /d %%j in ("%ProgramFiles(x86)%\javafx-sdk-*") do set JFX_HOME=%%j& popd & goto :jfxPresent
if exist "%CommonProgramFiles%\javafx-sdk-*" for /d %%j in ("%CommonProgramFiles%\javafx-sdk-*") do set JFX_HOME=%%j& popd & goto :jfxPresent
if exist "%CommonProgramFiles(x86)%\javafx-sdk-*" for /d %%j in ("%CommonProgramFiles(x86)%\javafx-sdk-*") do set JFX_HOME=%%j& popd & goto :jfxPresent

echo JavaFX not found
popd
goto :EOF

:jfxPresent

set PATH=%PATH%;%JFX_HOME%\bin

REM ***************************************
REM *  Add postest2 modules to classpath  *
REM ***************************************

set classpath=postest2.jar

REM ***************************************
REM *  Add Device Specific jar's here...  *
REM ***************************************

rem set classpath=%classpath%;....

REM ***************************************
REM *  Add Device Specific binary folders *
REM *  here                               *
REM ***************************************

rem set PATH=%PATH%;....

REM ***************************************
REM *  Start POSTest2                     *
REM ***************************************

echo java --module-path=%JFX_HOME%\lib --add-modules=javafx.controls,javafx.fxml -cp %classpath% postest2.POSTest2
java --module-path=%JFX_HOME%\lib --add-modules=javafx.controls,javafx.fxml -cp %classpath% postest2.POSTest2

