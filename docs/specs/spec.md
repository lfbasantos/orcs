# Especificações Funcionais - Orcs v0.3.0

**Versão:** 0.3.0  
**Data:** Janeiro 2026  
**Status:** 🚧 EM DESENVOLVIMENTO

---

## Resumo de Implementação

### ✅ Funcionalidades Completas (v0.2.0)
As seguintes funcionalidades já estão **implementadas e funcionando**:

- **Sistema de Lojas**: Loja de Itens, Armas & Armaduras, Mantos & Magias
- **Taverna**: Recuperação de HP/MP com 3 tipos de bebidas
- **Arena**: Modo Ataque Normal e Modo Rampage com combate completo
- **Baú**: Visualização, equipar e vender itens
- **Sistema de Níveis**: Cálculo automático de XP e level-up
- **Persistência**: Salvamento automático após transações
- **Combate**: Cálculo de ataque/defesa/dano com bônus de equipamento

### 🎯 Foco da v0.3.0
Esta versão foca em **melhorias de qualidade, validações e experiência do usuário**:

| Categoria | Requisitos | Prioridade |
|-----------|------------|------------|
| Validação de Entrada | FR10-FR12 | 🔴 Alta |
| Tratamento de Erros | FR13-FR14 | 🔴 Alta |
| Interface Visual | FR15-FR17 | 🟡 Média |
| Experiência do Usuário | FR18-FR20 | 🟢 Baixa |

---

## 1. Escopo

Este documento descreve os requisitos funcionais **pendentes** para a versão 0.3.0 do jogo Orcs, focando em validações, tratamento de erros, interface visual e experiência do usuário.

---

## 2. Requisitos Funcionais Pendentes

### 2.1. Validação de Entrada

#### FR10: Validação de Opções de Menu
**Descrição:** O sistema deve validar todas as entradas do usuário nos menus.

**Comportamento Esperado:**
- Quando jogador digita opção inválida:
  - Mensagem clara "Opção inválida" é exibida
  - Menu é reexibido automaticamente
  - Jogador pode tentar novamente
- Opções aceitas são claramente indicadas (ex: [1], [2], [X])
- Entrada case-insensitive quando apropriado (X = x)

**Critérios de Aceitação:**
- Sistema não quebra com entrada inválida
- Feedback imediato ao usuário
- Menu permanece acessível após erro

---

#### FR11: Validação de Nome de Personagem
**Descrição:** O sistema deve validar o nome do personagem ao criar ou carregar.

**Comportamento Esperado:**
- Ao criar personagem:
  - Nome não pode ser vazio
  - Nome deve ter entre 3 e 20 caracteres
  - Apenas letras, números e espaços são permitidos
  - Se inválido, mensagem explicativa é exibida e nome é solicitado novamente
- Ao carregar personagem:
  - Se arquivo não existir, mensagem clara é exibida
  - Jogador retorna ao menu principal
  - Não ocorre erro de sistema

**Critérios de Aceitação:**
- Nomes válidos são aceitos
- Nomes inválidos são rejeitados com explicação
- Sistema não quebra com nomes problemáticos

---

#### FR12: Validação de Transações Comerciais
**Descrição:** O sistema deve validar todas as transações de compra e venda.

**Comportamento Esperado:**
- Compras:
  - Verificar saldo suficiente antes de confirmar
  - IDs de itens devem existir no catálogo
  - Classe do jogador deve permitir o item (guerreiro vs mago)
- Vendas:
  - Item deve existir no baú
  - ID deve ser válido
  - Confirmação antes de remover item permanentemente
- Em qualquer erro:
  - Transação é cancelada
  - Estado do jogo permanece inalterado
  - Mensagem clara do motivo

**Critérios de Aceitação:**
- Impossível comprar sem ouro
- Impossível vender item inexistente
- Impossível equipar item de classe errada
- Estado consistente após erro

---

### 2.6. Tratamento de Erros

#### FR13: Recuperação de Erros de Arquivo
**Descrição:** O sistema deve lidar graciosamente com problemas de leitura/escrita de arquivos.

