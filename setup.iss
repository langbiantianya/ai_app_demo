#define MyAppName "ai_demo"

[Setup]
AppVersion=1.0
DefaultDirName={commonpf}\ai_demo
AppName={#MyAppName}
DefaultGroupName={#MyAppName}
OutputDir=./build/windows
OutputBaseFilename=ai_demo-Setup
DiskSpanning=yes
Compression=lzma2
SolidCompression=yes

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"
Name: "chinesesimplified"; MessagesFile: "compiler:Languages\ChineseSimplified.isl"

[Tasks]
; 添加桌面图标选项
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked


[Files]
Source: "build\libs\*.exe"; DestDir: "{app}"
Source: "build\libs\lib\*"; DestDir: "{app}\lib"
Source: "build\libs\*.jar"; DestDir: "{app}"
Source: "build\libs\ollama\*"; DestDir: "{app}\ollama";Flags: recursesubdirs
Source: "build\libs\models\*"; DestDir: "{app}\models";Flags: recursesubdirs
Source: "build\libs\jre\*"; DestDir: "{app}\jre";Flags: recursesubdirs


[Icons]
; 修改图标配置
Name: "{group}\ai_demo"; Filename: "{app}\ai_demo.exe"
Name: "{group}\{cm:UninstallProgram,ai_demo}"; Filename: "{uninstallexe}"
; 可选桌面图标
Name: "{autodesktop}\ai_demo"; Filename: "{app}\ai_demo.exe"; Tasks: desktopicon