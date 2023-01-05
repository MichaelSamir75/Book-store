drop schema if exists bookstore ;
create schema BOOKSTORE;
use BOOKSTORE ;

/*---------------------------------- Schema ----------------------------------*/
 create table PUBLISHER(
  publisherName         varchar(100) NOT NULL,
  publisherAddress      varchar(200) NOT NULL ,
  publisherPhone        varchar(11) ,
  primary key (publisherName)
);

create table BOOK(
	isbn                int auto_increment,
    title               varchar(100) unique NOT NULL,
    publisherName       varchar(100) NOT NULL,
    publicationYear     varchar(4) NOT NULL,
    sellingPrice       float NOT NULL ,
    category            varchar(10) NOT NULL,
    numOfCopies       int NOT NULL ,
    threshold           int NOT NULL check (threshold >= 0), 
	primary key (isbn) , 
	foreign key (publisherName) references publisher(publisherName) ON UPDATE CASCADE ON DELETE CASCADE
 );
 
create table AUTHOR(
  authorId      int auto_increment,
  authorName    varchar(100) NOT NULL ,
  primary key (authorId)  
);

create table BOOK_AUTHORS(
  isbn     int NOT NULL,
  authorId int NOT NULL,
  primary key (isbn, authorId),
  foreign key (isbn) references book (isbn) ON UPDATE CASCADE ON DELETE CASCADE,
  foreign key (authorId) references AUTHOR (authorId) ON UPDATE CASCADE ON DELETE CASCADE
);

create table USER_INFORMATION(
  userId     int auto_increment,
  userName   varchar(100) NOT NULL,
  password   varchar(50) NOT NULL,
  phone      varchar(11) ,
  first_name varchar(50) NOT NULL,
  last_name  varchar(50) NOT NULL,
  email      varchar(250) NOT NULL UNIQUE,
  Shipping_address varchar(100) NOT NULL ,
  primary key(userId)
);

create table MANAGER(
  userId     int NOT NULL ,
  foreign key (userId) references USER_INFORMATION (userId) ON UPDATE CASCADE ON DELETE CASCADE
);

create table ORDERS(
  orderId       int auto_increment,
  userId        int NOT NULL,
  isbn          int NOT NULL,
  quantity      int NOT NULL,
  primary key(orderId),
  foreign key (isbn) references BOOK (isbn) ON UPDATE CASCADE ON DELETE CASCADE,
  foreign key (userId) references USER_INFORMATION (userId) ON UPDATE CASCADE ON DELETE CASCADE
);

create table SELLING_ORDERS(
  orderId       int auto_increment,
  userId        int NOT NULL,
  isbn          int NOT NULL,
  quantity      int NOT NULL,
  date          date ,
  primary key(orderId , isbn),
  foreign key (isbn) references BOOK (isbn) ON UPDATE CASCADE ON DELETE CASCADE,
  foreign key (userId) references USER_INFORMATION (userId) ON UPDATE CASCADE ON DELETE CASCADE
);

create table CREDIT_CARD(
  cardNumber  varchar(20) NOT NULL,
  expiryDate  Date NOT NULL,
  primary key (cardNumber)
);

create table categories
(
  name varchar(10) primary key
);

insert into categories
values ('science'),
       ('art'),
       ('religion'),
       ('history'),
       ('geography');
/*---------------------------------- Indexing ----------------------------------*/
create index userIdIndex on user_information(userId);
create index userEmailIndex on user_information(email);
create index bookIsbnIndex on book(isbn);
create index bookTitleIndex on book(title);
create index bookAuthorIsbnIndex on book_authors(isbn);
create index authorIdIndex on author(authorId);
create index authorNameIndex on author(authorName);
create index userIdOrdersIndex on orders(userId);
create index isbnOrdersIndex on orders(isbn);
/*---------------------------------- Triggers ----------------------------------*/
delimiter $$
create trigger modify_existing_books
 before update on book
 for each row
begin
    if  new.numOfCopies<0 then
        SIGNAL SQLSTATE '45000'SET MESSAGE_TEXT = 'Cannot update book with negative number of copies ';
end if;
end;$$
delimiter ;
-- INSERT INTO `bookstore`.`publisher` (`publisherName`, `publisherAddress`, `publisherPhone`) VALUES ('elahram', 'cairo', '01023564789');
-- INSERT INTO `bookstore`.`book` (`title`, `publisherName`, `publicationYear`, `sellingPrice`, `category`, `numOfCopies`, `threshold`) VALUES ('algo', 'elahram', '2022', '130', 'history', '5', '2');
-- update book set numOfCopies=-1 where book.isbn=1;

delimiter $$
create trigger place_order
after update on book 
for each row
begin
    if  new.numOfCopies<new.threshold and old.numOfCopies>old.threshold then
            insert into ORDERS(userId,isbn,quantity) values ( "0" ,old.isbn,old.threshold);
    end if;
end;$$
delimiter ;
-- INSERT INTO `bookstore`.`user_information` (`userId`, `userName`, `password`, `phone`, `first_name`, `last_name`, `email`, `Shipping_address`) VALUES ('0', 'system', '123456789', '01069852347', 'hassan', 'mohamed', 'k@gmail.com', 'alex');
-- INSERT INTO `bookstore`.`publisher` (`publisherName`, `publisherAddress`, `publisherPhone`) VALUES ('elahram', 'cairo', '01023564789');
-- INSERT INTO `bookstore`.`book` (`title`, `publisherName`, `publicationYear`, `sellingPrice`, `category`, `numOfCopies`, `threshold`) VALUES ('algo', 'elahram', '2022', '130', 'history', '5', '2');
-- update book set numOfCopies=1 where book.isbn=1;

delimiter $$
create trigger confirmOrder 
before  delete on ORDERS
for each row
begin
    update book set numOfCopies = numOfCopies + old.quantity where isbn = old.isbn ;
end;$$
delimiter ;
-- delete from ORDERS where orders.orderId = 2;

