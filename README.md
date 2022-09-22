<h1 align="center">AgroTech - FIAP</h1>

<img style="width: 100%" src="./git-images/Gro-Logo.svg" alt="img-profile"/>

## Para instalá-lo em sua máquina, siga os passos abaixo:

1° - Faça o git clone do projeto e use sua versão mais atualizada da branch ***master***;

2° - Rode a aplicação através da classe ***GroApplication***;

3° - Estamos utilizando o banco da Oracle da FIAP de um dos nossos integrantes para salvar nossas informações;

4° - Teste as requisições no Postman/Insomnia ou alguma ferramenta de consumo de apis de sua preferência.

##  Requisições da aplicação:

### Usuários:

---

***Comprador:***

- Criar usuário comprador - Requisição POST: **http://localhost:8080/buyers**

***Body - JSON:***

```json
{
    "name": "Alan Junior",
    "username": "alanjr1",
    "email": "alanjr@gmail.com",
    "phone": "11950236161",
    "password": "senha123",
    "cnpj": "78833461000132",
    "adress": "Rua Carlos Fernandes 123",
    "cep": "03843100"
}
```

***Vendedor:***

- Buscar um vendedor por ID - Requisição GET: **http://localhost:8080/sellers/{id}**

--- 
### Pedidos

- Buscar todos os pedidos - Requisição GET: **http://localhost:8080/orders**

- Atualizar status de pedido: Informar o ID do pedido e o código do status que está em: `br.com.projectgro.model.enums.OrderStatus`. Requisição PATCH: **http://localhost:8080/orders/id/{idPedido}/status/{idStatus}**

- Deletar um pedido - Requisição DELETE: **http://localhost:8080/orders/{id}**

---

### Categorias

- Buscar todas as categorias - Requisição GET: **http://localhost:8080/categories**
- Buscar uma categoria por id - Requisição GET: **http://localhost:8080/categories/{id}**

---

## IMPORTANTE

_Caso você queira utilizar o seu banco da FIAP, apenas troque o RM e a SENHA em ***application.properties***._

_Por padrão, ao rodar a aplicação, o banco é deletado e criado novamente. Sendo assim, todas suas alterações serão excluídas._

_Dentro de `br/com/projectgro/config/TestConfig.java`, você encontrará os ***inserts*** da aplicação que estão como testes_.

