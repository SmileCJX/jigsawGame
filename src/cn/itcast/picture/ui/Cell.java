package cn.itcast.picture.ui;

import javax.swing.Icon;
import javax.swing.JButton;
/*
 * 图片小方格类
 */
public class Cell extends JButton {
	//带有图片的小方格
	public Cell(Icon icon) {
		super(icon);
		//设置当前的小方格大小
		this.setSize(150,150);
	}
	
	//带有图片并且带有文字的小方格
	public Cell(String text, Icon icon) {
		super(text, icon);
		//设置小方格大小
		this.setSize(150, 150);
		this.setHorizontalTextPosition(CENTER);//文字水平居中显示
		this.setVerticalTextPosition(CENTER);//文字垂直居中显示
	}
	
	//当前方格的移动
	public void move(String direction){//上，下，左，右
		switch (direction) {
		case "UP":
			this.setLocation(this.getBounds().x, this.getBounds().y - 150);
			break;
		case "DOWN":
			this.setLocation(this.getBounds().x, this.getBounds().y + 150);
			break;
		case "LEFT":
			this.setLocation(this.getBounds().x - 150, this.getBounds().y);
			break;
		case "RIGHT":
			this.setLocation(this.getBounds().x + 150, this.getBounds().y);
			break;
		default://其他情况，不做移动处理
			break;
		}
	}
	
}
