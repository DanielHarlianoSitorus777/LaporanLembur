-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 13, 2020 at 08:56 AM
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
(14, '1001', 'Ardian', '12345', 'ardianpramudya81@gmail.com', '12345678', 'salatiga', 14, 2, 1),
(18, '1004', 'Maria', '12345', 'maria@email.com', '12345', 'salatiga', 14, 1, 1),
(29, '1005', 'Kelvin', '12345', 'tepinnko@gmail.com', '12345', 'solo', 14, 1, 1),
(32, '1007', 'Alphitaaaaaaaaa', '12345', 'ardianalphita@gmail.com', '12345678', 'salatiga', 14, 1, 1),
(33, '1008', 'Daniel', '12345', 'daniel@email.com', '12345', 'salatiga', 14, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `overtime`
--

CREATE TABLE `overtime` (
  `Id` int(255) NOT NULL,
  `Submit_Date` varchar(10) NOT NULL DEFAULT current_timestamp(),
  `Start_Time` time DEFAULT NULL,
  `End_Time` time DEFAULT NULL,
  `Total_Time` varchar(100) DEFAULT NULL,
  `Description` varchar(1000) NOT NULL,
  `Manager_Notes` varchar(1000) DEFAULT NULL,
  `Status` varchar(10) DEFAULT NULL,
  `Employee` int(255) NOT NULL,
  `Department` int(10) NOT NULL,
  `reorder` int(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `overtime`
--

INSERT INTO `overtime` (`Id`, `Submit_Date`, `Start_Time`, `End_Time`, `Total_Time`, `Description`, `Manager_Notes`, `Status`, `Employee`, `Department`, `reorder`) VALUES
(35, '2020-11-29', '18:19:41', '21:19:41', '2020-11-29', 'makan', NULL, 'Pending', 14, 2, 1),
(36, '2022-05-11', '11:28:01', '13:28:01', '2020-11-29', 'ma', NULL, 'Approve', 14, 1, 3),
(37, '2022-05-11', '11:28:01', '13:28:01', '2020-11-29', 'aw', NULL, 'Reject', 14, 1, 2),
(38, '2022-05-11', '11:28:01', '13:28:01', '2020-11-29', 'aw', NULL, 'Approve', 14, 1, 3),
(39, '2022-05-11', '11:28:01', '13:28:01', '2020-11-29', 'a', NULL, 'Approve', 14, 1, 3),
(40, '2022-05-11', '11:28:01', '15:28:01', '2020-11-29', 'aa', NULL, 'Reject', 14, 1, 2),
(41, '2022-05-11', '11:28:01', '13:28:01', '02:00:00', '12', NULL, 'Reject', 14, 1, 2),
(42, '2022-05-11', '11:28:01', '14:28:01', '03:00:00', 'www', NULL, 'Reject', 14, 1, 2),
(43, '2022-05-10', '11:28:01', '13:28:01', '02:00:00', 'aaaa', NULL, 'Reject', 14, 1, 2),
(44, '2022-05-10', '11:28:01', '13:28:01', '02:00:00', 'asd', NULL, 'Approve', 14, 1, 3),
(46, '2020-11-29', '18:19:41', '21:19:41', '03:00:00', 'hh', NULL, 'Pending', 14, 2, 1),
(47, '2022-05-10', '11:28:01', '14:28:01', '03:00:00', 'lalalal', NULL, 'Approve', 14, 1, 3),
(48, '2020-12-01', '11:32:23', '14:32:23', '03:00:00', 'eee', NULL, 'Reject', 14, 1, 2),
(49, '2020-12-01', '11:35:54', '14:35:54', '03:00:00', 'uuu', 'lu ngapain', 'Reject', 14, 1, 2),
(55, '2020-12-01', '13:24:17', '16:24:17', '03:00:00', 'lembur kerja tugas', 'gapapa. bekerja lah dengan giat demi masa depan', 'Approve', 14, 1, 3),
(56, '2020-12-02', '11:09:40', '15:09:40', '04:00:00', 'Sarapan Pagi', 'jangan lupa bagi-bagi yoi', 'Approve', 14, 1, 3),
(57, '2020-12-02', '11:11:06', '14:11:06', '03:00:00', 'Tidur Sebentar', NULL, 'Approve', 14, 1, 3),
(58, '2020-12-02', '11:08:02', '13:08:02', '02:00:00', 'coba lembur', NULL, 'Approve', 14, 1, 3),
(59, '2020-12-02', '11:08:55', '13:08:55', '02:00:00', 'coba lembur lagi', 'kerja yang bagus sodaraku', 'Approve', 14, 1, 3),
(60, '2020-12-02', '11:36:33', '14:36:33', '03:00:00', 'Makan siang', 'jangan lupa bagi-bagi yoi yoi', 'Approve', 18, 1, 3),
(61, '2020-12-06', '11:03:37', '13:03:37', '02:00:00', 'Istirahat sore', NULL, 'Approve', 18, 1, 3),
(62, '2020-12-06', '11:05:32', '12:05:32', '01:00:00', 'tiduur tidurr', NULL, 'Approve', 18, 1, 3),
(63, '2020-12-06', '10:15:30', '13:15:30', '03:00:00', 'makan makan', 'ajakin dong', 'Approve', 29, 1, 3),
(64, '2020-12-11', '05:29:57', '08:29:57', '03:00:00', 'makan siang', NULL, 'Approve', 29, 1, 3),
(65, '2020-12-12', '03:35:26', '08:35:26', '05:00:00', 'coba submit', NULL, 'Reject', 29, 1, 2),
(66, '2020-12-12', '11:00:00', '13:28:01', '02:28:01', 'coba lagi', NULL, 'Pending', 29, 1, 1),
(67, '2020-12-12', '11:00:00', '13:28:01', '02:28:01', 'coba lagi', NULL, 'Pending', 29, 1, 1),
(68, '2020-12-12', '11:00:00', '14:30:00', '03:30:00', 'kerja keras', 'kerja keras bersama-sama', 'Pending', 29, 1, 1),
(69, '2020-12-13', '11:00:00', '13:10:00', '02:10:00', 'coba coba lagi', NULL, 'Approved', 29, 1, 3),
(70, '2020-12-13', '11:00:00', '11:30:00', '00:30:00', 'coba', 'selamat mencoba-coba', 'Rejected', 18, 1, 2),
(71, '2020-12-13', '11:00:00', '12:40:00', '01:40:00', 'AAAA', NULL, 'Reject', 18, 1, 2),
(72, '2020-12-13', '11:00:00', '12:20:00', '01:20:00', 'coba reorder 1', NULL, 'Approve', 18, 1, 3),
(73, '2020-12-13', '11:00:00', '12:00:00', '01:00:00', 'coba email', NULL, 'Approved', 18, 1, 3);

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
(2, 'total lembur (1 bulan)', 72000),
(5, 'lembur tambahan ngasal', 3600);

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
  MODIFY `Id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `overtime`
--
ALTER TABLE `overtime`
  MODIFY `Id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- AUTO_INCREMENT for table `policy`
--
ALTER TABLE `policy`
  MODIFY `Id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

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
