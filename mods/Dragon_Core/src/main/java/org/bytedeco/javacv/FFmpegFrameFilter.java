/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacv;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.Locale;
import org.bytedeco.ffmpeg.avfilter.AVFilter;
import org.bytedeco.ffmpeg.avfilter.AVFilterContext;
import org.bytedeco.ffmpeg.avfilter.AVFilterGraph;
import org.bytedeco.ffmpeg.avfilter.AVFilterInOut;
import org.bytedeco.ffmpeg.avutil.AVFrame;
import org.bytedeco.ffmpeg.avutil.AVRational;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avfilter;
import org.bytedeco.ffmpeg.global.avformat;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.ffmpeg.global.postproc;
import org.bytedeco.ffmpeg.global.swresample;
import org.bytedeco.ffmpeg.global.swscale;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.PointerScope;
import org.bytedeco.javacpp.ShortPointer;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameFilter;

public class FFmpegFrameFilter
extends FrameFilter {
    private static Exception loadingException = null;
    AVFilterContext buffersink_ctx;
    AVFilterContext[] buffersrc_ctx;
    AVFilterContext[] setpts_ctx;
    AVFilterGraph filter_graph;
    AVRational time_base;
    AVFilterContext abuffersink_ctx;
    AVFilterContext[] abuffersrc_ctx;
    AVFilterContext[] asetpts_ctx;
    AVFilterGraph afilter_graph;
    AVRational atime_base;
    AVFrame image_frame;
    AVFrame samples_frame;
    AVFrame filt_frame;
    BytePointer[] image_ptr;
    BytePointer[] image_ptr2;
    BytePointer[] samples_ptr;
    Buffer[] image_buf;
    Buffer[] image_buf2;
    Buffer[] samples_buf;
    Frame frame;
    Frame inframe;
    private volatile boolean started = false;

    public static void tryLoad() throws Exception {
        if (loadingException != null) {
            throw loadingException;
        }
        try {
            Loader.load(avutil.class);
            Loader.load(avcodec.class);
            Loader.load(avformat.class);
            Loader.load(postproc.class);
            Loader.load(swresample.class);
            Loader.load(swscale.class);
            Loader.load(avfilter.class);
            avformat.av_register_all();
            avfilter.avfilter_register_all();
        }
        catch (Throwable t2) {
            if (t2 instanceof Exception) {
                loadingException = (Exception)t2;
                throw loadingException;
            }
            loadingException = new Exception("Failed to load " + FFmpegFrameRecorder.class, t2);
            throw loadingException;
        }
    }

    public FFmpegFrameFilter(String videoFilters, String audioFilters, int imageWidth, int imageHeight, int audioChannels) {
        this.filters = videoFilters;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.pixelFormat = 3;
        this.frameRate = 30.0;
        this.aspectRatio = 0.0;
        this.videoInputs = 1;
        this.afilters = audioFilters;
        this.audioChannels = audioChannels;
        this.sampleFormat = 1;
        this.sampleRate = 44100;
        this.audioInputs = 1;
    }

    public FFmpegFrameFilter(String filters, int imageWidth, int imageHeight) {
        this(filters, null, imageWidth, imageHeight, 0);
    }

    public FFmpegFrameFilter(String afilters, int audioChannels) {
        this(null, afilters, 0, 0, audioChannels);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void release() throws Exception {
        Class<avfilter> clazz = avfilter.class;
        synchronized (avfilter.class) {
            this.releaseUnsafe();
            // ** MonitorExit[var1_1] (shouldn't be in output)
            return;
        }
    }

    public synchronized void releaseUnsafe() throws Exception {
        int i2;
        this.started = false;
        if (this.image_ptr2 != null) {
            for (i2 = 0; i2 < this.image_ptr2.length; ++i2) {
                avutil.av_free(this.image_ptr2[i2]);
            }
            this.image_ptr2 = null;
        }
        if (this.filter_graph != null) {
            avfilter.avfilter_graph_free(this.filter_graph);
            this.buffersink_ctx.releaseReference();
            for (i2 = 0; i2 < this.buffersrc_ctx.length; ++i2) {
                this.buffersrc_ctx[i2].releaseReference();
                this.setpts_ctx[i2].releaseReference();
            }
            this.time_base.releaseReference();
            this.buffersink_ctx = null;
            this.buffersrc_ctx = null;
            this.setpts_ctx = null;
            this.filter_graph = null;
            this.time_base = null;
        }
        if (this.afilter_graph != null) {
            avfilter.avfilter_graph_free(this.afilter_graph);
            this.abuffersink_ctx.releaseReference();
            for (i2 = 0; i2 < this.abuffersrc_ctx.length; ++i2) {
                this.abuffersrc_ctx[i2].releaseReference();
                this.asetpts_ctx[i2].releaseReference();
            }
            this.atime_base.releaseReference();
            this.abuffersink_ctx = null;
            this.abuffersrc_ctx = null;
            this.asetpts_ctx = null;
            this.afilter_graph = null;
            this.atime_base = null;
        }
        if (this.image_frame != null) {
            avutil.av_frame_free(this.image_frame);
            this.image_frame = null;
        }
        if (this.samples_frame != null) {
            avutil.av_frame_free(this.samples_frame);
            this.samples_frame = null;
        }
        if (this.filt_frame != null) {
            avutil.av_frame_free(this.filt_frame);
            this.filt_frame = null;
        }
        this.frame = null;
    }

    protected void finalize() throws Throwable {
        super.finalize();
        this.release();
    }

    @Override
    public int getImageWidth() {
        return this.buffersink_ctx != null ? avfilter.av_buffersink_get_w(this.buffersink_ctx) : super.getImageWidth();
    }

    @Override
    public int getImageHeight() {
        return this.buffersink_ctx != null ? avfilter.av_buffersink_get_h(this.buffersink_ctx) : super.getImageHeight();
    }

    @Override
    public int getPixelFormat() {
        return this.buffersink_ctx != null ? avfilter.av_buffersink_get_format(this.buffersink_ctx) : super.getPixelFormat();
    }

    @Override
    public double getFrameRate() {
        if (this.buffersink_ctx != null) {
            AVRational r2 = avfilter.av_buffersink_get_frame_rate(this.buffersink_ctx);
            if (r2.num() == 0 && r2.den() == 0) {
                r2 = avfilter.av_buffersink_get_time_base(this.buffersink_ctx);
                return (double)r2.den() / (double)r2.num();
            }
            return (double)r2.num() / (double)r2.den();
        }
        return super.getFrameRate();
    }

    @Override
    public double getAspectRatio() {
        if (this.buffersink_ctx != null) {
            AVRational r2 = avfilter.av_buffersink_get_sample_aspect_ratio(this.buffersink_ctx);
            double a2 = (double)r2.num() / (double)r2.den();
            return a2 == 0.0 ? 1.0 : a2;
        }
        return super.getAspectRatio();
    }

    @Override
    public int getAudioChannels() {
        return this.abuffersink_ctx != null ? avfilter.av_buffersink_get_channels(this.abuffersink_ctx) : super.getAudioChannels();
    }

    @Override
    public int getSampleFormat() {
        return this.abuffersink_ctx != null ? avfilter.av_buffersink_get_format(this.abuffersink_ctx) : super.getSampleFormat();
    }

    @Override
    public int getSampleRate() {
        return this.abuffersink_ctx != null ? avfilter.av_buffersink_get_sample_rate(this.abuffersink_ctx) : super.getSampleRate();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void start() throws Exception {
        Class<avfilter> clazz = avfilter.class;
        synchronized (avfilter.class) {
            this.startUnsafe();
            // ** MonitorExit[var1_1] (shouldn't be in output)
            return;
        }
    }

    public synchronized void startUnsafe() throws Exception {
        try (PointerScope scope = new PointerScope(new Class[0]);){
            if (this.frame != null) {
                throw new Exception("start() has already been called: Call stop() before calling start() again.");
            }
            this.image_frame = avutil.av_frame_alloc();
            this.samples_frame = avutil.av_frame_alloc();
            this.filt_frame = avutil.av_frame_alloc();
            this.image_ptr = new BytePointer[]{null};
            this.image_ptr2 = new BytePointer[]{null};
            this.image_buf = new Buffer[]{null};
            this.image_buf2 = new Buffer[]{null};
            this.samples_ptr = new BytePointer[]{null};
            this.samples_buf = new Buffer[]{null};
            this.frame = new Frame();
            if (this.image_frame == null || this.samples_frame == null || this.filt_frame == null) {
                throw new Exception("Could not allocate frames");
            }
            if (this.filters != null && this.imageWidth > 0 && this.imageHeight > 0 && this.videoInputs > 0) {
                this.startVideoUnsafe();
            }
            if (this.afilters != null && this.audioChannels > 0 && this.audioInputs > 0) {
                this.startAudioUnsafe();
            }
            this.started = true;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void startVideoUnsafe() throws Exception {
        AVFilter buffersrc = avfilter.avfilter_get_by_name("buffer");
        AVFilter buffersink = avfilter.avfilter_get_by_name("buffersink");
        AVFilter setpts = avfilter.avfilter_get_by_name("setpts");
        AVFilterInOut[] outputs = new AVFilterInOut[this.videoInputs];
        AVFilterInOut inputs = avfilter.avfilter_inout_alloc();
        AVRational time_base = avutil.av_inv_q(avutil.av_d2q(this.frameRate, 1001000));
        int[] pix_fmts = new int[]{this.pixelFormat, -1};
        try {
            int ret;
            this.filter_graph = avfilter.avfilter_graph_alloc();
            if (outputs == null || inputs == null || this.filter_graph == null) {
                throw new Exception("Could not allocate video filter graph: Out of memory?");
            }
            AVRational r2 = avutil.av_d2q(this.aspectRatio > 0.0 ? this.aspectRatio : 1.0, 255);
            String args = String.format(Locale.ROOT, "video_size=%dx%d:pix_fmt=%d:time_base=%d/%d:pixel_aspect=%d/%d", this.imageWidth, this.imageHeight, this.pixelFormat, time_base.num(), time_base.den(), r2.num(), r2.den());
            this.buffersrc_ctx = new AVFilterContext[this.videoInputs];
            this.setpts_ctx = new AVFilterContext[this.videoInputs];
            for (int i2 = 0; i2 < this.videoInputs; ++i2) {
                String name = this.videoInputs > 1 ? i2 + ":v" : "in";
                outputs[i2] = avfilter.avfilter_inout_alloc();
                this.buffersrc_ctx[i2] = (AVFilterContext)new AVFilterContext().retainReference();
                ret = avfilter.avfilter_graph_create_filter(this.buffersrc_ctx[i2], buffersrc, name, args, null, this.filter_graph);
                if (ret < 0) {
                    throw new Exception("avfilter_graph_create_filter() error " + ret + ": Cannot create video buffer source.");
                }
                this.setpts_ctx[i2] = (AVFilterContext)new AVFilterContext().retainReference();
                ret = avfilter.avfilter_graph_create_filter(this.setpts_ctx[i2], setpts, this.videoInputs > 1 ? "setpts" + i2 : "setpts", "N", null, this.filter_graph);
                if (ret < 0) {
                    throw new Exception("avfilter_graph_create_filter() error " + ret + ": Cannot create setpts filter.");
                }
                ret = avfilter.avfilter_link(this.buffersrc_ctx[i2], 0, this.setpts_ctx[i2], 0);
                if (ret < 0) {
                    throw new Exception("avfilter_graph_create_filter() error " + ret + ": Cannot link setpts filter.");
                }
                outputs[i2].name(avutil.av_strdup(new BytePointer(name)));
                outputs[i2].filter_ctx(this.setpts_ctx[i2]);
                outputs[i2].pad_idx(0);
                outputs[i2].next(null);
                if (i2 <= 0) continue;
                outputs[i2 - 1].next(outputs[i2]);
            }
            String name = this.videoInputs > 1 ? "v" : "out";
            this.buffersink_ctx = (AVFilterContext)new AVFilterContext().retainReference();
            ret = avfilter.avfilter_graph_create_filter(this.buffersink_ctx, buffersink, name, null, null, this.filter_graph);
            if (ret < 0) {
                throw new Exception("avfilter_graph_create_filter() error " + ret + ": Cannot create video buffer sink.");
            }
            inputs.name(avutil.av_strdup(new BytePointer(name)));
            inputs.filter_ctx(this.buffersink_ctx);
            inputs.pad_idx(0);
            inputs.next(null);
            ret = avfilter.avfilter_graph_parse_ptr(this.filter_graph, this.filters, inputs, outputs[0], null);
            if (ret < 0) {
                throw new Exception("avfilter_graph_parse_ptr() error " + ret);
            }
            ret = avfilter.avfilter_graph_config(this.filter_graph, null);
            if (ret < 0) {
                throw new Exception("avfilter_graph_config() error " + ret);
            }
            this.time_base = (AVRational)avfilter.av_buffersink_get_time_base(this.buffersink_ctx).retainReference();
        }
        finally {
            avfilter.avfilter_inout_free(inputs);
            avfilter.avfilter_inout_free(outputs[0]);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void startAudioUnsafe() throws Exception {
        AVFilter abuffersrc = avfilter.avfilter_get_by_name("abuffer");
        AVFilter abuffersink = avfilter.avfilter_get_by_name("abuffersink");
        AVFilter asetpts = avfilter.avfilter_get_by_name("asetpts");
        AVFilterInOut[] aoutputs = new AVFilterInOut[this.audioInputs];
        AVFilterInOut ainputs = avfilter.avfilter_inout_alloc();
        int[] sample_fmts = new int[]{this.sampleFormat, -1};
        try {
            int ret;
            this.afilter_graph = avfilter.avfilter_graph_alloc();
            if (aoutputs == null || ainputs == null || this.afilter_graph == null) {
                throw new Exception("Could not allocate audio filter graph: Out of memory?");
            }
            this.abuffersrc_ctx = new AVFilterContext[this.audioInputs];
            this.asetpts_ctx = new AVFilterContext[this.audioInputs];
            for (int i2 = 0; i2 < this.audioInputs; ++i2) {
                String name = this.audioInputs > 1 ? i2 + ":a" : "in";
                aoutputs[i2] = avfilter.avfilter_inout_alloc();
                String aargs = String.format(Locale.ROOT, "channels=%d:sample_fmt=%d:sample_rate=%d:channel_layout=%d", this.audioChannels, this.sampleFormat, this.sampleRate, avutil.av_get_default_channel_layout(this.audioChannels));
                this.abuffersrc_ctx[i2] = (AVFilterContext)new AVFilterContext().retainReference();
                ret = avfilter.avfilter_graph_create_filter(this.abuffersrc_ctx[i2], abuffersrc, name, aargs, null, this.afilter_graph);
                if (ret < 0) {
                    throw new Exception("avfilter_graph_create_filter() error " + ret + ": Cannot create audio buffer source.");
                }
                this.asetpts_ctx[i2] = (AVFilterContext)new AVFilterContext().retainReference();
                ret = avfilter.avfilter_graph_create_filter(this.asetpts_ctx[i2], asetpts, this.audioInputs > 1 ? "asetpts" + i2 : "asetpts", "N", null, this.afilter_graph);
                if (ret < 0) {
                    throw new Exception("avfilter_graph_create_filter() error " + ret + ": Cannot create asetpts filter.");
                }
                ret = avfilter.avfilter_link(this.abuffersrc_ctx[i2], 0, this.asetpts_ctx[i2], 0);
                if (ret < 0) {
                    throw new Exception("avfilter_graph_create_filter() error " + ret + ": Cannot link asetpts filter.");
                }
                aoutputs[i2].name(avutil.av_strdup(new BytePointer(name)));
                aoutputs[i2].filter_ctx(this.asetpts_ctx[i2]);
                aoutputs[i2].pad_idx(0);
                aoutputs[i2].next(null);
                if (i2 <= 0) continue;
                aoutputs[i2 - 1].next(aoutputs[i2]);
            }
            String name = this.audioInputs > 1 ? "a" : "out";
            this.abuffersink_ctx = (AVFilterContext)new AVFilterContext().retainReference();
            ret = avfilter.avfilter_graph_create_filter(this.abuffersink_ctx, abuffersink, name, null, null, this.afilter_graph);
            if (ret < 0) {
                throw new Exception("avfilter_graph_create_filter() error " + ret + ": Cannot create audio buffer sink.");
            }
            ainputs.name(avutil.av_strdup(new BytePointer(name)));
            ainputs.filter_ctx(this.abuffersink_ctx);
            ainputs.pad_idx(0);
            ainputs.next(null);
            ret = avfilter.avfilter_graph_parse_ptr(this.afilter_graph, this.afilters, ainputs, aoutputs[0], null);
            if (ret < 0) {
                throw new Exception("avfilter_graph_parse_ptr() error " + ret);
            }
            ret = avfilter.avfilter_graph_config(this.afilter_graph, null);
            if (ret < 0) {
                throw new Exception("avfilter_graph_config() error " + ret);
            }
            this.atime_base = (AVRational)avfilter.av_buffersink_get_time_base(this.abuffersink_ctx).retainReference();
        }
        finally {
            avfilter.avfilter_inout_free(ainputs);
            avfilter.avfilter_inout_free(aoutputs[0]);
        }
    }

    @Override
    public void stop() throws Exception {
        this.release();
    }

    @Override
    public void push(Frame frame) throws Exception {
        this.push(frame, frame != null && frame.opaque instanceof AVFrame ? ((AVFrame)frame.opaque).format() : -1);
    }

    public void push(Frame frame, int pixelFormat) throws Exception {
        this.push(0, frame, pixelFormat);
    }

    public void push(int n2, Frame frame) throws Exception {
        this.push(n2, frame, frame != null && frame.opaque instanceof AVFrame ? ((AVFrame)frame.opaque).format() : -1);
    }

    public synchronized void push(int n2, Frame frame, int pixelFormat) throws Exception {
        if (!this.started) {
            throw new Exception("start() was not called successfully!");
        }
        this.inframe = frame;
        if (frame != null && frame.image != null && this.buffersrc_ctx != null) {
            this.image_frame.pts(frame.timestamp * (long)this.time_base.den() / (1000000L * (long)this.time_base.num()));
            this.pushImage(n2, frame.imageWidth, frame.imageHeight, frame.imageDepth, frame.imageChannels, frame.imageStride, pixelFormat, frame.image);
        }
        if (frame != null && frame.samples != null && this.abuffersrc_ctx != null) {
            this.samples_frame.pts(frame.timestamp * (long)this.atime_base.den() / (1000000L * (long)this.atime_base.num()));
            this.pushSamples(n2, frame.audioChannels, this.sampleRate, this.sampleFormat, frame.samples);
        }
        if (frame == null || frame.image == null && frame.samples == null) {
            if (this.buffersrc_ctx != null && n2 < this.buffersrc_ctx.length) {
                avfilter.av_buffersrc_add_frame_flags(this.buffersrc_ctx[n2], null, 4);
            }
            if (this.abuffersrc_ctx != null && n2 < this.abuffersrc_ctx.length) {
                avfilter.av_buffersrc_add_frame_flags(this.abuffersrc_ctx[n2], null, 4);
            }
        }
    }

    public synchronized void pushImage(int n2, int width, int height, int depth, int channels, int stride, int pixelFormat, Buffer ... image) throws Exception {
        try (PointerScope scope = new PointerScope(new Class[0]);){
            BytePointer data;
            if (!this.started) {
                throw new Exception("start() was not called successfully!");
            }
            int step = stride * Math.abs(depth) / 8;
            BytePointer bytePointer = data = image[0] instanceof ByteBuffer ? new BytePointer((ByteBuffer)image[0]).position(0L) : new BytePointer((Pointer)new Pointer(image[0]).position(0L));
            if (pixelFormat == -1) {
                if ((depth == 8 || depth == -8) && channels == 3) {
                    pixelFormat = 3;
                } else if ((depth == 8 || depth == -8) && channels == 1) {
                    pixelFormat = 8;
                } else if ((depth == 16 || depth == -16) && channels == 1) {
                    pixelFormat = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN) ? 29 : 30;
                } else if ((depth == 8 || depth == -8) && channels == 4) {
                    pixelFormat = 26;
                } else if ((depth == 8 || depth == -8) && channels == 2) {
                    pixelFormat = 24;
                } else {
                    throw new Exception("Could not guess pixel format of image: depth=" + depth + ", channels=" + channels);
                }
            }
            if (pixelFormat == 24) {
                step = width;
            }
            avutil.av_image_fill_arrays(new PointerPointer(this.image_frame), this.image_frame.linesize(), data, pixelFormat, width, height, 1);
            this.image_frame.linesize(0, step);
            this.image_frame.format(pixelFormat);
            this.image_frame.width(width);
            this.image_frame.height(height);
            int ret = avfilter.av_buffersrc_add_frame_flags(this.buffersrc_ctx[n2], this.image_frame, 12);
            if (ret < 0) {
                throw new Exception("av_buffersrc_add_frame_flags() error " + ret + ": Error while feeding the filtergraph.");
            }
        }
    }

    public synchronized void pushSamples(int n2, int audioChannels, int sampleRate, int sampleFormat, Buffer ... samples) throws Exception {
        try (PointerScope scope = new PointerScope(new Class[0]);){
            int i2;
            int sampleSize;
            if (!this.started) {
                throw new Exception("start() was not called successfully!");
            }
            Pointer[] data = new Pointer[samples.length];
            int n3 = samples != null ? (samples[0].limit() - samples[0].position()) / (samples.length > 1 ? 1 : audioChannels) : (sampleSize = 0);
            if (samples != null && samples[0] instanceof ByteBuffer) {
                sampleFormat = data.length > 1 ? 5 : 0;
                for (i2 = 0; i2 < data.length; ++i2) {
                    data[i2] = new BytePointer((ByteBuffer)samples[i2]);
                }
            } else if (samples != null && samples[0] instanceof ShortBuffer) {
                sampleFormat = data.length > 1 ? 6 : 1;
                for (i2 = 0; i2 < data.length; ++i2) {
                    data[i2] = new ShortPointer((ShortBuffer)samples[i2]);
                }
            } else if (samples != null && samples[0] instanceof IntBuffer) {
                sampleFormat = data.length > 1 ? 7 : 2;
                for (i2 = 0; i2 < data.length; ++i2) {
                    data[i2] = new IntPointer((IntBuffer)samples[i2]);
                }
            } else if (samples != null && samples[0] instanceof FloatBuffer) {
                sampleFormat = data.length > 1 ? 8 : 3;
                for (i2 = 0; i2 < data.length; ++i2) {
                    data[i2] = new FloatPointer((FloatBuffer)samples[i2]);
                }
            } else if (samples != null && samples[0] instanceof DoubleBuffer) {
                sampleFormat = data.length > 1 ? 9 : 4;
                for (i2 = 0; i2 < data.length; ++i2) {
                    data[i2] = new DoublePointer((DoubleBuffer)samples[i2]);
                }
            } else if (samples != null) {
                for (i2 = 0; i2 < data.length; ++i2) {
                    data[i2] = new Pointer(samples[i2]);
                }
            }
            avutil.av_samples_fill_arrays(new PointerPointer(this.samples_frame), this.samples_frame.linesize(), new BytePointer(data[0]), audioChannels, sampleSize, sampleFormat, 1);
            for (i2 = 0; i2 < samples.length; ++i2) {
                this.samples_frame.data(i2, new BytePointer(data[i2]));
            }
            this.samples_frame.channels(audioChannels);
            this.samples_frame.channel_layout(avutil.av_get_default_channel_layout(audioChannels));
            this.samples_frame.nb_samples(sampleSize);
            this.samples_frame.format(sampleFormat);
            this.samples_frame.sample_rate(sampleRate);
            int ret = avfilter.av_buffersrc_add_frame_flags(this.abuffersrc_ctx[n2], this.samples_frame, 12);
            if (ret < 0) {
                throw new Exception("av_buffersrc_add_frame_flags() error " + ret + ": Error while feeding the filtergraph.");
            }
        }
    }

    @Override
    public synchronized Frame pull() throws Exception {
        if (!this.started) {
            throw new Exception("start() was not called successfully!");
        }
        this.frame.keyFrame = false;
        this.frame.imageWidth = 0;
        this.frame.imageHeight = 0;
        this.frame.imageDepth = 0;
        this.frame.imageChannels = 0;
        this.frame.imageStride = 0;
        this.frame.image = null;
        this.frame.sampleRate = 0;
        this.frame.audioChannels = 0;
        this.frame.samples = null;
        this.frame.opaque = null;
        Frame f2 = null;
        if (f2 == null && this.buffersrc_ctx != null) {
            f2 = this.pullImage();
        }
        if (f2 == null && this.abuffersrc_ctx != null) {
            f2 = this.pullSamples();
        }
        if (f2 == null && this.inframe != null && (this.inframe.image != null && this.buffersrc_ctx == null || this.inframe.samples != null && this.abuffersrc_ctx == null)) {
            f2 = this.inframe;
        }
        this.inframe = null;
        return f2;
    }

    public synchronized Frame pullImage() throws Exception {
        try (PointerScope scope = new PointerScope(new Class[0]);){
            if (!this.started) {
                throw new Exception("start() was not called successfully!");
            }
            avutil.av_frame_unref(this.filt_frame);
            int ret = avfilter.av_buffersink_get_frame(this.buffersink_ctx, this.filt_frame);
            if (ret == avutil.AVERROR_EAGAIN() || ret == avutil.AVERROR_EOF()) {
                Frame frame = null;
                return frame;
            }
            if (ret < 0) {
                throw new Exception("av_buffersink_get_frame(): Error occurred: " + avutil.av_make_error_string(new BytePointer(256L), 256L, ret).getString());
            }
            this.frame.imageWidth = this.filt_frame.width();
            this.frame.imageHeight = this.filt_frame.height();
            this.frame.imageDepth = 8;
            if (this.filt_frame.data(1) == null) {
                this.frame.imageStride = this.filt_frame.linesize(0);
                BytePointer ptr = this.filt_frame.data(0);
                if (ptr != null && !ptr.equals(this.image_ptr[0])) {
                    this.image_ptr[0] = ptr.capacity(this.frame.imageHeight * Math.abs(this.frame.imageStride));
                    this.image_buf[0] = ptr.asBuffer();
                }
                this.frame.image = this.image_buf;
                this.frame.image[0].position(0).limit(this.frame.imageHeight * Math.abs(this.frame.imageStride));
                this.frame.imageChannels = Math.abs(this.frame.imageStride) / this.frame.imageWidth;
                this.frame.opaque = this.filt_frame;
            } else {
                this.frame.imageStride = this.frame.imageWidth;
                int size = avutil.av_image_get_buffer_size(this.filt_frame.format(), this.frame.imageWidth, this.frame.imageHeight, 1);
                if (this.image_ptr2[0] == null || this.image_ptr2[0].capacity() < (long)size) {
                    avutil.av_free(this.image_ptr2[0]);
                    this.image_ptr2[0] = new BytePointer(avutil.av_malloc(size)).capacity(size);
                    this.image_buf2[0] = this.image_ptr2[0].asBuffer();
                }
                this.frame.image = this.image_buf2;
                this.frame.image[0].position(0).limit(size);
                this.frame.imageChannels = (size + this.frame.imageWidth * this.frame.imageHeight - 1) / (this.frame.imageWidth * this.frame.imageHeight);
                ret = avutil.av_image_copy_to_buffer(this.image_ptr2[0].position(0L), (int)this.image_ptr2[0].capacity(), new PointerPointer(this.filt_frame), this.filt_frame.linesize(), this.filt_frame.format(), this.frame.imageWidth, this.frame.imageHeight, 1);
                if (ret < 0) {
                    throw new Exception("av_image_copy_to_buffer() error " + ret + ": Cannot pull image.");
                }
                this.frame.opaque = this.image_ptr2[0];
            }
            this.frame.timestamp = 1000000L * this.filt_frame.pts() * (long)this.time_base.num() / (long)this.time_base.den();
            Frame frame = this.frame;
            return frame;
        }
    }

    public synchronized Frame pullSamples() throws Exception {
        try (PointerScope scope = new PointerScope(new Class[0]);){
            if (!this.started) {
                throw new Exception("start() was not called successfully!");
            }
            avutil.av_frame_unref(this.filt_frame);
            int ret = avfilter.av_buffersink_get_frame(this.abuffersink_ctx, this.filt_frame);
            if (ret == avutil.AVERROR_EAGAIN() || ret == avutil.AVERROR_EOF()) {
                Frame frame = null;
                return frame;
            }
            if (ret < 0) {
                throw new Exception("av_buffersink_get_frame(): Error occurred: " + avutil.av_make_error_string(new BytePointer(256L), 256L, ret).getString());
            }
            int sample_format = this.filt_frame.format();
            int planes = avutil.av_sample_fmt_is_planar(sample_format) != 0 ? this.filt_frame.channels() : 1;
            int data_size = avutil.av_samples_get_buffer_size((IntPointer)null, this.filt_frame.channels(), this.filt_frame.nb_samples(), this.filt_frame.format(), 1) / planes;
            if (this.samples_buf == null || this.samples_buf.length != planes) {
                this.samples_ptr = new BytePointer[planes];
                this.samples_buf = new Buffer[planes];
            }
            this.frame.audioChannels = this.filt_frame.channels();
            this.frame.sampleRate = this.filt_frame.sample_rate();
            this.frame.samples = this.samples_buf;
            this.frame.opaque = this.filt_frame;
            int sample_size = data_size / avutil.av_get_bytes_per_sample(sample_format);
            for (int i2 = 0; i2 < planes; ++i2) {
                BytePointer p2 = this.filt_frame.data(i2);
                if (!p2.equals(this.samples_ptr[i2]) || this.samples_ptr[i2].capacity() < (long)data_size) {
                    this.samples_ptr[i2] = p2.capacity(data_size);
                    ByteBuffer b2 = p2.asBuffer();
                    switch (sample_format) {
                        case 0: 
                        case 5: {
                            this.samples_buf[i2] = b2;
                            break;
                        }
                        case 1: 
                        case 6: {
                            this.samples_buf[i2] = b2.asShortBuffer();
                            break;
                        }
                        case 2: 
                        case 7: {
                            this.samples_buf[i2] = b2.asIntBuffer();
                            break;
                        }
                        case 3: 
                        case 8: {
                            this.samples_buf[i2] = b2.asFloatBuffer();
                            break;
                        }
                        case 4: 
                        case 9: {
                            this.samples_buf[i2] = b2.asDoubleBuffer();
                            break;
                        }
                        default: {
                            assert (false);
                            break;
                        }
                    }
                }
                this.samples_buf[i2].position(0).limit(sample_size);
            }
            this.frame.timestamp = 1000000L * this.filt_frame.pts() * (long)this.atime_base.num() / (long)this.atime_base.den();
            Frame frame = this.frame;
            return frame;
        }
    }

    static {
        try {
            FFmpegFrameFilter.tryLoad();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static class Exception
    extends FrameFilter.Exception {
        public Exception(String message) {
            super(message + " (For more details, make sure FFmpegLogCallback.set() has been called.)");
        }

        public Exception(String message, Throwable cause) {
            super(message, cause);
        }
    }
}

