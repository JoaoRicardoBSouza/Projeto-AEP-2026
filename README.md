Sobre o projeto

Este projeto é um sistema de denúncias de problemas públicos, onde cidadãos podem registrar ocorrências em sua cidade, como ruas esburacadas, falhas na iluminação e etc...

O que o sistema faz
  -Registrar denúncias de problemas públicos
  -Listar todas as denúncias cadastradas
  -Permitir que administradores marquem denúncias como resolvidas
Exemplos de denúncias
  -Poste de luz caído ou sem funcionamento
  -Buracos na rua
  -Problemas de infraestrutura urbana
Controle de acesso
  -Usuários comuns:
  -Podem criar e visualizar denúncias
  -Administradores (email terminando com @admin.com):
  -Possuem todas as permissões
  -Podem marcar denúncias como resolvidas
Banco de dados
  -Utiliza o banco H2 (local)
  -As tabelas são criadas automaticamente ao iniciar o sistema
Estrutura do projeto
  -Main → Interface principal (menu no console)
  -Denuncia → Classe que representa uma denúncia
  -DenunciaDAO → Operações de banco de dados (CRUD)
  -ConexaoBanco → Gerenciamento da conexão
  -TableManager → Criação das tabelas
Como executar
  -Clonar o repositório
  -Abrir em uma IDE (ex: IntelliJ ou Eclipse)
  -Executar a classe Main
