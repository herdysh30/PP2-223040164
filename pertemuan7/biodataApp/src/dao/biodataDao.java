package dao;

import db.MySqlConnection;

import java.lang.reflect.Member;
import java.sql.Connection;  
import java.sql.PreparedStatement;  
import java.sql.Statement;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.util.List;  
import java.util.ArrayList;  
import model.Biodata;

public class biodataDao {
    public int insert(Biodata biodata) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO biodata (nama, tanggal_lahir, jenis_kelamin, noTelepon) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
    
            statement.setString(1, biodata.getNama());
            statement.setString(2, biodata.getTanggalLahir());
            statement.setString(3, biodata.getJenisKelamin());
            statement.setString(4, biodata.getNoTelepon());
            result = statement.executeUpdate();
    
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    String newId = String.valueOf(generatedKeys.getInt(1));
                    biodata.setId(newId); 
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    

    public int update(Biodata biodata) {  
        int result = -1;  
        try(Connection connection = MySqlConnection.getInstance().getConnection()) {  
            PreparedStatement statement = connection.prepareStatement("update biodata set nama = ?, tanggal_lahir = ?, jenis_kelamin = ?, noTelepon = ? where id = ?");  
            statement.setString(1, biodata.getNama());  
            statement.setString(2, biodata.getTanggalLahir());
            statement.setString(3, biodata.getJenisKelamin());
            statement.setString(4, biodata.getNoTelepon());
            statement.setString(5, biodata.getId());  
            result = statement.executeUpdate();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return result;  
    } 

    public int delete(Biodata biodata) {  
        int result = -1;  
        try(Connection connection = MySqlConnection.getInstance().getConnection()) {  
            PreparedStatement statement = connection.prepareStatement("delete from biodata where id = ?");  
            statement.setString(1, biodata.getId());  
            result = statement.executeUpdate();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return result;  
    }
    public List<Biodata> findAll() {
        List<Biodata> list = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            Statement statement = connection.createStatement();
            try (ResultSet resultSet = statement.executeQuery("SELECT id, nama, tanggal_lahir, jenis_kelamin, noTelepon FROM biodata")) {
                while (resultSet.next()) {
                    Biodata biodata = new Biodata();
                    biodata.setId(String.valueOf(resultSet.getInt("id")));
                    biodata.setNama(resultSet.getString("nama"));
                    biodata.setTanggalLahir(resultSet.getString("tanggal_lahir"));
                    biodata.setJenisKelamin(resultSet.getString("jenis_kelamin"));
                    biodata.setNoTelepon(resultSet.getString("noTelepon"));
                    list.add(biodata);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    } 
}
