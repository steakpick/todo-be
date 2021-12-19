CREATE DATABASE IF NOT EXISTS `todoDB`;

USE `todoDB`;

CREATE TABLE IF NOT EXISTS `task` (
    `id` int(11) NOT NULL auto_increment,
    `name` varchar(100) NOT NULL,
    `description` varchar(200),
    `status` varchar(30),
    `importance` varchar(30),
    `urgency` varchar(30),
    `complexity` varchar(30),
    `priority` varchar(30),
    `creationDate` DATETIME NOT NULL,
    `startDate` DATETIME,
    `endDate` DATETIME,
    `dueDate` DATETIME,
    PRIMARY KEY  (`id`)
);

CREATE TABLE IF NOT EXISTS `board` (
    `id` int(11) NOT NULL auto_increment,
    `name` varchar(100) NOT NULL,
    PRIMARY KEY  (`id`)
);

CREATE TABLE IF NOT EXISTS `task_board` (
    `id` int(11) NOT NULL auto_increment,
    `idBoard` int(11),
    `idTask` int(11),
    PRIMARY KEY  (`id`),
    FOREIGN KEY (`idBoard`) REFERENCES board (id) ON DELETE CASCADE ,
    FOREIGN KEY (`idTask`) REFERENCES task (id) ON DELETE CASCADE
);