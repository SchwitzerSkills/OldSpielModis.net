package de.oldspielmodis.bungee.mysql;

import de.oldspielmodis.bungee.Bungee;
import de.oldspielmodis.bungee.utils.BanType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Ban {

    private MySQL mySQL;

    public void createTable(){
        this.mySQL = Bungee.getInstance().getMySQL();
        mySQL.update("CREATE TABLE IF NOT EXISTS Ban (UUID VARCHAR(100), REASON VARCHAR(100), DURATION VARCHAR(100), TIME VARCHAR(100), TYPE VARCHAR(100))");
    }

    public void addBan(String uuid, String reason, String duration, String time, String banType){
        this.mySQL = Bungee.getInstance().getMySQL();
        mySQL.update("INSERT INTO Ban (UUID, REASON, DURATION, TIME, TYPE) VALUES ('" + uuid + "', '" + reason + "', '" + duration + "', '" + time + "', '" + banType + "')");
    }

    public void removeBan(String uuid){
        this.mySQL = Bungee.getInstance().getMySQL();
        mySQL.update("DELETE FROM Ban WHERE UUID=?", uuid);
    }

    public String getDurationFromBan(String uuid){
        this.mySQL = Bungee.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM Ban WHERE UUID=?", uuid)){
            if(rs.next()){
                return rs.getString("DURATION");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }

    public String getTimeFromBan(String uuid){
        this.mySQL = Bungee.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM Ban WHERE UUID=?", uuid)){
            if(rs.next()){
                return rs.getString("TIME");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }

    public String getReasonFromBan(String uuid){
        this.mySQL = Bungee.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM Ban WHERE UUID=?", uuid)){
            if(rs.next()){
                return rs.getString("REASON");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }

    public String getTypeFromBan(String uuid){
        this.mySQL = Bungee.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM Ban WHERE UUID=?", uuid)){
            if(rs.next()){
                return rs.getString("TYPE");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }

    public boolean isBanned(String uuid){
        this.mySQL = Bungee.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM Ban WHERE UUID=?", uuid)){
            if(rs.next()){
                return rs.getString("UUID") != null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean isBanned(String uuid, BanType banType){
        this.mySQL = Bungee.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM Ban WHERE UUID='" + uuid + "' AND TYPE='" + banType + "'")){
            if(rs.next()){
                return rs.getString("UUID") != null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
