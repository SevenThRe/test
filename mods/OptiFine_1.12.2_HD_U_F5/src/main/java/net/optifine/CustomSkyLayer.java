/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  amu
 *  anh
 *  bib
 *  buk
 *  bus
 *  bve
 *  cdy
 *  et
 *  vg
 */
package net.optifine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import net.optifine.config.ConnectedParser;
import net.optifine.config.Matches;
import net.optifine.config.RangeListInt;
import net.optifine.render.Blender;
import net.optifine.util.NumUtils;
import net.optifine.util.SmoothFloat;
import net.optifine.util.TextureUtils;

public class CustomSkyLayer {
    public String source = null;
    private int startFadeIn = -1;
    private int endFadeIn = -1;
    private int startFadeOut = -1;
    private int endFadeOut = -1;
    private int blend = 1;
    private boolean rotate = false;
    private float speed = 1.0f;
    private float[] axis = DEFAULT_AXIS;
    private RangeListInt days = null;
    private int daysLoop = 8;
    private boolean weatherClear = true;
    private boolean weatherRain = false;
    private boolean weatherThunder = false;
    public anh[] biomes = null;
    public RangeListInt heights = null;
    private float transition = 1.0f;
    private SmoothFloat smoothPositionBrightness = null;
    public int textureId = -1;
    private amu lastWorld = null;
    public static final float[] DEFAULT_AXIS = new float[]{1.0f, 0.0f, 0.0f};
    private static final String WEATHER_CLEAR = "clear";
    private static final String WEATHER_RAIN = "rain";
    private static final String WEATHER_THUNDER = "thunder";

    public CustomSkyLayer(Properties props, String defSource) {
        ConnectedParser cp = new ConnectedParser("CustomSky");
        this.source = props.getProperty("source", defSource);
        this.startFadeIn = this.parseTime(props.getProperty("startFadeIn"));
        this.endFadeIn = this.parseTime(props.getProperty("endFadeIn"));
        this.startFadeOut = this.parseTime(props.getProperty("startFadeOut"));
        this.endFadeOut = this.parseTime(props.getProperty("endFadeOut"));
        this.blend = Blender.parseBlend(props.getProperty("blend"));
        this.rotate = this.parseBoolean(props.getProperty("rotate"), true);
        this.speed = this.parseFloat(props.getProperty("speed"), 1.0f);
        this.axis = this.parseAxis(props.getProperty("axis"), DEFAULT_AXIS);
        this.days = cp.parseRangeListInt(props.getProperty("days"));
        this.daysLoop = cp.parseInt(props.getProperty("daysLoop"), 8);
        List<String> weatherList = this.parseWeatherList(props.getProperty("weather", WEATHER_CLEAR));
        this.weatherClear = weatherList.contains(WEATHER_CLEAR);
        this.weatherRain = weatherList.contains(WEATHER_RAIN);
        this.weatherThunder = weatherList.contains(WEATHER_THUNDER);
        this.biomes = cp.parseBiomes(props.getProperty("biomes"));
        this.heights = cp.parseRangeListInt(props.getProperty("heights"));
        this.transition = this.parseFloat(props.getProperty("transition"), 1.0f);
    }

    private List<String> parseWeatherList(String str) {
        List<String> weatherAllowedList = Arrays.asList(WEATHER_CLEAR, WEATHER_RAIN, WEATHER_THUNDER);
        ArrayList<String> weatherList = new ArrayList<String>();
        String[] weatherStrs = Config.tokenize(str, " ");
        for (int i = 0; i < weatherStrs.length; ++i) {
            String token = weatherStrs[i];
            if (!weatherAllowedList.contains(token)) {
                Config.warn("Unknown weather: " + token);
                continue;
            }
            weatherList.add(token);
        }
        return weatherList;
    }

    private int parseTime(String str) {
        if (str == null) {
            return -1;
        }
        String[] strs = Config.tokenize(str, ":");
        if (strs.length != 2) {
            Config.warn("Invalid time: " + str);
            return -1;
        }
        String hourStr = strs[0];
        String minStr = strs[1];
        int hour = Config.parseInt(hourStr, -1);
        int min = Config.parseInt(minStr, -1);
        if (hour < 0 || hour > 23 || min < 0 || min > 59) {
            Config.warn("Invalid time: " + str);
            return -1;
        }
        if ((hour -= 6) < 0) {
            hour += 24;
        }
        int time = hour * 1000 + (int)((double)min / 60.0 * 1000.0);
        return time;
    }

    private boolean parseBoolean(String str, boolean defVal) {
        if (str == null) {
            return defVal;
        }
        if (str.toLowerCase().equals("true")) {
            return true;
        }
        if (str.toLowerCase().equals("false")) {
            return false;
        }
        Config.warn("Unknown boolean: " + str);
        return defVal;
    }

