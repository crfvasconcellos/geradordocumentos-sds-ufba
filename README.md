# Gerador de Documentos PDF - SDS UFBA

Aplicação completa para preenchimento automático de modelos Word (`.docx`) e conversão/download instantâneo em **PDF**.

---

## Como Usar (Para Usuários Não Desenvolvedores)

### Requisito Único:
Ter o **Java (versão 17 ou superior)** instalado no computador.

### Passo a Passo:
1. Dê **dois cliques** no arquivo **`iniciar.bat`**.
2. A aplicação iniciará automaticamente e abrirá o navegador na página do gerador (`http://localhost:8080`).
3. Preencha o **Nome da Pessoa** e o **Nome do Campeonato**.
4. Clicar em **Enviar e Baixar PDF**. O arquivo PDF com a data de hoje formatada será baixado na hora!

---

## Estrutura do Projeto

- **`GeradorDocumentos.jar`**: Executável único compilado contendo a aplicação backend Spring Boot e a interface frontend integradas.
- **`iniciar.bat`**: Script para iniciar a aplicação e abrir o navegador em 1 clique.
- **`backend/reeditor/`**: Código-fonte Spring Boot (Java, Maven, Apache POI e conversor PDF).
- **`frontend/static/`**: Arquivos da interface de usuário (HTML, CSS e JS).
