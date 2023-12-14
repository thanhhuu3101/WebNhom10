-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 14, 2023 at 11:07 AM
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
CREATE Database `azshop`;
--
USE `azshop`;
-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `ten` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `ten`, `user_id`) VALUES
(1, 'quần dài', 82),
(2, 'quần ngắn', 82),
(3, 'quần đen', 82);

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

CREATE TABLE `hoadon` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `tong_tien` int(11) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hoadon`
--

INSERT INTO `hoadon` (`id`, `user_id`, `tong_tien`, `status`) VALUES
(90, 82, 200000, 0);

-- --------------------------------------------------------

--
-- Table structure for table `hoadonchitiet`
--

CREATE TABLE `hoadonchitiet` (
  `id` int(11) NOT NULL,
  `hoaDon_id` int(11) DEFAULT NULL,
  `product_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `so_luong` int(11) NOT NULL,
  `thanh_tien` int(50) NOT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hoadonchitiet`
--

INSERT INTO `hoadonchitiet` (`id`, `hoaDon_id`, `product_id`, `user_id`, `so_luong`, `thanh_tien`, `status`) VALUES
(113, 82, 45, 83, 1, 200000, 1),
(114, 82, 46, 83, 1, 150000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `ten` varchar(255) NOT NULL,
  `so_luong` int(11) NOT NULL DEFAULT 0,
  `don_gia` int(11) NOT NULL DEFAULT 0,
  `mau_sac` varchar(255) NOT NULL,
  `kich_thuoc` varchar(255) NOT NULL,
  `img` varchar(255) DEFAULT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `ten`, `so_luong`, `don_gia`, `mau_sac`, `kich_thuoc`, `img`, `category_id`) VALUES
(1, 'quần dài 7 49 lỗ', 15, 200000, 'đen', '40cm', 'images.jpg', 1),
(2, 'quần đen 3 lỗ và áo thủng 4 lỗ', 15, 150000, 'đen', '40cm', 'img2.jpg', 2),
(3, 'quần ngắn aaaaaa', 15, 230000, 'trắng', '50cm', 'img7.jpg', 2),
(4, 'quần đen thủng 3 lỗ ', 15, 43000, 'đen', '60cm và 50cm', 'img4.jpg', 3),
(5, 'Quần lủng 2 lỗ thừa 1 lỗ', 15, 123000, 'đen', '80cm', 'img5.jpg', 3),
(6, 'quần dài dó', 15, 50000, '1', '50cm', 'img6.jpg', 1),
(7, 'quần dài 14 lỗ', 3, 230000, 'red', '40cm', 'img3.jpg', 1),
(8, 'quần đen 3 lỗ 1', 4, 233000, 'đen', '40cm', 'img7.jpg', 3),
(9, 'quần đen 3 lỗ 123', 4, 3, 'đen', '40cm', 'img9.jpg', 3),
(10, 'quần ngắn quáaaaaaaaaa', 3, 50000, 'xanh', '40cm', 'img8.jpg', 2),
(57, 'quần', 1, 20000, 'đen', '40cm', 'img7.jpg', 3);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `ho_ten` varchar(255) NOT NULL,
  `gioi_tinh` int(11) NOT NULL DEFAULT 1,
  `sdt` varchar(255) NOT NULL,
  `dia_chi` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `role` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `ho_ten`, `gioi_tinh`, `sdt`, `dia_chi`, `email`, `password`, `avatar`, `role`) VALUES
(82, 'THANG', 1, '0844567893', 'ĐN', 'admin@gmail.com', '$2a$10$suKdht.Fzsb1lP0ZTBQH/ut/XdlWtnT8PBsWMRlFmWbdchmM4wneu', NULL, 1),
(89, 'ThanhPhung', 1, '0844567892', 'Q9', 'p@gmail.com', '$2a$10$B2aug3IfowPV0O5JCUtt/uD5e9PFaDFMQzcBmx8O8uGb7YyfjZ1HS', NULL, 1),
(91, 'THANGVU', 1, '0786865906', 'Q8', 't@gmail.com', '$2a$10$JUiXqAZmKmR5qZimZFPrCeZ5iRnqmg7SJHpp2eNVVenlQxiK8jGBK', NULL, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `hoadonchitiet`
--
ALTER TABLE `hoadonchitiet`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=91;

--
-- AUTO_INCREMENT for table `hoadonchitiet`
--
ALTER TABLE `hoadonchitiet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=115;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=93;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
