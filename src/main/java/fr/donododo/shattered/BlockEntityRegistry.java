package fr.donododo.shattered;

import fr.donododo.shattered.entities.CrystalBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityRegistry {

    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister
            .create(ForgeRegistries.BLOCK_ENTITY_TYPES, Shattered.MODID);

    public static final RegistryObject<BlockEntityType<CrystalBlock>> CRYSTAL_ENTITYBLOCK = TILES
            .register("habitat", () -> BlockEntityType.Builder
                    .of(CrystalBlock::new, BlockRegistry.CRYSTAL.get()).build(null));

}
