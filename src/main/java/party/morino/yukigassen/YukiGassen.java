package party.morino.yukigassen;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.Objects;
import java.util.Random;

public class YukiGassen extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onHit(ProjectileHitEvent event) {
        if (event.getHitEntity() instanceof Player player &&
                event.getEntity() instanceof Snowball snowball
        ) {
            var random = new Random().nextInt(0, 100);
            var isDeathBall = (5 >= random);

            if (isDeathBall) {
                player.setLastDamageCause(new EntityDamageEvent(snowball, EntityDamageEvent.DamageCause.PROJECTILE, 0.0));
                player.setHealth(0);
            } else {
                var playrVector = player.getVelocity();
                var addVector = snowball.getVelocity().add(new Vector(0.0D, 1.0D, 0.0D));
                var velocity = playrVector.add(addVector);

                player.damage(0.1D, snowball);
                player.setVelocity(velocity);
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        var player = event.getPlayer();
        if (Objects.requireNonNull(player.getLastDamageCause()).getEntityType().equals(EntityType.SNOWBALL)) {
            event.deathMessage(player.name().append(Component.text("は石ころの入った雪玉で破壊された")));
        }
    }
}
