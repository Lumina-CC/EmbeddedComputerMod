package org.luminacc.additions.embedded.block;

import dan200.computercraft.shared.computer.core.ComputerState;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.Property;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import net.minecraft.state.property.EnumProperty;

import static java.util.Objects.isNull;

public class EmbeddedComputerBlock extends HorizontalFacingBlock implements BlockEntityProvider {
    public static EnumProperty powered = EnumProperty.of("state", ComputerState.class);
    public EmbeddedComputerBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(powered,ComputerState.OFF));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING,powered);
    }
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        var comp1 = new EmbeddedComputerBlockEntity(pos,state);
        if (!isNull(comp1.getWorld()) && !comp1.getWorld().isClient() && isNull(comp1.getServerComputer())) {
            comp1.createServerComputer();
        } else if(!isNull(comp1.getWorld()) && !comp1.getWorld().isClient()){
            comp1.getServerComputer().turnOn();
        }
        return comp1;
    }

    // update for peripherals
    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        var comp1 = world.getBlockEntity(pos);
        if (!isNull(comp1) && comp1 instanceof EmbeddedComputerBlockEntity) {
            var comp = (EmbeddedComputerBlockEntity) comp1;
            comp.neighborChanged(pos);
        }
    }
    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        var comp1 = world.getBlockEntity(pos);
        if (!isNull(comp1) && comp1 instanceof EmbeddedComputerBlockEntity) {
            var comp = (EmbeddedComputerBlockEntity) comp1;
            comp.updateInputsImmediately();
        }
    }

    //turn on computer
    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {
        var blockEntity1 = world.getBlockEntity(blockPos);
        if (!world.isClient() && !isNull(blockEntity1)) {
            var blockEntity = (EmbeddedComputerBlockEntity) blockEntity1;
            var computer = blockEntity.createServerComputer();
            computer.turnOn();
        }
        return ActionResult.success(true);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }
}