**Comportamento Esperado:**
- Se diretório orcdata não puder ser criado:
  - Mensagem clara explicando o problema
  - Sugestão de criar manualmente
  - Jogo não fecha abruptamente
- Se save corrompido for detectado:
  - Mensagem informando corrupção
  - Opção de criar novo personagem
  - Save corrompido não é sobrescrito
- Se disco cheio durante salvamento:
  - Mensagem de erro clara
  - Estado anterior preservado
  - Jogador pode tentar novamente

**Critérios de Aceitação:**
- Nenhum erro de arquivo causa crash
- Mensagens sempre explicativas
- Dados não são perdidos silenciosamente

---

#### FR14: Proteção Contra Estado Inválido
**Descrição:** O sistema deve prevenir e detectar estados de jogo inválidos.

**Comportamento Esperado:**
- HP negativo:
  - Automaticamente ajustado para 0
  - Sistema de poções ativado se disponível
- Ouro negativo:
  - Impossível gastar mais que possui
  - Validação antes de dedução
- Level inconsistente com XP:
  - Recalculado automaticamente ao carregar
  - Sincronizado após cada ganho de XP
- Equipamento incompatível:
  - Detectado ao carregar
  - Desequipado automaticamente
  - Mensagem informativa ao jogador

**Critérios de Aceitação:**
- Estado sempre consistente
- Recuperação automática quando possível
- Usuário informado de ajustes

---

### 2.7. Interface Visual

#### FR15: Sistema de Cores por Contexto
**Descrição:** Diferentes tipos de mensagens devem ter cores distintas para melhor legibilidade.

**Comportamento Esperado:**
- Cores aplicadas consistentemente:
  - **Verde**: Mensagens de sucesso (compra realizada, nível aumentou, vitória)
  - **Vermelho**: Mensagens de erro (dinheiro insuficiente, opção inválida, derrota)
  - **Amarelo**: Avisos e informações importantes (HP baixo, última poção)
  - **Azul**: Status e estatísticas (HP, MP, Gold, Level)
  - **Branco**: Texto normal e menus
- Cores funcionam em terminais comuns (Linux, macOS, Windows)
- Se terminal não suporta cores, texto permanece legível

**Critérios de Aceitação:**
- Cores aplicadas em todos os contextos apropriados
- Contraste adequado para leitura
- Fallback funcional sem cores

---

#### FR16: Formatação de Interface
**Descrição:** Menus e informações devem ser bem formatados e organizados.

**Comportamento Esperado:**
- Menus têm:
  - Título claro separado visualmente
  - Opções numeradas e alinhadas
  - Linha separadora entre seções
  - Prompt de entrada visível
- Status do personagem exibido consistentemente:
  - Sempre na mesma ordem
  - Valores alinhados
  - Unidades claras (gold, HP, etc.)
- Mensagens de combate:
  - Ação clara
  - Resultado imediato
  - Espaçamento adequado entre turnos

**Critérios de Aceitação:**
- Interface limpa e organizada
- Fácil identificar informações importantes
- Experiência consistente em todo o jogo

---

#### FR17: Indicadores Visuais de Estado
**Descrição:** O jogador deve ter feedback visual constante sobre estados críticos.

**Comportamento Esperado:**
- Alertas automáticos quando:
  - HP ≤ 20% do máximo: Aviso em vermelho
  - Sem poções: Aviso ao entrar em combate
  - Nível aumentado: Mensagem destacada com novo level
  - Conquista desbloqueada: Notificação especial
- Durante combate:
  - Barra de status sempre visível
  - Mudanças de HP destacadas
  - Efeitos de equipamento indicados
- Transições de estado claras (de vivo para derrotado, de nível X para X+1)

**Critérios de Aceitação:**
- Jogador nunca perde informação crítica
- Feedback imediato a ações importantes
- Estados visuais distintos

---

### 2.8. Experiência do Usuário

#### FR18: Confirmações de Ações Irreversíveis
**Descrição:** Ações importantes devem ter confirmação antes de execução.

