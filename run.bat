cd "$(CURRENT_DIRECTORY)"
del /S *.class
javac -cp .;sql/*; TicketManagementSystem.java
java -cp .;sql/*; TicketManagementSystem