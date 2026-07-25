-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 25, 2026 at 12:05 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fooddelivery`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
                          `userId` varchar(50) NOT NULL,
                          `name` varchar(100) NOT NULL,
                          `email` varchar(100) NOT NULL,
                          `password` varchar(255) NOT NULL,
                          `contactNumber` varchar(20) DEFAULT NULL,
                          `accessLevel` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`userId`, `name`, `email`, `password`, `contactNumber`, `accessLevel`) VALUES
    ('A-001', 'System Admin', 'admin@fooddelivery.com', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', '09171234567', 'SuperAdmin');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
                             `userId` varchar(50) NOT NULL,
                             `name` varchar(100) DEFAULT NULL,
                             `email` varchar(100) DEFAULT NULL,
                             `password` varchar(100) DEFAULT NULL,
                             `contactNumber` varchar(20) DEFAULT NULL,
                             `deliveryAddress` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`userId`, `name`, `email`, `password`, `contactNumber`, `deliveryAddress`) VALUES
                                                                                                        ('C-1784962898336', 'kenji', 'kenjiuehara@gmail.com', 'c775e7b757ede630cd0aa1113bd102661ab38829ca52a6422ab782862f268646', '1234567890', 'qwert'),
                                                                                                        ('C-1784968259186', 'kenji', 'kenji@gmail.com', '8bb0cf6eb9b17d0f7d22b456f121257dc1254e1f01665370476383ea776df414', '1234235346', 'dgssfgsdf'),
                                                                                                        ('C-1784968267782', 'kenji', 'kenji@gmail.com', '8bb0cf6eb9b17d0f7d22b456f121257dc1254e1f01665370476383ea776df414', '1234235346', 'dgssfgsdf');

-- --------------------------------------------------------

--
-- Table structure for table `menu_items`
--

CREATE TABLE `menu_items` (
                              `itemId` varchar(50) NOT NULL,
                              `restaurantId` varchar(50) DEFAULT NULL,
                              `name` varchar(100) DEFAULT NULL,
                              `description` varchar(255) DEFAULT NULL,
                              `price` double DEFAULT NULL,
                              `category` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `menu_items`
--

INSERT INTO `menu_items` (`itemId`, `restaurantId`, `name`, `description`, `price`, `category`) VALUES
                                                                                                    ('M1', 'R1', 'Chickenjoy', 'Fried chicken', 89, 'Main'),
                                                                                                    ('M2', 'R1', 'Jolly Spaghetti', 'Sweet style', 55, 'Main'),
                                                                                                    ('M3', 'R1', 'Peach Mango Pie', 'Dessert', 39, 'Dessert'),
                                                                                                    ('M4', 'R1', 'Burger Steak', 'Rice meal', 99, 'Main'),
                                                                                                    ('M5', 'R2', 'Chicken Inasal', 'Grilled chicken with rice', 99, 'Main'),
                                                                                                    ('M6', 'R2', 'Halo-Halo', 'Filipino shaved ice dessert', 65, 'Dessert'),
                                                                                                    ('M7', 'R3', 'Beef Wanton Mami', 'Noodle soup with beef', 85, 'Main'),
                                                                                                    ('M8', 'R3', 'Siopao Asado', 'Steamed bun with pork', 45, 'Snack');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
                          `orderId` varchar(50) NOT NULL,
                          `customerId` varchar(50) DEFAULT NULL,
                          `status` varchar(50) DEFAULT NULL,
                          `totalAmount` double DEFAULT NULL,
                          `itemsSummary` varchar(255) DEFAULT NULL,
                          `riderId` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`orderId`, `customerId`, `status`, `totalAmount`, `itemsSummary`, `riderId`) VALUES
                                                                                                       ('ORD-1784512701460', 'C-1784512636356', 'Accepted', 419, 'Jolly Spaghetti x1, Jolly Spaghetti x1, Jolly Spaghetti x1, Jolly Spaghetti x1, Jolly Spaghetti x1, Jolly Spaghetti x1, Chickenjoy x1, ', NULL),
                                                                                                       ('ORD-1784538434109', 'C-1784538393640', 'Accepted', 891, 'Burger Steak x1, Burger Steak x1, Burger Steak x1, Burger Steak x1, Burger Steak x1, Burger Steak x1, Burger Steak x1, Burger Steak x1, Burger Steak x1, ', NULL),
                                                                                                       ('ORD-1784555791969', 'C-1784252464442', 'Accepted', 220, 'Jolly Spaghetti x1, Jolly Spaghetti x1, Jolly Spaghetti x1, Jolly Spaghetti x1, ', NULL),
                                                                                                       ('ORD-1784556107233', 'C-1784252464442', 'Accepted', 117, 'Peach Mango Pie x1, Peach Mango Pie x1, Peach Mango Pie x1, ', 'R-1784537885327'),
                                                                                                       ('ORD-1784556162801', 'C-1784252464442', 'Accepted', 273, 'Peach Mango Pie x1, Peach Mango Pie x1, Peach Mango Pie x1, Peach Mango Pie x1, Peach Mango Pie x1, Peach Mango Pie x1, Peach Mango Pie x1, ', 'R-1784537885327'),
                                                                                                       ('ORD-1784558441019', 'C-1784252464442', 'Cancelled', 312, 'Peach Mango Pie x1, Peach Mango Pie x1, Peach Mango Pie x1, Peach Mango Pie x1, Peach Mango Pie x1, Peach Mango Pie x1, Peach Mango Pie x1, Peach Mango Pie x1, ', NULL),
                                                                                                       ('ORD-1784593235163', 'C-1784593194027', 'Accepted', 234, 'Peach Mango Pie x1, Peach Mango Pie x1, Peach Mango Pie x1, Peach Mango Pie x1, Peach Mango Pie x1, Peach Mango Pie x1, ', 'R-1784960952460'),
                                                                                                       ('ORD-1784964920273', 'C-1784962898336', 'Accepted', 585, 'Halo-Halo x1, Halo-Halo x1, Halo-Halo x1, Halo-Halo x1, Halo-Halo x1, Halo-Halo x1, Halo-Halo x1, Halo-Halo x1, Halo-Halo x1, ', 'R-1784968053889'),
                                                                                                       ('ORD-1784966774836', 'C-1784962898336', 'Accepted', 792, 'Burger Steak x1, Burger Steak x1, Burger Steak x1, Burger Steak x1, Burger Steak x1, Burger Steak x1, Burger Steak x1, Burger Steak x1, ', 'R-1784968053889'),
                                                                                                       ('ORD-1784967557887', 'C-1784962898336', 'Accepted', 445, 'Chickenjoy x1, Chickenjoy x1, Chickenjoy x1, Chickenjoy x1, Chickenjoy x1, ', 'R-1784968053889'),
                                                                                                       ('ORD-1784967576400', 'C-1784962898336', 'Accepted', 594, 'Chicken Inasal x1, Chicken Inasal x1, Chicken Inasal x1, Chicken Inasal x1, Chicken Inasal x1, Chicken Inasal x1, ', 'R-1784968053889'),
                                                                                                       ('ORD-1784969434822', 'C-1784962898336', 'Cancelled', 356, 'Chickenjoy x1, Chickenjoy x1, Chickenjoy x1, Chickenjoy x1, ', NULL),
                                                                                                       ('ORD-1784969908491', 'C-1784962898336', 'Pending', 340, 'Beef Wanton Mami x1, Beef Wanton Mami x1, Beef Wanton Mami x1, Beef Wanton Mami x1, ', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `restaurants`
--

CREATE TABLE `restaurants` (
                               `restaurantId` varchar(50) NOT NULL,
                               `name` varchar(100) DEFAULT NULL,
                               `address` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `restaurants`
--

INSERT INTO `restaurants` (`restaurantId`, `name`, `address`) VALUES
                                                                  ('R1', 'Jollibee', 'Cebu City'),
                                                                  ('R2', 'Mang Inasal', 'Fuente Osmeña, Cebu City'),
                                                                  ('R3', 'Chowking', 'IT Park, Lahug, Cebu City');

-- --------------------------------------------------------

--
-- Table structure for table `riders`
--

CREATE TABLE `riders` (
                          `userId` varchar(50) NOT NULL,
                          `name` varchar(100) DEFAULT NULL,
                          `email` varchar(100) DEFAULT NULL,
                          `password` varchar(100) DEFAULT NULL,
                          `contactNumber` varchar(20) DEFAULT NULL,
                          `vehicleType` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `riders`
--

INSERT INTO `riders` (`userId`, `name`, `email`, `password`, `contactNumber`, `vehicleType`) VALUES
                                                                                                 ('R-1784968053889', 'kenji', 'kenjiuehara@gmail.com', '15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225', '3475345', 'Lamborghini'),
                                                                                                 ('R-1784968066945', 'kenji', 'kenjiuehara@gmail.com', '15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225', '3475345', 'Lamborghini');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
    ADD PRIMARY KEY (`userId`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
    ADD PRIMARY KEY (`userId`);

--
-- Indexes for table `menu_items`
--
ALTER TABLE `menu_items`
    ADD PRIMARY KEY (`itemId`),
  ADD KEY `restaurantId` (`restaurantId`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
    ADD PRIMARY KEY (`orderId`);

--
-- Indexes for table `restaurants`
--
ALTER TABLE `restaurants`
    ADD PRIMARY KEY (`restaurantId`);

--
-- Indexes for table `riders`
--
ALTER TABLE `riders`
    ADD PRIMARY KEY (`userId`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `menu_items`
--
ALTER TABLE `menu_items`
    ADD CONSTRAINT `menu_items_ibfk_1` FOREIGN KEY (`restaurantId`) REFERENCES `restaurants` (`restaurantId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
