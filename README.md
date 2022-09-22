<h1 align="center">AgroTech - FIAP</h1>

<img style="width: 100%" src="./git-images/Logo.svg" alt="img-profile"/>

## Para instalá-lo em sua máquina, siga os passos abaixo:

1° - Faça o git clone do projeto e use sua versão mais atualizada da branch "master"

2° - Rode a aplicação através da classe 'GroApplication'

3° - Estamos utilizando o banco da Oracle da FIAP de um dos nossos integrantes para salvar nossas informações!

4° - Para fazer as requisições utilize o Postman, aqui estão algumas delas:

---
***Criar usuário comprador:***

- Requisição POST - URL: **localhost:8080/buyers**

Exemplo: 

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

---

***Checar os pedidos:*** 
- Requisição GET - URL: **localhost:8080/orders**

--- 

***Atualizar status de pedido:***

- Informar o ID do pedido e o código do status que está em: `br.com.projectgro.model.enums.OrderStatus`.
- Requisição PATCH - URL: **localhost:8080/orders/id/{idPedido}/status/{idStatus}**

--- 
***Checar um vendedor por ID:***

- Requisição GET - URL: **localhost:8080/sellers/{id}**

---
**IMPORTANTE**

_Caso você queira utilizar o seu banco da FIAP, apenas troque o RM e a SENHA em ***application.properties***._

_Por padrão, ao rodar a aplicação, o banco é deletado e criado novamente. Sendo assim, todas suas alterações serão excluídas._

_Dentro de `br/com/projectgro/config/TestConfig.java`, você encontrará os ***inserts*** da aplicação que estão como testes_.

