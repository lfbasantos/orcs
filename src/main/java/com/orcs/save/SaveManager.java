package com.orcs.save;

import java.io.*;
import java.util.*;
import com.orcs.ui.Console;

/**
 * Gerenciador de salvamento e carregamento de dados do jogo.
 * Responsável por persistência de personagens e inventário.
 */
public class SaveManager {
    
    private String playerName = "";
    private String item = "";
    private String playerClass = "";
    private Integer mana;
    private Integer skill;
    private Integer health;
    private Integer experience;
    private Integer level;
    private Integer gold;
    private Integer potions;
    private int position;
    
    /**
     * Configura os atributos do personagem
     */
    public void configureAttributes(String name, Integer skill, Integer health, 
                                   Integer experience, Integer level, Integer gold, 
                                   Integer potions, String playerClass, Integer mana) {
        this.playerName = name;
        this.playerClass = playerClass;
        this.skill = skill;
        this.health = health;
        this.experience = experience;
        this.level = level;
        this.gold = gold;
        this.potions = potions;
        this.mana = mana;
    }

    /**
     * Salva o personagem em arquivo
     */
    public void save() {
        try {
            File dataDir = new File("orcdata");
            if (!dataDir.exists()) {
                dataDir.mkdir();
            }
            
            File file = new File("orcdata/" + playerName + ".gamesave");
            file.createNewFile();
            
            FileWriter writer = new FileWriter(file);
            writer.write(skill + ";" + health + ";" + experience + ";" + level + ";" + 
                        gold + ";" + potions + ";" + playerClass + ";" + mana);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calcula o nível baseado na experiência
     */
    public int calculateLevel(Integer xp) {
        int[] factors = new int[10000];
        int[] start = new int[10000];
        int[] end = new int[10000];
        
        int count = 2;
        factors[1] = 500;
        start[1] = 1;
        end[1] = 1;
        
        while (count < 10001) {
            factors[count] = factors[count - 1] + 500;
            start[count] = (factors[count] * ((count - 1) * (count - 1)));
            end[count] = (factors[count] * ((count - 1) * (count - 1)));
            
            if (xp >= start[count - 1] && xp <= end[count]) {
                return count;
            }
            count++;
        }
        
        return xp;
    }

    /**
     * Calcula a habilidade baseada no nível
     */
    public int calculateSkill(Integer level) {
        int factor = 0;
        int count = 0;
        int skill = 1;
        
        while (count < 10001) {
            factor = count + 12;
            
            if (skill == level) {
                return factor;
            }
            count += 2;
            skill++;
        }
        
        return level;
    }

    /**
     * Calcula o limite de HP baseado no nível
     */
    public int calculateMaxHealth(Integer level, Integer currentHealth) {
        int maxHealth = level * 10 + (level * 10);
        
        if (currentHealth >= maxHealth) {
            return maxHealth;
        } else {
            return currentHealth;
        }
    }

    /**
     * Calcula o limite de Mana baseado no nível
     */
    public int calculateMaxMana(Integer level, Integer currentMana) {
        int maxMana = level * 10 + (level * 20);
        
        if (currentMana >= maxMana) {
            return maxMana;
        } else {
            return currentMana;
        }
    }

    /**
     * Gera um nome de orc aleatório
     */
    public String generateOrcName(Integer id) {
        String names = "Gludbog;Ulgha;Kubguk;Duugh;Tobdakh;Zugrak;Drutrug;Thokgor;Zikgor;Bugrog;" +
                       "Brughnab;Mashnakh;Sugugh;Bludmuk;Sagdakh;Skagblug;Thughnab;Odbog;Ruglut;Garzug";
        List<String> nameList = Arrays.asList(names.split(";"));
        
        if (id > 0 && id <= nameList.size()) {
            return nameList.get(id - 1);
        }
        return nameList.get(0);
    }

    /**
     * Inicializa o baú/inventário do personagem
     */
    public void initializeInventory(String name) {
        this.playerName = name;
        String item1 = "1;Arma;1;1;100;1;Faca Pequena Atk 1 Dano 1 $100\n";
        String item2 = "2;Set;0;0;50;1;Roupas Simples Def 0 Absorve 0 $50\n";
        
        try {
            File dataDir = new File("orcdata");
            if (!dataDir.exists()) {
                dataDir.mkdir();
            }
            
            File file = new File("orcdata/" + name + ".items");
            file.createNewFile();
            
            FileWriter writer = new FileWriter(file);
            writer.write(item1);
            writer.write(item2);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insere um item no baú
     */
    public void addItemToInventory(String name, String item) {
        this.playerName = name;
        this.item = item;
        
        try {
            File file = new File("orcdata/" + name + ".items");
            FileWriter writer = new FileWriter(file, true);
            writer.write(item);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carrega um atributo do personagem
     */
    public String load(String name, int position) {
        this.playerName = name;
        String errorExit = "ERR";
        String okExit = "0";
        this.position = position;
        
        Console console = new Console();
        
        try {
            Scanner scanner = new Scanner(new FileReader("orcdata/" + name + ".gamesave"));
            scanner.useDelimiter(";");
            
            int count = 1;
            while (scanner.hasNext()) {
                String value = scanner.next();
                if ("DONE".equals(value)) {
                    scanner.close();
                    break;
                }
                if (count == position) {
                    scanner.close();
                    return value;
                }
                count++;
            }
        } catch (IOException e) {
            console.print("Personagem Não Encontrado.");
            console.pause();
            return errorExit;
        }
        
        return okExit;
    }

    /**
     * Carrega o baú do personagem
     */
    public String loadInventory(String name) {
        this.playerName = name;
        String errorExit = "ERR";
        String okExit = "0";
        String endExit = "FIM";
        String item;
        Integer itemId = 1;
        
        Console console = new Console();
        
        try {
            Scanner scanner = new Scanner(new FileReader("orcdata/" + name + ".items"));
            scanner.useDelimiter("\\n");
            
            while (scanner.hasNext()) {
                String line = scanner.next();
                if ("DONE".equals(line)) {
                    scanner.close();
                    return endExit;
                }
                
                List<String> itemData = Arrays.asList(line.split(";"));
                item = itemData.get(6);
                console.print("# [" + itemId + "] " + item);
                itemId++;
            }
            scanner.close();
        } catch (IOException e) {
            console.print("Baú Não Encontrado.");
            console.pause();
            return errorExit;
        }
        
        return okExit;
    }

    /**
     * Carrega um item equipado
     */
    public String loadEquippedItem(String name, String itemType, String parameter) {
        this.playerName = name;
        String param = parameter;
        String equippedFlag = "";
        String itemTypeToFind = itemType;
        String itemEquippedType = "";
        String itemName = "";
        String bonus1 = "";
        String bonus2 = "";
        String notFound = "N/A";
        String error = "ERR";
        
        Console console = new Console();
        
        try {
            Scanner scanner = new Scanner(new FileReader("orcdata/" + name + ".items"));
            scanner.useDelimiter("\\n");
            
            while (scanner.hasNext()) {
                String line = scanner.next();
                if ("DONE".equals(line)) {
                    if ("2".equals(itemTypeToFind)) {
                        scanner.close();
                        return "0";
                    } else if ("1".equals(itemTypeToFind)) {
                        scanner.close();
                        return notFound;
                    }
                }
                
                List<String> itemData = Arrays.asList(line.split(";"));
                equippedFlag = itemData.get(5);
                itemName = itemData.get(6);
                bonus1 = itemData.get(2);
                bonus2 = itemData.get(3);
                itemEquippedType = itemData.get(1);
                
                if (equippedFlag.equals("1") && itemTypeToFind.equals(itemEquippedType)) {
                    if ("1".equals(param)) {
                        scanner.close();
                        return itemName;
                    }
                    if ("2".equals(param)) {
                        scanner.close();
                        return bonus1;
                    }
                    if ("3".equals(param)) {
                        scanner.close();
                        return bonus2;
                    }
                    equippedFlag = "0";
                    itemTypeToFind = "";
                }
            }
            scanner.close();
        } catch (IOException e) {
            console.print("Baú Não Encontrado.");
            console.pause();
            return error;
        }
        
        if ("2".equals(itemTypeToFind)) {
            return "0";
        } else if ("1".equals(itemTypeToFind)) {
            return notFound;
        }
        return notFound;
    }

    /**
     * Vende um item do inventário
     */
    public Integer sellItem(String name, String itemId, Integer gold) {
        this.playerName = name;
        Integer goldAmount = gold;
        String idToRemove = itemId;
        Integer itemIdCount = 1;
        Integer errorExit = 9;
        
        Console console = new Console();
        
        try {
            File tempFile = new File("orcdata/" + name + ".items.temp");
            File originalFile = new File("orcdata/" + name + ".items");
            tempFile.createNewFile();
            
            FileWriter writer = new FileWriter(tempFile);
            Scanner scanner = new Scanner(new FileReader("orcdata/" + name + ".items"));
            scanner.useDelimiter("\\n");
            
            while (scanner.hasNext()) {
                String line = scanner.next();
                if ("DONE".equals(line)) {
                    scanner.close();
                    writer.close();
                    return goldAmount;
                }
                
                List<String> itemData = Arrays.asList(line.split(";"));
                String itemPrice = itemData.get(4);
                
                if (itemIdCount != Integer.parseInt(idToRemove)) {
                    writer.write(line + "\n");
                    goldAmount = goldAmount + Integer.parseInt(itemPrice);
                }
                
                itemIdCount++;
            }
            
            scanner.close();
            writer.flush();
            writer.close();
            
            boolean success = tempFile.renameTo(originalFile);
            if (success) {
                console.print("#");
            }
            
        } catch (IOException e) {
            console.print("Baú Não Encontrado.");
            console.pause();
            return errorExit;
        }
        
        return goldAmount;
    }

    /**
     * Equipa um item do inventário
     */
    public Integer equipItem(String name, String itemId) {
        this.playerName = name;
        String itemType = "N/A";
        Integer itemIdNum = Integer.parseInt(itemId);
        String itemRegistry;
        Integer itemIdCount = 1;
        Integer errorExit = 9;
        Integer successExit = 0;
        
        Console console = new Console();
        
        try {
            // Primeiro, determina o tipo do item
            Scanner scanner = new Scanner(new FileReader("orcdata/" + name + ".items"));
            scanner.useDelimiter("\\n");
            itemIdCount = 1;
            
            while (scanner.hasNext()) {
                String line = scanner.next();
                if ("DONE".equals(line)) {
                    scanner.close();
                    return successExit;
                }
                
                if (itemIdNum == itemIdCount) {
                    List<String> itemData = Arrays.asList(line.split(";"));
                    itemType = itemData.get(1);
                }
                itemIdCount++;
            }
            scanner.close();
            
            // Desativa todos os itens do tipo selecionado
            File tempFile = new File("orcdata/" + name + ".items.temp");
            File originalFile = new File("orcdata/" + name + ".items");
            tempFile.createNewFile();
            FileWriter writer = new FileWriter(tempFile);
            
            Scanner scanner2 = new Scanner(new FileReader("orcdata/" + name + ".items"));
            scanner2.useDelimiter("\\n");
            itemIdCount = 1;
            
            while (scanner2.hasNext()) {
                String line = scanner2.next();
                if ("DONE".equals(line)) {
                    writer.close();
                    scanner2.close();
                    return successExit;
                }
                
                List<String> itemData = Arrays.asList(line.split(";"));
                if (itemData.get(1).equals(itemType)) {
                    itemRegistry = itemData.get(0) + ";" + itemData.get(1) + ";" + 
                                  itemData.get(2) + ";" + itemData.get(3) + ";" + 
                                  itemData.get(4) + ";0;" + itemData.get(6);
                    writer.write(itemRegistry + "\n");
                    writer.flush();
                } else {
                    writer.write(line + "\n");
                }
                itemIdCount++;
            }
            
            scanner2.close();
            writer.close();
            boolean success = tempFile.renameTo(originalFile);
            
            // Ativa o item selecionado
            File tempFile2 = new File("orcdata/" + name + ".items.temp");
            File originalFile2 = new File("orcdata/" + name + ".items");
            tempFile2.createNewFile();
            FileWriter writer2 = new FileWriter(tempFile2);
            
            Scanner scanner3 = new Scanner(new FileReader("orcdata/" + name + ".items"));
            scanner3.useDelimiter("\\n");
            itemIdCount = 1;
            
            while (scanner3.hasNext()) {
                String line = scanner3.next();
                if ("DONE".equals(line)) {
                    writer2.close();
                    scanner3.close();
                    return successExit;
                }
                
                List<String> itemData = Arrays.asList(line.split(";"));
                
                if (itemIdCount == itemIdNum && itemData.get(1).equals(itemType)) {
                    itemRegistry = itemData.get(0) + ";" + itemData.get(1) + ";" + 
                                  itemData.get(2) + ";" + itemData.get(3) + ";" + 
                                  itemData.get(4) + ";1;" + itemData.get(6);
                    writer2.write(itemRegistry + "\n");
                    writer2.flush();
                } else {
                    writer2.write(line + "\n");
                }
                itemIdCount++;
            }
            
            scanner3.close();
            writer2.close();
            boolean success2 = tempFile2.renameTo(originalFile2);
            
        } catch (IOException e) {
            console.print("Baú Não Encontrado.");
            console.pause();
            return errorExit;
        }
        
        return successExit;
    }
}
