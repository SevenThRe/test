/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bytedeco.opencv.opencv_core.CvMat
 *  org.bytedeco.opencv.opencv_core.IplImage
 */
package org.bytedeco.javacv;

import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.IplImage;

public class Blobs {
    static int BLOBROWCOUNT = 3500;
    static int BLOBCOLCOUNT = 2700;
    static int BLOBTOTALCOUNT = (BLOBROWCOUNT + BLOBCOLCOUNT) * 5;
    public static int BLOBLABEL = 0;
    public static int BLOBPARENT = 1;
    public static int BLOBCOLOR = 2;
    public static int BLOBAREA = 3;
    public static int BLOBPERIMETER = 4;
    public static int BLOBSUMX = 5;
    public static int BLOBSUMY = 6;
    public static int BLOBSUMXX = 7;
    public static int BLOBSUMYY = 8;
    public static int BLOBSUMXY = 9;
    public static int BLOBMINX = 10;
    public static int BLOBMAXX = 11;
    public static int BLOBMINY = 12;
    public static int BLOBMAXY = 13;
    public static int BLOBDATACOUNT = 14;
    public static int[][] LabelMat = new int[BLOBROWCOUNT][BLOBCOLCOUNT];
    public static double[][] RegionData = new double[BLOBTOTALCOUNT][BLOBDATACOUNT];
    public static int MaxLabel;
    public int LabelA;
    public int LabelB;
    public int LabelC;
    public int LabelD;
    public int ColorA;
    public int ColorB;
    public int ColorC;
    public int ColorD;
    public int jrow;
    public int jcol;
    public static int[] SubsumedLabel;
    public static int[] CondensationMap;
    static double iField;
    static double jField;
    static double[] iProperty;
    static double[] jProperty;

    public void PrintRegionData() {
        this.PrintRegionData(0, MaxLabel);
    }

    public void PrintRegionData(int Label0, int Label1) {
        if (Label0 < 0) {
            Label0 = 0;
        }
        if (Label1 > MaxLabel) {
            Label1 = MaxLabel;
        }
        if (Label1 < Label0) {
            return;
        }
        for (int Label = Label0; Label <= Label1; ++Label) {
            double[] Property2 = RegionData[Label];
            int ThisLabel = (int)Property2[BLOBLABEL];
            int ThisParent = (int)Property2[BLOBPARENT];
            int ThisColor = (int)Property2[BLOBCOLOR];
            double ThisArea = Property2[BLOBAREA];
            double ThisPerimeter = Property2[BLOBPERIMETER];
            double ThisSumX = Property2[BLOBSUMX];
            double ThisSumY = Property2[BLOBSUMY];
            double ThisSumXX = Property2[BLOBSUMXX];
            double ThisSumYY = Property2[BLOBSUMYY];
            double ThisSumXY = Property2[BLOBSUMXY];
            int ThisMinX = (int)Property2[BLOBMINX];
            int ThisMaxX = (int)Property2[BLOBMAXX];
            int ThisMinY = (int)Property2[BLOBMINY];
            int ThisMaxY = (int)Property2[BLOBMAXY];
            String Str1 = " " + Label + ": L[" + ThisLabel + "] P[" + ThisParent + "] C[" + ThisColor + "]";
            String Str2 = " AP[" + ThisArea + ", " + ThisPerimeter + "]";
            String Str3 = " M1[" + ThisSumX + ", " + ThisSumY + "] M2[" + ThisSumXX + ", " + ThisSumYY + ", " + ThisSumXY + "]";
            String Str4 = " MINMAX[" + ThisMinX + ", " + ThisMaxX + ", " + ThisMinY + ", " + ThisMaxY + "]";
            String Str = Str1 + Str2 + Str3 + Str4;
            System.out.println(Str);
        }
        System.out.println();
    }

