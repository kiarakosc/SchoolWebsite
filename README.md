# Some Basic E2E Test for Testing University Website


This project contains a set of end-to-end (E2E) tests for the university website. 

## Tools and Technologies
* Playwright with Java
* JUnit for testing
* Page Object Model (POM) for organizing page interactions

I chose IntelliJ IDEA for running code.

## Setting Up IntelliJ IDEA and Running the Code (Maven Project)

### 1. Download and Install IntelliJ IDEA
Visit the official JetBrains website: https://www.jetbrains.com/idea/. <br />
Choose the version: <br />
 * Community Edition (Free): Suitable for most Java and Maven projects.<br />
 * Ultimate Edition (Paid): Includes additional features for web development and enterprise frameworks.<br />
 
Download the installer for your operating system (Windows, macOS, or Linux).<br />
Run the installer and follow the on-screen instructions to complete the installation.<br />
    
### 2. Clone the Project from GitHub
Open IntelliJ IDEA and navigate to File > New > Project from Version Control.<br />
Select Git.<br />
Enter GitHub repository URL (https://github.com/kiarakosc/SchoolWebsite.git) and choose a destination folder.<br />
Click Clone. IntelliJ will download the project files to your local machine.<br />
    
### 3. Open the Maven Project in IntelliJ IDEA
After cloning, IntelliJ will automatically open the project.<br />
Verify that IntelliJ recognizes the pom.xml file. If not:<br />
 *  Navigate to the pom.xml file in the project view.<br />
 *  Right-click on it and select Add as Maven Project.<br />
   
### 4. Run the Code
Locate the main class or test class you wish to run in the Project view.<br />
Right-click the class file and select Run 'ClassName'.<br />
Alternatively, set up a new run configuration via the Run menu if required.<br />
    

  
## Project Structure
Page Classes:
```
SchoolWebsite/                # Root directory of the project
├── .idea/                    
├── src/                      # Source code directory
│   ├── main/java/org/example/    
│   │   ├── EshopPage.java        # LoginPage.java, EshopPage.java, MainPage.java:
│   │   ├── LoginPage.java          Page Object Model (POM) classes that represent different
│   │   ├── MainPage.java           pages of the website and their associated actions
│   ├── resources/            # Resources used in the project
│   ├── Test/                 # UI Test directory
│   │   ├── java/           
│   │       ├── EshopPageTest.java  # Test cases for E-shop page
│   │       ├── LoginPageTest.java  # Test cases for Login page
│   │       ├── MainPageTest.java   # Test cases for Main page
│   ├── APITest/              # API Test directory
│       ├── java/             
│           ├── Test01.java       # API testing class
├── target/                   # Compiled classes and resource
├── .gitignore                # Git ignore file
├── pom.xml                   # Maven configuration file for dependencies

```  

## Required dependencies
Add these dependencies to your pom.xml file:

    <dependencies>
        <!-- Playwright Dependency -->
        <dependency>
            <groupId>com.microsoft.playwright</groupId>
            <artifactId>playwright</artifactId>
            <version>1.47.0</version>
        </dependency>

        <!-- JUnit Jupiter Dependencies -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.9.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.9.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-params</artifactId>
            <version>5.9.0</version>
            <scope>test</scope>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.24</version>
            <optional>true</optional>
        </dependency>

        <!-- Swagger Annotations -->
        <dependency>
            <groupId>io.swagger</groupId>
            <artifactId>swagger-annotations</artifactId>
            <version>1.5.0</version>
        </dependency>
    </dependencies>


