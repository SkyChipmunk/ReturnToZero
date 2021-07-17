package org.cubit.returntozero.listener

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.inventory.ItemStack
import org.cubit.returntozero.constants.FINAL_MONSTERS_KILLED
import org.cubit.returntozero.data.ReturnToZeroData
import org.cubit.returntozero.util.adjustItemKill
import org.cubit.returntozero.util.getItemKill
import org.cubit.returntozero.util.getReturnToZeroItem

class ReturnToZeroListener : Listener {

    @EventHandler
    fun EntityDeathEvent.onEntityDeathEvent() {
        val itemStack = entity.killer?.inventory?.itemInMainHand ?: ItemStack(Material.AIR)
        if (ReturnToZeroData.returnToZeroMap.containsKey(itemStack.type)) {
            itemStack.adjustItemKill()
            //if (itemStack.type == Material.DIAMOND_SWORD && itemStack.itemMeta?.lore?.contains(FINAL_MONSTERS_KILLED)!!) return
            entity.killer?.getReturnToZeroItem()
        }
    }
}