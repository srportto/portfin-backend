
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/srportto/portinvestimentos-pi/blob/master/LICENSE)

![PortInvestimentos](https://github.com/srportto/portinvestimentos-pi/blob/master/midia/pi-icon-png.png)
# Sobre o projeto PI



PortInvestimentos é uma aplicação back-end que fornece uma API RestFul para prover de gestão de investimentos.

A aplicação consiste em possibilitar o cadastro de clientes e a gestão de investimentos dos mesmos, registro de compra, venda e posse de ativos.

## Requisitos de negócios
- [x] Possibilitar o cadastro de clientes
- [ ] Possibilitar o registro da compra de um ativos
- [ ] Possibilitar o regitro de venda de ativos 
- [ ] Possibilitar a consulta de ativos sobre posse
- [ ] Possibilitar o calculo de preço medio de ativos 
- [ ] Possibilitar o calculo de ganho com a valorização do ativo 
- [ ] Possibilitar uma visão de carteira de ativos 
- [ ] Possibilitar a visão do tamanho em porcentagem de um ativo em carteira 
- [ ] Possibilitar o regitro de aplicações de renda variavel e fixa
- [ ] Possibilitar o regitro de pagamentos de dividendos 
- [ ] Possibilitar o regitro de pagamentos de rendimentos  
- [ ] Possibilitar o regitro de pagamentos de JCPs

## Requisitos de técnicos
- [ ] Atender requisições de pedidos de JSON e XML(Content negotiation)
- [ ] Configurar Pool de conexões para alem do "default (10)" do Spring Data Jpa
- [ ] Implementar controle de sessão
- [ ] Utilizar "Migrations" como estrategia de manutenção de banco de dados
- [ ] Criptografar senha de acesso dos clientes 
- [ ] Realizar mapeamento objeto relacional (oneToOne)
- [ ] Realizar mapeamento objeto relacional (oneToMany)
- [ ] Realizar mapeamento objeto relacional (ManyToOne)
- [ ] Realizar mapeamento objeto relacional (ManyToMany)



## Protótipo da aplicação
 ⚠ Em desenvolvimento ⚠


## Modelo conceitual
![Modelo Conceitual](https://github.com/srportto/portbank/blob/main/outros/modelagem.jpeg)

# Tecnologias utilizadas
## Back end
- Java 11 via OpenJDK
  - Herança
  - Polimorfismo
  - Interface
  - Generics
  - Override
  - Sobrecargas 
  - Manipulação de datas
  - Manipulação de Strings  
  - Manipulação de arrays
  - Manipulação de collections    
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA  
- JPA / Hibernate
- Maven
- FlayWay (migrations)
- JSON
- MySql  
- Intellij IDE
- Lombok

## Front end
- Não há 

## Implantação em produção
- [ ] Deploy na AWS 


# Como executar o projeto

## Back end
Pré-requisitos: 
* Java 11
* MySql server 8.0
* MySql Workbench 8.0 CE


```bash
# clonar repositório
git clone https://github.com/srportto/portbank.git

# entrar na pasta do projeto back end
cd portbank

# executar o projeto
./mvnw spring-boot:run
```



# Autor

Caique SilVa Porto

https://www.linkedin.com/in/caique-porto-26b50586/
