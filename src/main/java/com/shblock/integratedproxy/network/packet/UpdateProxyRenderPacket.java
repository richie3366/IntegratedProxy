package com.shblock.integratedproxy.network.packet;

import com.shblock.integratedproxy.client.data.AccessProxyClientData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.cyclops.cyclopscore.datastructure.DimPos;
import org.cyclops.cyclopscore.network.CodecField;
import org.cyclops.cyclopscore.network.PacketCodec;

public class UpdateProxyRenderPacket extends PacketCodec {
    @CodecField
    private BlockPos proxy_pos;
    @CodecField
    private RegistryKey<World> proxy_dim;
    @CodecField
    private BlockPos target_pos;
    @CodecField
    private RegistryKey<World> target_dim;

    public UpdateProxyRenderPacket() { }

    public UpdateProxyRenderPacket(DimPos proxy_pos, DimPos target_pos) {
        this.proxy_pos = proxy_pos.getBlockPos();
        this.proxy_dim = proxy_pos.getWorldKey();
        this.target_pos = target_pos.getBlockPos();
        this.target_dim = target_pos.getWorldKey();
    }

    @Override
    public boolean isAsync() {
        return false;
    }

    @Override
    public void actionClient(World world, PlayerEntity player) {
        AccessProxyClientData.getInstance().putTarget(
                DimPos.of(this.proxy_dim, this.proxy_pos),
                DimPos.of(this.target_dim, this.target_pos)
        );
    }

    @Override
    public void actionServer(World world, ServerPlayerEntity player) { }
}
