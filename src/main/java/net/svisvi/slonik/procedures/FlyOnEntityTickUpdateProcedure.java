package net.svisvi.slonik.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

import java.util.stream.Collectors;
import java.util.Random;
import java.util.List;
import java.util.Comparator;

public class FlyOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double dis = 0;
		double speed = 0;
		double Yaw = 0;
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.ASH, x, y, z, 5, 0.2, 0.2, 0.2, 1);
		speed = Mth.nextDouble(new Random(), 0.3, 0.7);
		{
			final Vec3 _center = new Vec3(x, y, z);
			List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(10 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).collect(Collectors.toList());
			for (Entity entityiterator : _entfound) {
				if (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 10, 10, 10), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)) == entityiterator) {
					dis = Math.sqrt(Math.pow((entityiterator.getX() + Mth.nextDouble(new Random(), -0.1, 0.1)) - entity.getX(), 2) + Math.pow((entityiterator.getY() + Mth.nextDouble(new Random(), -0.1, 0.1)) - entity.getY(), 2)
							+ Math.pow((entityiterator.getZ() + Mth.nextDouble(new Random(), -0.1, 0.1)) - entity.getZ(), 2));
					if (entity instanceof Mob _entity && entityiterator instanceof LivingEntity _ent)
						_entity.setTarget(_ent);
					if (entity instanceof Mob _entity)
						_entity.getNavigation().moveTo((entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), (Mth.nextDouble(new Random(), 1, 2)));
					speed = 0.1;
					Yaw = entity.getYRot();
					entity.setDeltaMovement(new Vec3((speed * Math.cos((Yaw + 90) * (Math.PI / 180))), (0.4 * (((entityiterator.getY() + Mth.nextDouble(new Random(), -0.1, 0.1)) - entity.getY()) / dis) + entity.getDeltaMovement().y()),
							(speed * Math.sin((Yaw + 90) * (Math.PI / 180)))));
				}
			}
		}
		{
			final Vec3 _center = new Vec3(x, y, z);
			List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(128 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).collect(Collectors.toList());
			for (Entity entityiterator : _entfound) {
				if (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 128, 128, 128), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)) == entityiterator) {
					if (entity instanceof Mob _entity && entityiterator instanceof LivingEntity _ent)
						_entity.setTarget(_ent);
					if (entity instanceof Mob _entity)
						_entity.getNavigation().moveTo((entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), (Mth.nextDouble(new Random(), 1, 2)));
				}
			}
		}
	}
}
