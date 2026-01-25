# 📚 Guia de Desenvolvimento - Orcs Game

## Estrutura do Projeto

```
/workspaces/orcs/
├── Orcs.java              # Código-fonte principal
├── MANIFEST.MF            # Manifesto para o JAR
├── build.sh               # Script de compilação
├── run.sh                 # Script de execução
├── Orcs.jar               # Arquivo JAR executável (gerado)
├── orcdata/               # Diretório de saves (criado automaticamente)
└── README.md              # Documentação
```

## Classes Principais

### 1. **cInterage**
Responsável pela interação com o usuário via console.
- `mFala()` - Exibe mensagem
- `mPergunta()` - Faz pergunta ao usuário
- `mPausa()` - Aguarda ENTER
- `mLimpa()` - Limpa a tela

### 2. **cSalvaCarrega**
Sistema de persistência de dados.
- `mSalvar()` - Salva personagem
- `mCarregar()` - Carrega personagem
- `mInicializaBau()` - Cria inventário inicial
- `mCarregarBau()` - Lista itens
- `mVenderItem()` - Vende item na loja
- `mEquipaItem()` - Equipa item

### 3. **cProcessaArena**
Gerencia o sistema de combate.
- `mDados()` - Gera número aleatório para dados
- `mNpcH()` - Define habilidade do NPC
- `mNpcE()` - Define energia do NPC
- `mNpcGold()` - Define ouro do NPC
- `mNpcXP()` - Define XP do NPC
- `mAtaqueArena()` - Processa combate

### 4. **Orcs** (Main)
Classe principal com o loop do jogo.

---

## Como Compilar e Executar

### Compilação Rápida
```bash
./build.sh
```

### Execução
```bash
./run.sh
```

### Manual (Alternativo)
```bash
# Compilar
javac Orcs.java

# Empacotar
jar cfm Orcs.jar MANIFEST.MF *.class

# Executar
java -jar Orcs.jar
```

---

## Sistema de Dados

### Arquivo de Personagem (`.gamesave`)
Formato: `HPmax;HPcurrent;XP;Level;Gold;Potions;Class;Mana`

Exemplo:
```
20;18;1500;2;500;3;Warrior;40
```

### Arquivo de Inventário (`.items`)
Formato: `ID;Type;Bonus1;Bonus2;Price;Equipped;Description`

Exemplo:
```
1;Arma;1;1;100;1;Faca Pequena Atk 1 Dano 1 $100
2;Set;0;0;50;0;Roupas Simples Def 0 Absorve 0 $50
```

---

## Mecânicas do Jogo

### Sistema de Combate
1. **Ataque vs Defesa**: Compara força de ataque do jogador contra defesa do inimigo
2. **Cálculo de Dano**: `1d6 + bônus de arma`
3. **Absorção**: Armadura reduz dano recebido
4. **Turnos**: Jogador ataca, depois o inimigo
5. **Poções**: Recuperam HP quando perde em combate

### Progressão
- **XP por Combate**: Variável por nível do inimigo
- **Níveis**: Calculados por XP acumulado
- **Habilidade**: Aumenta com nível
- **HP Máximo**: 20 × Nível × 2
- **Mana**: 10 × Nível + 20 × Nível

### Loja
- Compra de armas e armaduras
- Venda de itens excedentes
- Ouro como moeda

---

## Como Estender o Jogo

### Adicionar Nova Classe de Personagem
1. Edite `cSalvaCarrega.mInicializaBau()` para itens iniciais
2. Modifique `Orcs.main()` para oferecer nova classe
3. Ajuste bônus no `cProcessaArena`

### Adicionar Novas Armas/Armaduras
1. Edite o arquivo `.items` manualmente ou
2. Adicione method em `cSalvaCarrega` para inserir novos itens

### Melhorar o Sistema de Combate
1. Edite `cProcessaArena.mAtaqueArena()`
2. Adicione criticals, habilidades especiais, etc.

---

## Troubleshooting

### "Orcs.jar não encontrado"
Execute `./build.sh` primeiro.

### "Diretório orcdata não pode ser criado"
Verifique permissões de escrita no diretório do projeto.

### Jogo fecha abruptamente
Possível problema com leitura de console. Tente:
```bash
java -cp Orcs.jar Orcs
```

---

## Próximas Melhorias Sugeridas

- [ ] Sistema de magia/feitiços
- [ ] Diferentes tipos de inimigos
- [ ] Quests/Missões
- [ ] Sistema de companheiros
- [ ] Mapa/Exploração
- [ ] Mais classes de personagens
- [ ] Sistema de respawnear itens

---

**Versão:** 0.1.6 - Alpha  
**Última atualização:** Janeiro 2026
