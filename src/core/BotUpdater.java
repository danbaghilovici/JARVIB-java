package core;

import core.tasks.RequestUpdateTask;
import inter.Initiable;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BotUpdater  implements Initiable {
    private static final int CHECK_NEW_UPDATES_INITIAL_DELAY=0;
    private static final int CHECK_NEW_UPDATES_TIME_PERIOD=1;
    private final String TAG;

    private CustomBot botInfo;
    private ScheduledExecutorService updaterScheduledExecutorService;

    private AtomicInteger lastUpdateId;

    public BotUpdater(CustomBot botInfo) {
        this.botInfo=botInfo;
        TAG=botInfo.getFirstName()+" ("+botInfo.getId()+")"+" UPDATER";
    }


    @Override
    public void init() {
        lastUpdateId=new AtomicInteger();
        updaterScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        Thread starterThread= new Thread(new RequestUpdateTask(true,lastUpdateId,botInfo));
        starterThread.start();
        try {
            starterThread.join();
            Launcher.LOGGER.consoleLog(TAG,"SUCCESS ON GETTING THE LAST UPDATE ID");
        } catch (InterruptedException e) {
            Launcher.LOGGER.consoleLog(TAG,true,"THREAD NOT JOINING"+e.getMessage()+". EXITING...");
            terminate();
        }
    }

    @Override
    public void destroy() {

    }

    public void start() {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Launcher.LOGGER.consoleLog(TAG,"BEGINNING UPDATE SCHEDULE");
        updaterScheduledExecutorService.scheduleAtFixedRate(new RequestUpdateTask(false,lastUpdateId,botInfo)
                , CHECK_NEW_UPDATES_INITIAL_DELAY, CHECK_NEW_UPDATES_TIME_PERIOD, TimeUnit.SECONDS);



    }

    @Override
    public void stop() {
        updaterScheduledExecutorService.shutdown();
    }

    @Override
    public void terminate() {

    }


}
