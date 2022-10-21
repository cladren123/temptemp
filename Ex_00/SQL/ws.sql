create database `workshop`;
use `workshop`;

drop table `book`;
CREATE TABLE IF NOT EXISTS `book` (
  `isbn` CHAR(12) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `author` VARCHAR(50) NOT NULL,
  `price` INT NOT NULL,
  `desc` TEXT NULL,
  `img` VARCHAR(100) NULL,
  PRIMARY KEY (`isbn`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `user` (
  `id` VARCHAR(50) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `pass` VARCHAR(100) NOT NULL,
  `rec_id` VARCHAR(50) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_user1_idx` (`rec_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_user1`
    FOREIGN KEY (`rec_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `comment` (
  `isbn` CHAR(12) NOT NULL,
  `id` VARCHAR(50) NOT NULL,
  `rating` INT default 5,
  `comment` VARCHAR(1000) NULL,
  constraint check( `rating` >0 and `rating`<=5),
  PRIMARY KEY (`isbn`, `id`),
  INDEX `fk_comment_user1_idx` (`id` ASC) VISIBLE,
  CONSTRAINT `fk_comment_book`
    FOREIGN KEY (`isbn`)
    REFERENCES `book` (`isbn`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

insert into user values('ssafy', '김싸피','1234', null);
insert into user values('admin', '관리자','1234', null);
insert into user values('hyewon', '주혜원','1234', null);

insert into book values('1111', 'ssafy1', '싸피1', 1111, 'ssafy1', null);
insert into book values('2222', 'ssafy2', '싸피2', 2222, 'ssafy2', null);
insert into book values('3333', 'ssafy3', '싸피3', 3333, 'ssafy3', null);
insert into book values('4444', 'ssafy4', '싸피4', 4444, 'ssafy4', null);
insert into book values('5555', 'ssafy5', '싸피5', 5555, 'ssafy5', null);
commit;

select * from user;
select * from book;