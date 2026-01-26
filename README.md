# 🗡️ Orcs - Text-Based RPG Game

> Um jogo de RPG textual puro em Java, sem dependências complexas!

**Versão:** 0.2.0 - Alpha  
**Autor:** Luiz Filipe - lfbasantos@gmail.com

---

## 📋 Características

- 🎮 **100% Textual** - Interface puramente baseada em texto
- ⚡ **Sem Dependências** - Usa apenas Java padrão
- 🏃 **Pronto para Jogar** - Executável como JAR standalone
- ⚔️ **Sistema de Combate** - Batalhas por turnos e modo rampage
- 🏪 **Sistema de Lojas** - 3 lojas diferentes (Itens, Armas, Magias)
- 🍺 **Taverna** - Recupere HP/MP com bebidas
- 💰 **Sistema de Economia** - Ouro, compra/venda de itens
- 📊 **Progressão** - Sistema de níveis e experiência com XP automático
- 🎒 **Baú Completo** - Visualize, equipe e venda itens
- 💾 **Salvamento** - Persistência automática de personagens

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

### Funcionalidades Completas
1. **Criar Personagem** - Escolha entre Guerreiro ou Mago
2. **Loja de Itens** - Compre poções de HP (50 gold)
3. **Loja de Armas & Armaduras** - Equipamentos para guerreiros
4. **Loja de Mantos & Magias** - Equipamentos para magos
5. **Taverna** - Recupere HP/MP com Cerveja, Vinho ou Chá de Cogumelo
6. **Ver Baú** - Visualize, equipe ou venda seus itens
7. **Arena** - Dois modos de combate:
   - **Ataque Normal**: Combate turno a turno detalhado
   - **Rampage**: Combate contínuo até derrota
8. **Sistema de Níveis** - Ganhe XP, suba de nível automaticamente
9. **Auto-Save** - Progresso salvo após cada ação importante

---

## 🔧 Requisitos de Sistema

- Java 8+
- 10MB de espaço em disco
- Terminal/Console para entrada/saída

---

## 🛠️ Roadmap

### ✅ v0.2.0 - CONCLUÍDO (Janeiro 2026)
- ✓ Sistema completo de Loja de Itens (poções)
- ✓ Taverna com recuperação de HP/MP (3 bebidas)
- ✓ Loja de Armas & Armaduras (4 itens)
- ✓ Loja de Mantos & Magias (4 itens)
- ✓ Arena com Modo Normal e Rampage
- ✓ Sistema de combate com bônus de equipamento
- ✓ Auto-consumo de poções em emergência
- ✓ Geração dinâmica de NPCs por nível
- ✓ Sistema de baú completo (ver, equipar, vender)

### 🚧 v0.3.0 - Melhorias de Qualidade (Próximo)
- [ ] Validação de entrada em todos os menus
- [ ] Tratamento robusto de erros de arquivo
- [ ] Sistema de cores ANSI no console
- [ ] Confirmações para ações irreversíveis
- [ ] Sistema de ajuda contextual
- [ ] Feedback visual de progresso
- [ ] Testes unitários básicos

### 🎯 v0.4.0 - Sistema de Magia Avançado
- [ ] Feitiços para magos (Fire Bolt, Ice Bolt, Heal)
- [ ] Sistema de consumo de Mana otimizado
- [ ] Balanceamento de classes (Warrior vs Mage)
- [ ] Novas magias na loja de cajados

### 🔮 Futuro (Médio/Longo Prazo)
- Sistema de Quests e Missões
- Tipos variados de NPCs (Goblin, Troll, Dragão)
- Boss fights com recompensas especiais
- Mapa de exploração com múltiplos locais
- Sistema de Crafting e Receitas
- Achievements e conquistas

> 📖 Veja [docs/ROADMAP.md](docs/ROADMAP.md) para detalhes completos

---

## 📝 Notas

Este é um projeto em desenvolvimento ativo. Contribuições são bem-vindas!

Para mais informações ou reportar bugs, entre em contato com o autor.
