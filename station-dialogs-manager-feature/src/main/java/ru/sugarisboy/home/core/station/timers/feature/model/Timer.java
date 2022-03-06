package ru.sugarisboy.home.core.station.timers.feature.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.OffsetDateTime;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "timer")
public class Timer {

    @Id
    @GeneratedValue
    private UUID id;

    private OffsetDateTime start;

    private OffsetDateTime finish;

    private String name;

    private boolean notify;
}
