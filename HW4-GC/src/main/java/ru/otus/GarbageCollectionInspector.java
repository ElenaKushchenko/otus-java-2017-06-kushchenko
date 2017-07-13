package ru.otus;

import com.sun.management.GarbageCollectionNotificationInfo;
import com.sun.management.GcInfo;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * Created by Elena on 06.07.2017.
 */
public class GarbageCollectionInspector {

    public void run() {
        final List<GarbageCollectorMXBean> gcMXBeans = ManagementFactory.getGarbageCollectorMXBeans();

        gcMXBeans.forEach(gcBean -> {
            final NotificationEmitter emitter = (NotificationEmitter) gcBean;

            final NotificationListener listener = (notification, handback) -> {
                if (GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION.equals(notification.getType())) {
                    final GcInfo gcInfo = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData()).getGcInfo();

                    System.out.print("\u001B[31m");     // red color
                    System.out.println(gcBean.getName());
                    System.out.println("Collection Count " + gcBean.getCollectionCount());
                    System.out.println("Collection Duration " + gcInfo.getDuration());
                    System.out.println("Collection Time " + gcBean.getCollectionTime());
                    System.out.print("\u001B[0m");      //reset color
                }
            };

            emitter.addNotificationListener(listener, null, null);
        });
    }
}
