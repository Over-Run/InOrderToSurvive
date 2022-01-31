package io.github.overrun.baka4n.inordertosurvive;

import io.github.overrun.baka4n.inordertosurvive.registry.AllRegistry;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.github.overrun.baka4n.inordertosurvive.Modid.modid;


// The value here should match an entry in the META-INF/mods.toml file

@Mod(modid)
public class InOrderToSurvive
{
    //I event Bus for forge
    public IEventBus iEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    // using log4j
    private static final Logger msg = LogManager.getLogger();
    // log message void
    public static void LOGGER(String var1, String var2, Object throwable) {
        switch (var1) {
            case "info" -> {
                if (throwable instanceof Throwable) {
                    msg.info("[" + modid + "]" + var2, (Throwable) throwable);
                } else {
                    msg.info("[" + modid + "]" + var2);
                }
            }
            case "debug" -> {
                msg.debug("[" + modid + "]" + var2);
            }
        }
    }
    //add a comment
    public InOrderToSurvive() {
        //registry all -> block item fluid and other
        AllRegistry.registry(iEventBus);
        // Register the setup method for mod loading
        iEventBus.addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER("info", "HELLO FROM PREINIT",null);
        LOGGER("info", "DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
