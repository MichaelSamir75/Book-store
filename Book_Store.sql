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
    title               varchar(100) NOT NULL,
    publisherName       varchar(100) NOT NULL,
    publicationYear     year NOT NULL,
    sellingPrice       float NOT NULL ,
    category            varchar(10) NOT NULL,
    numOfCopies       int NOT NULL CHECK (numOfCopies >= 0) ,
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
  orderId       int NOT NULL,
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

/*---------------------------------- Triggers ----------------------------------*/
delimiter $$

create trigger modify_existing_books
 before update on book
 for each row
begin
    if  new.numOfCopies<0 then
        SIGNAL SQLSTATE '45000'SET MESSAGE_TEXT = 'Cannot update book bookmodify_existing_booksmodify_existing_bookswith negative number of copies ';
end if;
end;$$

create trigger place_order
after update on book 
for each row
begin
    if  new.numOfCopies<new.threshold and old.numOfCopies>old.threshold then
            insert into ORDERS(user_id,isbn,quantity) values ( "0" ,old.isbn,old.threshold);
    end if;
end;$$

create trigger confirmOrder 
before  delete on ORDERS
for each row
begin
    update book set numOfCopies = numOfCopies + old.quantity where isbn = old.isbn ;
end;$$

UPDATE `bookstore`.`book` SET `numOfCopies` = '-1' WHERE (`isbn` = '1');

