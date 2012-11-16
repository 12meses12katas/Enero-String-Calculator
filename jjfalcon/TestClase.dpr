{-------------------------------------------------------------------------------
<Class>_Test

DESCRIPCION

Proyecto que centraliza la ejecucion de tests de PLAYER:
* Test Funcionales o de Aceptacion del Sistema (casos de usuario)
* Test Unitarios para cada modulo o clase

USO

Para usar el sistema basta lanzar el ejecutable de los siguientes modos:
* Modo UI que permite controlar dunit
* Modo Consola que permite automatizar test en integracion continua (incluir -h)

DESARROLLO

Plataforma  : Windows XP SP2 y Delphi 7 Enterprise

Historial   :
  - version 1.0 (01/07/2009)

TODO        : <tareas pendientes de realizar>
  - ...

 Copyright 1996-2009 SOME Sistemas Informáticos, S.L.
-------------------------------------------------------------------------------}

{$APPTYPE CONSOLE}

program TestClase;

uses
  Forms,
  sysutils,
  TextTestRunner,
  GUITestRunner,
  TestFramework,
  UStringCalculator_Test in 'UStringCalculator_Test.pas',
  UStringCalculator in 'UStringCalculator.pas';

{$R *.res}

begin
  if FindCmdLineSwitch('h', ['-', '/'], true) then
    TextTestRunner.RunRegisteredTests(rxbHaltOnFailures)
  else
  begin
    Application.Initialize;
    GUITestRunner.RunRegisteredTests;
  end;
end.

