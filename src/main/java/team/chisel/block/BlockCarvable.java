package team.chisel.block;

import java.util.List;

import com.cricketcraft.chisel.api.ICarvable;
import com.cricketcraft.chisel.api.carving.CarvableHelper;
import com.cricketcraft.chisel.api.carving.IVariationInfo;
import com.cricketcraft.chisel.api.rendering.ClientUtils;

import team.chisel.Chisel;
import com.cricketcraft.chisel.api.ChiselTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCarvable extends Block implements ICarvable {

	public CarvableHelper carverHelper;
	private boolean isAlpha;

	public BlockCarvable() {
		this(Material.rock);
	}

	public BlockCarvable(Material m) {
		super(m);
		if (m == Material.rock || m == Material.iron) {
			setHarvestLevel("pickaxe", 0);
		}
		carverHelper = new CarvableHelper(this);
		setResistance(10.0F);
		setHardness(2.0F);
		setCreativeTab(ChiselTabs.tabOtherChiselBlocks);
	}

	public BlockCarvable setStained(boolean a) {
		this.isAlpha = a;
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return isAlpha ? 1 : 0;
	}

	@Override
	public IIcon getIcon(int side, int metadata) {
		return carverHelper.getIcon(side, metadata);
	}

	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		return carverHelper.getIcon(world, x, y, z, side);
	}

	@Override
	public int damageDropped(int i) {
		return i;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		carverHelper.registerBlockIcons(Chisel.MOD_ID, this, register);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubBlocks(Item item, CreativeTabs tabs, List list) {
		carverHelper.registerSubBlocks(this, tabs, list);
	}

	@Override
	public int getRenderType() {
		return ClientUtils.renderCTMId;
	}

	@Override
	public IVariationInfo getManager(IBlockAccess world, int x, int y, int z, int metadata) {
		return carverHelper.getVariation(metadata);
	}

	@Override
	public IVariationInfo getManager(int meta) {
		return carverHelper.getVariation(meta);
	}

	public static class SoundType extends Block.SoundType {

		public final String soundNameStep;
		public final String soundNameBreak;
		public final String soundNamePlace;

		/**
		 * Creates a SoundType with automatic names for step and break sounds. Sound names dig.soundName and step.soundName must be specified in the sounds.json
		 * 
		 * @param soundName
		 *            block of the sound. Will automatically be expanded to "mod:dig.soundName" and "mod:step.soundName" respectively)
		 * @param volume
		 *            default 1.0f
		 * @param frequency
		 *            default 1.0f
		 */
		public SoundType(String soundName, float volume, float frequency) {
			super(soundName, volume, frequency);
			this.soundNameStep = null;
			this.soundNameBreak = null;
			this.soundNamePlace = null;
		}

		/**
		 * Creates a SoundType with manual names for step and break sounds. Sound names must be specified in the sounds.json
		 * 
		 * @param soundNameBreak
		 *            block break sound
		 * @param soundNameStep
		 *            block step sound
		 * @param volume
		 *            default 1.0f
		 * @param frequency
		 *            default 1.0f
		 */
		public SoundType(String soundNameBreak, String soundNameStep, float volume, float frequency) {
			super(soundNameStep, volume, frequency);
			this.soundNameStep = soundNameStep;
			this.soundNameBreak = soundNameBreak;
			this.soundNamePlace = null;
		}

		/**
		 * Creates a SoundType with manual names for step, break and place sounds. Sound names must be specified in the sounds.json
		 * 
		 * @param soundNameBreak
		 *            block break sound
		 * @param soundNameStep
		 *            block step sound
		 * @param soundNamePlace
		 *            block place sound
		 * @param volume
		 *            default 1.0f
		 * @param frequency
		 *            default 1.0f
		 */
		public SoundType(String soundNameBreak, String soundNameStep, String soundNamePlace, float volume, float frequency) {
			super(soundNameStep, volume, frequency);
			this.soundNameStep = soundNameStep;
			this.soundNameBreak = soundNameBreak;
			this.soundNamePlace = soundNamePlace;
		}

		@Override
		public String getBreakSound() {
			if (soundNameBreak == null)
				return Chisel.MOD_ID + ":dig." + this.soundName;
			else
				return this.soundNameBreak;
		}

		@Override
		public String getStepResourcePath() {
			if (soundNameStep == null)
				return Chisel.MOD_ID + ":step." + this.soundName;
			else
				return this.soundNameStep;
		}

		@Override
		public String func_150496_b() {
			if (soundNamePlace == null)
				return getBreakSound();
			else
				return this.soundNamePlace;
		}
	}
}
