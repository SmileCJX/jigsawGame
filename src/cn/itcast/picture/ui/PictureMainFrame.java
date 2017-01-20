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
	
	private String [] items = {"�Ʊ�1","�Ʊ�2"};
	private JRadioButton addNumInfo;//������ʾ
	private JRadioButton clearNumInfo;//�����ʾ
	private PictureCanvas canvas;//ƴͼ��
	private PicturePreview preview;//Ԥ����
	private JComboBox<String> box;//������
	private JTextField name; //ͼƬ����
	public static JTextField step; //����
	private JButton start;//��ʼ��ť
	//�ղ������췽��
	public PictureMainFrame(){
		//supre();
		
		init();//�����ʼ������
		
		addComponent();//������
		
		addPreviewImage();//���Ԥ��ͼƬ��ƴͼͼƬ
		
		addActionListener(); //Ϊ�������¼�����
	}
	//Ϊ�������¼�����
	private void addActionListener() {
		//������ʾ
		addNumInfo.addActionListener(new ActionListener() {
			//���ʱ��������ķ���
			@Override
			public void actionPerformed(ActionEvent e) {
				//���������ʾ����ʾ
				canvas.reLoadPictureAddNumber();
			}
		});
		
		//�����ʾ
		clearNumInfo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//���ʮ����ʾ�����
				canvas.reLoadPictureClearNumber();
			}
		});
		
		//������
		box.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				//��ȡ��ѡ���ͼƬ�����
				int num = box.getSelectedIndex(); //Ĭ�ϴ�0��ʼ
				//���µ���ͼƬID
				PictureCanvas.pictureID = num + 1;
				//����Ԥ����
				preview.repaint(); //���»��ƽ���
				//����ƴͼ��
				canvas.reLoadPictureClearNumber();
				//������Ϸ״̬��
				name.setText("ͼƬ���ƣ�"+box.getSelectedItem()); //����ͼƬ����
				
				int stepNum = PictureCanvas.stepNum = 0; 
				step.setText("������"+ stepNum); //���ò�����ʾ
				//��ť��
				//��ѡ��ť���ó������ʾ��ťѡ��״̬
				clearNumInfo.setSelected(true);
			}
		});
		
		//��ʼ��ť
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//���ƶ��Ĳ�������
				PictureCanvas.stepNum = 0;
				//��Ϸ״̬���в�����ʾ����'
				step.setText("������"+ PictureCanvas.stepNum);
				//��С�����������λ�����򣬴���˳��
				canvas.start();
			}
		});
		
	}
	//���Ԥ��ͼƬ��ƴͼͼƬ
	private void addPreviewImage() {
		//����һ�����,����һ��ƴͼ����Ԥ����
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));//���ò���Ϊ��񲼾�
		canvas = new PictureCanvas();
		canvas.setBorder(new TitledBorder("ƴͼ��"));//��ӱ߿�
		preview = new PicturePreview();
		preview.setBorder(new TitledBorder("Ԥ����"));//��ӱ߿�
		//��ƴͼ����ͼƬԤ������ӵ��м�������
		panel.add(canvas,BorderLayout.WEST);//���
		panel.add(preview,BorderLayout.EAST);//�ұ�
		
		//�������ʾ����������
		this.add(panel,BorderLayout.CENTER);//������ʾ
	}

	//������
	private void addComponent() {
		//�����������������Ϸ���ʾ����壬�������Ҫ������ť������Ϸ״̬��
		JPanel panel = new JPanel();
//		panel.setBackground(Color.PINK);//�������ı���ɫ
		panel.setLayout(new GridLayout(1,2));
		
		//������ߵİ�ť�����
		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(new TitledBorder("��ť��"));//��ӱ߿�
		leftPanel.setBackground(Color.PINK);//���ö�Ӧ�ı���ɫ
		
		addNumInfo = new JRadioButton("������ʾ",false);
		clearNumInfo = new JRadioButton("�����ʾ",true);
		//��Ӱ�ť��
		ButtonGroup buttonGroup = new ButtonGroup();
		box = new JComboBox<String>(items);
		start = new JButton("start");
		panel.add(leftPanel,BorderLayout.WEST);//���
		
		//��ӵ�ѡ��ť����ť����
		buttonGroup.add(addNumInfo);
		buttonGroup.add(clearNumInfo);
		//���ñ���ɫ
		addNumInfo.setBackground(Color.PINK);
		clearNumInfo.setBackground(Color.PINK);
		start.setBackground(Color.PINK);
		
		//�������������
		leftPanel.add(addNumInfo);
		leftPanel.add(clearNumInfo);
		leftPanel.add(new JLabel("                ѡ��ͼƬ"));
		leftPanel.add(box);
		leftPanel.add(start);
		//---------------------------------------
		//�����ұߵİ�ť�����
		JPanel rightPanel = new JPanel();
		rightPanel.setBorder(new TitledBorder("��Ϸ״̬"));//��ӱ߿��Ч��
		rightPanel.setBackground(Color.PINK);//���ñ���ɫ
		rightPanel.setLayout(new GridLayout(1,2));//���ò���Ϊ��񲼾�
		
		name = new JTextField("ͼƬ���ƣ�СŮ��");
		step = new JTextField("������0");
		
		//�����ı����ܱ༭
		name.setEditable(false);
		step.setEditable(false);
		
		//�������ӵ���Ϸ״̬�����
		rightPanel.add(name,BorderLayout.WEST);//���
		rightPanel.add(step,BorderLayout.EAST);//�ұ�
			
		panel.add(rightPanel,BorderLayout.EAST);//�ұ�
		
		//----------------------------------------------
		//����panel����������Ϸ���ʾ
		this.add(panel,BorderLayout.NORTH);//����
	}

	//�����ʼ������
	private void init(){
		//���ô��ڵı���
		this.setTitle("ƴͼ��Ϸ");
		//���ô��ڵĴ�С
		this.setSize(1000,720);
		//���ô��ڵ���ʾλ��
		this.setLocation(150,10);
		//���ô��ڵĴ�С
		this.setResizable(false);
		//���ô��ڵ�Ĭ�Ϲرղ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
