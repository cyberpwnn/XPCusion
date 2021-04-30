package com.example.examplemod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod(modid = ExampleMod.MODID, name = ExampleMod.NAME, version = ExampleMod.VERSION)
public class ExampleMod
{
    private long ms = System.currentTimeMillis();
    public static final String MODID = "xpcusion";
    public static final String NAME = "XP Cusion";
    public static final String VERSION = "1.0";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {

    }

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event)
    {
        if(!event.side.isServer())
        {
            return;
        }

        if(event.phase == TickEvent.Phase.START) {
            if(System.currentTimeMillis() - ms < (Math.random() * 2000) + 3000)
            {
                return;
            }

            ms = System.currentTimeMillis();

            event.world.playerEntities.iterator().forEachRemaining((i) -> {
                if(i.experienceLevel >= 15)
                {
                    return;
                }

                i.addExperience(2);
            });
        }
    }
}
