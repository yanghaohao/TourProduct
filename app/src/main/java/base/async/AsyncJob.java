package base.async;


import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by luowp on 2016/7/12.
 * will be run on background thread random
 */
public abstract class AsyncJob implements Runnable {
    protected AtomicBoolean mStop = new AtomicBoolean(false);

    @Override
    public abstract void run();

    public final void start() {
        if (!checkValid()) {
            return;
        }

        Async.run(this);
    }

    public final void stop() {
        mStop.set(true);
    }

    public final void startDelay(long delay) {
        if (!checkValid()) {
            return;
        }

        Async.schedule(delay, this);
    }

    //allow subclass to check start environment
    protected boolean checkValid() {
        return true;
    }
}
