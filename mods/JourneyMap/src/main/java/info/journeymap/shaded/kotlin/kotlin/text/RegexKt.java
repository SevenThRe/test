/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.text;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.collections.CollectionsKt;
import info.journeymap.shaded.kotlin.kotlin.jvm.functions.Function1;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.kotlin.kotlin.ranges.IntRange;
import info.journeymap.shaded.kotlin.kotlin.ranges.RangesKt;
import info.journeymap.shaded.kotlin.kotlin.text.FlagEnum;
import info.journeymap.shaded.kotlin.kotlin.text.MatchResult;
import info.journeymap.shaded.kotlin.kotlin.text.MatcherMatchResult;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.regex.Matcher;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=2, d1={"\u0000>\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0014\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0082\b\u001a\u001e\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\t2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0002\u001a\u0016\u0010\r\u001a\u0004\u0018\u00010\b*\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0002\u001a\f\u0010\u000e\u001a\u00020\u000f*\u00020\u0010H\u0002\u001a\u0014\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0006H\u0002\u001a\u0012\u0010\u0012\u001a\u00020\u0006*\b\u0012\u0004\u0012\u00020\u00030\u0013H\u0002\u00a8\u0006\u0014"}, d2={"fromInt", "", "T", "Linfo/journeymap/shaded/kotlin/kotlin/text/FlagEnum;", "", "value", "", "findNext", "Linfo/journeymap/shaded/kotlin/kotlin/text/MatchResult;", "Ljava/util/regex/Matcher;", "from", "input", "", "matchEntire", "range", "Linfo/journeymap/shaded/kotlin/kotlin/ranges/IntRange;", "Ljava/util/regex/MatchResult;", "groupIndex", "toInt", "", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
public final class RegexKt {
    /*
     * WARNING - void declaration
     */
    private static final int toInt(@NotNull Iterable<? extends FlagEnum> $this$toInt) {
        void $this$fold$iv;
        Iterable<? extends FlagEnum> iterable = $this$toInt;
        int initial$iv = 0;
        boolean $i$f$fold = false;
        int accumulator$iv = initial$iv;
        for (Object element$iv : $this$fold$iv) {
            void option;
            FlagEnum flagEnum = (FlagEnum)element$iv;
            int value = accumulator$iv;
            boolean bl = false;
            accumulator$iv = value | option.getValue();
        }
        return accumulator$iv;
    }

    private static final /* synthetic */ <T extends Enum<T>> Set<T> fromInt(int value) {
        int $i$f$fromInt = 0;
        Intrinsics.reifiedOperationMarker(4, "T");
        EnumSet<Enum> enumSet = EnumSet.allOf(Enum.class);
        boolean bl = false;
        boolean bl2 = false;
        EnumSet<Enum> $this$apply = enumSet;
        boolean bl3 = false;
        CollectionsKt.retainAll((Iterable)$this$apply, (Function1)new Function1<T, Boolean>(value){
            final /* synthetic */ int $value$inlined;
            {
                this.$value$inlined = n;
                super(1);
            }

            public final boolean invoke(T it) {
                return (this.$value$inlined & ((FlagEnum)it).getMask()) == ((FlagEnum)it).getValue();
            }
        });
        Set set = Collections.unmodifiableSet((Set)enumSet);
        Intrinsics.checkExpressionValueIsNotNull(set, "Collections.unmodifiable\u2026mask == it.value }\n    })");
        return set;
    }

    private static final MatchResult findNext(@NotNull Matcher $this$findNext, int from, CharSequence input) {
        return !$this$findNext.find(from) ? null : (MatchResult)new MatcherMatchResult($this$findNext, input);
    }

    private static final MatchResult matchEntire(@NotNull Matcher $this$matchEntire, CharSequence input) {
        return !$this$matchEntire.matches() ? null : (MatchResult)new MatcherMatchResult($this$matchEntire, input);
    }

    private static final IntRange range(@NotNull java.util.regex.MatchResult $this$range) {
        return RangesKt.until($this$range.start(), $this$range.end());
    }

    private static final IntRange range(@NotNull java.util.regex.MatchResult $this$range, int groupIndex) {
        return RangesKt.until($this$range.start(groupIndex), $this$range.end(groupIndex));
    }

    public static final /* synthetic */ Set access$fromInt(int value) {
        return RegexKt.fromInt(value);
    }

    public static final /* synthetic */ MatchResult access$findNext(Matcher $this$access_u24findNext, int from, CharSequence input) {
        return RegexKt.findNext($this$access_u24findNext, from, input);
    }

    public static final /* synthetic */ MatchResult access$matchEntire(Matcher $this$access_u24matchEntire, CharSequence input) {
        return RegexKt.matchEntire($this$access_u24matchEntire, input);
    }

    public static final /* synthetic */ int access$toInt(Iterable $this$access_u24toInt) {
        return RegexKt.toInt($this$access_u24toInt);
    }

    public static final /* synthetic */ IntRange access$range(java.util.regex.MatchResult $this$access_u24range) {
        return RegexKt.range($this$access_u24range);
    }

    public static final /* synthetic */ IntRange access$range(java.util.regex.MatchResult $this$access_u24range, int groupIndex) {
        return RegexKt.range($this$access_u24range, groupIndex);
    }
}

