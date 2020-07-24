package com.jijiaxin.goods.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "tt_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aid;

    private String aname;

    private Integer pid;

    @Transient
    private List<Address> addressList;
}
