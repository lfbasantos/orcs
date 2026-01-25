#!/bin/bash

# Orcs Game Builder
# Script para compilar e empacotar o jogo em um JAR executável

echo "========================================="
echo "   ORCS - Text-Based RPG Game Builder"
echo "========================================="
echo ""

# Cores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Limpar arquivos antigos
echo -e "${YELLOW}[1/3]${NC} Limpando arquivos compilados antigos..."
rm -f *.class Orcs.jar
echo -e "${GREEN}✓${NC} Arquivos antigos removidos"
echo ""

# Compilar
echo -e "${YELLOW}[2/3]${NC} Compilando código Java..."
javac Orcs.java
if [ $? -eq 0 ]; then
    echo -e "${GREEN}✓${NC} Compilação bem-sucedida"
else
    echo -e "${RED}✗${NC} Erro na compilação"
    exit 1
fi
echo ""

# Criar JAR
echo -e "${YELLOW}[3/3]${NC} Empacotando em JAR executável..."
jar cfm Orcs.jar MANIFEST.MF *.class
if [ $? -eq 0 ]; then
    echo -e "${GREEN}✓${NC} JAR criado com sucesso"
else
    echo -e "${RED}✗${NC} Erro ao criar JAR"
    exit 1
fi
echo ""

# Informações finais
echo "========================================="
echo -e "${GREEN}Build concluído com sucesso!${NC}"
echo "========================================="
echo ""
echo "Para rodar o jogo, execute:"
echo -e "${YELLOW}  java -jar Orcs.jar${NC}"
echo ""
