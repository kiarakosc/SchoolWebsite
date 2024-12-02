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
SchoolWebsite/                    # Root directory of the project\
│\
├── src                           # Source code directory
│   └── main/java/org/example/    # Main Java package 
│       ├── LoginPage.java        # LoginPage.java, EshopPage.java, MainPage.java: 
│       ├── EshopPage.java          Page Object Model (POM) classes that represent different 
│       └── MainPage.java           pages of the website and their associated actions
│               
│               
│
├── Test/                         # Test directory
│   └── java/                     # Test code package
│       ├── LoginPageTest.java    # Test cases for Login page
│       ├── EshopPageTest.java    # Test cases for E-shop page
│       └── MainPageTest.java     # Test cases for Main page
│
├── resources/                    # Resources used in the project
│
├── pom.xml                       # Maven configuration file for dependencies
└── .gitignore                    # Git ignore file
```  

## How to Run the Tests
Clone this repository.

Set up the required dependencies using the pom.xml file.
* <!-- Playwright Dependency -->
    <dependency>
        <groupId>com.microsoft.playwright</groupId>
        <artifactId>playwright</artifactId>
        <version>1.41.0</version> <!-- Use the latest version -->
    </dependency>
    
*   <!-- JUnit Dependency -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.10.0</version> <!-- Use the latest version -->
        <scope>test</scope>
    </dependency>
Execute the tests using your preferred Java IDE(I used IntelliJ IDEA 2024.2.2).

:)

