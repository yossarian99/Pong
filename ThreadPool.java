/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yossarian
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yossarian
 */
import java.util.LinkedList;

/**
    Pula w¹tków stanowi grupê z okrelon¹ liczb¹ w¹tków,
    które mog¹ byæ u¿ywane do wykonywania zadañ.
*/
public class ThreadPool extends ThreadGroup {

    private boolean isAlive;
    private LinkedList taskQueue;
    private int threadID;
    private static int threadPoolID;

    /**
        Tworzy nowy obiekt ThreadPool.
        @param numThreads Liczba w¹tków w puli.
    */
    public ThreadPool() {
        super("ThreadPool-" + (threadPoolID++));
        setDaemon(true);

        
        isAlive = true;

        taskQueue = new LinkedList();
        
            new PooledThread().start();
        
    }


    /**
        Powoduje uruchomienie nowego zadania. Metoda ta natychmiast siê koñczy,
        a zadanie jest wykonywane przez nastêpny wolny w¹tek z ThreadPool.
        <p>Zadania s¹ wykonywane w kolejnoci ich uruchomienia.
        @param task Zadanie do wykonania. Je¿eli ma wartoæ null, 
        nie jest podejmowana ¿adna akcja.
        @throws IllegalStateException - w przypadku, gdy ten obiekt
        ThreadPool jest zamkniêty.
    */
    public synchronized void runTask(Runnable task) {
        if (!isAlive) {
            throw new IllegalStateException();
        }
        if (task != null) {
            taskQueue.add(task);
            notify();
        }

    }


    protected synchronized Runnable getTask()
        throws InterruptedException
    {
        while (taskQueue.size() == 0) {
            if (!isAlive) {
                return null;
            }
            wait();
        }
        return (Runnable)taskQueue.removeFirst();
    }


    /**
        Zamyka ten obiekt ThreadPool i koñczy pracê. Wszystkie zadania s¹ 
        zatrzymywane i nie zostanie uruchomiony ¿aden w¹tek oczekuj¹cy.
        Po zamkniêciu obiektu ThreadPool nie mo¿e on ju¿ 
        uruchamiaæ ¿adnych zadañ.
    */
    public synchronized void close() {
        if (isAlive) {
            isAlive = false;
            taskQueue.clear();
            interrupt();
        }
    }


    /**
        Zamyka tan obiekt ThreadPool i czeka na zakoñczenie wszystkich 
        dzia³aj¹cych w¹tków. Wykonywane s¹ wszystkie oczekuj¹ce w¹tki.
    */
    public void join() {
        // Powiadamia wszystkie oczekuj¹ce w¹tki, ¿e bie¿¹cy ThreadPool
        // zosta³ wy³¹czony.
        synchronized (this) {
            isAlive = false;
            notifyAll();
        }

        // Oczekiwanie na zakoñczenie wszystkich w¹tków.
        Thread[] threads = new Thread[activeCount()];
        int count = enumerate(threads);
        for (int i=0; i<count; i++) {
            try {
                threads[i].join();
            }
            catch (InterruptedException ex) { }
        }
    }


    /**
        PooledThread jest w¹tkiem z grupy ThreadPool, 
        który mo¿e wykonywaæ zadania (Runnable).
    */
    private class PooledThread extends Thread {


        public PooledThread() {
            super(ThreadPool.this,
                "PooledThread-" + (threadID++));
        }


        public void run() {
            while (!isInterrupted()) {

                // Pobranie zadania do wykonania.
                Runnable task = null;
                try {
                    task = getTask();
                }
                catch (InterruptedException ex) { }

                // Je¿eli getTask() zwróci null lub zostanie 
                // przerwany, w¹tek zostanie zamkniêty.
                if (task == null) {
                    return;
                }

                // Uruchamia w¹tek i zbiera wszystkie zg³oszone 
                // przez niego wyj¹tki.
                try {
                    task.run();
                }
                catch (Throwable t) {
                    uncaughtException(this, t);
                }
            }
        }
    }
}

