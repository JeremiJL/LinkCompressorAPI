## Link Compressor Rest API Application

The application facilitates the user's creation of redirections for the desired link.
Target link and redirections, as well as an optional password and visit counter
are stored in a database.

The user can secure provided redirection with
password so that any modifications will require passing the password as
'request body' first.

The application implements the full CRUD set of operations on 'redirection' objects.
The user can communicate with the application in JSON and XML formats.

### Implementation in Spring :
- Spring DATA JPA and H2 database are responsible for data persistence.
- Spring Web for API.

