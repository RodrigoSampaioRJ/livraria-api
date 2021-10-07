CREATE TABLE `tb_livro` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `titulo` varchar(255) NOT NULL,
  `data_lancamento` date NOT NULL,
  `paginas` int NOT NULL,
  `id_autor` int
);

ALTER TABLE `tb_livro` ADD FOREIGN KEY (`id_autor`) REFERENCES `tb_autor` (`id`);