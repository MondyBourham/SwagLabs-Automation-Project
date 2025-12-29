# SwagLabs E-Commerce Automation Project

A comprehensive, production-ready automated testing suite for the [SwagLabs (SauceDemo)](https://www.saucedemo.com/) e-commerce platform. This project demonstrates professional test automation practices with a focus on scalability, maintainability, and robust reporting.

---

## 🎯 Project Overview

This automation framework was developed as a practical training exercise to master advanced test automation concepts and industry best practices. The project covers the complete end-to-end user journey on the SwagLabs platform, from authentication through order completion, ensuring critical business functionality remains intact through continuous regression testing.

**Key Achievement:** Implemented a professional-grade testing framework with clean code architecture, comprehensive error handling, and detailed test reporting capabilities.

---

## 🛠️ Technology Stack

| Component | Technology | Version |
| :--- | :--- | :--- |
| **Programming Language** | Java | 21 |
| **Test Automation Framework** | Selenium WebDriver | 4.38.0 |
| **Test Runner & Orchestration** | TestNG | 7.10.2 |
| **Build Automation** | Maven | Latest |
| **Reporting & Analytics** | Allure Reports | 2.32.0 |
| **Logging Framework** | Log4j2 | 2.25.2 |
| **Data Serialization** | Gson | 2.13.2 |
| **Test Data Generation** | Java Faker | 1.0.2 |
| **Configuration Management** | YAML (SnakeYAML) | 2.2 |

---

## ✨ Key Features & Highlights

### 1. **Page Object Model (POM) Architecture**
Implemented a clean separation between test logic and UI element locators, ensuring:
- High code reusability and maintainability
- Easy updates when UI elements change
- Improved test readability and organization

### 2. **Data-Driven Testing**
Supports multiple test scenarios through:
- JSON-based test data files for flexible data management
- Java Faker for dynamic, realistic test data generation
- Parameterized tests for comprehensive coverage

### 3. **Professional Test Reporting**
- **Allure Reports:** Interactive, detailed test reports with step-by-step execution flow
- **Automatic Screenshots:** Captures screenshots on test failures for quick debugging
- **Execution Metrics:** Detailed pass/fail statistics and trend analysis

### 4. **Robust Logging & Debugging**
- **Log4j2 Integration:** Comprehensive logging at multiple levels (INFO, WARN, ERROR)
- **Execution Trails:** Detailed logs for audit trails and troubleshooting
- **Emoji-Enhanced Logs:** Visual indicators for quick log scanning (✅, ❌, ⚠️, 🔍)

### 5. **Custom TestNG Listeners**
- **InvokedMethodListener:** Tracks test method invocations and execution flow
- **ITestListener:** Handles test lifecycle events, failures, and reporting

### 6. **Cross-Browser Ready**
- **DriverFactory Pattern:** Centralized browser initialization supporting multiple browsers
- **Easy Scalability:** Add new browsers with minimal code changes

### 7. **Advanced Test Utilities**
- **DataUtilies:** Manages JSON data and properties file reading
- **LogsUtils:** Simplified logging with consistent formatting
- **Utilty:** Common helper methods for repeated operations (login, waits, etc.)

---

## 📂 Project Structure

```
SwagLabs-Automation-Project/
├── src/
│   ├── main/java/
│   │   ├── DriverFactory/
│   │   │   └── DriverFactory.java          # Browser initialization & management
│   │   ├── Pages/
│   │   │   ├── LoginPage.java              # Login page object
│   │   │   ├── LandingPage.java            # Home page object
│   │   │   ├── CartPage.java               # Shopping cart page object
│   │   │   ├── CheckOutPage.java           # Checkout page object
│   │   │   ├── OverViewPage.java           # Order overview page object
│   │   │   └── FinishingOrderPage.java     # Order confirmation page object
│   │   └── Utilities/
│   │       ├── DataUtilies.java            # JSON & properties file handling
│   │       ├── LogsUtils.java              # Logging utilities
│   │       └── Utilty.java                 # Common helper methods
│   ├── main/resources/
│   │   ├── allure.properties               # Allure report configuration
│   │   └── log4j2.properties               # Log4j2 configuration
│   └── test/java/
│       ├── Listeners/
│       │   ├── ITestListener.java          # TestNG listener for test events
│       │   └── InvokedMethodListener.java   # Method invocation tracking
│       ├── Tests/
│       │   ├── LoginTest.java              # Login test cases
│       │   ├── LandingTest.java            # Landing page test cases
│       │   ├── CartTest.java               # Cart functionality test cases
│       │   ├── CheckOutTest.java           # Checkout flow test cases
│       │   ├── OverViewTest.java           # Order overview test cases
│       │   └── FinishingOrderTest.java     # Order completion test cases
│       └── resources/TestData/
│           ├── ValidLogin.json             # Valid login credentials
│           ├── InvalidLogin.json           # Invalid login credentials
│           ├── usersData.json              # User test data
│           └── environment.properties      # Environment configuration
├── Test Runner/
│   ├── LoginSuite.xml                      # Login test suite
│   ├── LandingSuite.xml                    # Landing page test suite
│   ├── CartSuite.xml                       # Cart test suite
│   ├── CheckOutSuit.xml                    # Checkout test suite
│   ├── OverViewSuit.xml                    # Overview test suite
│   ├── FinishingSuit.xml                   # Finishing order test suite
│   └── RegressionSuit.xml                  # Full regression suite
├── Test-Outputs/
│   ├── Logs/                               # Test execution logs
│   └── ScreenShoots/                       # Failure screenshots
├── pom.xml                                 # Maven configuration
└── README.md                               # This file
```

---

## 📋 Test Coverage

### **Login Functionality**
- ✅ Valid login with correct credentials
- ✅ Invalid username with valid password
- ✅ Valid username with invalid password
- ✅ Empty username and password
- ✅ Empty username only
- ✅ Empty password only
- ✅ Login page identity validation

### **Landing Page**
- ✅ Product listing verification
- ✅ Product sorting functionality
- ✅ Page layout and element visibility

### **Shopping Cart**
- ✅ Add products to cart
- ✅ Remove products from cart
- ✅ Cart count verification
- ✅ Product quantity comparison

### **Checkout Process**
- ✅ Checkout step one (user information entry)
- ✅ Checkout step two (order review)
- ✅ Checkout completion

### **Order Management**
- ✅ Order overview verification
- ✅ Order finalization
- ✅ Confirmation message validation

---

## 🚀 Getting Started

### **Prerequisites**
- **JDK 21** or higher installed
- **Maven** (3.6.0 or later) installed
- **Chrome** or **Firefox** browser installed
- **Git** for version control

### **Installation Steps**

1. **Clone the repository:**
   ```bash
   git clone https://github.com/MondyBourham/SwagLabs-Automation-Project.git
   cd SwagLabs-Automation-Project
   ```

2. **Install dependencies:**
   ```bash
   mvn clean install
   ```

3. **Configure environment settings:**
   - Update `src/test/resources/TestData/environment.properties` with your browser and URL preferences
   - Ensure test data files are properly configured

---

## ⚙️ Running Tests

### **Run All Tests**
```bash
mvn test
```

### **Run Specific Test Suite**
```bash
mvn test -Dsuite=Test Runner/LoginSuite.xml
```

### **Run Full Regression Suite**
```bash
mvn test -Dsuite=Test Runner/RegressionSuit.xml
```

### **Run Tests with Specific Browser**
Update `environment.properties` and run:
```bash
mvn test
```

---

## 📊 Generating Allure Reports

### **Generate and View Report**
```bash
mvn clean test
allure serve target/allure-results
```

This command will:
- Execute all tests
- Generate detailed Allure reports
- Open the report in your default browser

### **Report Features**
- Step-by-step test execution flow
- Failure screenshots and error messages
- Test duration and performance metrics
- Historical trend analysis
- Environment and browser information

---

## 📝 Test Data Management

### **JSON Test Data Files**
Located in `src/test/resources/TestData/`:

**ValidLogin.json:**
```json
{
  "username": "standard_user",
  "password": "secret_sauce"
}
```

**InvalidLogin.json:**
```json
{
  "username": "invalid_user",
  "password": "wrong_password"
}
```

### **Environment Configuration**
Edit `environment.properties` to customize:
- Browser type (Chrome, Firefox, Edge)
- Application URLs
- Timeout values
- Logging levels

---

## 🔧 Configuration Files

### **Log4j2 Configuration** (`log4j2.properties`)
Controls logging output format, level, and file management.

### **Allure Configuration** (`allure.properties`)
Manages Allure report generation and display settings.

---

## 🎓 Learning Outcomes

This project demonstrates proficiency in:
- Advanced Selenium WebDriver usage
- Page Object Model design pattern
- TestNG framework and annotations
- Data-driven testing approaches
- Custom listener implementation
- Professional logging and reporting
- Maven build automation
- Git version control
- Test automation best practices

---

## 📌 Best Practices Implemented

1. **Code Organization:** Clear separation of concerns (Pages, Tests, Utilities)
2. **Naming Conventions:** Descriptive, consistent naming across the codebase
3. **Error Handling:** Comprehensive exception handling with meaningful error messages
4. **Waits Strategy:** Explicit waits instead of hard sleeps for reliability
5. **Test Independence:** Each test is independent and can run in any order
6. **Reusability:** Common operations abstracted into utility methods
7. **Documentation:** Clear comments and documentation throughout the code
8. **CI/CD Ready:** Maven configuration supports automated pipeline integration

---

## 🐛 Troubleshooting

### **Common Issues**

**Issue:** Tests fail with "Element not found" exception
- **Solution:** Verify the application URL is correct in `environment.properties`
- Check browser compatibility and WebDriver version

**Issue:** Allure reports not generating
- **Solution:** Ensure `allure-commandline` is installed: `npm install -g allure-commandline`
- Run: `allure serve target/allure-results`

**Issue:** Tests timeout
- **Solution:** Increase timeout values in `environment.properties`
- Check internet connection and application responsiveness

---

## 📞 Support & Contribution

For issues, suggestions, or contributions:
1. Create an issue in the GitHub repository
2. Submit a pull request with improvements
3. Share feedback and best practices

---

## 📄 License

This project is open source and available for educational and professional use.

---

## 🙏 Acknowledgments

This project was developed as part of professional training in Software Quality Assurance and Test Automation, demonstrating industry best practices and modern automation frameworks.

---

**Last Updated:** December 29, 2025

**Project Status:** ✅ Active & Maintained
