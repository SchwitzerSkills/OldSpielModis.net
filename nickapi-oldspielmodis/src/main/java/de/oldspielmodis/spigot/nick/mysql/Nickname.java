package de.oldspielmodis.spigot.nick.mysql;

import de.oldspielmodis.spigot.nick.Nickapi;
import de.oldspielmodis.spigot.nick.mysql.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Nickname {

    private MySQL mySQL;

    public void createTable(){
        this.mySQL = Nickapi.getInstance().getMySQL();
        mySQL.update("CREATE TABLE IF NOT EXISTS Nicknames (ID VARCHAR(100), NICKNAME VARCHAR(100))");
    }

    public void addNickname(String id, String nickname){
        this.mySQL = Nickapi.getInstance().getMySQL();
        mySQL.update("INSERT INTO Nicknames (ID, NICKNAME) VALUES ('" + id + "', '" + nickname + "')");
    }

    public void removeNickname(String id){
        this.mySQL = Nickapi.getInstance().getMySQL();
        mySQL.update("DELETE FROM Nicknames WHERE ID=?", id);
    }

    public boolean IDExist(String id){
        this.mySQL = Nickapi.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT COUNT(*) AS count FROM Nicknames WHERE ID=?", id)){
            if(rs.next()){
                return rs.getInt("count") != 0;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean NicknamesExists(){
        this.mySQL = Nickapi.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT COUNT(*) AS count FROM Nicknames")){
            if(rs.next()){
                return rs.getInt("count") != 0;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean NicknamesExists(String nickname){
        this.mySQL = Nickapi.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT COUNT(*) AS count FROM Nicknames WHERE NICKNAME=?", nickname)){
            if(rs.next()){
                return rs.getInt("count") != 0;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public String RandomNickname(String id){
        this.mySQL = Nickapi.getInstance().getMySQL();
        try(ResultSet rs = mySQL.query("SELECT * FROM Nicknames WHERE ID=?", id)){
            if(rs.next()){
                return rs.getString("NICKNAME");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }

    public String getNickname(String id){
        this.mySQL = Nickapi.getInstance().getMySQL();
        try(ResultSet rs = mySQL.query("SELECT * FROM Nicknames WHERE ID=?", id)){
            if(rs.next()){
                return rs.getString("NICKNAME");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }

    public Integer getIDS(){
        this.mySQL = Nickapi.getInstance().getMySQL();
        try(ResultSet rs = mySQL.query("SELECT COUNT(*) AS count FROM Nicknames")){
            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public List<String> getNicknames(){
        this.mySQL = Nickapi.getInstance().getMySQL();
        List<String> list = new ArrayList<>();
        try(ResultSet rs = mySQL.query("SELECT * FROM Nicknames")){
            while (rs.next()){
                list.add(rs.getString("NICKNAME"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