    public static int NextRegion(int Parent, int Color2, double MinArea, double MaxArea, int Label) {
        int i2;
        double DParent = Parent;
        double DColor = Color2;
        if (DColor > 0.0) {
            DColor = 1.0;
        }
        for (i2 = Label; i2 <= MaxLabel; ++i2) {
            double[] Region2 = RegionData[i2];
            double ThisParent = Region2[BLOBPARENT];
            double ThisColor = Region2[BLOBCOLOR];
            if (!(DParent >= 0.0 && DParent != ThisParent || DColor >= 0.0 && DColor != ThisColor || Region2[BLOBAREA] < MinArea) && !(Region2[BLOBAREA] > MaxArea)) break;
        }
        if (i2 > MaxLabel) {
            i2 = -1;
        }
        return i2;
    }

    public static int PriorRegion(int Parent, int Color2, double MinArea, double MaxArea, int Label) {
        int i2;
        double DParent = Parent;
        double DColor = Color2;
        if (DColor > 0.0) {
            DColor = 1.0;
        }
        for (i2 = Label; i2 >= 0; --i2) {
            double[] Region2 = RegionData[i2];
            double ThisParent = Region2[BLOBPARENT];
            double ThisColor = Region2[BLOBCOLOR];
            if (!(DParent >= 0.0 && DParent != ThisParent || DColor >= 0.0 && DColor != ThisColor || Region2[BLOBAREA] < MinArea) && !(Region2[BLOBAREA] > MaxArea)) break;
        }
        if (i2 < 0) {
            i2 = -1;
        }
        return i2;
    }

    public void ResetRegion(int Label) {
        double[] RegionD = RegionData[Label];
        RegionD[Blobs.BLOBMAXY] = 0.0;
        RegionD[Blobs.BLOBMINY] = 0.0;
        RegionD[Blobs.BLOBMAXX] = 0.0;
        RegionD[Blobs.BLOBMINX] = 0.0;
        RegionD[Blobs.BLOBSUMXY] = 0.0;
        RegionD[Blobs.BLOBSUMYY] = 0.0;
        RegionD[Blobs.BLOBSUMXX] = 0.0;
        RegionD[Blobs.BLOBSUMY] = 0.0;
        RegionD[Blobs.BLOBSUMX] = 0.0;
        RegionD[Blobs.BLOBPERIMETER] = 0.0;
        RegionD[Blobs.BLOBAREA] = 0.0;
        RegionD[Blobs.BLOBCOLOR] = 0.0;
        RegionD[Blobs.BLOBPARENT] = 0.0;
        RegionD[Blobs.BLOBLABEL] = 0.0;
        System.arraycopy(RegionD, 0, RegionData[Label], 0, BLOBDATACOUNT);
    }

