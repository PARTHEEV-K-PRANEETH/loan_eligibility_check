# loan_eligibility_check
🚀 Loan Management & Eligibility System






A Java-based project that helps users check loan eligibility, calculate EMIs, and track loan payments using core programming concepts such as inheritance, exception handling, arrays, and file handling.

📘 Project Description

This project simulates a real-world loan processing flow:

Welcome Page

Loan Eligibility Form

→ If eligible → Login Page

→ If not eligible → Improvement Suggestions + Lower Loan Options

Loan & EMI Tracking Dashboard

It demonstrates good OOP design and separation of concerns using multiple classes.

🧩 Features

✔️ Loan eligibility checking

✔️ EMI calculation based on loan amount, interest rate & tenure

✔️ Login interface (dummy)

✔️ Loan tracking with payment history

✔️ Inheritance (Loan → PersonalLoan)

✔️ Uses final keyword for constants

✔️ Exception handling for invalid inputs

✔️ File I/O to save user loan details (optional if included)

✔️ Array usage for storing payments

📂 Project Structure
LoanSystem/
│
├── Main.java              // Starts the program
├── Loan.java              // Parent class
├── PersonalLoan.java      // Child class (Inheritance)
├── LoanManager.java       // Handles EMI + tracking logic
└── userData.txt           // File storing loan details (if enabled)

🛠️ Tech Stack
Component	Details
Language	Java (JDK 8+)
Concepts	OOP, Inheritance, Exception Handling, Arrays, File I/O
Input	Scanner (CLI-based program)
▶️ How to Run
javac Main.java
java Main


Make sure all .java files are in the same folder.

📌 OOP Concepts Used

Classes & Objects

Inheritance (e.g., PersonalLoan extends Loan)

Method Overriding

Encapsulation with private fields & getters/setters

final keyword for constants like LOAN_ID

Exception Handling with try–catch

Arrays for storing payment history

🖥️ Execution Workflow (Simplified)
Welcome Page
     ↓
Loan Eligibility Form
     ↓
Eligible? ── Yes → Login Page → EMI Tracker → Save/View Details
         └── No → Show Tips + Alternative Loan Options

📄 Sample Output Screenshot

(Add your execution screenshot here)

📘 Conclusion

This project successfully demonstrates how Java OOP principles can be applied to build a modular and functional loan system.
It can be extended with:

GUI (JavaFX / Swing)

Database (MySQL)

Multiple loan categories

Mobile app version
