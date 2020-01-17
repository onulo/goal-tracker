package com.obit.goaltracker.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@NoArgsConstructor
@ToString
public class Goal {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String uid;

    private String description;
    private BigDecimal initialValue;
    private BigDecimal goalValue;
    private LocalDate goalDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "clientId", nullable = false)
    private Client client;

    //TODO refactor

    @Transient
    private List<Record> records;


}
