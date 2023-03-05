package net.svisvi.slonik.procedures;

import net.svisvi.slonik.entity.PahomEntity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import java.util.Random;

public class PahomEntityIsHurtProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double rand = 0;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		double speed = 0;
		double Yaw = 0;
		if (entity.isShiftKeyDown()) {
			if (Mth.nextInt(new Random(), 1, 10) > 5) {
				entity.setShiftKeyDown((false));
				if (entity instanceof PahomEntity) {
					((PahomEntity) entity).setAnimation("empty");
				}
			}
		}
		if (!entity.isSprinting() && (((PahomEntity) entity).animationprocedure).equals("kurlyk")) {
			if (entity instanceof PahomEntity) {
				((PahomEntity) entity).setAnimation("empty");
			}
		}
		if (!(((PahomEntity) entity).animationprocedure).equals("kurlyk") && entity.isSprinting()) {
			entity.setSprinting((false));
		}
		if (!entity.isShiftKeyDown() && (((PahomEntity) entity).animationprocedure).equals("bread")) {
			if (entity instanceof PahomEntity) {
				((PahomEntity) entity).setAnimation("empty");
			}
		}
		if (!(((PahomEntity) entity).animationprocedure).equals("bread") && entity.isShiftKeyDown()) {
			entity.setShiftKeyDown((false));
		}
		if (Mth.nextInt(new Random(), 1, 10) >= 6 && true && true && !entity.isSprinting()) {
			new Object() {
				private int ticks = 0;
				private float waitTicks;
				private LevelAccessor world;

				public void start(LevelAccessor world, int waitTicks) {
					this.waitTicks = waitTicks;
					MinecraftForge.EVENT_BUS.register(this);
					this.world = world;
				}

				@SubscribeEvent
				public void tick(TickEvent.ServerTickEvent event) {
					if (event.phase == TickEvent.Phase.END) {
						this.ticks += 1;
						if (this.ticks >= this.waitTicks)
							run();
					}
				}

				private void run() {
					if (true && true && !entity.isSprinting()) {
						if (entity instanceof PahomEntity) {
							((PahomEntity) entity).setAnimation("empty");
						}
						entity.setSprinting((true));
						if (entity instanceof PahomEntity) {
							((PahomEntity) entity).setAnimation("kurlyk");
						}
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, new BlockPos(entity.getX(), entity.getY(), entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("slonik:entity.pahom.kurlyk")), SoundSource.HOSTILE, 2, 1);
							} else {
								_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("slonik:entity.pahom.kurlyk")), SoundSource.HOSTILE, 2, 1, false);
							}
						}
					}
					MinecraftForge.EVENT_BUS.unregister(this);
				}
			}.start(world, 20);
		}
	}
}
