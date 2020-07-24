package com.jijiaxin.goods.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tt_type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;
    private String tname;
}
