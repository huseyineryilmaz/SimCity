package Graphics;

import java.nio.ByteBuffer;
import javafx.scene.image.WritablePixelFormat;

public class Image
{
    public static WritablePixelFormat format = WritablePixelFormat.getByteBgraInstance();
    public ByteBuffer buff;
    public ByteBuffer sizedBuff;
    public int width;
    public int height;
    public int sizedWidth;
    public int sizedHeigth;
    public int pivotX;
    public int pivotY;
    public Image(int width , int height , byte[] buffer)
    {
        sizedBuff = buff = ByteBuffer.wrap(buffer);
        this.width = sizedWidth = width;
        this.height = sizedHeigth = height;
    }
    public Image(int width , int height)
    {
        sizedBuff = buff = ByteBuffer.allocate(width * height * 4);
        this.width = sizedWidth = width;
        this.height = sizedHeigth = height;
    }
    public void scale(int targetWidth) 
    {
        int targetHeigth = height * targetWidth / width;
        pivotX = targetWidth / 2;
        pivotY = targetHeigth;
        if(targetWidth != sizedWidth || targetHeigth != sizedHeigth)
        {
            this.sizedWidth = targetWidth;
            this.sizedHeigth = targetHeigth;
            sizedBuff = ByteBuffer.allocate(targetWidth * targetHeigth *4);
            double x_ratio = width/(double)targetWidth ;
            double y_ratio = height/(double)targetHeigth ;
            double px, py ; 
            for (int i=0;i<targetHeigth  ;i++) {
                for (int j=0;j<targetWidth ;j++) {
                    px = Math.floor(j*x_ratio) ;
                    py = Math.floor(i*y_ratio) ;
                    sizedBuff.put(buff.array()[(int)(((py*width)+px) * 4)]);
                    sizedBuff.put(buff.array()[(int)(((py*width)+px) * 4) + 1]);
                    sizedBuff.put(buff.array()[(int)(((py*width)+px) * 4) + 2]);
                    sizedBuff.put(buff.array()[(int)(((py*width)+px) * 4) + 3]);
                }
            }
            sizedBuff.position(0);
        }
    }
    public void put(Image target , int targetX , int targetY)
    {
        int x = 0;
        int y = 0;
        int wd = this.sizedWidth;
        int hg = this.sizedHeigth;
       
        if(targetX - pivotX < 0)
        {
            x = pivotX - targetX;
        }
        if(targetX - pivotX + this.sizedWidth > target.sizedWidth)
        {
            wd = target.sizedWidth - targetX + pivotX;
        }
        if(targetY - pivotY  < 0)
        {
            y = pivotY - targetY;
        }
        
        if(targetY - pivotY + this.sizedHeigth > target.sizedHeigth)
        {
            hg = target.sizedHeigth - targetY + pivotY;
        }
        target.buff.position(4 *(target.sizedWidth *  (targetY - pivotY + y) + (targetX - pivotX + x)));
        sizedBuff.position(4 * (this.sizedWidth * y + x));
        // -1 <<<<<<<<<
        for (int i = y; i < hg -1 ; i++)
        { 
            for (int j = x; j < wd ; j++)
            {
                if(sizedBuff.get(sizedBuff.position() + 3) != 0)
                {                 
                    target.buff.putInt(sizedBuff.getInt());  
                }
                else 
                {
                    target.buff.position(target.buff.position() + 4);
                    sizedBuff.position(sizedBuff.position() + 4);
                }
            }
            target.buff.position(target.buff.position() + (target.width - wd + x) * 4);
            sizedBuff.position(sizedBuff.position() + (this.sizedWidth - wd + x) * 4);
        }
        target.buff.position(0);
        sizedBuff.position(0);
    }
}
 