    private float parseFloat(String str, float defVal) {
        if (str == null) {
            return defVal;
        }
        float val = Config.parseFloat(str, Float.MIN_VALUE);
        if (val == Float.MIN_VALUE) {
            Config.warn("Invalid value: " + str);
            return defVal;
        }
        return val;
    }

    private float[] parseAxis(String str, float[] defVal) {
        if (str == null) {
            return defVal;
        }
        String[] strs = Config.tokenize(str, " ");
        if (strs.length != 3) {
            Config.warn("Invalid axis: " + str);
            return defVal;
        }
        float[] fs = new float[3];
        for (int i = 0; i < strs.length; ++i) {
            fs[i] = Config.parseFloat(strs[i], Float.MIN_VALUE);
            if (fs[i] == Float.MIN_VALUE) {
                Config.warn("Invalid axis: " + str);
                return defVal;
            }
            if (!(fs[i] < -1.0f) && !(fs[i] > 1.0f)) continue;
            Config.warn("Invalid axis values: " + str);
            return defVal;
        }
        float ax = fs[0];
        float ay = fs[1];
        float az = fs[2];
        if (ax * ax + ay * ay + az * az < 1.0E-5f) {
            Config.warn("Invalid axis values: " + str);
            return defVal;
        }
        float[] as = new float[]{az, ay, -ax};
        return as;
    }

    public boolean isValid(String path) {
        int timeOff;
        int timeFadeOut;
        int timeOn;
        int timeSum;
        if (this.source == null) {
            Config.warn("No source texture: " + path);
            return false;
        }
        this.source = TextureUtils.fixResourcePath(this.source, TextureUtils.getBasePath(path));
        if (this.startFadeIn < 0 || this.endFadeIn < 0 || this.endFadeOut < 0) {
            Config.warn("Invalid times, required are: startFadeIn, endFadeIn and endFadeOut.");
            return false;
        }
        int timeFadeIn = this.normalizeTime(this.endFadeIn - this.startFadeIn);
        if (this.startFadeOut < 0) {
            this.startFadeOut = this.normalizeTime(this.endFadeOut - timeFadeIn);
            if (this.timeBetween(this.startFadeOut, this.startFadeIn, this.endFadeIn)) {
                this.startFadeOut = this.endFadeIn;
            }
        }
        if ((timeSum = timeFadeIn + (timeOn = this.normalizeTime(this.startFadeOut - this.endFadeIn)) + (timeFadeOut = this.normalizeTime(this.endFadeOut - this.startFadeOut)) + (timeOff = this.normalizeTime(this.startFadeIn - this.endFadeOut))) != 24000) {
            Config.warn("Invalid fadeIn/fadeOut times, sum is not 24h: " + timeSum);
            return false;
        }
        if (this.speed < 0.0f) {
            Config.warn("Invalid speed: " + this.speed);
            return false;
        }
        if (this.daysLoop <= 0) {
            Config.warn("Invalid daysLoop: " + this.daysLoop);
            return false;
        }
        return true;
    }

    private int normalizeTime(int timeMc) {
        while (timeMc >= 24000) {
            timeMc -= 24000;
        }
        while (timeMc < 0) {
            timeMc += 24000;
        }
        return timeMc;
    }

