### How to run from the Windows command line

1. Download and install the latest version of the  Java Platform, Standard Edition Development Kit .
The installation directory something like  C:\Program Files\Java\jdk1.XX_XX\bin.

2. To make sure that Windows can find the Java compiler and interpreter:
Select Start -> Computer -> System Properties -> Advanced system settings -> Environment Variables -> System variables -> PATH.
Prepend C:\Program Files\Java\ jdk1.XX_XX \bin; to the beginning of the PATH variable.

3. Launch the command prompt via All Programs -> Accessories -> Command Prompt. (Or press Windows+R, then open cmd)
To check that you have the right version of Java installed, type the text below. You should see something similar to the information printed below.

C:\Users\username>java -version

java version "1.8.0_171"
Java(TM) SE Runtime Environment (build 1.8.0_171-b11)
Java HotSpot(TM) 64-Bit Server VM (build 25.171-b11, mixed mode)

4. Then type:

C:\Users\username>javac -version
javac 1.8.0_171

5. From the Command Prompt, navigate to the directory containing your .java files, for example C:\BankAccount, 
by typing the cd command below:

C:\Users\username>cd C:\ BankAccount

6. Compile and Run 

C:\ BankAccount >javac BankAccount.java
C:\ BankAccount> java BankAccount

### Methods and use of packages: 

For this problem I utilized the java.io package which is needed to perform input and output in Java. 
The java.math.BigDecimal package was used to handle rounding and format conversion. That package is 
also beneficial for error-checking so that the program only accepts positive floats with 1 or two 
decimal places. Lastly, the java.until.Scanner class was used to obtain input data from the user.
