<p align="center">
  <img src="https://raw.githubusercontent.com/EricEOL/suambank/main/readme/suambank.png" />
</p>

O Suambank é um sistema bancário que realiza desde as operações básicas de saque, depósito e transferência, até a aquisição de empréstimos.
Com as transações bancárias registradas, é possível ver o saldo atual e extrato bancário com todas as transações realizadas pela conta.

*<strong>Obs:</strong> O SUAMBANK foi criado como projeto final do Projeto Integrador II do curso de Análise e Desenvolvimento de Sistemas da UNISUAM - Centro Universitário Augusto Motta.*

## Tecnologias

Tecnologias utilizadas nesse projeto:

* Java
* Spring Boot
* PostgreSQL
* Lombok
* JUnit5
* SpringDoc OpenAPI
* Java Mail
* Maven

## Rodando o projeto

Para utilizar o projeto em sua máquina é necessário seguir os seguintes passos:
1) Ter o Java instalado corretamente.
2) Clonar este repositório.
3) Ter o PostgreSQL instalado. (Obs: atentar-se para a porta utilizada pelo seu PostgreSQL - o projeto está direcionando para a porta 5433, que não é a porta padrão de instalação. Se a porta utilizada for outra, faça a alteração no arquivo application.properties em spring.datasource.url)
4) Dentro do PostgreSQL é necessário criar o database <strong>suambank</strong>.
5) Preste atenção nesse ponto, foi adicionada a funcionalidade de envio de email de notificação ao realizar uma transação do tipo transferência. Por conta disso, o aquivo application.properties não foi adicionado a esse projeto, no lugar dele foi colocado um application-dev.properties. Faça os seguintes passos:
   1) Renomeie o arquivo para application.properties
   2) Na propriedade spring.mail.username deve ser adicionado um email do outlook
   3) Na propriedade spring.mail.password deve ser adicionado a senha do email
6) Entrar na pasta do projeto pelo terminal e executar o seguinte comando:
<pre>
mvn spring-boot:run
</pre>
7) <strong>Agora seu projeto estará rodando e suas rotas podem ser acessadas pelos controllers.</strong> (Obs: Observe os controllers para saber que rotas utilizar)

### Observação sobre as rotas
Para facilitar o entendimento das rotas, após inicializar sua aplicação, acesse <strong>"/swagger-ui/index.html"</strong>, que será possível visualizar todas as rotas da aplicação, verificar os parâmetros necessários, os schemas das entidades e inclusive enviar dados para o database por lá mesmo.

## Ordem de criação das entidades
1) Crie primeiramente um Banco (Bank). | Rota POST para "/bank".
2) Crie um cliente (Client). | Rota POST para "/client".
3) Crie uma conta (Account) que pode ser corrente ou poupança. | Conta Corrente - Rota POST para "/account/checkingAccount" OU Conta Poupança - Rota POST para "/account/savingsAccount".
4) Realize um depósito por meio da Rota POST para "/transaction/deposit". Com o depósito realizado, haverá valor no saldo para realização das outras operações financeiras.

## Funcionalidades principais

* Saque
* Depósito
* Transferência
    * PIX
    * DOC
    * TED
* Empréstimos

## Interfaces
As interfaces estão em desenvolvimento, a imagem abaixo é uma ilustração criada no Figma.

<p align="center">
  <img src="https://raw.githubusercontent.com/EricEOL/suambank/main/readme/screenssuambank.png" />
</p>

## Em construção

- [ ] Funcionalidade de empréstimos bancários
- [ ] Front-end