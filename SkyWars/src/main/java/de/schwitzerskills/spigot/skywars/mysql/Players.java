package de.schwitzerskills.spigot.skywars.mysql;

import de.schwitzerskills.spigot.skywars.SkyWars;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Players {

    private MySQL mySQL;

    public void createTable(){
        this.mySQL = SkyWars.getInstance().getMySQL();
        mySQL.update("CREATE TABLE IF NOT EXISTS Players (UUID VARCHAR(100))");
    }

    public void addPlayer(String uuid){
        this.mySQL = SkyWars.getInstance().getMySQL();
        mySQL.update("INSERT INTO Players (UUID) VALUES (?)", uuid);
    }

    public boolean isExists(String uuid){
        this.mySQL = SkyWars.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT COUNT(*) AS count FROM Players WHERE UUID=?", uuid)){
            if(rs.next()){
                return rs.getInt("count") != 0;
            }
        } catch (SQLException e){
        }
        return false;
    }
}
