package org.cubit.returntozero.util

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.cubit.returntozero.constants.FINAL_MONSTERS_KILLED
import org.cubit.returntozero.constants.NUMBER_OF_MONSTERS_KILLED
import org.cubit.returntozero.data.ReturnToZeroData.returnToZeroMap
import org.cubit.returntozero.data.ReturnToZeroData.returnToZeros

fun Player.getReturnToZeroItem() {
    if (inventory.itemInMainHand.hasItemEntityKillNumber()) {
        inventory.setItemInMainHand(updateItem())
    }

}

fun Player.updateItem(): ItemStack {
    val indx = returnToZeros.indexOf(inventory.itemInMainHand.type)
    val itemMeta = inventory.itemInMainHand.itemMeta
    val lore = itemMeta?.lore ?: listOf()

    if ((returnToZeros.size - 1) == indx && !(inventory.itemInMainHand.itemMeta?.lore?.contains(FINAL_MONSTERS_KILLED)!!)) {
        return ItemStack(returnToZeros[0]).also { it.finalItemKill() }
    }
    println(returnToZeros[indx + 1])
    return ItemStack(returnToZeros[indx + 1]).also {
        if (lore.contains(FINAL_MONSTERS_KILLED))
            it.finalItemKill()
        else
            it.updateItemKill()
    }

}

fun ItemStack.getEntityKillNumber(): Int {
    return itemMeta?.lore?.get(0)?.replace("$NUMBER_OF_MONSTERS_KILLED ", "")?.toInt() ?: 0
}

fun ItemStack.getUpdateKillNumber(): Int {
    return returnToZeroMap[type]!!

}

fun ItemStack.hasItemEntityKillNumber(): Boolean {
    if (getEntityKillNumber() > getUpdateKillNumber()) {
        return true
    }
    return false

}

fun ItemStack.updateItemKill(): ItemStack {
    itemMeta?.lore = listOf("$NUMBER_OF_MONSTERS_KILLED 0")
    return this
}

fun ItemStack.finalItemKill(): ItemStack {
    lore(listOf("$NUMBER_OF_MONSTERS_KILLED 0", FINAL_MONSTERS_KILLED))
    return this
}

fun ItemStack.getItemKill(): Int {
    return itemMeta?.lore?.get(0)?.replace("$NUMBER_OF_MONSTERS_KILLED ", "")?.toInt() ?: 0
}

fun ItemStack.adjustItemKill() {
    val lore = itemMeta?.lore ?: listOf()
    if (lore.contains(FINAL_MONSTERS_KILLED)) {
        lore(listOf("$NUMBER_OF_MONSTERS_KILLED ${getItemKill() + 1}", FINAL_MONSTERS_KILLED))
        return
    }
    lore(listOf("$NUMBER_OF_MONSTERS_KILLED ${getItemKill() + 1}"))
}

fun ItemStack.lore(lore: List<String>) {
    itemMeta = itemMeta?.also {
        it.lore = lore
    }
}

