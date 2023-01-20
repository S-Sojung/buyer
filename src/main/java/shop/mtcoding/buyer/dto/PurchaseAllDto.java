package shop.mtcoding.buyer.dto;

import lombok.Getter;
import lombok.Setter;

//DTO는 화면에 뿌려줄 데이터베이스
@Getter
@Setter
public class PurchaseAllDto {
    private int id;
    private String username;
    private String name;
    private int count;

}
