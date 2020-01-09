//package com.obit.goaltracker.entity;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//import org.hibernate.annotations.GenericGenerator;
//
//@Entity
//@Data
//@NoArgsConstructor
//@ToString
//public class Record {
//
//    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//    private String uid;
//
//    private BigDecimal value;
//    private LocalDate recordDate;
//}
