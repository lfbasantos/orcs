# 🗡️ Orcs - Text-Based RPG Game

> Um jogo de RPG textual puro em Java, sem dependências complexas!

**Versão:** 0.1.6 - Alpha  
**Autor:** Luiz Filipe - lfbasantos@gmail.com

---

## 📋 Características

- 🎮 **100% Textual** - Interface puramente baseada em texto
- ⚡ **Sem Dependências** - Usa apenas Java padrão
- 🏃 **Pronto para Jogar** - Executável como JAR standalone
- ⚔️ **Sistema de Combate** - Batalhas por turnos com cálculo de dano
- 💰 **Sistema de Economia** - Ouro, itens e loja
- 📊 **Progressão** - Sistema de níveis e experiência
- 💾 **Salvamento** - Persistência de personagens

---

## 🚀 Como Compilar

### Opção 1: Script Automático (Recomendado)
```bash
./build.sh
```

### Opção 2: Make
```bash
make build
```

### Opção 3: Manual
```bash
# Compilar
mkdir -p build/classes dist
javac -d build/classes -sourcepath src/main/java $(find src/main/java -name '*.java')

# Criar o JAR
jar cfm dist/Orcs.jar MANIFEST.MF -C build/classes .
```

---

## 🎮 Como Jogar

Após compilar, execute:
```bash
./run.sh
# ou
java -jar dist/Orcs.jar
```

**Requisitos:** Java 8 ou superior

---

## 📁 Estrutura do Projeto

```
orcs/
├── src/main/java/           # Código-fonte
│   └── com/orcs/
│       ├── game/            # Classe principal (Orcs.java)
│       ├── ui/              # Interface do usuário (Console.java)
│       ├── save/            # Sistema de salvamento (SaveManager.java)
│       └── combat/          # Sistema de combate (Arena.java)
├── build/                   # Arquivos compilados (temporário)
├── dist/                    # JAR executável final
│   └── Orcs.jar            # ⭐ Jogo pronto para distribuir
├── docs/                    # Documentação completa
│   ├── DESENVOLVIMENTO.md  # Guia técnico
│   ├── ROADMAP.md          # Plano de melhorias
│   ├── TROUBLESHOOTING.md  # Resolução de problemas
│   └── ...
├── legacy/                  # Código original (backup)
├── orcdata/                 # Saves dos personagens
│   ├── *.gamesave          # Atributos
│   └── *.items             # Inventário
├── build.sh                 # Script de compilação
├── run.sh                   # Script de execução
├── Makefile                 # Build alternativo
└── README.md               # Este arquivo
```

### Estrutura de Dados

O jogo salva os dados dos personagens em `orcdata/`:
- `personagem.gamesave` - Atributos do personagem
- `personagem.items` - Inventário e equipamento

---

## 🎯 Gameplay

1. **Novo Personagem** - Crie seu Orc com classe específica
2. **Progresso** - Ganhe experiência em combate
3. **Equipamento** - Compre armas e armaduras na loja
4. **Arena** - Enfrente outros Orcs em combate
5. **Salve o Jogo** - Seu progresso é persistente

---

## 🔧 Requisitos de Sistema

- Java 8+
- 10MB de espaço em disco
- Terminal/Console para entrada/saída

---

## �️ Roadmap

### ✅ Concluído (v0.1.6)
- ✓ Refatoração completa do código
- ✓ Separação em múltiplos arquivos e pacotes
- ✓ Estrutura modular profissional
- ✓ Sistema de build automatizado
- ✓ Documentação completa

### 🔥 Próximas Implementações (Alta Prioridade)

#### v0.2.0 - Melhorias de Código
- [ ] Completar implementação da classe `Orcs.java` (lojas, taverna, arena)
- [ ] Adicionar validação de entrada do usuário
- [ ] Implementar tratamento de exceções robusto
- [ ] Melhorar sistema de cores ANSI no console
- [ ] Testes unitários básicos

#### v0.3.0 - Sistema de Magia
- [ ] Implementar feitiços para magos (Fire Bolt, Ice Bolt, Heal)
- [ ] Sistema de consumo de Mana
- [ ] Balanceamento de classes (Warrior vs Mage)
- [ ] Novas magias na loja de cajados

#### v0.4.0 - Novos Inimigos
- [ ] Tipos variados de NPCs (Goblin, Orc, Troll, Dragão)
- [ ] Dificuldade escalonada por tipo
- [ ] Boss fights com recompensas especiais
- [ ] Sistema de spawning inteligente

### 🎯 Futuro (Médio/Longo Prazo)
- Sistema de Quests e Missões
- Mapa de exploração com múltiplos locais
- Sistema de Companheiros
- Crafting e Receitas
- Achievements e conquistas
- Multiplayer local (batalhas PvP)

> 📖 Veja [docs/ROADMAP.md](docs/ROADMAP.md) para detalhes completos

---

## 📝 Notas

Este é um projeto em desenvolvimento ativo. Contribuições são bem-vindas!

Para mais informações ou reportar bugs, entre em contato com o autor.
