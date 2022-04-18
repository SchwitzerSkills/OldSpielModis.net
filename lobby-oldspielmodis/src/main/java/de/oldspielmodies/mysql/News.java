package de.oldspielmodies.mysql;

import de.oldspielmodies.lobby.Lobbysystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class News {

    private MySQL mySQL;

    public void createTables(){
        this.mySQL = Lobbysystem.getInstance().getMySQL();
        mySQL.update("CREATE TABLE IF NOT EXISTS News (ID VARCHAR(100), NACHRICHT VARCHAR(100), DATUM VARCHAR(100))");
    }

    public void addNews(int id, String nachricht, String datum){
        this.mySQL = Lobbysystem.getInstance().getMySQL();
        mySQL.update("INSERT INTO News (ID, NACHRICHT, DATUM) VALUES ('" + id + "', '" + nachricht + "', '" + datum + "')");
    }

    public void removeNews(int id){
        this.mySQL = Lobbysystem.getInstance().getMySQL();
        mySQL.update("DELETE FROM News WHERE ID=?", id);
    }

    public void updateNews(int id, String nachricht, String datum){
        this.mySQL = Lobbysystem.getInstance().getMySQL();
        mySQL.update("UPDATE News SET NACHRICHT=?, DATUM=? WHERE ID=?", nachricht, datum, id);
    }

    public boolean isExistsNews(int id){
        this.mySQL = Lobbysystem.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT COUNT(*) AS count FROM News WHERE ID=?", id)){
            if(rs.next()){
                return rs.getInt("count") != 0;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean isExistsNews(){
        this.mySQL = Lobbysystem.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT COUNT(*) AS count FROM News")){
            if(rs.next()){
                return rs.getInt("count") != 0;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean isExistsNewsID(int id){
        this.mySQL = Lobbysystem.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT COUNT(*) AS count FROM News WHERE ID=?", id)){
            if(rs.next()){
                return rs.getInt("count") != 0;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public List<String> getUpdates(){
        this.mySQL = Lobbysystem.getInstance().getMySQL();
        List<String> list = new ArrayList<>();
        try(ResultSet rs = mySQL.query("SELECT * FROM News")){
            while (rs.next()){
                list.add(rs.getString("NACHRICHT"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public String getUpdatesDatum(){
        this.mySQL = Lobbysystem.getInstance().getMySQL();
        try(ResultSet rs = mySQL.query("SELECT * FROM News WHERE ID='1'")){
            if (rs.next()){
                return rs.getString("DATUM");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }

    public List<Integer> getUpdatesID(){
        this.mySQL = Lobbysystem.getInstance().getMySQL();
        List<Integer> list = new ArrayList<>();
        try(ResultSet rs = mySQL.query("SELECT * FROM News")){
            while (rs.next()){
                list.add(rs.getInt("ID"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
