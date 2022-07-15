-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 15, 2022 at 07:10 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bluemalldb`
--

-- --------------------------------------------------------

--
-- Table structure for table `cliente`
--

CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL,
  `codigo` int(11) DEFAULT NULL,
  `nit` varchar(30) DEFAULT NULL,
  `correo` varchar(30) DEFAULT NULL,
  `genero` varchar(10) DEFAULT NULL,
  `nombre` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cliente`
--

INSERT INTO `cliente` (`idCliente`, `codigo`, `nit`, `correo`, `genero`, `nombre`) VALUES
(1, 333, 'cli3', 'CLI3@email.com', 'M', 'cli3');

-- --------------------------------------------------------

--
-- Table structure for table `producto`
--

CREATE TABLE `producto` (
  `idProducto` int(11) NOT NULL,
  `codigo` int(11) DEFAULT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `descripcion` varchar(30) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `producto`
--

INSERT INTO `producto` (`idProducto`, `codigo`, `nombre`, `descripcion`, `cantidad`, `precio`) VALUES
(2, 1, 'Policrome', 'pintura tipo a', 50, 22.5),
(3, 66, 'Argolla metal dorado', 'argolla de metal', 66, 14.25);

-- --------------------------------------------------------

--
-- Table structure for table `sucursal`
--

CREATE TABLE `sucursal` (
  `idSucusal` int(11) NOT NULL,
  `codigo` int(11) DEFAULT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  `direccion` varchar(30) DEFAULT NULL,
  `correo` varchar(30) DEFAULT NULL,
  `telefono` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sucursal`
--

INSERT INTO `sucursal` (`idSucusal`, `codigo`, `nombre`, `direccion`, `correo`, `telefono`) VALUES
(1, 166, 'Linda Vista', 'zona 2 vn', 'blue1@emai.com', '4563657'),
(2, 2, 'Proceres', 'zona 10 guatemala', 'test@email.com', '123456789'),
(3, 2, 'Proceres', 'zona 10 guatemala', 'test@email.com', '123456789'),
(4, 5, 'Villa Lobos', 'zona 12', 'zona@email.com', '14563213'),
(5, 6, 'villa canales', 'zona 1 villa canales', 'test@email.com', '45678962'),
(6, 3, 'test6', 'test6', 'test6@email.com', '456795'),
(7, 66, 'test7', 'test7', 'test7@email.com', '123465464'),
(8, 1, 'asdf', 'asdf', 'asfd', '45646');

-- --------------------------------------------------------

--
-- Table structure for table `vendedor`
--

CREATE TABLE `vendedor` (
  `idVendedor` int(11) NOT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `caja` int(11) DEFAULT NULL,
  `ventas` int(11) DEFAULT NULL,
  `genero` varchar(10) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `codigo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `vendedor`
--

INSERT INTO `vendedor` (`idVendedor`, `nombre`, `caja`, `ventas`, `genero`, `password`, `codigo`) VALUES
(2, 'JAM78', 88, 78, 'F', 'JAM7788', 77);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idCliente`);

--
-- Indexes for table `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`idProducto`);

--
-- Indexes for table `sucursal`
--
ALTER TABLE `sucursal`
  ADD PRIMARY KEY (`idSucusal`);

--
-- Indexes for table `vendedor`
--
ALTER TABLE `vendedor`
  ADD PRIMARY KEY (`idVendedor`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `producto`
--
ALTER TABLE `producto`
  MODIFY `idProducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `sucursal`
--
ALTER TABLE `sucursal`
  MODIFY `idSucusal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `vendedor`
--
ALTER TABLE `vendedor`
  MODIFY `idVendedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