    public void render(amu world, int timeOfDay, float celestialAngle, float rainStrength, float thunderStrength) {
        float positionBrightness = this.getPositionBrightness(world);
        float weatherBrightness = this.getWeatherBrightness(rainStrength, thunderStrength);
        float fadeBrightness = this.getFadeBrightness(timeOfDay);
        float brightness = positionBrightness * weatherBrightness * fadeBrightness;
        if ((brightness = Config.limit(brightness, 0.0f, 1.0f)) < 1.0E-4f) {
            return;
        }
        bus.i((int)this.textureId);
        Blender.setupBlend(this.blend, brightness);
        bus.G();
        if (this.rotate) {
            float angleDayStart = 0.0f;
            if (this.speed != (float)Math.round(this.speed)) {
                long worldDay = (world.S() + 18000L) / 24000L;
                double anglePerDay = this.speed % 1.0f;
                double angleDayNow = (double)worldDay * anglePerDay;
                angleDayStart = (float)(angleDayNow % 1.0);
            }
            bus.b((float)(360.0f * (angleDayStart + celestialAngle * this.speed)), (float)this.axis[0], (float)this.axis[1], (float)this.axis[2]);
        }
        bve tess = bve.a();
        bus.b((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        bus.b((float)-90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        this.renderSide(tess, 4);
        bus.G();
        bus.b((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        this.renderSide(tess, 1);
        bus.H();
        bus.G();
        bus.b((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        this.renderSide(tess, 0);
        bus.H();
        bus.b((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        this.renderSide(tess, 5);
        bus.b((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        this.renderSide(tess, 2);
        bus.b((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        this.renderSide(tess, 3);
        bus.H();
    }

    private float getPositionBrightness(amu world) {
        if (this.biomes == null && this.heights == null) {
            return 1.0f;
        }
        float positionBrightness = this.getPositionBrightnessRaw(world);
        if (this.smoothPositionBrightness == null) {
            this.smoothPositionBrightness = new SmoothFloat(positionBrightness, this.transition);
        }
        positionBrightness = this.smoothPositionBrightness.getSmoothValue(positionBrightness);
        return positionBrightness;
    }

    private float getPositionBrightnessRaw(amu world) {
        vg renderViewEntity = bib.z().aa();
        if (renderViewEntity == null) {
            return 0.0f;
        }
        et pos = renderViewEntity.c();
        if (this.biomes != null) {
            anh biome = world.b(pos);
            if (biome == null) {
                return 0.0f;
            }
            if (!Matches.biome(biome, this.biomes)) {
                return 0.0f;
            }
        }
        if (this.heights != null && !this.heights.isInRange(pos.q())) {
            return 0.0f;
        }
        return 1.0f;
    }

    private float getWeatherBrightness(float rainStrength, float thunderStrength) {
        float clearBrightness = 1.0f - rainStrength;
        float rainBrightness = rainStrength - thunderStrength;
        float thunderBrightness = thunderStrength;
        float weatherBrightness = 0.0f;
        if (this.weatherClear) {
            weatherBrightness += clearBrightness;
        }
        if (this.weatherRain) {
            weatherBrightness += rainBrightness;
        }
        if (this.weatherThunder) {
            weatherBrightness += thunderBrightness;
        }
        weatherBrightness = NumUtils.limit(weatherBrightness, 0.0f, 1.0f);
        return weatherBrightness;
    }

    private float getFadeBrightness(int timeOfDay) {
        if (this.timeBetween(timeOfDay, this.startFadeIn, this.endFadeIn)) {
            int timeFadeIn = this.normalizeTime(this.endFadeIn - this.startFadeIn);
            int timeDiff = this.normalizeTime(timeOfDay - this.startFadeIn);
            return (float)timeDiff / (float)timeFadeIn;
        }
        if (this.timeBetween(timeOfDay, this.endFadeIn, this.startFadeOut)) {
            return 1.0f;
        }
        if (this.timeBetween(timeOfDay, this.startFadeOut, this.endFadeOut)) {
            int timeFadeOut = this.normalizeTime(this.endFadeOut - this.startFadeOut);
            int timeDiff = this.normalizeTime(timeOfDay - this.startFadeOut);
            return 1.0f - (float)timeDiff / (float)timeFadeOut;
        }
        return 0.0f;
    }

    private void renderSide(bve tess, int side) {
        buk wr = tess.c();
        double tx = (double)(side % 3) / 3.0;
        double ty = (double)(side / 3) / 2.0;
        wr.a(7, cdy.g);
        wr.b(-100.0, -100.0, -100.0).a(tx, ty).d();
        wr.b(-100.0, -100.0, 100.0).a(tx, ty + 0.5).d();
        wr.b(100.0, -100.0, 100.0).a(tx + 0.3333333333333333, ty + 0.5).d();
        wr.b(100.0, -100.0, -100.0).a(tx + 0.3333333333333333, ty).d();
        tess.b();
    }

    public boolean isActive(amu world, int timeOfDay) {
        if (world != this.lastWorld) {
            this.lastWorld = world;
            this.smoothPositionBrightness = null;
        }
        if (this.timeBetween(timeOfDay, this.endFadeOut, this.startFadeIn)) {
            return false;
        }
        if (this.days != null) {
            long timeShift;
            long time = world.S();
            for (timeShift = time - (long)this.startFadeIn; timeShift < 0L; timeShift += (long)(24000 * this.daysLoop)) {
            }
            int day = (int)(timeShift / 24000L);
            int dayOfLoop = day % this.daysLoop;
            if (!this.days.isInRange(dayOfLoop)) {
                return false;
            }
        }
        return true;
    }

    private boolean timeBetween(int timeOfDay, int timeStart, int timeEnd) {
        if (timeStart <= timeEnd) {
            return timeOfDay >= timeStart && timeOfDay <= timeEnd;
        }
        return timeOfDay >= timeStart || timeOfDay <= timeEnd;
    }

    public String toString() {
        return "" + this.source + ", " + this.startFadeIn + "-" + this.endFadeIn + " " + this.startFadeOut + "-" + this.endFadeOut;
    }
}

