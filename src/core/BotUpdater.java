package core;

import core.tasks.RequestUpdateTask;
import inter.Initiable;
import core.CustomBot;
import utils.DataBaseDemo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BotUpdater  implements Initiable {
    private static final int CHECK_NEW_UPDATES_INITIAL_DELAY=0;
    private static final int CHECK_NEW_UPDATES_TIME_PERIOD=1;

    private CustomBot botInfo;
    private ScheduledExecutorService updaterScheduledExecutorService;

    private AtomicInteger lastUpdateId;

    public BotUpdater(CustomBot botInfo) {
        this.botInfo=botInfo;
    }

    private void initialize() throws InterruptedException {
        DataBaseDemo.LOGGER.log("SUCCESS ON TRYING TO START BOT "+botInfo.getFirstName()+"(ID: "+botInfo.getId()+")");
        DataBaseDemo.LOGGER.log("INITIALIZATING UPDATE MANAGER FOR BOT"+botInfo.getFirstName()+"(ID: "+botInfo.getId()+")");
        lastUpdateId=new AtomicInteger();
        updaterScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        Thread starterThread= new Thread(new RequestUpdateTask(true,lastUpdateId,botInfo));
        starterThread.start();
        starterThread.join();
        DataBaseDemo.LOGGER.log("SUCCESS ON INITIALIZATING UPDATE MANAGER FOR BOT"+botInfo.getFirstName()+"(ID: "+botInfo.getId()+")");
        DataBaseDemo.LOGGER.log("LAST UPDATE ID FOR BOT "+botInfo.getFirstName()+"(ID: "+botInfo.getId()+")"+" IS "+lastUpdateId);

    }

    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }

    public void start() {
        try {
            initialize();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DataBaseDemo.LOGGER.log("BEGINNING UPDATE SCHEDULE FOR BOT "+botInfo.getFirstName()+"(ID: "+botInfo.getId()+")");
        updaterScheduledExecutorService.scheduleAtFixedRate(new RequestUpdateTask(false,lastUpdateId,botInfo)
                , CHECK_NEW_UPDATES_INITIAL_DELAY, CHECK_NEW_UPDATES_TIME_PERIOD, TimeUnit.SECONDS);



    }

    @Override
    public void stop() {

    }

    @Override
    public void terminate() {

    }


}