    public void OldRegion(int NewLabelD, int Label1, int Label2) {
        int DeltaPerimeter = 0;
        if (Label1 >= 0 && Label1 != NewLabelD) {
            ++DeltaPerimeter;
            double[] Region1 = RegionData[Label1];
            int n2 = BLOBPERIMETER;
            Region1[n2] = Region1[n2] + 1.0;
            System.arraycopy(Region1, 0, RegionData[Label1], 0, BLOBDATACOUNT);
        }
        if (Label2 >= 0 && Label2 != NewLabelD) {
            ++DeltaPerimeter;
            double[] Region2 = RegionData[Label2];
            int n3 = BLOBPERIMETER;
            Region2[n3] = Region2[n3] + 1.0;
            System.arraycopy(Region2, 0, RegionData[Label2], 0, BLOBDATACOUNT);
        }
        this.LabelD = NewLabelD;
        double[] RegionD = RegionData[this.LabelD];
        RegionD[Blobs.BLOBLABEL] = this.LabelD;
        int n4 = BLOBPARENT;
        RegionD[n4] = RegionD[n4] + 0.0;
        int n5 = BLOBCOLOR;
        RegionD[n5] = RegionD[n5] + 0.0;
        int n6 = BLOBAREA;
        RegionD[n6] = RegionD[n6] + 1.0;
        int n7 = BLOBPERIMETER;
        RegionD[n7] = RegionD[n7] + (double)DeltaPerimeter;
        int n8 = BLOBSUMX;
        RegionD[n8] = RegionD[n8] + (double)this.jcol;
        int n9 = BLOBSUMY;
        RegionD[n9] = RegionD[n9] + (double)this.jrow;
        int n10 = BLOBSUMXX;
        RegionD[n10] = RegionD[n10] + (double)(this.jcol * this.jcol);
        int n11 = BLOBSUMYY;
        RegionD[n11] = RegionD[n11] + (double)(this.jrow * this.jrow);
        int n12 = BLOBSUMXY;
        RegionD[n12] = RegionD[n12] + (double)(this.jcol * this.jrow);
        RegionD[Blobs.BLOBMINX] = Math.min(RegionD[BLOBMINX], (double)this.jcol);
        RegionD[Blobs.BLOBMAXX] = Math.max(RegionD[BLOBMAXX], (double)this.jcol);
        RegionD[Blobs.BLOBMINY] = Math.min(RegionD[BLOBMINY], (double)this.jrow);
        RegionD[Blobs.BLOBMAXY] = Math.max(RegionD[BLOBMAXY], (double)this.jrow);
        System.arraycopy(RegionD, 0, RegionData[this.LabelD], 0, BLOBDATACOUNT);
    }

    public void NewRegion(int ParentLabel) {
        this.LabelD = ++MaxLabel;
        double[] RegionD = RegionData[this.LabelD];
        RegionD[Blobs.BLOBLABEL] = this.LabelD;
        RegionD[Blobs.BLOBPARENT] = ParentLabel;
        RegionD[Blobs.BLOBCOLOR] = this.ColorD;
        RegionD[Blobs.BLOBAREA] = 1.0;
        RegionD[Blobs.BLOBPERIMETER] = 2.0;
        RegionD[Blobs.BLOBSUMX] = this.jcol;
        RegionD[Blobs.BLOBSUMY] = this.jrow;
        RegionD[Blobs.BLOBSUMXX] = this.jcol * this.jcol;
        RegionD[Blobs.BLOBSUMYY] = this.jrow * this.jrow;
        RegionD[Blobs.BLOBSUMXY] = this.jcol * this.jrow;
        RegionD[Blobs.BLOBMINX] = this.jcol;
        RegionD[Blobs.BLOBMAXX] = this.jcol;
        RegionD[Blobs.BLOBMINY] = this.jrow;
        RegionD[Blobs.BLOBMAXY] = this.jrow;
        System.arraycopy(RegionD, 0, RegionData[this.LabelD], 0, BLOBDATACOUNT);
        Blobs.SubsumedLabel[this.LabelD] = -1;
        double[] RegionB = RegionData[this.LabelB];
        int n2 = BLOBPERIMETER;
        RegionB[n2] = RegionB[n2] + 1.0;
        System.arraycopy(RegionB, 0, RegionData[this.LabelB], 0, BLOBDATACOUNT);
        double[] RegionC = RegionData[this.LabelC];
        int n3 = BLOBPERIMETER;
        RegionC[n3] = RegionC[n3] + 1.0;
        System.arraycopy(RegionC, 0, RegionData[this.LabelC], 0, BLOBDATACOUNT);
    }

