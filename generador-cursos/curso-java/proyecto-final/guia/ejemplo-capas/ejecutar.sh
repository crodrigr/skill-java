#!/usr/bin/env bash
# Uso: ./ejecutar.sh [archivo|memoria]     (compila todo y ejecuta el menú)
set -e
cd "$(dirname "$0")"
rm -rf out && mkdir out
javac -encoding UTF-8 -d out $(find src -name '*.java')
java -cp out com.tareas.Principal "${1:-archivo}"
