CREATE TABLE `tb_autor` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `data_de_nascimento` date NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `mini_curriculo` varchar(255) NOT NULL
);


