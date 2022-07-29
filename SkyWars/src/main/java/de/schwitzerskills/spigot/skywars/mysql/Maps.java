package de.schwitzerskills.spigot.skywars.mysql;

import de.schwitzerskills.spigot.skywars.SkyWars;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Maps {

    private MySQL mySQL;

    public void createTable(){
        this.mySQL = SkyWars.getInstance().getMySQL();
        mySQL.update("CREATE TABLE IF NOT EXISTS Maps (MAP VARCHAR(100))");
    }

    public void setMap(String map){
        this.mySQL = SkyWars.getInstance().getMySQL();
        mySQL.update("INSERT INTO Maps (MAP) VALUES (?)", map);
    }

    public void switchMap(String map){
        this.mySQL = SkyWars.getInstance().getMySQL();
        mySQL.update("UPDATE Maps SET MAP=?", map);
    }

    public String getMap(){
        this.mySQL = SkyWars.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM Maps")){
            if(rs.next()){
                return rs.getString("MAP");
            }
        } catch (SQLException e){
        }
        return "";
    }

    public boolean isMap(){
        this.mySQL = SkyWars.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT COUNT(*) AS count FROM Maps")){
            if(rs.next()){
                return rs.getInt("count") != 0;
            }
        } catch (SQLException e){
        }
        return false;
    }
}
