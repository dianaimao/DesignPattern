package base.concurrent_read_write;

public class WriterWorker extends Thread {
    private int index = 0;
    private String filler;
    private SharedData data;

    public WriterWorker(SharedData data,String filler) {
        this.filler = filler;
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char c = nextChar();
                data.write(c);
                sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //循环读字符串的字符
    private char nextChar() {
        char c = filler.charAt(index);
        index++;
        if (index >= filler.length()) {
            index = 0;
        }
        return c;
    }
}
