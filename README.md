### This Website is built for following purpose:

For Demonstrate books online.
Getting detailed informations about books.
Adding and managing books and publishers.
User Friendly.
This is a Mini-project developed using Java, Jdbc.

Admin Have Following Access for this online store site:

- Add New Books and Publishers.
- Update New Books and Publishers.
- Add Books to spesific Publishers.
- View Books and Publishers Available.
- Remove Books and Publishers.

Publishers Have Following Access for this online library site:

- Create New Account or Register.
- Add New Books.
- Update Books.
- View Books Available.
- Remove Books.

Users Have Following Access for this online library site:

- View Available Books.

### Technologies used:
- Back-End Development
- Java [JDK 8+]
- Spring (Boot/Data/Security)
- JPA
- JWT
- Docker
- MapStruct

Database used.
- MySql

### ==== Software And Tools Required ====
- MySQL
- Intellij IDEA
- Java [JDK 8+]
- Tomcat v8.0+
- Gradle

### =============== Dummy Database Initialization =====================

STEP 1: Open MySQL Command Prompt or MySQL Workbench or just Data Source

STEP 2: Login to the administrator user as : mysql -u <username> -p (Enter Password if asked)

STEP 3 :Copy paste the following MySql Commands:

Note: Default tables will be create via JPA.
```MySQL
-- user-role datas
insert into role values (1,'ROLE_ADMIN');
insert into role values (2,'ROLE_PUBLISHER');
insert into role values (3,'ROLE_USER');

insert into  user values (1, 'Admin','$2a$10$Tvcbmk05q.e3Z9rmv0QDOOxbjWT3BPep4sYVm.cL2ju.5s9o95Opi','admin');
insert into  user values (2, 'Teas-Press','$2a$10$Tvcbmk05q.e3Z9rmv0QDOOxbjWT3BPep4sYVm.cL2ju.5s9o95Opi','teas');
insert into  user values (3, 'John','$2a$10$Tvcbmk05q.e3Z9rmv0QDOOxbjWT3BPep4sYVm.cL2ju.5s9o95Opi','jdoe');

insert into user_roles values (1, 1);
insert into user_roles values (2, 2);
insert into user_roles values (3, 3);

-- dummy datas for publishers and books
insert into books (id, author, isbn, title, publisher_id) values (2,'J. K. Rowling','0-7475-3269-9','Harry Potter and the Philosophers Stone',1);
insert into books (id, author, isbn, title, publisher_id) values (4,'J. R. R. Tolkien','0-6182-6030-7','The Hobbit',3);
insert into books (id, author, isbn, title, publisher_id) values (11,'King, Stephen','1501192264','The Green Mile',10);
insert into books (id, author, isbn, title, publisher_id) values (13,'Agatha Christie','0062073559','Death on the Nile',12);
insert into books (id, author, isbn, title, publisher_id) values (15,'J. K. Rowling','0','The Man and the Myth',14);
insert into books (id, author, isbn, title, publisher_id) values (19,'Brend, Barbara','0-7123-0392-8','The Emperor Akbars Khamsa of Nizami',18);
insert into books (id, author, isbn, title, publisher_id) values (21,'Klavan, Andrew','1-6844-2270-1','The Emperors Sword: Another Kingdom',5);
insert into books (id, author, isbn, title, publisher_id) values (23,'Kipling, Rudyard','0141325291','The Jungle Book',22);
insert into books (id, author, isbn, title, publisher_id) values (25,'Draizer Teodor','5389084810','Sestra Kerri',24);
insert into books (id, author, isbn, title, publisher_id) values (27,'Dilqəm Əhməd','0','Mühacirlərin dönüşü',26);

insert into publisher (id, country, name) values (1,'England','Bloomsbury');
insert into publisher (id, country, name) values (3,'United Kingdom','George Allen & Unwinomsbury');
insert into publisher (id, country, name) values (5,'Nashville, US','Turner');
insert into publisher (id, country, name) values (10,'USA','Gallery Books');
insert into publisher (id, country, name) values (12,'USA','HarperCollins');
insert into publisher (id, country, name) values (14,'USA','Australia and New Zealand Book Company');
insert into publisher (id, country, name) values (18,'England','British Library Board');
insert into publisher (id, country, name) values (22,'UK','Puffin Books');
insert into publisher (id, country, name) values (24,'RF','Azbuka');
insert into publisher (id, country, name) values (26,'AZE','Teas-Press');
```


"Suggestions and project Improvements are Invited!"<br/>
Thanks a lot<br/>
<b>Elgun Shukurov</b>
