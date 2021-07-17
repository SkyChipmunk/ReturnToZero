package org.cubit.returntozero

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.cubit.returntozero.listener.ReturnToZeroListener

class ReturnToZero : JavaPlugin(){

    companion object {
        lateinit var inst: JavaPlugin
    }

    override fun onEnable() {
        inst = this
        Bukkit.getPluginManager().registerEvents(ReturnToZeroListener() , this)

    }
}