package com.fmdgroup.vatcher.services;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fmdgroup.vatcher.model.Notifications;
import com.fmdgroup.vatcher.model.SingleUser;

@RunWith(SpringRunner.class)

public class NotificationServiceImplTest {


    @MockBean
    private ConcurrentHashMap<Long, BlockingQueue<Notifications>> notificationQueueMap;

    @Autowired
    private NotificationServiceImpl notificationServiceImpl;

    @Test
    public void testSendNotification() {
        // create a mock BlockingQueue for a user
        BlockingQueue<Notifications> notificationQueue = new LinkedBlockingQueue<>();
        when(notificationQueueMap.computeIfAbsent(anyLong(), key -> new LinkedBlockingQueue<>())).thenReturn(notificationQueue);

        // send a notification to the user
        SingleUser receiver1 = new SingleUser();
        Long id = 1L;
        receiver1.setId(id);
        Date timestamp1 = new Date();
        Notifications notification1 = new Notifications("Test notification.", receiver1, timestamp1 , false);
        notificationServiceImpl.sendNotification(id, notification1);

        // verify that the notification was added to the mock queue
        verify(notificationQueueMap).computeIfAbsent(id, key -> new LinkedBlockingQueue<>());
        verify(notificationQueue).offer(notification1);
    }

}
