Eclipse Collections Kata 
===================
A [kata](https://en.wikipedia.org/wiki/Kata) is an exercise in martial arts. 
A [code kata](http://codekata.com/) is an exercise in programming which helps hone your skills through practice and repetition. 
The Eclipse Collections Kata is a fun way to help you learn idiomatic Eclipse Collections usage. 
This particular kata is set up as a series of unit tests which fail. 
Your task is to make them pass, using Eclipse Collections.

Initialize Kata
---------------
Clone this repo or simply download and extract the master [zip file](https://github.com/eclipse/eclipse-collections-kata/archive/master.zip), 
then follow the instructions below for your IDE/platform. 


### Eclipse users

Initialize Eclipse project with the command below. 

##### Mac/Linux/Unix:
```
./gradlew eclipse
```

##### Windows:
```
gradlew.bat eclipse
```
Once the project is initialized, 
you can import the project from "Import" => "General" => "Existing Projects into Workspace". 
Select "eclipse-collections-kata" directory as root directory, 
make sure to choose "Search for nested projects" option and finish.
### IntelliJ IDEA users

Initialize IntelliJ IDEA project with the command below. 

##### Mac/Linux/Unix:
```
./gradlew idea
```

##### Windows:
```
gradlew.bat idea
```

Once the project is initialized, 
you can open the project from "File" => "Open..." => choose "eclipse-collections-kata" folder. 
You might see a dialog to suggest importing Gradle project, it's up to you to do so if you'd like to invoke gradle builds from IDEA.
Kata can run in IDEA even if you don't import Gradle project.

### NetBeans users
We haven't come up with an instruction for NetBeans yet. 
We welcome contributions from NetBeans users to add guidance here!


Work on Kata exercises
----------------------
There are two separate katas under different directories (Company Kata and Pet Kata).

```
company-kata/src/tests
pet-kata/src/tests
```

You can refer to slides for the [Company Kata](http://www.goldmansachs.com/gs-collections/documents/GS%20Collections%20Training%20Session%20and%20Kata%205.0.0.pdf) and the [Pet Kata](https://www.eclipsecon.org/na2016/sites/default/files/slides/Eclipse_Collections_Mini_Kata.pdf) to learn the concepts of each exercise. 

Check out the [solution branch](https://github.com/eclipse/eclipse-collections-kata/tree/solutions) for your reference.

Enjoy happy learning with Eclipse Collections Kata!
