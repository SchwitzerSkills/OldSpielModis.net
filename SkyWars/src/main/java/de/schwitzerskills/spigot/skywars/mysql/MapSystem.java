package de.schwitzerskills.spigot.skywars.mysql;

import de.schwitzerskills.spigot.skywars.SkyWars;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapSystem {

    private MySQL mySQL;

    public void createTable(){
        this.mySQL = SkyWars.getInstance().getMySQL();
        mySQL.update("CREATE TABLE IF NOT EXISTS MapSystem (ID VARCHAR(100), MAP VARCHAR(100))");
    }

    public void addMap(int id, String map){
        this.mySQL = SkyWars.getInstance().getMySQL();
        mySQL.update("INSERT INTO MapSystem (ID, MAP) VALUES (?, ?)", id, map);
    }

    public void removeMap(String map){
        this.mySQL = SkyWars.getInstance().getMySQL();
        mySQL.update("DELETE FROM MapSystem WHERE MAP=?", map);
    }

    public boolean hasID(int id){
        this.mySQL = SkyWars.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT COUNT(*) AS count FROM MapSystem WHERE ID=?", id)){
            if(rs.next()){
                return rs.getInt("count") != 0;
            }
        } catch (SQLException e){
        }
        return false;
    }

    public boolean hasMap(String map){
        this.mySQL = SkyWars.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT COUNT(*) AS count FROM MapSystem WHERE MAP=?", map)){
            if(rs.next()){
                return rs.getInt("count") != 0;
            }
        } catch (SQLException e){
        }
        return false;
    }

    public boolean hasMaps(){
        this.mySQL = SkyWars.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT COUNT(*) AS count FROM MapSystem")){
            if(rs.next()){
                return rs.getInt("count") != 0;
            }
        } catch (SQLException e){
        }
        return false;
    }

    public List<String> getMaps(int id){
        this.mySQL = SkyWars.getInstance().getMySQL();
        List<String> list = new ArrayList<>();
        try (ResultSet rs = mySQL.query("SELECT * FROM MapSystem WHERE ID='" + id + "'")){
            if(rs.next()){
                list.add(rs.getString("MAP"));
            }
        } catch (SQLException e){
        }
        return list;
    }

    public Integer getIds(String map){
        this.mySQL = SkyWars.getInstance().getMySQL();
        try(ResultSet rs = mySQL.query("SELECT * FROM MapSystem WHERE MAP=?", map)){
            if(rs.next()){
                return rs.getInt("ID");
            }
        } catch (SQLException e){
        }
        return 0;
    }

    public String getMap(int id){
        this.mySQL = SkyWars.getInstance().getMySQL();
        try(ResultSet rs = mySQL.query("SELECT * FROM MapSystem WHERE ID=?", id)){
            if(rs.next()){
                return rs.getString("MAP");
            }
        } catch (SQLException e){
        }
        return "";
    }
}
