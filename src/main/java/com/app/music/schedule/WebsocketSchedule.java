
package com.app.music.schedule;

import com.app.music.config.MyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

@EnableScheduling
@Component
public class WebsocketSchedule {

    @Autowired
    private MyHandler myHandler;
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

//    @Scheduled(fixedDelay = 2000)
////    public void sendMsg() {
////        //myHandler.sendMessageToUser("888",new TextMessage("hxc"));
////        System.out.println(1111);
////        myHandler.sendMessageToAllUsers(new TextMessage("cccccccc"));
////    }

    public void schedule(Runnable task, String corn) {
        threadPoolTaskScheduler.schedule(task, new CronTrigger(corn));
    }
}
