package model;

import java.util.List;
import org.apache.ibatis.annotations.*;

public interface  CustomerMapper {
    @Select("SELECT * FROM customers")
    List<Customer> getAllUsers();

    @Insert("INSERT INTO customers (nama,email,noTelp,alamat) VALUES (#{nama}, #{email}, #{noTelp}, #{alamat})")
    void insertUser(Customer customer);

    @Update("UPDATE customers SET nama = #{nama}, email = #{email}, noTelp = #{noTelp}, alamat = #{alamat} WHERE id = #{id}")
    void updateUser(Customer customer); 

    @Delete("DELETE FROM customers WHERE id = #{id}")
    void deleteUser(int id);
}
