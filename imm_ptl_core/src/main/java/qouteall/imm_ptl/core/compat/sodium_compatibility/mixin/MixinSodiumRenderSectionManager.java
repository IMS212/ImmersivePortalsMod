package qouteall.imm_ptl.core.compat.sodium_compatibility.mixin;

import it.unimi.dsi.fastutil.objects.ObjectList;
import me.jellysquid.mods.sodium.client.render.chunk.ChunkRenderList;
import me.jellysquid.mods.sodium.client.render.chunk.RenderSection;
import me.jellysquid.mods.sodium.client.render.chunk.RenderSectionManager;
import net.minecraft.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import qouteall.imm_ptl.core.compat.sodium_compatibility.IESodiumRenderSectionManager;
import qouteall.imm_ptl.core.compat.sodium_compatibility.SodiumRenderingContext;

@Mixin(RenderSectionManager.class)
public class MixinSodiumRenderSectionManager implements IESodiumRenderSectionManager {
    @Shadow
    @Final
    @Mutable
    private ChunkRenderList chunkRenderList;
    
    @Shadow
    @Final
    @Mutable
    private ObjectList<RenderSection> tickableChunks;
    
    @Shadow
    @Final
    @Mutable
    private ObjectList<BlockEntity> visibleBlockEntities;
    
    @Override
    public void ip_swapContext(SodiumRenderingContext context) {
        ChunkRenderList chunkRenderListTmp = chunkRenderList;
        chunkRenderList = context.chunkRenderList;
        context.chunkRenderList = chunkRenderListTmp;
        
        ObjectList<RenderSection> tickableChunksTmp = tickableChunks;
        tickableChunks = context.tickableChunks;
        context.tickableChunks = tickableChunksTmp;
        
        ObjectList<BlockEntity> visibleBlockEntitiesTmp = visibleBlockEntities;
        visibleBlockEntities = context.visibleBlockEntities;
        context.visibleBlockEntities = visibleBlockEntitiesTmp;
    }
}
