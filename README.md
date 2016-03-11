# Stronghold
Stronghold is a code base using a command based architecture. This branch is a collaborative effort between a certain group of people to rewrite the rest of this year's code base.

## Innovation
Ever since our initial attempt at command based code last year, we asked ourselves "What can we do to advance this code? What can we do to further our knowledge?" Since then, we've come up with many revolutionary ideas, including:

#### Bullboard™
Bullboard™ is a brand new web dashboard that promises to make testing efficient, and competitions easier. It is accessed at port 5800.
#### Travis CI
Travis CI can improve the overall quality of the code pushed to the main repository by building and checking code, ensuring there is no buggy code in the main repository.
#### Gradle
By hitting the sweet spot between Ant and Maven, Gradle promises to be able to run complicated build scripts, all while maintaining portability and simplicity (to an extent). By using Gradle, we're able to build much more complex code bases compared to what we could do with Ant.

## Instructions and Reminders

#### Project Usage
Download IntelliJ IDEA; please don't use Eclipse. 

#### Git
To get changes from the remote, use rebase; never merge. It makes for ugly commit chains. In addition to that, never use the git integrated into the IDE; use the terminal.

#### Checking Code
Before relying on travis to check your code, you can perform the same commands yourself to save time!
```
stronghold $ ./gradlew check //unix check for styling errors
stronghold $ ./gradlew assemble //unix see if compiles

stronghold $ gradlew check //windows check
stronghold $ gradlew assemble //windows compile
```
