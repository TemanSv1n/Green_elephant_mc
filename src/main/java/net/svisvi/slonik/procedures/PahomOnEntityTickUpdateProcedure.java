package net.svisvi.slonik.procedures;

import net.svisvi.slonik.init.SlonikModParticleTypes;
import net.svisvi.slonik.init.SlonikModEntities;
import net.svisvi.slonik.entity.PahomEntity;
import net.svisvi.slonik.entity.FlyEntity;

import net.minecraftforge.server.ServerLifecycleHooks;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.MinecraftServer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.ChatType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.Util;

import java.util.stream.Collectors;
import java.util.Random;
import java.util.List;
import java.util.Comparator;

public class PahomOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		DamageSource pahom = new DamageSource("generic");
		boolean found = false;
		double xRadius = 0;
		double loop = 0;
		double zRadius = 0;
		double particleAmount = 0;
		double rand = 0;
		double kurlyk_stop = 0;
		double speed = 0;
		double Yaw = 0;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		if (entity.getPersistentData().getBoolean("charging") == true) {
			if (entity instanceof LivingEntity _entity)
				_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 30, 255, (false), (false)));
			if (entity instanceof LivingEntity _entity)
				_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 255, (false), (false)));
			entity.getPersistentData().putDouble("charge_counter", (entity.getPersistentData().getDouble("charge_counter") + 1));
			if (entity.getPersistentData().getDouble("charge_counter") % 120 == 0) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, new BlockPos(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("slonik:entity.pahom.spawned")), SoundSource.NEUTRAL, 2, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("slonik:entity.pahom.spawned")), SoundSource.NEUTRAL, 2, 1, false);
					}
				}
			}
			if (entity.getPersistentData().getDouble("charge_counter") % 140 == 0) {
				entity.getPersistentData().putBoolean("charging", (false));
			}
		}
		if (entity.getPersistentData().getBoolean("charging") == false) {
			if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 128, 128, 128), e -> true).isEmpty()) {
				if (!entity.isShiftKeyDown()) {
					entity.getPersistentData().putDouble("counter1", (entity.getPersistentData().getDouble("counter1") + 1));
					if (entity.getPersistentData().getDouble("counter1") % 60 == 0) {
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) / (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) >= 0.5) {
							rand = Mth.nextInt(new Random(), 1, 10);
						} else {
							rand = Mth.nextInt(new Random(), 1, 8);
						}
						if (rand >= 8 && true && true && !entity.isSprinting()) {
							if (!world.isClientSide()) {
								MinecraftServer _mcserv = ServerLifecycleHooks.getCurrentServer();
								if (_mcserv != null)
									_mcserv.getPlayerList().broadcastMessage(new TextComponent("kurlyk"), ChatType.SYSTEM, Util.NIL_UUID);
							}
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
						} else if (rand <= 2 && !(!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 12, 12, 12), e -> true).isEmpty())
								&& !world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 64, 64, 64), e -> true).isEmpty() && true) {
							if (!world.isClientSide()) {
								MinecraftServer _mcserv = ServerLifecycleHooks.getCurrentServer();
								if (_mcserv != null)
									_mcserv.getPlayerList().broadcastMessage(new TextComponent("cast"), ChatType.SYSTEM, Util.NIL_UUID);
							}
							entity.getPersistentData().putBoolean("cast", (true));
							if (entity instanceof PahomEntity) {
								((PahomEntity) entity).setAnimation("empty");
							}
							if (entity instanceof PahomEntity) {
								((PahomEntity) entity).setAnimation("cast");
							}
							if (entity instanceof LivingEntity _entity)
								_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 255, (false), (false)));
							if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 72, 72, 72), e -> true).isEmpty()) {
								sx = -3;
								for (int index0 = 0; index0 < (int) (6); index0++) {
									sy = -3;
									for (int index1 = 0; index1 < (int) (6); index1++) {
										sz = -3;
										for (int index2 = 0; index2 < (int) (6); index2++) {
											if ((world.getBlockState(new BlockPos(x + sx, y + sy, z + sz))).getMaterial() == net.minecraft.world.level.material.Material.AIR) {
												if (Mth.nextInt(new Random(), 1, 100) >= 95) {
													if (world instanceof ServerLevel _level) {
														Entity entityToSpawn = new FlyEntity(SlonikModEntities.FLY.get(), _level);
														entityToSpawn.moveTo((x + sx), (y + sy), (z + sz), entity.getYRot(), entity.getXRot());
														entityToSpawn.setYBodyRot(entity.getYRot());
														entityToSpawn.setYHeadRot(entity.getYRot());
														entityToSpawn.setDeltaMovement(0, 0, 0);
														if (entityToSpawn instanceof Mob _mobToSpawn)
															_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
														world.addFreshEntity(entityToSpawn);
													}
													if (world instanceof ServerLevel _level)
														_level.sendParticles(ParticleTypes.ASH, (x + sx), (y + sy), (z + sz), 20, 0.3, 0.3, 0.3, 0.1);
													if (world instanceof Level _level) {
														if (!_level.isClientSide()) {
															_level.playSound(null, new BlockPos(x + sx, y + sy, z + sz), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.evoker.cast_spell")), SoundSource.HOSTILE, 2, -1);
														} else {
															_level.playLocalSound((x + sx), (y + sy), (z + sz), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.evoker.cast_spell")), SoundSource.HOSTILE, 2, -1, false);
														}
													}
												}
											}
											sz = sz + 1;
										}
										sy = sy + 1;
									}
									sx = sx + 1;
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
										if (entity.getPersistentData().getBoolean("cast") == true) {
											if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 72, 72, 72), e -> true).isEmpty()) {
												if (world instanceof Level _level) {
													if (!_level.isClientSide()) {
														_level.playSound(null, new BlockPos(entity.getX(), entity.getY(), entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("slonik:entity.pahom.flies")), SoundSource.HOSTILE,
																3, 1);
													} else {
														_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("slonik:entity.pahom.flies")), SoundSource.HOSTILE, 3, 1,
																false);
													}
												}
												entity.getPersistentData().putBoolean("cast", (false));
											} else {
												entity.getPersistentData().putBoolean("cast", (false));
											}
										}
										MinecraftForge.EVENT_BUS.unregister(this);
									}
								}.start(world, 30);
							}
						} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) / (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) <= 0.5 && !entity.isSprinting()
								&& entity.getPersistentData().getBoolean("cast") == false && !entity.isShiftKeyDown() && rand >= 3 && rand <= 4) {
							entity.setSprinting((false));
							if (entity instanceof PahomEntity) {
								((PahomEntity) entity).setAnimation("empty");
							}
							if (entity instanceof PahomEntity) {
								((PahomEntity) entity).setAnimation("bread");
							}
							entity.setShiftKeyDown((true));
							if (!world.isClientSide()) {
								MinecraftServer _mcserv = ServerLifecycleHooks.getCurrentServer();
								if (_mcserv != null)
									_mcserv.getPlayerList().broadcastMessage(new TextComponent("bread"), ChatType.SYSTEM, Util.NIL_UUID);
							}
						}
					}
				}
			}
			if (entity.isShiftKeyDown() && (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) / (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) >= 0.5) {
				entity.setShiftKeyDown((false));
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
				if (entity instanceof PahomEntity) {
					((PahomEntity) entity).setAnimation("empty");
				}
			}
			if ((((PahomEntity) entity).animationprocedure).equals("bread") && entity.isShiftKeyDown()
					&& (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) / (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) <= 0.5) {
				if (entity instanceof LivingEntity _entity)
					_entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 3, (true), (true)));
				if (entity instanceof PahomEntity) {
					((PahomEntity) entity).setAnimation("bread");
				}
				if (entity instanceof LivingEntity _entity)
					_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 255, (false), (false)));
				particleAmount = 20;
				xRadius = 1.5;
				zRadius = 1.5;
				while (loop < particleAmount) {
					world.addParticle((SimpleParticleType) (SlonikModParticleTypes.SHIT_DRIP_PARTICLE.get()), (x + Math.cos(((Math.PI * 2) / particleAmount) * loop) * xRadius), (y + 1),
							(z + Math.sin(((Math.PI * 2) / particleAmount) * loop) * zRadius), 0, 0.05, 0);
					loop = loop + 1;
				}
				{
					final Vec3 _center = new Vec3(x, y, z);
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(4 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
							.collect(Collectors.toList());
					for (Entity entityiterator : _entfound) {
						if (!(entityiterator == entity) && !(entityiterator instanceof FlyEntity)) {
							entityiterator.hurt(new EntityDamageSource("drown.player", entity), Mth.nextInt(new Random(), 2, 4));
						}
					}
				}
			}
			if (entity.isSprinting() && (((PahomEntity) entity).animationprocedure).equals("kurlyk")) {
				if (entity instanceof PahomEntity) {
					((PahomEntity) entity).setAnimation("kurlyk");
				}
				if ((((PahomEntity) entity).animationprocedure).equals("kurlyk")) {
					world.levelEvent(2001, new BlockPos(entity.getX(), entity.getY(), entity.getZ()), Block.getId((world.getBlockState(new BlockPos(entity.getX(), entity.getY() - 1, entity.getZ())))));
					if (entity instanceof LivingEntity _entity)
						_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 2, (true), (true)));
					speed = 0.5;
					Yaw = entity.getYRot();
					entity.setDeltaMovement(new Vec3((speed * Math.cos((Yaw + 90) * (Math.PI / 180))), (entity.getDeltaMovement().y()), (speed * Math.sin((Yaw + 90) * (Math.PI / 180)))));
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, new BlockPos(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.attack.sweep")), SoundSource.HOSTILE, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.attack.sweep")), SoundSource.HOSTILE, 1, 1, false);
						}
					}
					{
						final Vec3 _center = new Vec3(
								(entity.level.clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(0.5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX()),
								(entity.level.clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(0.5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getY()),
								(entity.level.clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(0.5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ()));
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(2 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
								.collect(Collectors.toList());
						for (Entity entityiterator : _entfound) {
							if (!(entity == entityiterator)) {
								if (world instanceof ServerLevel _level)
									_level.sendParticles(ParticleTypes.SWEEP_ATTACK, (entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), 20, 0.7, 0.7, 0.7, 0.1);
								if (!(entityiterator instanceof LivingEntity _livEnt ? _livEnt.isBlocking() : false)) {
									entityiterator.hurt(new EntityDamageSource("cramming.player", entity), Mth.nextInt(new Random(), 3, 5));
								} else {
									if (entityiterator instanceof Player _player)
										_player.getCooldowns().addCooldown((entityiterator instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem(), 30);
									if (entityiterator instanceof Player _player)
										_player.getCooldowns().addCooldown((entityiterator instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem(), 30);
									if (world instanceof Level _level) {
										if (!_level.isClientSide()) {
											_level.playSound(null, new BlockPos(entityiterator.getX(), entityiterator.getY(), entityiterator.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.shield.block")),
													SoundSource.PLAYERS, 1, 1);
										} else {
											_level.playLocalSound((entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.shield.block")), SoundSource.PLAYERS, 1, 1,
													false);
										}
									}
									entity.setSprinting((false));
									if (entity instanceof PahomEntity) {
										((PahomEntity) entity).setAnimation("empty");
									}
								}
							}
						}
					}
					entity.getPersistentData().putDouble("kurlyk_counter", (entity.getPersistentData().getDouble("kurlyk_counter") + 1));
					if (entity.getPersistentData().getDouble("kurlyk_counter") % 35 == 0) {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, new BlockPos(entity.getX(), entity.getY(), entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("slonik:entity.pahom.kurlyk")), SoundSource.HOSTILE, 2, 1);
							} else {
								_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("slonik:entity.pahom.kurlyk")), SoundSource.HOSTILE, 2, 1, false);
							}
						}
					}
				}
				if (entity.getPersistentData().getDouble("kurlyk_counter") % 60 == 0) {
					entity.setSprinting((false));
					if (entity instanceof PahomEntity) {
						((PahomEntity) entity).setAnimation("empty");
					}
				}
			}
			if (!entity.isSprinting() && (((PahomEntity) entity).animationprocedure).equals("kurlyk")) {
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
						if (!entity.isSprinting() && (((PahomEntity) entity).animationprocedure).equals("kurlyk")) {
							if (entity instanceof PahomEntity) {
								((PahomEntity) entity).setAnimation("empty");
							}
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, 20);
			}
			if (!(((PahomEntity) entity).animationprocedure).equals("kurlyk") && entity.isSprinting()) {
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
						if (!(((PahomEntity) entity).animationprocedure).equals("kurlyk") && entity.isSprinting()) {
							entity.setSprinting((false));
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, 20);
			}
			if (entity.isShiftKeyDown()) {
				if (entity instanceof LivingEntity _entity)
					_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 255, (false), (false)));
			}
			if (!entity.isShiftKeyDown() && (((PahomEntity) entity).animationprocedure).equals("bread")) {
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
						if (!entity.isShiftKeyDown() && (((PahomEntity) entity).animationprocedure).equals("bread")) {
							if (entity instanceof PahomEntity) {
								((PahomEntity) entity).setAnimation("empty");
							}
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, 20);
			}
			if (!(((PahomEntity) entity).animationprocedure).equals("bread") && entity.isShiftKeyDown()) {
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
						if (!(((PahomEntity) entity).animationprocedure).equals("bread") && entity.isShiftKeyDown()) {
							entity.setShiftKeyDown((false));
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, 20);
			}
		}
	}
}
