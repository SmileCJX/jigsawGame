package cn.itcast.picture.ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/*
 * 图片预览类
 */
public class PicturePreview extends JPanel {
	//重写绘制组件方法，显示图片的显示
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//指定图片的路径
		String filename = "picture\\"+PictureCanvas.pictureID+".jpg";
		//通过图片的一个路径获取到对应的图片的图像
		ImageIcon icon = new ImageIcon(filename);
		Image image = icon.getImage();
		//把图像绘制到预览区的面板中
		g.drawImage(image, 20, 20, 450, 600, this);
	}
}
