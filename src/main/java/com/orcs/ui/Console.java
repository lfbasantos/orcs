package com.orcs.ui;

import java.io.*;

/**
 * Classe responsável pela interação com o usuário via console.
 * Gerencia entrada, saída e formatação de texto.
 */
public class Console {
    
    private String message = "";
    
    /**
     * Define uma nova mensagem a ser exibida
     * @param message A mensagem a ser definida
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Exibe a mensagem atual no console
     */
    public void print() {
        System.out.println(message);
    }

    /**
     * Exibe uma mensagem customizada
     * @param message A mensagem a exibir
     */
    public void print(String message) {
        System.out.println(message);
    }
    
    /**
     * Faz uma pergunta ao usuário e retorna a resposta
     * @return A resposta do usuário
     */
    public String input() {
        java.io.Console sysConsole = System.console();
        if (sysConsole != null) {
            return sysConsole.readLine("#-> ");
        }
        // Fallback para entrada padrão
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            return br.readLine();
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * Faz uma pergunta e aguarda resposta do usuário
     * @param question A pergunta a fazer
     * @return A resposta do usuário
     */
    public String input(String question) {
        java.io.Console sysConsole = System.console();
        if (sysConsole != null) {
            return sysConsole.readLine(question + " ");
        }
        // Fallback
        print(question);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            return br.readLine();
        } catch (IOException e) {
            return "";
        }
    }
    
    /**
     * Aguarda o usuário pressionar ENTER para continuar
     * @return A entrada do usuário
     */
    public String pause() {
        java.io.Console sysConsole = System.console();
        if (sysConsole != null) {
            return sysConsole.readLine("Tecle ENTER para continuar...");
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            return br.readLine();
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * Limpa a tela do console
     */
    public void clear() {
        // Imprime linhas em branco
        for (int i = 0; i < 50; ++i) {
            System.out.print("\n");
        }
        
        // Tenta limpar via comando do sistema
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (InterruptedException | IOException e) {
            // Ignorar se não conseguir limpar
        }
    }

    /**
     * Aguarda um segundo
     */
    public void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Aguarda um segundo (1000ms)
     */
    public void sleep() {
        sleep(1000);
    }
}
