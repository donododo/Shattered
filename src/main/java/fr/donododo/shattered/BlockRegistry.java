package fr.donododo.shattered;

import fr.donododo.shattered.block.Crystal;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            Shattered.MODID);

    public static final RegistryObject<Crystal> CRYSTAL = BLOCKS.register("crystal",
            Crystal::new);

}
