package shop.mtcoding.buyer.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/*
 * 회원가입, 로그인, 회원탈퇴, 회원수정
 */
@Mapper
public interface UserRepository { // 만약 무슨 기능을 만들어야할 지 모르겠다면?? CRUD만 만들어주면 된다.
    public int insert(@Param("username") String username, @Param("password") String password,
            @Param("email") String email);

    public List<User> findAll();

    public User findById(int id);

    // 한 건은 Param 을 붙이지 않아도 알아서 찾음
    public int updateById(@Param("id") int id, @Param("password") String password);

    public int deleteById(int id);
    // public int insert(@Param("username") String username, @Param("password")
    // String password,
    // @Param("email") String email);

    public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    // public User login(@Param("username") String username, @Param("password")
    // String password);
    // // findByUsernameAndPassword

    // public int delete(@Param("id") int id);

    // public int updatePassword(@Param("id") int id, @Param("password") String
    // password);
}
