-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-06-2016 a las 17:40:51
-- Versión del servidor: 5.6.26
-- Versión de PHP: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyecto_final`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `usuario` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  `id` int(11) NOT NULL,
  `tipo` varchar(10) NOT NULL
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `login`
--

INSERT INTO `login` (`usuario`, `password`, `id`, `tipo`) VALUES
('mk', 'mk', 1, 'barra'),
('cocinero', 'c', 2, 'cocina');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE IF NOT EXISTS `pedido` (
  `id` int(11) NOT NULL,
  `mesa` int(11) NOT NULL,
  `estado_cuenta` int(2) NOT NULL,
  `estado` int(11) NOT NULL
) ENGINE=MyISAM AUTO_INCREMENT=417 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id`, `mesa`, `estado_cuenta`, `estado`) VALUES
(416, 2, 1, 1),
(415, 1, 0, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido_producto`
--

CREATE TABLE IF NOT EXISTS `pedido_producto` (
  `id` int(10) NOT NULL,
  `id_producto` int(5) NOT NULL,
  `cantidad` int(5) NOT NULL,
  `mesa` int(3) NOT NULL,
  `estado` int(1) NOT NULL,
  `id_pedido` int(11) NOT NULL,
  `listo` int(1) NOT NULL DEFAULT '0',
  `listo_cocina` int(1) NOT NULL,
  `nombre` varchar(30) NOT NULL
) ENGINE=MyISAM AUTO_INCREMENT=393 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `pedido_producto`
--

INSERT INTO `pedido_producto` (`id`, `id_producto`, `cantidad`, `mesa`, `estado`, `id_pedido`, `listo`, `listo_cocina`, `nombre`) VALUES
(392, 1, 1, 1, 1, 415, 0, 0, 'Cerveza'),
(391, 5, 1, 1, 1, 415, 0, 0, 'Vino');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE IF NOT EXISTS `producto` (
  `id` int(3) NOT NULL,
  `producto` varchar(30) NOT NULL,
  `tipo` varchar(16) NOT NULL,
  `precio` double NOT NULL,
  `estado` int(11) NOT NULL
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `producto`, `tipo`, `precio`, `estado`) VALUES
(1, 'Cerveza', 'Bebida', 2, 1),
(2, 'Solomillo', 'Carne', 13, 1),
(5, 'Vino', 'Bebida', 2, 1),
(8, 'Lomo', 'Carne', 4, 1),
(10, 'Coca-Cola', 'Bebida', 3, 1),
(11, 'Merluza', 'Pescado', 4, 1),
(12, 'Tropical', 'Verde', 6, 1),
(13, 'Helado Choco', 'Postres', 2, 1),
(17, 'Almejas', 'Pescado', 17, 1),
(16, 'Brownie', 'Postres', 4, 1),
(18, 'Tomate Raf', 'Verde', 20, 1),
(19, 'Fanta', 'Bebida', 2, 1),
(20, 'Norber-beer', 'Bebida', 12, 1),
(24, 'Rape', 'Pescado', 16, 1),
(23, 'Peleón', 'Bebida', 1, 1),
(25, 'De Frutas', 'Verde', 7, 1),
(26, 'Churrasco', 'Carne', 12, 1),
(27, 'Tarta Nata', 'Postres', 9, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pedido_producto`
--
ALTER TABLE `pedido_producto`
  ADD UNIQUE KEY `UNIQUE` (`id`) USING BTREE;

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `login`
--
ALTER TABLE `login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=417;
--
-- AUTO_INCREMENT de la tabla `pedido_producto`
--
ALTER TABLE `pedido_producto`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=393;
--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=28;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
