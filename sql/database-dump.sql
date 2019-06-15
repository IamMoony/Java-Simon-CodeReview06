-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 15, 2019 at 07:20 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `java_codereview06`
--

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE `class` (
  `classId` int(11) NOT NULL,
  `className` varchar(55) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`classId`, `className`) VALUES
(1, '1a'),
(2, '1b'),
(3, '2a'),
(4, '2b'),
(5, '3a'),
(6, '3b'),
(7, '4a'),
(8, '4b');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `studentId` int(11) NOT NULL,
  `studentName` varchar(55) DEFAULT NULL,
  `studentSurname` varchar(55) DEFAULT NULL,
  `studentEmail` varchar(55) DEFAULT NULL,
  `fk_classId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`studentId`, `studentName`, `studentSurname`, `studentEmail`, `fk_classId`) VALUES
(1, 'John', 'Doe', 'john.doe@domain.com', 1),
(2, 'Jane', 'Doe', 'jane.doe@domain.com', 2),
(3, 'Jonathan', 'Jones', 'jonathan.jones@domain.com', 3),
(4, 'Jerry', 'Jeriko', 'jerry.jerkiko@domain.com', 4),
(5, 'Mike', 'Meyers', 'mike.meyers@domain.com', 5),
(6, 'Simon', 'Strange', 'simon.strange@domain.com', 6),
(7, 'Jenny', 'Jeng', 'jenny.jeng@domain.com', 7),
(8, 'Chris', 'Curry', 'chris.curry@domain.com', 8);

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `teacherId` int(11) NOT NULL,
  `teacherName` varchar(55) DEFAULT NULL,
  `teacherSurname` varchar(55) DEFAULT NULL,
  `teacherEmail` varchar(55) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`teacherId`, `teacherName`, `teacherSurname`, `teacherEmail`) VALUES
(1, 'Karl', 'Lagerfeld', 'karl.lagerfeld@domain.com'),
(2, 'Gregor', 'Warmhud', 'gregor.warmhud@domain.com'),
(3, 'Tom', 'Turbo', 'tom.turbo@domain.com'),
(4, 'Konrad', 'Kasperl', 'konrad.kasperl@domain.com'),
(5, 'Frank', 'Fatal', 'frank.fatal@domain.com'),
(6, 'Susan', 'Sun', 'susan.sun@domain.com'),
(7, 'Katy', 'Perry', 'katy.perry@domain.com'),
(8, 'George', 'Bush', 'george.bush@domain.com');

-- --------------------------------------------------------

--
-- Table structure for table `teacherclass`
--

CREATE TABLE `teacherclass` (
  `fk_teacherId` int(11) DEFAULT NULL,
  `fk_classId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `teacherclass`
--

INSERT INTO `teacherclass` (`fk_teacherId`, `fk_classId`) VALUES
(1, 2),
(1, 6),
(2, 2),
(2, 8),
(3, 5),
(3, 8),
(4, 6),
(4, 8),
(5, 1),
(5, 3),
(6, 3),
(6, 7),
(7, 1),
(7, 7),
(8, 1),
(8, 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`classId`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`studentId`),
  ADD KEY `fk_classId` (`fk_classId`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`teacherId`);

--
-- Indexes for table `teacherclass`
--
ALTER TABLE `teacherclass`
  ADD KEY `fk_teacherId` (`fk_teacherId`,`fk_classId`),
  ADD KEY `fk_classId` (`fk_classId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `class`
--
ALTER TABLE `class`
  MODIFY `classId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `studentId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `teacher`
--
ALTER TABLE `teacher`
  MODIFY `teacherId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`fk_classId`) REFERENCES `class` (`classId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `teacherclass`
--
ALTER TABLE `teacherclass`
  ADD CONSTRAINT `teacherclass_ibfk_1` FOREIGN KEY (`fk_classId`) REFERENCES `class` (`classId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `teacherclass_ibfk_2` FOREIGN KEY (`fk_teacherId`) REFERENCES `teacher` (`teacherId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
