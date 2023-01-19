package shop.mtcoding.buyer.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PurchaseRepository {
    public int insert(@Param("user_id") int userId, @Param("product_id") int productId, @Param("count") int count);

    public List<Purchase> findAll();

    public Purchase findById(int id);

    public int updateById(@Param("id") int id, @Param("user_id") int userId, @Param("product_id") int productId);

    public int deleteById(int id);

    public List<Purchase> findByUserId(@Param("user_id") int user_id);
}
