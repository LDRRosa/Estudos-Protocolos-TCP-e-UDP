Sistema de Consulta de Horário Mundial (TCP & UDP)
Este projeto consiste em três implementações de um sistema cliente-servidor para consulta de data e hora baseada em fusos horários (ZoneId), explorando diferentes protocolos e modelos de concorrência em Java.

Autor: Leandro Rosa da Silva

Data: 20/03/2026

🛠️ Instruções de Execução
Para todos os comandos abaixo, abra o terminal na pasta raiz do projeto (geralmente a pasta src).

Versão 1: UDP (Sem Conexão)
Compilar: javac UDP/*.java

Rodar Servidor: java UDP.Servidor

Rodar Cliente: java UDP.Cliente (em um novo terminal)

Versão 2: TCP Single-Thread (Conexão Sequencial)
Compilar: javac TCPSingleThread/*.java

Rodar Servidor: java TCPSingleThread.TCPServer

Rodar Cliente: java TCPSingleThread.TCPClient (em um novo terminal)

Versão 3: TCP Multithread (Conexão Simultânea)
Compilar: javac TCPMultithread/*.java

Rodar Servidor: java TCPMultithread.TCPServerMulti

Rodar Cliente: java TCPMultithread.TesteCliente (em um novo terminal)

🔬 Análise Técnica: Performance e Concorrência
A principal diferença entre a Versão 2 (Single-Thread) e a Versão 3 (Multithread) reside na forma como o servidor gerencia a fila de conexões.

Versão 2: TCPSingleThread
Nesta versão, o servidor utiliza um loop único que aceita uma conexão, processa a requisição e fecha o socket antes de voltar ao topo do loop para aceitar o próximo cliente.

Impacto: Se um cliente demorar para enviar o fuso horário ou se a rede estiver lenta, todos os outros clientes na fila ficarão bloqueados no método accept().

Performance: Baixa escalabilidade. O tempo de resposta para o N-ésimo cliente é a soma dos tempos de processamento de todos os clientes anteriores.

Versão 3: TCPMultithread
Aqui, o servidor principal apenas "escuta" e aceita a conexão. No momento em que o clientSocket é criado, ele é passado para uma nova instância de ClienteHandler (que estende Thread), que executa de forma independente.

Impacto: O loop principal do servidor é liberado imediatamente para aceitar o próximo cliente, enquanto o processamento real ocorre em paralelo.

Performance: Alta escalabilidade. Múltiplos clientes recebem suas respostas quase simultaneamente, limitados apenas pelo hardware (CPU/Memória) do servidor.
