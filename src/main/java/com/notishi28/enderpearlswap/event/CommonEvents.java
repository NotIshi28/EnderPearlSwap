package com.notishi28.enderpearlswap.event;

import com.notishi28.enderpearlswap.EnderPearlSwap;
import com.notishi28.enderpearlswap.config.EnderPearlSwapConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = EnderPearlSwap.MOD_ID)
public class CommonEvents {

    @SubscribeEvent
    public static void enderpearlEvent(final ProjectileImpactEvent event) {
        if ( !EnderPearlSwapConfig.MODE.get() ) {
            Entity entity = event.getEntity();

            //Check if throwable is an enderpearl
            if (entity instanceof ThrownEnderpearl) {
                Level level = entity.level;

                //Only on serverside
                if (!level.isClientSide) {
                    ThrowableProjectile throwable = (ThrowableProjectile) entity;
                    Entity owner = throwable.getOwner();

                    //Distance the entity needs to be from throwable to be teleported
                    Double distance = EnderPearlSwapConfig.LANDING_AREA_DISTANCE.get();

                    //Make a list of all entities a block away from the throwable
                    List<Entity> entitiesAround = level.getEntities(entity, entity.getBoundingBox().inflate(distance, distance, distance));

                    for (Entity listedEntity : entitiesAround) {

                        //Check if both entities are living entities
                        if (owner instanceof LivingEntity && listedEntity instanceof LivingEntity) {

                            //Check if both entities are still in the same dimension
                            if (owner.level == listedEntity.level) {
                                listedEntity.moveTo(owner.getX(), owner.getY(), owner.getZ());
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void enderpearlEventDirect(final LivingHurtEvent event) {
        if ( EnderPearlSwapConfig.MODE.get() ) {
            if (event.getSource().getDirectEntity() instanceof ThrownEnderpearl) {
                Entity owner = event.getSource().getEntity();
                LivingEntity target = event.getEntity();
                Vec3 sourcePos = owner.position();

                //Check if both entities are still in the same dimension
                if (owner.level == target.level) {
                    target.moveTo(sourcePos);
                }
            }
        }
    }
}
