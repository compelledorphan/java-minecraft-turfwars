package space.codekingdoms.professorliam.turfwars;

import com.codekingdoms.nozzle.utils.Random;
import com.codekingdoms.nozzle.utils.Direction;
import java.lang.Math;
import java.lang.Integer;
import java.lang.Float;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.Location;
import org.bukkit.World;
import com.codekingdoms.nozzle.base.BaseGame;

public class Game extends BaseGame {
	
	public boolean isFirstBuild;
	
	public boolean isCombatPhase;
	
	public Location redSpawnPoint;
	
	public Location blueSpawnPoint;
	
	public boolean lastPlayerJoinedRed;
	
	public int currentOffset;
	
	public void onCodeUpdate() {
		
		isFirstBuild = true;
		isCombatPhase = false;
		lastPlayerJoinedRed = false;
		canMine = false;
		canPlaceBlocks = true;
		canDropItems = false;
		redSpawnPoint = new Location(world, -563.74, 65, 572.37);
		blueSpawnPoint = new Location(world, -562.98, 65, 671.99);
		startGame();
	
	}
	
	public void startGame() {
		
		for (Player player : getPlayerList()) {
			
			player.clearInventory();
			
		}
		
		teleportPlayers();
		buildPhase();
	
	}
	
	public void buildPhase() {
		
		broadcastTitle("Build Phase!", "Get those bases up!");
		world.setPVP(false);
		if (isFirstBuild) {
			
			for (Player player : getPlayerList()) {
				
				player.addItemToInventory(new ItemStack(Material.WOOL, 64));
				player.woolAmount = player.woolAmount + 64;
				startTimer(40);
				isCombatPhase = true;
				
			}
			
			
		}
		
		else {
			
			for (Player player : getPlayerList()) {
				
				player.addItemToInventory(new ItemStack(Material.WOOL, 32));
				startTimer(20);
				isCombatPhase = true;
				player.woolAmount = player.woolAmount + 32;
				
			}
			
			
		 }
		
	
	}
	
	public void combatPhase() {
		
		broadcastTitle("Combat Phase!", "Fight time!");
		isCombatPhase = false;
		world.setPVP(true);
		startTimer(90);
	
	}
	
	public void onTimerExpire() {
		
		if (isCombatPhase) {
			
			combatPhase();
			
		}
		
		else {
			
			buildPhase();
			
		 }
		
	
	}
	
	public void teleportPlayers() {
		
		for (Player player : getPlayerList()) {
			
			if (player.isRed) {
				
				player.teleport(redSpawnPoint);
				player.setBedSpawnLocation(redSpawnPoint);
				
			}
			
			else {
				
				player.teleport(blueSpawnPoint);
				player.setBedSpawnLocation(blueSpawnPoint);
				
			 }
			
			
		}
		
	
	}
	
	
}
