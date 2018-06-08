@echo on

@echo ======= start =======

echo %cd%

rd /s /q  %cd%\karaf\target\assembly\data\cache\org.eclipse.osgi\

xcopy /y /c /h /r %cd%\api\target\demo-api-0.1.0-SNAPSHOT.jar %cd%\karaf\target\assembly\system\com\catt\carbon\demo-api\0.1.0-SNAPSHOT\demo-api-0.1.0-SNAPSHOT.jar

xcopy /y /c /h /r %cd%\impl\target\demo-impl-0.1.0-SNAPSHOT.jar  %cd%\karaf\target\assembly\system\com\catt\carbon\demo-impl\0.1.0-SNAPSHOT\demo-impl-0.1.0-SNAPSHOT.jar

::xcopy /y /c /h /r %cd%\impl\conf\*.*  %cd%\karaf\target\assembly\conf\

::xcopy /y /c /h /r %cd%\lib\deploy\*.*  %cd%\karaf\target\assembly\deploy\

::debug
%cd%\karaf\target\assembly\bin\karaf.bat debug