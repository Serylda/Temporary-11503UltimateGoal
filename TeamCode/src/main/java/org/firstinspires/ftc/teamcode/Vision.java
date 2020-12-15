package org.firstinspires.ftc.teamcode;
        import android.graphics.Bitmap;
        import static android.graphics.Color.red;
        import static android.graphics.Color.green;
        import static android.graphics.Color.blue;

        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.vuforia.Frame;
        import com.vuforia.Image;
        import com.vuforia.PIXEL_FORMAT;
        import com.vuforia.Vuforia;

        import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
        import org.firstinspires.ftc.robotcore.external.ClassFactory;
        import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
        import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
        import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
        import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.Parameters;

        import java.util.ArrayList;
        import java.util.concurrent.BlockingQueue;


public class Vision {
    private LinearOpMode opMode;

    private VuforiaLocalizer vuforia;
    private Parameters parameters;
    private CameraDirection CAMERA_CHOICE = CameraDirection.BACK; // This is the camera opposite the screen.
    private static final boolean PHONE_IS_PORTRAIT = false;
    private static final String VUFORIA_KEY = "AcELeNr/////AAABmeg7NUNcDkPigDGNImdu5slLREdKn/q+qfajHBypycR0JUZYbfU0q2yZeSud79LJ2DS9uhr7Gu0xDM0DQZ36GRQDgMRwB8lf9TGZFQcoHq4kVAjAoEByEorXCzQ54ITCextAucpL2njKT/1IJxgREr6/axNEL2evyKSpOKoNOISKR6tkP6H3Ygd+FHm2tF/rsUCJHN5bTXrbRbwt5t65O7oJ6Wm8Foz1npbFI0bsD60cug4CpC/Ovovt2usxIRG8cpoQX49eA2jPRRLGXN8y1Nhh9Flr0poOkYoCExWo2iVunAGOwuCdB/rp/+2rkLBfWPvzQzrN9yBBP0JVJZ4biNQ41qqiuVvlc31O9xEvbKHt";


    private final int RED_LOW = 140;
    private final int GREEN_LOW = 100;
    private final int BLUE_LOW = 0;
    private final int RED_HIGH = 200;
    private final int GREEN_HIGH = 160;
    private final int BLUE_HIGH = 60;

    public Vision(LinearOpMode opMode) {

        this.opMode = opMode;

        // Configures Vuforia with the wanted camera
        int cameraMonitorViewId = this.opMode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", this.opMode.hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        params.vuforiaLicenseKey = VUFORIA_KEY;
        params.cameraName = opMode.hardwareMap.get(WebcamName.class, "Webcam");
        //params.cameraDirection = CameraDirection.DEFAULT;
        //params.cameraDirection = ;
        //Orientation
        //phone
        vuforia = ClassFactory.getInstance().createVuforia(params);

        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true); // Format returns 2 bytes per pixel in GGGBBBBB RRRRRGGG format (little-endian)
        vuforia.setFrameQueueCapacity(4);
        vuforia.enableConvertFrameToBitmap();

    }

    public int ringCount(char side) {
        Bitmap bitmap = null;
        try {
            bitmap = getBitmap();
        } catch (InterruptedException e) {
            opMode.telemetry.addData("ERROR", "Flaming jesus with fiery fibroids");
            opMode.telemetry.update();
        }

        int x;
        if (side == 'b') //blue side
        {
            x = bitmap.getWidth() * 1000 / 1280;
        }
        else //red side
        {
            x = 420;
        }

        int highY = 8964;
        int lowY = 0;
        for (int y = bitmap.getHeight() - 1; y > 490; y--)
        {
            int pixel = bitmap.getPixel(x, y);
            if(isOrange(bitmap.getPixel(x, y)) && isOrange(bitmap.getPixel(x, y-2)) && isOrange(bitmap.getPixel(x, y-4))
                    && isOrange(bitmap.getPixel(x, y-6)) && isOrange(bitmap.getPixel(x, y-8)))
            {
                highY = y;
                break;
            }
        }

        if(highY == 8964) //No orange found
        {
            return 0;
        }

        for (int y = highY - 200; y <=  highY-8; y++)
        {
            int pixel = bitmap.getPixel(x, y);
            if(isOrange(bitmap.getPixel(x, y)) && isOrange(bitmap.getPixel(x, y+2)) && isOrange(bitmap.getPixel(x, y+4))
                    && isOrange(bitmap.getPixel(x, y+6)) && isOrange(bitmap.getPixel(x, y+8)))
            {
                lowY = y;
                break;
            }
        }

        int yRange = highY - lowY;


        if (yRange < 40)
        {
            return 1;
        }
        else
        {
            return 4;
        }

    }

    public boolean isOrange(int pixel){
        return (RED_LOW <= red(pixel) && red(pixel) <= RED_HIGH && GREEN_LOW <= green(pixel) &&
                green(pixel) <= GREEN_HIGH && BLUE_LOW <= blue(pixel) && blue(pixel) <= BLUE_HIGH);
    }

    public Bitmap getBitmap() throws InterruptedException {

        VuforiaLocalizer.CloseableFrame picture;
        picture = vuforia.getFrameQueue().take();
        Image rgb = picture.getImage(1);

        long numImages = picture.getNumImages();

        //opMode.telemetry.addData("Num images", numImages);
        //opMode.telemetry.update();

        for (int i = 0; i < numImages; i++) {

            int format = picture.getImage(i).getFormat();
            if (format == PIXEL_FORMAT.RGB565) {
                rgb = picture.getImage(i);
                break;
            } else {
                opMode.telemetry.addLine("Didn't find correct RGB format");
                opMode.telemetry.update();


            }
        }

        Bitmap imageBitmap = Bitmap.createBitmap(rgb.getWidth(), rgb.getHeight(), Bitmap.Config.RGB_565);
        imageBitmap.copyPixelsFromBuffer(rgb.getPixels());

        //opMode.telemetry.addData("Image width", imageBitmap.getWidth());
        //opMode.telemetry.addData("Image height", imageBitmap.getHeight());
        //opMode.telemetry.update();
        //opMode.sleep(500);

        picture.close();


        return imageBitmap;
    }


}




