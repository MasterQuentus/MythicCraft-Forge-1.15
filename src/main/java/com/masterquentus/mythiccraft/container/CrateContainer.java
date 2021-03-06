package com.masterquentus.mythiccraft.container;

import com.masterquentus.mythiccraft.MythicCraft;
import com.masterquentus.mythiccraft.init.ModContainerTypes;
import com.masterquentus.mythiccraft.tileentity.CrateTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

import java.util.Objects;

public class CrateContainer extends Container {

	public final CrateTileEntity tileEntity;
	@SuppressWarnings("unused")
	private final IWorldPosCallable canInteractWithCallable;

	int NUM_ROWS = 4;

	public CrateContainer(final int windowId, final PlayerInventory playerInventory, final CrateTileEntity tileEntity) {
		super(ModContainerTypes.ALDER_CRATE.get(), windowId);
		this.tileEntity = tileEntity;
		this.canInteractWithCallable = IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos());

		// Main Inventory
		int startX = 8;
		int startY = 18;
		int slotSizePlus2 = 18;
		for (int row = 0; row < NUM_ROWS; ++row) {
			for (int column = 0; column < 9; ++column) {
				this.addSlot(new Slot(tileEntity, (row * 9) + column, startX + (column * slotSizePlus2), startY + (row * slotSizePlus2)));
			}
		}

		// Main Player Inventory
		int startPlayerInvY = startY * 5 + 12;
		for (int row = 0; row < 3; ++row) {
			for (int column = 0; column < 9; ++column) {
				this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, startX + (column * slotSizePlus2), startPlayerInvY + (row * slotSizePlus2)));
			}
		}

		// Hotbar
		int hotbarY = startPlayerInvY + (startPlayerInvY / 2) + 7;
		for (int column = 0; column < 9; ++column) {
			this.addSlot(new Slot(playerInventory, column, startX + (column * slotSizePlus2), hotbarY));
		}
	}

	private static CrateTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
		Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
		Objects.requireNonNull(data, "data cannot be null");
		final TileEntity tileAtPos = playerInventory.player.level.getBlockEntity(data.readBlockPos());
		if(tileAtPos instanceof CrateTileEntity) {
			return (CrateTileEntity)tileAtPos;
		}
		throw new IllegalStateException("Tile entity is not correct!" + tileAtPos);
	}

	public CrateContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
		this(windowId, playerInventory, getTileEntity(playerInventory, data));
	}
	
	@Override
	public boolean stillValid(PlayerEntity playerIn) {
		return true;
		// todo: check dist and instenceof cratetilenentity
		// return stillValid(canInteractWithCallable, playerIn, BlockInit.ALDER_CRATE.get());
	}


	@Override
	public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if(slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			MythicCraft.LOGGER.debug(itemstack1);
			itemstack = itemstack1.copy();
			if(index < (NUM_ROWS * 9)) {
				if(this.moveItemStackTo(itemstack, (NUM_ROWS * 9), this.slots.size(), true)) {
					MythicCraft.LOGGER.debug(1);
					slot.set(ItemStack.EMPTY); // i really shouldn't need this. idk whats going on
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(itemstack1, 0, (NUM_ROWS * 9), false)) {
				MythicCraft.LOGGER.debug(2);
				return ItemStack.EMPTY;
			}
			
			if(itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}

		MythicCraft.LOGGER.debug(3);
		return itemstack;
	}
	
}