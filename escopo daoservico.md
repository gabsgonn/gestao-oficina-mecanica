    **Tabela:** `public.veiculo`
**Campos:** `placa`, `proprietario` (UUID referência para pessoa)

**`montarSQL()`**
```sql
SELECT placa, proprietario FROM public.veiculo
```

**`montarItem()`**
- recebe `ResultSet`
- retorna `new Veiculo(placa, pessoa)`
- o `proprietario` no banco é um UUID — você vai precisar buscar a `Pessoa` pelo id para montar o objeto

**`manter()`**
```sql
INSERT INTO public.veiculo (placa, proprietario)
VALUES (?, ?)
ON CONFLICT (placa) DO UPDATE SET
proprietario = EXCLUDED.proprietario
RETURNING *
```

**`obterLista()`**
- filtro por `placa` opcional
- se `null` traz todos

**`obterPorPlaca()`**
```sql
WHERE placa = ?
```

Um detalhe importante: o `montarItem()` do `Veiculo` é um pouco diferente do `Pessoa` — porque o `proprietario` no banco é só um UUID, mas o objeto `Veiculo` espera uma `Pessoa` completa. Você vai precisar do `IPessoa` para buscar essa pessoa pelo id.

Tenta implementar e me manda quando terminar.