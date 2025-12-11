 import java.io.*;
import java.util.*;

// --------------------------- Base User (Parent Class) ---------------------------
class BaseUser {
    protected String name;
    protected int age;

    public BaseUser(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
}
// --------------------------- Applicant Model (Child Class) ---------------------------
class Applicant extends BaseUser {

    private double monthlyIncome;
    private int creditScore;

    public Applicant(String name, int age, double income, int creditScore) {
        super(name, age); // calling parent constructor
        this.monthlyIncome = income;
        this.creditScore = creditScore;
    }

    public double getIncome() { return monthlyIncome; }
    public int getCreditScore() { return creditScore; }
}

// --------------------------- Custom Exceptions ---------------------------
class InvalidInputException extends Exception {
    public InvalidInputException(String msg) {
        super(msg);
    }
}

// --------------------------- Loan Eligibility Service ---------------------------
class LoanEligibilityService {

    public boolean isEligible(Applicant a) {
        return a.getAge() >= 21 &&
               a.getIncome() >= 25000 &&
               a.getCreditScore() >= 650;
    }

    public void showImprovements(Applicant a) {
        System.out.println("\n❌ You are NOT eligible right now.");
        System.out.println("\nAreas to Improve:");

        if (a.getAge() < 21)
            System.out.println(" - Age must be at least 21");

        if (a.getIncome() < 25000)
            System.out.println(" - Monthly income should be ≥ ₹25,000");

        if (a.getCreditScore() < 650)
            System.out.println(" - Improve your credit score to ≥ 650");
// ---------------- ARRAY + SORTING ADDED HERE ----------------
    System.out.println("\n💰 Loan Options Available (Sorted by Interest Rate):");

    // Array of loan interest rates
    double[] interestRates = {10.5, 12.75, 9.0, 14.25, 11.0};

    // Sort using built-in array sort
    Arrays.sort(interestRates);

    for (double rate : interestRates) {
        System.out.println(" - Loan with interest rate: " + rate + "%");
    }

                   System.out.println();
        System.out.println("\n💰 Affordable Loan Options You Can Try:");
        System.out.println(" - Gold Loan");
        System.out.println(" - Small Personal Loan (up to ₹50,000)");
        System.out.println(" - Co-applicant loan\n");
    }
}

// --------------------------- Login Service ---------------------------
class LoginService {
    private final String LOGIN_FILE = "login_users.txt";

    public void register(String user, String pass) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(LOGIN_FILE, true));
        bw.write(user + "," + pass);
        bw.newLine();
        bw.close();
    }

    public boolean login(String user, String pass) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(LOGIN_FILE));
        String line = "";

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if (data[0].equals(user) && data[1].equals(pass)) {
                br.close();
                return true;
            }
        }

        br.close();
        return false;
    }
}

// --------------------------- Loan EMI Tracker ---------------------------
class LoanTracker {

    private double loanAmount;
    private double interestRate;
    private int tenureMonths;
    private double emi;
    private double remainingAmount;
    private final String DATA_FILE = "loan_tracker.txt";

    public void enterLoanDetails(Scanner sc) {
        try {
            System.out.print("Enter Loan Amount: ₹");
            loanAmount = sc.nextDouble();
            if (loanAmount <= 0) throw new InvalidInputException("Loan amount must be positive!");

            System.out.print("Enter Annual Interest Rate (%): ");
            interestRate = sc.nextDouble();
            if (interestRate <= 0) throw new InvalidInputException("Interest rate must be positive!");

            System.out.print("Enter Tenure in Months: ");
            tenureMonths = sc.nextInt();
            if (tenureMonths <= 0) throw new InvalidInputException("Tenure must be positive!");

            calculateEMI();
            remainingAmount = loanAmount * (1 + (interestRate / 100));

            saveLoanDetails();

            System.out.println("\n📌 Your EMI is: ₹" + String.format("%.2f", emi));
            System.out.println("📌 Total Payable Amount: ₹" + String.format("%.2f", remainingAmount));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void calculateEMI() {
        double monthlyRate = interestRate / (12 * 100);
        emi = (loanAmount * monthlyRate * Math.pow(1 + monthlyRate, tenureMonths)) /
               (Math.pow(1 + monthlyRate, tenureMonths) - 1);
    }

    public void makePayment(Scanner sc) {
        try {
            System.out.print("\nEnter EMI Amount to Pay: ₹");
            double payment = sc.nextDouble();

            if (payment <= 0) throw new InvalidInputException("Payment must be positive!");
            if (payment > remainingAmount) throw new InvalidInputException("Payment exceeds remaining loan!");

            remainingAmount -= payment;
            saveLoanDetails();

            System.out.println("\nPayment successful!");
            System.out.println("Remaining Loan Amount: ₹" + String.format("%.2f", remainingAmount));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void saveLoanDetails() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE));
        bw.write("Remaining Loan Amount: " + remainingAmount);
        bw.newLine();
        bw.close();
    }
}


// --------------------------- User Interface Pages ---------------------------
class Pages {

