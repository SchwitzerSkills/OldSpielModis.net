package de.schwitzerskills.spigot.skywars.mysql;

import de.schwitzerskills.spigot.skywars.SkyWars;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KitSystem {

    private MySQL mySQL;

    public void createTable(){
        this.mySQL = SkyWars.getInstance().getMySQL();
        mySQL.update("CREATE TABLE IF NOT EXISTS KitSystem (UUID VARCHAR(100), KIT VARCHAR(100))");
    }

    public void buyKit(String uuid, String kit){
        this.mySQL = SkyWars.getInstance().getMySQL();
        mySQL.update("INSERT INTO KitSystem (UUID, KIT) VALUES (?, ?)", uuid, kit);
    }

    public boolean hasKit(String uuid, String kit){
        this.mySQL = SkyWars.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT COUNT(*) AS count FROM KitSystem WHERE UUID=? AND KIT=?", uuid, kit)){
            if(rs.next()){
                return rs.getInt("count") != 0;
            }
        } catch (SQLException e){
        }
        return false;
    }
}
