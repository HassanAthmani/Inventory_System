-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 14, 2019 at 10:41 AM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inventory_project`
--

-- --------------------------------------------------------

--
-- Table structure for table `all_items`
--

CREATE TABLE `all_items` (
  `id` int(100) NOT NULL,
  `mac_address` varchar(100) NOT NULL,
  `brand` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  `version` varchar(100) NOT NULL,
  `addition_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `all_items`
--

INSERT INTO `all_items` (`id`, `mac_address`, `brand`, `model`, `version`, `addition_date`) VALUES
(1, '12029290', 'HP', 'ZBOOK', 'WINDOWS 7', '2019-06-02'),
(3, '12384756', 'HP', 'ZBOOK3', 'WINDOWS 10', '2019-06-03'),
(4, '1298378373', 'LENOVO', 'IDEAPAD', 'WINDOWS 10', '2019-06-04'),
(5, '34598654', 'LENOVO', 'ZEUS', 'WINDOWS 10', '2019-06-05'),
(6, '23985787', 'HP', 'PROBOOK', 'WINDOWS 8', '2019-06-05'),
(7, '48478595', 'APPLE', 'MAC PRO', 'XOS', '2019-06-05'),
(8, '57987809', 'ACER', 'PRO', 'UBUNTU', '2019-06-05'),
(9, '57898736', 'ACER', 'PRO', 'UBUNTU', '2019-06-05'),
(11, '39878909', 'ASUS', 'LEGACY', 'WINDOWS XP', '2019-06-05'),
(13, '12897485', 'ASUS ', 'LEGACY', 'WINDOWS 10', '2019-06-06'),
(14, '12985968', 'HP ', 'SPECTRE X360', 'WINDOWS 10', '2019-06-06'),
(15, '3489586089', 'HP', 'ELITEBOOK', 'WINDOWS 7', '2019-06-09'),
(16, '12787635', 'ASUS', 'ZEUS', 'WINDOWS 7', '2019-06-09');

-- --------------------------------------------------------

--
-- Table structure for table `deferred`
--

CREATE TABLE `deferred` (
  `id` int(100) NOT NULL,
  `mac_address` varchar(100) NOT NULL,
  `brand` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  `version` varchar(100) NOT NULL,
  `addition_date` date NOT NULL,
  `deferred_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `deferred`
--

INSERT INTO `deferred` (`id`, `mac_address`, `brand`, `model`, `version`, `addition_date`, `deferred_date`) VALUES
(1, '12029290', 'HP', 'ZBOOK', 'WINDOWS 7', '2019-06-02', '2019-06-08'),
(8, '57987809', 'ACER', 'PRO', 'UBUNTU', '2019-06-05', '2019-06-08'),
(9, '57898736', 'ACER', 'PRO', 'UBUNTU', '2019-06-05', '2019-06-09');

-- --------------------------------------------------------

--
-- Table structure for table `instock`
--

CREATE TABLE `instock` (
  `id` int(100) NOT NULL,
  `mac_address` varchar(100) NOT NULL,
  `brand` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  `version` varchar(100) NOT NULL,
  `addition_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `instock`
--

INSERT INTO `instock` (`id`, `mac_address`, `brand`, `model`, `version`, `addition_date`) VALUES
(11, '39878909', 'ASUS', 'LEGACY', 'WINDOWS XP', '2019-06-05'),
(13, '12897485', 'ASUS ', 'LEGACY', 'WINDOWS 10', '2019-06-06'),
(6, '23985787', 'HP', 'PROBOOK', 'WINDOWS 8', '2019-06-05'),
(7, '48478595', 'APPLE', 'MAC PRO', 'XOS', '2019-06-05'),
(5, '34598654', 'LENOVO', 'ZEUS', 'WINDOWS 10', '2019-06-05');

-- --------------------------------------------------------

--
-- Table structure for table `in_maintanance`
--

CREATE TABLE `in_maintanance` (
  `id` int(100) NOT NULL,
  `mac_address` varchar(100) NOT NULL,
  `brand` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  `version` varchar(100) NOT NULL,
  `addition_date` date NOT NULL,
  `worker_name` varchar(100) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `in_maintanance`
--

INSERT INTO `in_maintanance` (`id`, `mac_address`, `brand`, `model`, `version`, `addition_date`, `worker_name`, `date`) VALUES
(16, '12787635', 'ASUS', 'ZEUS', 'WINDOWS 7', '2019-06-09', 'STEVEN', '2019-06-09'),
(14, '12985968', 'HP ', 'SPECTRE X360', 'WINDOWS 10', '2019-06-06', 'JOB', '2019-06-09');

-- --------------------------------------------------------

--
-- Table structure for table `with_user`
--

CREATE TABLE `with_user` (
  `id` int(100) NOT NULL,
  `mac_address` varchar(100) NOT NULL,
  `brand` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  `version` varchar(100) NOT NULL,
  `addition_date` date NOT NULL,
  `user_name` varchar(100) NOT NULL,
  `user_phone` varchar(100) NOT NULL,
  `worker_name` varchar(100) NOT NULL,
  `date_assigned` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `with_user`
--

INSERT INTO `with_user` (`id`, `mac_address`, `brand`, `model`, `version`, `addition_date`, `user_name`, `user_phone`, `worker_name`, `date_assigned`) VALUES
(15, '3489586089', 'HP', 'ELITEBOOK', 'WINDOWS 7', '2019-06-09', 'JAMES', '0789867656', 'JACOB', '2019-06-09');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `all_items`
--
ALTER TABLE `all_items`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `all_items`
--
ALTER TABLE `all_items`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
