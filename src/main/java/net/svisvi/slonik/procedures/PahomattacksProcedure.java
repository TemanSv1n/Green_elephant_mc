package net.svisvi.slonik.procedures;

import net.svisvi.slonik.entity.PahomEntity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

import java.util.stream.Collectors;
import java.util.Random;
import java.util.List;
import java.util.Comparator;

@Mod.EventBusSubscriber
public class PahomattacksProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level, event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getSource().getEntity());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity sourceentity) {
		execute(null, world, x, y, z, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity sourceentity) {
		if (sourceentity == null)
			return;
		DamageSource pahom = new DamageSource("generic");
		pahom = new EntityDamageSource("cramming.player", sourceentity);
		if (sourceentity instanceof PahomEntity) {
			if (sourceentity.getPersistentData().getBoolean("charging") == false && !(((PahomEntity) sourceentity).animationprocedure).equals("kurlyk") && !(((PahomEntity) sourceentity).animationprocedure).equals("bread")
					&& !(((PahomEntity) sourceentity).animationprocedure).equals("cast")) {
				if (sourceentity instanceof PahomEntity) {
					((PahomEntity) sourceentity).setAnimation("attack");
				}
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
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, new BlockPos(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.attack.strong")), SoundSource.HOSTILE, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.attack.strong")), SoundSource.HOSTILE, 1, 1, false);
							}
						}
						{
							final Vec3 _center = new Vec3(
									(sourceentity.level
											.clip(new ClipContext(sourceentity.getEyePosition(1f), sourceentity.getEyePosition(1f).add(sourceentity.getViewVector(1f).scale(0.5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, sourceentity))
											.getBlockPos().getX()),
									(sourceentity.level
											.clip(new ClipContext(sourceentity.getEyePosition(1f), sourceentity.getEyePosition(1f).add(sourceentity.getViewVector(1f).scale(0.5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, sourceentity))
											.getBlockPos().getY()),
									(sourceentity.level
											.clip(new ClipContext(sourceentity.getEyePosition(1f), sourceentity.getEyePosition(1f).add(sourceentity.getViewVector(1f).scale(0.5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, sourceentity))
											.getBlockPos().getZ()));
							List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(3 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
									.collect(Collectors.toList());
							for (Entity entityiterator : _entfound) {
								if (!(sourceentity == entityiterator)) {
									if (world instanceof ServerLevel _level)
										_level.sendParticles(ParticleTypes.ASH, (entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), 20, 0.7, 0.7, 0.7, 0.1);
									if (!(entityiterator instanceof LivingEntity _livEnt ? _livEnt.isBlocking() : false)) {
										entityiterator.hurt(new EntityDamageSource("cramming.player", sourceentity), Mth.nextInt(new Random(), 5, 7));
									} else {
										if (entityiterator instanceof Player _player)
											_player.getCooldowns().addCooldown((entityiterator instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem(), 100);
										if (entityiterator instanceof Player _player)
											_player.getCooldowns().addCooldown((entityiterator instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem(), 100);
										if (world instanceof Level _level) {
											if (!_level.isClientSide()) {
												_level.playSound(null, new BlockPos(entityiterator.getX(), entityiterator.getY(), entityiterator.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.shield.block")),
														SoundSource.PLAYERS, 1, 1);
											} else {
												_level.playLocalSound((entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.shield.block")), SoundSource.PLAYERS, 1,
														1, false);
											}
										}
									}
								}
							}
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, 15);
			}
		}
	}
}
