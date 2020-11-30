-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 30, 2020 at 03:46 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_overtime`
--

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `Id` int(10) NOT NULL,
  `Department` varchar(100) NOT NULL,
  `Manager` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`Id`, `Department`, `Manager`) VALUES
(1, 'IT', 14),
(2, 'Ads', 6);

-- --------------------------------------------------------

--
-- Table structure for table `dummytime`
--

CREATE TABLE `dummytime` (
  `id` int(2) NOT NULL,
  `time` time NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `dummytime`
--

INSERT INTO `dummytime` (`id`, `time`) VALUES
(1, '00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `Id` int(255) NOT NULL,
  `NIK` varchar(12) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Password` varchar(6) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Telephone` varchar(12) NOT NULL,
  `Address` varchar(1000) NOT NULL,
  `Manager` int(255) DEFAULT NULL,
  `Title` int(10) DEFAULT NULL,
  `Department` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`Id`, `NIK`, `Name`, `Password`, `Email`, `Telephone`, `Address`, `Manager`, `Title`, `Department`) VALUES
(6, '1006', 'Bagas', '12345', 'bagas@email.com', '24680', 'magelang', 6, 3, 1),
(13, '1002', 'Kelvin', '12345', 'kelvin@email.com', '54321', 'solo', 14, 2, 2),
(14, '1001', 'Ardian', '12345', 'ardian@email.com', '12345', 'salatiga', 13, 2, 1),
(17, '1003', 'Daniel', '12345', 'daniel@email.com', '54321', 'salatiga', 6, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `overtime`
--

CREATE TABLE `overtime` (
  `Id` int(255) NOT NULL,
  `Submit_Date` datetime NOT NULL DEFAULT current_timestamp(),
  `Start_Time` time DEFAULT NULL,
  `End_Time` time DEFAULT NULL,
  `Total_Time` varchar(100) DEFAULT NULL,
  `Description` varchar(1000) NOT NULL,
  `Manager_Notes` varchar(1000) DEFAULT NULL,
  `Status` varchar(10) DEFAULT NULL,
  `Employee` int(255) NOT NULL,
  `Department` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `overtime`
--

INSERT INTO `overtime` (`Id`, `Submit_Date`, `Start_Time`, `End_Time`, `Total_Time`, `Description`, `Manager_Notes`, `Status`, `Employee`, `Department`) VALUES
(35, '2020-11-29 00:00:00', '18:19:41', '21:19:41', '2020-11-29', 'makan', NULL, 'Pending', 14, 2),
(36, '2022-05-11 00:00:00', '11:28:01', '13:28:01', '2020-11-29', 'ma', NULL, 'Pending', 14, 1),
(37, '2022-05-11 00:00:00', '11:28:01', '13:28:01', '2020-11-29', 'aw', NULL, 'Pending', 14, 1),
(38, '2022-05-11 00:00:00', '11:28:01', '13:28:01', '2020-11-29', 'aw', NULL, 'Pending', 14, 1),
(39, '2022-05-11 00:00:00', '11:28:01', '13:28:01', '2020-11-29', 'a', NULL, 'Pending', 14, 1),
(40, '2022-05-11 00:00:00', '11:28:01', '15:28:01', '2020-11-29', 'aa', NULL, 'Pending', 14, 1),
(41, '2022-05-11 00:00:00', '11:28:01', '13:28:01', '02:00:00', '12', NULL, 'Pending', 14, 1),
(42, '2022-05-11 00:00:00', '11:28:01', '14:28:01', '03:00:00', 'www', NULL, 'Pending', 14, 1),
(43, '2022-05-10 17:00:00', '11:28:01', '13:28:01', '02:00:00', 'aaaa', NULL, 'Pending', 14, 1),
(44, '2022-05-10 17:00:00', '11:28:01', '13:28:01', '02:00:00', 'asd', NULL, 'Pending', 14, 1),
(46, '2020-11-29 22:24:03', '18:19:41', '21:19:41', '03:00:00', 'hh', NULL, 'Pending', 14, 2),
(47, '2022-05-10 17:00:00', '11:28:01', '14:28:01', '03:00:00', 'lalalal', NULL, 'Pending', 14, 1);

--
-- Triggers `overtime`
--
DELIMITER $$
CREATE TRIGGER `insert_overtime` BEFORE INSERT ON `overtime` FOR EACH ROW SET NEW.Total_Time = TIMEDIFF(NEW.End_Time, NEW.Start_Time),
NEW.Status = "Pending"
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `policy`
--

CREATE TABLE `policy` (
  `Id` int(255) NOT NULL,
  `Description` text NOT NULL,
  `Time` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `policy`
--

INSERT INTO `policy` (`Id`, `Description`, `Time`) VALUES
(2, 'total lembur (1 bulan)', 72000);

-- --------------------------------------------------------

--
-- Table structure for table `title`
--

CREATE TABLE `title` (
  `Id` int(10) NOT NULL,
  `Title` varchar(100) NOT NULL,
  `Job_Desc` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `title`
--

INSERT INTO `title` (`Id`, `Title`, `Job_Desc`) VALUES
(1, 'Karyawan', 'Kerja'),
(2, 'Manager', 'Beri tugas'),
(3, 'Admin', 'Atur policy');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `manager_emp` (`Manager`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `manager` (`Manager`),
  ADD KEY `department` (`Department`),
  ADD KEY `title` (`Title`);

--
-- Indexes for table `overtime`
--
ALTER TABLE `overtime`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `employee` (`Employee`),
  ADD KEY `department_ot` (`Department`);

--
-- Indexes for table `policy`
--
ALTER TABLE `policy`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `title`
--
ALTER TABLE `title`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `Id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `overtime`
--
ALTER TABLE `overtime`
  MODIFY `Id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT for table `policy`
--
ALTER TABLE `policy`
  MODIFY `Id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `department`
--
ALTER TABLE `department`
  ADD CONSTRAINT `manager_emp` FOREIGN KEY (`Manager`) REFERENCES `employee` (`Id`);

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `department` FOREIGN KEY (`Department`) REFERENCES `department` (`Id`),
  ADD CONSTRAINT `manager` FOREIGN KEY (`Manager`) REFERENCES `employee` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `title` FOREIGN KEY (`Title`) REFERENCES `title` (`Id`);

--
-- Constraints for table `overtime`
--
ALTER TABLE `overtime`
  ADD CONSTRAINT `department_ot` FOREIGN KEY (`Department`) REFERENCES `department` (`Id`),
  ADD CONSTRAINT `employee` FOREIGN KEY (`Employee`) REFERENCES `employee` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
