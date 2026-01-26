#!/bin/bash

# Orcs Game Launcher
# Script para executar o jogo

if [ ! -f "dist/Orcs.jar" ]; then
    echo "Erro: dist/Orcs.jar não encontrado!"
    echo ""
    echo "Execute primeiro:"
    echo "  ./build.sh"
    exit 1
fi

echo "🎮 Iniciando Orcs..."
echo ""
java -jar dist/Orcs.jar
