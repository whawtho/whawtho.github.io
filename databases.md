<nav style="background-color:#f5f5f5; padding: 10px; border-bottom: 1px solid #ddd;">
    <a href="/" style="margin-right: 20px;">Home</a>
    <a href="code-review.html" style="margin-right: 20px;">Code Review</a>
    <a href="software-design" style="margin-right: 20px;">Software Design</a>
    <a href="algorithms" style="margin-right: 20px;">Algorithms & Data Structures</a>
    <!-- Right-aligned name -->
    <span style="float:right; font-weight:bold;">Zeb Hawthorne</span>
</nav>

# Databases

The artifact I selected for the databases category is my encryption project from CS-405, originally created in 2024. The program was built to read a text file, encrypt its contents with a basic XOR algorithm, and then write the encrypted and decrypted results back to new files. It worked, but the project had no form of persistent storage beyond file output. For this milestone, I added a SQLite database to store encryption logs, timestamps, keys used, and the encrypted output. This turns the program into something that treats data more like a real application instead of a simple file demo.

I chose this artifact for my ePortfolio because it gave me a simple foundation that I could expand into something more aligned with real software development. The original code only wrote encrypted content into output text files. For the enhancement, I added a structured storage system that works as a flat-file database. Instead of writing the processed data to an output file, the program now saves the student name, timestamp, key, encrypted content, and decrypted content into a CSV database file. This enhancement shows that I can take legacy code and introduce a new data-layer while preserving the rest of the program. It also shows that I understand basic database operations such as defining a schema, opening and appending records, and maintaining consistent formatting that can be read by other tools.

This enhancement also allowed me to contrast old behavior with new behavior. I commented out the original output-file logic and left it in the source code. This makes it clear how the program used to work and highlights the improvement. The CSV file now acts as a structured data store that groups all processed records together instead of scattering them into individual files. For this course outcome, I demonstrated the ability to store, manipulate, and maintain data using a simple database structure. This matches the database category by providing persistent organized storage and a cleaner workflow for any future data analysis.

My original plan in Module One was to add database functionality without switching to an external dependency like SQLite. That plan worked, and I stayed within the scope of the original artifact. I learned how to integrate a new feature without breaking the existing code. One of the challenges I faced was deciding how to store the encrypted and decrypted data. My first attempt at using SQLite required external libraries that did not compile correctly in the Visual Studio environment. Because of that, I had to adjust my plan and choose a flat-file database instead. This ended up being the most stable option and still met the requirements of the enhancement category. It also taught me that databases do not always require a heavy engine; the important part is creating structured, reliable storage that the program can use consistently.

Overall, I learned how to expand a small program with a realistic data-handling component and how to adapt when a technical path does not work out. This enhancement strengthened my skills in structured file design, persistent storage, and integrating a new feature into legacy code. It also prepared me for future work that involves handling stored data in industrial software or automation systems, which aligns well with my career path.


## Artifact Files

- [**Original file(s)**](https://github.com/whawtho/whawtho.github.io/tree/Databases/Mod5Encryption%20-%20Original)

- [**Enhanced file(s)**](https://github.com/whawtho/whawtho.github.io/tree/Databases/Mod5Encryption%20-%20Database%20Enhanced)
