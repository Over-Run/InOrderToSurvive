package io.github.overrun.baka4n.inordertosurvive;

import io.github.overrun.baka4n.inordertosurvive.registry.AllRegistry;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static io.github.overrun.baka4n.inordertosurvive.Final.inOrderToSurvive;
import static io.github.overrun.baka4n.inordertosurvive.Final.msg;

// The value here should match an entry in the META-INF/mods.toml file

@Mod(inOrderToSurvive)
public class InOrderToSurvive
{
    //I event Bus for forge
    public IEventBus iEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    // log message void
    public static void LOGGER(String var1, String var2, Object throwable) {
        switch (var1) {
            case "info" -> {
                if (throwable instanceof Throwable) {
                    msg.info('[' + inOrderToSurvive + ']' + var2, (Throwable) throwable);
                } else {
                    msg.info('[' + inOrderToSurvive + ']' + var2);
                }
            }
            case "debug" -> msg.debug('[' + inOrderToSurvive + ']' + var2);
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
