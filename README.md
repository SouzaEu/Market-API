# Medieval Market API

## Sobre o projeto

A **Medieval Market API** foi desenvolvida para simular um marketplace de fantasia, permitindo a criação, atualização, listagem e exclusão de personagens e itens mágicos. Também conta com filtros de busca avançados para facilitar a gestão dos recursos.

---

## Endpoints

### Personagens

| Método | Rota | Ação |
|:------:|:----:|:-----:|
| GET | `/api/characters` | Listar todos os personagens (paginado) |
| GET | `/api/characters/{id}` | Buscar personagem por ID |
| POST | `/api/characters` | Criar um novo personagem |
| PUT | `/api/characters/{id}` | Atualizar um personagem |
| DELETE | `/api/characters/{id}` | Deletar um personagem |
| GET | `/api/characters/search/name?name={name}` | Buscar personagem pelo nome |
| GET | `/api/characters/search/class?characterClass={class}` | Buscar personagem pela classe |

### Itens

| Método | Rota | Ação |
|:------:|:----:|:-----:|
| GET | `/api/items` | Listar todos os itens (paginado) |
| GET | `/api/items/{id}` | Buscar item por ID |
| POST | `/api/items` | Criar um novo item |
| PUT | `/api/items/{id}` | Atualizar um item |
| DELETE | `/api/items/{id}` | Deletar um item |
| GET | `/api/items/search/name?name={name}` | Buscar item pelo nome |
| GET | `/api/items/search/type?type={type}` | Buscar item pelo tipo |
| GET | `/api/items/search/rarity?rarity={rarity}` | Buscar item pela raridade |
| GET | `/api/items/search/price?minPrice={min}&maxPrice={max}` | Buscar itens por faixa de preço |
| GET | `/api/items/owner/{ownerId}` | Listar itens de um personagem |

---

## Modelos de Dados

### Personagem
- **id**: Long
- **name**: String (obrigatório)
- **characterClass**: Enum (`WARRIOR`, `MAGE`, `ARCHER`)
- **level**: int (1 a 99)
- **coins**: int (≥ 0)
- **items**: Lista de Itens

### Item
- **id**: Long
- **name**: String (obrigatório)
- **type**: Enum (`WEAPON`, `ARMOR`, `POTION`, `ACCESSORY`)
- **rarity**: Enum (`COMMON`, `RARE`, `EPIC`, `LEGENDARY`)
- **price**: int (≥ 0)
- **owner**: Personagem

---

## Como rodar o projeto

1. Clone o repositório:

```bash
git clone https://github.com/SouzaEu/Market-API.git
```

2. Acesse a pasta do projeto:

```bash
cd Market-API
```

3. Compile e rode a aplicação:

```bash
mvn clean install
mvn spring-boot:run
```

4. Acesse a H2 Console (opcional para visualizar o banco):

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`

5. A API estará disponível em:

```bash
http://localhost:8080/api/
```

---

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.1.0
- Spring Data JPA
- H2 Database
- Lombok
- Maven

---

> Projeto feito para prática e aprendizado, inspirado em universos de fantasia medieval.

---

Feito com ❤️ por [SouzaEu](https://github.com/SouzaEu)