    public void Subsume(int GoodLabel, int BadLabel, int PSign) {
        this.LabelD = GoodLabel;
        double[] GoodRegion = RegionData[GoodLabel];
        double[] BadRegion = RegionData[BadLabel];
        GoodRegion[Blobs.BLOBLABEL] = GoodRegion[BLOBLABEL];
        GoodRegion[Blobs.BLOBPARENT] = GoodRegion[BLOBPARENT];
        GoodRegion[Blobs.BLOBCOLOR] = GoodRegion[BLOBCOLOR];
        int n2 = BLOBAREA;
        GoodRegion[n2] = GoodRegion[n2] + BadRegion[BLOBAREA];
        int n3 = BLOBPERIMETER;
        GoodRegion[n3] = GoodRegion[n3] + BadRegion[BLOBPERIMETER] * (double)PSign;
        int n4 = BLOBSUMX;
        GoodRegion[n4] = GoodRegion[n4] + BadRegion[BLOBSUMX];
        int n5 = BLOBSUMY;
        GoodRegion[n5] = GoodRegion[n5] + BadRegion[BLOBSUMY];
        int n6 = BLOBSUMXX;
        GoodRegion[n6] = GoodRegion[n6] + BadRegion[BLOBSUMXX];
        int n7 = BLOBSUMYY;
        GoodRegion[n7] = GoodRegion[n7] + BadRegion[BLOBSUMYY];
        int n8 = BLOBSUMXY;
        GoodRegion[n8] = GoodRegion[n8] + BadRegion[BLOBSUMXY];
        GoodRegion[Blobs.BLOBMINX] = Math.min(GoodRegion[BLOBMINX], BadRegion[BLOBMINX]);
        GoodRegion[Blobs.BLOBMAXX] = Math.max(GoodRegion[BLOBMAXX], BadRegion[BLOBMAXX]);
        GoodRegion[Blobs.BLOBMINY] = Math.min(GoodRegion[BLOBMINY], BadRegion[BLOBMINY]);
        GoodRegion[Blobs.BLOBMAXY] = Math.max(GoodRegion[BLOBMAXY], BadRegion[BLOBMAXY]);
        System.arraycopy(GoodRegion, 0, RegionData[GoodLabel], 0, BLOBDATACOUNT);
    }

    public static int SubsumptionChain(int x2) {
        return Blobs.SubsumptionChain(x2, 0);
    }

    public static int SubsumptionChain(int x2, int Print) {
        String Str = "";
        if (Print > 0) {
            Str = "Subsumption chain for " + x2 + ": ";
        }
        int Lastx = x2;
        while (x2 > -1) {
            Lastx = x2;
            if (Print > 0) {
                Str = Str + " " + x2;
            }
            if (x2 == 0) break;
            x2 = SubsumedLabel[x2];
        }
        if (Print > 0) {
            System.out.println(Str);
        }
        return Lastx;
    }

