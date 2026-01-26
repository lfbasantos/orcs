# 🔧 Troubleshooting - Orcs Game

Guia para resolver problemas comuns ao jogar ou compilar o jogo.

---

## ⚠️ Problemas Comuns

### "Orcs.jar not found" ou "JAR não encontrado"

**Problema:** Tentou executar o jogo sem compilar antes.

**Solução:**
```bash
# Opção 1
./build.sh

# Opção 2
make build

# Opção 3
javac Orcs.java
jar cfm Orcs.jar MANIFEST.MF *.class
```

---

### "command not found: java"

**Problema:** Java não está instalado ou não está no PATH.

**Solução:**
```bash
# Verificar se Java está instalado
java -version

# Se não tiver, instalar:
# Ubuntu/Debian
sudo apt-get install default-jdk

# MacOS
brew install openjdk

# Ou download em: https://www.oracle.com/java/technologies/downloads/
```

---

### "Erro ao criar diretório orcdata"

**Problema:** Sem permissão de escrita no diretório do jogo.

**Solução:**
```bash
# Dar permissões ao diretório
chmod 755 .

# Ou executar de um diretório com permissões
mkdir ~/Orcs
cp -r /path/to/orcs/* ~/Orcs
cd ~/Orcs
java -jar Orcs.jar
```

---

### Jogo não responde ao input

**Problema:** Console não está em modo interativo.

**Solução:**
```bash
# Tente um destes comandos:

# Opção 1
java -jar Orcs.jar

# Opção 2
java -cp Orcs.jar Orcs

# Opção 3 (Com stdin explícito)
java -jar Orcs.jar < /dev/stdin
```

---

### "FileNotFoundException" ao carregar personagem

**Problema:** Arquivo de save corrompido ou deletado.

**Solução:**
```bash
# Verificar arquivo
cat orcdata/seu_personagem.gamesave

# Se estiver vazio ou corrompido, deletar e recriar
rm orcdata/seu_personagem.gamesave
rm orcdata/seu_personagem.items

# Ao jogar, crie um novo personagem com mesmo nome
```

---

### Jogo muito lento

**Problema:** Sistema sobrecarregado ou Java com pouca memória.

**Solução:**
```bash
# Dar mais memória ao Java
java -Xmx256m -jar Orcs.jar

# Ou fechar outros programas
```

---

### Caracteres estranhos na tela

**Problema:** Encoding de console incorreto.

**Solução:**
```bash
# Linux/Mac
export LANG=pt_BR.UTF-8
java -jar Orcs.jar

# Windows (PowerShell)
[System.Environment]::SetEnvironmentVariable('LANG', 'pt_BR.UTF-8', 'User')
```

---

### Erro "Main-Class not found"

**Problema:** MANIFEST.MF não está configurado corretamente.

**Solução:**
```bash
# Verificar o arquivo
cat MANIFEST.MF

# Deve conter:
# Main-Class: Orcs
# Manifest-Version: 1.0

# Se estiver errado, deletar e recriar
rm MANIFEST.MF
echo "Main-Class: Orcs" > MANIFEST.MF
echo "Manifest-Version: 1.0" >> MANIFEST.MF

# Recompilar
make clean && make build
```

---

## 🔍 Debug

### Ativar modo verbose
```bash
java -verbose:class -jar Orcs.jar 2>&1 | grep -i "orcs"
```

### Verificar estrutura do JAR
```bash
jar tf Orcs.jar
```

### Extrair JAR para inspeção
```bash
jar xf Orcs.jar
ls -la
```

### Testar compilação individual
```bash
javac -verbose Orcs.java
```

---

## 💡 Dicas de Performance

### 1. Compilação mais rápida
```bash
# Usar apenas o arquivo necessário
javac -target 8 Orcs.java
```

### 2. JAR mais compacto
```bash
jar cf Orcs.jar *.class
# Remover MANIFEST se não for usar
```

### 3. Monitorar recursos
```bash
# Em outra aba
watch 'ps aux | grep java'
```

---

## 📋 Checklist de Diagnóstico

Antes de reportar um bug, verificar:

- [ ] Java 8+ está instalado? (`java -version`)
- [ ] Arquivo Orcs.jar existe? (`ls -la Orcs.jar`)
- [ ] JAR pode ser extraído? (`jar tf Orcs.jar`)
- [ ] Main-Class está no MANIFEST? (`jar xf Orcs.jar META-INF/MANIFEST.MF && cat META-INF/MANIFEST.MF`)
- [ ] Código foi compilado? (`ls *.class`)
- [ ] Há espaço em disco? (`df -h`)
- [ ] Permissões corretas? (`ls -la | grep Orcs`)

---

## 🆘 Quando Pedir Ajuda

Se nenhuma solução funcionar, forneça:

1. **Comando executado:**
   ```bash
   ./run.sh
   ```

2. **Saída completa (com erro):**
   ```bash
   java -jar Orcs.jar 2>&1
   ```

3. **Informações do sistema:**
   ```bash
   java -version
   uname -a
   pwd
   ls -la
   ```

4. **Conteúdo relevante:**
   ```bash
   cat MANIFEST.MF
   jar tf Orcs.jar | head
   ```

---

## 📞 Contatos Úteis

- **Documentação Java:** https://docs.oracle.com/javase/
- **Comunidade Java:** https://stackoverflow.com/questions/tagged/java
- **JAR Specification:** https://docs.oracle.com/javase/8/docs/technotes/guides/jar/

---

**Última atualização:** Janeiro 2026

> Problema não listado aqui? Abra uma issue no repositório! 🐛
