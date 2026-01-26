package com.orcs.combat;

import java.util.Random;

/**
 * Gerenciador do sistema de combate da Arena.
 * Responsável por cálculos de dano, defesa e geração de NPCs.
 */
public class Arena {
    
    /**
     * Gera um número aleatório entre min e max (inclusive)
     * @param min O valor mínimo
     * @param max O valor máximo
     * @return Um número aleatório entre min e max
     */
    public static int rollDice(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    /**
     * Calcula a habilidade de um NPC baseado no nível
     * @param level O nível do NPC
     * @return A habilidade do NPC
     */
    public static int getNpcSkill(int level) {
        int minSkill = 6;
        int maxSkill = 18;
        Random random = new Random();
        int skill = random.nextInt((maxSkill - minSkill) + 1) + minSkill;
        return skill + level;
    }

    /**
     * Calcula a energia/HP de um NPC baseado no nível
     * @param level O nível do NPC
     * @return A energia do NPC
     */
    public static int getNpcHealth(int level) {
        int minHealth = 6;
        int maxHealth = 18;
        Random random = new Random();
        int health = random.nextInt((maxHealth - minHealth) + 1) + minHealth;
        return health + level;
    }

    /**
     * Calcula a experiência concedida por um NPC baseado no nível
     * @param level O nível do NPC
     * @return A experiência do NPC
     */
    public static int getNpcExperience(int level) {
        int minXp = 100;
        int maxXp = 500;
        Random random = new Random();
        int xp = random.nextInt((maxXp - minXp) + 1) + minXp;
        return xp * level;
    }

    /**
     * Calcula o ouro que um NPC pode dar baseado no nível
     * @param level O nível do NPC
     * @return O ouro do NPC
     */
    public static int getNpcGold(int level) {
        int minGold = 500;
        int maxGold = 1000;
        Random random = new Random();
        int gold = random.nextInt((maxGold - minGold) + 1) + minGold;
        return gold * level;
    }
}
