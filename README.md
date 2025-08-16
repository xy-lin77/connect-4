# Readme

City University of Hong Kong
CS3343 Software Engineering Practice
2023/24 Semester A
Project Title: Connect Four Game
Group 27


# Team Members

+------------------------------+----------+---------------------------+
| Name (in alphabetical order) | SID      | Role                      |
| Fong Tsz Wai                 | 57156110 | Program Developer         |
| JI Xin                       | 40147182 | Program Developer         |
| Ka Lam Mark Alexander LEE    | 56759365 | Program Tester            |
| LIN Xiaoyang                 | 57126676 | Program Manager           |
| SONG Rui                     | 57126559 | Assistant Program Manager |
+------------------------------+----------+---------------------------+


# Application Description

This is a Connect Four Game application.
It features Human vs. Human, and Human vs. AI (Easy / Hard) gameplay. 
Functions such as undo, seek advice, and game record review are supported.
Users can also customize their game preferences in the settings.


# Java Environment Configuration (Please make sure you have the latest JRE)

## For Windows:
    1. Download JDK from the official Oracle website: http://www.oracle.com/technetwork/java/javase/downloads/index.html
    2. Update the system path: https://confluence.atlassian.com/doc/setting-the-java_home-variable-in-windows-8895.html
    3. Test the setup: type 'java -version' in Command Prompt. If it returns the version of the JDK you installed, it's configured properly

## For Mac:
    1. Download JDK from the official Oracle website: http://www.oracle.com/technetwork/java/javase/downloads/index.html
    2. Set the environment variables:
        Open Terminal
        Type 'nano ~/.bash_profile'
        In the opened .bash_profile file, at the end of the file, add a new line that looks like this: export JAVA_HOME=$(/usr/libexec/java_home)
        Press 'Ctrl+X' to save and exit
    3. Test the setup: type 'java -version' in Terminal. If it returns the version of the JDK you installed, it's configured properly


# Run the application by ConnectFour.jar

    1. Open Command Prompt for Windows or Terminal for macOS
    2. Navigate to 'Release' directory, containing 'ConnectFour.jar' and 'system'
    3. (IMPORTANT) If you want to copy / move 'ConnectFour.jar' to the other directory, always put 'system' in the same directory as 'ConnectFour.jar'
    4. Execute 'java -jar ConnectFour.jar'


# Run the application in Eclipse

    1. Open Eclipse Java Project
    2. Copy files in 'Source/src' to 'src' in Eclipse Project
    3. Copy 'system' folder in 'Source' to Eclipse Project
    4. (IMPORTANT) Make sure the Package Explorer now has the following structure:
        Project
         |-JRE System Library
         |-src
          |-connectFourGame
          |-exception
          |-...
         |-JUnit 5
         |-system
          |-SysConfiguration.txt
    5. Right click the Project, select "Run As", select "Java Application", select 'Main - connectFourGame', press 'OK'



Please report any bugs or issues you encounter to our support team at xiaoyalin5-c@my.cityu.edu.hk  
