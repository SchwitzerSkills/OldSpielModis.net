package de.oldspielmodis.coins.mysql;

import de.oldspielmodis.coins.Coinssystem;
import de.oldspielmodis.coins.event.PlayerCoinsChangeEvent;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Coins {

    private MySQL mySQL;

    public void createTable(){
        this.mySQL = Coinssystem.getInstance().getMySQL();
        mySQL.update("CREATE TABLE IF NOT EXISTS Coins (UUID VARCHAR(100), COINS VARCHAR(100))");
    }

    public boolean isUserExists(String uuid){
        this.mySQL = Coinssystem.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT COUNT(*) AS count FROM Coins WHERE UUID=?", uuid)){
            if(rs.next()){
                return rs.getInt("count") != 0;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void addUser(String uuid){
        int coins = 0;
        this.mySQL = Coinssystem.getInstance().getMySQL();
        mySQL.update("INSERT INTO Coins (UUID, COINS) VALUES ('" + uuid + "', '" + coins + "')");
    }

    public void setCoins(String uuid, int coins){
        this.mySQL = Coinssystem.getInstance().getMySQL();
        mySQL.update("UPDATE Coins SET COINS=? WHERE UUID=?", coins, uuid);
        PlayerCoinsChangeEvent playerCoinsChangeEvent = new PlayerCoinsChangeEvent(uuid, coins);
        Bukkit.getPluginManager().callEvent(playerCoinsChangeEvent);
    }

    public void addCoins(String uuid, int coins){
        this.mySQL = Coinssystem.getInstance().getMySQL();
        int amount = getCoins(uuid) + coins;
        mySQL.update("UPDATE Coins SET COINS=? WHERE UUID=?", amount, uuid);
        PlayerCoinsChangeEvent playerCoinsChangeEvent = new PlayerCoinsChangeEvent(uuid, amount);
        Bukkit.getPluginManager().callEvent(playerCoinsChangeEvent);
    }

    public void removeCoins(String uuid, int coins){
        this.mySQL = Coinssystem.getInstance().getMySQL();
        int amount = getCoins(uuid) - coins;
        mySQL.update("UPDATE Coins SET COINS=? WHERE UUID=?", amount, uuid);
        PlayerCoinsChangeEvent playerCoinsChangeEvent = new PlayerCoinsChangeEvent(uuid, amount);
        Bukkit.getPluginManager().callEvent(playerCoinsChangeEvent);
    }

    public Integer getCoins(String uuid){
        this.mySQL = Coinssystem.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM Coins WHERE UUID=?", uuid)){
            if(rs.next()){
                return rs.getInt("COINS");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }
}
