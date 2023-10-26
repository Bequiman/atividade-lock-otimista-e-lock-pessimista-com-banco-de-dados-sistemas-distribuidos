package testecarga;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(100);

        // Execute as chamadas concorrentes
        for (int i = 0; i < 100; i++) {
            executor.execute(new HttpRequestTask());
        }
        // Encerre o pool de threads após a conclusão
        executor.shutdown();
    }
}


/*

ExecutorService executor = Executors.newFixedThreadPool(100); cria um pool de threads com um tamanho fixo de 100 threads
usando a classe ExecutorService.

ExecutorService: ExecutorService é uma interface no Java que fornece um framework para execução assíncrona de tarefas
(como threads). Ele é parte do pacote java.util.concurrent e fornece métodos para criar, gerenciar e controlar tarefas
em threads.

Executors: Executors é uma classe utilitária que fornece métodos estáticos para criar diferentes tipos de objetos
ExecutorService, incluindo pools de threads. Nesse caso, estamos usando o método newFixedThreadPool, que cria um pool de
threads com um tamanho fixo.

newFixedThreadPool(100): Este método cria um pool de threads com um tamanho fixo de 100 threads. Isso significa que, no
máximo, 100 threads estarão ativas simultaneamente no pool. Quando você envia tarefas para esse pool, ele distribuirá as
tarefas entre essas 100 threads disponíveis. Se todas as threads estiverem ocupadas, as tarefas adicionais serão
enfileiradas até que uma thread esteja livre.

O uso de um pool de threads é uma maneira eficaz de gerenciar a concorrência e paralelizar tarefas. Ele ajuda a evitar o
desperdício de recursos ao limitar o número de threads ativas, tornando mais fácil controlar e ajustar a concorrência
conforme necessário. Nesse caso, o pool de threads é criado com 100 threads, o que permite realizar 100 chamadas
simultâneas de requisições HTTP.


 */