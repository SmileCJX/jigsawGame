package cn.itcast.picture.ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/*
 * ͼƬԤ����
 */
public class PicturePreview extends JPanel {
	//��д���������������ʾͼƬ����ʾ
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//ָ��ͼƬ��·��
		String filename = "picture\\"+PictureCanvas.pictureID+".jpg";
		//ͨ��ͼƬ��һ��·����ȡ����Ӧ��ͼƬ��ͼ��
		ImageIcon icon = new ImageIcon(filename);
		Image image = icon.getImage();
		//��ͼ����Ƶ�Ԥ�����������
		g.drawImage(image, 20, 20, 450, 600, this);
	}
}
