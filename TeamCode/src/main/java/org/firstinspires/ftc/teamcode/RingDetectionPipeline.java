package org.firstinspires.ftc.teamcode;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import static org.firstinspires.ftc.teamcode.Constants.*;
import static org.firstinspires.ftc.teamcode.AutoBase.allianceColor;

class RingDetectionPipeline extends OpenCvPipeline
{

    static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(10,245);
    static final int REGION_WIDTH = 60;
    static final int REGION_HEIGHT = 60;

    static final Point REGION1_POINTA = new Point(
            REGION1_TOPLEFT_ANCHOR_POINT.x,
            REGION1_TOPLEFT_ANCHOR_POINT.y);
    static final Point REGION1_POINTB = new Point(
            REGION1_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
            REGION1_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

    Mat region1_Cb;
    Mat YCrCb = new Mat();
    Mat Cb = new Mat();
    protected int avg1;

    private volatile RingNumber ringNumber = null;

    void inputToCb(Mat input)
    {
        Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
        Core.extractChannel(YCrCb, Cb, 2);
    }

    @Override
    public void init(Mat firstFrame)
    {
        inputToCb(firstFrame);

        region1_Cb = Cb.submat(new Rect(REGION1_POINTA, REGION1_POINTB));
    }

    @Override
    public Mat processFrame(Mat input)
    {
        inputToCb(input);

        avg1 = (int) Core.mean(region1_Cb).val[0];

        if(avg1 > 120) {
            ringNumber = RingNumber.NONE;
        } else if (avg1 > 110) {
            ringNumber = RingNumber.ONE;
        } else {
            ringNumber = RingNumber.FOUR;
        }

        Imgproc.rectangle(
                input, // Buffer to draw on
                REGION1_POINTA, // First point which defines the rectangle
                REGION1_POINTB, // Second point which defines the rectangle
                GREEN, // The color the rectangle is drawn in
                2); // Thickness of the rectangle lines

        return input;
    }

    public RingNumber getRingNumber()
    {
        return ringNumber;
    }
}