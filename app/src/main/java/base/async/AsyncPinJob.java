package base.async;


/**
 * Created by luowp on 2016/7/12.
 * all AsyncPinJob will run in same background thread
 */
public abstract class AsyncPinJob implements Runnable {

    @Override
    public void run() {
    }

    public void start() {
        Async.scheduleInQueue(this);
    }

    public void startDelay(long delay) {
        Async.schedule(delay, new Runnable() {
            @Override
            public void run() {
                Async.scheduleInQueue(this);
            }
        });
    }
}
