package com.xhrmyy.hishelp.util;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

public class PictureUtil {
    private static BufferedImage bim;
    private String srcFile;
    private String destFile;
    private int width;
    private int height;
    private Image img;
    private static final Logger log = LoggerFactory.getLogger(PictureUtil.class);

    /**
     * 构造函数
     *
     * @param fileName String
     * @throws IOException
     */
    public PictureUtil(String fileName) throws IOException {
        File _file = new File(fileName); // 读入文件
        this.srcFile = fileName;
        // 查找最后一个.
        int index = this.srcFile.lastIndexOf(".");
        String ext = this.srcFile.substring(index);
        this.destFile = this.srcFile.substring(0, index) + "_s" + ext;
        img = ImageIO.read(_file); // 构造Image对象
        width = img.getWidth(null); // 得到源图宽
        height = img.getHeight(null); // 得到源图长
    }

    /**
     * 检查图片后缀是否符合要求
     *
     * @param fileName 文件名
     * @return 符合返回true，不符合返回false
     */
    public static boolean checkFormat(String fileName) {
        String extEnd = getImageFormat(fileName);
        if (!extEnd.equals(".BMP") && !extEnd.equals(".PNG") && !extEnd.equals(".JPG") && !extEnd.equals(".JPEG") && !extEnd.equals(".ICO")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取文件后缀名
     *
     * @param fileName
     * @return 文件格式
     */
    public static String getImageFormat(String fileName) {
        int extStart = fileName.lastIndexOf(".");
        String extEnd = fileName.substring(extStart, fileName.length()).toUpperCase();
        return extEnd;
    }

    /**
     * 根据hash值分离出图片的路径，图片的路径为两级，第一级为hash值的第一位和第二位，第二级为hash值的第三位和第四位h值
     *
     * @param hash 传入的hash值
     * @return 图片路径
     */
    public static String getHashPath(String hash) {
        String firstDir = hash.substring(0, 2);
        String secondDir = hash.substring(2, 4);
        return firstDir + "/" + secondDir;
    }

    /**
     *      * 根据指定大小和指定精度压缩图片
     *      *
     *      * @param srcPath
     *      *            源图片地址
     *      * @param desPath
     *      *            目标图片地址
     *      * @param desFileSize
     *      *            指定图片大小，单位kb
     *      * @param accuracy
     *      *            精度，递归压缩的比率，建议小于0.9
     *      * @return
     *     
     */
    /**
     *      * 根据指定大小和指定精度压缩图片
     *      *
     *      * @param srcPath
     *      *            源图片地址
     *      * @param desPath
     *      *            目标图片地址
     *      * @param desFileSize
     *      *            指定图片大小，单位kb
     *      * @param accuracy
     *      *            精度，递归压缩的比率，建议小于0.9
     *      * @return
     *     
     */
    public static String compressPicForScale(String srcPath, String desPath,
                                             long desFileSize, double accuracy) {
        if (StringUtils.isEmpty(srcPath) || StringUtils.isEmpty(srcPath)) {
            return null;
        }
        if (!new File(srcPath).exists()) {
            return null;
        }
        try {
            File srcFile = new File(srcPath);
            long srcFileSize = srcFile.length();
            log.info("源图片：" + srcPath + "，大小：" + srcFileSize / 1024 + "kb");

            // 1、先转换成jpg
            Thumbnails.of(srcPath).scale(1f).toFile(desPath);
            // 递归压缩，直到目标文件大小小于desFileSize
            compressPicCycle(desPath, desFileSize, accuracy);

            File desFile = new File(desPath);
            log.info("目标图片：" + desPath + "，大小" + desFile.length() / 1024 + "kb");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return desPath;
    }

    private static void compressPicCycle(String desPath, long desFileSize,
                                         double accuracy) throws IOException {
        File srcFileJPG = new File(desPath);
        long srcFileSizeJPG = srcFileJPG.length();
        // 2、判断大小，如果小于500kb，不压缩；如果大于等于500kb，压缩
        //if (srcFileSizeJPG <= desFileSize * 1024) {
        //    return;
        //}
        // 计算宽高
        bim = ImageIO.read(srcFileJPG);
        int srcWidth = bim.getWidth();
        int srcHeight = bim.getHeight();
        int desWidth = new BigDecimal(srcWidth).multiply(new BigDecimal(accuracy)).intValue();
        int desHeight = new BigDecimal(srcHeight).multiply(
                new BigDecimal(accuracy)).intValue();

        Thumbnails.of(desPath).size(desWidth, desHeight).outputQuality(accuracy).toFile(desPath);
        //compressPicCycle(desPath, desFileSize, accuracy);
    }


    public static void main(String[] args) {
        compressPicForScale("0d680d82b5.JPG", "0d680d82b5.JPG", 512, 0.8);
    }

    /**
     * 强制压缩/放大图片到固定的大小
     *
     * @param w int 新宽度
     * @param h int 新高度
     * @throws IOException
     */
    public void resize(int w, int h) throws IOException {
        BufferedImage _image = new BufferedImage(w, h,
                BufferedImage.TYPE_INT_RGB);
        _image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
        //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        //encoder.encode(_image); // 近JPEG编码
        if (!ImageIO.write(_image, getImageFormat(srcFile).substring(1), out))
            // 返回false则抛出异常
            throw new IllegalArgumentException("写入缩略图失败");
        out.close();
    }

    /**
     * 按照固定的比例缩放图片
     *
     * @param t double 比例
     * @throws IOException
     */
    public void resize(double t) throws IOException {
        int w = (int) (width * t);
        int h = (int) (height * t);
        resize(w, h);
    }

    /**
     * 以宽度为基准，等比例放缩图片
     *
     * @param w int 新宽度
     * @throws IOException
     */
    public void resizeByWidth(int w) throws IOException {
        int h = (int) (height * w / width);
        resize(w, h);
    }

    /**
     * 以高度为基准，等比例缩放图片
     *
     * @param h int 新高度
     * @throws IOException
     */
    public void resizeByHeight(int h) throws IOException {
        int w = (int) (width * h / height);
        resize(w, h);
    }

    /**
     * 按照最大高度限制，生成最大的等比例缩略图
     *
     * @param w int 最大宽度
     * @param h int 最大高度
     * @throws IOException
     */
    public void resizeFix(int w, int h) throws IOException {
        if (width / height > w / h) {
            resizeByWidth(w);
        } else {
            resizeByHeight(h);
        }
    }

    /**
     * 获取目标文件名 getDestFile
     */
    public String getDestFile() {
        return destFile;
    }

    /**
     * 设置目标文件名 setDestFile
     *
     * @param fileName String 文件名字符串
     */
    public void setDestFile(String fileName) throws Exception {
        if (!fileName.endsWith(".jpg")) {
            throw new Exception("Dest File Must end with \".jpg\".");
        }
        destFile = fileName;
    }

    /**
     * 获取图片原始宽度 getSrcWidth
     */
    public int getSrcWidth() {
        return width;
    }

    /**
     * 获取图片原始高度 getSrcHeight
     */
    public int getSrcHeight() {
        return height;
    }

    /**
     * 根据图片的尺寸来获取合适的压缩比例
     *
     * @param maxQuality
     * @return
     */
    public double getQuality(double maxQuality) {

        int imageSize = this.width * this.height;
        if (imageSize < 1280 * 720) {
            return maxQuality;
        } else if (imageSize <= 1920 * 1080) {
            return 0.6f;
        } else if (imageSize <= 2560 * 1440) {
            return 0.4f;
        } else {
            return 0.2f;
        }
    }
}
