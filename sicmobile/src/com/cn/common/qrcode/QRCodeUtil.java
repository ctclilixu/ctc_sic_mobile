package com.cn.common.qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

import com.swetake.util.Qrcode;

/**
 * QRCodeUtil
 * @author Frank
 * @version 1.0
 * @create 2016-3-3下午5:40:56
 */
public class QRCodeUtil {
	
	private static int size = 10;

	/**
	 * 生成二维码
	 * @param content 内容
	 * @param imgPath 二维码图片保存路径
	 * @param iconPath 二维码中间图片路径
	 */
	public void encoderQRCode(String content, String imgPath, String iconPath) {
		this.encoderQRCode(content, imgPath, "png", size, iconPath);
	}

	/**
	 * 生成二维码
	 * @param content 内容
	 * @param output 输出流
	 */
	public void encoderQRCode(String content, OutputStream output) {
		this.encoderQRCode(content, output, "png", size);
	}

	/**
	 * 生成二维码
	 * @param content 内容
	 * @param imgPath 二维码图片保存路径
	 * @param imgType 生成二维码图片类型
	 * @param iconPath 二维码中间图片路径
	 */
	public void encoderQRCode(String content, String imgPath,
			String imgType, String iconPath) {
		this.encoderQRCode(content, imgPath, imgType, size, iconPath);
	}

	/**
	 * 生成二维码
	 * @param content 内容
	 * @param output 输出流
	 * @param imgType 生成二维码图片类型
	 */
	public void encoderQRCode(String content, OutputStream output,
			String imgType) {
		this.encoderQRCode(content, output, imgType, 7);
	}

	/**
	 * @param content 内容
	 * @param imgPath 二维码图片保存路径
	 * @param imgType 生成二维码图片类型
	 * @param size 二维码图片大小
	 * @param iconPath 二维码中间图片路径
	 */
	public void encoderQRCode(String content, String imgPath, String imgType,
			int size, String iconPath) {
		try {
			BufferedImage bufImg = this.qRCodeCommon(content, imgType, size,
					iconPath);
			File imgFile = new File(imgPath);
			// 生成二维码
			ImageIO.write(bufImg, imgType, imgFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param content 内容
	 * @param output 输出流
	 * @param imgType 生成二维码图片类型
	 * @param size 二维码图片大小
	 */
	public void encoderQRCode(String content, OutputStream output,
			String imgType, int size) {
		try {
			BufferedImage bufImg = this.qRCodeCommon(content, imgType, size);
			// 生成二维码
			ImageIO.write(bufImg, imgType, output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 二维码生成共通方法
	 * @param content 内容
	 * @param imgType 生成二维码图片类型
	 * @param size 二维码图片大小
	 * @return
	 */
	private BufferedImage qRCodeCommon(String content, String imgType, int size) {
		BufferedImage bufImg = null;
		try {
			Qrcode qrcodeHandler = new Qrcode();
			// 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
			qrcodeHandler.setQrcodeErrorCorrect('M');
			qrcodeHandler.setQrcodeEncodeMode('B');
			// 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
			qrcodeHandler.setQrcodeVersion(size);
			// 获得内容的字节数组，设置编码格式
			byte[] contentBytes = content.getBytes("utf-8");
			// 图片尺寸
			int imgSize = 67 + 12 * (size - 1);
			bufImg = new BufferedImage(imgSize, imgSize,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D gs = bufImg.createGraphics();
			// 设置背景颜色
			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, imgSize, imgSize);

			// 设定图像颜色> BLACK
			gs.setColor(Color.BLACK);
			// 设置偏移量，不设置可能导致解析出错
			int pixoff = 2;
			// 输出内容> 二维码
			if (contentBytes.length > 0 && contentBytes.length < 800) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else {
				throw new Exception("QRCode content bytes length = "
						+ contentBytes.length + " not in [0, 800].");
			}
			gs.dispose();
			bufImg.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bufImg;
	}

	/**
	 * 二维码生成共通方法
	 * @param content 内容
	 * @param imgType 生成二维码图片类型
	 * @param size 二维码图片大小
	 * @param imgIcon 二维码中间图片路径
	 * @return
	 */
	private BufferedImage qRCodeCommon(String content, String imgType,
			int size, String imgIcon) {
		BufferedImage bufImg = null;
		try {
			Qrcode qrcodeHandler = new Qrcode();
			// 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
			qrcodeHandler.setQrcodeErrorCorrect('L');
			qrcodeHandler.setQrcodeEncodeMode('B');
			// 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
			qrcodeHandler.setQrcodeVersion(size);
			// 获得内容的字节数组，设置编码格式
			byte[] contentBytes = content.getBytes("utf-8");
			// 图片尺寸
			int imgSize = 65 + 12 * (size - 1);
			bufImg = new BufferedImage(imgSize, imgSize,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D gs = bufImg.createGraphics();
			// 设置背景颜色
			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, imgSize, imgSize);

			// 设定图像颜色> BLACK
			gs.setColor(Color.BLACK);
			// 设置偏移量，不设置可能导致解析出错
			int pixoff = 1;
			// 输出内容> 二维码
			if (contentBytes.length > 0 && contentBytes.length < 800) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				//System.out.println(codeOut.length);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else {
				throw new Exception("QRCode content bytes length = "
						+ contentBytes.length + " not in [0, 800].");
			}
			Image img = ImageIO.read(new File(imgIcon));
			gs.drawImage(img, (imgSize - imgSize / 6) / 2,
					(imgSize - imgSize / 6) / 2, imgSize / 6, imgSize / 6, null);
			gs.dispose();
			bufImg.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bufImg;
	}

	/**
	 * 解析二维码
	 * @param imgPath 二维码图片路径
	 * @return
	 */
	public String decoderQRCode(String imgPath) {
		// QRCode 二维码图片的文件
		File imageFile = new File(imgPath);
		BufferedImage bufImg = null;
		String content = null;
		try {
			bufImg = ImageIO.read(imageFile);
			QRCodeDecoder decoder = new QRCodeDecoder();
			content = new String(decoder.decode(new QRCodeImageDto(
					bufImg)), "utf-8");
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} catch (DecodingFailedException dfe) {
			System.out.println("Error: " + dfe.getMessage());
			dfe.printStackTrace();
		}
		return content;
	}

	/**
	 * 解析二维码
	 * @param input 输入流
	 * @return
	 */
	public String decoderQRCode(InputStream input) {
		BufferedImage bufImg = null;
		String content = null;
		try {
			bufImg = ImageIO.read(input);
			QRCodeDecoder decoder = new QRCodeDecoder();
			content = new String(decoder.decode(new QRCodeImageDto(
					bufImg)), "utf-8");
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} catch (DecodingFailedException dfe) {
			System.out.println("Error: " + dfe.getMessage());
			dfe.printStackTrace();
		}
		return content;
	}

	public static void main(String[] args) {
		try {
			String imgType = "bmp";
			String imgPath = "d://tttt." + imgType;
			String encoderContent = "http://www.shdsyg.cn/dsyg/";
			String iconPath = "D://1.png";
			QRCodeUtil qr = new QRCodeUtil();
			//生成二维码
			qr.encoderQRCode(encoderContent, imgPath, imgType, iconPath);
			
			//解析二维码
			OutputStream output = new FileOutputStream(imgPath);
			qr.encoderQRCode(encoderContent, output);
			String decoderContent = qr.decoderQRCode(imgPath);
			System.out.println("decoderContent:" + decoderContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
