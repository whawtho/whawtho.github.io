<nav style="background-color:#f5f5f5; padding: 10px; border-bottom: 1px solid #ddd;">
    <a href="/" style="margin-right: 20px;">Home</a>
    <a href="code-review.html" style="margin-right: 20px;">Code Review</a>
    <a href="algorithms" style="margin-right: 20px;">Algorithms & Data Structures</a>
    <a href="databases" style="margin-right: 20px;">Databases</a>

    <!-- Right-aligned name -->
    <span style="float:right; font-weight:bold;">Zeb Hawthorne</span>
</nav>

# Algorithms and Data Structures

The artifact I chose for the algorithms and data structures category is my Module 5 encryption.cpp program originally created in CS-405. The original version of this file used a simple XOR-based encryption function along with some basic input and output logic. It read a text file, applied the XOR transform with a key, and saved the encrypted and decrypted results. The original code worked, but the algorithm did not perform any input validation, and it did not protect itself from invalid states or missing data. This made it a good candidate for improvement in this course.

I selected this artifact because it demonstrates my ability to understand and work with core algorithmic concepts. The heart of the program is an encryption routine that processes data in a loop and applies a transformation to each character. The file also uses common data structures such as strings, streams, buffers, and character arrays. These are skills that show my ability to work with algorithm design, data handling, and input validation.

The artifact was also useful because there was clear room for improvement. The original code had almost no safety checks and it assumed all inputs were valid. There was no key validation, no runtime protections, no fallback logic, and no separation between the algorithm and the file-handling code. Improving those areas aligns directly with the expectations for this milestone.

The main enhancement for this milestone involved strengthening the encryption function and the dataflow around it. I kept the original XOR algorithm for continuity because the assignment is about enhancing the artifact rather than replacing it with something completely different. Instead of switching algorithms, I focused on making the existing logic more reliable, safer, and easier to maintain.

Below are the key differences between the original artifact and the enhanced version:

**Original version weaknesses:**

•	The XOR function assumed the key was always valid

•	No error handling existed around the algorithm

•	An empty or missing input file caused undefined behavior

•	The encryption loop wrote directly into the source buffer

•	Output building did not use any explicit memory handling

•	The algorithm, file reading, and error handling were all mixed together

**Enhanced version improvements:**

•	Added validation so the key must be non-empty

•	Added protection against reading an empty or missing input file

•	Rewrote the output-building logic to use reserve() and push_back() which is safer and more intentional

•	Added structured try/catch blocks so encryption errors do not crash the program

•	Introduced clearer separation between the algorithm and the file I/O workflow

•	Improved the handling of fallback scenarios when the file is not found

•	Added more explicit checks that data was successfully loaded before running the algorithm

These changes strengthened the entire program. The encryption algorithm now validates its inputs, handles error conditions, and produces predictable behavior even under unusual scenarios. The changes also show improvement in the algorithmic logic without changing the core purpose of the artifact.

For this milestone I planned to address the course outcome related to algorithms and data structures. The enhancements meet that outcome because they required me to analyze the original algorithm, identify weaknesses, and modify the internal data-handling logic to be more robust. I also applied structured exception management, better buffer handling, and more deliberate use of standard library features. These improvements all relate directly to algorithmic practices and design considerations.

Enhancing this artifact helped me sharpen my understanding of how algorithms behave when they receive bad input or operate under unexpected conditions. The original code performed the XOR transformation correctly, but it was fragile. Improving it taught me how easy it is for an algorithm to break when assumptions are not validated. I also learned more about safely building strings, managing buffer sizes, and structuring logic so that each part of the program has one clear responsibility.

One of the challenges I faced was deciding how far to go with the enhancements without replacing the original algorithm entirely. The assignment expects enhancements, not a complete rewrite. This forced me to stay focused on improving the existing logic instead of building something totally new. Making the algorithm more reliable, safer, and easier to reason about gave me a much stronger understanding of practical algorithm improvement and data handling.


## Artifact Files

- [**Original file(s)**](https://github.com/whawtho/whawtho.github.io/tree/Algorithms-and-Data-Structure/Mod5Encryption%20-%20Original)

- [**Enhanced file(s)**](https://github.com/whawtho/whawtho.github.io/tree/Algorithms-and-Data-Structure/Mod5_encryption%20-%20Enhanced)
