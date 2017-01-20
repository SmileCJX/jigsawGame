package cn.itcast.picture.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PictureMainFrame extends JFrame {
	
	private String [] items = {"科比1","科比2"};
	private JRadioButton addNumInfo;//数字提示
	private JRadioButton clearNumInfo;//清除提示
	private PictureCanvas canvas;//拼图区
	private PicturePreview preview;//预览区
	private JComboBox<String> box;//下拉框
	private JTextField name; //图片名称
	public static JTextField step; //步数
	private JButton start;//开始按钮
	//空参数构造方法
	public PictureMainFrame(){
		//supre();
		
		init();//界面初始化操作
		
		addComponent();//添加组件
		
		addPreviewImage();//添加预览图片与拼图图片
		
		addActionListener(); //为组件添加事件监听
	}
	//为组件添加事件监听
	private void addActionListener() {
		//数字提示
		addNumInfo.addActionListener(new ActionListener() {
			//点击时激活下面的方法
			@Override
			public void actionPerformed(ActionEvent e) {
				//完成数字提示的显示
				canvas.reLoadPictureAddNumber();
			}
		});
		
		//清除提示
		clearNumInfo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//完成十字提示的清除
				canvas.reLoadPictureClearNumber();
			}
		});
		
		//下拉框
		box.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				//获取到选择的图片的序号
				int num = box.getSelectedIndex(); //默认从0开始
				//更新当期图片ID
				PictureCanvas.pictureID = num + 1;
				//更新预览区
				preview.repaint(); //重新绘制界面
				//更新拼图区
				canvas.reLoadPictureClearNumber();
				//更新游戏状态区
				name.setText("图片名称："+box.getSelectedItem()); //设置图片名称
				
				int stepNum = PictureCanvas.stepNum = 0; 
				step.setText("步数："+ stepNum); //设置步数显示
				//按钮区
				//把选择按钮设置成清除提示按钮选中状态
				clearNumInfo.setSelected(true);
			}
		});
		
		//开始按钮
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//已移动的步数清零
				PictureCanvas.stepNum = 0;
				//游戏状态进行步数显示更新'
				step.setText("步数："+ PictureCanvas.stepNum);
				//对小方格进行重新位置排序，打乱顺序
				canvas.start();
			}
		});
		
	}
	//添加预览图片与拼图图片
	private void addPreviewImage() {
		//创建一个面板,包含一个拼图区和预览区
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));//设置布局为表格布局
		canvas = new PictureCanvas();
		canvas.setBorder(new TitledBorder("拼图区"));//添加边框
		preview = new PicturePreview();
		preview.setBorder(new TitledBorder("预览区"));//添加边框
		//把拼图区与图片预览区添加到中间的面板中
		panel.add(canvas,BorderLayout.WEST);//左边
		panel.add(preview,BorderLayout.EAST);//右边
		
		//把面板显示在主界面中
		this.add(panel,BorderLayout.CENTER);//居中显示
	}

	//添加组件
	private void addComponent() {
		//创建用来在主界面上方显示的面板，在面板中要包含按钮区与游戏状态区
		JPanel panel = new JPanel();
//		panel.setBackground(Color.PINK);//设置面板的背景色
		panel.setLayout(new GridLayout(1,2));
		
		//创建左边的按钮区面板
		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(new TitledBorder("按钮区"));//添加边框
		leftPanel.setBackground(Color.PINK);//设置对应的背景色
		
		addNumInfo = new JRadioButton("数字提示",false);
		clearNumInfo = new JRadioButton("清除提示",true);
		//添加按钮组
		ButtonGroup buttonGroup = new ButtonGroup();
		box = new JComboBox<String>(items);
		start = new JButton("start");
		panel.add(leftPanel,BorderLayout.WEST);//左边
		
		//添加单选按钮到按钮组中
		buttonGroup.add(addNumInfo);
		buttonGroup.add(clearNumInfo);
		//设置背景色
		addNumInfo.setBackground(Color.PINK);
		clearNumInfo.setBackground(Color.PINK);
		start.setBackground(Color.PINK);
		
		//添加组件到面板中
		leftPanel.add(addNumInfo);
		leftPanel.add(clearNumInfo);
		leftPanel.add(new JLabel("                选择图片"));
		leftPanel.add(box);
		leftPanel.add(start);
		//---------------------------------------
		//创建右边的按钮区面板
		JPanel rightPanel = new JPanel();
		rightPanel.setBorder(new TitledBorder("游戏状态"));//添加边框的效果
		rightPanel.setBackground(Color.PINK);//设置背景色
		rightPanel.setLayout(new GridLayout(1,2));//设置布局为表格布局
		
		name = new JTextField("图片名称：小女孩");
		step = new JTextField("步数：0");
		
		//设置文本框不能编辑
		name.setEditable(false);
		step.setEditable(false);
		
		//把组件添加到游戏状态面板中
		rightPanel.add(name,BorderLayout.WEST);//左边
		rightPanel.add(step,BorderLayout.EAST);//右边
			
		panel.add(rightPanel,BorderLayout.EAST);//右边
		
		//----------------------------------------------
		//设置panel在主界面的上方显示
		this.add(panel,BorderLayout.NORTH);//上面
	}

	//界面初始化方法
	private void init(){
		//设置窗口的标题
		this.setTitle("拼图游戏");
		//设置窗口的大小
		this.setSize(1000,720);
		//设置窗口的显示位置
		this.setLocation(150,10);
		//设置窗口的大小
		this.setResizable(false);
		//设置窗口的默认关闭操作
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
