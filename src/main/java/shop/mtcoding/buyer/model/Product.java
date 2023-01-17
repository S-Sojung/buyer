package shop.mtcoding.buyer.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Integer id;
    private String name;
    private int price;
    private int qty;
    private Timestamp createdAt;
}
