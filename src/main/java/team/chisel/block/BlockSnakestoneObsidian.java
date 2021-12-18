package team.chisel.block;

import java.util.Random;

import team.chisel.client.GeneralChiselClient;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockSnakestoneObsidian extends BlockSnakestone {

	public static IIcon[] particles = new IIcon[8];

	public BlockSnakestoneObsidian(String iconPrefix) {
		super(iconPrefix);

		flipTopTextures = true;

	}

	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		GeneralChiselClient.spawnSnakestoneObsidianFX(world, this, x, y, z);
	}

	@Override
	public void registerBlockIcons(IIconRegister register) {
		super.registerBlockIcons(register);

		for (int i = 0; i < particles.length; i++) {
			particles[i] = register.registerIcon(iconPrefix + "particles/" + i);
		}
	}

}
