#!/bin/bash

# Orcs Game Launcher
# Script para executar o jogo

if [ ! -f "Orcs.jar" ]; then
    echo "Erro: Orcs.jar não encontrado!"
    echo ""
    echo "Execute primeiro:"
    echo "  ./build.sh"
    exit 1
fi

echo "🎮 Iniciando Orcs..."
echo ""
java -jar Orcs.jar
