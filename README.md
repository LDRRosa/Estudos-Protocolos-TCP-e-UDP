🌍 Sistema de Consulta de Horário Mundial (TCP & UDP)

Este projeto implementa um sistema cliente-servidor para consulta de data e hora com base em fusos horários (ZoneId), explorando diferentes protocolos de rede e modelos de concorrência em Java.

O objetivo é demonstrar, na prática, as diferenças entre:

Comunicação sem conexão (UDP)

Comunicação orientada à conexão (TCP)

Execução sequencial vs concorrente (multithread)

👨‍💻 Autor

Leandro Rosa da Silva

📅 Data: 20/03/2026

🚀 Como Executar

Abra o terminal na pasta raiz do projeto (geralmente src).

📡 Versão 1: UDP (Sem Conexão)
javac UDP/*.java
java UDP.Servidor

Em outro terminal:

java UDP.Cliente
🔗 Versão 2: TCP Single-Thread (Sequencial)
javac TCPSingleThread/*.java
java TCPSingleThread.TCPServer

Em outro terminal:

java TCPSingleThread.TCPClient
⚡ Versão 3: TCP Multithread (Concorrente)
javac TCPMultithread/*.java
java TCPMultithread.TCPServerMulti

Em outro terminal:

java TCPMultithread.TesteCliente
🔬 Análise Técnica: Performance e Concorrência

A principal diferença entre as versões TCP está na forma como o servidor lida com múltiplas conexões.

🧵 TCP Single-Thread

Nesta abordagem, o servidor:

Aceita uma conexão (accept())

Processa a requisição

Encerra o socket

Volta a aguardar o próximo cliente

⚠️ Impacto

Clientes são atendidos um por vez

Outros clientes ficam bloqueados na fila

📉 Performance

Baixa escalabilidade

O tempo de resposta cresce linearmente com o número de clientes

🚀 TCP Multithread

Nesta versão, o servidor:

Aceita a conexão

Cria uma nova Thread para processar o cliente

Volta imediatamente para o accept()

⚡ Impacto

Múltiplos clientes são atendidos simultaneamente

O servidor não bloqueia enquanto processa requisições

📈 Performance

Alta escalabilidade

Respostas quase simultâneas

Limitado apenas por CPU e memória
