.PHONY: build run clean help

help:
	@echo "🗡️  Orcs - Text-Based RPG Game"
	@echo ""
	@echo "Comandos disponíveis:"
	@echo "  make build    - Compila o jogo e cria dist/Orcs.jar"
	@echo "  make run      - Executa o jogo"
	@echo "  make clean    - Remove arquivos compilados"
	@echo "  make help     - Mostra esta mensagem"
	@echo ""

build:
	@echo "Compilando..."
	mkdir -p build/classes dist
	javac -d build/classes -sourcepath src/main/java $$(find src/main/java -name '*.java')
	jar cfm dist/Orcs.jar MANIFEST.MF -C build/classes .
	@echo "✓ Build completo! Execute: make run"

run:
	@if [ -f "Orcs.jar" ]; then \
		java -jar dist/Orcs.jar; \
	else \
		echo "Erro: dist/Orcs.jar não encontrado. Execute: make build"; \
		exit 1; \
	fi

clean:
	rm -rf build dist *.class Orcs.jar
	@echo "✓ Arquivos compilados removidos"
