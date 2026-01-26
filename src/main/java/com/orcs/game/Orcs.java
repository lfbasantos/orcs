package com.orcs.game;

import java.io.*;
import java.util.*;
import com.orcs.ui.Console;
import com.orcs.save.SaveManager;
import com.orcs.combat.Arena;

/**
 * Classe principal do jogo Orcs.
 * Gerencia o loop principal, menus e fluxo de jogo.
 * 
 * Orcs - Alpha v.0.1.6
 * Luiz Filipe - lfbasantos@gmail.com
 * 
 * 02/05/2016 - Implementação do Limite de HP por Level
 * 06/05/2016 - Modificações na Arena para processar Ataque Mágico
 */
public class Orcs {

    public static void main(String[] args) {
        // Variáveis de PC e NPC
        String playerChoice, menuChoice, shopChoice, arenaChoice;
        String playerName = "";
        String npcName = "Drugul";
        String playerClass = "";
        Integer playerSkill = 0;
        Integer playerHealth = 0;
        Integer playerMana = 0;
        Integer playerXp = 0;
        Integer playerLevel = 0;
        Integer playerGold = 0;
        Integer playerPotions = 0;
        Integer npcSkill = 0;
        Integer npcHealth = 0;
        Integer npcXp = 0;
        Integer npcGold = 0;
        String verifyLoad = "0";
        int initOk = 1;
        int exit = 0;
        int exitMenu = 0;
        int exitShop = 0;
        int exitArena = 0;
        
        do {
            // Cria instâncias dos gerenciadores
            Console console = new Console();
            SaveManager saveManager = new SaveManager();
            
            // Limpa a tela
            console.clear();
            
            // Cria diretório de dados se não existir
            File dataDir = new File("orcdata");
            if (!dataDir.exists()) {
                try {
                    dataDir.mkdir();
                } catch (SecurityException se) {
                    console.print("# Erro! Não foi possível criar diretório orcdata. Crie manualmente ou execute com permissões de escrita.");
                }
            }
            
            // Tela inicial
            console.print("#");
            console.print("# ORCS v.0.1.6");
            console.print("#");
            console.print("# Bem-vindo ao mundo Sangrento dos Orcs!");
            console.print("#");
            console.print("# Escolha suas opções");
            console.print("# [1] Novo Personagem");
            console.print("# [2] Carregar Personagem");
            console.print("# [X] Encerrar");
            
            playerChoice = console.input();
            
            // Opção: Novo Personagem
            if (playerChoice.equals("1")) {
                console.clear();
                console.print("# Digite o Nome do Personagem");
                playerName = console.input();
                
                // Inicializa baú
                saveManager.initializeInventory(playerName);
                
                // Menu de seleção de classe
                console.clear();
                console.print("#");
                console.print("# Escolha sua Classe");
                console.print("#");
                console.print("# [1] Escola de Armas");
                console.print("# Especialização em Armas e Armaduras.");
                console.print("");
                console.print("# [2] Escola de Magia");
                console.print("# Especialização em Magias e Encantamentos.");
                playerClass = console.input();
                
                // Guerreiro
                if (playerClass.equals("1")) {
                    console.clear();
                    playerSkill = 12;
                    playerHealth = 16;
                    playerMana = 0;
                    playerPotions = 5;
                    playerXp = 0;
                    playerLevel = 1;
                    playerGold = 1000;
                    saveManager.configureAttributes(playerName, playerSkill, playerHealth, playerXp, 
                                                   playerLevel, playerGold, playerPotions, playerClass, playerMana);
                    saveManager.save();
                    console.print("# Personagem Gerado e Gravado.");
                    initOk = 0;
                    console.pause();
                }
                
                // Mago
                if (playerClass.equals("2")) {
                    console.clear();
                    playerSkill = 12;
                    playerHealth = 12;
                    playerMana = 20;
                    playerPotions = 3;
                    playerXp = 0;
                    playerLevel = 1;
                    playerGold = 1000;
                    saveManager.configureAttributes(playerName, playerSkill, playerHealth, playerXp, 
                                                   playerLevel, playerGold, playerPotions, playerClass, playerMana);
                    saveManager.save();
                    console.print("# Personagem Gerado e Gravado.");
                    initOk = 0;
                    console.pause();
                }
                playerChoice = "0";
            }
            
            // Opção: Carregar Personagem
            if (playerChoice.equals("2")) {
                console.print("# Digite o Nome do Personagem");
                playerName = console.input();
                verifyLoad = saveManager.load(playerName, 1);
                if (!verifyLoad.equals("ERR")) {
                    playerSkill = Integer.parseInt(saveManager.load(playerName, 1));
                    playerHealth = Integer.parseInt(saveManager.load(playerName, 2));
                    playerXp = Integer.parseInt(saveManager.load(playerName, 3));
                    playerLevel = Integer.parseInt(saveManager.load(playerName, 4));
                    playerGold = Integer.parseInt(saveManager.load(playerName, 5));
                    playerPotions = Integer.parseInt(saveManager.load(playerName, 6));
                    playerClass = saveManager.load(playerName, 7);
                    playerMana = Integer.parseInt(saveManager.load(playerName, 8));
                    initOk = 0;
                }
                console.pause();
                playerChoice = "0";
            }
            
            // Opção: Sair
            if (playerChoice.equals("X")) {
                console.clear();
                console.print("# Sair");
                System.exit(0);
            }
            
            // Loop principal do menu in-game
            while (exitMenu == 0 && initOk == 0) {
                console.clear();
                console.print("#");
                console.print("# MENU");
                console.print("#");
                console.print("# [1] Ver Ficha");
                console.print("# [2] Ver Baú");
                console.print("# [3] Loja de Itens");
                console.print("# [4] Taverna");
                console.print("# [5] Armas & Armaduras");
                console.print("# [6] Cajados & Magias");
                console.print("# [7] Arena");
                console.print("# [X] Sair");
                console.print("#->");
                
                menuChoice = console.input();
                
                // Sair do menu
                if (menuChoice.equals("X")) {
                    console.clear();
                    console.print("# Sair");
                    exitMenu = 1;
                }
                
                // Ver Ficha
                if (menuChoice.equals("1")) {
                    console.clear();
                    console.print("#");
                    console.print("# Ficha do Personagem");
                    console.print("#");
                    
                    // Atualiza valores
                    playerSkill = Integer.parseInt(saveManager.load(playerName, 1));
                    playerHealth = Integer.parseInt(saveManager.load(playerName, 2));
                    playerXp = Integer.parseInt(saveManager.load(playerName, 3));
                    playerLevel = Integer.parseInt(saveManager.load(playerName, 4));
                    playerGold = Integer.parseInt(saveManager.load(playerName, 5));
                    playerPotions = Integer.parseInt(saveManager.load(playerName, 6));
                    playerClass = saveManager.load(playerName, 7);
                    playerMana = Integer.parseInt(saveManager.load(playerName, 8));
                    
                    // Mostra ficha
                    if (playerClass.equals("1")) {
                        console.print("# Classe: Escola de Armas");
                    } else {
                        console.print("# Classe: Escola de Magia");
                    }
                    console.print("# Nome: " + playerName);
                    console.print("# XP: " + playerXp + " - Nível: " + playerLevel);
                    console.print("#");
                    console.print("# Habilidade: " + playerSkill);
                    console.print("# Pontos de Vida: " + playerHealth);
                    if (playerClass.equals("2")) {
                        console.print("# Pontos de Mana: " + playerMana);
                    }
                    console.print("#");
                    console.print("# Poções: " + playerPotions);
                    console.print("# Gold: " + playerGold);
                    console.print("#");
                    
                    if (playerClass.equals("1")) {
                        console.print("# Arma:");
                        saveManager.loadEquippedItem(playerName, "Arma", "1");
                        console.print("# Set:");
                        saveManager.loadEquippedItem(playerName, "Set", "1");
                    } else {
                        console.print("# Magia:");
                        saveManager.loadEquippedItem(playerName, "Magia", "1");
                        console.print("# Manto:");
                        saveManager.loadEquippedItem(playerName, "Manto", "1");
                    }
                    console.print("#");
                    console.pause();
                }
                
                // Ver Baú
                if (menuChoice.equals("2")) {
                    while (exitShop == 0) {
                        console.clear();
                        console.print("#");
                        console.print("# Baú do Personagem");
                        console.print("#");
                        playerGold = Integer.parseInt(saveManager.load(playerName, 5));
                        playerPotions = Integer.parseInt(saveManager.load(playerName, 6));
                        
                        console.print("# Nome: " + playerName);
                        console.print("# Poções: " + playerPotions);
                        console.print("# Gold: " + playerGold);
                        console.print("#");
                        saveManager.loadInventory(playerName);
                        console.print("#");
                        console.print("# [E] Equipar Item");
                        console.print("# [V] Vender");
                        console.print("# [X] Sair");
                        shopChoice = console.input();
                        
                        if (shopChoice.equals("V")) {
                            console.print("# Digite o ID do Item ou 0 para Cancelar.");
                            shopChoice = console.input();
                            if (shopChoice.equals("0")) {
                                exitShop = 0;
                            } else {
                                playerGold = saveManager.sellItem(playerName, shopChoice, playerGold);
                                saveManager.configureAttributes(playerName, playerSkill, playerHealth, playerXp, 
                                                               playerLevel, playerGold, playerPotions, playerClass, playerMana);
                                saveManager.save();
                                console.print("# Item [" + shopChoice + "] Vendido.");
                                console.pause();
                            }
                        }
                        
                        if (shopChoice.equals("E")) {
                            console.print("# Digite o ID do Item ou 0 para Cancelar.");
                            shopChoice = console.input();
                            if (shopChoice.equals("0")) {
                                exitShop = 0;
                            } else {
                                saveManager.equipItem(playerName, shopChoice);
                                console.print("# Item [" + shopChoice + "] Equipado.");
                                console.pause();
                            }
                        }
                        
                        if (shopChoice.equals("X")) {
                            exitShop = 1;
                        }
                    }
                    exitShop = 0;
                }
                
                // Loja de Itens (Poções)
                if (menuChoice.equals("3")) {
                    while (exitShop == 0) {
                        console.clear();
                        console.print("#");
                        console.print("# LOJA DE ITENS");
                        console.print("#");
                        
                        playerGold = Integer.parseInt(saveManager.load(playerName, 5));
                        playerPotions = Integer.parseInt(saveManager.load(playerName, 6));
                        
                        console.print("# Poções: " + playerPotions);
                        console.print("# Gold: " + playerGold);
                        console.print("#");
                        console.print("# [1] [50] Poção de HP 1D");
                        console.print("# [X] Sair");
                        
                        shopChoice = console.input();
                        
                        if (shopChoice.equals("1")) {
                            if (playerGold < 50) {
                                console.print("# Dinheiro insuficiente");
                                console.pause();
                            } else {
                                playerGold = playerGold - 50;
                                playerPotions++;
                                saveManager.configureAttributes(playerName, playerSkill, playerHealth, playerXp, 
                                                               playerLevel, playerGold, playerPotions, playerClass, playerMana);
                                saveManager.save();
                                console.print("# Item comprado com sucesso.");
                                console.pause();
                            }
                        }
                        
                        if (shopChoice.equals("X")) {
                            exitShop = 1;
                        }
                    }
                    exitShop = 0;
                }
                
                // Taverna
                if (menuChoice.equals("4")) {
                    while (exitShop == 0) {
                        console.clear();
                        console.print("#");
                        console.print("# TAVERNA DO CÃO SARNENTO");
                        console.print("#");
                        
                        playerGold = Integer.parseInt(saveManager.load(playerName, 5));
                        playerHealth = Integer.parseInt(saveManager.load(playerName, 2));
                        playerMana = Integer.parseInt(saveManager.load(playerName, 8));
                        playerLevel = Integer.parseInt(saveManager.load(playerName, 4));
                        
                        console.print("# Gold: " + playerGold);
                        console.print("#");
                        console.print("# Pontos de Vida: " + playerHealth);
                        if (playerClass.equals("2")) {
                            console.print("# Pontos de Mana: " + playerMana);
                        }
                        console.print("#");
                        console.print("# [1] [10] Cerveja - Recupera 2 Pontos de HP/MP");
                        console.print("#");
                        console.print("# [2] [50] Vinho - Recupera 5 Pontos de HP/MP");
                        console.print("#");
                        console.print("# [3] [150] Chá de Cogumelo - Recupera 10 Pontos de HP/MP");
                        console.print("#");
                        console.print("# [X] Sair");
                        
                        shopChoice = console.input();
                        
                        if (shopChoice.equals("1")) {
                            if (playerGold < 10) {
                                console.print("# Dinheiro insuficiente");
                                console.pause();
                            } else {
                                playerGold = playerGold - 10;
                                playerPotions++;
                                playerHealth = playerHealth + 2;
                                playerMana = playerMana + 2;
                                playerHealth = saveManager.calculateMaxHealth(playerLevel, playerHealth);
                                playerMana = saveManager.calculateMaxMana(playerLevel, playerMana);
                                saveManager.configureAttributes(playerName, playerSkill, playerHealth, playerXp, 
                                                               playerLevel, playerGold, playerPotions, playerClass, playerMana);
                                saveManager.save();
                                console.print("# Recuperação realizada.");
                                console.pause();
                            }
                        }
                        
                        if (shopChoice.equals("2")) {
                            if (playerGold < 50) {
                                console.print("# Dinheiro insuficiente");
                                console.pause();
                            } else {
                                playerGold = playerGold - 50;
                                playerPotions++;
                                playerHealth = playerHealth + 5;
                                playerMana = playerMana + 5;
                                playerHealth = saveManager.calculateMaxHealth(playerLevel, playerHealth);
                                playerMana = saveManager.calculateMaxMana(playerLevel, playerMana);
                                saveManager.configureAttributes(playerName, playerSkill, playerHealth, playerXp, 
                                                               playerLevel, playerGold, playerPotions, playerClass, playerMana);
                                saveManager.save();
                                console.print("# Recuperação realizada.");
                                console.pause();
                            }
                        }
                        
                        if (shopChoice.equals("3")) {
                            if (playerGold < 150) {
                                console.print("# Dinheiro insuficiente");
                                console.pause();
                            } else {
                                playerGold = playerGold - 150;
                                playerPotions++;
                                playerHealth = playerHealth + 10;
                                playerMana = playerMana + 10;
                                playerHealth = saveManager.calculateMaxHealth(playerLevel, playerHealth);
                                playerMana = saveManager.calculateMaxMana(playerLevel, playerMana);
                                saveManager.configureAttributes(playerName, playerSkill, playerHealth, playerXp, 
                                                               playerLevel, playerGold, playerPotions, playerClass, playerMana);
                                saveManager.save();
                                console.print("# Recuperação realizada.");
                                console.pause();
                            }
                        }
                        
                        if (shopChoice.equals("X")) {
                            exitShop = 1;
                        }
                    }
                    exitShop = 0;
                }
                
                // Loja de Armas & Armaduras
                if (menuChoice.equals("5")) {
                    String item1 = "1;Arma;1;1;100;0;Faca Pequena Atk 1 Dano 1 $100";
                    String item2 = "2;Arma;2;2;1000;0;Espada Atk 2 Dano 2 $1000";
                    String item3 = "3;Set;0;1;100;0;Roupas Simples Def 0 Abs 1 $100";
                    String item4 = "4;Set;1;2;1000;0;Set de Couro Def 1 Abs 2 $1000";
                    
                    while (exitShop == 0) {
                        console.clear();
                        console.print("#");
                        console.print("# ARMAS & ARMADURAS");
                        console.print("#");
                        
                        playerGold = Integer.parseInt(saveManager.load(playerName, 5));
                        
                        console.print("# Gold: " + playerGold);
                        console.print("#");
                        console.print("# [1] Faca Pequena Atk 1 Dano 1 $100");
                        console.print("# [2] Espada Atk 2 Dano 2 $1000");
                        console.print("# [3] Roupas Simples Def 0 Abs 1 $100");
                        console.print("# [4] Set de Couro Def 1 Abs 2 $1000");
                        console.print("#");
                        console.print("# [X] Sair");
                        
                        shopChoice = console.input();
                        
                        if (shopChoice.equals("1")) {
                            if (playerGold < 100) {
                                console.print("# Dinheiro insuficiente");
                                console.pause();
                            } else {
                                playerGold = playerGold - 100;
                                saveManager.addItemToInventory(playerName, item1 + "\n");
                                saveManager.configureAttributes(playerName, playerSkill, playerHealth, playerXp, 
                                                               playerLevel, playerGold, playerPotions, playerClass, playerMana);
                                saveManager.save();
                                console.print("# Item comprado com sucesso.");
                                console.pause();
                            }
                        }
                        
                        if (shopChoice.equals("2")) {
                            if (playerGold < 1000) {
                                console.print("# Dinheiro insuficiente");
                                console.pause();
                            } else {
                                playerGold = playerGold - 1000;
                                saveManager.addItemToInventory(playerName, item2 + "\n");
                                saveManager.configureAttributes(playerName, playerSkill, playerHealth, playerXp, 
                                                               playerLevel, playerGold, playerPotions, playerClass, playerMana);
                                saveManager.save();
                                console.print("# Item comprado com sucesso.");
                                console.pause();
                            }
                        }
                        
                        if (shopChoice.equals("3")) {
                            if (playerGold < 100) {
                                console.print("# Dinheiro insuficiente");
                                console.pause();
                            } else {
                                playerGold = playerGold - 100;
                                saveManager.addItemToInventory(playerName, item3 + "\n");
                                saveManager.configureAttributes(playerName, playerSkill, playerHealth, playerXp, 
                                                               playerLevel, playerGold, playerPotions, playerClass, playerMana);
                                saveManager.save();
                                console.print("# Item comprado com sucesso.");
                                console.pause();
                            }
                        }
                        
                        if (shopChoice.equals("4")) {
                            if (playerGold < 1000) {
                                console.print("# Dinheiro insuficiente");
                                console.pause();
                            } else {
                                playerGold = playerGold - 1000;
                                saveManager.addItemToInventory(playerName, item4 + "\n");
                                saveManager.configureAttributes(playerName, playerSkill, playerHealth, playerXp, 
                                                               playerLevel, playerGold, playerPotions, playerClass, playerMana);
                                saveManager.save();
                                console.print("# Item comprado com sucesso.");
                                console.pause();
                            }
                        }
                        
                        if (shopChoice.equals("X")) {
                            exitShop = 1;
                        }
                    }
                    exitShop = 0;
                }
                
                // Loja de Mantos & Magias
                if (menuChoice.equals("6")) {
                    String item1 = "5;Magia;1;1;100;0;Bola de Fogo Atk 1 Dano 1 $100";
                    String item2 = "6;Magia;2;2;1000;0;Bola de Gelo Atk 2 Dano 2 $1000";
                    String item3 = "7;Manto;0;1;100;0;Manto Aprendiz Def 0 Abs 1 $100";
                    String item4 = "8;Manto;1;2;1000;0;Manto Sagrado Def 1 Abs 2 $1000";
                    
                    while (exitShop == 0) {
                        console.clear();
                        console.print("#");
                        console.print("# MANTOS & MAGIAS");
                        console.print("#");
                        
                        playerGold = Integer.parseInt(saveManager.load(playerName, 5));
                        
                        console.print("# Gold: " + playerGold);
                        console.print("#");
                        console.print("# [1] Bola de Fogo Atk 1 Dano 1 $100");
                        console.print("# [2] Bola de Gelo Atk 2 Dano 2 $1000");
                        console.print("# [3] Manto Aprendiz Def 0 Abs 1 $100");
                        console.print("# [4] Manto Sagrado Def 1 Abs 2 $1000");
                        console.print("#");
                        console.print("# [X] Sair");
                        
                        shopChoice = console.input();
                        
                        if (shopChoice.equals("1")) {
                            if (playerGold < 100) {
                                console.print("# Dinheiro insuficiente");
                                console.pause();
                            } else {
                                playerGold = playerGold - 100;
                                saveManager.addItemToInventory(playerName, item1 + "\n");
                                saveManager.configureAttributes(playerName, playerSkill, playerHealth, playerXp, 
                                                               playerLevel, playerGold, playerPotions, playerClass, playerMana);
                                saveManager.save();
                                console.print("# Item comprado com sucesso.");
                                console.pause();
                            }
                        }
                        
                        if (shopChoice.equals("2")) {
                            if (playerGold < 1000) {
                                console.print("# Dinheiro insuficiente");
                                console.pause();
                            } else {
                                playerGold = playerGold - 1000;
                                saveManager.addItemToInventory(playerName, item2 + "\n");
                                saveManager.configureAttributes(playerName, playerSkill, playerHealth, playerXp, 
                                                               playerLevel, playerGold, playerPotions, playerClass, playerMana);
                                saveManager.save();
                                console.print("# Item comprado com sucesso.");
                                console.pause();
                            }
                        }
                        
                        if (shopChoice.equals("3")) {
                            if (playerGold < 100) {
                                console.print("# Dinheiro insuficiente");
                                console.pause();
                            } else {
                                playerGold = playerGold - 100;
                                saveManager.addItemToInventory(playerName, item3 + "\n");
                                saveManager.configureAttributes(playerName, playerSkill, playerHealth, playerXp, 
                                                               playerLevel, playerGold, playerPotions, playerClass, playerMana);
                                saveManager.save();
                                console.print("# Item comprado com sucesso.");
                                console.pause();
                            }
                        }
                        
                        if (shopChoice.equals("4")) {
                            if (playerGold < 1000) {
                                console.print("# Dinheiro insuficiente");
                                console.pause();
                            } else {
                                playerGold = playerGold - 1000;
                                saveManager.addItemToInventory(playerName, item4 + "\n");
                                saveManager.configureAttributes(playerName, playerSkill, playerHealth, playerXp, 
                                                               playerLevel, playerGold, playerPotions, playerClass, playerMana);
                                saveManager.save();
                                console.print("# Item comprado com sucesso.");
                                console.pause();
                            }
                        }
                        
                        if (shopChoice.equals("X")) {
                            exitShop = 1;
                        }
                    }
                    exitShop = 0;
                }
                
                // Arena
                if (menuChoice.equals("7")) {
                    // Variáveis de batalha
                    Integer pcAtk = 0;
                    Integer npcAtk = 0;
                    Integer pcDef = 0;
                    Integer npcDef = 0;
                    Integer pcDamage = 0;
                    Integer npcDamage = 0;
                    
                    // Carrega bônus de equipamento
                    String bonusAtkStr = saveManager.loadEquippedItem(playerName, "Arma", "2");
                    String bonusDefStr = saveManager.loadEquippedItem(playerName, "Set", "2");
                    String bonusDmgStr = saveManager.loadEquippedItem(playerName, "Arma", "3");
                    String bonusAbsStr = saveManager.loadEquippedItem(playerName, "Set", "3");
                    
                    Integer bonusAtk = bonusAtkStr.equals("N/A") ? 0 : Integer.parseInt(bonusAtkStr);
                    Integer bonusDef = bonusDefStr.equals("N/A") ? 0 : Integer.parseInt(bonusDefStr);
                    Integer bonusDmg = bonusDmgStr.equals("N/A") ? 0 : Integer.parseInt(bonusDmgStr);
                    Integer bonusAbs = bonusAbsStr.equals("N/A") ? 0 : Integer.parseInt(bonusAbsStr);
                    
                    // Carrega level do jogador
                    playerLevel = Integer.parseInt(saveManager.load(playerName, 4));
                    
                    // Gera NPC inicial
                    npcName = saveManager.generateOrcName(Arena.rollDice(1, 20));
                    npcSkill = Arena.getNpcSkill(playerLevel);
                    npcHealth = Arena.getNpcHealth(playerLevel);
                    npcGold = Arena.getNpcGold(playerLevel);
                    npcXp = Arena.getNpcExperience(playerLevel);
                    
                    while (exitArena == 0) {
                        console.clear();
                        
                        // Atualiza valores do jogador
                        playerSkill = Integer.parseInt(saveManager.load(playerName, 1));
                        playerHealth = Integer.parseInt(saveManager.load(playerName, 2));
                        playerXp = Integer.parseInt(saveManager.load(playerName, 3));
                        playerLevel = Integer.parseInt(saveManager.load(playerName, 4));
                        playerGold = Integer.parseInt(saveManager.load(playerName, 5));
                        playerPotions = Integer.parseInt(saveManager.load(playerName, 6));
                        playerClass = saveManager.load(playerName, 7);
                        playerMana = Integer.parseInt(saveManager.load(playerName, 8));
                        
                        console.print("#");
                        console.print("# ARENA");
                        console.print("# " + playerName + " - H: " + playerSkill + " E: " + playerHealth + 
                                     " Gold: " + playerGold + " LVL/XP " + playerLevel + "/" + playerXp);
                        console.print("#");
                        console.print("# " + npcName + " - H: " + npcSkill + " E: " + npcHealth + " Gold: " + npcGold);
                        console.print("#");
                        console.print("#");
                        console.print("# [1] Atacar");
                        console.print("# [2] Rampage");
                        console.print("#");
                        console.print("# [X] Sair");
                        
                        arenaChoice = console.input();
                        
                        // Modo Ataque Normal
                        if (arenaChoice.equals("1")) {
                            if (npcHealth < 1 || playerHealth < 1) {
                                if (npcHealth < 1) {
                                    console.print("# Gerando Novo NPC");
                                    console.sleep();
                                    npcName = saveManager.generateOrcName(Arena.rollDice(1, 20));
                                    npcSkill = Arena.getNpcSkill(playerLevel);
                                    npcHealth = Arena.getNpcHealth(playerLevel);
                                    npcGold = Arena.getNpcGold(playerLevel);
                                    npcXp = Arena.getNpcExperience(playerLevel);
                                }
                                
                                if (playerHealth < 1) {
                                    console.print("# Sem pontos de Vida, Vá beber!");
                                }
                            } else {
                                console.print("# Processa Ataque de " + playerName);
                                console.sleep();
                                
                                // Round do Jogador
                                pcAtk = playerSkill + Arena.rollDice(2, 12) + bonusAtk;
                                npcDef = npcSkill + Arena.rollDice(2, 12);
                                console.print("# Força de Ataque: " + pcAtk);
                                console.sleep();
                                console.print("# Força de Defesa: " + npcDef);
                                console.sleep();
                                
                                if (pcAtk > npcDef) {
                                    console.print("# Ataque bem sucedido!");
                                    console.sleep();
                                    
                                    pcDamage = Arena.rollDice(1, 6) + bonusDmg;
                                    console.print("# " + npcName + " sofre " + pcDamage + " pontos de dano.");
                                    console.sleep();
                                    
                                    npcHealth = npcHealth - pcDamage;
                                    
                                    if (npcHealth < 1) {
                                        console.print("# " + npcName + " foi destruído");
                                        playerGold = playerGold + npcGold;
                                        playerXp = playerXp + npcXp;
                                        playerLevel = saveManager.calculateLevel(playerXp);
                                        playerSkill = saveManager.calculateSkill(playerLevel);
                                        saveManager.configureAttributes(playerName, playerSkill, playerHealth, playerXp, 
                                                                       playerLevel, playerGold, playerPotions, playerClass, playerMana);
                                        saveManager.save();
                                        console.pause();
                                    }
                                } else {
                                    console.print("# " + npcName + " Defendeu!");
                                    console.sleep();
                                }
                                
                                if (npcHealth > 0) {
                                    // Round do NPC
                                    console.print("# Ataque de " + npcName + "!");
                                    console.sleep();
                                    npcAtk = npcSkill + Arena.rollDice(2, 12);
                                    pcDef = playerSkill + Arena.rollDice(2, 12) + bonusDef;
                                    console.print("# Força de Ataque: " + npcAtk);
                                    console.sleep();
                                    console.print("# Força de Defesa: " + pcDef);
                                    console.sleep();
                                    
                                    if (npcAtk > pcDef) {
                                        console.print("# Ataque bem sucedido!");
                                        console.sleep();
                                        
                                        npcDamage = Arena.rollDice(1, 6) - bonusAbs;
                                        if (npcDamage < 0) npcDamage = 0;
                                        console.print("# " + playerName + " sofre " + npcDamage + " pontos de dano.");
                                        console.sleep();
                                        
                                        playerHealth = playerHealth - npcDamage;
                                        saveManager.configureAttributes(playerName, playerSkill, playerHealth, playerXp, 
                                                                       playerLevel, playerGold, playerPotions, playerClass, playerMana);
                                        saveManager.save();
                                        
                                        // Auto-usa poções se necessário
                                        while (playerHealth < 1 && playerPotions > 0) {
                                            playerHealth = playerHealth + Arena.rollDice(2, 12);
                                            playerPotions = playerPotions - 1;
                                            saveManager.configureAttributes(playerName, playerSkill, playerHealth, playerXp, 
                                                                           playerLevel, playerGold, playerPotions, playerClass, playerMana);
                                            saveManager.save();
                                            console.print("# Recuperando Energia");
                                            console.sleep();
                                        }
                                        
                                        if (playerHealth < 1 && playerPotions < 1) {
                                            console.print("# " + playerName + " foi humilhado.");
                                            console.pause();
                                        }
                                    } else {
                                        console.print("# " + playerName + " Defendeu!");
                                        console.sleep();
                                    }
                                }
                            }
                        }
                        
                        // Modo Rampage
                        if (arenaChoice.equals("2")) {
                            if (npcHealth < 1 || playerHealth < 1) {
                                if (npcHealth < 1) {
                                    console.print("# Gerando Novo NPC");
                                    console.sleep();
                                    npcName = saveManager.generateOrcName(Arena.rollDice(1, 20));
                                    npcSkill = Arena.getNpcSkill(playerLevel);
                                    npcHealth = Arena.getNpcHealth(playerLevel);
                                    npcGold = Arena.getNpcGold(playerLevel);
                                    npcXp = Arena.getNpcExperience(playerLevel);
                                }
                                
                                if (playerHealth < 1) {
                                    console.print("# Sem pontos de Vida, Vá beber!");
                                }
                            } else {
                                console.print("# RAAAMPAGEEE!");
                                console.sleep();
                                
                                while (npcHealth > 0 && playerHealth > 0) {
                                    if (npcHealth < 1 || playerHealth < 1) {
                                        if (npcHealth < 1) {
                                            console.print("# Gerando Novo NPC");
                                            console.sleep();
                                            npcName = saveManager.generateOrcName(Arena.rollDice(1, 20));
                                            npcSkill = Arena.getNpcSkill(playerLevel);
                                            npcHealth = Arena.getNpcHealth(playerLevel);
                                            npcGold = Arena.getNpcGold(playerLevel);
                                            npcXp = Arena.getNpcExperience(playerLevel);
                                        }
                                        
                                        if (playerHealth < 1) {
                                            console.print("# Sem pontos de Vida, Vá beber!");
                                        }
                                    } else {
                                        // Round do Jogador
                                        pcAtk = playerSkill + Arena.rollDice(2, 12) + bonusAtk;
                                        npcDef = npcSkill + Arena.rollDice(2, 12);
                                        
                                        if (pcAtk > npcDef) {
                                            pcDamage = Arena.rollDice(1, 6) + bonusDmg;
                                            console.print("# " + npcName + " sofre " + pcDamage + " pontos de dano.");
                                            console.sleep();
                                            
                                            npcHealth = npcHealth - pcDamage;
                                            
                                            if (npcHealth < 1) {
                                                console.print("# " + npcName + " foi destruído");
                                                playerGold = playerGold + npcGold;
                                                playerXp = playerXp + npcXp;
                                                playerLevel = saveManager.calculateLevel(playerXp);
                                                playerSkill = saveManager.calculateSkill(playerLevel);
                                                saveManager.configureAttributes(playerName, playerSkill, playerHealth, playerXp, 
                                                                               playerLevel, playerGold, playerPotions, playerClass, playerMana);
                                                saveManager.save();
                                                console.pause();
                                            }
                                        }
                                        
                                        if (npcHealth > 0) {
                                            // Round do NPC
                                            npcAtk = npcSkill + Arena.rollDice(2, 12);
                                            pcDef = playerSkill + Arena.rollDice(2, 12) + bonusDef;
                                            
                                            if (npcAtk > pcDef) {
                                                npcDamage = Arena.rollDice(1, 6) - bonusAbs;
                                                if (npcDamage < 0) npcDamage = 0;
                                                console.print("# " + playerName + " sofre " + npcDamage + " pontos de dano.");
                                                console.sleep();
                                                
                                                playerHealth = playerHealth - npcDamage;
                                                saveManager.configureAttributes(playerName, playerSkill, playerHealth, playerXp, 
                                                                               playerLevel, playerGold, playerPotions, playerClass, playerMana);
                                                saveManager.save();
                                                
                                                // Auto-usa poções
                                                while (playerHealth < 1 && playerPotions > 0) {
                                                    playerHealth = playerHealth + Arena.rollDice(2, 12);
                                                    playerPotions = playerPotions - 1;
                                                    saveManager.configureAttributes(playerName, playerSkill, playerHealth, playerXp, 
                                                                                   playerLevel, playerGold, playerPotions, playerClass, playerMana);
                                                    saveManager.save();
                                                }
                                                
                                                if (playerHealth < 1 && playerPotions < 1) {
                                                    console.print("# " + playerName + " foi humilhado. tsc tsc ¬¬");
                                                    console.pause();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        
                        if (arenaChoice.equals("X")) {
                            exitArena = 1;
                        }
                    }
                    exitArena = 0;
                }
            }
            exitMenu = 0;
            
        } while (exit == 0);
    }
}