    Scanner sc = new Scanner(System.in);
    LoanEligibilityService eligibilityService = new LoanEligibilityService();
    LoginService loginService = new LoginService();
    LoanTracker loanTracker = new LoanTracker();


    // ---------------- Welcome Page ----------------
    public void welcomePage() {
        System.out.println("\n==============================");
        System.out.println("       WELCOME TO LOAN APP    ");
        System.out.println("==============================");
        System.out.println("1. Check Loan Eligibility");
        System.out.println("2. Login to Track Loan");
        System.out.print("Enter choice: ");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1 -> loanEligibilityPage();
            case 2 -> loginPage();
            default -> System.out.println("Invalid Choice!");
        }
    }


    // ---------------- Loan Eligibility Page (Page 1) ----------------
    public void loanEligibilityPage() {

        try {
            System.out.print("\nEnter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            if (age <= 0) throw new InvalidInputException("Age must be positive!");

            System.out.print("Enter Monthly Income (₹): ");
            double income = sc.nextDouble();
            if (income <= 0) throw new InvalidInputException("Income must be positive!");

            System.out.print("Enter Credit Score (300-900): ");
            int credit = sc.nextInt();
            if (credit < 300 || credit > 900)
                throw new InvalidInputException("Credit score must be 300–900!");

            Applicant a = new Applicant(name, age, income, credit);

            if (eligibilityService.isEligible(a)) {
                System.out.println("\n🎉 You are Eligible!");
                System.out.println("Redirecting to Login Page...\n");
                loginPage();
            } else {
                eligibilityService.showImprovements(a);
            }

        } catch (InvalidInputException e) {
            System.out.println("Input Error: " + e.getMessage());
        }
    }


    // ---------------- Login Page (Page 2) ----------------
    public void loginPage() {
        try {
            System.out.println("\n=========== LOGIN PAGE IF YOU LIKE TO TRACK YOUR LOAN ===========");
            System.out.println("1. Register");
            System.out.println("2. Login");

            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            if (ch == 1) {
                System.out.print("Create Username: ");
                String user = sc.nextLine();
                System.out.print("Create Password: ");
                String pass = sc.nextLine();

                loginService.register(user, pass);
                System.out.println("Registration successful!\n");
            }

            System.out.print("Enter Username: ");
            String u = sc.nextLine();
            System.out.print("Enter Password: ");
            String p = sc.nextLine();

            boolean success = loginService.login(u, p);

            if (success) {
                System.out.println("\n✅ Login Successful!");
                loanTrackerPage();
            } else {
                System.out.println("\n❌ Invalid Credentials!");
            }

        } catch (IOException e) {
            System.out.println("File Error: " + e.getMessage());
        }
    }


    // ---------------- Loan Tracker Page ----------------
    public void loanTrackerPage() {

        while (true) {
            System.out.println("\n========= LOAN & EMI TRACKER =========");
            System.out.println("1. Enter Loan Details");
            
            System.out.println("2. Exit");

            System.out.print("Choose: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> loanTracker.enterLoanDetails(sc);
                case 2 -> {
                    System.out.println("\nThank you for using Loan App!");
                    return;
                }
                default -> System.out.println("Invalid Choice!");
            }
        }
    }
}


// --------------------------- MAIN CLASS ---------------------------
public class LoanSystemMain {
    public static void main(String[] args) {
        Pages p = new Pages();
        p.welcomePage();
}
}
