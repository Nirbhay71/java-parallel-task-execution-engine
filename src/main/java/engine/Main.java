package engine;

public class Main {

    public static void main(String[] args) {

        ExecutionEngine engine = new ExecutionEngine(3);

        for(int i=1;i<=10;i++) {

            int taskId = i;

            engine.submitTask(() -> {

                System.out.println(
                    "Executing Task " + taskId +
                    " by " + Thread.currentThread().getName()
                );

                try {
                    Thread.sleep(2000);
                } catch(Exception e) {}

            });
        }
    }
}