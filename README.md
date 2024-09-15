# Library Java

Library Java is a Java application designed to simulate the functionality of a library system. Users can log in, search for books, and reserve them. The application includes user and admin roles, with admins having additional capabilities to manage and monitor book rentals.

## Description

Library Java is an application that mirrors the operations of a library. It allows users to log in, search for books by genre or title, and reserve them. Once a book is checked out, it is marked as "borrowed" and is no longer visible in the book database. Admins have special privileges to monitor book rentals, check overdue books, and view the book database, which is read from a `.txt` file.

## Features

- **User Authentication:** Users can log in to access the system.
- **Book Search:** Users can search for books by genre or title.
- **Book Reservation:** Users can reserve books and the status of the book is updated to "borrowed" once checked out.
- **Admin Capabilities:** Admins can:
  - View who has borrowed books and when.
  - Check if anyone is overdue on returning books.
  - Access and manage the book database, which is read from a `.txt` file.
- **Book Status Management:** Books that are checked out are not shown in the available book list.

## Technologies

- **Language:** Java
- **File Handling:** `.txt` files for book database

## Installation

To set up and run Library Java locally, follow these steps:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-repo/library-java.git
