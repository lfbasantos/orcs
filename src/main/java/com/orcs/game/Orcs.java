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
                
                // Menu procedimentos das outras lojas omitido para brevidade...
                // (Loja de Itens, Taverna, Armas & Armaduras, Magias, Arena)
            }
            exitMenu = 0;
            
        } while (exit == 0);
    }
}