**Comportamento Esperado:**
- Requerem confirmação:
  - Vender item (não pode ser desfeito)
  - Sair do jogo com progresso não salvo
  - Sobrescrever save existente
- Confirmação mostra:
  - Descrição clara da ação
  - Consequências
  - Opções [S]im / [N]ão
- "Cancelar" sempre retorna ao estado anterior sem mudanças

**Critérios de Aceitação:**
- Usuário não perde itens acidentalmente
- Sempre possível cancelar
- Mensagens de confirmação claras

---

#### FR19: Sistema de Ajuda Contextual
**Descrição:** Jogador deve ter acesso a ajuda sobre mecânicas do jogo.

**Comportamento Esperado:**
- Opção [?] ou [H] em menus principais exibe:
  - Como funciona aquela seção
  - Dicas úteis
  - Atalhos disponíveis
- Primeira vez em cada seção:
  - Tutorial breve automático (pode ser pulado)
  - Dicas para iniciantes
- Glossário de termos acessível no menu principal

**Critérios de Aceitação:**
- Ajuda disponível em contextos principais
- Informações corretas e úteis
- Não intrusiva para jogadores experientes

---

#### FR20: Feedback de Progresso
**Descrição:** O jogador deve ver claramente seu progresso no jogo.

**Comportamento Esperado:**
- Após cada combate:
  - Resumo de XP ganho
  - Ouro obtido
  - Progresso para próximo nível (barra ou %)
- No menu principal:
  - Estatísticas gerais visíveis
  - Total de vitórias
  - Tempo jogado (se possível)
- Ao aumentar de nível:
  - Celebração visual
  - Novos atributos destacados
  - Recompensas mencionadas

**Critérios de Aceitação:**
- Progresso sempre visível
- Metas claras para jogador
- Sensação de avanço constante

---

## 3. Regras de Negócio

### RN01: Sistema de Níveis
- Level é calculado exclusivamente por XP total acumulado
- XP nunca diminui
- Atributos aumentam automaticamente com level

### RN02: Limite de Recursos por Nível
- HP máximo = Level × 20
- MP máximo (magos) = Level × 30
- Equipamento tem requisitos de level mínimo

### RN03: Economia
- Preço de venda = 50% do preço de compra
- Ouro não pode ser negativo
- Transações são atômicas (tudo ou nada)

### RN04: Combate
- Apenas um combate ativo por vez
- NPC é gerado baseado no level do jogador
- Recompensas proporcionais ao level do NPC

### RN05: Persistência
- Salvamento automático após cada mudança significativa
- Um save por personagem (nome único)
- Formato de save retrocompatível

---

## 4. Critérios de Qualidade

### 4.1. Usabilidade
- Tempo de resposta < 1 segundo para ações locais
- Menus intuitivos, sem necessidade de manual
- Feedback imediato a todas as ações

### 4.2. Confiabilidade
- Zero crashes por input de usuário
- Dados sempre consistentes
- Recuperação automática de erros não-críticos

### 4.3. Manutenibilidade
- Código modular e bem documentado
- Testes cobrindo cenários principais
- Logs de erro informativos para debug

---

## 5. Casos de Uso Principais

### Caso de Uso 1: Jogador Novo
1. Inicia jogo
2. Cria personagem com nome e classe
3. Recebe equipamento inicial
4. Explora taverna para entender mecânica
5. Entra na arena e vence primeiro combate
6. Compra equipamento melhor com ouro ganho
7. Progride e sobe de nível

### Caso de Uso 2: Jogador Retornando
1. Carrega save existente
2. Verifica status atual
3. Vai à taverna recuperar HP
4. Equipa melhor arma do baú
5. Entra no modo rampage
6. Joga até morrer ou cansar
7. Salva e sai

### Caso de Uso 3: Recuperação de Erro
1. Jogador tenta comprar sem ouro
2. Recebe mensagem clara de erro
3. Vai à arena ganhar ouro
4. Retorna à loja e compra com sucesso

---

## 6. Notas de Implementação

*Esta seção será preenchida na documentação técnica (não inclusa neste documento funcional).*

---

**Fim do Documento**
