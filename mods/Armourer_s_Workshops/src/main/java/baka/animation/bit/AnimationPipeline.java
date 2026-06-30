/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Queues
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.EnumHandSide
 */
package baka.animation.bit;

import baka.animation.instruct.DamageInstruct;
import baka.animation.instruct.FinishInstruct;
import baka.animation.instruct.InstructBase;
import baka.animation.instruct.InstructBuilder;
import baka.animation.instruct.LegActionInstruct;
import baka.animation.instruct.RotateInstruct;
import baka.animation.instruct.SkillInstruct;
import baka.animation.instruct.SoundInstruct;
import baka.animation.instruct.SwordTrailInstruct;
import baka.data.SwordTrailData;
import baka.util.BBUtil;
import com.google.common.collect.Queues;
import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.standard.data.PlayerData;
import io.netty.buffer.ByteBuf;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumHandSide;

public class AnimationPipeline
extends AnimationBit<PlayerData> {
    private final Object queueLock;
    private final Queue<InstructBase> queue;
    private boolean legAction;
    private boolean speedChange;
    protected InstructBase currentInstruct = null;
    private final SwordTrailData rightTrailData = new SwordTrailData();
    private final SwordTrailData leftTrailData = new SwordTrailData();
    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    public AnimationPipeline(boolean legAction, boolean speedChange) {
        this.queue = new ArrayDeque<InstructBase>();
        this.queueLock = new Object();
        this.legAction = legAction;
        this.speedChange = speedChange;
    }

    public AnimationPipeline(Queue<InstructBase> queue, boolean legAction, boolean speedChange) {
        this.queue = queue;
        this.queueLock = new Object();
        this.legAction = legAction;
        this.speedChange = speedChange;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void push(InstructBase instruct) {
        Object object = this.queueLock;
        synchronized (object) {
            this.queue.add(instruct);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void push(AnimationPipeline pipeline) {
        Object object = this.queueLock;
        synchronized (object) {
            this.queue.addAll(pipeline.queue);
        }
    }

    @Override
    public void onPlay(PlayerData data) {
        data.swordTrail.reset();
        data.leftSwordTrail.reset();
    }

    @Override
    public String[] getActions(PlayerData entityData) {
        return new String[0];
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void perform(PlayerData data) {
        Object object = this.queueLock;
        synchronized (object) {
            boolean isStart = false;
            if (this.currentInstruct == null) {
                this.currentInstruct = this.queue.poll();
                isStart = true;
            } else if (!this.currentInstruct.isPerformed()) {
                isStart = true;
            } else if (this.currentInstruct.isFinish()) {
                if (this.currentInstruct instanceof RotateInstruct) {
                    this.currentInstruct.perform(data);
                }
                this.currentInstruct = this.queue.poll();
                isStart = true;
            }
            if (this.currentInstruct != null) {
                long waitTime;
                if (this.rightTrailData.enable) {
                    data.swordTrail.add(data, EnumHandSide.RIGHT, this.rightTrailData.length, 0.0f, 0.0f, 0.0f);
                    data.swordTrail.setColor(this.rightTrailData.red, this.rightTrailData.green, this.rightTrailData.blue);
                } else {
                    data.swordTrail.reset();
                }
                if (this.leftTrailData.enable) {
                    data.leftSwordTrail.add(data, EnumHandSide.LEFT, this.leftTrailData.length, 0.0f, 0.0f, 0.0f);
                    data.leftSwordTrail.setColor(this.leftTrailData.red, this.leftTrailData.green, this.leftTrailData.blue);
                } else {
                    data.leftSwordTrail.reset();
                }
                if (this.currentInstruct instanceof LegActionInstruct) {
                    this.legAction = ((LegActionInstruct)this.currentInstruct).isLegAction();
                    return;
                }
                if (this.currentInstruct instanceof SwordTrailInstruct) {
                    SwordTrailInstruct instruct = (SwordTrailInstruct)this.currentInstruct;
                    if (instruct.isLeft()) {
                        this.leftTrailData.enable = instruct.isEnable();
                        this.leftTrailData.length = instruct.getLength();
                        this.leftTrailData.red = instruct.getSwordTrailRed();
                        this.leftTrailData.green = instruct.getSwordTrailGreen();
                        this.leftTrailData.blue = instruct.getSwordTrailBlue();
                    } else {
                        this.rightTrailData.enable = instruct.isEnable();
                        this.rightTrailData.length = instruct.getLength();
                        this.rightTrailData.red = instruct.getSwordTrailRed();
                        this.rightTrailData.green = instruct.getSwordTrailGreen();
                        this.rightTrailData.blue = instruct.getSwordTrailBlue();
                    }
                }
                this.currentInstruct.start();
                this.currentInstruct.perform(data);
                if (this.currentInstruct.isFinish()) {
                    this.perform(data);
                } else if (isStart && (waitTime = this.currentInstruct.getWaitTime(data)) > 0L && data.getEntity() == Minecraft.getMinecraft().player) {
                    executor.schedule(new WaitTask(data, this.currentInstruct), waitTime, TimeUnit.MILLISECONDS);
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean isFinish() {
        Object object = this.queueLock;
        synchronized (object) {
            return this.currentInstruct == null ? this.queue.isEmpty() : this.queue.isEmpty() && this.currentInstruct.isFinish();
        }
    }

    public static AnimationPipeline from(ByteBuf buf) {
        boolean legAction = BBUtil.readBool(buf);
        boolean speedChange = BBUtil.readBool(buf);
        int queueSize = buf.readInt();
        ArrayDeque queue = Queues.newArrayDeque();
        for (int i2 = 0; i2 < queueSize; ++i2) {
            InstructBase instruct = InstructBuilder.from(buf);
            if (instruct == null) continue;
            queue.add(instruct);
        }
        return new AnimationPipeline(queue, legAction, speedChange);
    }

    public boolean isLegAction() {
        return this.legAction;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void clear() {
        Object object = this.queueLock;
        synchronized (object) {
            this.queue.clear();
        }
    }

    public void setLegAction(boolean legAction) {
        this.legAction = legAction;
    }

    private class WaitTask
    implements Runnable {
        private PlayerData data;
        private InstructBase now;

        public WaitTask(PlayerData data, InstructBase now) {
            this.data = data;
            this.now = now;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void run() {
            if (Minecraft.getMinecraft().player == null) {
                return;
            }
            if (this.now == AnimationPipeline.this.currentInstruct) {
                Object object = AnimationPipeline.this.queueLock;
                synchronized (object) {
                    if (this.now == AnimationPipeline.this.currentInstruct) {
                        if (this.now instanceof RotateInstruct) {
                            this.now.perform(this.data);
                        }
                        this.jump();
                    }
                }
            }
        }

        private void jump() {
            AnimationPipeline.this.currentInstruct = (InstructBase)AnimationPipeline.this.queue.poll();
            if (AnimationPipeline.this.currentInstruct != null) {
                if (AnimationPipeline.this.currentInstruct instanceof FinishInstruct) {
                    AnimationPipeline.this.currentInstruct.perform(this.data);
                    this.jump();
                } else if (AnimationPipeline.this.currentInstruct instanceof SkillInstruct) {
                    AnimationPipeline.this.currentInstruct.perform(this.data);
                    this.jump();
                } else if (AnimationPipeline.this.currentInstruct instanceof LegActionInstruct) {
                    AnimationPipeline.this.legAction = ((LegActionInstruct)AnimationPipeline.this.currentInstruct).isLegAction();
                    this.jump();
                } else if (AnimationPipeline.this.currentInstruct instanceof SoundInstruct) {
                    AnimationPipeline.this.currentInstruct.perform(this.data);
                    this.jump();
                } else if (AnimationPipeline.this.currentInstruct instanceof DamageInstruct) {
                    AnimationPipeline.this.currentInstruct.perform(this.data);
                    this.jump();
                } else {
                    AnimationPipeline.this.currentInstruct.start();
                }
            }
        }
    }
}