    public int BlobAnalysis(IplImage Src, int Col0, int Row0, int Cols, int Rows, int Border2, int MinArea) {
        int Label;
        int NewParent;
        int NewLabel;
        int OldParent;
        int OldLabel;
        int Label2;
        CvMat SrcMat = Src.asCvMat();
        int SrcCols = SrcMat.cols();
        int SrcRows = SrcMat.rows();
        if (Col0 < 0) {
            Col0 = 0;
        }
        if (Row0 < 0) {
            Row0 = 0;
        }
        if (Cols < 0) {
            Cols = SrcCols;
        }
        if (Rows < 0) {
            Rows = SrcRows;
        }
        if (Col0 + Cols > SrcCols) {
            Cols = SrcCols - Col0;
        }
        if (Row0 + Rows > SrcRows) {
            Rows = SrcRows - Row0;
        }
        if (Cols > BLOBCOLCOUNT || Rows > BLOBROWCOUNT) {
            System.out.println("Error in Class Blobs: Image too large: Edit Blobs.java");
            System.exit(666);
            return 0;
        }
        boolean FillLabel = false;
        int FillColor = 0;
        if (Border2 > 0) {
            FillColor = 1;
        }
        this.LabelD = 0;
        this.LabelC = 0;
        this.LabelB = 0;
        this.LabelA = 0;
        this.ColorC = this.ColorD = FillColor;
        this.ColorB = this.ColorD;
        this.ColorA = this.ColorD;
        for (int k2 = 0; k2 < BLOBTOTALCOUNT; ++k2) {
            Blobs.SubsumedLabel[k2] = -1;
        }
        MaxLabel = 0;
        double[] BorderRegion = RegionData[0];
        BorderRegion[Blobs.BLOBLABEL] = 0.0;
        BorderRegion[Blobs.BLOBPARENT] = -1.0;
        BorderRegion[Blobs.BLOBAREA] = Rows + Cols + 4;
        BorderRegion[Blobs.BLOBCOLOR] = FillColor;
        BorderRegion[Blobs.BLOBSUMX] = 0.5 * ((2.0 + (double)Cols) * ((double)Cols - 1.0)) - (double)Rows - 1.0;
        BorderRegion[Blobs.BLOBSUMY] = 0.5 * ((2.0 + (double)Rows) * ((double)Rows - 1.0)) - (double)Cols - 1.0;
        BorderRegion[Blobs.BLOBMINX] = -1.0;
        BorderRegion[Blobs.BLOBMINY] = -1.0;
        BorderRegion[Blobs.BLOBMAXX] = (double)Cols + 1.0;
        BorderRegion[Blobs.BLOBMAXY] = (double)Rows + 1.0;
        System.arraycopy(BorderRegion, 0, RegionData[0], 0, BLOBDATACOUNT);
        for (int irow = Row0; irow < Row0 + Rows; ++irow) {
            this.jrow = irow - Row0;
            for (int icol = Col0; icol < Col0 + Cols; ++icol) {
                this.jcol = icol - Col0;
                this.ColorB = this.ColorC = FillColor;
                this.ColorA = this.ColorC;
                this.LabelD = 0;
                this.LabelC = 0;
                this.LabelB = 0;
                this.LabelA = 0;
                this.ColorD = (int)SrcMat.get(this.jrow, this.jcol);
                if (this.jrow == 0 || this.jcol == 0) {
                    if (this.jcol > 0) {
                        this.ColorC = (int)SrcMat.get(this.jrow, this.jcol - 1);
                        this.LabelC = LabelMat[this.jrow][this.jcol - 1];
                    }
                    if (this.jrow > 0) {
                        this.ColorB = (int)SrcMat.get(this.jrow - 1, this.jcol);
                        this.LabelB = LabelMat[this.jrow - 1][this.jcol];
                    }
                } else {
                    this.ColorA = (int)SrcMat.get(this.jrow - 1, this.jcol - 1);
                    if (this.ColorA > 0) {
                        this.ColorA = 1;
                    }
                    this.ColorB = (int)SrcMat.get(this.jrow - 1, this.jcol);
                    if (this.ColorB > 0) {
                        this.ColorB = 1;
                    }
                    this.ColorC = (int)SrcMat.get(this.jrow, this.jcol - 1);
                    if (this.ColorC > 0) {
                        this.ColorC = 1;
                    }
                    this.LabelA = LabelMat[this.jrow - 1][this.jcol - 1];
                    this.LabelB = LabelMat[this.jrow - 1][this.jcol];
                    this.LabelC = LabelMat[this.jrow][this.jcol - 1];
                }
                if (this.ColorA > 0) {
                    this.ColorA = 1;
                }
                if (this.ColorB > 0) {
                    this.ColorB = 1;
                }
                if (this.ColorC > 0) {
                    this.ColorC = 1;
                }
                if (this.ColorD > 0) {
                    this.ColorD = 1;
                }
                int Case = 0;
                Case = this.ColorA == this.ColorB ? (this.ColorC == this.ColorD ? (this.ColorA == this.ColorC ? 1 : 2) : (this.ColorA == this.ColorC ? 5 : 6)) : (this.ColorC == this.ColorD ? (this.ColorA == this.ColorC ? 3 : 4) : (this.ColorA == this.ColorC ? 7 : 8));
                if (Case == 1) {
                    this.OldRegion(this.LabelC, -1, -1);
                } else if (Case == 2 || Case == 3) {
                    this.OldRegion(this.LabelC, this.LabelB, this.LabelC);
                } else if (Case == 5 || Case == 8) {
                    if ((this.jrow == Rows || this.jcol == Cols) && this.ColorD == FillColor) {
                        this.OldRegion(0, -1, -1);
                    } else {
                        this.NewRegion(this.LabelB);
                    }
                } else if (Case == 6 || Case == 7) {
                    this.OldRegion(this.LabelB, this.LabelB, this.LabelC);
                } else {
                    int LabelX;
                    int LabelBRoot = Blobs.SubsumptionChain(this.LabelB);
                    int LabelCRoot = Blobs.SubsumptionChain(this.LabelC);
                    int LabelRoot = Math.min(LabelBRoot, LabelCRoot);
                    if (LabelBRoot < LabelCRoot) {
                        this.OldRegion(this.LabelB, -1, -1);
                        LabelX = this.LabelC;
                    } else {
                        this.OldRegion(this.LabelC, -1, -1);
                        LabelX = this.LabelB;
                    }
                    int NextLabelX = LabelX;
                    while (LabelRoot < LabelX) {
                        NextLabelX = SubsumedLabel[LabelX];
                        Blobs.SubsumedLabel[LabelX] = LabelRoot;
                        LabelX = NextLabelX;
                    }
                }
                if ((this.jrow == Rows || this.jcol == Cols) && this.ColorD == FillColor) {
                    int LabelRoot;
                    if (this.jcol < Cols) {
                        if (this.ColorC != FillColor) {
                            LabelRoot = Blobs.SubsumptionChain(this.LabelB);
                            Blobs.SubsumedLabel[LabelRoot] = 0;
                        }
                    } else if (this.jrow < Rows && this.ColorB != FillColor) {
                        LabelRoot = Blobs.SubsumptionChain(this.LabelC);
                        Blobs.SubsumedLabel[LabelRoot] = 0;
                    }
                    this.OldRegion(0, -1, -1);
                }
                Blobs.LabelMat[this.jrow][this.jcol] = this.LabelD;
            }
        }
        int Offset = 0;
        for (Label2 = 1; Label2 <= MaxLabel; ++Label2) {
            if (SubsumedLabel[Label2] > -1) {
                ++Offset;
            }
            Blobs.CondensationMap[Label2] = Label2 - Offset;
        }
        for (Label2 = 1; Label2 <= MaxLabel; ++Label2) {
            int BetterLabel = Blobs.SubsumptionChain(Label2);
            if (BetterLabel == Label2) continue;
            this.Subsume(BetterLabel, Label2, 1);
        }
        int NewMaxLabel = 0;
        for (OldLabel = 1; OldLabel <= MaxLabel; ++OldLabel) {
            if (SubsumedLabel[OldLabel] >= 0) continue;
            double[] OldRegion = RegionData[OldLabel];
            OldParent = (int)OldRegion[BLOBPARENT];
            NewLabel = CondensationMap[OldLabel];
            NewParent = Blobs.SubsumptionChain(OldParent);
            NewParent = CondensationMap[NewParent];
            OldRegion[Blobs.BLOBLABEL] = NewLabel;
            OldRegion[Blobs.BLOBPARENT] = NewParent;
            System.arraycopy(OldRegion, 0, RegionData[NewLabel], 0, BLOBDATACOUNT);
            NewMaxLabel = NewLabel;
        }
        for (Label = NewMaxLabel + 1; Label <= MaxLabel; ++Label) {
            this.ResetRegion(Label);
        }
        for (Label = MaxLabel = NewMaxLabel; Label > 0; --Label) {
            int ThisParent;
            double[] ThisRegion = RegionData[Label];
            int ThisArea = (int)ThisRegion[BLOBAREA];
            Blobs.SubsumedLabel[Label] = ThisArea < MinArea ? (ThisParent = (int)ThisRegion[BLOBPARENT]) : -1;
        }
        Offset = 0;
        for (Label = 1; Label <= MaxLabel; ++Label) {
            if (SubsumedLabel[Label] > -1) {
                ++Offset;
            }
            Blobs.CondensationMap[Label] = Label - Offset;
        }
        for (Label = 1; Label <= MaxLabel; ++Label) {
            int BetterLabel = Blobs.SubsumptionChain(Label);
            if (BetterLabel == Label) continue;
            this.Subsume(BetterLabel, Label, -1);
        }
        for (OldLabel = 1; OldLabel <= MaxLabel; ++OldLabel) {
            if (SubsumedLabel[OldLabel] >= 0) continue;
            double[] OldRegion = RegionData[OldLabel];
            OldParent = (int)OldRegion[BLOBPARENT];
            NewLabel = CondensationMap[OldLabel];
            NewParent = Blobs.SubsumptionChain(OldParent);
            NewParent = CondensationMap[NewParent];
            OldRegion[Blobs.BLOBLABEL] = NewLabel;
            OldRegion[Blobs.BLOBPARENT] = NewParent;
            System.arraycopy(OldRegion, 0, RegionData[NewLabel], 0, BLOBDATACOUNT);
            NewMaxLabel = NewLabel;
        }
        for (Label = NewMaxLabel + 1; Label <= MaxLabel; ++Label) {
            this.ResetRegion(Label);
        }
        MaxLabel = NewMaxLabel;
        for (Label = 0; Label <= MaxLabel; ++Label) {
            double[] ThisRegion = RegionData[Label];
            double Area = ThisRegion[BLOBAREA];
            double SumX = ThisRegion[BLOBSUMX];
            double SumY = ThisRegion[BLOBSUMY];
            double SumXX = ThisRegion[BLOBSUMXX];
            double SumYY = ThisRegion[BLOBSUMYY];
            double SumXY = ThisRegion[BLOBSUMXY];
            SumXX /= Area;
            SumYY /= Area;
            SumXY /= Area;
            SumXX -= (SumX /= Area) * SumX;
            SumYY -= (SumY /= Area) * SumY;
            if ((SumXY -= SumX * SumY) > -1.0E-14 && SumXY < 1.0E-14) {
                SumXY = 0.0;
            }
            ThisRegion[Blobs.BLOBSUMX] = SumX;
            ThisRegion[Blobs.BLOBSUMY] = SumY;
            ThisRegion[Blobs.BLOBSUMXX] = SumXX;
            ThisRegion[Blobs.BLOBSUMYY] = SumYY;
            ThisRegion[Blobs.BLOBSUMXY] = SumXY;
            System.arraycopy(ThisRegion, 0, RegionData[Label], 0, BLOBDATACOUNT);
        }
        BorderRegion = RegionData[0];
        BorderRegion[Blobs.BLOBSUMXY] = 0.0;
        BorderRegion[Blobs.BLOBSUMYY] = 0.0;
        BorderRegion[Blobs.BLOBSUMXX] = 0.0;
        System.arraycopy(BorderRegion, 0, RegionData[0], 0, BLOBDATACOUNT);
        return MaxLabel;
    }

    public static void SortRegions(int Col) {
        for (int i2 = 0; i2 < MaxLabel; ++i2) {
            for (int j2 = i2 + 1; j2 <= MaxLabel; ++j2) {
                iProperty = RegionData[i2];
                iField = iProperty[Col];
                jProperty = RegionData[j2];
                jField = jProperty[Col];
                if (!(iField > jField)) continue;
                Blobs.RegionData[i2] = jProperty;
                Blobs.RegionData[j2] = iProperty;
            }
        }
    }

    static {
        SubsumedLabel = new int[BLOBTOTALCOUNT];
        CondensationMap = new int[BLOBTOTALCOUNT];
    }
}

