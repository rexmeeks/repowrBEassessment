To run:
mvn clean install
mvn spring-boot:run

(or ./mvnw if you don't have maven installed)

I didn't really have much time to do super verbose testing, but the correct flow is tested:
to run tests import the testing postman collection under resources, go to the collection runner and 
run the repowr assessment testing collection, in there it also has all the endpoints for the project as well, 
only modify the requests that are marked as modifiable otherwise it'll mess with the tests

there's also another that's Repowr Assessment Editable that you can import and change stuff on

(edit: spent too long messing with the postman collection runner and couldn't get the iterative 
tests to work, so in the interest of time just leaving it like this, doing a single iteration, 
personally I prefer to use a testing suite like cucumber (in Java) just used Postman, because 
it's quick, so not super familiar)