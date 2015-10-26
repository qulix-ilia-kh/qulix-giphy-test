# qulix-giphy-test
Test application of interaction with Giphy

Comments:

1. As there is no requirements regarding order in which images must be
loadded, this decision was left to the fresco library.

2. As there is no requirements regarding 'load more' or paging
functionality in search, we load only one page of 50 results in search
list.

---
Setting Up

To build the application the following environment is used:
- jdk 1.7
- jdk 1.8
- gradle-2.4
- android-sdk
- android studio 1.4

Also the following environment variables have to be specified:
- JAVA7_HOME
- JAVA8_HOME

For example
~/.gradle/gradle.properties:
JAVA7_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_75.jdk/Contents/Home
JAVA8_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_66.jdk/Contents/Home

---
Running

1. Launch android studio
2. Import project from sources
3. sync project with gradle files
4. enable USB debugging on device and conenct it to computer
5. run the application build.
6. select target device on device chooser
