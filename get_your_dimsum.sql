-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 18, 2021 at 03:43 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `get_your_dimsum`
--

-- --------------------------------------------------------

--
-- Table structure for table `informasi_akun`
--

CREATE TABLE `informasi_akun` (
  `username` varchar(150) NOT NULL,
  `password` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `informasi_akun`
--

INSERT INTO `informasi_akun` (`username`, `password`) VALUES
('andre', '123'),
('budi', '123'),
('dadang', '123'),
('dina', '111'),
('george', '123'),
('ihsan', 'oke'),
('ihsansfd', 'oke123'),
('syahrul', '12345'),
('wahyu', '123'),
('windy', '123');

-- --------------------------------------------------------

--
-- Table structure for table `informasi_pengiriman`
--

CREATE TABLE `informasi_pengiriman` (
  `nama_penerima` varchar(255) NOT NULL,
  `alamat_penerima` varchar(255) NOT NULL,
  `RT` int(99) NOT NULL,
  `RW` int(99) NOT NULL,
  `kelurahan` varchar(255) NOT NULL,
  `kecamatan` varchar(255) NOT NULL,
  `kabupaten` varchar(255) NOT NULL,
  `provinsi` varchar(255) NOT NULL,
  `kode_pos` varchar(10) NOT NULL,
  `no_telp` bigint(255) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `informasi_pengiriman`
--

INSERT INTO `informasi_pengiriman` (`nama_penerima`, `alamat_penerima`, `RT`, `RW`, `kelurahan`, `kecamatan`, `kabupaten`, `provinsi`, `kode_pos`, `no_telp`, `username`) VALUES
('Andreas', 'San Andreas', 99, 99, 'TownBack', 'Gorrd', 'Berlllti', 'Virraar', '99999', 988646474, 'andre'),
('Dadang', 'Kp. Terdekat', 4, 3, 'Megawati', 'Guguntara', 'Kebab', 'Perpre', '445533', 998765554, 'dadang'),
('Wahyu Rahayu', 'Kp. Bulak', 5, 5, 'Muktiwari', 'Cibitung', 'Bekasi', 'Jawa Barat', '17520', 89514121147, 'wahyu');

-- --------------------------------------------------------

--
-- Table structure for table `informasi_pesanan`
--

CREATE TABLE `informasi_pesanan` (
  `id_pesanan` int(150) NOT NULL,
  `username` varchar(255) NOT NULL,
  `nama_produk` varchar(255) NOT NULL,
  `kuantitas` varchar(255) NOT NULL,
  `harga` varchar(255) NOT NULL,
  `total_harga` int(255) NOT NULL,
  `note` varchar(255) NOT NULL,
  `status_pengiriman` tinyint(1) NOT NULL,
  `status_pembayaran` tinyint(1) NOT NULL,
  `alamat` text NOT NULL,
  `no_resi` varchar(255) NOT NULL,
  `ongkir` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `informasi_pesanan`
--

INSERT INTO `informasi_pesanan` (`id_pesanan`, `username`, `nama_produk`, `kuantitas`, `harga`, `total_harga`, `note`, `status_pengiriman`, `status_pembayaran`, `alamat`, `no_resi`, `ongkir`) VALUES
(15, 'ihsan', 'Dimsum Ayam,Dimsum Sapi,Dimsum Keju,Dimsum Extra Keju,Dimsum Penyet,Dimsum Penyet', '1,1,0,0,0,0', '3000,3000,3000,3000,3000,3000', 16000, 'sdfgdfsgg', 1, 1, 'Ihsan 09383475589\nKaerra, 05/02, kasdfas, dfsgsd, sdfgdsg, sdfsgfs, 94848', 'J&T JP98492840', 10000),
(16, 'wahyu', 'Dimsum Ayam,Dimsum Sapi,Dimsum Keju,Dimsum Extra Keju,Dimsum Penyet,Dimsum Penyet', '3,0,0,1,0,0', '3000,3000,3000,3000,3000,3000', 22000, 'Rasa Manis', 1, 1, 'Wahyu Rahayu 089514121147\nKp. Bulak Kunyit, 04/03, Muktiwari, Cibitung, Bekasi, Jawa Barat, 17520', 'SiCepatSC84343', 10000),
(17, 'wahyu', 'Dimsum Ayam,Dimsum Sapi,Dimsum Keju,Dimsum Extra Keju,Dimsum Penyet,Dimsum Penyet', '10,100,100,100,0,0', '3000,3000,3000,3000,3000,3000', 965000, 'Pakai sambal yang banyak yaa', 1, 1, 'Wahyu Rahayu 089514121147\nKp. Bulak Kunyit, 04/03, Muktiwari, Cibitung, Bekasi, Jawa Barat, 17520', 'J&TJP546645', 35000),
(18, 'wahyu', 'Dimsum Ayam,Dimsum Sapi,Dimsum Keju,Dimsum Extra Keju,Dimsum Penyet,Dimsum Penyet', '3,1,1,0,0,0', '3000,3000,3000,3000,3000,3000', 25000, 'sdfgds', 1, 1, 'Wahyu 097655\nKp. Bulakk, 04/03, Kecerr, Macan, Singa, Bebek, 19443', 'SiNinja099455', 10000),
(19, 'dadang', 'Dimsum Ayam,Dimsum Sapi,Dimsum Keju,Dimsum Extra Keju,Dimsum Penyet,Dimsum Penyet', '0,1,0,0,0,0', '3000,3000,3000,3000,3000,3000', 13000, 'jangan disambelin', 1, 1, 'Dadang 998765554\nKp. Terdekat, 04/03, Megawati, Guguntara, Kebab, Perpre, 445533', 'SiCepat884435', 10000),
(20, 'dadang', 'Dimsum Ayam,Dimsum Sapi,Dimsum Keju,Dimsum Extra Keju,Dimsum Penyet,Dimsum Penyet', '2,5,0,0,0,0', '3000,3000,3000,3000,3000,3000', 31000, 'Pake Sambel yg banyoak', 1, 1, 'Dadang 998765554\nKp. Terdekat, 04/03, Megawati, Guguntara, Kebab, Perpre, 445533', 'JNE6648384', 10000),
(21, 'andre', 'Dimsum Ayam,Dimsum Sapi,Dimsum Keju,Dimsum Extra Keju,Dimsum Penyet,Dimsum Penyet', '2,0,0,0,100,0', '3000,3000,3000,3000,3000,3000', 326000, 'Cepetan oooyy laparr', 1, 1, 'Andreas 988646474\nSan Andreas, 99/99, TownBack, Gorrd, Berlllti, Virraar, 99999', 'Shinobi88', 20000),
(22, 'wahyu', 'Dimsum Ayam,Dimsum Sapi,Dimsum Keju,Dimsum Extra Keju,Dimsum Penyet,Dimsum Pedas Manis', '4,5,5,6,6,5', '3000,3000,3000,3000,3000,3000', 103000, 'Yang rasa kejunya jangan banyak2', 1, 1, 'Wahyu Rahayu 089515121147\nKp. Bulak Kunyit, 05/04, Muktiwari, Cibitung, Bekasi, Jawa Barat, 17520', 'JNE3344533', 10000),
(23, 'wahyu', 'Dimsum Ayam,Dimsum Sapi,Dimsum Keju,Dimsum Extra Keju,Dimsum Penyet,Dimsum Rendang plus', '1,1,1,1,1,1', '3000,3000,3500,4000,4500,5000', 33000, 'Cepat yah kita dah laparr', 1, 1, 'Wahyu Rahayu 89514121147\nKp. Bulak, 05/05, Muktiwari, Cibitung, Bekasi, Jawa Barat, 17520', 'SiCepat775544', 10000),
(24, 'ihsansfd', 'Dimsum Ayam,Dimsum Sapi,Dimsum Keju,Dimsum Extra Keju,Dimsum Penyet,Dimsum Rendang plus', '4,5,4,5,0,4', '3000,3000,3500,4000,4500,5000', 91000, 'Sambelnya banyakin yaa', 1, 1, 'Ihsan Nurul Iman 081154409\nJl. Kebon Nanas Utara, 08/81, Jatinegara, Cipinang, Jakarta Timur, DKI Jakarta, 198491', '-', 10000),
(26, 'syahrul', 'Dimsum Ayam,Dimsum Sapi,Dimsum Keju,Dimsum Extra Keju,Dimsum Penyet,Dimsum Rendang plus', '2,2,2,0,0,0', '3000,3000,3500,4000,4500,5000', 29000, 'sdffdg', 0, 0, 'Syahrul 059587\ndfsgsdgf, 09/98, dfsgdsg, dfsdfsg, dfsfgdf, dsfg, 84748', '-', 10000);

-- --------------------------------------------------------

--
-- Table structure for table `informasi_produk`
--

CREATE TABLE `informasi_produk` (
  `nama_produk` varchar(255) NOT NULL,
  `harga_produk` int(255) NOT NULL,
  `stok` int(255) NOT NULL,
  `berat_barang` double(5,2) NOT NULL,
  `id_produk` int(255) NOT NULL,
  `deskripsi_produk` varchar(2555) NOT NULL,
  `gambar` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `informasi_produk`
--

INSERT INTO `informasi_produk` (`nama_produk`, `harga_produk`, `stok`, `berat_barang`, `id_produk`, `deskripsi_produk`, `gambar`) VALUES
('Dimsum Keju', 3000, 3000, 0.13, 10, 'Produk Dimsum Keju', 'C:\\Users\\hp\\Documents\\NetBeansProjects\\Get Your Dimsum\\src\\produkImage\\04 dimsum.jpg'),
('Dimsum Ayam', 3000, 3000, 0.13, 13, 'Produk Dimsum Ayam', 'C:\\Users\\hp\\Documents\\NetBeansProjects\\Get Your Dimsum\\src\\produkImage\\02 dimsum ayam.jpg'),
('Dimsum Sapi', 3000, 3000, 0.13, 20, 'Produk Dimsum Sapi', 'C:\\Users\\hp\\Documents\\NetBeansProjects\\Get Your Dimsum\\src\\produkImage\\03 dimsum ayam.jpg'),
('Dimsum Spesial', 4000, 3000, 0.15, 21, 'Produk Dimsum Spesial', 'C:\\Users\\hp\\Documents\\NetBeansProjects\\Get Your Dimsum\\src\\produkImage\\05 dimsum.jpg'),
('Dimsum Extra Keju', 4500, 2000, 0.12, 24, 'Merupakan Produk Terbaru Kami', 'C:\\Users\\hp\\Documents\\NetBeansProjects\\Get Your Dimsum\\src\\produkImage\\04 dimsum.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `informasi_akun`
--
ALTER TABLE `informasi_akun`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `informasi_pengiriman`
--
ALTER TABLE `informasi_pengiriman`
  ADD PRIMARY KEY (`no_telp`);

--
-- Indexes for table `informasi_pesanan`
--
ALTER TABLE `informasi_pesanan`
  ADD PRIMARY KEY (`id_pesanan`);

--
-- Indexes for table `informasi_produk`
--
ALTER TABLE `informasi_produk`
  ADD PRIMARY KEY (`id_produk`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `informasi_pesanan`
--
ALTER TABLE `informasi_pesanan`
  MODIFY `id_pesanan` int(150) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
