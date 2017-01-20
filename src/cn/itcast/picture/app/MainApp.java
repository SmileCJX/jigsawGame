package cn.itcast.picture.app;

import cn.itcast.picture.ui.PictureMainFrame;

/**
 * 启动器
 * @author Administrator
 *
 */
public class MainApp {
	public static void main(String[] args) {
		//创建主界面
		PictureMainFrame frame = new PictureMainFrame();
		//显示主界面
		frame.setVisible(true);
	}
}
