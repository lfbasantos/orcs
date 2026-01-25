.PHONY: build run clean help

help:
	@echo "🗡️  Orcs - Text-Based RPG Game"
	@echo ""
	@echo "Comandos disponíveis:"
	@echo "  make build    - Compila o jogo e cria Orcs.jar"
	@echo "  make run      - Executa o jogo"
	@echo "  make clean    - Remove arquivos compilados"
	@echo "  make help     - Mostra esta mensagem"
	@echo ""

build:
	@echo "Compilando..."
	javac Orcs.java
	jar cfm Orcs.jar MANIFEST.MF *.class
	@echo "✓ Build completo! Execute: make run"

run:
	@if [ -f "Orcs.jar" ]; then \
		java -jar Orcs.jar; \
	else \
		echo "Erro: Orcs.jar não encontrado. Execute: make build"; \
		exit 1; \
	fi

clean:
	rm -f *.class Orcs.jar
	@echo "✓ Arquivos compilados removidos"
