**Tabela:** `gabrielgon.servico`
**Campos:** `numero`, `descricao`, `data_inicio`, `data_finalizacao`, `valor`, `placa_veiculo`

**`montarSQL()`**

```sql
SELECT numero, descricao, data_inicio, data_finalizacao, valor, placa_veiculo
FROM gabrielgon.servico
```

**`montarItem()`**

- recebe `ResultSet`
- `data_inicio` e `data_finalizacao` → `rs.getObject("data_inicio", LocalDateTime.class)`
- `valor` → `rs.getBigDecimal("valor")`
- `placa_veiculo` → busca o `Veiculo` pelo `daoVeiculo.obterPorPlaca()`
- retorna `new Servico(numero, descricao, dataInicio, veiculo)`
- depois seta `dataFinalizacao` e `valor` se não forem nulos

**`manter()`**

```sql
INSERT INTO gabrielgon.servico (descricao, data_inicio, data_finalizacao, valor, placa_veiculo)
VALUES (?, ?, ?, ?, ?)
ON CONFLICT (numero) DO UPDATE SET
descricao = EXCLUDED.descricao,
data_finalizacao = EXCLUDED.data_finalizacao,
valor = EXCLUDED.valor
RETURNING *
```

**`obterLista()`**

- filtro por `placaVeiculo` opcional
- `WHERE placa_veiculo = ?`

**`obterPorId()`**

- `WHERE numero = ?`
- `numero` é `long` — usa `cmd.setLong(1, numero)`

**Construtor** vai precisar do `IVeiculo` igual o `VeiculoDao` precisou do `IPessoa`:

```java
public ServicoDao(Connection conexao, IVeiculo daoVeiculo) {
    this.conexao = conexao;
    this.daoVeiculo = daoVeiculo;
}
```

E na `DaoFabrica`:

```java
public static IServico criarServico(Conexao conexao) {
    if ("postgresql".equalsIgnoreCase(conexao.getProvedor())) {
        IVeiculo daoVeiculo = criarVeiculo(conexao);
        return new ServicoDao(conexao.getConnection(), daoVeiculo);
    }
    return null;
}
```
