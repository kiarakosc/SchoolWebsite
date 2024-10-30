# Some Basic E2E Test for Testing University Website


This project contains a set of end-to-end (E2E) tests for the university website. 

## Tools and Technologies
* Playwright with Java
* Page Object Model (POM) for organizing page interactions.
  
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
