package de.oldspielmodies.mysql;

import de.oldspielmodies.lobby.Lobbysystem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Setting {

    private MySQL mySQL;

    public void createTable(){
        this.mySQL = Lobbysystem.getInstance().getMySQL();
        mySQL.update("CREATE TABLE IF NOT EXISTS Setting (UUID VARCHAR(100), SETTING VARCHAR(100))");
    }

    public void setSetting(String uuid, String setting){
        this.mySQL = Lobbysystem.getInstance().getMySQL();
        mySQL.update("INSERT INTO Setting (UUID, SETTING) VALUES ('" + uuid + "', '" + setting + "')");
    }

    public void removeSetting(String uuid, String setting){
        this.mySQL = Lobbysystem.getInstance().getMySQL();
        mySQL.update("DELETE FROM Setting WHERE UUID=? AND SETTING=?", uuid, setting);
    }

    public boolean hasSetting(String uuid, String setting){
        this.mySQL = Lobbysystem.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT COUNT(*) AS count FROM Setting WHERE UUID=? AND SETTING=?", uuid, setting)){
            if(rs.next()){
                return rs.getInt("count") != 0;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
