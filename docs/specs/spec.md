# Especificações Funcionais - Orcs v0.2.0

**Versão:** 0.2.0  
**Data:** Janeiro 2026  
**Status:** ✅ IMPLEMENTADO (Janeiro 2026)

---

## Status de Implementação

| Requisito | Status | Observações |
|-----------|--------|-------------|
| FR01-FR03 | ✅ Implementado | Sistema de lojas completo |
| FR04 | ✅ Implementado | Taverna funcional |
| FR05-FR06 | ✅ Implementado | Arena com modo normal e rampage |
| FR07-FR09 | ✅ Implementado | Sistema de baú completo |
| FR10-FR12 | ⚠️ Parcial | Validação básica presente, pode ser melhorada |
| FR13-FR14 | ⚠️ Parcial | Tratamento de erros básico implementado |
| FR15-FR17 | ❌ Pendente | Sistema de cores ANSI (roadmap v0.3.0) |
| FR18-FR20 | ❌ Pendente | Melhorias de UX (roadmap v0.3.0) |

---

## 1. Escopo

Este documento descreve os requisitos funcionais para a versão 0.2.0 do jogo Orcs, focando em melhorias de código, completude das funcionalidades e experiência do usuário.

---

## 2. Requisitos Funcionais

### 2.1. Sistema de Lojas

#### FR01: Loja de Itens - Compra de Poções
**Descrição:** O jogador deve poder comprar poções de cura na Loja de Itens.

**Comportamento Esperado:**
- Ao selecionar a opção "Loja de Itens" no menu principal, o jogador visualiza:
  - Saldo atual de ouro
  - Quantidade atual de poções
  - Lista de itens disponíveis com preço
- O jogador pode selecionar um item pelo número
- Se o jogador tiver ouro suficiente, a compra é confirmada e:
  - O ouro é deduzido
  - A quantidade de poções aumenta
  - Uma mensagem de confirmação é exibida
- Se o jogador não tiver ouro suficiente:
  - Uma mensagem "Dinheiro insuficiente" é exibida
  - Nenhuma alteração ocorre no inventário

**Critérios de Aceitação:**
- Saldo de ouro atualizado corretamente após compra
- Quantidade de poções atualizada corretamente
- Transação salva automaticamente

---

#### FR02: Loja de Armas & Armaduras
**Descrição:** O jogador da classe Guerreiro deve poder comprar e vender armas e armaduras.

**Comportamento Esperado:**
- Menu exibe itens específicos para guerreiros:
  - Armas: Faca Pequena, Espada, etc.
  - Armaduras: Roupas Simples, Set de Couro, etc.
- Cada item mostra atributos (Ataque, Dano, Defesa, Absorção) e preço
- Ao comprar um item:
  - Item é adicionado ao baú do jogador
  - Ouro é deduzido
  - Confirmação é exibida
- Sistema verifica saldo antes de permitir compra

**Critérios de Aceitação:**
- Itens aparecem no baú após compra
- Apenas guerreiros podem acessar esta loja
- Preços exibidos correspondem ao custo real

---

#### FR03: Loja de Cajados & Magias
**Descrição:** O jogador da classe Mago deve poder comprar magias e mantos.

**Comportamento Esperado:**
- Menu exibe itens específicos para magos:
  - Magias: Bola de Fogo, Bola de Gelo, etc.
  - Mantos: Manto Aprendiz, Manto Sagrado, etc.
- Cada item mostra atributos mágicos e preço
- Sistema de compra funciona similar à loja de armas
- Apenas magos podem acessar esta loja

**Critérios de Aceitação:**
- Magias e mantos aparecem no baú após compra
- Apenas magos podem acessar esta loja
- Atributos mágicos são considerados no combate

---

### 2.2. Sistema de Taverna

#### FR04: Taverna - Recuperação de Pontos
**Descrição:** O jogador deve poder recuperar HP e Mana na taverna comprando bebidas.

**Comportamento Esperado:**
- Menu da taverna exibe:
  - Saldo de ouro atual
  - HP atual do jogador
  - MP atual (se mago)
  - Lista de bebidas com preços e efeitos:
    - Cerveja (10 gold) - Recupera 2 HP/MP
    - Vinho (50 gold) - Recupera 5 HP/MP
    - Chá de Cogumelo (150 gold) - Recupera 10 HP/MP
- Ao comprar uma bebida:
  - HP/MP aumenta imediatamente
  - Ouro é deduzido
  - HP/MP não pode exceder o máximo do nível atual
  - Confirmação visual da recuperação

