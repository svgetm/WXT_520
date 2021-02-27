package ru.getmanenko.meteorologicaldata.core;

import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.global.Events;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import ru.getmanenko.meteorologicaldata.MeteoEvent;
import ru.getmanenko.meteorologicaldata.entity.MeteoData;

import javax.inject.Inject;
import java.util.UUID;

@Component(MeteoDbUpdate.NAME)
public class MeteoDbUpdate {
    public static final String NAME = "meteorologicaldata_MeteoDbUpdate";

    @Inject
    private Events events;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)

    public void beforeCommit(EntityChangedEvent<MeteoData, UUID> event) {

        EntityChangedEvent.Type typeEvent = event.getType();

        if (typeEvent == EntityChangedEvent.Type.CREATED) {

            events.publish(new MeteoEvent(this));

        }
    }
}