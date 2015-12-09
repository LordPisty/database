# database

### Environment Requirements
* JDK: 1.8 (1.8.0_40)
* Gradle: 2.7

### General Assumptions
* any invalid command will be ignored, and result in a no-op on the DB

### Run instructions
* From the project folder run `gradle run`
  * the gradle runner accept also input from file, e.g.: `gradle run < test.txt`
* or after running `gradle build` from the project folder, from `<project_folder>\build\classes\main` run `java com.database.Main`

### Available Gradle Tasks:
 *  `clean` - delete all build artifacts
 *  `test` - run all unit tests (reports -> 'build/reports/tests/', results -> 'build/test-results/')
 *  `intTest` - run integration tests (reports -> 'build/reports/intTests/', results -> 'build/test-results/')
 *  `generateJavadocs` - generate javadoc documentation into 'build/docs/javadoc/'
 *  `build` - build application
 *  `run` - run main method
