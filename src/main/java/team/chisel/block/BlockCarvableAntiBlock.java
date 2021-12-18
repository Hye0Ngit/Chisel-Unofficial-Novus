package team.chisel.block;

import com.cricketcraft.chisel.api.ICarvable;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.IBlockAccess;

public class BlockCarvableAntiBlock extends BlockCarvableColor implements ICarvable {

	public BlockCarvableAntiBlock() {
		super(Material.rock);
	}

	@Override
	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
		return false;
	}
}
