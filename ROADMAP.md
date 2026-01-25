# 🎯 Roadmap - Melhorias Futuras

Este documento lista sugestões para expandir e melhorar o jogo Orcs.

## 🔥 Melhorias Críticas (Alta Prioridade)

### 1. Limpeza de Código
- [ ] Refatorar nomes de variáveis (vNome → playerName)
- [ ] Separar em múltiplos arquivos `.java`
- [ ] Remover código comentado
- [ ] Adicionar JavaDoc aos métodos

### 2. Correções de Bugs
- [ ] Problema de limpeza de tela em Linux
- [ ] Melhorar tratamento de exceções
- [ ] Validação de entrada do usuário
- [ ] Evitar crashes com inputs inválidos

### 3. UI/UX
- [ ] Menu mais intuitivo
- [ ] Melhor formatação de texto
- [ ] Sistema de cores (ANSI)
- [ ] Status bar do personagem sempre visível

---

## 🎮 Novas Mecânicas (Médio Prazo)

### Sistema de Magia
```java
// Novo tipo de ataque para magos
- Fogo (Fire Bolt) - 1d8 de dano
- Gelo (Frost Bolt) - 1d6 + congelamento
- Curação (Heal) - Recupera HP
- Consumo de Mana por feitiço
```

### Sistema de Habilidades Especiais
```java
// Abilidades únicas por classe
Warrior: Whirlwind Attack, Shield Bash
Mage: Fireball, Teleport
Rogue: Backstab, Evasion
```

### Diferentes Tipos de Inimigos
```java
- Goblin (Fraco)
- Orc (Médio)
- Troll (Forte)
- Dragão (Chefe)
```

---

## 📖 Sistema de Quests

```java
class Quest {
    String name;
    String description;
    int rewardXP;
    int rewardGold;
    boolean completed;
    
    // Tipos de quest:
    // - Eliminate X enemies
    // - Collect X items
    // - Reach level X
    // - Defeat boss
}
```

---

## 🗺️ Sistema de Exploração

```
Mapa do Mundo:
[Taverna] -- [Floresta] -- [Caverna]
    |            |             |
[Loja] -- [Cidade Central] -- [Torre]
    |            |             |
[Cemitério] -- [Rio] -- [Castelo do Dragão]

- Cada local tem inimigos diferentes
- Inimigos mais fortes em locais afastados
- Eventos aleatórios enquanto viaja
```

---

## 👥 Sistema de Companheiros

```java
class Companion {
    String name;
    String race;
    int level;
    int health;
    int damage;
    
    void attack(Enemy enemy) { }
    void takeDamage(int dmg) { }
}

// Benefícios:
// - Mais dano em combate
// - Compartilha bônus
// - Pode morrer e precisa reviver
```

---

## 💎 Sistema de Crafting

```java
class Recipe {
    String name;
    Item[] ingredients;
    Item result;
    int exp;
}

// Exemplos:
// - Poção de Força: 2x Herb + 1x Crystal → +2 ATK
// - Armadura Melhorada: Armor + Metal → Armor +1 DEF
```

---

## 🏆 Sistema de Achievements

```java
enum Achievement {
    FIRST_VICTORY("Ganhe o primeiro combate"),
    LEVEL_10("Alcance nível 10"),
    RICH("Tenha 10.000 ouro"),
    COLLECTOR("Tenha todos os itens"),
    GODSLAYER("Derrote o Dragão")
}
```

---

## 📊 Melhorias Técnicas

### Estrutura de Classes
```
game/
├── Game.java (Main)
├── player/
│   ├── Player.java
│   ├── Character.java
│   └── Inventory.java
├── enemy/
│   ├── Enemy.java
│   ├── NPC.java
│   └── Boss.java
├── combat/
│   ├── Arena.java
│   ├── Combat.java
│   └── Damage.java
├── world/
│   ├── Map.java
│   ├── Location.java
│   └── Quest.java
├── item/
│   ├── Item.java
│   ├── Weapon.java
│   └── Armor.java
├── ui/
│   ├── Console.java
│   ├── Menu.java
│   └── Display.java
└── save/
    ├── SaveManager.java
    └── FileIO.java
```

### Padrões de Design
- [ ] Singleton para gerenciador de game
- [ ] Factory para criar inimigos
- [ ] Observer para eventos
- [ ] Strategy para diferentes tipos de combate
- [ ] MVC para separar lógica da UI

---

## 🚀 Versões Futuras

### v0.2.0 - Combat Enhanced
- Sistema de magia
- Habilidades especiais
- Inimigos diferentes

### v0.3.0 - World Expansion
- Sistema de exploração
- Múltiplos locais
- Quests

### v0.4.0 - Deep RPG
- Companheiros
- Crafting
- Achievements

### v1.0.0 - Release Completo
- Polimento final
- Balanceamento
- Documentação completa

---

## 📝 Como Contribuir

1. Escolha uma melhoria do list
2. Crie uma branch: `git checkout -b feature/sua-feature`
3. Implemente com código limpo
4. Teste bem
5. Faça pull request

---

**Última atualização:** Janeiro 2026
**Mantido por:** Comunidade Orcs
