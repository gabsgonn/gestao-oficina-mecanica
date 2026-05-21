HTTP Request → Servlet → Negocio → Dao → Banco
HTTP Response ← Servlet ← Negocio ← Dao ←

---

modulos a adicionar no pom

<dependency>
    <groupId>com.nsinova.oficina</groupId>
    <artifactId>negocio</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
<dependency>
    <groupId>com.nsinova.oficina</groupId>
    <artifactId>modulo</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
<dependency>
    <groupId>com.nsinova.oficina</groupId>
    <artifactId>conexao</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>

# +

<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
    <scope>provided</scope>
</dependency>

---
