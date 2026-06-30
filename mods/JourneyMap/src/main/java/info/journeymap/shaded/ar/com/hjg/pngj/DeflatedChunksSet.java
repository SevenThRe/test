/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.ar.com.hjg.pngj;

import info.journeymap.shaded.ar.com.hjg.pngj.DeflatedChunkReader;
import info.journeymap.shaded.ar.com.hjg.pngj.PngjException;
import info.journeymap.shaded.ar.com.hjg.pngj.PngjInputException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class DeflatedChunksSet {
    protected byte[] row;
    private int rowfilled;
    private int rowlen;
    private int rown;
    State state = State.WAITING_FOR_INPUT;
    private Inflater inf;
    private final boolean infOwn;
    private DeflatedChunkReader curChunk;
    private boolean callbackMode = true;
    private long nBytesIn = 0L;
    private long nBytesOut = 0L;
    public final String chunkid;

    public DeflatedChunksSet(String chunkid, int initialRowLen, int maxRowLen, Inflater inflater, byte[] buffer) {
        this.chunkid = chunkid;
        this.rowlen = initialRowLen;
        if (initialRowLen < 1 || maxRowLen < initialRowLen) {
            throw new PngjException("bad inital row len " + initialRowLen);
        }
        if (inflater != null) {
            this.inf = inflater;
            this.infOwn = false;
        } else {
            this.inf = new Inflater();
            this.infOwn = true;
        }
        this.row = buffer != null && buffer.length >= initialRowLen ? buffer : new byte[maxRowLen];
        this.rown = -1;
        this.state = State.WAITING_FOR_INPUT;
        try {
            this.prepareForNextRow(initialRowLen);
        }
        catch (RuntimeException e) {
            this.close();
            throw e;
        }
    }

    public DeflatedChunksSet(String chunkid, int initialRowLen, int maxRowLen) {
        this(chunkid, initialRowLen, maxRowLen, null, null);
    }

    protected void appendNewChunk(DeflatedChunkReader cr) {
        if (!this.chunkid.equals(cr.getChunkRaw().id)) {
            throw new PngjInputException("Bad chunk inside IdatSet, id:" + cr.getChunkRaw().id + ", expected:" + this.chunkid);
        }
        this.curChunk = cr;
    }

    protected void processBytes(byte[] buf, int off, int len) {
        this.nBytesIn += (long)len;
        if (len < 1 || this.state.isDone()) {
            return;
        }
        if (this.state == State.ROW_READY) {
            throw new PngjInputException("this should only be called if waitingForMoreInput");
        }
        if (this.inf.needsDictionary() || !this.inf.needsInput()) {
            throw new RuntimeException("should not happen");
        }
        this.inf.setInput(buf, off, len);
        if (this.isCallbackMode()) {
            while (this.inflateData()) {
                int nextRowLen = this.processRowCallback();
                this.prepareForNextRow(nextRowLen);
                if (!this.isDone()) continue;
                this.processDoneCallback();
            }
        } else {
            this.inflateData();
        }
    }

    private boolean inflateData() {
        try {
            if (this.state == State.ROW_READY) {
                throw new PngjException("invalid state");
            }
            if (this.state.isDone()) {
                return false;
            }
            int ninflated = 0;
            if (this.row == null || this.row.length < this.rowlen) {
                this.row = new byte[this.rowlen];
            }
            if (this.rowfilled < this.rowlen && !this.inf.finished()) {
                try {
                    ninflated = this.inf.inflate(this.row, this.rowfilled, this.rowlen - this.rowfilled);
                }
                catch (DataFormatException e) {
                    throw new PngjInputException("error decompressing zlib stream ", e);
                }
                this.rowfilled += ninflated;
                this.nBytesOut += (long)ninflated;
            }
            State nextstate = null;
            nextstate = this.rowfilled == this.rowlen ? State.ROW_READY : (!this.inf.finished() ? State.WAITING_FOR_INPUT : (this.rowfilled > 0 ? State.ROW_READY : State.WORK_DONE));
            this.state = nextstate;
            if (this.state == State.ROW_READY) {
                this.preProcessRow();
                return true;
            }
        }
        catch (RuntimeException e) {
            this.close();
            throw e;
        }
        return false;
    }

    protected void preProcessRow() {
    }

    protected int processRowCallback() {
        throw new PngjInputException("not implemented");
    }

    protected void processDoneCallback() {
    }

    public byte[] getInflatedRow() {
        return this.row;
    }

    public void prepareForNextRow(int len) {
        this.rowfilled = 0;
        ++this.rown;
        if (len < 1) {
            this.rowlen = 0;
            this.done();
        } else if (this.inf.finished()) {
            this.rowlen = 0;
            this.done();
        } else {
            this.state = State.WAITING_FOR_INPUT;
            this.rowlen = len;
            if (!this.callbackMode) {
                this.inflateData();
            }
        }
    }

    public boolean isWaitingForMoreInput() {
        return this.state == State.WAITING_FOR_INPUT;
    }

    public boolean isRowReady() {
        return this.state == State.ROW_READY;
    }

    public boolean isDone() {
        return this.state.isDone();
    }

    public boolean isTerminated() {
        return this.state.isTerminated();
    }

    public boolean ackNextChunkId(String id) {
        if (this.state.isTerminated()) {
            return false;
        }
        if (id.equals(this.chunkid)) {
            return true;
        }
        if (!this.allowOtherChunksInBetween(id)) {
            if (this.state.isDone()) {
                if (!this.isTerminated()) {
                    this.terminate();
                }
                return false;
            }
            throw new PngjInputException("Unexpected chunk " + id + " while " + this.chunkid + " set is not done");
        }
        return true;
    }

    protected void terminate() {
        this.close();
    }

    public void close() {
        try {
            if (!this.state.isTerminated()) {
                this.state = State.TERMINATED;
            }
            if (this.infOwn && this.inf != null) {
                this.inf.end();
                this.inf = null;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void done() {
        if (!this.isDone()) {
            this.state = State.WORK_DONE;
        }
    }

    public int getRowLen() {
        return this.rowlen;
    }

    public int getRowFilled() {
        return this.rowfilled;
    }

    public int getRown() {
        return this.rown;
    }

    public boolean allowOtherChunksInBetween(String id) {
        return false;
    }

    public boolean isCallbackMode() {
        return this.callbackMode;
    }

    public void setCallbackMode(boolean callbackMode) {
        this.callbackMode = callbackMode;
    }

    public long getBytesIn() {
        return this.nBytesIn;
    }

    public long getBytesOut() {
        return this.nBytesOut;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("idatSet : " + this.curChunk.getChunkRaw().id + " state=" + (Object)((Object)this.state) + " rows=" + this.rown + " bytes=" + this.nBytesIn + "/" + this.nBytesOut);
        return sb.toString();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static enum State {
        WAITING_FOR_INPUT,
        ROW_READY,
        WORK_DONE,
        TERMINATED;


        public boolean isDone() {
            return this == WORK_DONE || this == TERMINATED;
        }

        public boolean isTerminated() {
            return this == TERMINATED;
        }
    }
}

