-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 24, 2024 at 03:16 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `k5_banhaisan`
--

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `mancc` int(11) NOT NULL,
  `tenncc` varchar(50) NOT NULL,
  `diachincc` varchar(255) NOT NULL,
  `sdtncc` int(10) NOT NULL,
  `emailncc` varchar(255) NOT NULL,
  `stkncc` int(20) NOT NULL,
  `ghichu` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhacungcap`
--

INSERT INTO `nhacungcap` (`mancc`, `tenncc`, `diachincc`, `sdtncc`, `emailncc`, `stkncc`, `ghichu`) VALUES
(1, 'Cảng hải sản miền trung', '213 Lệ Trân', 222922922, 'haisanmientrung@gmail.com', 993993993, 'Mối nhập chính'),
(2, 'Trung tâm Bãi sao', '745 Hoàng Hà', 935335335, 'baisao@gmail.com', 935335335, 'Chuyên cung cấp cá'),
(3, 'Trạm Mực ', '468 Nguyễn Thà', 988888988, 'trammuc@gmail.com', 988888988, 'Mối cung cấp mực');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_account`
--

CREATE TABLE `tbl_account` (
  `id` int(10) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `sdt` int(10) NOT NULL,
  `authorities` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_account`
--

INSERT INTO `tbl_account` (`id`, `username`, `password`, `name`, `email`, `sdt`, `authorities`) VALUES
(1, 'admin', '123', 'admin', 'ad@gmail.com', 33933933, 0),
(2, 'admin1', '123', 'admin1', 'ad1@gmail.com', 494494494, 0),
(3, 'vanloc', '123', 'huynhLoc', 'loc@gmail.com', 339330333, 1),
(4, 'gacon', 'ga@gmail.com', '', '', 0, 1),
(5, '', '', '', '', 0, 1),
(6, 'alo', '321', 'Nguen van huy', 'huy12@gmail.com', 8623452, 1),
(7, 'alo113', '123', 'alo113', 'a@gmail.com', 933933933, 1),
(8, 'taottt12', '123', 'taottt12', 'tao@gmail.com', 399878788, 1),
(9, '', '', '', '', 0, 1),
(10, 'loc', '1', 'Van Cong', 'loc@gmail.com', 933933933, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_cthdb`
--

CREATE TABLE `tbl_cthdb` (
  `id_HDB` int(10) NOT NULL,
  `id_MH` int(11) NOT NULL,
  `giaban` int(50) NOT NULL,
  `soluong` int(50) NOT NULL,
  `mucgiamgia` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_cthdb`
--

INSERT INTO `tbl_cthdb` (`id_HDB`, `id_MH`, `giaban`, `soluong`, `mucgiamgia`) VALUES
(26, 7, 120000, 1, 0),
(26, 8, 890000, 1, 0),
(27, 3, 187000, 1, 0),
(27, 5, 170000, 1, 0),
(28, 5, 170000, 1, 0),
(28, 6, 576000, 2, 0),
(29, 7, 120000, 1, 0),
(29, 8, 1780000, 2, 0),
(30, 23, 12000, 1, 0),
(31, 7, 120000, 1, 0),
(31, 8, 1780000, 2, 0);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_hoadonban`
--

CREATE TABLE `tbl_hoadonban` (
  `id_HDB` int(10) NOT NULL,
  `id_KH` int(10) NOT NULL,
  `ngayban` date NOT NULL,
  `ghichu` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_hoadonban`
--

INSERT INTO `tbl_hoadonban` (`id_HDB`, `id_KH`, `ngayban`, `ghichu`) VALUES
(26, 30, '2023-10-10', 'null'),
(27, 31, '2023-10-10', 'null'),
(28, 32, '2023-10-10', 'null'),
(29, 33, '2023-10-10', 'null'),
(31, 35, '2023-10-17', 'null');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_hoadonnhap`
--

CREATE TABLE `tbl_hoadonnhap` (
  `ma_hdn` int(11) NOT NULL,
  `ma_mh` int(11) NOT NULL,
  `so_luong` int(50) NOT NULL,
  `ma_ncc` int(11) NOT NULL,
  `ngay_nhap` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_hoadonnhap`
--

INSERT INTO `tbl_hoadonnhap` (`ma_hdn`, `ma_mh`, `so_luong`, `ma_ncc`, `ngay_nhap`) VALUES
(7, 19, 2, 1, '2023-10-14 20:38:06.000000'),
(8, 19, 12, 1, '2023-10-14 20:38:12.000000'),
(9, 1, 33, 1, '2023-10-14 20:38:27.000000'),
(10, 19, 30, 1, '2023-10-14 20:39:15.000000'),
(11, 8, 1000, 1, '2023-10-15 18:50:14.000000'),
(12, 19, 5, 1, '2023-10-15 18:50:36.000000'),
(13, 23, 50, 1, '2023-10-15 20:01:54.000000'),
(14, 24, 35, 1, '2023-10-17 12:15:39.000000'),
(15, 25, 50, 3, '2023-10-17 14:38:58.000000');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_khachhang`
--

CREATE TABLE `tbl_khachhang` (
  `id_KH` int(10) NOT NULL,
  `id_user` int(10) NOT NULL,
  `tenKH` varchar(50) NOT NULL,
  `emailKH` varchar(100) NOT NULL,
  `sdtKH` int(10) NOT NULL,
  `diaChiKH` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_khachhang`
--

INSERT INTO `tbl_khachhang` (`id_KH`, `id_user`, `tenKH`, `emailKH`, `sdtKH`, `diaChiKH`) VALUES
(30, 7, 'jkghdf', 'jhasfa', 933933933, '1lo jkh'),
(31, 7, 'dfgsdg', 'fsdgsg', 0, 'sfgsfg'),
(32, 7, 'dfa', 'hjfg', 345234, 'dfgh'),
(33, 7, 'ukj', 'dfgdfgh', 3455634, 'fgdbdfb'),
(34, 6, 'Nguyen Van Manh', 'm@gmail.com', 933733633, '12 nguyen xi'),
(35, 10, 'Nguyen Van A', 'a@gmail.com', 933933933, '12 Le Loi');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_loaihang`
--

CREATE TABLE `tbl_loaihang` (
  `malh` int(11) NOT NULL,
  `tenloaihang` varchar(50) NOT NULL,
  `mota` varchar(255) DEFAULT NULL,
  `ghichu` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_loaihang`
--

INSERT INTO `tbl_loaihang` (`malh`, `tenloaihang`, `mota`, `ghichu`) VALUES
(1, 'Tôm', 'Tôm hùm,Tôm sú,Tôm tít,Tôm càng xanh...', NULL),
(2, 'Cua ghẹ', 'Cua ghẹ Alaska,Cua ghẹ Mỹ,Cua ghẹ Tasmania,Cua ghẹ Đại Tây Dương...', NULL),
(3, 'Cá', 'Cá hồi,Cá basa,Cá chình bông, Cá mập sữa, Cá mú, Cá Chim, Cá dìa...', NULL),
(4, 'Mực', 'Mực ống, mực cơm, mực lá, bạch tuộc...', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_mathang`
--

CREATE TABLE `tbl_mathang` (
  `ma_mh` int(11) NOT NULL,
  `tensanpham` varchar(50) DEFAULT NULL,
  `soluong` int(50) DEFAULT NULL,
  `dongia` int(50) DEFAULT NULL,
  `donvitinh` varchar(50) DEFAULT NULL,
  `ghichu` varchar(255) DEFAULT NULL,
  `hinhanh` text DEFAULT NULL,
  `mancc` int(11) DEFAULT NULL,
  `malh` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_mathang`
--

INSERT INTO `tbl_mathang` (`ma_mh`, `tensanpham`, `soluong`, `dongia`, `donvitinh`, `ghichu`, `hinhanh`, `mancc`, `malh`) VALUES
(1, 'Mực ống', 53, 137000, 'ky', NULL, 'mucong.jpg', 3, 4),
(2, 'Mực nan', 47, 146000, 'ky', NULL, 'mucnan.png', 3, 4),
(3, 'Bạch tuột', 48, 187000, 'ky', NULL, 'bachtuot.jpg', 3, 4),
(4, 'Tôm hùm Alaska', 50, 469000, 'ky', NULL, 'tomhum.jpg', 1, 1),
(5, 'Tôm sú', 48, 170000, 'ky', NULL, 'tomsu.jpg', 1, 1),
(6, 'Tôm càng xanh', 48, 288000, 'ky', NULL, 'tomcangxanh.jpg', 1, 1),
(7, 'Tôm tít', 45, 120000, 'ky', NULL, 'tomtit.jpg', 1, 1),
(8, 'Cua hoàng đế', 1048, 890000, 'ky', NULL, 'cuahoangde.jpg', 1, 2),
(23, 'cá heo', 49, 12000, 'vbnm', NULL, '15-10-23-169739153254609.jpg', 1, 3),
(24, 'cá  chim', 35, 123000, 'ký', NULL, '16-10-23-169742812764649.jpg', 2, 3),
(25, 'Cá  Hồi', 50, 12333, 'ký', NULL, '17-10-23-169752831293278.jpg', 2, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`mancc`);

--
-- Indexes for table `tbl_account`
--
ALTER TABLE `tbl_account`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_cthdb`
--
ALTER TABLE `tbl_cthdb`
  ADD PRIMARY KEY (`id_HDB`,`id_MH`);

--
-- Indexes for table `tbl_hoadonban`
--
ALTER TABLE `tbl_hoadonban`
  ADD PRIMARY KEY (`id_HDB`),
  ADD KEY `id_KH` (`id_KH`);

--
-- Indexes for table `tbl_hoadonnhap`
--
ALTER TABLE `tbl_hoadonnhap`
  ADD PRIMARY KEY (`ma_hdn`);

--
-- Indexes for table `tbl_khachhang`
--
ALTER TABLE `tbl_khachhang`
  ADD PRIMARY KEY (`id_KH`) USING BTREE,
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `tbl_loaihang`
--
ALTER TABLE `tbl_loaihang`
  ADD PRIMARY KEY (`malh`);

--
-- Indexes for table `tbl_mathang`
--
ALTER TABLE `tbl_mathang`
  ADD PRIMARY KEY (`ma_mh`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  MODIFY `mancc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_account`
--
ALTER TABLE `tbl_account`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `tbl_hoadonban`
--
ALTER TABLE `tbl_hoadonban`
  MODIFY `id_HDB` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `tbl_hoadonnhap`
--
ALTER TABLE `tbl_hoadonnhap`
  MODIFY `ma_hdn` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `tbl_khachhang`
--
ALTER TABLE `tbl_khachhang`
  MODIFY `id_KH` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `tbl_loaihang`
--
ALTER TABLE `tbl_loaihang`
  MODIFY `malh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_mathang`
--
ALTER TABLE `tbl_mathang`
  MODIFY `ma_mh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