**Critérios de Aceitação:**
- HP/MP recuperado conforme especificado
- Limite máximo de HP/MP respeitado por nível
- Transação salva automaticamente
- Mensagem clara de quanto foi recuperado

---

### 2.3. Sistema de Arena

#### FR05: Arena - Modo Ataque Normal
**Descrição:** O jogador deve poder enfrentar NPCs em combate individual por turnos.

**Comportamento Esperado:**
- Ao entrar na arena, um NPC é gerado automaticamente
- Interface mostra:
  - Status do jogador (Nome, Habilidade, Energia, Gold, Level/XP)
  - Status do NPC (Nome, Habilidade, Energia, Gold)
  - Opções de ação
- Ao selecionar "Atacar":
  - Sistema calcula força de ataque do jogador (Habilidade + dados + bônus de equipamento)
  - Sistema calcula defesa do NPC
  - Se ataque > defesa:
    - Dano é calculado e aplicado ao NPC
    - Mensagem de sucesso é exibida
  - Se ataque ≤ defesa:
    - NPC defende, mensagem é exibida
- Turno do NPC ocorre automaticamente após turno do jogador
- Quando NPC é derrotado:
  - Jogador recebe ouro e XP
  - Nível é recalculado automaticamente
  - Novo NPC pode ser gerado
- Se jogador perde toda energia:
  - Poções são consumidas automaticamente se disponíveis
  - Se sem poções, mensagem de derrota é exibida

**Critérios de Aceitação:**
- Combate por turnos funciona corretamente
- Cálculos de ataque/defesa/dano são precisos
- Recompensas concedidas corretamente
- Auto-consumo de poções em emergência
- Sistema salva após cada vitória

---

#### FR06: Arena - Modo Rampage
**Descrição:** O jogador deve poder lutar continuamente até ser derrotado ou sair.

**Comportamento Esperado:**
- Similar ao modo normal, mas:
  - Combate continua automaticamente sem pausa entre inimigos
  - Mensagens de ataque são resumidas (menos verbose)
  - Novos NPCs são gerados automaticamente quando anteriores são derrotados
  - Jogador acumula ouro e XP continuamente
  - Modo termina quando:
    - Jogador é derrotado
    - Jogador fica sem energia e sem poções
    - Jogador escolhe sair manualmente

**Critérios de Aceitação:**
- Sequência de combates ininterrupta
- Performance adequada com múltiplos combates
- Recompensas acumuladas corretamente
- Jogador pode sair a qualquer momento

---

### 2.4. Gerenciamento de Baú

#### FR07: Baú - Visualização de Inventário
**Descrição:** O jogador deve poder visualizar todos os itens em seu baú.

**Comportamento Esperado:**
- Menu "Ver Baú" exibe:
  - Nome do jogador
  - Quantidade de poções
  - Saldo de ouro
  - Lista numerada de todos os itens com descrição completa
- Cada item mostra: ID, tipo, atributos e preço original
- Opções disponíveis:
  - [E] Equipar Item
  - [V] Vender Item
  - [X] Sair

**Critérios de Aceitação:**
- Todos os itens são listados corretamente
- IDs correspondem aos itens corretos
- Interface clara e organizada

---

#### FR08: Baú - Equipar Item
**Descrição:** O jogador deve poder equipar armas, armaduras, magias e mantos de seu baú.

**Comportamento Esperado:**
- Ao selecionar opção "Equipar":
  - Jogador digita ID do item
  - Sistema verifica tipo do item
  - Se item do mesmo tipo já estiver equipado:
    - Item anterior é desequipado automaticamente
  - Novo item é marcado como equipado
  - Confirmação é exibida
- Apenas um item de cada tipo pode estar equipado simultaneamente:
  - 1 arma OU 1 magia
  - 1 armadura (set) OU 1 manto
- Bônus do item equipado são aplicados imediatamente ao combate

**Critérios de Aceitação:**
- Sistema troca equipamento corretamente
- Apenas um item por tipo equipado
- Bônus aplicados corretamente
- Estado persistido no save

---

#### FR09: Baú - Vender Item
**Descrição:** O jogador deve poder vender itens de seu baú por ouro.

**Comportamento Esperado:**
- Ao selecionar opção "Vender":
  - Jogador digita ID do item
  - Se ID = 0, operação é cancelada
  - Se ID válido:
    - Item é removido do baú
    - Ouro equivalente ao preço do item é adicionado
    - Confirmação é exibida com ID do item vendido
- Item equipado pode ser vendido (será desequipado automaticamente)

**Critérios de Aceitação:**
- Item removido do inventário
- Ouro creditado corretamente
- Transação persistida
- Não é possível vender item inexistente

---

### 2.5. Validação de Entrada

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
