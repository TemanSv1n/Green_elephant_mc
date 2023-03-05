package net.svisvi.slonik.procedures;

import net.svisvi.slonik.init.SlonikModEntities;
import net.svisvi.slonik.entity.PahomEntity;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

public class EmptyplateBlockAddedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(new BlockPos(x, y - 1, z))).getBlock() == Blocks.EMERALD_BLOCK) {
			if (!(!world.getEntitiesOfClass(PahomEntity.class, AABB.ofSize(new Vec3(x, y, z), 128, 128, 128), e -> true).isEmpty())) {
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.ASH, x, y, z, 200, 1, 2, 1, 1);
				if (world instanceof ServerLevel _serverLevel) {
					Entity _spawnentity = new PahomEntity(SlonikModEntities.PAHOM.get(), _serverLevel);
					_spawnentity.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
					_spawnentity.getPersistentData().putBoolean("charging", (true));
					if (_spawnentity instanceof LivingEntity _entity)
						_entity.setHealth(1);
					if (_spawnentity instanceof LivingEntity _entity)
						_entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 130, 3, (false), (false)));
					if (_spawnentity instanceof PahomEntity) {
						((PahomEntity) _spawnentity).setAnimation("charge");
					}
					if (_spawnentity instanceof Mob _spawnmob)
						_spawnmob.finalizeSpawn(_serverLevel, world.getCurrentDifficultyAt(_spawnentity.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
					world.addFreshEntity(_spawnentity);
				}
			}
		}
	}
}
