package sfschouten.dronemod.item.battery;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import sfschouten.dronemod.ModularMulticopterDrones;
import sfschouten.dronemod.init.MMDCreativeTabs;
import cofh.api.energy.IEnergyContainerItem;

public abstract class ItemDroneBattery extends Item implements IEnergyContainerItem {
	protected int capacity;
	protected int maxReceive;
	protected int maxExtract;

	public ItemDroneBattery() {
		super();
		this.setCreativeTab(MMDCreativeTabs.tabGeneral);
		this.setMaxStackSize(16);
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setMaxTransfer(int maxTransfer) {
		setMaxReceive(maxTransfer);
		setMaxExtract(maxTransfer);
	}

	public void setMaxReceive(int maxReceive) {
		this.maxReceive = maxReceive;
	}

	public void setMaxExtract(int maxExtract) {
		this.maxExtract = maxExtract;
	}

	/* IEnergyContainerItem */
	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
		if (container.stackTagCompound == null) {
			container.stackTagCompound = new NBTTagCompound();
		}
		int energy = container.stackTagCompound.getInteger("Energy");
		int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));

		if (!simulate) {
			energy += energyReceived;
			container.stackTagCompound.setInteger("Energy", energy);
		}
		return energyReceived;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) {
			return 0;
		}
		int energy = container.stackTagCompound.getInteger("Energy");
		int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));

		if (!simulate) {
			energy -= energyExtracted;
			container.stackTagCompound.setInteger("Energy", energy);
		}
		return energyExtracted;
	}

	@Override
	public int getEnergyStored(ItemStack container) {
		if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) {
			return 0;
		}
		return container.stackTagCompound.getInteger("Energy");
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		return capacity;
	}
}