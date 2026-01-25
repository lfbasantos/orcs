# 📚 Índice do Projeto Orcs

## 🎮 Arquivos Principais

### Executáveis
- **`Orcs.jar`** (16 KB)
  - Arquivo JAR compilado e pronto para executar
  - Execute com: `java -jar Orcs.jar`
  - ⭐ Este é o arquivo que você precisa para jogar

- **`run.sh`** (274 bytes)
  - Script para executar o jogo facilmente
  - Execute com: `./run.sh`

- **`build.sh`** (1.3 KB)
  - Script para recompilar o jogo do zero
  - Execute com: `./build.sh`
  - Limpa arquivos antigos e cria novo JAR

### Código-Fonte
- **`Orcs.java`** (52 KB)
  - Código-fonte completo do jogo
  - Contém 4 classes principais:
    - `cInterage` - Interação com usuário
    - `cSalvaCarrega` - Sistema de saves
    - `cProcessaArena` - Sistema de combate
    - `Orcs` - Main loop do jogo

### Configuração
- **`MANIFEST.MF`** (75 bytes)
  - Arquivo de manifesto para o JAR
  - Define a classe principal (Main-Class: Orcs)
  - Requerido para executar com `java -jar`

- **`Makefile`**
  - Alternativa ao shell script
  - Comandos: `make build`, `make run`, `make clean`

---

## 📖 Documentação

### Para Jogadores
1. **`QUICKSTART.txt`** ⭐ 
   - Comece por aqui!
   - Instruções rápidas para jogar
   - 3 formas diferentes de executar

2. **`README.md`**
   - Visão geral do jogo
   - Características principais
   - Como compilar e jogar
   - Requisitos de sistema

### Para Desenvolvedores
3. **`DESENVOLVIMENTO.md`**
   - Guia técnico detalhado
   - Descrição de cada classe
   - Sistema de dados
   - Mecânicas do jogo
   - Como estender o código

4. **`ROADMAP.md`**
   - Plano de melhorias futuras
   - Ideias de novas features
   - Refatorações sugeridas
   - Versões planejadas

5. **`TROUBLESHOOTING.md`**
   - Solução de problemas comuns
   - Guia de debug
   - Dicas de performance
   - Checklist de diagnóstico

---

## 🗂️ Estrutura Organizacional

```
Projeto Orcs/
├── 🎮 EXECUTÁVEIS
│   ├── Orcs.jar              ← Jogue isto!
│   ├── run.sh                ← Ou isto!
│   └── build.sh              ← Recompile com isto
│
├── 💻 CÓDIGO
│   └── Orcs.java             ← Código-fonte
│
├── ⚙️ CONFIGURAÇÃO
│   ├── MANIFEST.MF           ← JAR config
│   └── Makefile              ← Make config
│
├── 📚 DOCUMENTAÇÃO
│   ├── QUICKSTART.txt        ← Comece aqui!
│   ├── README.md             ← Guia geral
│   ├── DESENVOLVIMENTO.md    ← Guia técnico
│   ├── ROADMAP.md            ← Futuro
│   ├── TROUBLESHOOTING.md    ← Problemas
│   └── INDEX.md              ← Este arquivo
│
└── 💾 SAVES (criado ao jogar)
    └── orcdata/
        ├── personagem.gamesave
        └── personagem.items
```

---

## 🚀 Primeiros Passos

### Opção A: Mais Rápido
```bash
./run.sh
```

### Opção B: Manual
```bash
java -jar Orcs.jar
```

### Opção C: Com Make
```bash
make run
```

---

## 📊 Mapa de Leitura Recomendado

### Se você quer...

**...jogar imediatamente:**
1. Leia `QUICKSTART.txt`
2. Execute `./run.sh`
3. Divirta-se!

**...entender como o jogo funciona:**
1. Leia `README.md` (visão geral)
2. Leia `DESENVOLVIMENTO.md` (técnico)
3. Explore o código `Orcs.java`

**...melhorar o jogo:**
1. Leia `DESENVOLVIMENTO.md` (estrutura)
2. Estude `Orcs.java` (implementação)
3. Escolha uma melhoria do `ROADMAP.md`
4. Implemente com `make build` para testar

**...resolver problemas:**
1. Consulte `TROUBLESHOOTING.md`
2. Execute comandos de diagnóstico
3. Recompile com `./build.sh`

---

## 📈 Tamanho dos Arquivos

| Arquivo | Tamanho | Tipo |
|---------|---------|------|
| Orcs.jar | 16 KB | Executável |
| Orcs.java | 52 KB | Código |
| DESENVOLVIMENTO.md | 3.8 KB | Documentação |
| ROADMAP.md | 4.3 KB | Documentação |
| TROUBLESHOOTING.md | 4.4 KB | Documentação |
| README.md | 1.7 KB | Documentação |
| QUICKSTART.txt | 1.1 KB | Documentação |
| build.sh | 1.3 KB | Script |
| Makefile | ~0.7 KB | Configuração |
| MANIFEST.MF | 75 B | Configuração |
| run.sh | 274 B | Script |

**Total:** ~89 KB (muito compacto!)

---

## ✅ Checklist de Setup

- [ ] Java 8+ instalado? (`java -version`)
- [ ] Arquivo `Orcs.jar` existe?
- [ ] Scripts têm permissão de execução? (`ls -la run.sh`)
- [ ] Pode ler o README.md?
- [ ] Pode executar `./run.sh`?

Se tudo estiver marcado, está pronto para jogar! 🎮

---

## 🆘 Precisa de Ajuda?

1. **Erro de compilação?** → Consulte `TROUBLESHOOTING.md`
2. **Não sabe como jogar?** → Leia `QUICKSTART.txt`
3. **Quer entender o código?** → Estude `DESENVOLVIMENTO.md`
4. **Tem ideias para melhorias?** → Veja `ROADMAP.md`

---

## 🎯 Próximos Passos

1. ✅ Você tem tudo compilado e pronto
2. 🎮 Execute `./run.sh` para jogar
3. 📖 Leia `DESENVOLVIMENTO.md` se quiser mexer no código
4. 🚀 Implemente melhorias do `ROADMAP.md`

---

**Bem-vindo ao mundo dos Orcs!** 🗡️

*Última atualização: Janeiro 2026*
