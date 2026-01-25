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

### Opção 2: Manual
```bash
# Compilar
javac Orcs.java

# Criar o JAR
jar cfm Orcs.jar MANIFEST.MF *.class
```

---

## 🎮 Como Jogar

Após compilar, execute:
```bash
java -jar Orcs.jar
```

**Requisitos:** Java 8 ou superior

---

## 📁 Estrutura de Dados

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

## 📝 Notas

Este é um projeto em desenvolvimento. Algumas funcionalidades podem estar em fase de testes.

Para mais informações ou reportar bugs, entre em contato com o autor.
