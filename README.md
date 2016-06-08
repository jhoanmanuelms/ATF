# ATF
Automated Test Framework that uses Cucumber+Selenium. This framework is created to execute automated tests over Mulesoft's Anypoint platform.

TEST SCENARIOS COVERED
- Adding Roles

STEPS TO EXECUTE
- Clone or download ATF
- Go to the ATF folder
- Execute the commands:
    - gradlew.bat test (Windows)
    - ./gradlew test (Linux)

PREREQUISITES
- You need to have Mozilla Firefox installed

TODO
- Move sign in steps to a separate class since they can be reused by another test suites
- Create step definitions to delete roles in order to perform a tear down after the tests have been executed
- Implement more test scenarios
