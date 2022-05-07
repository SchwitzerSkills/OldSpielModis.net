package de.oldspielmodis.bungee.mysql;

import de.oldspielmodis.bungee.Bungee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Mute {

    private MySQL mySQL;

    public void createTable(){
        this.mySQL = Bungee.getInstance().getMySQL();
        mySQL.update("CREATE TABLE IF NOT EXISTS Mute (UUID VARCHAR(100), REASON VARCHAR(100), DURATION VARCHAR(100), DAYS VARCHAR(100))");
    }

    public void addMute(String uuid, String reason, String duration, String day){
        this.mySQL = Bungee.getInstance().getMySQL();
        mySQL.update("INSERT INTO Mute (UUID, REASON, DURATION, DAYS) VALUES ('" + uuid + "', '" + reason + "', '" + duration + "', '" + day + "')");
    }

    public void removeMute(String uuid){
        this.mySQL = Bungee.getInstance().getMySQL();
        mySQL.update("DELETE FROM Mute WHERE UUID=?", uuid);
    }

    public String getDurationFromMute(String uuid){
        this.mySQL = Bungee.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM Mute WHERE UUID=?", uuid)){
            if(rs.next()){
                return rs.getString("DURATION");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }

    public String getReasonFromMute(String uuid){
        this.mySQL = Bungee.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM Mute WHERE UUID=?", uuid)){
            if(rs.next()){
                return rs.getString("REASON");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }

    public boolean isMuted(String uuid){
        this.mySQL = Bungee.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM Mute WHERE UUID=?", uuid)){
            if(rs.next()){
                return rs.getString("UUID") != null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
