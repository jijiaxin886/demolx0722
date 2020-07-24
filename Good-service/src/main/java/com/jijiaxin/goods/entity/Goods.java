package com.jijiaxin.goods.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tt_goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //主键
    private Integer gid;
    //商品名称
    private String gname;
    //pic
    private String pic;
    //单价
    private Double price;
    //生产地区
    private Integer provinceId;
    //促销类型
    private String chuanxiao;

    private Integer cityId;
    //上架时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "provinceId",insertable =false,updatable = false)
    private Address address;

    @ManyToOne
    @JoinColumn(name = "cityId",insertable =false,updatable = false)
    private Address addresss;

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinTable(name = "t_g_t",joinColumns = @JoinColumn(name = "gid"),inverseJoinColumns = @JoinColumn(name = "tid"))
    private List<Type> types;

    @Transient
    private Double startPrice;

    @Transient
    private Double endPrice;

}
