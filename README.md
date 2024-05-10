# Status : 
Parcialmente concluído, será anexado os testes de unidade em breve. att 10/05/2024

# Meu objetivo ao desenvolver esse desafio :
Meu objetivo principal é colocar em prática meus conhecimentos sobre arquitetura e melhores práticas. Estou focado em aprimorar minhas habilidades na organização do versionamento de código no GitHub, trabalhando com branches e pull requests de maneira eficaz. Isso inclui:

- Aplicar princípios de design e arquitetura de software para construir aplicações robustas e escaláveis.
- Utilizar as melhores práticas de desenvolvimento e codificação para garantir código limpo e bem organizado.
- Gerenciar eficientemente o versionamento de código usando branches e pull requests, visando melhor colaboração e integração contínua.


# Sobre o desafio da UOL
## Teste para BackEnd para UOL HOST
Montamos este teste para conhecer seus conhecimentos e habilidades em linguagem Java, programação orientada a objetos e boas práticas de programação.

O teste consiste em montar uma aplicação Java capaz de recuperar informações de um arquivo XML e de um arquivo JSON, persistir um cadastro em um banco de dados em memória ou em arquivo e listar os cadastros em uma interface simples.

## Proposta
O 'novo' sistema de cadastro de jogadores do UOL precisa de uma nova cara! Isso porque a área de lazer da empresa definiu que todo jogador deverá ter um codinome. A proposta foi um sucesso e muitos candidatos se inscreveram, por isso a área de lazer acabou restringindo os codinomes em duas listas distintas: "Os Vingadores" e "A Liga da Justiça".

Seu desafio é elaborar um sistema em Java capaz de:

1. Permitir o cadastro de jogadores de acordo com os codinomes contidos nos links de referência `vingadores.json` e `liga_da_justica.xml`.
2. Apresentar um cadastro contendo nome, e-mail e telefone do jogador (sendo que nome e e-mail são obrigatórios).
3. Persistir a informação cadastrada em um banco de dados em memória, como HSQLDB ou arquivo.
4. Obter, a qualquer momento, a lista de todos os jogadores cadastrados com seus respectivos codinomes e também a informação de qual lista o codinome foi extraído.
5. Impedir a utilização de um mesmo codinome para diferentes usuários (a menos que o codinome seja para listas diferentes).
6. Incluir o codinome escolhido dentro das listas Os Vingadores ou A Liga da Justiça.
7. Obrigatoriamente, ler a informação do codinome em arquivos na internet (links abaixo). Atenção: não vale guardar a informação do codinome localmente (em um arquivo, em uma classe, em um banco de dados etc.


## Arquitetura de referência
![image](https://github.com/im2back/Uol-Challenger/assets/117541466/6843832e-bee6-4685-af40-46c561a28485)


## Instruções
Não há certo ou errado. Queremos apenas saber mais sobre seus conhecimentos na linguagem Java, como uso de bibliotecas públicas, e também seu cuidado com o código fonte, levando em consideração clareza de ideias, organização de código, documentação e testes.

Faça um clone deste projeto, crie um novo projeto no seu próprio GitHub e siga os seguintes passos:

1. Faça um clone do projeto [https://github.com/uolhost/test-backEnd-Java.git](https://github.com/uolhost/test-backEnd-Java.git)
2. Crie um novo projeto dentro do seu GitHub ([https://github.com](https://github.com))
3. Desenvolva um sistema que atenda os casos de uso apresentados.
4. Para montar seu sistema, leve em consideração a arquitetura de referência dentro da pasta `referência`.
5. Criar uma interface em HTML que contenha um formulário para receber nome, e-mail e telefone.
6. Criar uma interface em HTML que liste os jogadores cadastrados por nome, e-mail, telefone, codinome e lista de referência.
7. Criar uma ou mais classes que faça(m) uma requisição HTTP para o arquivo referência “Liga da Justiça” em: [https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml](https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml)
8. Criar uma ou mais classes que faça(m) uma requisição HTTP para o arquivo referência “Os Vingadores” em: [https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json](https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json)
9. Criar uma ou mais classes que contenha(m) as regras para persistir e recuperar cadastros de jogadores.
10. Documente como o projeto deve ser iniciado para que possamos rodar sua aplicação.
11. Suba a sua proposta para o projeto que você criou no GitHub. Exemplo: [https://github.com/seuNome](https://github.com/seuNome)
12. Envie-nos o link do GitHub do seu projeto para podermos realizar o download. Exemplo: [https://github.com/seuNome/test-backEnd-Java.git](https://github.com/seuNome/test-backEnd-Java.git)




## Regras
Você poderá utilizar o Java em qualquer versão :)
Você poderá utilizar quaisquer frameworks da linguagem Java :)
Para persistir as informações, você poderá utilizar um banco de dados em memória gerenciado por você ou utilizar um banco, como HSQLDB.
Você também pode optar por gravar em arquivo.
Não vale utilizar o codinome de um mesmo arquivo mais de uma vez.
Detalhes como criação de testes unitários, ordenação da lista de cadastrados ou filtro da lista são opcionais. Mas, se você fizer iremos apreciar! =)


## O que apreciamos
- Organização
- Simplicidade
- Objetividade
- Reúso de código
- Testes unitários
- Padronização de código
- Padrões de projeto

## Quem buscamos
Queremos uma pessoa que goste do que faz, trabalhe em equipe e tenha vontade de inovar, buscando sempre atualização e soluções inovadoras.

Se você se identificou, venha fazer parte do nosso time!

