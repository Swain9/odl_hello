@echo on

@echo ======= start =======

echo %cd%

rd /s /q  %cd%\karaf\target\assembly\data\cache\org.eclipse.osgi\

xcopy /y /c /h /r %cd%\api\target\demo-api-0.1.0-SNAPSHOT.jar %cd%\karaf\target\assembly\system\com\catt\carbon\demo-api\0.1.0-SNAPSHOT\demo-api-0.1.0-SNAPSHOT.jar

xcopy /y /c /h /r %cd%\impl\target\demo-impl-0.1.0-SNAPSHOT.jar  %cd%\karaf\target\assembly\system\com\catt\carbon\demo-impl\0.1.0-SNAPSHOT\demo-impl-0.1.0-SNAPSHOT.jar

::xcopy /y /c /h /r %cd%\impl\conf\*.*  %cd%\karaf\target\assembly\conf\

::xcopy /y /c /h /r %cd%\lib\deploy\*.*  %cd%\karaf\target\assembly\deploy\

::如果在yang文件中，定义的不同的结构和类型，需要重新编译整个项目？否则启动karaf的时候会出现包缺少的异常

::debug
%cd%\karaf\target\assembly\bin\karaf.bat debug