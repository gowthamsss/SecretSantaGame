**Secret Santa Assignment**

**Project Description**
This Java project automates the Secret Santa gift exchange assignment by reading employee details from an Excel file and assigning each employee a random recipient (avoiding assignments from last year). 
The program ensures that no one is assigned to themselves or their last year's recipient. The results are saved in a new Excel file for easy distribution.

**Features**
Parses employee data from an Excel file.
Reads last year’s Secret Santa assignments to avoid reassignments.
Randomly assigns Secret Santa participants, ensuring no one gets themselves or their last year’s recipient.
Writes the new assignments to an Excel file.
Provides meaningful error handling for cases where assignments cannot be made.

**Technologies Used**
Java: Core language used for developing the application.
Apache POI: Library used to read and write .xlsx (Excel) files.

**Prerequisites**
Make sure you have the following installed:

Java 8 or higher.
Apache Maven (for dependency management).


**Installation**
1. Clone the repository to your local machine:
git clone https://github.com/gowthamsss/SecretSantaGame.git
cd SecretSantaGame

2. Add the required dependencies to your pom.xml (or ensure Apache POI is properly included).
       <dependencies>
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml</artifactId>
            <version>5.2.3</version>
        </dependency>
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
            <version>5.2.3</version>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-collections4</artifactId>
            <version>4.4</version>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>2.18.0</version> <!-- Ensure this version matches your log4j-api version -->
        </dependency>
    </dependencies>
    
3. Compile and run the project

**Running the Project
To run the project, simply execute the Main.java class. The program will:**

Read employee data from Employee-List.xlsx.
Read last year's assignments from Last-Year-Santa-List.xlsx.
Randomly assign new Secret Santa pairings while avoiding duplicates from the previous year.
Save the new pairings to Secret-Santa-Game-Result.xlsx.

**Error Handling**

**File Not Found**: If the input Excel files are missing or cannot be read, an IOException is thrown.

**Invalid Assignments**: If the program is unable to find valid assignments due to constraints (such as too few participants or incompatible data), an IllegalStateException is thrown.

