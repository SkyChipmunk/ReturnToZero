package org.cubit.returntozero.util

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.cubit.returntozero.constants.FINAL_MONSTERS_KILLED
import org.cubit.returntozero.constants.NUMBER_OF_MONSTERS_KILLED
import org.cubit.returntozero.data.ReturnToZeroData.returnToZeros

fun Player.getReturnToZeroItem(itemStack: ItemStack): ItemStack {
    val indx = returnToZeros.indexOf(itemStack.type)
    val lore = inventory.itemInMainHand.itemMeta?.lore ?: listOf()
    if ((returnToZeros.size - 1) == indx && !(itemStack.itemMeta?.lore?.contains(FINAL_MONSTERS_KILLED)!!)) {
        return ItemStack(returnToZeros[0]).also { it.itemMeta = it.itemMeta?.finalItemKill() }
    }
    return ItemStack(returnToZeros[indx + 1]).also {
        if (lore.contains(FINAL_MONSTERS_KILLED))
            it.itemMeta = it.itemMeta?.finalItemKill()
        else
            it.itemMeta = it.itemMeta?.updateItemKill()
    }
}

fun ItemMeta.updateItemKill(): ItemMeta {
    lore = listOf("$NUMBER_OF_MONSTERS_KILLED 0")
    return this
}

fun ItemMeta.finalItemKill(): ItemMeta {
    lore = listOf("$NUMBER_OF_MONSTERS_KILLED 0", FINAL_MONSTERS_KILLED)
    return this
}

fun ItemMeta.getItemKill(): Int {
    return lore?.get(0)?.replace("$NUMBER_OF_MONSTERS_KILLED ", "")?.toInt() ?: 0
}

fun ItemMeta.adjustItemKill(): ItemMeta {
    if (lore?.contains(FINAL_MONSTERS_KILLED)!!) {
        lore = listOf("$NUMBER_OF_MONSTERS_KILLED ${getItemKill() + 1}", FINAL_MONSTERS_KILLED)
        return this
    }
    lore = listOf("$NUMBER_OF_MONSTERS_KILLED ${getItemKill() + 1}")
    return this
}

