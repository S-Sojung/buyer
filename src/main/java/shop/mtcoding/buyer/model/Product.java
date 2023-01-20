package shop.mtcoding.buyer.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.buyer.util.DateUtil;

@Getter
@Setter
public class Product {
    private int id;
    private String name;
    private int price;
    private int qty;
    private Timestamp createdAt;

    // getCreatedAt 과 따로인 getCreatedAtToString
    public String getCreatedAtToString() {
        return DateUtil.format(createdAt);
    }
}
