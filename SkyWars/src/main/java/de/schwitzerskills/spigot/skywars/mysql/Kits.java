package de.schwitzerskills.spigot.skywars.mysql;

import de.schwitzerskills.spigot.skywars.SkyWars;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Kits {

    private MySQL mySQL;

    public void createTable(){
        this.mySQL = SkyWars.getInstance().getMySQL();
        mySQL.update("CREATE TABLE IF NOT EXISTS Kits (UUID VARCHAR(100), KIT VARCHAR(100))");
    }

    public void addKit(String uuid, String kit){
        this.mySQL = SkyWars.getInstance().getMySQL();
        mySQL.update("INSERT INTO Kits (UUID, KIT) VALUES (?, ?)", uuid, kit);
    }

    public void updateKit(String uuid, String kit){
        this.mySQL = SkyWars.getInstance().getMySQL();
        mySQL.update("UPDATE Kits SET KIT=? WHERE UUID=?", kit, uuid);
    }

    public String getKit(String uuid){
        this.mySQL = SkyWars.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM Kits WHERE UUID=?", uuid)){
            if(rs.next()){
                return rs.getString("KIT");
            }
        } catch (SQLException e){
        }
        return "";
    }
    public boolean whichKit(String uuid, String kit){
        this.mySQL = SkyWars.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT COUNT(*) AS count FROM Kits WHERE UUID=? AND KIT=?", uuid, kit)){
            if(rs.next()){
                return rs.getInt("count") != 0;
            }
        } catch (SQLException e){
        }
        return false;
    }
}
