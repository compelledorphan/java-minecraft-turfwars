package space.codekingdoms.professorliam.turfwars;

import org.bukkit.Location;
import org.bukkit.World;
import com.codekingdoms.nozzle.utils.Random;
import com.codekingdoms.nozzle.utils.Direction;
import java.lang.Math;
import java.lang.Integer;
import java.lang.Float;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import com.codekingdoms.nozzle.base.BasePlayer;

public class Player extends BasePlayer {
	
	public boolean isRed;
	
	public boolean isMarksman;
	
	public int woolAmount;
	
	public int arrowAmount;
	
	public void onProjectileHitTarget( String projectileType, Location hitZone ) {
		
		arrowAmount -- ;
		sendMessage(hitZone.getBlock().getType().toString());
		if (hitZone.getBlock().getType() == Material.AIR) {
			
			hitZone.getBlock().setType(Material.STONE);
			
		}
		
	
	}
	
	public void equipTeamColors() {
		
		if (isRed) {
			
			equipColoredArmor(250, 0, 0);
			
		}
		
		else {
			
			equipColoredArmor(0, 0, 250);
			
		 }
		
	
	}
	
	public void onRespawn() {
		
		clearInventory();
		woolAmount = 0;
		arrowAmount = 0;
		equipTeamColors();
		equipWeapons();
		addItemToInventory(new ItemStack(Material.WOOL, 6));
		woolAmount = woolAmount + 6;
	
	}
	
	public void addWool() {
		
		setInterval(
			
			() -> {
				
				if (woolAmount < 4) {
					
					addItemToInventory(new ItemStack(Material.WOOL));
					woolAmount ++;
					
				}
				
				
			}
			
			
		, 5, 5);
	
	}
	
	public void onStart() {
		
		equipTeamColors();
		equipWeapons();
		woolAmount = 0;
		arrowAmount = 0;
		addWool();
	
	}
	
	public void equipWeapons() {
		
		setItemInMainHand(new ItemStack(Material.BOW));
		if (! isMarksman) {
			
			addItemToInventory(new ItemStack(Material.IRON_SWORD));
			
		}
		
		addArrows();
	
	}
	
	public void onJoin() {
		
		if (getGame().lastPlayerJoinedRed) {
			
			getGame().lastPlayerJoinedRed = false;
			isRed = false;
			
		}
		
		else {
			
			getGame().lastPlayerJoinedRed = true;
			isRed = true;
			
		 }
		
	
	}
	
	public void getBlockBelow() {
		
		
	
	}
	
	public void addArrows() {
		
		if (isMarksman) {
			
			setInterval(
				
				() -> {
					
					if (arrowAmount < 2) {
						
						addItemToInventory(new ItemStack(Material.ARROW));
						arrowAmount ++;
						
					}
					
					
				}
				
				
			, 5, 2);
			
		}
		
		else {
			
			setInterval(
				
				() -> {
					
					if (arrowAmount < 2) {
						
						addItemToInventory(new ItemStack(Material.ARROW));
						arrowAmount ++;
						
					}
					
					
				}
				
				
			, 5, 4);
			
		 }
		
	
	}
	
	public void onMove( Direction direction ) {
		
		if (! isMarksman) {
			
			
		}
		
	
	}
	
	public void onDamagePlayer( String name, double damage ) {
		
		
	
	}
	
	
}
